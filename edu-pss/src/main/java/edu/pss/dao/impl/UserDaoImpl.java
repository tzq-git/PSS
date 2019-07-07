package edu.pss.dao.impl;

import com.liuvei.common.DbFun;
import edu.pss.bean.User;
import edu.pss.dao.UserDao;
import edu.pss.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    
    @Override
    public List<User> list() {

        List<User> list = new ArrayList<User>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From User ");
        sbSQL.append(" Where userId!=1");

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
    public Long insert(User bean) {

        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Insert Into User ");
        sbSQL.append(" ( ");
        sbSQL.append(" userName, userPwd, type, telPhone, email ");
        sbSQL.append(" ) ");
        sbSQL.append(" Values(?,?,?,?,?) ");

        paramsList.add(bean.getUserName());
        paramsList.add(bean.getUserPwd());
        paramsList.add(bean.getType());
        paramsList.add(bean.getTelPhone());
        paramsList.add(bean.getEmail());

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = DBUtils.getConnection();
        try{

            Long num = DBUtils.update(conn, sql, params);

            if ( num > 0){
                /**
                 *获取《当前连接》插入得最后一个自增ID值
                 */
                sql = "Select @@IDENTITY";
                result = DBUtils.queryScalarLong(conn, sql);
            }
        }catch( Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(conn);
        }

        return result;
    }

    @Override
    public Long delete(Long id) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" delete from User ");
        sbSQL.append(" where userId=? and userId!=1 ");

        paramsList.add(id);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = DBUtils.getConnection();
        try{
            result = DBUtils.update(conn, sql, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBUtils.close(conn);
        }
        return result;
    }

    @Override
    public Long update(User bean) {

        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" update User ");
        sbSQL.append(" set userName=?, userPwd=?, type=?, telPhone=?, email=? ");
        sbSQL.append(" where userId=? and userId!=1 ");

        paramsList.add(bean.getUserName());
        paramsList.add(bean.getUserPwd());
        paramsList.add(bean.getType());
        paramsList.add(bean.getTelPhone());
        paramsList.add(bean.getEmail());

        paramsList.add(bean.getUserId());

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();
        Connection conn = null;
        try{
            conn = DBUtils.getConnection();

            result = DBUtils.update(conn, sql, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBUtils.close(conn);
        }

        return result;
    }

    @Override
    public User loadById(Long id) {
        User bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from User ");
        sbSQL.append(" where userId=? ");

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

    @Override
    public User loadByName(String name) {
        User bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from User ");
        sbSQL.append(" where userName=?");

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

        sbSQL.append(" Select count(1) From User ");
        sbSQL.append(" Where userId!=1 ");

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
    public List<User> pager(Long pageNum, Long pageSize) {

        List<User> list = new ArrayList<User>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From User ");
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

        sbSQL.append(" Select count(1) From User ");
        sbSQL.append(" where userName like ? and userId!=1 ");
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
    public Long restPwd(Long id, String pwd) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" update User ");
        sbSQL.append(" set userPwd=?");
        sbSQL.append(" where userId=?");

        paramsList.add(pwd);

        paramsList.add(id);

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();
        Connection conn = null;
        try{
            conn = DBUtils.getConnection();

            result = DBUtils.update(conn, sql, params);

        } catch ( Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            DBUtils.close(conn);
        }

        return result;
    }

    @Override
    public List<User> pagerByName(String name, Long pageNum, Long pageSize) {
        List<User> list = new ArrayList<User>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Select * From User ");
        sbSQL.append(" where userName like ? and userId!=1 ");
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

    private User toBean(ResultSet rs){
            User bean = new User();

            try{
                bean.setUserId(rs.getLong("userId"));
                bean.setUserName(rs.getString("userName"));
                bean.setUserPwd(rs.getString("userPwd"));
                bean.setType(rs.getLong("type"));
                bean.setTelPhone(rs.getString("telPhone"));
                bean.setEmail(rs.getString("email"));

            } catch ( Exception e){
                e.printStackTrace();
            }

            return bean;
        }
        
}
