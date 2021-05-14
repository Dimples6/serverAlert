package com.lianyu;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 服务器异常钉钉报警
 */
public class SelfRun {


    //钉钉群的webhook地址
    public static String WEBHOOK_TOKEN ="https://oapi.dingtalk.com/robot/send?access_token=333f944bbf6475965eda90f1df76d9106129e6e19fec1e92b325325893f28b22";
    Uiti uiti = new Uiti();
    public void selfWarning(){

        String serviceName =uiti.PropertisUiti();//获取配置文件里面的内容

        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");


        String msg = "("+serviceName+")监控服务器程序运行正常！";
        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"" + msg + "\"}}";


        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);


        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
