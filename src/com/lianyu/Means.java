package com.lianyu;



import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Means {

    public int a = 0;

    private int b =0;

    ForWarning forWarning = new ForWarning();

    Recover recover = new Recover();

    Refuse refuse = new Refuse();

    Self self =new Self();

    DiskC diskC = new DiskC();

    DiskF diskF = new DiskF();

    DiskForWarning diskForWarning = new DiskForWarning();

    Logger logger = Logger.getLogger(this.getClass());

    public void forDemo() throws InterruptedException {

        Thread.sleep(300000);

        /**
         * 监控服务器C盘的磁盘空间
         */
        long monitoringC = diskC.monitoring();//获取到C盘所剩的空间
        if(monitoringC < 3){
            diskForWarning.warning("C");
            logger.warn("服务器C磁盘空间少于3G");
        }
        /**
         * 监控服务器F盘的磁盘空间
         */
        long monitoringF = diskF.monitoring();//获取到F盘所剩的空间
        if(monitoringF < 3){
            diskForWarning.warning("F");
            logger.warn("服务器D磁盘空间少于3G");
        }

        b++;
        if(b == 24){//当b等于2个小时的时候
            self.bist();//走发送一条钉钉的消息
            b=0;
        }

        try {
            CloseableHttpClient closeableHttpClient= HttpClients.createDefault();//1、创建实例
            HttpGet httpGet=new HttpGet("http://yun.fjzx.gov.cn/");//2、创建请求

            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0");

            CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet); //3、执行
            HttpEntity httpEntity=closeableHttpResponse.getEntity(); //4、获取实体

            int number = closeableHttpResponse.getStatusLine().getStatusCode();//获取到请求页面时的StatusCode值
            if(number != 200){
                logger.warn("服务器出现异常，请求页面报送："+number);
                Thread.sleep(10000);//休眠10秒后再走方法
                two();
            }
            if(a > 0 && number == 200){//服务器出现异常，处理完正常后走这里
                logger.warn("服务器恢复正常！");
                recover.normal();//调用钉钉发送服务器回复正常的信息
                a = 0;
            }

            if(number == 200){
                logger.warn("服务器正常运行！");
            }

        }catch (Exception e) {//拒绝连接时走这边
            logger.warn("服务器请求链接被拒绝！");
            Thread.sleep(10000);
            two();
            e.printStackTrace();
        }
    }

    public void two(){

        try {
            CloseableHttpClient closeableHttpClient= HttpClients.createDefault();//1、创建实例
            HttpGet httpGet=new HttpGet("http://yun.fjzx.gov.cn/");//2、创建请求

            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0");

            CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet); //3、执行
            HttpEntity httpEntity=closeableHttpResponse.getEntity(); //4、获取实体

            int number = closeableHttpResponse.getStatusLine().getStatusCode();//获取到请求页面时的StatusCode值

            if(number != 200){
                logger.warn("经过10秒再次请求，服务器依旧异常，请求页面报送："+number+"发送钉钉消息！");
                forWarning.warning(number);//调用发送钉钉的消息页面
                a++;
            }

        }catch (Exception e){
            logger.warn("经常10秒再次请求服务器链接依旧被拒绝，发送钉钉消息！");
            refuse.refuse();//调用钉钉发送请求被拒绝的信息
            a++;
            e.printStackTrace();
        }
    }

}
