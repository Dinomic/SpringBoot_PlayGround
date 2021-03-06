package Personal.Dinomic.SpringBoot_PlayGround.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.persistence.criteria.CriteriaBuilder;

@Configuration
//@EnableWebSecurity // (1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // (1)

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // (2)
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll() // (3)
                .anyRequest().authenticated() // (4)
                .and()
            .formLogin() // (5)
                .loginPage("/signIn") // (5)
                .permitAll()
                .and()
            .logout() // (6)
                .permitAll()
                .and()
            .httpBasic(); // (7)
        /*
        1.A normal Spring @Configuration with the @EnableWebSecurity annotation,
            extending from WebSecurityConfigurerAdapter.
        2.By overriding the adapter’s configure(HttpSecurity) method,
            you get a nice little DSL with which you can configure your FilterChain.
        3.All requests going to / and /home are allowed (permitted) -
            the user does not have to authenticate. You are using an antMatcher,
            which means you could have also used wildcards (*, \*\*, ?) in the string.
        4.Any other request needs the user to be authenticated first,
            i.e. the user needs to login.
        5.You are allowing form login (username/password in a form),
            with a custom loginPage (/login, i.e. not Spring Security’s auto-generated one).
            Anyone should be able to access the login page, without having to log in first
            (permitAll; otherwise we would have a Catch-22!).
        6.The same goes for the logout page
        7.On top of that, you are also allowing Basic Auth,
            i.e. sending in an HTTP Basic Auth Header to authenticate.
         */
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                .username("abc")
                .password("abc")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}