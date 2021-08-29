package security.repository.mapper;

import java.util.List;
import security.repository.po.UserRoleView;
import security.repository.po.UserRoleViewExample;

public interface UserRoleViewMapper {
    long countByExample(UserRoleViewExample example);

    List<UserRoleView> selectByExample(UserRoleViewExample example);
}