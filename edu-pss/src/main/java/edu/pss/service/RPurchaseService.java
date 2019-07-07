package edu.pss.service;

import edu.pss.bean.RPurchase;

import java.util.List;

public interface RPurchaseService {

    List<RPurchase> list();

    Long count();

    List<RPurchase> pager(Long pageNum, Long pageSize);

    Long insert(RPurchase bean);

    Long delete(Long id);

    Long update(RPurchase bean);

    RPurchase load(Long id);

    RPurchase loadByName(String name);

    Long countByName(String name);

    List<RPurchase> pagerByName(String name,Long pageNum,Long pageSize);
}
