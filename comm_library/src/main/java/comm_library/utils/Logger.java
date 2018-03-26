package comm_library.utils;

import android.text.TextUtils;


public class Logger {

    private final String prefix;
    private final String suffix;
    public Logger(String prefix){
        this(prefix,null);
    }
    public Logger(String prefix,String suffix) {
        this.prefix = TextUtils.isEmpty(prefix) ? "" : prefix + " >>:\n\t";
        this.suffix = TextUtils.isEmpty(suffix) ? "" : "    :<< " + suffix;

    }

    public void v(String msg){
            LogUtil.v(prefix + msg + suffix);
    }
    public void d(String msg){
        LogUtil.d(prefix + msg + suffix);
    }
    public void i(String msg){
        LogUtil.i(prefix + msg + suffix);
    }
    public void w(String msg){
        LogUtil.w(prefix + msg + suffix);
    }
    public void e(String msg){
        LogUtil.e(prefix + msg + suffix);
    }

}
