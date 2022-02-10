package com.rookie.mall.demo.config;

import com.rookie.mall.demo.dto.UserUtils;
import com.rookie.mall.demo.entity.UmsAdmin;
import com.rookie.mall.demo.entity.UmsPermission;
import com.rookie.mall.demo.service.UmsAdminService;
import com.rookie.mall.demo.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author: chen
 * @date: 2022/1/19
 * @description: TODO 类描述
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UmsAdminService service;

    /*
     * @param
     * @description: 获取密码加密实例
     * @return:
     * @author: chen
     * @date:
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    /*
     * @param
     * @description: 配置数据源
     * @return:
     * @author: chen
     * @date:
     */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        //实现UserDetailsService 接口
        return username ->{
            //重写loadUserByUsername方法，返回值UserDetails
            UmsAdmin umsAdmin = service.getUmsAdminByUsername(username);
            if (umsAdmin != null) {
                List<UmsPermission> permission = service.getPermission(umsAdmin.getId());
                //TODO 用户与角色的关系
                UserUtils userUtils = new UserUtils(umsAdmin, permission);
                return userUtils;
            }
            return null;
        };

    }

    /*
     * @param http
     * @description: 限定页面访问权限
     * @return:
     * @author: chen
     * @date:
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //限定页面访问权限
                .antMatchers("/register","/register").permitAll()//不需要通过登录验证就可以被访问的资源路径
                //.antMatchers("/admin/**").hasRole("admin")
                //.antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated()// 除上面外的所有请求全部需要鉴权认证
                .and()
                .formLogin()
                .and()
                .rememberMe()//自动登录
                .and()
                .csrf().disable();//禁用csrf跨域伪造，避免post请求无法访问

    }

    /*
     * @param
     * @description: 角色权限控制,admin用户拥有user的权限
     * @return:
     * @author: chen
     * @date:
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

}
