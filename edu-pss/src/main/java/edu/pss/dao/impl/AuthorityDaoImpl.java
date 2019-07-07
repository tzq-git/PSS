package edu.pss.dao.impl;

import edu.pss.bean.Authority;
import edu.pss.dao.AuthorityDao;
import edu.pss.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthorityDaoImpl implements AuthorityDao {

    @Override
    public List<Authority> list() {

        List<Authority> list = new ArrayList<Authority>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From Authority ");
        sbSQL.append(" Where userId!=1 ");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = DBUtils.getConnection();

        try{
            list = DBUtils.query(conn, sql, Authority.class, params);

        }catch( Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(conn);
        }

        return list;
    }
    
    @Override
    public Long update(Authority bean) {

        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Update Authority ");
        sbSQL.append(" Set haveSaleMan=?, haveGoods=?, haveCustomer=?, haveSupplier=? ");
        sbSQL.append(" , havePurchaseIn=?, havePurchaseOut=?, haveSaleOut=?, haveSaleReturn=? ");
        sbSQL.append(" Where userId=? and userId!=1 ");


        paramsList.add(bean.getHaveSaleMan());
        paramsList.add(bean.getHaveGoods());
        paramsList.add(bean.getHaveCustomer());
        paramsList.add(bean.getHaveSupplier());

        paramsList.add(bean.getHavePurchaseIn());
        paramsList.add(bean.getHavePurchaseOut());
        paramsList.add(bean.getHaveSaleOut());
        paramsList.add(bean.getHaveSaleReturn());

        paramsList.add(bean.getUserId());

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = DBUtils.getConnection();
        try{
            result = DBUtils.update(conn, sql, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

        return result;
    }

    @Override
    public Authority loadById(Long id) {
        Authority bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From Authority ");
        sbSQL.append(" Where userId=? ");

        paramsList.add(id);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        ResultSet rs = null;
        Connection conn = DBUtils.getConnection();

        try {
            bean = DBUtils.queryOne(conn, sql, Authority.class, params);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return bean;
    }

    @Override
    public Long insert(Authority bean) {

        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Insert Into Authority ");
        sbSQL.append(" ( ");
        sbSQL.append(" userId, userName, description");
        sbSQL.append(" , haveSaleMan, haveGoods, haveCustomer, haveSupplier ");
        sbSQL.append(" , havePurchaseIn, havePurchaseOut, haveSaleOut, haveSaleReturn ");
        sbSQL.append(" ) ");
        sbSQL.append(" Values(?,?,?, ?,?,?,?, ?,?,?,?) ");

        paramsList.add(bean.getUserId());
        paramsList.add(bean.getUserName());
        paramsList.add(bean.getDescription());

        paramsList.add(bean.getHaveSaleMan());
        paramsList.add(bean.getHaveGoods());
        paramsList.add(bean.getHaveCustomer());
        paramsList.add(bean.getHaveSupplier());

        paramsList.add(bean.getHavePurchaseIn());
        paramsList.add(bean.getHavePurchaseOut());
        paramsList.add(bean.getHaveSaleOut());
        paramsList.add(bean.getHaveSaleReturn());

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = DBUtils.getConnection();
        try{

            result = DBUtils.update(conn, sql, params);

        }catch( Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtils.close(conn);
        }
        return result;
    }

    @Override
    public Authority loadByName(String name) {
        Authority bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from Authority ");
        sbSQL.append(" where userName=? ");

        paramsList.add(name);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = DBUtils.getConnection();
        try {

            bean = DBUtils.queryOne(conn, sql, Authority.class, params);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            DBUtils.close(conn);
        }

        return bean;
    }

    @Override
    public Long count() {

        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();

        sbSQL.append("Select count(1) From Authority");
        sbSQL.append(" Where userId!=1 ");

        String sql = sbSQL.toString();

        Connection conn = DBUtils.getConnection();
        try{

            result = DBUtils.queryScalarLong(conn, sql);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            DBUtils.close(conn);
        }
        //返回总条数减去admin的一条记录
        return result-1;
    }

    @Override
    public List<Authority> pager(Long pageNum, Long pageSize) {

        List<Authority> list = new ArrayList<Authority>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From Authority ");
        sbSQL.append(" Where userId!=1 ");
        sbSQL.append(" order by userId asc ");

        if (pageNum < 1){
            pageNum = 1L;
        }
        if ( pageSize < 1){
            pageSize = 10L;
        }
        Long startIndex = (pageNum -1) * pageSize;
        sbSQL.append(" limit " + startIndex + ", " + pageSize);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = DBUtils.getConnection();
        try{

            list = DBUtils.query(conn, sql, Authority.class, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            DBUtils.close(conn);
        }

        return list;
    }

    @Override
    public Long countByName(String name) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select count(1) From Authority ");
        sbSQL.append(" where userName like ? and userId!=1 ");
        paramsList.add("%" + name + "%");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = DBUtils.getConnection();
        try{
            result = DBUtils.queryScalarLong(conn, sql, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
          DBUtils.close(conn);
        }

        return result;
    }

    @Override
    public List<Authority> pagerByName(String name, Long pageNum, Long pageSize) {
        List<Authority> list = new ArrayList<Authority>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From Authority ");
        sbSQL.append(" where userName like ? and userId!=1");
        sbSQL.append(" order by userId asc ");
        paramsList.add("%" + name + "%");

        if (pageNum < 1){
            pageNum = 1L;
        }
        if ( pageSize < 1){
            pageSize = 10L;
        }
        Long startIndex = (pageNum -1) * pageSize;
        sbSQL.append(" limit " + startIndex + ", " + pageSize);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = DBUtils.getConnection();

        try{

            list = DBUtils.query(conn, sql, Authority.class, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            DBUtils.close(conn);
        }

        return list;
    }

}
