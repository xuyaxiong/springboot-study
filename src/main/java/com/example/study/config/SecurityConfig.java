package com.example.study.config;

import com.example.study.service.UserService;
import com.example.study.utils.AjaxResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler((req, res, authentication) -> {
                    res.setContentType("application/json;charset=utf-8");
                    PrintWriter out = res.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(authentication.getPrincipal()));
                    out.flush();
                    out.close();
                })
                .failureHandler((req, res, exception) -> {
                    res.setContentType("application/json;charset=utf-8");
                    PrintWriter out = res.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(AjaxResponse.failure(-1, exception.getMessage())));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler((req, res, authentication) -> {
                    res.setContentType("application/json;charset=utf-8");
                    PrintWriter out = res.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(AjaxResponse.success("注销成功")));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((req, res, exception) -> {
                    res.setContentType("application/json;charset=utf-8");
                    PrintWriter out = res.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(AjaxResponse.failure(-1, "尚未登录")));
                    out.flush();
                    out.close();
                });
    }
}
