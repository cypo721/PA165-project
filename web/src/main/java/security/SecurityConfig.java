package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



/**
 *  Marek Bohm
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AuthenticationProviderImpl authProvider;

    @Bean
    public AuthenticationProvider authProvider(){
        return new AuthenticationProviderImpl();
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                    .antMatchers("/", "/home/", "/machine/","/login/", "/accessdenied").permitAll()
//                    .antMatchers("/rental/list", "/revision/list").hasAnyAuthority("ADMIN", "CUSTOMER")
//                    .antMatchers("/user/list", "/machine/edit/**", "/machine/new", "/rental/new").hasAuthority("ADMIN")
//                    .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=invalid_attempt")
                .usernameParameter("email").passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
}