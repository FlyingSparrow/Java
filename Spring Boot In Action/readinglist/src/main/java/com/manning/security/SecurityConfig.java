package com.manning.security;

import org.springframework.context.annotation.Configuration;

/**
 * @author wangjianchun
 * @create 2018/4/27
 */
@Configuration
//@EnableWebSecurity
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

    /*@Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/")
            .access("hasRole('READER')")
            .antMatchers("*//**").permitAll()
            .and()
            .formLogin().loginPage("/login")
            .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)
                    throws UsernameNotFoundException {
                return readerRepository.findOne(username);
            }
        });
    }*/
}
