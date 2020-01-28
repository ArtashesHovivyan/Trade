package am.trade.tradeappapi.config;

import am.trade.tradeappapi.security.JwtAuthenticationEntryPoint;
import am.trade.tradeappapi.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/rest/users/{id}").hasAnyAuthority("admin")
                .antMatchers(HttpMethod.POST, "/rest/category").hasAnyAuthority("admin")
                .antMatchers(HttpMethod.POST, "/rest/category/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/rest/category/{id}").hasAnyAuthority("admin")
                .antMatchers(HttpMethod.POST, "/rest/users").permitAll()
                .antMatchers(HttpMethod.PUT, "/rest/users/addImage/{userId}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/rest/users/{id}").hasAnyAuthority("admin")
//                .antMatchers(HttpMethod.GET, "/rest/people/{id}").permitAll()
//                .antMatchers(HttpMethod.GET, "/rest/people/phoneoremail/{name}").permitAll()
                .antMatchers(HttpMethod.POST, "/rest/people").permitAll()
                .antMatchers(HttpMethod.DELETE, "/rest/people/*").permitAll()
                .anyRequest().permitAll();

        // Custom JWT based security filter
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().cacheControl();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }


}