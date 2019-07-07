package edu.pss.service;

import edu.pss.bean.User;

import java.util.List;

public interface UserService {

    List<User> list();

    Long insert(User bean);

    Long delete(Long id);

    Long update(User bean);

    User loadById(Long id);

    User loadByName(String name);

    Long count();

    List<User> pager(Long pageNum, Long pageSize);

    Long countByName(String name);

    List<User> pagerByName(String name, Long pageNum, Long pageSize);

    Long restPwd(Long id, String newPwd, String oldPwd);
    
}
