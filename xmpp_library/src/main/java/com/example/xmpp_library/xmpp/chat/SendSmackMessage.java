package com.example.xmpp_library.xmpp.chat;

import com.example.xmpp_library.utils.LogUtil;
import com.example.xmpp_library.xmpp.connect.XMPPConnection;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

/**
 * Created by bree on 2018/3/22.
 * Smack发送抽象借口
 */

public class SendSmackMessage extends Send {

    private XMPPTCPConnection xmpptcpConnection;
    private XMPPConnection connection;
    protected ChatManager  chatManager;
    protected Chat chat;
    protected Message message;

    public SendSmackMessage(String from, String to) {
        super(from, to);
    }

    @Override
    protected void creatChat() {
        //获取xmpp封装的连接对象
        connection= XMPPConnection.getInstance();
        //是否已经连接
        if (connection.isConnected()) {
            //获取xmpptcp连接对象
            xmpptcpConnection = connection.getXmpptcpConnection();
            //创建聊天管理对象
            chatManager= ChatManager.getInstanceFor(xmpptcpConnection);
        }else {
            LogUtil.e("xmpp not connected");
            return;
        }
        chat = chatManager.createChat(to);
        LogUtil.v("creat chat to "+to);
        initMessage();
    }

    @Override
    protected void send(String content) {
        try {
            //构建msg
            message.setType(Message.Type.chat);
            message.setBody(content);
            //发送msg
            chat.sendMessage(message);
            LogUtil.v("send message to  " +to +"/n"+message.toString());
        } catch (SmackException.NotConnectedException e) {
            LogUtil.v("send message to" +to);
            e.printStackTrace();
        }
    }

    private Message initMessage(){
        message=new Message();
        message.setTo(to);
        message.setFrom(from);
        return message;
    }

}
