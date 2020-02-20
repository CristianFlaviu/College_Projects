package com.example.springrmiclient;

import java.rmi.RMISecurityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.example.springrmiclient.service.HelloWorldRMI;

@SpringBootApplication
public class SpringRmiClientApplication {
	
	//@Autowired
	static HelloWorldRMI helloWorldRMI;

	@Bean
    RmiProxyFactoryBean rmiProxy() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(HelloWorldRMI.class);
        bean.setServiceUrl("rmi://localhost:1099/helloworldrmi");
    	
        return bean;
    }
 
    @SuppressWarnings("deprecation")
	public static void main(String[] args) 
    {

    	helloWorldRMI = SpringApplication.run(SpringRmiClientApplication.class, args)
                                                .getBean(HelloWorldRMI.class);
 
        System.out.println("================Client Side ========================");
 
        //System.out.println(helloWorldRMI.sayHelloRmi("Sajal"));
    }
    
    public static HelloWorldRMI getHello() {
    	return helloWorldRMI;
    }

}
