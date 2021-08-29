package security.common.strategy;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import security.common.entry.JsonAuthentication;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author 水张哲
 */
@Component
public class MySessionInformationExpiredStrategy extends JsonAuthentication implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", -1);
        jsonObject.put("msg", "您的账号再另一处登录!");
        this.writeJson(sessionInformationExpiredEvent.getResponse(), jsonObject);
    }
}
