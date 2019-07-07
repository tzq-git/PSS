package edu.pss.service.impl;

import edu.pss.bean.SalePerson;
import edu.pss.dao.SalePersonDao;
import edu.pss.dao.impl.SalePersonDaoImpl;
import edu.pss.service.SalePersonService;

import java.util.List;

public class SalePersonServiceImpl implements SalePersonService {
    private SalePersonDao salePersonDao = new SalePersonDaoImpl();


    @Override
    public List<SalePerson> list() {
        return salePersonDao.list();
    }

    @Override
    public Long insert(SalePerson bean){
        return salePersonDao.insert(bean);
    }
    @Override
    public Long delete(Long id){
        return salePersonDao.delete(id);
    }
    @Override
    public Long update(SalePerson bean){
        return salePersonDao.update(bean);
    }
    @Override
    public SalePerson load(Long id){
        return salePersonDao.load(id);
    }
    @Override
    public SalePerson loadByName(String name){
        return salePersonDao.loadByName(name);
    }


    @Override
    public List<SalePerson> pager(Long pageNum, Long pageSize){
        return  salePersonDao.pager(pageNum,pageSize);
    }
    @Override
    public Long count(){
        return salePersonDao.count();
    }

    @Override
    public Long countByName(String name) {
        return salePersonDao.countByName(name);
    }

    @Override
    public List<SalePerson> pagerByName(String name, Long pageNum, Long pageSize) {
        return salePersonDao.pagerByName(name, pageNum, pageSize);
    }

    @Override
    public Long loadCustomer(Long id) {
        return salePersonDao.loadCustomer(id);
    }
}
