package com.example.bree.mysmack;

/**
 * Created by bree on 2018/3/23.
 */

public class Data {
    //心跳包
    public static byte[] bag={(byte)0xA5,(byte)0x00,(byte)0x00};

    private static byte[] get(byte[] bytes){

        for (int i=0;i<bytes.length;i++){
            int i1 = bytes[i] + bytes[i + 1];
        }
        return null;
    }

}
