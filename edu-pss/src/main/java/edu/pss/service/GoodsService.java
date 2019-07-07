package edu.pss.service;

import edu.pss.bean.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> list();

    List<Goods> lowList();

    Long count();

    List<Goods> pager(Long pageNum, Long pageSize);

    Long insert(Goods bean);

    Long delete(Long id);

    Long update(Goods bean);

    Goods load(Long id);

    Goods loadByName(String name);

    Long countByName(String name);

    List<Goods> pagerByName(String name,Long pageNum,Long pageSize);

    Long countEX(String gid ,String gName);

    List<Goods> pagerEX(String gid, String gName, Long pageNum, Long pageSize);
}
