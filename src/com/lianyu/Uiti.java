package com.lianyu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Uiti {

    public Uiti() {
    }

    public String PropertisUiti(){
        String service;
        String message=null;

        Properties properties = new Properties();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new
                    FileInputStream("C:/src/service.properties"),"utf-8"));
            while ((service=br.readLine()) != null) {
                message = service.replace("serviceName:","");
                System.out.println(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }



}
