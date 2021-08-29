package security.common.entry;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 水张哲
 */
public class JsonAuthentication {

    protected void writeJson(
            HttpServletResponse httpServletResponse,
            Object data
    ) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.getWriter().write(JSON.toJSONString(data));
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }

}
