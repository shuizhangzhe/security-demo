package security.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.repository.mapper.UserMapper;
import security.repository.mapper.UserRoleViewMapper;
import security.repository.po.User;
import security.repository.po.UserExample;
import security.repository.po.UserRoleView;
import security.repository.po.UserRoleViewExample;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService 实现类
 * @author 水张哲
 * @date 2021.08.28
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleViewMapper userRoleViewMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.isEmpty()){
            throw new UsernameNotFoundException("用户名不存在!");
        }else {
            User user = userList.get(0);
            UserRoleViewExample userRoleViewExample = new UserRoleViewExample();
            userRoleViewExample.createCriteria().andUserIdEqualTo(user.getId());
            List<UserRoleView> userRoleViewList = userRoleViewMapper.selectByExample(userRoleViewExample);
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>(userRoleViewList.size());
            for (UserRoleView o : userRoleViewList) {
                grantedAuthorities.add(new SimpleGrantedAuthority(o.getRoleEnName()));
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    grantedAuthorities
            );
        }
    }
}
