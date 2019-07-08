package edu.pss.dao.impl;

import com.liuvei.common.DbFun;
import edu.pss.bean.Purchase;
import edu.pss.dao.PurchaseDao;
import edu.pss.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDaoImpl  implements PurchaseDao {
    @Override
    public List<Purchase> list() {
        List<Purchase> list = new ArrayList<Purchase>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

      //  sbSQL.append(" select * from Purchase ");

        sbSQL.append(" select P.*,G.gName from Purchase P left join Goods G on P.gid=G.gid");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConn();

            rs = DbFun.query(conn, sql, params);
            while (rs.next()){
                list.add(toBeanEX(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.close(conn);
        }

        return list;
    }

    @Override
    public List<Purchase> list(String year) {
        List<Purchase> list = new ArrayList<Purchase>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        //  sbSQL.append(" select * from Purchase ");

        sbSQL.append(" select P.*,G.gName from Purchase P left join Goods G on P.gid=G.gid where buyDate between  ?  and ?");

        paramsList.add(year+"-1-1 00:00:00");
        paramsList.add(year+"-12-31 23:59:59");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();


        Connection conn = null;
        ResultSet rs = null;
        try{
            conn = DbUtil.getConn();

            rs = DbFun.query(conn, sql, params);
            while (rs.next()){
                list.add(toBeanEX(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.close(conn);
        }

        return list;
    }

    @Override
    public Long insert(Purchase bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" Insert Into Purchase");
        sbSQL.append("(");
        sbSQL.append(" purchaseId,pid,pName,buyDate");
        sbSQL.append(" ,gid,num,price,totalAmount");
        sbSQL.append(" )");
        sbSQL.append(" value(?,?,?,?, ?,?,?,?)");


        paramsList.add(bean.getPurchaseId());
        paramsList.add(bean.getPid());
        paramsList.add(bean.getpName());
        paramsList.add(bean.getBuyDate());

        paramsList.add(bean.getGid());
        paramsList.add(bean.getNum());
        paramsList.add(bean.getPrice());
        paramsList.add(bean.getTotalAmount());

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try{
            conn = DbUtil.getConn();

            result = DbFun.update(conn, sql, params);

//            if(num>0){
//                sql = "Select @@identity";
//                result = DbFun.queryScalarLong(conn, sql);
//            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    //更新之后开始执行增加相应商品的存量
        List<Object> paramsList1 = new ArrayList<Object>();
        StringBuffer sbSQL1 = new StringBuffer();
        Double nowStock = new GoodsDaoImpl().load(bean.getGid()).getNowStock();
        sbSQL1.append(" update Goods set nowStock=? where gid=?");
        paramsList1.add(nowStock+bean.getNum());
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
    public Long update(Purchase bean) {


        //在更新之前确认更新数据是增加还是减少
        Double cnum = bean.getNum()-load(bean.getPurchaseId()).getNum();


        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" update  Purchase");
        sbSQL.append(" set pid=?,pName=?,buyDate=?");
        sbSQL.append(" ,gid=?,num=?,price=?,totalAmount=?");
        sbSQL.append(" where purchaseId=?");


        paramsList.add(bean.getPid());
        paramsList.add(bean.getpName());
        paramsList.add(bean.getBuyDate());

        paramsList.add(bean.getGid());
        paramsList.add(bean.getNum());
        paramsList.add(bean.getPrice());
        paramsList.add(bean.getTotalAmount());

        paramsList.add(bean.getPurchaseId());


        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try{
            conn = DbUtil.getConn();

            result = DbFun.update(conn, sql, params);


        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }


        //更新之后开始执行改变相应商品的存量
        List<Object> paramsList1 = new ArrayList<Object>();
        StringBuffer sbSQL1 = new StringBuffer();
        Double nowStock = new GoodsDaoImpl().load(bean.getGid()).getNowStock();
        sbSQL1.append(" update Goods set nowStock=? where gid=?");
        paramsList1.add(nowStock+cnum);
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
    public Long delete(Long id) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" delete from Purchase");
        sbSQL.append(" where purchaseId=?");

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

        //删除之后开始执行减少相应商品的存量
        Purchase bean = load(id);
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
    public Purchase load(Long id) {

        Purchase bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();



        sbSQL.append(" select P.*,G.gName from Purchase P");
        sbSQL.append(" left join Goods G on P.gid=G.gid");
        sbSQL.append(" where P.purchaseId=?");
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
    public Purchase loadByName(String name) {
        Purchase bean = null;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from purchase");
        sbSQL.append(" where pName=?");
        sbSQL.append(" order by purchaseId asc");

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


    private Purchase toBean(ResultSet rs) {

        Purchase bean = new Purchase ();

        try {

            bean.setPurchaseId (rs.getLong( "purchaseId"));
            bean.setPid (rs.getLong( "pid"));
            bean.setpName (rs.getString( "pName"));
            bean.setBuyDate (rs.getDate(  "buyDate"));
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

    private Purchase toBeanEX(ResultSet rs) {

        Purchase bean = new Purchase ();

        try {

            bean.setPurchaseId (rs.getLong( "purchaseId"));
            bean.setPid (rs.getLong( "pid"));
            bean.setpName (rs.getString( "pName"));
            bean.setBuyDate (rs.getDate(  "buyDate"));
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

        sbSQL.append(" select count(1) From Purchase");

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
    public List<Purchase> pager(Long pageNum, Long pageSize) {

        List<Purchase> list = new ArrayList<Purchase>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();


        sbSQL.append(" select P.*,G.gName from Purchase P");
        sbSQL.append(" left join Goods G on P.gid=G.gid");
        sbSQL.append(" order by purchaseId asc");


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

        sbSQL.append(" select count(1) From Purchase");
        sbSQL.append(" where pName like ?");
        sbSQL.append(" order by purchaseId asc");

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
    public List<Purchase> pagerByName(String name,Long pageNum, Long pageSize) {

        System.out.println("name是：" +name);
        List<Purchase> list = new ArrayList<Purchase>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();


        sbSQL.append(" select P.*,G.gName from Purchase P");
        sbSQL.append(" left join Goods G on P.gid=G.gid where P.pName like ?");
        sbSQL.append(" order by purchaseId asc");

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
