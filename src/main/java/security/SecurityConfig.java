package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, password, active from users where user_id=?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }



    //to config security of web paths in application,login,logout
    @Bean
    //this gonna pass in a hp security that can make use of
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        //restrict access based on the http request
        http.authorizeHttpRequests(configuer->
                        configuer
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/users/buyers/**").hasRole("buyer")
                                .requestMatchers("/users/sellers/**").hasRole("seller")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )

                .formLogin(form->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticatedTheUser")
                                .permitAll()

                )
                .logout(logout->logout.permitAll()

                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );
        return http.build();
    }


}
