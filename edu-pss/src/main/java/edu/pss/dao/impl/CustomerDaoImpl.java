package edu.pss.dao.impl;

import com.liuvei.common.DbFun;
import edu.pss.bean.Customer;
import edu.pss.dao.CustomerDao;
import edu.pss.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{
    @Override
    public List<Customer> list() {
        List<Customer> list = new ArrayList<Customer>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from Customer ");

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
    public Long insert(Customer bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();


        sbSQL.append(" Insert Into Customer");
        sbSQL.append("(");
        sbSQL.append(" cName,companySN,companyAN,sid");
        sbSQL.append(" ,phone,fax,address,receiveAddress,isDeleted");
        sbSQL.append(" )");
        sbSQL.append(" value(?,?,?,?, ?,?,?,?,?)");

        paramsList.add(bean.getcName());
        paramsList.add(bean.getCompanySN());
        paramsList.add(bean.getCompanyAN());
        paramsList.add(bean.getSid());

        paramsList.add(bean.getPhone());
        paramsList.add(bean.getFax());
        paramsList.add(bean.getAddress());
        paramsList.add(bean.getReceiveAddress());
        paramsList.add(bean.getIsDeleted());



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
    public Long update(Customer bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" update  Customer");
        sbSQL.append(" set cName=?,companySN=?,companyAN=?,sid=?");
        sbSQL.append(" ,phone=?,fax=?,address=?,receiveAddress=?");
        sbSQL.append(" where cid=?");


        paramsList.add(bean.getcName());
        paramsList.add(bean.getCompanySN());
        paramsList.add(bean.getCompanyAN());
        paramsList.add(bean.getSid());

        paramsList.add(bean.getPhone());
        paramsList.add(bean.getFax());
        paramsList.add(bean.getAddress());
        paramsList.add(bean.getReceiveAddress());

        paramsList.add(bean.getCid());


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

        sbSQL.append(" delete from Customer");
        sbSQL.append(" where cid=?");

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
    public Customer load(Long id) {

        Customer bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select C.*,S.sCName  from Customer C");
        sbSQL.append(" left join SalePerson S on C.sid=S.sid");
        sbSQL.append(" where C.cid=?");

        paramsList.add(id);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConn();

            rs = DbFun.query(conn, sql,params);

            if(rs.next()){
                bean= toBeanEX(rs);
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
    public Customer loadByName(String name) {
        Customer bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from customer");
        sbSQL.append(" where cName=?");
        sbSQL.append(" order by cid asc");

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


    private Customer toBean(ResultSet rs) {

        Customer bean = new Customer ();

        try {

            bean.setCid (rs.getLong( "cid"));
            bean.setcName (rs.getString( "cName"));
            bean.setCompanySN (rs.getString( "companySN"));
            bean.setCompanyAN (rs.getString(  "companyAN"));
            bean.setSid (rs.getLong(  "sid"));
            bean.setPhone (rs.getString(  "phone"));
            bean.setFax (rs.getString( "fax"));
            bean.setAddress (rs.getString(  "address"));
            bean.setReceiveAddress (rs.getString (  "receiveAddress"));
            bean.setIsDeleted (rs.getLong (  "isDeleted"));


        }catch (SQLException e){

            e. printStackTrace() ;

            throw new RuntimeException(e);
        }
        return bean;

    }

    private Customer toBeanEX(ResultSet rs) {

        Customer bean = new Customer ();

        try {

            bean.setCid (rs.getLong( "cid"));
            bean.setcName (rs.getString( "cName"));
            bean.setCompanySN (rs.getString( "companySN"));
            bean.setCompanyAN (rs.getString(  "companyAN"));
            bean.setSid (rs.getLong(  "sid"));
            bean.setsCName(rs.getString( "sCName"));//新加入的销售员参照名
            bean.setPhone (rs.getString(  "phone"));
            bean.setFax (rs.getString( "fax"));
            bean.setAddress (rs.getString(  "address"));
            bean.setReceiveAddress (rs.getString (  "receiveAddress"));
            bean.setIsDeleted (rs.getLong (  "isDeleted"));


        }catch (SQLException e){

            // TODO Auto-generated catch block
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

        sbSQL.append(" select count(1) From Customer");

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
    public List<Customer> pager(Long pageNum, Long pageSize) {

        List<Customer> list = new ArrayList<Customer>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select C.*,S.sCName  from Customer C");
        sbSQL.append(" left join SalePerson S on C.sid=S.sid");
        sbSQL.append(" order by C.cid asc");


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
                list.add(toBeanEX(rs));

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

        sbSQL.append(" select count(1) From Customer");
        sbSQL.append(" where cName like ?");
        sbSQL.append(" order by cid asc");

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
    public List<Customer> pagerByName(String name,Long pageNum, Long pageSize) {

        System.out.println("name是：" +name);
        List<Customer> list = new ArrayList<Customer>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select C.*,S.sCName  from Customer C");
        sbSQL.append(" left join SalePerson S on C.sid=S.sid");
        sbSQL.append(" where C.cName like ?");
        sbSQL.append(" order by C.cid asc");

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
                list.add(toBeanEX(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DbUtil.close(conn);
        }
        return list;
    }
}
