package security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.repository.po.PersistentLogins;
import security.service.PersistentLoginsService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 水张哲
 */
@Tag(name = "管理员")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private PersistentLoginsService persistentLoginsService;

    @Operation(summary = "持久登录列", description = "持久登录列", tags = {"管理员"})
    @GetMapping("/listPersistentLogins")
    private List<PersistentLogins> listPersistentLogins(){
        return persistentLoginsService.listPersistentLogins();
    }
}
