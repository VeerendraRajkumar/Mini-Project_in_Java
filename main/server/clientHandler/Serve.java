package server.clientHandler;

import java.io.*;
import java.net.*;
import server.utility.*;

public class Serve extends Thread
{
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    static String output;

    public Serve(Socket s)
    {
     socket = s;
    }
    
    private void sendImage( File file)
    {
    	System.out.println("sendImage");
    	try
    	{
    	FileInputStream fin = new FileInputStream(file);
    	
    	byte[] data = fin.readAllBytes();
    	out.writeInt(data.length);
    	out.write(data);
    	out.flush();

        System.out.println("sendImage->end");            
    	fin.close();
    	}
    	catch(IOException ioe)
	 {
	  System.out.println("Its seems ioe\n" + ioe);
	 }
    }
    
    private File receive() throws IOException
    {
    //receive Image
       int i;
       File iFile;
       FileOutputStream fout;
    
       iFile = new File("imglib/in.jpg");
	    fout = new FileOutputStream(iFile);
     
                //receive and save image from client
            	System.out.println("Bfr while");
            	i = in.readInt();
            	byte[] data = new byte[i];
            	in.readFully(data);
            	fout.write(data);
            	     
		fout.flush();
		System.out.println("Image Has Been Received");  
		fout.close();
		
	return iFile;
    }
    
    private void doService()
    {
       try
    	{   
    	    in = new DataInputStream ( socket.getInputStream());
            out = new DataOutputStream ( socket.getOutputStream());
            int serviceCode = 0;
            File iFile, oFile;
            FileOutputStream fout;
    	
    	    while(true)
    	    {        
	    //receive service code
            serviceCode = in.readInt();

            //serviceCode = Integer.parseInt(output);
            System.out.println("Received serviceCode\t" + serviceCode );
            
            switch(serviceCode)
            {
                case 1:
                	//service 1
                	//OCR
                	iFile = receive();
                	OCR o =  new OCR();
                	output = o.extract(iFile);
                	out.writeUTF(output);
                	break;
                case 2:
                	//service 2
                	//GrayScale
                	iFile = receive();
                	GrayScale g = new GrayScale();
                	oFile = g.gray(iFile);
          		sendImage(oFile);
                	break;
                case 3:
                	//service 3
                	//MirrorImage
                	iFile = receive();
                	MirrorImage m = new MirrorImage();
                	oFile = m.mirror(iFile);
          		sendImage(oFile);
                	break;
                case 4:
                	//service 4
                	//RandomPixels Image Generator
                	RandomImage r = new RandomImage();
                	oFile = r.randomImg();
                	sendImage(oFile);
                	break;
                case 5:
                	//service 5
                	//Negative
                	iFile = receive();
                	Negative n = new Negative();
                	oFile = n.negate(iFile);
          		sendImage(oFile);
                	break;
                //default:
                	//Send txt Something went wrong!!
                	//Please try again
            }
            }//end of while
        }catch(IOException ioe)
	{
	   System.out.println("IOE: " + ioe);
	}
	catch(NumberFormatException nfe)
	{
	  System.out.println("NFE: " + nfe);
	  doService();
	}
    }
    
    public void run()
    {
            System.out.println("Serving Client");   
                doService(); 
    }
	
}
