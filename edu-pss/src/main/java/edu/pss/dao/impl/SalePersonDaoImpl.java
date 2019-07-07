package edu.pss.dao.impl;

import com.liuvei.common.DbFun;
import edu.pss.bean.SalePerson;
import edu.pss.dao.SalePersonDao;
import edu.pss.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalePersonDaoImpl implements SalePersonDao {
    @Override
    public List<SalePerson> list() {
        List<SalePerson> list = new ArrayList<SalePerson>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from SalePerson ");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConn();  ////////////////////here

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
    public Long insert(SalePerson bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Insert Into SalePerson");
        sbSQL.append("(");
        sbSQL.append(" sCName,sEName,rCustomer");
        sbSQL.append(" ,tel,phone,email,address");
        sbSQL.append(" )");
        sbSQL.append(" value(?,?,?, ?,?,?,?)");

        paramsList.add(bean.getsCName());
        paramsList.add(bean.getsEName());
        paramsList.add(bean.getrCustomer());

        paramsList.add(bean.getTel());
        paramsList.add(bean.getPhone());
        paramsList.add(bean.getEmail());
        paramsList.add(bean.getAddress());




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
    public Long update(SalePerson bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" update  SalePerson");
        sbSQL.append(" set sCName=?,sEName=?,rCustomer=?");
        sbSQL.append(" ,tel=?,phone=?,email=?,address=?,isDeleted=?");

        sbSQL.append(" where sid=?");


        paramsList.add(bean.getsCName());
        paramsList.add(bean.getsEName());
        paramsList.add(bean.getrCustomer());

        paramsList.add(bean.getTel());
        paramsList.add(bean.getPhone());
        paramsList.add(bean.getEmail());
        paramsList.add(bean.getAddress());
        paramsList.add(bean.getIsDeleted());

        paramsList.add(bean.getSid());


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

        sbSQL.append(" delete from SalePerson");
        sbSQL.append(" where sid=?");

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
    public SalePerson load(Long id) {

        SalePerson bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from salePerson");
        sbSQL.append(" where sid=?");

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
    public SalePerson loadByName(String name) {
        SalePerson bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from salePerson");
        sbSQL.append(" where sCName=?");
        sbSQL.append(" order by sid asc");

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


    private SalePerson toBean(ResultSet rs) {

        SalePerson bean = new SalePerson ();

        try {

            bean.setSid (rs.getLong( "sid"));
            bean.setsCName(rs.getString("sCName"));
            bean.setsEName(rs.getString("sEName"));
            bean.setTel(rs.getString("tel"));

            bean.setPhone(rs.getString("phone"));
            bean.setAddress(rs.getString("address"));
            bean.setEmail(rs.getString("email"));
            bean.setrCustomer(rs.getString("rCustomer"));
            bean.setIsDeleted(rs.getLong("isDeleted"));


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

        sbSQL.append(" select count(1) From SalePerson");

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
    public List<SalePerson> pager(Long pageNum, Long pageSize) {

        List<SalePerson> list = new ArrayList<SalePerson>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * from SalePerson");
        sbSQL.append(" order by sid asc");


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

        sbSQL.append(" select count(1) From SalePerson");
        sbSQL.append(" where sCName like ?");
        sbSQL.append(" order by sid asc");

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
    public List<SalePerson> pagerByName(String name,Long pageNum, Long pageSize) {

        System.out.println("name是：" +name);
        List<SalePerson> list = new ArrayList<SalePerson>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * from SalePerson");
        sbSQL.append(" where sCName like ?");
        sbSQL.append(" order by sid asc");

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
    public Long loadCustomer(Long id) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select count(1) From Customer ");
        sbSQL.append(" where sid=?");

        paramsList.add(id);

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

}
