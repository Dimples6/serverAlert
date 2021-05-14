package com.lianyu;

/**
 * 程序自检
 */
public class Self {

    SelfRun selfRun = new SelfRun();


    public void bist()  {
        selfRun.selfWarning();//调用发送钉钉
    }
}
