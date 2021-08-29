package security.common.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author 水张哲
 */
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("All")
                .apiInfo(apiInfo());
    }

    /**
     * 配置 Swagger 信息
     */
    private ApiInfo apiInfo(){
        // 作者信息
        Contact contact = new Contact("XXX", "", "XXX@qq.com");
        return new ApiInfo(
                "XXX系统API文档",
                "这里是文档描述",
                "v1.0.0",
                "",
                contact,
                "Apache 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
