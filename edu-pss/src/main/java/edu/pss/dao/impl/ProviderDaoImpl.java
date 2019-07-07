package edu.pss.dao.impl;

import com.liuvei.common.DbFun;
import edu.pss.bean.Provider;
import edu.pss.dao.ProviderDao;
import edu.pss.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProviderDaoImpl implements ProviderDao {
    @Override
    public List<Provider> list() {
        List<Provider> list = new ArrayList<Provider>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from Provider ");

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
    public Long insert(Provider bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();


        sbSQL.append(" Insert Into Provider");
        sbSQL.append("(");
        sbSQL.append(" pName,legalRepresentative,contactPerson,contactTel");
        sbSQL.append(" ,companyFax,companyTel,providerAddress,notes");
        sbSQL.append(" )");
        sbSQL.append(" value(?,?,?,?, ?,?,?,?)");

        paramsList.add(bean.getpName());
        paramsList.add(bean.getLegalRepresentative());
        paramsList.add(bean.getContactPerson());
        paramsList.add(bean.getContactTel());

        paramsList.add(bean.getCompanyFax());
        paramsList.add(bean.getCompanyTel());
        paramsList.add(bean.getProviderAddress());
        paramsList.add(bean.getNotes());


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
    public Long update(Provider bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" update  Provider");
        sbSQL.append(" set pName=?,legalRepresentative=?,contactPerson=?,contactTel=?");
        sbSQL.append(" ,companyFax=?,companyTel=?,providerAddress=?,notes=?");
        sbSQL.append(" where pid=?");


        paramsList.add(bean.getpName());
        paramsList.add(bean.getLegalRepresentative());
        paramsList.add(bean.getContactPerson());
        paramsList.add(bean.getContactTel());

        paramsList.add(bean.getCompanyFax());
        paramsList.add(bean.getCompanyTel());
        paramsList.add(bean.getProviderAddress());
        paramsList.add(bean.getNotes());

        paramsList.add(bean.getPid());


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

        sbSQL.append(" delete from Provider");
        sbSQL.append(" where pid=?");

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
    public Provider load(Long id) {

        Provider bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from provider");
        sbSQL.append(" where pid=?");

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
    public Provider loadByName(String name) {
        Provider bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from provider");
        sbSQL.append(" where pName=?");
        sbSQL.append(" order by pid asc");

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


    private Provider toBean(ResultSet rs) {

        Provider bean = new Provider ();

        try {

            bean.setPid (rs.getLong( "pid"));
            bean.setpName (rs.getString( "pName"));
            bean.setLegalRepresentative (rs.getString( "legalRepresentative"));
            bean.setContactPerson (rs.getString(  "contactPerson"));
            bean.setContactTel (rs.getString(  "contactTel"));
            bean.setCompanyFax (rs.getString(  "companyFax"));
            bean.setCompanyTel (rs.getString( "companyTel"));
            bean.setProviderAddress (rs.getString(  "providerAddress"));
            bean.setNotes (rs.getString (  "notes"));


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

        sbSQL.append(" select count(1) From Provider");

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
    public List<Provider> pager(Long pageNum, Long pageSize) {

        List<Provider> list = new ArrayList<Provider>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * from Provider");
        sbSQL.append(" order by pid asc");


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

        sbSQL.append(" select count(1) From Provider");
        sbSQL.append(" where pName like ?");
        sbSQL.append(" order by pid asc");

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
    public List<Provider> pagerByName(String name,Long pageNum, Long pageSize) {

        System.out.println("name是：" +name);
        List<Provider> list = new ArrayList<Provider>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * from Provider");
        sbSQL.append(" where pName like ?");
        sbSQL.append(" order by pid asc");

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
}
