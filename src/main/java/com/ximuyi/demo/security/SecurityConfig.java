package com.ximuyi.demo.security;

import com.ximuyi.demo.mybatis.MybatisUserService;
import com.ximuyi.demo.mybatis.model.MybatisUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {

    @Autowired
    private MybatisUserService mybatisUserService;

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/hello");
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
        ;
        http.userDetailsService(userDetailsService());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            PasswordEncoder encoder = passwordEncoder();
            MybatisUser mybatisUser = mybatisUserService.getUserByName(username);
            if (mybatisUser != null) {
                return User.builder().passwordEncoder(encoder::encode)
                        .roles(mybatisUser.getRole())
                        .password(mybatisUser.getPassword())
                        .username(mybatisUser.getUserName())
                        .build();
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }
}
