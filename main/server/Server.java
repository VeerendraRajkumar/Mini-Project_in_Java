package server;

import java.io.*;
import java.net.*;
import server.utility.*;
import server.clientHandler.*;

public class Server
{
	public static void main(String[] args)
	{
	try
	    {
	     int clients = 0;	
	     ServerSocket ss;
	     Socket socket;
	     
	     System.out.println("Server Started");
	     ss= new ServerSocket(6543);
	     
		while(true)
		{
		clients++;//number of clients
		
		//accept connection
		socket = ss.accept();  
		//details about the connection
		System.out.println(socket);
           	System.out.println("Client "+ clients +"  Connected");
		
		//start serving the client in new thread
		Serve serve = new Serve(socket); 
		serve.start();
		}
		
	}
	catch (IOException ioe) 
	{   System.out.println("IOE: " + ioe);
                        ioe.printStackTrace();
        }
  }	
}
