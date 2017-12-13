package cat.tecnocampus.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String USERS_QUERY = "select username, password, enabled from user where username = ?";

    private static final String AUTHORITIES_QUERY = "select username, role from user_roles where username = ?";
	
	
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder();
    }
   
     //Configure authentication manager
     @Override
     public void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.jdbcAuthentication()
                 .dataSource(dataSource)
                 .usersByUsernameQuery(USERS_QUERY)
                 .authoritiesByUsernameQuery(AUTHORITIES_QUERY)
                 .passwordEncoder(passEncoder());
     }
    //Configure Spring security's filter chain
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    /*
    TODO 1 (Security): modify the matchers so that
        * /h2-console (and bellow), /stations, /byebye, api (and bellow) should be accessed by the whole world
        * /user (and bellow), /welcome should only be accessed by logged in users
        * /users (and bellow) should only be accessed by ADMIN users
        * The rest of links should only be accessed by logged in users
        * When a user logs in the system always redirects him to welcome. Whe want it to go to the protected resource he
          was trying to access (when he was not logged in yet)
     HINT: you may need to modify some of the already existing matchers, delete existing ones and/or add new ones
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .mvcMatchers("/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .mvcMatchers("/stations").permitAll()
                    .antMatchers("/byebye").permitAll()
                    .antMatchers("/api/**").permitAll()
                .and()
                .formLogin() //a login form is showed when no authenticated request
                    .successForwardUrl("/welcome")
                .and()
                .rememberMe()
                    .tokenValiditySeconds(2419200)
                    .key("fgc")
                .and()
                .logout()
                    .logoutSuccessUrl("/byebye"); //where to go when logout is successful

        //Required to allow h2-console work
        http
                .csrf().disable()
                .headers()
                .frameOptions().disable();
    }
}

