package security.common.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import security.common.entry.JsonAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证成功处理程序
 * @author 水张哲
 */
@Component
public class MyAuthenticationSuccessHandler extends JsonAuthentication implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code", 200);
        jsonObject.put("msg", "登录成功!");
        this.writeJson(httpServletResponse, jsonObject);
    }
}
