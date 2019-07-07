package edu.pss.service.impl;

import edu.pss.bean.OutBound;
import edu.pss.dao.impl.OutBoundDaoImpl;
import edu.pss.dao.OutBoundDao;
import edu.pss.service.OutBoundService;

import java.util.List;

public class OutBoundServiceImpl implements OutBoundService {

    OutBoundDao outBoundDao = new OutBoundDaoImpl();

    @Override
    public List<OutBound> list() {
        return outBoundDao.list();
    }

    @Override
    public Long insert(OutBound bean) {
        return outBoundDao.insert(bean);
    }

    @Override
    public Long delete(Long id) {
        return outBoundDao.delete(id);
    }

    @Override
    public Long update(OutBound bean) {
        return outBoundDao.update(bean);
    }

    @Override
    public OutBound loadById(Long id) {
        return outBoundDao.loadById(id);
    }

    /**
     * 暂未使用，留作拓展
     * @param name
     * @return
     */
    @Override
    public OutBound loadByName(String name) {
        return outBoundDao.loadByName(name);
    }

    @Override
    public Long count() {
        return outBoundDao.count();
    }

    @Override
    public List<OutBound> pager(Long pageNum, Long pageSize) {
        return outBoundDao.pager(pageNum, pageSize);
    }

    @Override
    public Long countByName(String name) {
        return outBoundDao.countByName(name);
    }

    @Override
    public List<OutBound> pagerByName(String name, Long pageNum, Long pageSize) {
        return outBoundDao.pagerByName(name, pageNum, pageSize);
    }
}
