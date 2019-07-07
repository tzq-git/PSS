package edu.pss.service;

import edu.pss.bean.DealRecord;

import java.util.List;

public interface DealRecordService {
    List<DealRecord> list(Long cid);

    Long insert(DealRecord bean);

    Long delete(Long id);

    Long update(DealRecord bean);
}
