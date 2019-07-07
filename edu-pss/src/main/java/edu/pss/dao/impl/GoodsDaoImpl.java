package edu.pss.dao.impl;

import com.liuvei.common.DbFun;
import com.liuvei.common.SysFun;
import edu.pss.bean.Goods;
import edu.pss.dao.GoodsDao;
import edu.pss.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public List<Goods> list() {
        List<Goods> list = new ArrayList<Goods>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from Goods ");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConn();

            rs = DbFun.query(conn, sql, params);
            while (rs.next()){
                list.add(toBean(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.close(conn);
        }

        return list;
    }

    @Override
    public Long insert(Goods bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();



        sbSQL.append(" Insert Into Goods");
        sbSQL.append("(");
        sbSQL.append(" gName,saveStock,nowStock,buyPrice");
        sbSQL.append(" ,price,lastBuyTime,lastSaleTime");
        sbSQL.append(" )");
        sbSQL.append(" value(?,?,?,?, ?,?,?)");

        paramsList.add(bean.getgName());
        paramsList.add(bean.getSaveStock());
        paramsList.add(bean.getNowStock());
        paramsList.add(bean.getBuyPrice());

        paramsList.add(bean.getPrice());
        paramsList.add(bean.getLastBuyTime());
        paramsList.add(bean.getLastSaleTime());


        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try{
            conn = DbUtil.getConn();

            Long num = DbFun.update(conn, sql, params);

            if(num>0){
                sql = "Select @@identity";
                result = DbFun.queryScalarLong(conn, sql);
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }finally {
            DbUtil.close(conn);
        }




        return result;
    }

    @Override
    public Long update(Goods bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" update  Goods");
        sbSQL.append(" set gName=?,saveStock=?,nowStock=?,buyPrice=?");
        sbSQL.append(" ,price=?,lastBuyTime=?,lastSaleTime=?");
        sbSQL.append(" where gid=?");


        paramsList.add(bean.getgName());
        paramsList.add(bean.getSaveStock());
        paramsList.add(bean.getNowStock());
        paramsList.add(bean.getBuyPrice());

        paramsList.add(bean.getPrice());
        paramsList.add(bean.getLastBuyTime());
        paramsList.add(bean.getLastSaleTime());


        paramsList.add(bean.getGid());


        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try{
            conn = DbUtil.getConn();

            result = DbFun.update(conn, sql, params);


        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }finally {
            DbUtil.close(conn);
        }




        return result;
    }


    @Override
    public Long delete(Long id) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" delete from Goods");
        sbSQL.append(" where gid=?");

        paramsList.add(id);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try{

            conn = DbUtil.getConn();

            result = DbFun.update(conn, sql, params);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn);
        }
        return result;
    }

    @Override
    public Goods load(Long id) {

        Goods bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from goods");
        sbSQL.append(" where gid=?");

        paramsList.add(id);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConn();

            rs = DbFun.query(conn, sql,params);

            if(rs.next()){
                bean= toBean(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DbUtil.close(conn);
        }


        return bean;
    }

    @Override
    public Goods loadByName(String name) {
        Goods bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from goods");
        sbSQL.append(" where gName=?");
        sbSQL.append(" order by gid asc");

        paramsList.add(name);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConn();

            rs = DbFun.query(conn, sql,params);

            if(rs.next()){
                bean= toBean(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DbUtil.close(conn);
        }

        return bean;
    }


    private Goods toBean(ResultSet rs) {

        Goods bean = new Goods ();

        try {

            bean.setGid (rs.getLong( "gid"));
            bean.setgName (rs.getString( "gName"));
            bean.setSaveStock (rs.getDouble( "saveStock"));
            bean.setNowStock (rs.getDouble(  "nowStock"));
            bean.setBuyPrice(rs.getDouble("buyPrice"));
            bean.setPrice (rs.getDouble("price"));
            bean.setLastBuyTime (rs.getDate(  "lastBuyTime"));
            bean.setLastSaleTime (rs.getDate( "lastSaleTime"));


        }catch (SQLException e){

            e. printStackTrace() ;

            throw new RuntimeException(e);
        }
        return bean;

    }

    @Override
    public Long count() {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select count(1) From Goods");

        String sql = sbSQL.toString();
        Object[] param = paramsList.toArray();

        Connection conn = null;

        try {
            conn = DbUtil.getConn();

            result = DbFun.queryScalarLong(conn, sql, param);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DbUtil.close(conn);
        }


        return result;
    }

    @Override
    public List<Goods> pager(Long pageNum, Long pageSize) {

        List<Goods> list = new ArrayList<Goods>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * from Goods");
        sbSQL.append(" order by gid asc");


        if(pageNum<1){
            pageNum = 1L;
        }
        if(pageSize<1){
            pageSize = 10L;
        }

        Long startIndex = (pageNum - 1)*pageSize;
        sbSQL.append(" limit "+ startIndex+","+pageSize);

        String sql = sbSQL.toString();
        Object[] param = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConn();
            rs = DbFun.query(conn, sql, param);

            while(rs.next()){
                list.add(toBean(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DbUtil.close(conn);
        }


        return list;
    }

    @Override
    public Long countByName(String name) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select count(1) From Goods");
        sbSQL.append(" where gName like ?");
        sbSQL.append(" order by gid asc");

        name="%" +name+ "%";
        paramsList.add(name);


        String sql = sbSQL.toString();
        Object[] param = paramsList.toArray();

        Connection conn = null;

        try {
            conn = DbUtil.getConn();

            result = DbFun.queryScalarLong(conn, sql, param);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DbUtil.close(conn);
        }


        return result;
    }

    @Override
    public List<Goods> pagerByName(String name,Long pageNum, Long pageSize) {

        System.out.println("name是：" +name);
        List<Goods> list = new ArrayList<Goods>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * from Goods");
        sbSQL.append(" where gName like ?");
        sbSQL.append(" order by gid asc");

        name = "%" +name+ "%";
        paramsList.add(name);

        if(pageNum<1){
            pageNum = 1L;
        }
        if(pageSize<1){
            pageSize = 10L;
        }

        Long startIndex = (pageNum - 1)*pageSize;
        sbSQL.append(" limit "+ startIndex+","+pageSize);

        String sql = sbSQL.toString();
        Object[] param = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConn();
            rs = DbFun.query(conn, sql, param);

            while(rs.next()){
                list.add(toBean(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DbUtil.close(conn);
        }
        return list;
    }

    @Override
    public Long countEX(String gid, String gName) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();
        if(SysFun.isNullOrEmpty(gid)&&!SysFun.isNullOrEmpty(gName)){
            sbSQL.append(" select count(1) From Goods where gName like ?");
            gName="%" +gName+ "%";
            paramsList.add(gName);
        }else if(!SysFun.isNullOrEmpty(gid)&&SysFun.isNullOrEmpty(gName)){
            sbSQL.append(" select count(1) From Goods where gid = ?");
            gid="%" +gid+ "%";
            paramsList.add(gid);
        }else if(!SysFun.isNullOrEmpty(gid)&&!SysFun.isNullOrEmpty(gName)){
            sbSQL.append(" select count(1) From Goods where gName like ? and gid like ?");
            gName="%" +gName+ "%";
            gid="%" +gid+ "%";
            paramsList.add(gid);
            paramsList.add(gName);
        }else{
            sbSQL.append(" select count(1) From Goods ");
        }



        String sql = sbSQL.toString();
        Object[] param = paramsList.toArray();

        Connection conn = null;

        try {
            conn = DbUtil.getConn();

            result = DbFun.queryScalarLong(conn, sql, param);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DbUtil.close(conn);
        }


        return result;
    }

    @Override
    public List<Goods> pagerEX(String gid, String gName, Long pageNum, Long pageSize) {
        System.out.println("gName是：" +gName);
        System.out.println("gid是：" +gid);
        List<Goods> list = new ArrayList<Goods>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        if(SysFun.isNullOrEmpty(gid)&&!SysFun.isNullOrEmpty(gName)){
            sbSQL.append(" select * From Goods where gName like ? order by gid asc");
            gName="%" +gName+ "%";
            paramsList.add(gName);
        }else if(!SysFun.isNullOrEmpty(gid)&&SysFun.isNullOrEmpty(gName)){
            sbSQL.append(" select * From Goods where gid=? order by gid asc");
            paramsList.add(gid);
        }else if(!SysFun.isNullOrEmpty(gid)&&!SysFun.isNullOrEmpty(gName)){
            sbSQL.append(" select * From Goods where gName like ? and gid=? order by gid asc");
            gName="%" +gName+ "%";
            paramsList.add(gName);
            paramsList.add(gid);
        }else{
            sbSQL.append(" select * From Goods order by gid asc");
        }


        if(pageNum<1){
            pageNum = 1L;
        }
        if(pageSize<1){
            pageSize = 10L;
        }

        Long startIndex = (pageNum - 1)*pageSize;
        sbSQL.append(" limit "+ startIndex+","+pageSize);

        String sql = sbSQL.toString();
        Object[] param = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConn();
            rs = DbFun.query(conn, sql, param);

            while(rs.next()){
                list.add(toBean(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DbUtil.close(conn);
        }
        return list;
    }


    public List<Goods> lowList() {
        List<Goods> list = new ArrayList<Goods>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from Goods ");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConn();

            rs = DbFun.query(conn, sql, params);
            while (rs.next()){
                if(rs.getDouble("nowStock")<rs.getDouble("saveStock")){
                    list.add(toBean(rs));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.close(conn);
        }

        return list;
    }

}
