package com.example.xmpp_library.xmpp.User;

import com.example.xmpp_library.utils.LogUtil;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;

/**
 * Created by bree on 2018/3/22.
 */

public class UserListenrt implements StanzaListener {
    @Override
    public void processPacket(Stanza packet) throws SmackException.NotConnectedException {
        if (packet instanceof Presence) {
            Presence presence = (Presence) packet;
            String fromId = presence.getFrom();
            String from = presence.getFrom().split("@")[0];//我这里只为了打印去掉了后缀
            if (presence.getType().equals(Presence.Type.subscribe)) {
                LogUtil.d("yangbinnew请求添加好友" + from);
            } else if (presence.getType().equals(Presence.Type.subscribed)) {//对方同意订阅
                LogUtil.d("yangbinnew同意订阅" + from);
            } else if (presence.getType().equals(Presence.Type.unsubscribe)) {//取消订阅
                LogUtil.d("yangbinnew取消订阅" + from);
            } else if (presence.getType().equals(Presence.Type.unsubscribed)) {//拒绝订阅
                LogUtil.d("yangbinnew拒绝订阅" + from);
            } else if (presence.getType().equals(Presence.Type.unavailable)) {//离线
                LogUtil.d("yangbinnew离线" + from);
            } else if (presence.getType().equals(Presence.Type.available)) {//上线
                LogUtil.d("yangbinnew上线" + from);
            }
        }
    }
}
