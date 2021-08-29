package security.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsUtils;
import security.common.constant.SecurityConstant;
import security.common.entry.MyAuthenticationEntryPoint;
import security.common.handler.MyAccessDeniedHandler;
import security.common.handler.MyAuthenticationFailureHandler;
import security.common.handler.MyAuthenticationSuccessHandler;
import security.common.handler.MyLogoutSuccessHandler;
import security.common.manager.MyAccessDecisionManager;
import security.common.strategy.MyInvalidSessionStrategy;
import security.common.strategy.MySessionInformationExpiredStrategy;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.TreeMap;

/**
 * @author 水张哲
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Resource
    private MyAccessDecisionManager myAccessDecisionManager;
    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Resource
    private MyInvalidSessionStrategy myInvalidSessionStrategy;
    @Resource
    private MySessionInformationExpiredStrategy mySessionInformationExpiredStrategy;

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        /// jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置无需 Security 安全控制的接口
        web.ignoring()
                .antMatchers(SecurityConstant.SWAGGER_WHITELIST)
                .antMatchers(SecurityConstant.DRUID_WHITELIST)
                .antMatchers(SecurityConstant.SYSTEM_WHITELIST);
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域资源共享
        http.cors();
        // 关闭CSRF跨站保护
        http.csrf().disable();

        // 决定哪些接口开启防护，哪些接口绕过防护
        http.authorizeRequests()
                .accessDecisionManager(myAccessDecisionManager)
                // 允许前端跨域联调
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 其它所有接口需要认证才能访问
                .anyRequest().authenticated();

        // token配置
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(1800)
                .userDetailsService(userDetailsService);

        // 用户登录配置（成功与失败处理）
        http.formLogin()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);

        // 用户登出配置 (成功处理)
        http.logout()
                .logoutSuccessHandler(myLogoutSuccessHandler);

        // 指定认证错误处理器
        http.exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                .and().exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);

        // Session模式 前后端分离适用 STATELESS
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .invalidSessionStrategy(myInvalidSessionStrategy)
                // 设置最大允许登录数
                .maximumSessions(1)
                // 当达到最大允许登录数后是否阻止登录
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(mySessionInformationExpiredStrategy);

    }
}
