package security.common.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import security.common.entry.JsonAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销成功处理程序
 * @author 水张哲
 */
@Component
public class MyLogoutSuccessHandler extends JsonAuthentication implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code", 200);
        jsonObject.put("msg", "注销成功!");
        this.writeJson(httpServletResponse, jsonObject);
    }
}
