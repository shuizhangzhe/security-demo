package security.common.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import security.common.entry.JsonAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证失败处理程序
 * @author 水张哲
 */
@Component
public class MyAuthenticationFailureHandler extends JsonAuthentication implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code", 401);
        if (e instanceof InternalAuthenticationServiceException){
            jsonObject.put("msg", "账号不存在!");
        } else if (e instanceof DisabledException){
            jsonObject.put("msg", "账号不可用!");
        } else if (e instanceof AccountExpiredException){
            jsonObject.put("msg", "账号已过期!");
        } else if (e instanceof CredentialsExpiredException){
            jsonObject.put("msg", "密码已过期!");
        } else if (e instanceof LockedException){
            jsonObject.put("msg", "账号已冻结!");
        } else if (e instanceof BadCredentialsException){
            jsonObject.put("msg", "用户名或密码错误!");
        } else {
            jsonObject.put("msg", "登录失败!");
        }
        this.writeJson(httpServletResponse, jsonObject);
    }
}
