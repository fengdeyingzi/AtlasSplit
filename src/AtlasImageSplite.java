
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.Iterator;

/**
 * atlas 图集文件分割
 */
public class AtlasImageSplite {

	public static String outPath = "pack_out/";
	public static String atlasFilePath = "pack.atlas";
    public static String imageFilePath = "pack.png";
    public static String main(String args[]) throws Exception{
        

        
        File outDir = new File(outPath);
        if(!outDir.exists()){
            outDir.mkdirs();
        }
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(atlasFilePath)));
        String line = null;
        int count = 0;
        String name = null;
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        while(null != (line = br.readLine())){
            count++;
            if(1 == count) continue;;
            if(line.startsWith("size:")) continue;
            if(line.startsWith("format:")) continue;
            if(line.startsWith("filter:")) continue;
            if(line.startsWith("repeat:")) continue;

            if(line.startsWith("  index")){
                System.out.println("处理" + name);
                buffer.append("处理" + name);
                FileInputStream is = null;
                ImageInputStream iis = null;
                BufferedImage bi = ImageIO.read(new File(imageFilePath));
                System.out.printf("%s:%d,%d,%d,%d\n",name, x, y, w, h);
                buffer.append(String.format("%s:%d,%d,%d,%d\n",name, x, y, w, h));
                BufferedImage subImage = bi.getSubimage(x, y, w, h);
                ImageIO.write(subImage, "png", new File(outDir + File.separator + name + ".png"));
                continue;
            }
            if(line.startsWith("  xy:")){
                line = line.replaceAll("  xy:","");
                String par[] = line.split(",");
                x = parseInt(par[0].trim());
                y = parseInt(par[1].trim());
                continue;
            }
            if(line.startsWith("  size:")){
                line = line.replaceAll("  size:","");
                String par[] = line.split(",");
                w = parseInt(par[0].trim());
                h = parseInt(par[1].trim());
                continue;
            }
            if(line.startsWith("  ")) continue;
            name = line.trim();
        }
        buffer.append("处理完成\n");
        return buffer.toString();
    }

    public static int parseInt(String str){
    return parseInt(str, 0);
    }

    public static int parseInt(String str, int defaultValue){
        try{
            return Integer.parseInt(str);
        }catch (Exception e) {}
        return defaultValue;
    }
}