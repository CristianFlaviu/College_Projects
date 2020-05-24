package ro.sd.a2.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.dto.CompanyDto;
import ro.sd.a2.dto.CurrencyDto;
import ro.sd.a2.dto.EmailDTO;
import ro.sd.a2.dto.UsertDto;
import ro.sd.a2.entity.*;
import ro.sd.a2.service.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class AdminController {


    @Autowired
    private UserService userService ;

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


    @RequestMapping(value = "/logout-success")
    public ModelAndView logouget()
    {
        ModelAndView modelAndView=new ModelAndView("Logout");

        modelAndView.addObject("logSucc","you have loggedOut succesfully");

        return modelAndView;
    }
    @RequestMapping(value = "/logout")
    public ModelAndView logSucces()
    {
        ModelAndView modelAndView=new ModelAndView("Logout");

        modelAndView.addObject("logTry","you are trying to log out");

        return modelAndView;
    }


    @RequestMapping(value = "/admin",method =RequestMethod.GET)
    public ModelAndView adminMainGet()
    {
        ModelAndView modelAndView=new ModelAndView("MainAdminPage");


        return modelAndView;
    }
    @RequestMapping(value = "/admin/newCompany", method = RequestMethod.GET)
    public ModelAndView newCompanyget(@ModelAttribute("company") @Valid Company company , BindingResult bindingResult, @RequestParam(name="currency_id" ,defaultValue = "default-id") String currency_id)
    {
        ModelAndView modelAndView=new ModelAndView("NewCompany");

        modelAndView.addObject("list",currencyService.getAll());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/newCompany", method = RequestMethod.POST)
    public ModelAndView newCompanyPost(@ModelAttribute("company") @Valid CompanyDto companyDto , BindingResult bindingResult)
    {
        ModelAndView modelAndView=new ModelAndView("NewCompany");
        List<CurrencyDto> currencyDtoList=currencyService.getAll().stream().map(a->new CurrencyDto(a)).collect(Collectors.toList());

        modelAndView.addObject("list",currencyDtoList);

        if(bindingResult.hasErrors())
        {
            System.out.println("errors");
        }
        else
        {
            Company company=new Company();
            company.setId(UUID.randomUUID().toString());
            company.setName(companyDto.getName());
            company.setCurrency(currencyService.getById(companyDto.getCurrency_id()));

            companyService.insertCompany(company);
            return new ModelAndView("redirect:/admin");
        }
        return modelAndView;

    }

    @RequestMapping(value = "/admin/newFactura",method = RequestMethod.GET)
    public ModelAndView newFacturaGet( @ModelAttribute("user_id") String user_id)
    {
        ModelAndView modelAndView=new ModelAndView("NewFactura");

        List<UsertDto> list=userService.getAllUser().stream().map(a->new UsertDto(a)).collect(Collectors.toList());

        modelAndView.addObject("list",list);

        return modelAndView;
    }


    @RequestMapping(value = "/admin/newFactura",method = RequestMethod.POST)
    public ModelAndView adminCreateFacturaPost( @ModelAttribute("user_id") String user1_id,String nr_facturi)
    {
        ModelAndView modelAndView=new ModelAndView("NewFactura");
        List<UsertDto> list=userService.getAllUser().stream().map(a->new UsertDto(a)).collect(Collectors.toList());

        modelAndView.addObject("list",list);

        Factura factura=new Factura();
        factura.setUser(userService.getById(user1_id));

        List<Company> companyList=companyService.getAll();
        Random random=new Random();
        try {
            int number=Integer.valueOf(nr_facturi);
            if(number<=0)
            {

                throw new Exception("negativ number");
            }
            for(int i=0;i<number;i++)
            {   int intexRandCompany=random.nextInt(companyList.size());
                factura.setCompany(companyList.get(intexRandCompany));
                factura.setSumaBani(random.nextInt(500));
                factura.setId(UUID.randomUUID().toString());
                facturaService.insertFactura(factura);
            }

        }catch (Exception e)
        {
            modelAndView.addObject("logError","wrong number of facturi");

            return modelAndView;
        }

        return modelAndView;
    }

    @RequestMapping(value = "/admin/newEmail", method = RequestMethod.GET)
    public ModelAndView newEmailGet(@ModelAttribute("emailDTO")  EmailDTO emailDTO )
    {
        ModelAndView modelAndView=new ModelAndView("SendEmail");

        List<UsertDto> userList=userService.getAllUser().stream().map(a->new UsertDto(a)).collect(Collectors.toList());
        modelAndView.addObject("list",userList);


        return modelAndView;
    }

    @RequestMapping(value = "/admin/newEmail", method = RequestMethod.POST)
    public ModelAndView newEmailPost(@ModelAttribute("emailDTO")@Valid  EmailDTO emailDTO ,BindingResult bindingResult,@RequestParam("user_id")String user_id)
    {
        ModelAndView modelAndView=new ModelAndView("SendEmail");
        List<UsertDto> userList=userService.getAllUser().stream().map(a->new UsertDto(a)).collect(Collectors.toList());
        modelAndView.addObject("list",userList);

        if(bindingResult.hasErrors())
        {
            System.out.println("error");
        }
        else {
            String email=userService.getById(user_id).getEmail();
            emailService.sendNotification(emailDTO.getMessage(),emailDTO.getSubject(),email);
            return new ModelAndView("redirect:/admin");
        }

        return modelAndView;
    }


    @RequestMapping(value = "/admin/viewUsers",method =RequestMethod.GET)
    public ModelAndView viewUsers()
    {
        ModelAndView model= new ModelAndView("ViewUsers");

        List<UsertDto> usertDtoList=userService.getAllUser().stream().map(a->new UsertDto(a)).collect(Collectors.toList());
        model.addObject("users",usertDtoList);

        return model;

    }

    @RequestMapping(value = "/admin/notifyUsers",method = RequestMethod.GET)
    @ResponseBody
    public String notifyUser()
    {
        ModelAndView modelAndView=new ModelAndView();

        List<GeneralAccount> generalAccounts=generalAccountService.getAllBySumaLessThan(0);

        generalAccounts.stream().forEach(a->emailService.sendNotification("account with IBAN: "+a.getIBAN()+"has a negative amount of money",
                                                                            "BancaTa-Suma negativa",
                                                                                a.getUser().getEmail()));

        return "users have been notified";

    }

}
