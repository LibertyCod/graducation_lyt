package com.graduation.project.configurer;

import com.alibaba.fastjson.JSON;
import com.graduation.project.core.ApiResponse;
import com.graduation.project.exception.errorcode.BizErrorCode;
import com.graduation.project.filter.JWTAuthenticationFilter;
import com.graduation.project.util.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Binbin Wang
 * @date 2017/11/16
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
        .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/user/login**").permitAll()
                .antMatchers("/user/add**").permitAll()
                .antMatchers("/user/update**").permitAll()
                .antMatchers("/email/register**").permitAll()
                .antMatchers("/captcha").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(jWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
                    throws IOException, ServletException {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(
                        JSON.toJSONString(ApiResponse.getErrResponse(BizErrorCode.PERMISSION_DENIED, "无效token")));
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/admin/**", "/js/**", "/css/**", "/images/**",
                "/**/favicon.ico","/swagger-ui.html","/webjars/**","/swagger-resources/**",
                "/v2/**");
    }

    @Bean
    public JWTAuthenticationFilter jWTAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();}
}
