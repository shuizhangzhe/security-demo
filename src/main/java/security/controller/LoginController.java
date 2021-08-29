package security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 水张哲
 */
@Tag(name = "登录登出")
@RestController
public class LoginController {

    @Operation(summary = "登录", description = "登录", tags = {"登录登出"})
    @PostMapping(value = "/login")
    public void login (
            @Parameter(name = "username", description = "用户名") @RequestParam String username,
            @Parameter(name = "password", description = "密码") @RequestParam String password,
            @Parameter(name = "remember-me", description = "记住我") @RequestParam Boolean remember
    ){

    }

    @Operation(summary = "注销", description = "注销", tags = {"登录登出"})
    @PostMapping(value = "/logout")
    public void logout(){

    }
}
