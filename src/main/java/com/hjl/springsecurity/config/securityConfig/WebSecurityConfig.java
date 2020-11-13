package com.hjl.springsecurity.config.securityConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;


/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 15:31
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            /**
             * @param charSequence 输入的密码
             * @param s 数据库加密的密码
             * @return
             */
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                System.out.println("matches:-》charSequence: "+charSequence.toString()+" ,S: "+s);
                BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
                boolean f = bcryptPasswordEncoder.matches(charSequence,s);//比较密码
                System.out.println(f);
                return f;
            }
        });


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/regis","/register").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin().loginPage("/login")
                // 设置登陆成功页
                .defaultSuccessUrl("/").permitAll()
                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and()
                // 异常处理跳转
                .exceptionHandling().accessDeniedPage("/500")
                .and()
                .logout().permitAll();
        //开启记住我功能
        // http.rememberMe().rememberMeParameter("remember-me");

        //以下这句就可以控制单个用户只能创建一个session，也就只能在服务器登录一次
        http.sessionManagement().maximumSessions(1).expiredUrl("/login");

         // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }


}

