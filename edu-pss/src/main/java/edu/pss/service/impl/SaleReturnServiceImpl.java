package edu.pss.service.impl;

import edu.pss.bean.SaleReturn;
import edu.pss.dao.impl.SaleReturnDaoImpl;
import edu.pss.dao.SaleReturnDao;
import edu.pss.service.SaleReturnService;

import java.util.List;

public class SaleReturnServiceImpl implements SaleReturnService {

    SaleReturnDao saleReturnDao = new SaleReturnDaoImpl();

    @Override
    public List<SaleReturn> list() {
        return saleReturnDao.list();
    }

    @Override
    public Long insert(SaleReturn bean) {
        return saleReturnDao.insert(bean);
    }

    @Override
    public Long delete(Long id) {
        return saleReturnDao.delete(id);
    }

    @Override
    public Long update(SaleReturn bean) {
        return saleReturnDao.update(bean);
    }

    @Override
    public SaleReturn loadById(Long id) {
        return saleReturnDao.loadById(id);
    }

    /**
     * 暂未使用，留作拓展
     * @param name
     * @return
     */
    @Override
    public SaleReturn loadByName(String name) {
        return saleReturnDao.loadByName(name);
    }

    @Override
    public Long count() {
        return saleReturnDao.count();
    }

    @Override
    public List<SaleReturn> pager(Long pageNum, Long pageSize) {
        return saleReturnDao.pager(pageNum, pageSize);
    }

    @Override
    public Long countByName(String name) {
        return saleReturnDao.countByName(name);
    }

    @Override
    public List<SaleReturn> pagerByName(String name, Long pageNum, Long pageSize) {
        return saleReturnDao.pagerByName(name, pageNum, pageSize);
    }
    
}
