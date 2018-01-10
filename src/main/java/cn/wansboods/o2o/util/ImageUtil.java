package cn.wansboods.o2o.util;

import cn.wansboods.o2o.controller.AreaController;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat( "yyyyMMddHHmmss");
    private static final Random r = new Random();

    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr ){

        String realFileName = getRandomFileName(); //随机名
        String extension = getFileExtension( thumbnail ); //扩展名
        makeDirPath( targetAddr );

        String relatibeAddr = targetAddr + realFileName + extension;
        File dest = new File( PathUtil.getImgBasePath() + relatibeAddr );
        try {
            Thumbnails.of(thumbnail.getInputStream()).size( 200,200 )
                    .watermark( Positions.BOTTOM_RIGHT, ImageIO.read( new File(basePath + "/watermark.jpg")),0.25f )
                    .outputQuality( 0.8f ).toFile( dest );
        }catch ( IOException e ){
            e.printStackTrace();
        }

        return relatibeAddr;
    }

    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File( realFileParentPath );
        if( !dirPath.exists() ){
            dirPath.mkdirs();
        }
    }

    private static String getFileExtension(CommonsMultipartFile cFile) {
        String originalFileName = cFile.getOriginalFilename();
        return originalFileName.substring( originalFileName.lastIndexOf("."));
    }



    /**
     *
     * @return
     */
    public static String getRandomFileName() {
        //获取随机的五位数
        int rannum = r.nextInt(89999 ) + 10000;
        String nowTimeStr = sDateFormat.format( new Date() );
        return nowTimeStr + rannum;
    }

    public static void main(String[] args) {
        Logger ptr = LoggerFactory.getLogger( ImageUtil.class );
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        ptr.info( "图片的地址名称{}", basePath );
        try {
            Thumbnails.of(new File("G:\\img\\testImg.jpg"))
                    .size(800, 600)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File( basePath + "\\jpg\\jiaziyingxiang.png")), 0.5f)
                    .outputQuality(0.8)
                    .toFile("G:\\img\\testImg_new.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
