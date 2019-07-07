package edu.pss.dao.impl;

import com.liuvei.common.DbFun;
import edu.pss.bean.OutBound;
import edu.pss.dao.OutBoundDao;
import edu.pss.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OutBoundDaoImpl implements OutBoundDao {
    
    @Override
    public List<OutBound> list() {

        List<OutBound> list = new ArrayList<OutBound>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from OutBound ");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();

            rs = DbFun.query(conn, sql, params);

            while ( rs.next()){
                list.add(toBean(rs));
            }
        }catch( Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtils.close(conn);
        }

        return list;
    }

    @Override
    public Long insert(OutBound bean) {

        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Insert Into OutBound ");
        sbSQL.append(" ( ");
        sbSQL.append(" orderId, cName, gName, dealDate, outDate, address ");
        sbSQL.append(" , num, price, Amount, salesMan, SalesDetailId ");
        sbSQL.append(" ) ");
        sbSQL.append(" Values(?,?,?,?,?,?, ?,?,?,?,?) ");

        paramsList.add(bean.getOrderId());
        paramsList.add(bean.getcName());
        paramsList.add(bean.getgName());
        paramsList.add(bean.getDealDate());
        paramsList.add(bean.getOutDate());
        paramsList.add(bean.getAddress());

        paramsList.add(bean.getNum());
        paramsList.add(bean.getPrice());
        paramsList.add(bean.getAmount());
        paramsList.add(bean.getSalesMan());
        paramsList.add(bean.getSaleDetailId());

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try{
            conn = DBUtils.getConnection();

            result = DbFun.update(conn, sql, params);

//            if ( num > 0){
//                sql = "Select @@identity";
//                result = DbFun.queryScalarLong(conn, sql);
//            }
        }catch( Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBUtils.close(conn);
        }
        return result;
    }

    @Override
    public Long delete(Long id) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" delete from OutBound ");
        sbSQL.append(" where orderId=? ");

        paramsList.add(id);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try{
            conn = DBUtils.getConnection();

            result = DbFun.update(conn, sql, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBUtils.close(conn);
        }
        return result;
    }

    @Override
    public Long update(OutBound bean) {

        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();
        //判断Servlet传过来的bean，其中的status是否有参数
        if(bean.getStatus()!=null){
            sbSQL.append(" Update OutBound ");
            sbSQL.append(" set cName=?, gName=?, dealDate=?, outDate=?, address=? ");
            sbSQL.append(" , num=?, price=?, Amount=?, status=?, salesMan=?, SalesDetailId=? ");
            sbSQL.append(" Where orderId=? ");

            paramsList.add(bean.getcName());
            paramsList.add(bean.getgName());
            paramsList.add(bean.getDealDate());
            paramsList.add(bean.getOutDate());
            paramsList.add(bean.getAddress());

            paramsList.add(bean.getNum());
            paramsList.add(bean.getPrice());
            paramsList.add(bean.getAmount());
            paramsList.add(bean.getStatus());
            paramsList.add(bean.getSalesMan());
            paramsList.add(bean.getSaleDetailId());


            paramsList.add(bean.getOrderId());
        }else{
            sbSQL.append(" Update OutBound ");
            sbSQL.append(" set cName=?, gName=?, dealDate=?, outDate=?, address=? ");
            sbSQL.append(" , num=?, price=?, Amount=?, salesMan=?, SalesDetailId=? ");
            sbSQL.append(" Where orderId=? ");

            paramsList.add(bean.getcName());
            paramsList.add(bean.getgName());
            paramsList.add(bean.getDealDate());
            paramsList.add(bean.getOutDate());
            paramsList.add(bean.getAddress());

            paramsList.add(bean.getNum());
            paramsList.add(bean.getPrice());
            paramsList.add(bean.getAmount());
            paramsList.add(bean.getSalesMan());
            paramsList.add(bean.getSaleDetailId());


            paramsList.add(bean.getOrderId());

        }


        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();
        Connection conn = null;
        try{
            conn = DBUtils.getConnection();

            result = DbFun.update(conn, sql, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBUtils.close(conn);
        }

        return result;
    }

    @Override
    public OutBound loadById(Long id) {
        OutBound bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from OutBound ");
        sbSQL.append(" Where orderId=? ");

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
    public OutBound loadByName(String name) {
        OutBound bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * from OutBound ");
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

        sbSQL.append(" Select count(1) From OutBound ");


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
    public List<OutBound> pager(Long pageNum, Long pageSize) {

        List<OutBound> list = new ArrayList<OutBound>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From OutBound ");
        sbSQL.append(" order by dealDate asc ");

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

        sbSQL.append(" Select count(1) From OutBound ");
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
    public List<OutBound> pagerByName(String name, Long pageNum, Long pageSize) {
        List<OutBound> list = new ArrayList<OutBound>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From OutBound ");
        sbSQL.append(" where cName like ? ");
        sbSQL.append(" order by orderId asc ");
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

    private OutBound toBean(ResultSet rs){
        OutBound bean = new OutBound();

        try{
           bean.setOrderId(rs.getLong("orderId"));

           bean.setcName(rs.getString("cName"));
           bean.setgName(rs.getString("gName"));
           bean.setDealDate(rs.getTimestamp("dealDate"));
           bean.setOutDate(rs.getTimestamp("outDate"));
           bean.setAddress(rs.getString("address"));

           bean.setNum(rs.getDouble("num"));
           bean.setPrice(rs.getDouble("price"));
           bean.setAmount(rs.getDouble("amount"));
           bean.setStatus(rs.getLong("status"));
           bean.setSalesMan(rs.getString("salesMan"));
           bean.setSaleDetailId(rs.getLong("salesDetailId"));

        } catch ( Exception e){
            e.printStackTrace();
        }

        return bean;
    }
}
