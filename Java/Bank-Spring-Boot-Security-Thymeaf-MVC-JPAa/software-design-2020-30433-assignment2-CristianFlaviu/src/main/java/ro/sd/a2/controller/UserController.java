package ro.sd.a2.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.sd.a2.dto.BriefAccDto;
import ro.sd.a2.dto.CurrencyDto;
import ro.sd.a2.dto.LoginDTO;
import ro.sd.a2.entity.*;
import ro.sd.a2.messages.RootMessages;
import ro.sd.a2.service.*;
import ro.sd.a2.util.GenerateCSV;
import ro.sd.a2.util.GenerateFile;
import ro.sd.a2.util.GeneratePDF;


import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;


@Controller
public class UserController implements WebMvcConfigurer {

    @Autowired
    private UserService userService;

    @Autowired
    private AdressService adressService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private GeneralAccountService generalAccountService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TranzactionService tranzactionService;

    @Autowired
    private EmailService emailService;


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView userMainViewGet() {
        return new ModelAndView("HomeUser");
    }

    @RequestMapping(value = "/user/mainPage", method = RequestMethod.GET)
    public ModelAndView showUserOp(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("UserMainView");
        String aut_data = authentication.getName();
        User user = aut_data.contains("@") ?
                userService.getByEmail(aut_data)
                : userService.getByUsername(aut_data);

        modelAndView.addObject("name", user.getFirstName());

        return modelAndView;
    }


    @RequestMapping(value = "/user/addMoneySave", method = RequestMethod.GET)
    public ModelAndView userAddMoneySave(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("AddAccSave");

        User user = authentication.getName().contains("@") ?
                userService.getByEmail(authentication.getName())
                : userService.getByUsername(authentication.getName());


        List<GeneralAccount> listAcc = user.getGeneralAccountSet().stream().filter(a -> a.getType().equals(GeneralAccount.SavingAccout)).collect(Collectors.toList());


        if(listAcc.size()>0)
        {
            modelAndView.addObject("existAcc","error");

        }

        modelAndView.addObject("list", listAcc);
        return modelAndView;

    }

    @RequestMapping(value = "/user/addMoneySave", method = RequestMethod.POST)
    public ModelAndView userAddMoneySavePost(Authentication authentication, @RequestParam(value = "suma") String suma, @RequestParam(value = "account_id") int account_id, @RequestParam("month") String month) {
        ModelAndView modelAndView = new ModelAndView("AddAccSave");
        User user = authentication.getName().contains("@") ?
                userService.getByEmail(authentication.getName())
                : userService.getByUsername(authentication.getName());

        List<GeneralAccount> listAcc = user.getGeneralAccountSet().stream().filter(a -> a.getType().equals(GeneralAccount.SavingAccout)).collect(Collectors.toList());

        System.out.println(listAcc.size());
        if(listAcc.size()>0)
        {
            modelAndView.addObject("existAcc","error");

        }

        modelAndView.addObject("list", listAcc);

        try {
            if (suma.equals(""))
                modelAndView.addObject("sumError", "empty field");
            if (month.equals(""))
                modelAndView.addObject("monthError", "empty field");

            int suma_int = Integer.valueOf(suma);
            int month_int = Integer.valueOf(month);

            if (suma_int < 0) {
                modelAndView.addObject("sumError", "negative suma");
                return modelAndView;
            }
            if (month_int < 0) {
                modelAndView.addObject("monthError", "negative suma");
                return modelAndView;
            }
            GeneralAccount generalAccount = generalAccountService.getGeneralAccountById(account_id);
            generalAccount.saveMoney(suma_int,month_int);
            generalAccountService.insertGeneralAccount(generalAccount);

        } catch (Exception e) {

            modelAndView.addObject("convError", "invalid month or sum format type");
        }

        return modelAndView;

    }

    @RequestMapping(value = "/user/addMoneySpend", method = RequestMethod.GET)
    public ModelAndView addMoneySpendGet(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("AddAccSpend");

        User user = authentication.getName().contains("@") ?
                userService.getByEmail(authentication.getName())
                : userService.getByUsername(authentication.getName());

        List<GeneralAccount> listAcc = user.getGeneralAccountSet().stream().filter(a -> a.getType().equals(GeneralAccount.SpendingAccount)).collect(Collectors.toList());
        if(listAcc.size()>0)
        {
            modelAndView.addObject("existAcc","exist");

        }

        modelAndView.addObject("list", listAcc);


        return modelAndView;
    }

    @RequestMapping(value = "/user/addMoneySpend", method = RequestMethod.POST)
    public ModelAndView addMoneySpend(Authentication authentication, @RequestParam(value = "nr") String nr, @RequestParam(value = "account_id", defaultValue = "1") int account_id) {
        ModelAndView modelAndView = new ModelAndView("AddAccSpend");

        User user = authentication.getName().contains("@") ?
                userService.getByEmail(authentication.getName())
                : userService.getByUsername(authentication.getName());

        List<GeneralAccount> listAcc = user.getGeneralAccountSet().stream().filter(a -> a.getType().equals(GeneralAccount.SpendingAccount)).collect(Collectors.toList());


        if(listAcc.size()>0)
        {
            modelAndView.addObject("existAcc","exist");

        }

        modelAndView.addObject("list", listAcc);

        try {
            int suma;

            System.err.println(nr);
            if (nr.equals("")) {

                modelAndView.addObject("sumError", "empty field");
                return modelAndView;
            }
            suma = Integer.valueOf(nr);

            if (suma < 0) {
                modelAndView.addObject("sumError", "negative suma");
                return modelAndView;
            }
            GeneralAccount generalAccount = generalAccountService.getGeneralAccountById(account_id);
            generalAccount.saveMoney(suma,0);
            generalAccountService.insertGeneralAccount(generalAccount);

        } catch (Exception e) {
            modelAndView.addObject("sumError", "invalid suma");

        }
        return modelAndView;
    }

    @RequestMapping("/user/briefAcc")
    public ModelAndView briefAcc(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("BriefAccDetails");
        User user = authentication.getName().contains("@") ?
                userService.getByEmail(authentication.getName())
                : userService.getByUsername(authentication.getName());



        List<BriefAccDto> list = user.getGeneralAccountSet().stream().map(a -> new BriefAccDto(a)).collect(Collectors.toList());

        if(list.size()>0)
        {
            modelAndView.addObject("existAcc","exist");
        }
        modelAndView.addObject("list", list);
        return modelAndView;
    }
    @RequestMapping("/user/detailsAcc")
    public ModelAndView AccDetails(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("ViewDetailedAccount");
        User user = authentication.getName().contains("@") ?
                userService.getByEmail(authentication.getName())
                : userService.getByUsername(authentication.getName());

        List<GeneralAccount> list = new ArrayList<>(user.getGeneralAccountSet());
        if(list.size()>0)
            modelAndView.addObject("exist","a");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @RequestMapping(value = "/user/newAcc", method = RequestMethod.GET)
    public ModelAndView newAccGet(Authentication authendication) {
        ModelAndView modelAndView = new ModelAndView("NewAccount");
        List<CurrencyDto> list = currencyService.getAll().stream().map(a -> new CurrencyDto(a)).collect(Collectors.toList());
        modelAndView.addObject("list", list);

        return modelAndView;
    }

    @RequestMapping(value = "/user/newAcc", method = RequestMethod.POST)
    public ModelAndView newAccPost(@ModelAttribute("currency_id") String currency_id, @ModelAttribute("type_acc") String type_Acc, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("NewAccount");

        List<CurrencyDto> list = currencyService.getAll().stream().map(a -> new CurrencyDto(a)).collect(Collectors.toList());
        modelAndView.addObject("list", list);

        User user = authentication.getName().contains("@") ?
                userService.getByEmail(authentication.getName())
                : userService.getByUsername(authentication.getName());

        Currency currency = currencyService.getById(currency_id);

        GeneralAccount generalAccount = AccountFactory.makeAccount(type_Acc);


        generalAccount.setUser(user);
        generalAccount.setCurrency(currency);
        generalAccount.setIBan();

        System.err.println(type_Acc);
        System.err.println(generalAccount.getType());

        List<GeneralAccount> accountList=generalAccountService.getAllByUserTypeCurrency(user, generalAccount.getType(), generalAccount.getCurrency());

        System.err.println(accountList.size());
        if (generalAccountService.getAllByUserTypeCurrency(user, generalAccount.getType(), generalAccount.getCurrency()).size() > 2) {

            modelAndView.addObject("errorAcc", "to many accounts of this type");
            return modelAndView;
        }
        generalAccountService.insertGeneralAccount(generalAccount);

        return modelAndView;
    }

    @RequestMapping(value = "/user/newUser", method = RequestMethod.GET)
    public ModelAndView newUserPost(@ModelAttribute("user") User user, @RequestParam(name = "address_id", defaultValue = "default-id") String address_id) {

        ModelAndView modelAndView = new ModelAndView("NewUser");
        modelAndView.addObject("adresses", adressService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/user/newUser", method = RequestMethod.POST)
    public ModelAndView newUserGet(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @RequestParam(name = "address_id", defaultValue = "default-id") String address_id, @RequestParam(name = "birthDate") String birthDate) {

        ModelAndView modelAndView = new ModelAndView("NewUser");
        boolean exist_error = false;
        modelAndView.addObject("adresses", adressService.getAll());

        if (userService.getByUsername(user.getUsername()) != null) {
            modelAndView.addObject("usernameError", "username already taken");
            exist_error = true;
        }
        if (userService.getByEmail(user.getEmail()) != null) {
            modelAndView.addObject("emailError", "there exist an account on this email ");
            exist_error = true;
        }
        if (userService.getByAdress(adressService.getAdressById(address_id)) != null) {
            modelAndView.addObject("adressError", "adress already exists ");
            exist_error = true;
        }
        if(user.getPassword().length()<5)
        {
            modelAndView.addObject("passError", "invalid password min lenght 5 ");
            exist_error = true;
        }


        if (exist_error)
        { return modelAndView;}

        try {
            user.setId(UUID.randomUUID().toString());
            user.setAdmin(false);
            int day = Integer.valueOf(birthDate.substring(0, 2));
            int month = Integer.valueOf(birthDate.substring(3, 5));
            int year = Integer.valueOf(birthDate.substring(6, 10));

            LocalDate birdDate = LocalDate.of(year, month, day);
            int age = Period.between(birdDate, LocalDate.now()).getYears();
            user.setAge(age);
            user.setBirthDate(birdDate);
            user.setAddress(adressService.getAdressById(address_id));
            user.setResetPassCode(UUID.randomUUID().toString());


            userService.insertUser(user);
        } catch (Exception e) {
            modelAndView.addObject("birthDateError", "invalid format");
            return modelAndView;
        }
        return new ModelAndView("redirect:/user");
    }


    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public ModelAndView showForm1(LoginDTO loginDTO) {

        ModelAndView model = new ModelAndView("Login");

        return model;
    }

    @PostMapping(value = "/log", params = "action=login")
    public ModelAndView tryLogin(@Valid LoginDTO loginDTO, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView("Login");
        if (bindingResult.hasErrors()) {
            System.out.println("errors");

        } else {
            User user = userService.getByUsername(loginDTO.getUsername());
            if (user == null) {
                model.addObject("logError", RootMessages.NULL_USERNAME);
            } else {
                if (!user.getPassword().equals(loginDTO.getPassword()))
                    model.addObject("logError", RootMessages.INCORRECT_COMBINATION);

                else {
                    return new ModelAndView("/user/mainView");
                }
            }

        }


        return model;
    }

    @RequestMapping(value = "/log", params = "action=newUser", method = RequestMethod.POST)
    public ModelAndView goToCreateUser(@Valid LoginDTO loginDTO, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView("redirect:/user/newUser");
        return model;
    }

    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.GET)
    public ModelAndView resetPassGEt() {
        ModelAndView modelAndView = new ModelAndView("ResetPassword");

        return modelAndView;
    }

    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
    public ModelAndView resetPassPost(String code, String new_pass) {
        ModelAndView modelAndView = new ModelAndView("ResetPassword");


        User user = userService.getByResetPassCode(code);
        if (user == null) {
            modelAndView.addObject("codeError", "wrong code ");

        } else {

            if (new_pass.length() < 5 || new_pass.length() > 15) {
                modelAndView.addObject("newPass_error", "size of the password should be between 5 and 15");
            } else {
                user.setPassword(new_pass);
                user.setResetPassCode(UUID.randomUUID().toString());
                userService.insertUser(user);
            }
        }


        return modelAndView;
    }

    @RequestMapping(value = "user/emailResetPassword", method = RequestMethod.GET)
    public ModelAndView sendResetPassEmailGet() {
        return new ModelAndView("ResetPassSendEmail");
    }


    @RequestMapping(value = "user/emailResetPassword", method = RequestMethod.POST)
    public ModelAndView sendResetPassEmail(String email) {
        ModelAndView modelAndView = new ModelAndView("ResetPassSendEmail");
        Random random = new Random();
        User user = userService.getByEmail(email);
        if (user == null) {
            modelAndView.addObject("emailError", "this email doest not exist in our database");

            return modelAndView;
        } else {

            String code = UUID.randomUUID().toString();


            String message = "In order to reset your password access this link "
                    + "http://localhost:7799/app/user/resetPassword " +
                    "with the following code: " + code;

            user.setResetPassCode(code);

            userService.insertUser(user);

            String subject = "Reset password";

            emailService.sendNotification(message, subject, user.getEmail());
        }

        return new ModelAndView("redirect:/user/resetPassword");
    }

    @RequestMapping(value = "/empty", method = RequestMethod.GET)
    public ModelAndView empthyMethod() {
        ModelAndView modelAndView = new ModelAndView("EmptyHtml");

        Address address = new Address();
        adressService.insertAdress(address);

        User user = new User();
        user.setAddress(address);
        userService.insertUser(user);

        Currency currency = new Currency();
        currencyService.insertCurrency(currency);

        GeneralAccount generalAccount = new SavingAccount();
        generalAccount.setCurrency(currency);
        generalAccount.setUser(user);
        generalAccountService.insertGeneralAccount(generalAccount);

        Company company = new Company();
        company.setCurrency(currency);
        companyService.insertCompany(company);

        Factura factura = new Factura();
        factura.setCompany(company);
        factura.setUser(user);
        facturaService.insertFactura(factura);

        Tranzaction tranzaction = new Tranzaction();
        tranzaction.setFactura(factura);
        tranzactionService.insertTranzaction(tranzaction);

        return modelAndView;
    }


    @RequestMapping(value = "/user/payFactura", method = RequestMethod.GET)
    public ModelAndView payFactura( Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("PayFactura");

        User user = authentication.getName().contains("@") ?
                userService.getByEmail(authentication.getName())
                : userService.getByUsername(authentication.getName());

        List<GeneralAccount> list =new ArrayList<>(user.getGeneralAccountSet());
        modelAndView.addObject("listAcc", list);

        List<Factura> facturaList=new ArrayList<>(user.getFacturaSet());
        modelAndView.addObject("listFac",facturaList);


        GenerateFile generateFile=new GenerateCSV();



        try {
            generateFile.generateFile(tranzactionService.getAll());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/user/payFactura", method = RequestMethod.POST)
    public ModelAndView payFacturaPost(Authentication authentication,String facc_id,String acc_id) {
        ModelAndView modelAndView = new ModelAndView("PayFactura");

        User user = authentication.getName().contains("@") ?
                userService.getByEmail(authentication.getName())
                : userService.getByUsername(authentication.getName());

        List<GeneralAccount> list =new ArrayList<>(user.getGeneralAccountSet());
        modelAndView.addObject("listAcc", list);


        List<Factura> facturaList=new ArrayList<>(user.getFacturaSet());
        modelAndView.addObject("listFac",facturaList);



        Factura factura=facturaService.getById(facc_id);
        GeneralAccount generalAccount=generalAccountService.getGeneralAccountById(Integer.valueOf(acc_id));


        int suma_de_platiti=factura.getSumaBani()*factura.getCompany().getCurrency().getUnitValue();

        generalAccount.spendMoney(suma_de_platiti);

        generalAccountService.insertGeneralAccount(generalAccount);

        Tranzaction tranzaction=new Tranzaction();

        if(tranzactionService.getByFactura(factura)!=null)
        {
            System.out.println("factura deja platitita");
            modelAndView.addObject("facturaPaid","Factura already paid");
        }
        else
            {
            tranzaction.setFactura(factura);
            tranzaction.setGeneralAccount(generalAccount);

            tranzactionService.insertTranzaction(tranzaction);


        }


        return modelAndView;
    }
}
