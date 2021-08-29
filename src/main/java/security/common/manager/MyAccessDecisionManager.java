package security.common.manager;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 访问决策管理器
 * @author 水张哲
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        FilterInvocation filterInvocation = (FilterInvocation) o;
        String requestUrl = filterInvocation.getRequestUrl().split("\\?")[0];
        for (ConfigAttribute configAttribute : collection) {
            String needAuthority = configAttribute.getAttribute();
            // 如果某个地址没有对应的角色可以访问，则不放行
            if(needAuthority == null){
                throw new AccessDeniedException("权限不足!");
            } else {
                for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                    if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
                        return ;
                    }
                }
            }
        }
        throw new AccessDeniedException("权限不足!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
