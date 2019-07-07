package edu.pss.service;

import edu.pss.bean.Purchase;

import java.util.List;

public interface PurchaseService {

    List<Purchase> list();

    List<Purchase> list(String year);

    Long count();

    List<Purchase> pager(Long pageNum, Long pageSize);

    Long insert(Purchase bean);

    Long delete(Long id);

    Long update(Purchase bean);

    Purchase load(Long id);

    Purchase loadByName(String name);

    Long countByName(String name);

    List<Purchase> pagerByName(String name,Long pageNum,Long pageSize);
}
