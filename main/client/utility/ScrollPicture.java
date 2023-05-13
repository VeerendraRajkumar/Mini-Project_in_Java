package client.utility;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.*;
import javax.swing.WindowConstants.*;

/**
  *This uses ScrollablePicture class to enable 
  *the picture to be scrolled.
**/

public class ScrollPicture extends JPanel 
{   private ScrollablePicture picture;
 
    public ScrollPicture( BufferedImage myPicture)
    {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        ImageIcon img = new ImageIcon(myPicture);

        //Set up the scroll pane.
        picture = new ScrollablePicture(img, Toolkit.getDefaultToolkit().getScreenResolution()/2);
        
        JScrollPane pictureScrollPane = new JScrollPane(picture);
        pictureScrollPane.setPreferredSize(new Dimension(500, 250));

        add(pictureScrollPane);
        setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
    }

}
