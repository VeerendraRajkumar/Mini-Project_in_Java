package client.utility;

//import files.
import java.io.*; 
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
 
import javax.swing.*; 
import javax.imageio.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import javax.swing.WindowConstants.*; 

//setUp(); is to run this.
public class Service
{
	JFrame frame;
	Socket socket;
	File file;
	DataInputStream in;
	DataOutputStream out;
	int serviceCode = 0;
	
        public Service()
        {
        	setUp();
        }
        
        public void setUp()
        { 
	    // Create and set up the window.
	    frame = new JFrame("Client");
	    socket = null;
	    setConnection();
	    //frame.setJMenuBar(createMenuBar());

	    // Display the window.
	    frame.setSize(500,300);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}
    	
    private void handler()
    {
       frame.setJMenuBar(null );
       JLabel l1 = new JLabel("Server NOT Available.");
       l1.setBounds(150, 10, 200, 20);
       JLabel l2 = new JLabel("Please Try again Later.");
       l2.setBounds(150, 30, 400, 20);
       frame.add(l1);
       frame.add(l2);
       frame.setVisible(true);
    }
    
    private void setConnection() 
	{
		frame.setLayout(null);
		
		JLabel intro = new JLabel("This is Client to My Server. ");
		intro.setBounds(150, 10, 200, 20);
		frame.add(intro);
		
		JLabel connect = new JLabel("Connected to Server.");
		connect.setBounds(150, 30, 200, 20);
		frame.add(connect);
		frame.setVisible(true);
		
		try
		{
		   //Connects to Server at port 6543
		   socket = new Socket("10.0.3.154",6543);
		   frame.setJMenuBar(createMenuBar());
		   in = new DataInputStream(socket.getInputStream());
		   out = new DataOutputStream(socket.getOutputStream());
		}
		catch(IOException ioe)
		{ 
		   System.out.println("IOE:\t"+ioe);
		   frame.remove(intro);
		   frame.remove(connect);
		   /*intro = new JLabel("Server NOT Available.");
		   intro.setBounds(150, 10, 200, 20);
		   connect = new JLabel("Please Try again Later.");
		   connect.setBounds(150, 30, 400, 20);
		   frame.add(intro);
		   frame.add(connect);
		   frame.setVisible(true);*/
		   handler();
		 }		
	}
    
    private JMenuBar createMenuBar()
    {
        JLabel label = new JLabel("");
 	JMenuBar menuBar;
 	JMenu service,service2;
	JMenuItem itemOCR;
	JMenuItem gray, mirror, rimg, negate  ;
        
        //Create the menu bar.
        menuBar = new JMenuBar();
        
        //Bulid service Menu.
        service = new JMenu("Services");
        service.setMnemonic(KeyEvent.VK_S);
	menuBar.add(service);
	
	//itemOCR menuitem
	itemOCR = new JMenuItem("OCR",new ImageIcon("images/OCR.png"));
	itemOCR.setMnemonic(KeyEvent.VK_O);
	itemOCR.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_1, ActionEvent.ALT_MASK));//Alt+1
	service.add(itemOCR);
	service.addSeparator();
	
	//submenu for service 2
	service2 = new JMenu("IP");
	service2.setMnemonic(KeyEvent.VK_I);
	
	//adding menuitems into the submenu
	gray = new JMenuItem("GrayScale",new ImageIcon("images/grayScale.png"));
	gray.setMnemonic(KeyEvent.VK_G);
	gray.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_2, ActionEvent.ALT_MASK));//Alt+2
        service2.add(gray);
        
        mirror = new JMenuItem("MirrorImage",new ImageIcon("images/MirrorImage.png"));
	mirror.setMnemonic(KeyEvent.VK_M);
	mirror.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_3, ActionEvent.ALT_MASK));//Alt+3
        service2.add(mirror);
        
        rimg = new JMenuItem("RandomImage",new ImageIcon("images/RandomImage.png"));
	rimg.setMnemonic(KeyEvent.VK_R);
	rimg.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_4, ActionEvent.ALT_MASK));//Alt+4
        service2.add(rimg);
       
        negate = new JMenuItem("Negative",new ImageIcon("images/negative.png"));
	negate.setMnemonic(KeyEvent.VK_N);
	negate.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_5, ActionEvent.ALT_MASK));//Alt+5
        service2.add(negate);
        
        service.add(service2);
        
	//Actionlistener for item.OCR
        itemOCR.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	try
            	{
                serviceCode = 1;
                if(createFileChooser()==1)
                 {
                   out.writeInt(serviceCode);
                   sendImage(file);
                   receive(serviceCode);
                 }
                }
                catch (IOException ioe)
        	{System.out.println("IOE:"+ioe);
        	   handler();
        	}   
            }
        });
        
        //Actionlistener for item gray
        gray.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	try
            	{
            	serviceCode = 2;
            	if(createFileChooser()==1)
                 {
                   out.writeInt(serviceCode);
                   sendImage(file);
                   receive(serviceCode);
                 }
                }
                catch (IOException ioe)
        	{ 
        	   System.out.println("IOE:"+ioe);
        	   handler();
        	}   
            }
        });
        
        //Actionlistener for item mirror
        mirror.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	try
            	{
            	serviceCode = 3;
                if(createFileChooser()==1)
                 {
                   out.writeInt(serviceCode);
                   sendImage(file);
                   receive(serviceCode);
                 }
                }
                catch (IOException ioe)
	        {
	           System.out.println("IOE:"+ioe);
	           handler();
	        }  
            }
        });
        
        //ActionListener for item rimg.
        rimg.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	try
            	{
            	  serviceCode = 4;
                  out.writeInt(serviceCode);
                  receive(serviceCode);
                }
                catch (IOException ioe)
	        {
	           System.out.println("IOE:"+ioe);
	           handler();
	        }  
            }
        });
        
        //ActionListener for item negate.
        negate.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	try
            	{
            	serviceCode = 5;
            	if(createFileChooser()==1)
                 {
                   out.writeInt(serviceCode);
                   sendImage(file);
                   receive(serviceCode);
                 }
                }
                catch (IOException ioe)
        	{ 
        	   System.out.println("IOE:"+ioe);
        	   handler();
        	}   
            }
        });
        
        //receive(serviceCode);
        return menuBar;
    }
    
    private void receive(int serviceCode)
	{
		try
		{
		switch(serviceCode)
		  {
			case 1:
				System.out.println("inside receive case 1");
				String output = in.readUTF();
				JOptionPane.showInternalMessageDialog​(null,output);
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				File file = new File("imglib/out.jpg");
		            	FileOutputStream fout = new FileOutputStream(file);
		            	int i;
		     
		                //receive and save image from client
		            	i = in.readInt();
			    	byte[] data = new byte[i];
			    	in.readFully(data);
			    	fout.write(data);  
				        
				fout.flush();
				System.out.println("Image Has Been Received"); 
				fout.close();
				
				showImage(file);
				break;
			default:
				JOptionPane.showInternalMessageDialog​(null,"Something went wrong!!!\nPlease try again.");
		  }
		}
		catch (IOException ioe)
        	{
        	   System.out.println("IOE:"+ioe);
        	   handler();
        	}
	}
    
    //lets user choose a file and sends that to server.
    private int createFileChooser() 
    {
    	System.out.println("Bfr FC");
    	//File file;
        JFileChooser fileChooser = new JFileChooser( FileSystemView.getFileSystemView() );
 	
	FileNameExtensionFilter filter = new FileNameExtensionFilter( "jpg,jpeg,JPG,tiff,png", "jpg", "jpeg", "png", "tiff", "JPG"); 
				fileChooser.setFileFilter(filter);
				int response = fileChooser.showDialog(null, "Select image"); 
				if (response == JFileChooser.APPROVE_OPTION) 
				{ 	
				  file = new File( fileChooser.getSelectedFile().getAbsolutePath() ); 
				  showImage(file);
				  //sendImage(file);
				  return 1;
    				}
    				else
    				  return 0;
    }
    
    private void showImage(File file) 
    {	
    	try
    	{
    	System.out.println("showImage");
    	BufferedImage myPicture = ImageIO.read(file);
	Graphics2D g = (Graphics2D) myPicture.getGraphics();
    	JComponent newContentPane = new ScrollPicture( myPicture);
        newContentPane.setOpaque(false); //content panes must be opaque.
        frame.setContentPane(newContentPane);
        frame.setVisible(true);
        System.out.println("showImage->end");
        }
        catch(IOException ioe)
	 {
	  System.out.println("Its seems ioe\n" + ioe);
	 }
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
}
