package com.example.xmpp_library.xmpp.chat;

import com.example.xmpp_library.utils.LogUtil;
import com.example.xmpp_library.xmpp.connect.XMPPConnection;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

/**
 * Created by bree on 2018/3/22.
 */

public class ReceiveSmackMessage extends Receive {
    private XMPPTCPConnection xmpptcpConnection;
    private XMPPConnection connection;
    protected ChatManager chatManager;
    public ReceiveSmackMessage(){
        //获取xmpp封装的连接对象
        connection= XMPPConnection.getInstance();
        //是否已经连接
        if (connection.isConnected()) {
            //获取xmpptcp连接对象
            xmpptcpConnection = connection.getXmpptcpConnection();
            //创建聊天管理对象
        }else {
            LogUtil.e("xmpp not connected");
            return;
        }

        chatManager = ChatManager.getInstanceFor(xmpptcpConnection);
    }
    @Override
    public void setOnReceiveMessageListener(final ReceiveMessageListener listener) {
            chatManager.addChatListener(new ChatManagerListener() {
                @Override
                public void chatCreated(Chat chat, boolean createdLocally) {
                    chat.addMessageListener(new ChatMessageListener() {
                        @Override
                        public void processMessage(Chat chat, Message message) {
                            listener.onReceive(message);
                        }
                    });
                }
            });
    }
}
