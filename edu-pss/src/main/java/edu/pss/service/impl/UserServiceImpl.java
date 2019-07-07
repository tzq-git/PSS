package edu.pss.service.impl;

import edu.pss.bean.User;
import edu.pss.dao.UserDao;
import edu.pss.dao.impl.UserDaoImpl;
import edu.pss.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class UserServiceImpl implements UserService {
    
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public Long insert(User bean) {

        //对密码进行MD5加密
        String EncryptedPwd = DigestUtils.md5Hex(bean.getUserPwd());
        bean.setUserPwd(EncryptedPwd);
        return userDao.insert(bean);
    }

    @Override
    public Long delete(Long id) {
        //小样，不可能让你删除超级管理员的
        if ( id==1 ) return 0L;
        return userDao.delete(id);
    }

    @Override
    public Long update(User bean) {
        //将密码及逆行MD5加密
        String encryptedPwd = DigestUtils.md5Hex(bean.getUserPwd());
        bean.setUserPwd(encryptedPwd);
        return userDao.update(bean);
    }

    @Override
    public User loadById(Long id) {
        return userDao.loadById(id);
    }

    @Override
    public User loadByName(String name) {
        return userDao.loadByName(name);
    }

    @Override
    public Long count() {
        return userDao.count();
    }

    @Override
    public List<User> pager(Long pageNum, Long pageSize) {
        return userDao.pager(pageNum, pageSize);
    }

    @Override
    public Long countByName(String name) {
        return userDao.countByName(name);
    }

    @Override
    public List<User> pagerByName(String name, Long pageNim, Long pageSize) {
        return userDao.pagerByName(name, pageNim, pageSize);
    }

    @Override
    public Long restPwd(Long id, String newPwd, String oldPwd) {

        String encryptedPwd = null;

        //带有就密码参数值得操作为修改密码，需要验证原来密码是否正确
        if ( oldPwd!=null){
            //将密码及逆行MD5加密
            encryptedPwd = DigestUtils.md5Hex(oldPwd);
        }

        User user = userDao.loadById(id);
        if ( encryptedPwd!=null && !encryptedPwd.equals(user.getUserPwd()) ){
            return 0L;
        } else {
            encryptedPwd = DigestUtils.md5Hex(newPwd);
        }
        return userDao.restPwd(id, encryptedPwd);
    }
}
