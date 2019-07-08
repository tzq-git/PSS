package cn.test.test;

import edu.pss.bean.Authority;
import edu.pss.bean.OutBound;
import edu.pss.bean.SaleReturn;
import edu.pss.bean.User;
import edu.pss.dao.impl.OutBoundDaoImpl;
import edu.pss.dao.impl.SaleReturnDaoImpl;
import edu.pss.dao.OutBoundDao;
import edu.pss.dao.SaleReturnDao;
import edu.pss.service.AuthorityService;
import edu.pss.service.impl.AuthorityServiceImpl;
import edu.pss.service.impl.UserServiceImpl;
import edu.pss.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ABCTest {

    @Test
    public void test() {
        String dealDateStr = "2018-01-07 11:11:11";
        Date dealDate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dealDate = formatter.parse(dealDateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(dealDate);
    }

    @Test
    public void test2() {

        OutBoundDao outBoundDao = new OutBoundDaoImpl();
        OutBound outBound = null;
        for(int i=0;i<20;i++){
            outBound = new OutBound();
            outBound.setOrderId(Long.parseLong("11111111")+i);
            outBound.setcName("张三");
            outBound.setDealDate(new Date());
            outBound.setOutDate(new Date());
            outBound.setAddress("上下店路15号");
            outBound.setPrice(1000000d);
            outBoundDao.insert(outBound);
        }
    }

    @Test
    public void test3(){
        SaleReturnDao saleReturnDao = new SaleReturnDaoImpl();
        SaleReturn saleReturn = null;
        for(int i=0;i<20;i++){
            saleReturn = new SaleReturn();
            saleReturn.setReturnId(Long.parseLong("22222222")+i);
            saleReturn.setOrderId(Long.parseLong("11111111")+i);
            saleReturn.setcName("张三");
            saleReturn.setPrice("1000000");
            saleReturn.setSalesMan("哈哈");
            saleReturn.setReturnDate(new Date());
            saleReturnDao.insert(saleReturn);

        }
    }

    @Test
    public void test4(){
        User user = null;
        Authority authority = null;

        UserService userService = new UserServiceImpl();
        AuthorityService authorityService = new AuthorityServiceImpl();
        user = new User();
        user.setUserId(1L);
        user.setUserName("admin");
        String enty = DigestUtils.md5Hex("admin");
        user.setUserPwd(enty);
        user.setType(0L);
        user.setTelPhone("66666666");
        user.setEmail("666666@163.com");
        userService.update(user) ;

        /*for(int i=0;i<10;i++){
            user = new User();
            authority = new Authority();

            user.setUserName("user"+i);
            String enty = DigestUtils.md5Hex("123456");
            user.setUserPwd(enty);
            user.setType(1L);
            user.setTelPhone("1514567890"+i);
            user.setEmail("111111@163.com");

            Long id = userService.insert(user);

            System.out.println(id+"userId");

            authority.setUserId(id);
            authority.setUserName("user"+i);
            authorityService.insert(authority);
        }*/
    }

    @Test
    public void FormatDate(){
        String time = "2018-11-11 11:11:11";
        Date date = new Date();
        System.out.println(date);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            time = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(time);
    }

    @Test
    public void test5(){
        UserService userService = new UserServiceImpl();
        AuthorityService authorityService = new AuthorityServiceImpl();
        List<User> list = userService.list();
        for(User user:list){
            Authority authority = authorityService.loadById(user.getUserId());
            if ( authority==null ){
                authority = new Authority();
                authority.setUserId(user.getUserId());
                authority.setUserName(user.getUserName());
                authorityService.insert(authority);
            }
        }
    }

    @Test
    public void Test6(){
        String enty = DigestUtils.md5Hex("123456");
        String enty1 = DigestUtils.md5Hex("admin");
        System.out.println(enty);
        System.out.println(enty1);
    }

    /*select * from test
    where date_format(create_time,'%Y-%m-%d') between '2018-07-30' and '2018-07-31';*/
}
