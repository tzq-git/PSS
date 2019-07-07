package edu.pss.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    //方便别的类调用==》静态
    //创建C3P0连接池对象
    private static ComboPooledDataSource c3p0 = null;

    //私有化构造方法
    //为什么要私有化构造函数,实现单例模式
    private DBUtils() {
    }

    public DataSource getDataSource() {
        return c3p0;
    }

    //利用静态代码块加载驱动
    static {
        //利用静态代码块来读取C3P0的配置文件
        // 静态块 执行的优先级比较高  静态代码块只执行一次
        try {
            c3p0 = new ComboPooledDataSource();
        } catch (Exception e) {
            System.out.println("读取XML文件异常...");
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     */
//    写一个方法方便用户调用到连接
    public static Connection getConnection() {
        //声明Connection对象
        Connection conn = null;
        try {
            conn = c3p0.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //返回连接给调用者
        return conn;
    }

    /**
     * 根据条件查询列表
     *
     * @param sql
     * @param clazz
     * @param param
     * @return
     */
    public static <T> List<T> query(Connection conn, String sql, Class<T> clazz, Object... param) {

        try {
            QueryRunner qr = new QueryRunner();
            return qr.query(conn, sql, new BeanListHandler<T>(clazz), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    /**
     * 根据条件返回单个对象
     *
     * @param sql
     * @param clazz
     * @param param
     * @return
     */
    public static <T> T queryOne(Connection conn, String sql, Class<T> clazz, Object... param) {

        try {
            QueryRunner qr = new QueryRunner();
            return qr.query(conn, sql, new BeanHandler<T>(clazz), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Long queryScalarLong(Connection conn, String sql, Object... params) {
        Object obj = null;
        try {
            QueryRunner qr = new QueryRunner();
            obj = qr.query(conn, sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Long.parseLong(obj.toString());
    }

    /**
     * 新增 修改 删除
     *
     * @param sql
     * @param params
     * @throws Exception
     */
    public static Long update(Connection conn, String sql, Object... params) {

        int result = 0;

        try {
            QueryRunner qr = new QueryRunner();
            result = qr.update(conn, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Long(result);
    }

    /**
     * 关闭资源
     */
    public static void close(Connection con) {
        //关闭顺序==>rs<pstm<con
        //关闭预编译对象
        //关闭连接
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
