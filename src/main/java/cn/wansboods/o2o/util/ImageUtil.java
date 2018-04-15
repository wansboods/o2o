package cn.wansboods.o2o.util;

import cn.wansboods.o2o.web.superadmin.AreaController;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat( "yyyyMMddHHmmss");
    private static final Random r = new Random();

    /**
     * 生成随机文件名 当前年月日小时分钟秒 + 五位随机数
     * @return
     */
    public static String getRandomFileName() {
        //获取随机的五位数
        int rannum = r.nextInt(89999 ) + 10000;
        String nowTimeStr = sDateFormat.format( new Date() );
        return nowTimeStr + rannum;
    }

    /**
     *  获取输入的文件流的扩展名
     * @param fileName
     * @return
     */
    public static String getFileExtension( String fileName ) {
        String suffix = null;
        try {
            suffix  = fileName.substring( fileName.lastIndexOf(".") );
        }catch ( Exception e ){
            return ".jpg";
        }

        return suffix;
    }

    /**
     * 创建生成目录
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File( realFileParentPath );
        if( !dirPath.exists() ){
            dirPath.mkdirs();
        }
    }

    /**
     * 保存图片地址
     * @param thumbnailInputStream
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(InputStream thumbnailInputStream, String fileName, String targetAddr ){

        String realFileName = getRandomFileName(); //随机名
        String extension = getFileExtension( fileName ); //扩展名
        System.out.print( targetAddr );
        System.out.print( PathUtil.getImgBasePath() );
        makeDirPath( targetAddr );

        String relatibeAddr = targetAddr + realFileName + extension;
        System.out.print( relatibeAddr );
        System.out.print( PathUtil.getImgBasePath() );

        File dest = new File( PathUtil.getImgBasePath() + relatibeAddr );
        try {
            Thumbnails.of(thumbnailInputStream)
                    .size( 200,200 )
                    .watermark( Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File( basePath + "\\jpg\\jiaziyingxiang.png")), 0.5f)
                    .outputQuality(0.8)
                    .toFile( dest );
        }catch ( IOException e ){
            e.printStackTrace();
        }

        return relatibeAddr;
    }

    /**
     * storePath 是文件路径还是目录路径
     * 如果是文件路径删除文件
     * 如果是目录删除目录下所有文件
     * @param storePath
     */
    public static void deleteFileOrPath( String storePath ){
        File fileOrPath = new File( PathUtil.getImgBasePath() + storePath );
        if(fileOrPath.exists()){
            if( fileOrPath.isDirectory() ){
                File files[] = fileOrPath.listFiles();
                for( int i = 0; i < files.length; i ++ ){
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }


    }

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger( ImageUtil.class );
        //classpath 绝对路径
//        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        logger.info( "图片的地址名称{}", basePath );
        try {
            Thumbnails.of( new File("E:\\img\\testImg.jpg") )
                    .size(800, 600 )
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File( basePath + "\\jpg\\jiaziyingxiang.png")), 0.5f)
                    .outputQuality(0.8)
                    .toFile("E:\\img\\testImg_new.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
