package edu.pss.service;

import edu.pss.bean.OutBound;

import java.util.List;

public interface OutBoundService {

    List<OutBound> list();

    Long insert(OutBound bean);

    Long delete(Long id);

    Long update(OutBound bean);

    OutBound loadById(Long id);

    OutBound loadByName(String name);

    Long count();

    List<OutBound> pager(Long pageNum, Long pageSize);

    Long countByName(String name);

    List<OutBound> pagerByName(String name, Long pageNum, Long pageSize);
}

