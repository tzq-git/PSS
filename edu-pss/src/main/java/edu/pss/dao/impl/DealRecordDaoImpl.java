package edu.pss.dao.impl;

import com.liuvei.common.DbFun;
import edu.pss.bean.DealRecord;
import edu.pss.dao.DealRecordDao;
import edu.pss.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealRecordDaoImpl implements DealRecordDao {
    @Override
    public List<DealRecord> list(Long cid) {
        List<DealRecord> list = new ArrayList<DealRecord>();

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        paramsList.add(cid);

        sbSQL.append(" select * from DealRecord ");
        sbSQL.append(" where cid=?");


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
    public Long insert(DealRecord bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();


        sbSQL.append(" Insert Into DealRecord");
        sbSQL.append("(");
        sbSQL.append(" orderId,cid,dealDate,salesMan");
        sbSQL.append(" ,receiveAddress,billId,amount");
        sbSQL.append(" )");
        sbSQL.append(" value(?,?,?,?, ?,?,?)");

        paramsList.add(bean.getOrderId());
        paramsList.add(bean.getCid());
        paramsList.add(bean.getDealDate());
        paramsList.add(bean.getSalesMan());

        paramsList.add(bean.getReceiveAddress());
        paramsList.add(bean.getBillId());
        paramsList.add(bean.getAmount());



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
    public Long update(DealRecord bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" update  DealRecord");
        sbSQL.append(" set cid=?,dealDate=?,salesMan=?");
        sbSQL.append(" ,receiveAddress=?,billId=?,amount=?");
        sbSQL.append(" where orderId=?");



        paramsList.add(bean.getCid());
        paramsList.add(bean.getDealDate());
        paramsList.add(bean.getSalesMan());

        paramsList.add(bean.getReceiveAddress());
        paramsList.add(bean.getBillId());
        paramsList.add(bean.getAmount());

        paramsList.add(bean.getOrderId());


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

        sbSQL.append(" delete from DealRecord");
        sbSQL.append(" where orderId=?");

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

    private DealRecord toBean(ResultSet rs) {

        DealRecord bean = new DealRecord ();

        try {
            bean.setOrderId(rs.getLong("orderId"));
            bean.setDealDate(rs.getDate("dealDate"));
            bean.setSalesMan(rs.getString("salesMan"));
            bean.setReceiveAddress(rs.getString("receiveAddress"));
            bean.setBillId(rs.getLong("billId"));
            bean.setAmount(rs.getDouble("amount"));

        }catch (SQLException e){

            e. printStackTrace() ;

            throw new RuntimeException(e);
        }
        return bean;

    }
}
