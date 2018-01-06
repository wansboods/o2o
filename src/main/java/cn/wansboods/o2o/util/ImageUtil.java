package cn.wansboods.o2o.util;

import cn.wansboods.o2o.controller.AreaController;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

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
