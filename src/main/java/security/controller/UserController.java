package security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import security.repository.po.PersistentLogins;
import security.service.PersistentLoginsService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 水张哲
 */
@Tag(name = "用户")
@RestController
public class UserController {
    @Resource
    private PersistentLoginsService persistentLoginsService;

    @Operation(summary = "我的持久登录列", description = "我的持久登录列", tags = {"用户"})
    @GetMapping("/listMyPersistentLogins")
    private List<PersistentLogins> listMyPersistentLogins(){
        return persistentLoginsService.listMyPersistentLogins();
    }
}
