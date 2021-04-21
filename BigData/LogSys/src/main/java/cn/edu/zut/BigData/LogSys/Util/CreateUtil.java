package cn.edu.zut.BigData.LogSys.Util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateUtil {
    private static int[] ips = new int[20];
    private static SimpleDateFormat format;
    private static String[] urls;
    private static String[] ms;//请求方式
    private static int[] status;//状态码
    private static String[] referrers;//访问来源
    static{
        for (int i = 0; i < ips.length; i++) {
            ips[i] = getIntRandom(0,256);
        }
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        urls = "other,shop,category".split(",");
        ms = "GET,POST".split(",");
        status = new int[]{200,200, 404,200, 200,200,500,200,200,200};
        referrers = new String[]{"百度", "搜狗", "必应", "谷歌", "360","-"};
    }
    public static String createLineLog(){
        return getIp()+" - - "+getUID()+" ["+getCurrentTime()+" +0800] "+getURL()+" "+getReferrer()+" "+getStatus();
    }

    //创建IP地址
    private static String getIp(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<4;i++){
            sb.append(ips[getIntRandom(0,ips.length)]);
            sb.append(".");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    //创建用户ID
    private static String getUID(){
        return "U"+fillZero(getIntRandom(0,100000),8);
    }

    //创建时间字段
    private static String getCurrentTime(){
        return format.format(new Date());
    }

    //创建URl
    private static String getURL(){
        return "\""+ms[getIntRandom(0,100)%ms.length] + " " + urls[getIntRandom(0,urls.length)]+'/'+getIntRandom(10,1000)+".html HTTP/1.1\"";
    }
    //创建状态码
    private static String getStatus(){
        return ""+status[getIntRandom(0,1000)%status.length];
    }
    //创建访问来源
    private static String getReferrer(){
        return referrers[getIntRandom(0,referrers.length)];
    }


    private static String fillZero(Object o,int length){
        String str = o.toString();
        for(int i = 0;i<length - o.toString().length();i++){
            str = "0"+str;
        }
        return str;
    }

    private static int getIntRandom(int start,int end){
        return (int)(Math.random()*(end-start))+start;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/opt/apps/logsys/data/tmp.log");
        //System.out.println(createLineLog());
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        while (true){
            //如果日志文件过大，则重命名日志文件，创建新文件
            if(file.length() > 1024*1024*500){
                file.renameTo(new File(file.getParent()+"/"+System.currentTimeMillis()+"-tmp.log"));
                //创建新文件
                file.createNewFile();
            }
            long begin = System.currentTimeMillis();
            bw.write(createLineLog());
            bw.newLine();
            bw.flush();
            Thread.sleep(getIntRandom(10,2000));
        }
    }
}
