package com.lianyu;

import java.io.File;

/**
 * 获取磁盘C
 */
public class DiskC {

    public long monitoring(){
        File file = new File("c:");
        long totalSpace = file.getTotalSpace();//总空间大小
        long freeSpace = file.getFreeSpace();//剩余空间大小
        long usedSpace = totalSpace - freeSpace;//已用空间大小
        long size = freeSpace / 1024 / 1024 / 1024;
        return size;
    }
}
