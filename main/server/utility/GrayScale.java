package server.utility;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.swing.*;

public class GrayScale 
{

   BufferedImage  image;
   int width;
   int height;
   
   public File gray(File input) 
   {
   
      try {
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         
         for(int i=0; i<height; i++) {
         
            for(int j=0; j<width; j++) {
            
               Color c = new Color(image.getRGB(j, i));

               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *0.114);
               Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
               image.setRGB(j,i,newColor.getRGB());
              
            }
         }
         
         File output = new File("imglib/Grayout.jpg");
         ImageIO.write(image, "jpg", output);
         return output;
         
      } catch (Exception e) {}
      return null;
   }
}
