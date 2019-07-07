package edu.pss.service.impl;

import edu.pss.bean.Goods;
import edu.pss.dao.GoodsDao;
import edu.pss.dao.impl.GoodsDaoImpl;
import edu.pss.service.GoodsService;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao = new GoodsDaoImpl();


    @Override
    public List<Goods> list() {
        return goodsDao.list();
    }

    @Override
    public List<Goods> lowList() {
        return goodsDao.lowList();
    }

    @Override
    public Long insert(Goods bean){
        return goodsDao.insert(bean);
    }
    @Override
    public Long delete(Long id){
        return goodsDao.delete(id);
    }
    @Override
    public Long update(Goods bean){
        return goodsDao.update(bean);
    }
    @Override
    public Goods load(Long id){
        return goodsDao.load(id);
    }
    @Override
    public Goods loadByName(String name){
        return goodsDao.loadByName(name);
    }


    @Override
    public List<Goods> pager(Long pageNum, Long pageSize){
        return  goodsDao.pager(pageNum,pageSize);
    }
    @Override
    public Long count(){
        return goodsDao.count();
    }

    @Override
    public Long countByName(String name) {
        return goodsDao.countByName(name);
    }

    @Override
    public List<Goods> pagerByName(String name, Long pageNum, Long pageSize) {
        return goodsDao.pagerByName(name, pageNum, pageSize);
    }

    @Override
    public Long countEX(String gid ,String gName) {
        return goodsDao.countEX(gid,gName);
    }

    @Override
    public List<Goods> pagerEX(String gid, String gName, Long pageNum, Long pageSize) {
        return goodsDao.pagerEX(gid,gName,pageNum,pageSize);
    }
}
