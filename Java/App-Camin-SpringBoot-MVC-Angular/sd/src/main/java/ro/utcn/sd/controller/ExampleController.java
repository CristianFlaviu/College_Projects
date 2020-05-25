package ro.utcn.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.dto.ResponseDto;
import ro.utcn.sd.dto.UserExampleDto;
import ro.utcn.sd.entity.User;
import ro.utcn.sd.service.RoomService;
import ro.utcn.sd.service.UserService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;


    @GetMapping("/{id}")
    public UserExampleDto getUserById(@PathVariable String id) {


        User user=userService.getById(id);

        List<String> phones = Arrays.asList("07434411", "07665433", "02504434");
        return new UserExampleDto(user.getFirstName(), "39987", phones);
    }

    @GetMapping("/all")
    public List<UserExampleDto> getAll() {

        List<String> phones1 = Arrays.asList("07434411", "07665433", "02504434");
        UserExampleDto userExampleDto1 = new UserExampleDto("Billy", "39987", phones1);

        List<String> phones2 = Arrays.asList("066411");
        UserExampleDto userExampleDto2 = new UserExampleDto("Bob", "39987", phones2);

        List<String> phones3 = Arrays.asList("0347411", "02504434");
        UserExampleDto userExampleDto3 = new UserExampleDto("Radu", "39987", phones3);

        UserExampleDto userExampleDto4 = new UserExampleDto("Jhonny", "2", null);

        return Arrays.asList(userExampleDto1, userExampleDto2, userExampleDto3, userExampleDto4);
    }

//    @PostMapping("/test")
//    public ResponseEntity getUserById(@RequestBody UserExampleDto userExampleDto) {
//
//        /** Most used responses for positive flows:
//         * HttpStatus.OK - 200
//         * HttpStatus.CREATED - 201
//         */
//        if (userExampleDto.getId().equals("123")) {
//            //log succes......
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new ResponseDto("Cool. Your ID is accepted!", "INFORMATION_MESSAGE"));
//        }
//
//
//        /** Most used responses for negative flows:
//         * HttpStatus.BAD_REQUEST
//         * HttpStatus.NOT_ACCEPTED
//         * HttpStatus.CONFLICT
//         * HttpStatus.NOT_MODIFIED
//         */
//
//        // log.error("atee....").
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(new ResponseDto("Invalid ID:" + userExampleDto.getId() + " .Please check again!", "ERROR_MESSAGE")); // or warning
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(){

        return null;
    }

}
