package security.common.strategy;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;
import security.common.entry.JsonAuthentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 水张哲
 */
@Component
public class MyInvalidSessionStrategy extends JsonAuthentication implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 401);
        jsonObject.put("msg", "会话超时!");
        this.writeJson(httpServletResponse, jsonObject);
    }
}
