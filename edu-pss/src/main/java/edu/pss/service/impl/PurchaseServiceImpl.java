package edu.pss.service.impl;

import edu.pss.bean.Purchase;
import edu.pss.dao.PurchaseDao;
import edu.pss.dao.impl.PurchaseDaoImpl;
import edu.pss.service.PurchaseService;

import java.util.List;

public class PurchaseServiceImpl implements PurchaseService {
    private PurchaseDao purchaseDao = new PurchaseDaoImpl();


    @Override
    public List<Purchase> list() {
        return purchaseDao.list();
    }

    @Override
    public List<Purchase> list(String year) {
        return purchaseDao.list(year);
    }

    @Override
    public Long insert(Purchase bean){
        return purchaseDao.insert(bean);
    }
    @Override
    public Long delete(Long id){
        return purchaseDao.delete(id);
    }
    @Override
    public Long update(Purchase bean){
        return purchaseDao.update(bean);
    }
    @Override
    public Purchase load(Long id){
        return purchaseDao.load(id);
    }
    @Override
    public Purchase loadByName(String name){
        return purchaseDao.loadByName(name);
    }


    @Override
    public List<Purchase> pager(Long pageNum, Long pageSize){
        return  purchaseDao.pager(pageNum,pageSize);
    }
    @Override
    public Long count(){
        return purchaseDao.count();
    }

    @Override
    public Long countByName(String name) {
        return purchaseDao.countByName(name);
    }

    @Override
    public List<Purchase> pagerByName(String name, Long pageNum, Long pageSize) {
        return purchaseDao.pagerByName(name, pageNum, pageSize);
    }
}
