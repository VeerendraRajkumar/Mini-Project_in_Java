package server.utility;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MirrorImage
{
    public File mirror(File input)
    {
        BufferedImage simg = null;
        try
        { 
            simg = ImageIO.read(input);
        }
        catch(IOException ioe)
        {
            System.out.println("IOE: " + ioe);
        }
        int width = simg.getWidth();
        int height = simg.getHeight();
        BufferedImage mimg = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++)
       {
            for (int lx = 0, rx = width - 1; lx < width; lx++, rx--)
            {
                int p = simg.getRGB(lx, y);
                mimg.setRGB(rx, y, p);
            }
        }
        // save mirror image
        try
        {
            File f = new File("imglib/MirrorOut.png");
            if(ImageIO.write(mimg, "png", f))
            {
             System.out.println("Done.");
             return f;
            }
            else
            {
             System.out.println("NotDone."); 
            }
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
