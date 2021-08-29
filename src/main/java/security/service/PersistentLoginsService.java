package security.service;

import security.repository.po.PersistentLogins;

import java.util.List;

/**
 * @author 水张哲
 */
public interface PersistentLoginsService {

    List<PersistentLogins> listMyPersistentLogins();

    List<PersistentLogins> listPersistentLogins();
}
