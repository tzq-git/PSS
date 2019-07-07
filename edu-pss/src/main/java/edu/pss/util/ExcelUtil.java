package edu.pss.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelUtil {

//    public static void main(String[] args) {
//        OutBoundDao outBoundDao = new OutBoundDaoImpl();
//        List<OutBound> list = outBoundDao.list();
//        try {
//            toWrite("user", list, OutBound.class.getName());
//            toRead("user.xlsx");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public  static<T>  XSSFWorkbook  toWrite(String[] tableName, List<T> list) throws Exception{
        //在内存中创建一个Excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();

        //给Excel文件创建一张 Sheet1表
        Sheet sheet = workbook.createSheet();   //table --》下面的Sheet1名

        //创建表头
        Row row = sheet.createRow(0);
        for(int j = 0; j < tableName.length; j++){
            Cell cell = row.createCell(j);
            cell.setCellValue(tableName[j]);
        }
        //循环创建多行
        for (int i = 0; i < list.size(); i++){
            T t = list.get(i);//每次获取集合中的对象

            row = sheet.createRow(i+1);
            Field[] fields = t.getClass().getDeclaredFields();//获取该次对象所有的字段==》数组
            for (int j = 0; j<fields.length-1; j++) {

                Cell cell = row.createCell(j);
                //遍历这个字段数组
                //要访问私有的字段  就要设置强制访问为true
                fields[j].setAccessible(true);
                //获取该字段对象的字段名
//                fields[i].getName();//字段名
                Object obj= fields[j].get(t);//字段值(该对象的实例)

//                System.out.println("字段名为：" + fields[j].getName() + ",字段类型为：" + fields[j].getType() + ", 字段值为：" + fields[j].get(t));
                if ( obj!=null){
                    cell.setCellValue(obj.toString());
                } else {
                    cell.setCellValue("");
                }
            }
        }

        //文件输出流--》把内存中的excel文件写到磁盘中
        /*FileOutputStream fos = new FileOutputStream(filename + ".xlsx");
        workbook.write(fos);
        fos.flush();
        fos.close();*/

        //返回Excel文件
        return workbook;
    }

    public static List<Object> toRead(String filename) throws Exception{
        //拿到文件
        Workbook workbook = WorkbookFactory.create(new File(filename));
        Sheet sheet = workbook.getSheetAt(0);//读取第一张表
        //获取到总行数 -->最后一行就是总行数
        int lastRowNum = sheet.getLastRowNum();
        for(int i = 0; i < lastRowNum; i++){
            Row row = sheet.getRow(i); //拿每一行
            int lastCellNum = row.getLastCellNum();//拿到对应行的总列数
            for(int j = 0; j < lastCellNum; j++){
                Cell cell = row.getCell(j);//循环获取一行中的每一个单元格
                System.out.print(cell.getStringCellValue() + " ---> ");
            }
            System.out.println();
        }
        return null;
    }
}
