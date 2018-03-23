package com.example.xmpp_library.xmpp.message;

/**
 * Created by bree on 2018/3/22.
 */

public class ReceiveMessage  {
    private Receive receive;
    public ReceiveMessage(){
        receive=new ReceiveSmackMessage();
    }


    public void setOnReceiveMessageListener(ReceiveMessageListener listener) {
        receive.setOnReceiveMessageListener(listener);
    }
}
