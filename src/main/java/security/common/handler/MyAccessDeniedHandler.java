package security.common.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import security.common.entry.JsonAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问被拒绝处理程序
 * @author 水张哲
 */
@Component
public class MyAccessDeniedHandler extends JsonAuthentication implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("code", 403);
        jsonObject.put("msg", "权限不足!");
        this.writeJson(httpServletResponse, jsonObject);
    }
}
