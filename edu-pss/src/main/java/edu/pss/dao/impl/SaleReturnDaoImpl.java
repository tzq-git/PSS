package edu.pss.dao.impl;

import com.liuvei.common.DbFun;
import edu.pss.bean.SaleReturn;
import edu.pss.dao.SaleReturnDao;
import edu.pss.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleReturnDaoImpl implements SaleReturnDao {
    @Override
    public List<SaleReturn> list() {

        StringBuffer sbSQL = new StringBuffer();
        List<SaleReturn> list = new ArrayList<SaleReturn>();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From SaleReturn ");

        Object[] params = paramsList.toArray();
        String sql = sbSQL.toString();

        Connection conn = null;
        ResultSet rs = null;
        try {

            conn = DBUtils.getConnection();
            rs = DbFun.query(conn, sql, params);

            while (rs.next()){
                list.add(toBean(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }

        return list;
    }

    @Override
    public Long insert(SaleReturn bean) {
        Long result = 0L;
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<>();

        sbSQL.append(" Insert Into SaleReturn ");
        sbSQL.append(" ( ");
        sbSQL.append(" returnId, orderId, cName, returnDate ");
        sbSQL.append(" , salesMan, price, returnDetailId ");
        sbSQL.append(" ) ");
        sbSQL.append(" Values(?,?,?,?, ?,?,?) ");

        paramsList.add(bean.getReturnId());
        paramsList.add(bean.getOrderId());
        paramsList.add(bean.getcName());
        paramsList.add(bean.getReturnDate());

        paramsList.add(bean.getSalesMan());
        paramsList.add(bean.getPrice());
        paramsList.add(bean.getReturnDetailId());

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            result = DbFun.update(conn, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }
        return result;
    }

    @Override
    public Long delete(Long id) {
        Long result = 0L;
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<>();

        sbSQL.append(" Delete From SaleReturn ");
        sbSQL.append(" Where returnId=? ");

        paramsList.add(id);

        Object[] params = paramsList.toArray();
        String sql = sbSQL.toString();

        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            result = DbFun.update(conn, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }

        return result;
    }

    @Override
    public Long update(SaleReturn bean) {
        Long result = 0L;
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<>();

        sbSQL.append(" Update SaleReturn ");
        sbSQL.append(" set cName=?, returnDate=?, salesMan=? ");
        sbSQL.append(" , price=?, returnDetailId=? ");
        sbSQL.append(" Where returnId=? ");

        paramsList.add(bean.getcName());
        paramsList.add(bean.getReturnDate());
        paramsList.add(bean.getSalesMan());
        paramsList.add(bean.getPrice());
        paramsList.add(bean.getReturnDetailId());

        paramsList.add(bean.getReturnId());

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try {
            conn = DBUtils.getConnection();

            result = DbFun.update(conn, sql, params);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn);
        }

        return result;
    }

    @Override
    public SaleReturn loadById(Long id) {
        SaleReturn bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from SaleReturn ");
        sbSQL.append(" Where returnId=? ");

        paramsList.add(id);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            rs = DbFun.query(conn, sql, params);

            if ( rs.next() ){
                bean = toBean(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            DBUtils.close(conn);
        }
        return bean;
    }

    /**
     * 暂未使用，留作拓展
     * @param name
     * @return
     */
    @Override
    public SaleReturn loadByName(String name) {
        SaleReturn bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * from SaleReturn ");
        sbSQL.append(" Where cName=? ");

        paramsList.add(name);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();

            rs = DbFun.query(conn, sql, params);

            if ( rs.next() ){
                bean = toBean(rs);
            }
        } catch (SQLException e) {
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
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select count(1) From SaleReturn ");


        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try{
            conn = DBUtils.getConnection();

            result = DbFun.queryScalarLong(conn, sql, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBUtils.close(conn);
        }
        return result;
    }

    @Override
    public List<SaleReturn> pager(Long pageNum, Long pageSize) {

        List<SaleReturn> list = new ArrayList<SaleReturn>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From SaleReturn ");
        sbSQL.append(" order by returnDate asc ");

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

        ResultSet rs = null;
        Connection conn = null;
        try{
            conn = DBUtils.getConnection();

            rs = DbFun.query(conn, sql, params);

            while ( rs.next()){
                list.add( toBean(rs) );
            }
        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBUtils.close(conn);
        }

        return list;
    }

    @Override
    public Long countByName(String name) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select count(1) From SaleReturn ");
        sbSQL.append(" where cName like ?");
        paramsList.add("%" + name + "%");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try{
            conn = DBUtils.getConnection();

            result = DbFun.queryScalarLong(conn, sql, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBUtils.close(conn);
        }
        return result;
    }

    @Override
    public List<SaleReturn> pagerByName(String name, Long pageNum, Long pageSize) {
        List<SaleReturn> list = new ArrayList<SaleReturn>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From SaleReturn ");
        sbSQL.append(" where cName like ? ");
        sbSQL.append(" order by returnDate asc ");
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

        ResultSet rs = null;
        Connection conn = null;
        try{
            conn = DBUtils.getConnection();

            rs = DbFun.query(conn, sql, params);

            while ( rs.next()){
                list.add( toBean(rs) );
            }
        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBUtils.close(conn);
        }

        return list;
    }

    private SaleReturn toBean(ResultSet rs){
        SaleReturn bean = new SaleReturn();

        try{
            bean.setReturnId(rs.getLong("returnId"));
            bean.setOrderId(rs.getLong("orderId"));
            bean.setcName(rs.getString("cName"));
            bean.setReturnDate(rs.getTimestamp("returnDate"));
            bean.setPrice(rs.getString("price"));
            bean.setReturnDetailId(rs.getLong("returnDetailId"));
            bean.setSalesMan(rs.getString("salesMan"));
        } catch ( Exception e){
            e.printStackTrace();
        }

        return bean;
    }
}
