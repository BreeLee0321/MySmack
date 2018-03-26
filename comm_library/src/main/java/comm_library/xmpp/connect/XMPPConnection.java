package comm_library.xmpp.connect;


import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.ping.PingFailedListener;
import org.jivesoftware.smackx.ping.PingManager;

import java.util.Date;

import comm_library.constant.Conf;
import comm_library.constant.State;
import comm_library.exception.SmackInvocationException;
import comm_library.utils.LogUtil;


/**
 * Created by bree on 2018/3/21.
 */

public class XMPPConnection extends IConnect {
    private static XMPPConnection xmppConnect;

    private XMPPConnectListener xmppConnectListener;

    //连接对象
    private  XMPPTCPConnection xmpptcpConnection;
    private State state;
    private PingManager pingManager;

    private long lastPing = new Date().getTime();

    private XMPPConnection(){}

    public static synchronized XMPPConnection getInstance() {
        if (xmppConnect == null) {
            xmppConnect = new XMPPConnection();
        }
            return xmppConnect;
    }

    /**
     * 连接
     * @param host
     * @param port
     * @param connectListener
     * @throws SmackInvocationException
     */
    @Override
    public void connect(String host, int port, XMPPConnectListener connectListener) throws SmackInvocationException {
            this.xmppConnectListener=connectListener;
        if (!isConnected()) {
            setConnectState(State.CONNECTING);
            if (xmpptcpConnection== null) {
                xmpptcpConnection= createConnection(host,port);
            }

            try {
                xmpptcpConnection.connect();
                setConnectState(State.CONNECTED);
            } catch(Exception e) {
                throw new SmackInvocationException(e);

            }
        }
    }

    /**
     * 断开连接
     */
    @Override
    public void disConnect() {
        //断开连接
        if (isConnected()) {
            xmpptcpConnection.disconnect();
            setConnectState(State.DISCONNECTED);
            xmpptcpConnection=null;

        }
    }

    /**
     * 片段是否连接
     * @return
     */
    @Override
    public boolean isConnected() {
        return xmpptcpConnection!= null && xmpptcpConnection.isConnected();
    }

    /**
     * 创建XMPP连接对象
     * @return
     * @param host
     * @param port
     */
    private XMPPTCPConnection createConnection(String host, int port) {
        SmackConfiguration.DEBUG = true;
        XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();

        try {
            //设置openfire主机IP
            config.setHost(host);
            //设置openfire服务器名称
            config.setServiceName(host);
            //设置端口号：默认5222
            config.setPort(port);
            //禁用SSL连接
            config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled).setCompressionEnabled(false);
            //设置Debug
            config.setDebuggerEnabled(Conf.OPENFIRE_DEBUG);
            //设置在线状态
            config.setSendPresence(true);
            //设置开启压缩，可以节省流量
            config.setCompressionEnabled(true);
            //不需要经过同意添加好友
            Roster.setDefaultSubscriptionMode(Roster.SubscriptionMode.accept_all);

        } catch (Exception e) {
            e.printStackTrace(
            );
        }
        return new XMPPTCPConnection(config.build());
    }

    /**
     * 登陆
     * @param username
     * @param password
     * @throws SmackInvocationException
     */
    public void login(String username,String password) throws SmackInvocationException {
        try {
            if (!xmpptcpConnection.isAuthenticated()) {
                LogUtil.i(username+ "@"+password+" login start");
                xmpptcpConnection.login(username, password);
            }else
            onConnectionEstablished();
            LogUtil.i(username+ "@"+password+" login Success");
        } catch(Exception e) {
            SmackInvocationException exception = new SmackInvocationException(e);
            // this is caused by wrong username/password, do not reconnect
            if (exception.isCausedBySASLError()) {
                disConnect();
                LogUtil.i(username+ "@"+password+" login faied ");
            }
            throw exception;
        }
    }

    /**
     * 注册
     * @param username
     * @param password
     * @throws SmackException.NotConnectedException
     * @throws XMPPException.XMPPErrorException
     * @throws SmackException.NoResponseException
     */
    public void signup(String username, String password) throws SmackException.NotConnectedException, XMPPException.XMPPErrorException, SmackException.NoResponseException {
        LogUtil.i(username+ "@"+password+" signup start ");
        AccountManager.getInstance(xmpptcpConnection).createAccount(username, password);
        LogUtil.i(username+ "@"+password+" signup Sucess ");

    }
    /**
     * 登陆完成
     */
    private void onConnectionEstablished() {
        if (getState() != State.CONNECTED) {
            //processOfflineMessages();

            try {
                xmpptcpConnection.sendPacket(new Presence(Presence.Type.available));
            } catch (Exception e) {
                e.printStackTrace();
            }

            OutgoingFileTransfer.setResponseTimeout(30000);

            pingManager = PingManager.getInstanceFor(xmpptcpConnection);
            pingManager.registerPingFailedListener(new PingFailedListener() {
                @Override
                public void pingFailed() {
                    // Note: remember that maybeStartReconnect is called from a different thread (the PingTask) here, it may causes synchronization problems
                    long now = new Date().getTime();
                    if (now - lastPing > 30000) {
                        startReconnectIfNecessary();
                        lastPing = now;
                    } else {
                    }
                }
            });

        }
    }

    private void startReconnectIfNecessary() {
        disConnect();
        setConnectState(State.WAITING_TO_CONNECT);
        setConnectState(State.WAITING_FOR_NETWORK);
    }

    /**
     * 设置连接状态
     * @param state
     */
    private void setConnectState(State state){
        LogUtil.d("xmpp connect state "+state);
        if (xmppConnectListener!=null){
            switch (state){
                case CONNECTING:
                    xmppConnectListener.onConnecting();
                    break;
                case CONNECTED:
                    xmppConnectListener.onConnected(xmpptcpConnection);
                    break;
                case DISCONNECTED:
                    xmppConnectListener.onDisConnected();
                    break;
                case WAITING_TO_CONNECT:
                    break;
                case WAITING_FOR_NETWORK:
                    xmppConnectListener.onNetworkDisconnected();
                    break;
            }
            this.state=state;
        }
    }

    /**
     * 获取当前连接状态
     * @return
     */
    private State getState(){
        return this.state;
    }

    /**
     * 获取连接对象
     * @return
     */
    public XMPPTCPConnection getXmpptcpConnection(){
        if (xmpptcpConnection!=null)
            return xmpptcpConnection;
        return null;
    }


}
