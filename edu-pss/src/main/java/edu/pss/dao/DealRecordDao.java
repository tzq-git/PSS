package edu.pss.dao;

import edu.pss.bean.DealRecord;

import java.util.List;

public interface DealRecordDao {
    List<DealRecord> list(Long cid);
    Long delete(Long id);
    Long insert(DealRecord bean);
    Long update(DealRecord bean);
}
