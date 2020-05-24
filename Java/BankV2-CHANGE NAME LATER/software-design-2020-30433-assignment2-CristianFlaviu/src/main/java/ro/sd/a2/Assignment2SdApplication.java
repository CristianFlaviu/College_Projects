package ro.sd.a2;

import ch.qos.logback.classic.boolex.GEventEvaluator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.crypto.bcrypt.BCrypt;
import ro.sd.a2.entity.GeneralAccount;
import ro.sd.a2.entity.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication()
@EntityScan(basePackages = { "ro.sd.a2.entity" })

public class Assignment2SdApplication {

    public static void main(String[] args) {

   SpringApplication.run(Assignment2SdApplication.class, args);


//        User user=new User();
//        user.setPassword(user.hashPassword("123"));
//
//        System.out.println(user.getPassword());
//        user.checkPass("123",user.getPassword());



    }

}
