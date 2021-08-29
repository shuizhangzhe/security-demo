package security.service.impl;

import org.springframework.stereotype.Service;
import security.common.util.SecurityUtil;
import security.repository.mapper.PersistentLoginsMapper;
import security.repository.po.PersistentLogins;
import security.repository.po.PersistentLoginsExample;
import security.service.PersistentLoginsService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 水张哲
 */
@Service
public class PersistentLoginsServiceImpl implements PersistentLoginsService {
    @Resource
    private PersistentLoginsMapper persistentLoginsMapper;

    @Override
    public List<PersistentLogins> listMyPersistentLogins() {
        PersistentLoginsExample persistentLoginsExample = new PersistentLoginsExample();
        persistentLoginsExample.createCriteria().andUsernameEqualTo(SecurityUtil.getCurrentUsername());
        return persistentLoginsMapper.selectByExample(persistentLoginsExample);
    }

    @Override
    public List<PersistentLogins> listPersistentLogins() {
        return persistentLoginsMapper.selectByExample(new PersistentLoginsExample());
    }
}
