package comm_library.xmpp.User;

import comm_library.xmpp.connect.XMPPConnection;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;

/**
 * Created by bree on 2018/3/22.
 */

public class UserSearchManager {
    private XMPPTCPConnection connection;
    public UserSearchManager(){
        connection= XMPPConnection.getInstance().getXmpptcpConnection();
    }

}
