package cn.edu.zut.hw.hanzhaolong;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HBaseUtil {
    private static Connection conn;
    private static Admin admin;
    private static Configuration conf;


    //获取连接
    public static Connection getConnection(){
        return conn;
    }

    //获取admin
    public static Admin getAdmin() throws Exception{
        admin = conn.getAdmin();
        return admin;
    }
    //获取连接信息
    public static void setConnection(String zookeeperQuorum) throws Exception{
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum",zookeeperQuorum);
        conn = ConnectionFactory.createConnection(conf);
    }

    //创建表
    public static boolean createTable(String tableName,String[] fms) throws Exception{
        boolean temp;
        getAdmin();
        HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tableName));
        if(admin.tableExists(TableName.valueOf(tableName))){
            System.out.println("表" + tableName + "已存在");
            temp = false;
        }else{
            for (String fm : fms){
                descriptor.addFamily(new HColumnDescriptor(fm));
            }
            temp = true;
        }
        admin.createTable(descriptor);
        return temp;

    }
    //删除表
    public static boolean deleteTable(String tableName) throws Exception{
       //禁用表
        admin.disableTable(TableName.valueOf(tableName));
        //删除表
        admin.deleteTable(TableName.valueOf(tableName));

        return true;
    }

    //查询表
    public static String[] listTableName() throws Exception{
        TableName[] table;
        table = admin.listTableNames();
        String[] ts = new String[table.length];
        for (int i = 0; i < table.length; i++) {
            ts[i] = table[i].toString();
            System.out.printf(ts[i]);
        }
        return ts;
    }
    //插入单条数据
    public static boolean insert(String tableName,SampleCell cell)throws Exception{
        //设置Put对象
        Put put = new Put(Bytes.toBytes(cell.getRowkey()));
        //添加列族名、列名以及数据
        put.addColumn(Bytes.toBytes(cell.getFamilyName()), Bytes.toBytes(cell.getColName()), Bytes.toBytes(cell.getData()));
        Table table = conn.getTable(TableName.valueOf(tableName));
        table.put(put);
        return true;
    }

    //批量插入数据
    public static boolean insert(String tableName, List<SampleCell> cells)throws Exception{
        //Put对象
        List<Put> puts = new ArrayList<>();
        //遍历把对象中的数据添加到Put对象里
        for (SampleCell cell:cells) {
            Put put = new Put(Bytes.toBytes(cell.getRowkey()));
            put.addColumn(Bytes.toBytes(cell.getFamilyName()), Bytes.toBytes(cell.getColName()), Bytes.toBytes(cell.getData()));
            puts.add(put);
        }
        Table table = conn.getTable(TableName.valueOf(tableName));
        table.put(puts);


        return true;
    }

    //更新数据
    public static boolean update(String tableName,SampleCell cell) throws IOException {
        //Put对象
        Put put = new Put(Bytes.toBytes(cell.getRowkey()));
        put.addColumn(Bytes.toBytes(cell.getFamilyName()), Bytes.toBytes(cell.getColName()), Bytes.toBytes(cell.getData()));
        Table table = conn.getTable(TableName.valueOf(tableName));
        table.put(put);
        return true;
    }

    //删除数据
    public static boolean delete(String tableName,SampleCell cell) throws Exception{
        Table table = conn.getTable(TableName.valueOf(tableName));
        //获取Delete对象
        Delete delete = new Delete(Bytes.toBytes(cell.getRowkey()));
        delete.addColumn(Bytes.toBytes(cell.getFamilyName()), Bytes.toBytes(cell.getColName()));
        table.delete(delete);
        return true;
    }

    //查找全部数据
    public static List<SampleCell> listAll(String tableName) throws Exception {
        //创建List集合
        List<SampleCell> list = new ArrayList<SampleCell>();
        Table table = conn.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner rs = table.getScanner(scan);
        for (Result r:rs) {
            //遍历Cell对象
            for (Cell cell:r.rawCells()) {
                SampleCell sampleCell = new SampleCell();
                sampleCell.setFamilyName(Bytes.toString(CellUtil.cloneFamily(cell)));
                sampleCell.setColName(Bytes.toString(CellUtil.cloneQualifier(cell)));
                sampleCell.setRowkey(Bytes.toString(CellUtil.cloneRow(cell)));
                sampleCell.setData(Bytes.toString(CellUtil.cloneValue(cell)));
                list.add(sampleCell);
            }
        }
        return list;
    }
    //返回数据
    public static List<SampleCell> queryByRowKey(String tableName,String rowKey) throws Exception {
        List<SampleCell> list = new ArrayList<SampleCell>();
        Table table = conn.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        for (Cell cell:result.rawCells()){
            SampleCell sampleCell = new SampleCell();
            sampleCell.setFamilyName(Bytes.toString(CellUtil.cloneFamily(cell)));
            sampleCell.setColName(Bytes.toString(CellUtil.cloneQualifier(cell)));
            sampleCell.setRowkey(Bytes.toString(CellUtil.cloneRow(cell)));
            sampleCell.setData(Bytes.toString(CellUtil.cloneValue(cell)));
            list.add(sampleCell);
        }
        return list;
    }



    public static void main(String[] args) throws Exception {
        //获取连接信息
        setConnection("node01:2181,node02:2181,node03:2181");
        //创建表
        createTable("stu", new String[]{"education","info"});
        //查询表
        listTableName();

            //单条插入

        SampleCell sampleCell = new SampleCell();
        sampleCell.setColName("name");
        sampleCell.setData("hahaha");
        sampleCell.setFamilyName("info");
        sampleCell.setRowkey("001");
        insert("stu",sampleCell);
        //多条插入
        ArrayList<SampleCell> list = new ArrayList<>();
        SampleCell sampleCell1 = new SampleCell();
        sampleCell1.setColName("name");
        sampleCell1.setData("xixixi");
        sampleCell1.setFamilyName("info");
        sampleCell1.setRowkey("002");
        SampleCell sampleCell2 = new SampleCell();
        sampleCell2.setColName("name");
        sampleCell2.setData("yoyoyo");
        sampleCell2.setFamilyName("info");
        sampleCell2.setRowkey("003");
        list.add(sampleCell1);
        list.add(sampleCell2);
        insert("stu",list);
        //查询全部
        List<SampleCell> list1 = new ArrayList<>();
        list1 = HBaseUtil.listAll("stu");
        for (SampleCell c:list1
             ) {
            System.out.println(c);
        }
        //按rowkey查询
        List<SampleCell> list2 = new ArrayList<>();
        list2 = queryByRowKey("stu","001");
        System.out.println(list2);
        }
        //删除表
        //deleteTable("student");


    }
