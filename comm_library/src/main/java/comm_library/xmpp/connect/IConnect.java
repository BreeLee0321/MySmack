package comm_library.xmpp.connect;


import comm_library.exception.SmackInvocationException;

/**
 * Created by bree on 2018/3/21.
 */
 public abstract class IConnect {
    abstract void connect(String host, int port, XMPPConnectListener connectListener) throws SmackInvocationException;
    abstract void disConnect();
    abstract boolean isConnected();
}
