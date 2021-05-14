package com.lianyu;



public class Warning {

    public static void main(String[] args) {
        Means forDemo = new Means();
        try {
            while (true) {
                forDemo.forDemo();
            }
        }catch (Exception e){
            System.out.println("1");
        }
    }

}
