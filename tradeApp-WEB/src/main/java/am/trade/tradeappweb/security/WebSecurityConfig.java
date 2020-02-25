package am.trade.tradeappweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailServiceImpl")
    private UserDetailsService userDetailsService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/registerSection").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/order").permitAll()
//                .antMatchers("/mainPage").hasAnyAuthority("ADMIN")
//                .antMatchers("/").hasAuthority("ADMIN")
//                .antMatchers("/profile").hasAnyAuthority("ADMIN", "USER")
//                .antMatchers("/basket").hasAnyAuthority("ADMIN", "USER")
//                .antMatchers("/wishListALL").hasAnyAuthority("ADMIN", "USER")
//                .antMatchers("/admin").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .successForwardUrl("/mainPage")
                .failureForwardUrl("/log")
                .usernameParameter("login")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and();
    }
}
