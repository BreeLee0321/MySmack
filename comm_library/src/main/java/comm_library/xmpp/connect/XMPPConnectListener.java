package comm_library.xmpp.connect;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;

/**
 * Created by bree on 2018/3/21.
 */

public interface XMPPConnectListener {
    void onConnecting();
    void onConnected(XMPPTCPConnection xmppConnect);
    void onDisConnected();
    void onNetworkDisconnected();
}
