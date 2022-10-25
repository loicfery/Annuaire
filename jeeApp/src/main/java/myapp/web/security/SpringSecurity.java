package myapp.web.security;

import myapp.dao.IDirectoryDao;
import myapp.model.XUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    IDirectoryDao dao;

    @Autowired
    UserDetailsService myUserDetailsService;

    @PostConstruct
    public void init() {
        var encoder = passwordEncoder();
        var aa = new XUser("aaa", encoder.encode("aaa"), Set.of("ADMIN", "USER"));
        var loic = new XUser("loic@email.com", encoder.encode("admin"), Set.of("ADMIN","USER"));
        var bastien = new XUser("bastien@email.com",encoder.encode("userx"),Set.of("USER"));

        dao.addXUser(bastien);
        dao.addXUser(aa);
        dao.addXUser(loic);
        System.out.println("--- INIT SPRING SECURITY");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//
                .antMatchers("/","/webjars/**" ,"/person/edit","/register","/password-forgot"//
                )
                .permitAll()//
                .antMatchers("/group/edit","/group/delete","/person/delete")//
                .hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/",true).permitAll()
                .and().logout().logoutUrl("/logout").deleteCookies("JSESSIONID").permitAll();

    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
