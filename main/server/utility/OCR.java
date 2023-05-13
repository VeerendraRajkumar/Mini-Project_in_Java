package server.utility;

import java.io.*; 
import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;

import net.sourceforge.tess4j.*; 

public class OCR //extends Thread
{
	public String extract(File file)
	{
		Tesseract tesseract = new Tesseract(); 
		try 
		{ 
			//path to training data of OCR engine.
			tesseract.setDatapath("/home/msc-1/Desktop/work/Assignment2/Tess4J-3.4.8-src/Tess4J/tessdata/"); 
			
			//OCR is performed on the given file.
			String text 
				= tesseract.doOCR(file); 
 
			System.out.print(text); 
			//text = getText(text);
			return text;
			
		}catch (TesseractException e) 
		{ 
			System.out.println("\n"+e); 
		}
		return "\0";
	}
	
	//this method gets txt specifically for Aadharcard  backside
	private static String getAadharTxt(Tesseract scn,String imgAdd)
	{ 
	 String txt = "Didn't obtain any information try with another image.";
	 
	 try 
	 {
	  BufferedImage bufferedImage = ImageIO.read(new File(imgAdd));
			//aadhar card details recovery
	  Rectangle rect = new Rectangle( bufferedImage.getWidth()/2, bufferedImage.getMinY()/2, bufferedImage.getWidth()/2, bufferedImage.getHeight());
  	  txt = scn.doOCR(bufferedImage, rect);
	 }
	 catch(IOException ioe)
	  {
		System.out.println("\n" + ioe );
	  }																																													
	 catch(TesseractException te)
	  {
		System.out.println("\n" + te );
	  }
	  return txt;
	}
	
/*	private String getText(String input)
	{
	    StringTokenizer st = new StringTokenizer(input);
	    int g=0,o=0;
     	    while (st.hasMoreTokens()) 
     	    {
            
              //System.out.println(st.nextToken());
     	    	if(st.nextToken().equalsIgnoreCase("Government"))
     	    	{
     	    	    if(st.nextToken().equalsIgnoreCase("of"))
     	    	    if(st.nextToken().equalsIgnoreCase("India"))
     	    	    {
     	    	    	
     	    	    }
     	    	}
     	    }
	
	}*/
	
}

