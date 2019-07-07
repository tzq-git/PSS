package edu.pss.service.impl;

import edu.pss.bean.Authority;
import edu.pss.dao.AuthorityDao;
import edu.pss.dao.impl.AuthorityDaoImpl;
import edu.pss.service.AuthorityService;

import java.util.List;

public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityDao authorityDao = new AuthorityDaoImpl();

    @Override
    public List<Authority> list() {
        return authorityDao.list();
    }

    @Override
    public Long insert(Authority bean) {
        return authorityDao.insert(bean);
    }

    @Override
    public Long update(Authority bean) {
        return authorityDao.update(bean);
    }

    @Override
    public Authority loadById(Long id) {
        return authorityDao.loadById(id);
    }

    @Override
    public Authority loadByName(String name) {
        return authorityDao.loadByName(name);
    }

    @Override
    public Long count() {
        return authorityDao.count();
    }

    @Override
    public List<Authority> pager(Long pageNum, Long pageSize) {
        return authorityDao.pager(pageNum, pageSize);
    }

    @Override
    public Long countByName(String name) {
        return authorityDao.countByName(name);
    }

    @Override
    public List<Authority> pagerByName(String name, Long pageNum, Long pageSize) {
        return authorityDao.pagerByName(name, pageNum, pageSize);
    }
}
