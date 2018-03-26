package comm_library.xmpp.message;


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
    /**
     * 发送字符串
     * @param content
     */
   protected abstract void send(String content);
    /**
     * 发送十六进制的字节数组
     * @param bs
     */
   protected abstract void sendHexByteArray(byte[] bs);

}
