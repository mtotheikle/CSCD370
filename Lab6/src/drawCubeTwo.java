
/******************************************************************************
* File : drawCube.java
* Author : http://java.macteki.com/
* Description :
* Demonstrate a flood fill algorithm.
* Tested with : JDK 1.6
* Macteki flood filler
* http://java.macteki.com/2011/03/how-to-do-flood-fill-operation-in-java.html
* Ivan Macteki (Macteki)
* Edwin Armstrong A.K.A (efa)
******************************************************************************/
 
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Point;
 
class drawCubeTwo
{
  //******************************************************************
  // fill the whole image with a color specified by its RGB component
  // by Macteki
  //******************************************************************
  static void fillImage(BufferedImage image, int red, int green, int blue)
  {
	 int packedRGB = packRgb(255,255,255);//white
 
    for (int y=0;y<image.getHeight(null);y++)
    {
      for (int x=0;x<image.getWidth(null);x++)
        image.setRGB(x,y,packedRGB);
    }
  }
 
  //*************************************************
  // implements the flood fill algorithm. by Macteki
  //*************************************************
  public static void floodFill(BufferedImage image, int x,int y, int fillColor)
  {
    java.util.ArrayList<Point> examList=new java.util.ArrayList<Point>();
 
    int initialColor=image.getRGB(x,y);
    examList.add(new Point(x,y));
 
    while (examList.size()>0)
    {
      Point p = examList.remove(0);  // get and remove the first point in the list
      if (image.getRGB(p.x,p.y)==initialColor)
      {
        x = p.x;  y = p.y;
        image.setRGB(x, y, fillColor);  // fill current pixel
 
        examList.add(new Point(x-1,y));        // check west neighbor
        examList.add(new Point(x+1,y));        // check east neighbor
        examList.add(new Point(x,y-1));        // check north neighbor
        examList.add(new Point(x,y+1));        // check south neighbor
 
       // waitNS(1);    // delay to see floodFill() work
		 // repaintImage(image);
		  
		}
    }
  } 
  
  // repaint the image  by Macteki
  private static void repaintImage(BufferedImage image)
  {
    _imageLabel.setIcon(new ImageIcon(image));
    _imageLabel.repaint();   
  }
  
 //******************************************************
 //waitNS:: wait for perscipbed nanosecond (efa) 5/10/12
 //******************************************************
  public static void waitNS(long ns)  
  {
   try {  Thread.sleep(ns);  }   // Pause ns
        catch (Exception ignore) { ; }
  }
  
  //***********************************************
  //packRGB:: Create a packed color it. by Macteki
  //***********************************************
  public static int packRgb(int r,int g,int b)
  {
    return (r*256+g)*256+b;
  }
 
  static JLabel _imageLabel;
  public static void main(String[] args) throws Exception
  {
    // create an 300x300 RGB image
    BufferedImage image=new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
 
    // fill the image with green color
    fillImage(image,0,255,0);		 

 
    JLabel imageLabel=new JLabel();
    _imageLabel = imageLabel;  // make it global
    imageLabel.setIcon(new ImageIcon(image));
    imageLabel.setText("Filling the box with yellow color ...");
 
    javax.swing.JFrame window=new javax.swing.JFrame();
    window.setTitle("Cube Experiment");
    window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
 
    window.add(imageLabel);
 
    window.pack();
    window.setVisible(true);

	//************************* Draw a box and fill it, (efa)  5/10/12 ************************
	 java.awt.Graphics2D gr=(java.awt.Graphics2D) image.getGraphics();
  	 
	 int x1 = 50; int y1 = 150;
	 int x2 = 150; int y2 = 150;
	 int x3 = 150; int y3 = 250;
	 int x4 = 50; int y4 = 250;
	 
     gr.setColor(new java.awt.Color(0,0,255));  // blue
	 gr.setStroke(new java.awt.BasicStroke(2));  // set pen width to 2 pixels
	 	 
     gr.drawLine(x1, y1, x2, y2);
	 repaintImage(image);
	 waitNS(500);
	 
	 gr.drawLine(x2, y2, x3, y3);
	 repaintImage(image);
	 waitNS(500);
	 
	 gr.drawLine(x3, y3, x4, y4);
	 repaintImage(image);
	 waitNS(500);
	 
	 gr.drawLine(x4, y4, x1, y1);
	 repaintImage(image);
	 waitNS(500);
	 
	 // Back box
	 int b2x1 = 100; int b2y1 = 100;
	 int b2x2 = 200; int b2y2 = 100;
	 int b2x3 = 200; int b2y3 = 200;
	 int b2x4 = 100; int b2y4 = 200;
	 
     gr.setColor(new java.awt.Color(0,0,0));  // blue
	 gr.setStroke(new java.awt.BasicStroke(2));  // set pen width to 2 pixels
	 	 
     gr.drawLine(b2x1, b2y1, b2x2, b2y2);
	 repaintImage(image);
	 waitNS(500);
	 
	 gr.drawLine(b2x2, b2y2, b2x3, b2y3);
	 repaintImage(image);
	 waitNS(500);

	 gr.drawLine(b2x3, b2y3, b2x4, b2y4);
	 repaintImage(image);
	 waitNS(500);
	 
	 gr.drawLine(b2x4, b2y4, b2x1, b2y1);
	 repaintImage(image);
	 waitNS(500);

	 // Draw back sides
	 gr.drawLine(x1, y1, b2x1, b2y1);
	 repaintImage(image);
	 waitNS(500);
	 
	 gr.drawLine(x2, y2, b2x2, b2y2);
	 repaintImage(image);
	 waitNS(500);
	 
	 gr.drawLine(x3, y3, b2x3, b2y3);
	 repaintImage(image);
	 waitNS(500);
	 
	 gr.drawLine(x4, y4, b2x4, b2y4);
	 repaintImage(image);
	 waitNS(500);
	 
	 gr.setColor(new java.awt.Color(255, 255, 255));  // white
	 gr.setStroke(new java.awt.BasicStroke(2));  // set pen width to 2 pixels
	 
	 //gr.drawLine(b2x1, b2y1, b2x1 + 50, y1);
	 gr.drawLine(b2x4, b2y4 - 51, b2x1, b2y1 + 2);

	 int yellow = packRgb(255,255,0);
	 floodFill(image, (b2x4+b2y2)/2, (b2y3+b2y4)/2, yellow);//flood fill at center
	 
    // fill the square with yellow color
    //int yellow = packRgb(255,255,0);
	int black = packRgb(0,0,0);
    //floodFill(image, (x2+b2x2)/2, (y3+b2y4)/2, yellow);//flood fill at center
    //*****************************************************************************************

    imageLabel.setIcon(new ImageIcon(image));
    imageLabel.setText("Completed !");
 
  }
}


