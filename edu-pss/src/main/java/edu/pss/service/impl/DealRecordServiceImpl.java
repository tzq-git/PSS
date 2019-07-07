package edu.pss.service.impl;

import edu.pss.bean.DealRecord;
import edu.pss.dao.DealRecordDao;
import edu.pss.dao.impl.DealRecordDaoImpl;
import edu.pss.service.DealRecordService;

import java.util.List;

public class DealRecordServiceImpl implements DealRecordService {
    DealRecordDao dealRecordDao = new DealRecordDaoImpl();
    @Override
    public List<DealRecord> list(Long cid) {
        return dealRecordDao.list(cid);
    }

    @Override
    public Long insert(DealRecord bean){
        return dealRecordDao.insert(bean);
    }
    @Override
    public Long delete(Long id){
        return dealRecordDao.delete(id);
    }
    @Override
    public Long update(DealRecord bean){
        return dealRecordDao.update(bean);
    }
}
