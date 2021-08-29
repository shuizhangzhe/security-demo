package security.common.entry;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名用户访问资源无权限异常
 * @author 水张哲
 */
@Component
public class MyAuthenticationEntryPoint extends JsonAuthentication implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code", 403);
        jsonObject.put("msg", "请先登录!");
        this.writeJson(httpServletResponse, jsonObject);

    }
}
