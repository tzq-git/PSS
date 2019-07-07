package edu.pss.dao.impl;

import com.liuvei.common.DbFun;
import edu.pss.bean.RPurchase;
import edu.pss.dao.RPurchaseDao;
import edu.pss.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RPurchaseDaoImpl implements RPurchaseDao {
    @Override
    public List<RPurchase> list() {
        List<RPurchase> list = new ArrayList<RPurchase>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from RPurchase ");

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
    public Long insert(RPurchase bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Insert Into RPurchase");
        sbSQL.append("(");
        sbSQL.append(" rPurchaseId,pid,pName,outDate");
        sbSQL.append(" ,gid,num,price,totalAmount");
        sbSQL.append(" )");
        sbSQL.append(" value(?,?,?,?, ?,?,?,?)");

        paramsList.add(bean.getrPurchaseId());
        paramsList.add(bean.getPid());
        paramsList.add(bean.getpName());
        paramsList.add(bean.getOutDate());

        paramsList.add(bean.getGid());
        paramsList.add(bean.getNum());
        paramsList.add(bean.getPrice());
        paramsList.add(bean.getTotalAmount());

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try{
            conn = DbUtil.getConn();

            result= DbFun.update(conn, sql, params);



        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }

        //更新之后开始执行减少相应商品的存量
        List<Object> paramsList1 = new ArrayList<Object>();
        StringBuffer sbSQL1 = new StringBuffer();
        Double nowStock = new GoodsDaoImpl().load(bean.getGid()).getNowStock();
        sbSQL1.append(" update Goods set nowStock=? where gid=?");
        paramsList1.add(nowStock-bean.getNum());
        paramsList1.add(bean.getGid());

        String sql1 = sbSQL1.toString();
        Object[] params1 = paramsList1.toArray();

        try{
            DbFun.update(conn, sql1, params1);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }finally {
            DbUtil.close(conn);
        }


        return result;
    }

    @Override
    public Long update(RPurchase bean) {

        //在更新之前确认更新数据是增加还是减少
        Double cnum = load(bean.getrPurchaseId()).getNum()-bean.getNum();

        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" update  RPurchase");
        sbSQL.append(" set pid=?,pName=?,outDate=?");
        sbSQL.append(" ,gid=?,num=?,price=?,totalAmount=?");
        sbSQL.append(" where rPurchaseId=?");


        paramsList.add(bean.getPid());
        paramsList.add(bean.getpName());
        paramsList.add(bean.getOutDate());

        paramsList.add(bean.getGid());
        paramsList.add(bean.getNum());
        paramsList.add(bean.getPrice());
        paramsList.add(bean.getTotalAmount());

        paramsList.add(bean.getrPurchaseId());


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

        sbSQL.append(" delete from RPurchase");
        sbSQL.append(" where rPurchaseId=?");

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
    public RPurchase load(Long id) {

        RPurchase bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();


        sbSQL.append(" select R.*,G.gName from RPurchase R");
        sbSQL.append(" left join Goods G on R.gid=G.gid");
        sbSQL.append(" where R.rPurchaseId=?");

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
    public RPurchase loadByName(String name) {
        RPurchase bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from rPurchase");
        sbSQL.append(" where pName=?");
        sbSQL.append(" order by rPurchaseId asc");

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


    private RPurchase toBean(ResultSet rs) {

        RPurchase bean = new RPurchase ();

        try {

            bean.setrPurchaseId (rs.getLong( "rPurchaseId"));
            bean.setPid (rs.getLong( "pid"));
            bean.setpName (rs.getString( "pName"));
            bean.setOutDate (rs.getDate(  "outDate"));
            bean.setGid (rs.getLong(  "gid"));
            bean.setNum (rs.getDouble(  "num"));
            bean.setPrice (rs.getDouble( "price"));
            bean.setTotalAmount (rs.getDouble(  "totalAmount"));


        }catch (SQLException e){

            // TODO Auto-generated catch block
            e. printStackTrace() ;

            throw new RuntimeException(e);
        }
        return bean;

    }

    private RPurchase toBeanEX(ResultSet rs) {

        RPurchase bean = new RPurchase ();

        try {

            bean.setrPurchaseId (rs.getLong( "rPurchaseId"));
            bean.setPid (rs.getLong( "pid"));
            bean.setpName (rs.getString( "pName"));
            bean.setOutDate (rs.getDate(  "outDate"));
            bean.setGid (rs.getLong(  "gid"));
            bean.setgName(rs.getString("gName"));//增加一个属性
            bean.setNum (rs.getDouble(  "num"));
            bean.setPrice (rs.getDouble( "price"));
            bean.setTotalAmount (rs.getDouble(  "totalAmount"));


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

        sbSQL.append(" select count(1) From RPurchase");

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
    public List<RPurchase> pager(Long pageNum, Long pageSize) {

        List<RPurchase> list = new ArrayList<RPurchase>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();


        sbSQL.append(" select R.*,G.gName from RPurchase R");
        sbSQL.append(" left join Goods G on R.gid=G.gid");
        sbSQL.append(" order by rPurchaseId asc");


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

        sbSQL.append(" select count(1) From RPurchase");
        sbSQL.append(" where pName like ?");
        sbSQL.append(" order by rPurchaseId asc");


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
    public List<RPurchase> pagerByName(String name,Long pageNum, Long pageSize) {

        System.out.println("name是：" +name);
        List<RPurchase> list = new ArrayList<RPurchase>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();


        sbSQL.append(" select R.*,G.gName from RPurchase R");
        sbSQL.append(" left join Goods G on R.gid=G.gid");
        sbSQL.append(" where R.pName like ?");
        sbSQL.append(" order by rPurchaseId asc");

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
