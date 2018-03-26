package comm_library.utils;

import android.util.Log;


public class LogUtil {

    public static final int LEVEL_VERBOSE = 5;
    public static final int LEVEL_DEBUG = 4;
    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_WARN = 2;
    public static final int LEVEL_ERROR = 1;

    public static int level = LEVEL_VERBOSE;

    public static boolean enable = true;

    public static final String TAG = "xmpp library";

    public static void v(String msg){
        if (enable && level >= LEVEL_VERBOSE)
            Log.v(TAG,msg);
    }
    public static void d(String msg){
        if (enable && level >= LEVEL_DEBUG)
            Log.d(TAG,msg);
    }
    public static void i(String msg){
        if (enable && level >= LEVEL_INFO)
            Log.i(TAG,msg);
    }
    public static void w(String msg){
        if (enable && level >= LEVEL_WARN)
            Log.w(TAG,msg);
    }
    public static void e(String msg){
        if (enable && level >= LEVEL_ERROR)
            Log.e(TAG,msg);
    }
}
