package edu.pss.dao;

import edu.pss.bean.RPurchase;

import java.util.List;

public interface RPurchaseDao {
    List<RPurchase> list();

    RPurchase load(Long id);
    RPurchase loadByName(String name);

    Long delete(Long id);
    Long insert(RPurchase bean);
    Long update(RPurchase bean);

    Long count();
    List<RPurchase> pager(Long pageNum, Long pageSize);

    Long countByName(String name);
    List<RPurchase> pagerByName(String name ,Long pageNum, Long pageSize);
}
