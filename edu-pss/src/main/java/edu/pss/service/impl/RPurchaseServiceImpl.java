package edu.pss.service.impl;

import edu.pss.bean.RPurchase;
import edu.pss.dao.RPurchaseDao;
import edu.pss.dao.impl.RPurchaseDaoImpl;
import edu.pss.service.RPurchaseService;

import java.util.List;

public class RPurchaseServiceImpl implements RPurchaseService {
    private RPurchaseDao rPurchaseDao = new RPurchaseDaoImpl();


    @Override
    public List<RPurchase> list() {
        return rPurchaseDao.list();
    }

    @Override
    public Long insert(RPurchase bean){
        return rPurchaseDao.insert(bean);
    }
    @Override
    public Long delete(Long id){
        return rPurchaseDao.delete(id);
    }
    @Override
    public Long update(RPurchase bean){
        return rPurchaseDao.update(bean);
    }
    @Override
    public RPurchase load(Long id){
        return rPurchaseDao.load(id);
    }
    @Override
    public RPurchase loadByName(String name){
        return rPurchaseDao.loadByName(name);
    }


    @Override
    public List<RPurchase> pager(Long pageNum, Long pageSize){
        return  rPurchaseDao.pager(pageNum,pageSize);
    }
    @Override
    public Long count(){
        return rPurchaseDao.count();
    }

    @Override
    public Long countByName(String name) {
        return rPurchaseDao.countByName(name);
    }

    @Override
    public List<RPurchase> pagerByName(String name, Long pageNum, Long pageSize) {
        return rPurchaseDao.pagerByName(name, pageNum, pageSize);
    }
}
