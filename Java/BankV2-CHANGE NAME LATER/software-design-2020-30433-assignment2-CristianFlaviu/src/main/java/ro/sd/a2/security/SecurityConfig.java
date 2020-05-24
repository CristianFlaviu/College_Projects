package ro.sd.a2.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import ro.sd.a2.entity.User;
import ro.sd.a2.service.UserService;

import javax.sql.DataSource;
import java.util.List;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/*").access("hasRole('ADMIN')")
                .antMatchers("/user/newUser").permitAll()
                .antMatchers("/user/resetPassword").permitAll()
                .antMatchers("/user/emailResetPassword").permitAll()
                .antMatchers("/user/*").access("hasRole('USER')")

                .and()
                .formLogin()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout-success").permitAll();


    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)      throws Exception
    {
        List<User> userList=userService.getAllUser();

        for(User user:userList)
        {
            if(user.isAdmin())
            { auth.inMemoryAuthentication().withUser(user.getUsername()).password(user.getPassword()).roles("ADMIN","USER");
                auth.inMemoryAuthentication().withUser(user.getEmail()).password(user.getPassword()).roles("ADMIN","USER");

            }
            else
            {
                auth.inMemoryAuthentication().withUser(user.getUsername()).password(user.getPassword()).roles("USER");
                auth.inMemoryAuthentication().withUser(user.getEmail()).password(user.getPassword()).roles("USER");
              }
        }

    }

    @Component
    public class AuthenticationEventListener {

        @EventListener
        public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {

            String username = (String) event.getAuthentication().getPrincipal();
            System.out.println(username);

        }

    }
    @Autowired
    DataSource dataSource;




    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}

