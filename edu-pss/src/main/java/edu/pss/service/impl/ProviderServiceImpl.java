package edu.pss.service.impl;

import edu.pss.bean.Provider;
import edu.pss.dao.ProviderDao;
import edu.pss.dao.impl.ProviderDaoImpl;
import edu.pss.service.ProviderService;

import java.util.List;

public class ProviderServiceImpl implements ProviderService {
    private ProviderDao providerDao = new ProviderDaoImpl();


    @Override
    public List<Provider> list() {
        return providerDao.list();
    }

    @Override
    public Long insert(Provider bean){
        return providerDao.insert(bean);
    }
    @Override
    public Long delete(Long id){
        return providerDao.delete(id);
    }
    @Override
    public Long update(Provider bean){
        return providerDao.update(bean);
    }
    @Override
    public Provider load(Long id){
        return providerDao.load(id);
    }
    @Override
    public Provider loadByName(String name){
        return providerDao.loadByName(name);
    }


    @Override
    public List<Provider> pager(Long pageNum, Long pageSize){
        return  providerDao.pager(pageNum,pageSize);
    }
    @Override
    public Long count(){
        return providerDao.count();
    }

    @Override
    public Long countByName(String name) {
        return providerDao.countByName(name);
    }

    @Override
    public List<Provider> pagerByName(String name, Long pageNum, Long pageSize) {
        return providerDao.pagerByName(name, pageNum, pageSize);
    }
}
