package server.utility;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class RandomImage
{
    //public static void main(String args[])throws IOException
    public File randomImg()
    {
        int width = 640, height = 320;
        BufferedImage img = null;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // file object
        File f = null;
       // create random values pixel by pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int a = (int)(Math.random()*256); //generating
                int r = (int)(Math.random()*256); //values
                int g = (int)(Math.random()*256); //less than
                int b = (int)(Math.random()*256); //256
                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel
                img.setRGB(x, y, p);
           }
        }
        // write image
        try
        {
            f = new File("imglib/RIOut.png");
            if(ImageIO.write(img, "png", f))
            {
             System.out.println("Finished.");
             return f;
            }
            else
            System.out.println("NotDone.");
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
