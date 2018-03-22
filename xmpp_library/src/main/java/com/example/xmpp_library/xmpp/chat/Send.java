package com.example.xmpp_library.xmpp.chat;


/**
 * Created by bree on 2018/3/22.
 * 发送数据的扩展借口
 */

public abstract class Send {
    protected String from;
    protected String to;

    public Send(String from, String to){
        this.from=from;
        this.to=to;
        creatChat();

    }

    /**
     * 创建聊天
     */
   protected abstract void creatChat();
   protected abstract void send(String content);

}
