package cn.wansboods.o2o.util;

public class PathUtil {
    private static String seperator = System.getProperty( "file.separator" );

    /**
     *  项目图片的根路径
     * @return
     */
    public static String getImgBasePath(){
        String os = System.getProperty( "os.name");
        String basePath="";
        if( os.toLowerCase().startsWith( "win" ) ){
            basePath = "E:/prokectdev/image/";
        }else{
            basePath = "/home/wasnboods/image/";
        }

        basePath=basePath.replace( "/", seperator );

        return basePath;
    }

    /**
     * 项目图片的子路径
     * @param shopId
     * @return
     */
    public static String getShopImagePath( long shopId ){
        String imagePath = "/upload/item/shop" + shopId + "/";
        return imagePath.replace( "/", seperator );
    }
}
