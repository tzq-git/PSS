package edu.pss.dao;

import edu.pss.bean.Goods;

import java.util.List;

public interface GoodsDao {
    List<Goods> list();

    Goods load(Long id);
    Goods loadByName(String name);

    List<Goods> lowList();

    Long delete(Long id);
    Long insert(Goods bean);
    Long update(Goods bean);

    Long count();
    List<Goods> pager(Long pageNum, Long pageSize);

    Long countByName(String name);
    List<Goods> pagerByName(String name ,Long pageNum, Long pageSize);

    Long countEX(String gid,String gName);

    List<Goods> pagerEX(String gid, String gName, Long pageNum, Long pageSize);
}
