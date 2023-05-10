package com.kh.baribari.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    private final AuthenticationFailureHandler customFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/register*").permitAll()
                .antMatchers("/shopping/scrap/add").hasRole("USER")
                .antMatchers("/shopping/scarp/remove").hasRole("USER")
                .antMatchers("/review/register").hasRole("USER")
                .antMatchers("/review/remove").hasRole("USER")
                .antMatchers("/review/modify").hasRole("USER")
                .antMatchers("/shopping/register").hasRole("MANAGER")
                .antMatchers("/shopping/registerProduct").hasRole("MANAGER")
                .antMatchers("/shopping/modify").hasRole("MANAGER")
                .antMatchers("/shopping/delete").hasRole("MANAGER")
                .antMatchers("/shopping/mdRecommend").hasRole("ADMIN")
                .antMatchers("/notice/write").hasRole("ADMIN")
                .antMatchers("/notice/modify").hasRole("ADMIN")
                .antMatchers("/notice/delete").hasRole("ADMIN")
                .antMatchers("/qna/write").hasRole("ADMIN")
                .antMatchers("/qna/write").hasRole("USER")
                .antMatchers("/qna/delete").hasRole("ADMIN")
                .antMatchers("/qna/delete").hasRole("USER")
                .antMatchers("/admin*").hasRole("ADMIN")
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/myPageSeller/*").hasRole("MANAGER")
                .antMatchers("/myPageSeller*").hasRole("MANAGER")
                .antMatchers("/myPageUser/*").hasRole("USER")
                .antMatchers("/myPageUser*").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .failureHandler(customFailureHandler)
                .usernameParameter("userId")
                .passwordParameter("userPw")
                .defaultSuccessUrl("/", true)
//                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error403");

        return http.build();
    }



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/static/js/**","/static/css/**", "/static/img/**", "/static/**" , "/template/**","/static/assets/**");
    }

}
