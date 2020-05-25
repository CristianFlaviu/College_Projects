package ro.utcn.sd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ro.utcn.sd.service.UserService;


/**
 * This class is used to provide security to the endpoints.
 * Controllers can also provide security  at each endpoint
 * (Please check the annotations @HasRole and @HasAnyRoles).
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailsService;

    /**
     * This is an easy example of hashed password.
     * Store this hashed password on database for a user, but when you perform login use 123123
     * $2a$10$0aISzamI0jBCVTxONzJlHOk7O7QS.XPFIheLVhXultVa9Ju7SarZ6
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Please read the following links to understand some key concepts:
     * https://www.baeldung.com/java-config-spring-security
     * https://www.programcreek.com/java-api-examples/?api=org.springframework.security.config.annotation.web.builders.HttpSecurity
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .authorizeRequests().antMatchers("/register/**").permitAll()
                .and().formLogin()
                .defaultSuccessUrl("/admin/allRooms")
                .failureUrl("/login?error=true").permitAll()
                .and().logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
//                .antMatchers("/admin/**").access("hasAuthority('ADMIN')")
//                .antMatchers("/user/**").access("hasAuthority('USER')")
                .anyRequest().permitAll();
        /**
         * Comment the above part and enable this one to disable security.
         * It is easier for development and testing.
         */
        //  http.authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
