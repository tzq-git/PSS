package edu.pss.dao;

import edu.pss.bean.Purchase;

import java.util.List;

public interface PurchaseDao {
    List<Purchase> list();
    List<Purchase> list(String year);

    Purchase load(Long id);
    Purchase loadByName(String name);

    Long delete(Long id);
    Long insert(Purchase bean);
    Long update(Purchase bean);

    Long count();
    List<Purchase> pager(Long pageNum, Long pageSize);

    Long countByName(String name);
    List<Purchase> pagerByName(String name ,Long pageNum, Long pageSize);
}
