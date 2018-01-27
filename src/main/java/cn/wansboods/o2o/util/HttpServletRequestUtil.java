package cn.wansboods.o2o.util;



public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request, String key ){
        try{
            return Integer.decode( request.getParameter( key ) );
        }catch ( Exception e ){
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key ){
        try{
            return Long.valueOf( request.getParameter( key ) );
        }catch ( Exception e ){
            return -1l;
        }
    }

    public static double getDouble(HttpServletRequest request, String key ){
        try{
            return Double.valueOf( request.getParameter( key ) );
        }catch ( Exception e ){
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key ){
        try{
            return Boolean.valueOf( request.getParameter( key ) );
        }catch ( Exception e ){
            return false;
        }
    }

}
