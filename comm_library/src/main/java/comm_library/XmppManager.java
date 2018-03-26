package comm_library;


import android.content.Context;


import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import comm_library.exception.SmackInvocationException;
import comm_library.xmpp.connect.XMPPConnectListener;
import comm_library.xmpp.connect.XMPPConnection;
import comm_library.xmpp.message.ReceiveMessage;
import comm_library.xmpp.message.ReceiveMessageListener;
import comm_library.xmpp.message.SendMessage;

/**
 * Created by bree on 2018/3/21.
 * 对外借口类
 */

public class XmppManager {
    private static XmppManager instance;
    /**
     * 管理连接类
     */
    private XMPPConnection connection;
    /**
     * 发送消息管理类
     */
    private SendMessage sendManager;
    /**
     * 接收消息管理类
     */
    private ReceiveMessage receiveMessage;

    private Context context;



    private XmppManager() {
        connection= XMPPConnection.getInstance();

    }

    public static synchronized XmppManager getInstance() {
        if (instance == null) {
            instance = new XmppManager();
        }

        return instance;
    }

    public  void init(Context context) {
        this.context=context;
        //开启服务
//        context.startService(new Intent(context,XmppService.class));
        connection=XMPPConnection.getInstance();

    }
    /**
     * 连接服
     * @param host
     * @param port
     * @param listener
     */
    public void connect(String host, int port, XMPPConnectListener listener) throws SmackInvocationException {
        connection.connect(host,port,listener);
    }

    /**
     * 注册
     * @param user
     * @param psd
     */
    public  void singup(String user, String psd) throws SmackException.NotConnectedException, XMPPException.XMPPErrorException, SmackException.NoResponseException {
        connection.signup(user,psd);
    }

    /**
     * 登陆
     * @param user
     * @param psd
     */
    public  void login( String user, String psd) throws SmackInvocationException {
        connection.login(user,psd);

    }

    /**
     * 发送字符串
     * @param from
     * @param to
     * @param content
     */
    public void send(String from,String to,String content){
        sendManager=new SendMessage(from,to);
        sendManager.send(content);
    }

    /**
     * 发送字节数组
     * @param from
     * @param to
     * @param content
     */
    public void sendHexByteArray(String from,String to,byte[] content){
        sendManager=new SendMessage(from,to);
        sendManager.sendHexByteArray(content);
    }
    /**
     * 接受消息监听
     * @param listener
     */
    public void setOnMessageReceiveListener(ReceiveMessageListener listener){
        receiveMessage=new ReceiveMessage();
        receiveMessage.setOnReceiveMessageListener(listener);
    }

}
