package ro.utcn.sd.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.Exceptions.*;
import ro.utcn.sd.constants.AppointmentType;
import ro.utcn.sd.constants.ClientMessages;
import ro.utcn.sd.constants.EndpointsConstant;
import ro.utcn.sd.constants.ServerMessages;
import ro.utcn.sd.dto.*;
import ro.utcn.sd.email.EmailSerivice;
import ro.utcn.sd.entity.*;
import ro.utcn.sd.export.ContextGenerator;
import ro.utcn.sd.export.CsvGenerator;
import ro.utcn.sd.export.PdfGenerator;
import ro.utcn.sd.mapper.AnnouncementMapper;
import ro.utcn.sd.mapper.AppointmentMapper;
import ro.utcn.sd.mapper.ReportMapper;
import ro.utcn.sd.mapper.UserMapper;
import ro.utcn.sd.service.*;
import ro.utcn.sd.util.ApiResponseFactory;
import ro.utcn.sd.util.AppointmentFactory;
import ro.utcn.sd.validator.AnnoucementValidator;
import ro.utcn.sd.validator.AppointmentValidator;
import ro.utcn.sd.validator.ReportValidator;
import ro.utcn.sd.validator.UserValidator;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final static Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private EmailSerivice emailSerivice;

    /**
     * EXPORT FILE
     */

    @GetMapping(EndpointsConstant.EXPORT + "{type}")
    public ResponseEntity generateFile(@PathVariable String type) {
        ContextGenerator contextGenerator;

        try {
            if (type.equals("pdf")) {
                contextGenerator = new ContextGenerator(new PdfGenerator());
                contextGenerator.executeGenerateFile(userService.getAll());
            } else if (type.equals("csv")) {
                contextGenerator = new ContextGenerator(new CsvGenerator());
                contextGenerator.executeGenerateFile(userService.getAll());

            }
            return ApiResponseFactory.createSucessMessage(null, "new file " + type + " genereted");
        } catch (Exception e) {
            return ApiResponseFactory.createErrorMessage(null, "error genereting the file", HttpStatus.BAD_REQUEST);
        }

    }


    /**
     * TOKEN
     */
    public String generateToken(User user) {

        String token = JWT.create()
                .withIssuer("auth0")
                .withSubject(user.getId())
                .withClaim("role", user.getAdmin() ? "admin" : "user")
                .withClaim("roomNumber", user.getRoom().getRoomNumber())
                .withClaim("name", user.getLastName() + " " + user.getLastName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86_400_000))
                .sign(HMAC256("SecretKeyToGenJWTs"));
        return token;
    }

    public String generateResetPassToken() {
        String token = JWT.create()
                .withIssuer("auth0")
                .withSubject("reset password")
                .withExpiresAt(new Date(System.currentTimeMillis() + 6_400_000))
                .sign(HMAC256("SecretKeyToGenJWTs"));
        return token;
    }


    @PostMapping(EndpointsConstant.RESET_PASSWORD_GENERATE_TOKEN)
    public ResponseEntity getResetToken(@RequestBody UserDto userDto) {
        String generated_token = generateResetPassToken();

        User user = userService.getByEmail(userDto.getEmail());

        if (Objects.isNull(user)) {
            LOGGER.info("invalid email " + userDto.getEmail());


            return ApiResponseFactory.createErrorMessage(null, "invalid email", HttpStatus.BAD_REQUEST);

        }
        LOGGER.info("passToken changed for user" + user.getEmail() + " with token \n\n " + generated_token);
        userService.updateToken(user, generated_token);

        LOGGER.info("new token generated " + generated_token);
        emailSerivice.sendNotification(ClientMessages.RESET_PASSWORD_SUBJECT + generated_token, "RESET_PASSWORD", user.getEmail());
        return ApiResponseFactory.createSucessMessage(generated_token, "new token generated");

    }

    @PostMapping(EndpointsConstant.SET_NEW_PASSWORD)
    public ResponseEntity setNewPassword(@RequestBody UserDto userDto) {

        User user = userService.getByEmail(userDto.getEmail());
        String token = userDto.getId();

        if (Objects.isNull(user)) {
            return ApiResponseFactory.createErrorMessage(null, "This email does not exist in our database", HttpStatus.BAD_REQUEST);
        }

        if (user.getResetPassToken().equals(token)) {
            user.setResetPassToken("empty-token");
            user.setPassword(userDto.getPassword());
            userService.add(user);

            LOGGER.info("SUCCESS PASSWORD CHANGED");
            return ApiResponseFactory.createSucessMessage(userDto, "PASSWORD CHANGED");
        }
        LOGGER.info("EXPECTED TOKEN \n" + user.getResetPassToken() + "\n");
        LOGGER.info("INVALID token \n  " + token);


        return ApiResponseFactory.createErrorMessage(null, "TOKEN and Email doest not match", HttpStatus.NOT_ACCEPTABLE);

    }


    public ResponseEntity getTokenBearer(@RequestBody String token) {
        Algorithm algorithm = Algorithm.HMAC256("SecretKeyToGenJWTs");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        String subject = jwt.getSubject();


        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }


    @PostMapping(EndpointsConstant.LOGIN_USER)
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        User user = userService.getByEmail(email);

        if (Objects.isNull(user))
            return ApiResponseFactory.createErrorMessage(null, "this email does not exist in our database", HttpStatus.BAD_REQUEST);
        else {
            if (user.getPassword().equals(password) == false) {

                return ApiResponseFactory.createErrorMessage(null, "bad credintial", HttpStatus.BAD_REQUEST);
            }

            String token = generateToken(user);

            return ApiResponseFactory.createSucessMessage(token, "success");

        }
    }


    @PostMapping(EndpointsConstant.REGISTER_USER)
    public ResponseEntity register(@RequestBody UserDto userDto) {
        Room room = null;
        if (Objects.nonNull(userDto.getRoomNumber())&& !userDto.getRoomNumber().equals(""))
            room = roomService.getByNumber(userDto.getRoomNumber());
        else {
            LOGGER.warning(String.format(ServerMessages.NULL_ATTRIBUTE, "roomNumber"));
            return ApiResponseFactory.createErrorMessage(userDto,String.format(ServerMessages.NULL_ATTRIBUTE, "roomNumber") , HttpStatus.BAD_REQUEST);
        }
        String id = UUID.randomUUID().toString();
        User user = UserMapper.DtoToUser(userDto, id, room);

        try {
            UserValidator.validate(user, userService.getAll());
            userService.add(user);
            String clientMessage = String.format(ClientMessages.OBJECT_SUCCESFULLY_INSERTED, "user", id);
            String mailSubject = String.format(ClientMessages.ACCOUNT_CREATED_MESSAGE, user.getFirstName(), user.getUsername(), user.getPassword());

            emailSerivice.sendNotification(mailSubject, ClientMessages.ACCOUNT_CREATED_MESSAGE, user.getEmail());

            LOGGER.info(String.format(ServerMessages.SUCCESSFULLY_INSERTED, "user", user.getId()));
            return ApiResponseFactory.createSucessMessage(userDto, clientMessage);

        } catch (
                NotUniqueEmailException e1) {
            return ApiResponseFactory.createErrorMessage(null, ClientMessages.EMAIL_ALREADY_USED, HttpStatus.CONFLICT);
        } catch ( NotUniqueUsernameException e2) {
            return ApiResponseFactory.createErrorMessage(null, ClientMessages.USERNAME_ALREADY_USED, HttpStatus.CONFLICT);
        } catch ( MissingAtributeException e3) {
            return ApiResponseFactory.createErrorMessage(null, e3.getMessage(), HttpStatus.CONFLICT);
        } catch (NullPointerException e4) {
            //normally it should never get here
            return ApiResponseFactory.createErrorMessage(null, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    /**
     * Appointments
     */

    @GetMapping(EndpointsConstant.ALL_WATER_MACHINE_APPOINTMENTS)
    public ResponseEntity getAllWMApp() {

        List<AppointmentDto> allAppointment = appointmentService.getAllByType(AppointmentType.Water_Machine).stream().map(a -> new AppointmentDto(a)).collect(Collectors.toList());

        return ApiResponseFactory.createSucessMessage(allAppointment, ClientMessages.SUCCESS);
    }

    @GetMapping(EndpointsConstant.ALL_FOOTABALL_APPOINTMENTS)
    public ResponseEntity getAllFoootballApp() {
        List<AppointmentDto> allAppointment = appointmentService.getAllByType(AppointmentType.Fooball).stream().map(a -> new AppointmentDto(a)).collect(Collectors.toList());

        return ApiResponseFactory.createSucessMessage(allAppointment, ClientMessages.SUCCESS);
    }


    @GetMapping(EndpointsConstant.GET_WM_APPOINTMENT + "{id}")
    public ResponseEntity getWMApp(@RequestParam String id) {
        Appointment appointment = appointmentService.getById(id);
        String clientMessage;

        if (Objects.isNull(appointment)) {

            clientMessage = String.format(ClientMessages.OBJECT_NOT_EXISTS, "client", id);
            return ApiResponseFactory.createErrorMessage(null, clientMessage, HttpStatus.BAD_REQUEST);
        }


        return ApiResponseFactory.createSucessMessage(AppointmentMapper.AppointmentToDto(appointment), ClientMessages.SUCCESS);

    }

    @GetMapping(EndpointsConstant.GET_FOOTBALL_APPOINTMENT + "{id}")
    public ResponseEntity getFootballApp(@RequestParam String id) {
        Appointment appointment = appointmentService.getById(id);
        String clientMessage;
        if (Objects.isNull(appointment)) {

            clientMessage = String.format(ClientMessages.OBJECT_NOT_EXISTS, "client", id);
            return ApiResponseFactory.createErrorMessage(null, clientMessage, HttpStatus.BAD_REQUEST);

        }
        return ApiResponseFactory.createSucessMessage(AppointmentMapper.AppointmentToDto(appointment), ClientMessages.SUCCESS);

    }


    @PostMapping(EndpointsConstant.CREATE_WM_APPOINTMENT)
    public ResponseEntity createWMApp(@RequestBody AppointmentDto appointmentDto) {
        Room room = roomService.getByNumber(appointmentDto.getRoomNumber());
        String clientMessage;
        Appointment appointment = AppointmentFactory.createWMAppforRoom(appointmentDto, room);

        try {
            if (AppointmentValidator.validate(appointment, appointmentService.getAll())) {
                appointmentService.add(appointment);
                clientMessage = String.format(ClientMessages.OBJECT_SUCCESFULLY_INSERTED, "appointment", appointment.getId());
                LOGGER.info("Succesfully created WM app on " + appointment.getDay() + "  " + appointment.getStartHour());
                return ApiResponseFactory.createSucessMessage(appointmentDto, clientMessage);
            }

        } catch (MoreThanTwoAppoinmentsException e1) {
            return ApiResponseFactory.createErrorMessage(null, ClientMessages.MORE_THAN_TWO_APPOINTMENTS, HttpStatus.CONFLICT);
        } catch (DuplicateDataException e2) {
            return ApiResponseFactory.createErrorMessage(null, String.format(ClientMessages.DUPLICATE_APPOINTMENT, appointment.getDay(), appointment.getStartHour()), HttpStatus.CONFLICT);
        }

        clientMessage = ClientMessages.WRONG_DATA_INSERTED;

        LOGGER.info("wrong data inserted for WM App" + appointmentDto.getDay() + " " + appointment.getStartHour());
        return ApiResponseFactory.createErrorMessage(null, clientMessage, HttpStatus.NOT_ACCEPTABLE);

    }

    @PostMapping(EndpointsConstant.CREATE_FOTBALL_APPOINTMENT)
    public ResponseEntity createFootballApp(@RequestBody AppointmentDto appointmentDto) {
        User user = userService.getById("37");
        String clientMessage;

        Appointment appointment = AppointmentFactory.createFootballAppforRoom(appointmentDto, user.getRoom());

        if (AppointmentValidator.validate(appointment, appointmentService.getAll())) {
            appointmentService.add(appointment);
            clientMessage = String.format(ClientMessages.OBJECT_SUCCESFULLY_INSERTED, "appointment", appointment.getId());

            return ApiResponseFactory.createSucessMessage(appointmentDto, clientMessage);
        }
        clientMessage = ClientMessages.WRONG_DATA_INSERTED;

        return ApiResponseFactory.createErrorMessage(null, clientMessage, HttpStatus.BAD_REQUEST);


    }

    @DeleteMapping(EndpointsConstant.DELETE_APPOINTMENT + "{id}")
    public ResponseEntity deleteAppointment(@PathVariable String id) {
        Appointment appointment = appointmentService.getById(id);
        String clientMessage;
        if (Objects.isNull(appointment)) {
            clientMessage = String.format(ClientMessages.OBJECT_NOT_EXISTS, "appointment", id);

            return ApiResponseFactory.createErrorMessage(null, clientMessage, HttpStatus.BAD_REQUEST);
        }

        appointmentService.deleteById(id);
        clientMessage = String.format(ClientMessages.SUCCEFUL_DELETE, "appointment", id);
        List<AppointmentDto> listApp = appointmentService.getAll().stream().map(AppointmentMapper::AppointmentToDto).collect(Collectors.toList());
        return ApiResponseFactory.createSucessMessage(listApp, clientMessage);

    }

    /**
     * Reports
     */


    @GetMapping(EndpointsConstant.ALL_REPORTS)
    public ResponseEntity getAllReports() {
        List<ReportDto> listReports = reportService.getAll().stream().map(ReportMapper::ReportToDto).collect(Collectors.toList());

        return ApiResponseFactory.createSucessMessage(listReports, ClientMessages.SUCCESS);
    }

    @GetMapping(EndpointsConstant.GET_REPORTS_BY_ID + "{id}")
    public ResponseEntity getReportById(@PathVariable String id) {
        Report report = reportService.getById(id);
        String clientMessage;
        if (Objects.isNull(report)) {

            clientMessage = String.format(ClientMessages.OBJECT_NOT_EXISTS, "report", id);

            return ApiResponseFactory.createErrorMessage(null, clientMessage, HttpStatus.BAD_REQUEST);
        }

        return ApiResponseFactory.createSucessMessage(ReportMapper.ReportToDto(report), ClientMessages.SUCCESS);
    }


    @DeleteMapping(EndpointsConstant.DELETE_REPORT + "{id}")
    public ResponseEntity deleteReport(@PathVariable String id) {
        Report report = reportService.getById(id);
        String clientMessage;

        if (Objects.isNull(report)) {

            clientMessage = String.format(ClientMessages.OBJECT_NOT_EXISTS, "report", id);

            return ApiResponseFactory.createErrorMessage(null, clientMessage, HttpStatus.BAD_REQUEST);
        }
        reportService.deleteById(id);
        clientMessage = String.format(ClientMessages.SUCCEFUL_DELETE, "report", id);

        return ApiResponseFactory.createSucessMessage(reportService.getAll(), clientMessage);

    }


    @PostMapping(EndpointsConstant.CREATE_REPORT)
    public ResponseEntity createReport(@RequestBody ReportDto reportDto) {

        Room room = roomService.getByNumber(reportDto.getRoomNumber());

        if (Objects.isNull(room)) {
            LOGGER.info("bad roomNumber " + reportDto.getRoomNumber() + " for creating report");
            return ApiResponseFactory.createErrorMessage(null, ClientMessages.WRONG_DATA_INSERTED, HttpStatus.BAD_REQUEST);
        } else {
            String id = UUID.randomUUID().toString();
            Report report = ReportMapper.DtoToReport(reportDto, room, id);

            if (ReportValidator.validate(report)) {
                reportService.add(report);
                String clientMessage = String.format(ClientMessages.OBJECT_SUCCESFULLY_INSERTED, "report", id);
                LOGGER.info("new report created");
                return ApiResponseFactory.createSucessMessage(ReportMapper.ReportToDto(report), clientMessage);
            } else

                LOGGER.info("Bad data for creating report");
            return ApiResponseFactory.createErrorMessage(null, ClientMessages.WRONG_DATA_INSERTED, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(EndpointsConstant.GET_REPORTS_BY_ROOM_NUMBER + "{roomNumber}")
    public ResponseEntity getReportByRoomNumber(@PathVariable String roomNumber) {

        List<ReportDto> listReport = reportService.getAll().stream().filter(a -> a.getRoom().getRoomNumber().equals(roomNumber)).map(ReportMapper::ReportToDto).collect(Collectors.toList());


        return ApiResponseFactory.createSucessMessage(listReport, "here are all the reports");
    }


    /**
     * Announcements
     */
    @GetMapping(EndpointsConstant.ALL_ANNOUCEMENT)
    public ResponseEntity getAllAnnoucements() {
        List<AnnouncementDto> dtoList = announcementService.getAll().stream().map(AnnouncementMapper::AnnouncementToDto).collect(Collectors.toList());

        return ApiResponseFactory.createSucessMessage(dtoList, ClientMessages.SUCCESS);

    }

    @GetMapping(EndpointsConstant.GET_ANNOUNCEMENT_BY_ID + "{id}")
    public ResponseEntity getAnnoucementById(@PathVariable String id) {
        Announcement announcement = announcementService.getById(id);
        String clientMessages;
        if (Objects.isNull(announcement)) {

            clientMessages = String.format(ClientMessages.OBJECT_NOT_EXISTS, "announcement", id);
            return ApiResponseFactory.createErrorMessage(null, clientMessages, HttpStatus.BAD_REQUEST);
        }

        return ApiResponseFactory.createSucessMessage(AnnouncementMapper.AnnouncementToDto(announcement), ClientMessages.SUCCESS);
    }

    @PostMapping(EndpointsConstant.CREATE_ANNOUNCEMENT)
    public ResponseEntity createAnnouncement(@RequestBody AnnouncementDto announcementDto) {

        User user = userService.getById("37");
        Room room = user.getRoom();
        Announcement announcement = AnnouncementMapper.DtoToAnnoucement(announcementDto, UUID.randomUUID().toString(), user);

        if (AnnoucementValidator.validate(announcement)) {
            announcementService.add(announcement);

            String clientMessage = String.format(ClientMessages.OBJECT_SUCCESFULLY_INSERTED, "announcement", announcement.getId());
            return ApiResponseFactory.createSucessMessage(announcementDto, clientMessage);
        }


        return ApiResponseFactory.createErrorMessage(null, ClientMessages.WRONG_DATA_INSERTED, HttpStatus.NOT_ACCEPTABLE);

    }

    @DeleteMapping(EndpointsConstant.DELETE_ANNOUNCEMENT + "{id}")
    public ResponseEntity deleteAnnouncement(@PathVariable String id) {
        Announcement announcement = announcementService.getById(id);
        String clientMessage;

        if (Objects.isNull(announcement)) {
            clientMessage = String.format(ClientMessages.OBJECT_NOT_EXISTS, "annoucement ", id);
            return ApiResponseFactory.createErrorMessage(null, clientMessage, HttpStatus.BAD_REQUEST);
        }

        announcementService.deleteById(id);
        clientMessage = String.format(ClientMessages.SUCCEFUL_DELETE, "announcement", id);


        return ApiResponseFactory.createSucessMessage(appointmentService.getAll(), clientMessage);
    }


}
