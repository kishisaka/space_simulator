package us.ttyl.starship.utils;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageTransfomerFile 
{
	public ImageTransfomerFile() throws Exception
	{
		for(int i = 0; i < 360; i = i + 10)
		{
			 //create output buffer
			 BufferedImage output = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
			 
			 //get the source image from disk
			 BufferedImage img = ImageIO.read(new File("images_raw/map_player_ship.png"));
			  
			 //create Graphics instance from the output buffer
			 Graphics2D g2d = (Graphics2D) output.getGraphics();         
	
			 //create the transformations
	         AffineTransform at = new AffineTransform();
	         
	         //setup reposition transform
	         at.translate(-100, -100);
	         
	         //setup rotation transform
	         at.rotate(Math.toRadians(i), 200, 200);
	         
	         //draw the image with transformation to the output buffer
	         g2d.drawImage(img, at, null);
	         
	         //write transformed image to disk
	         ImageIO.write(output, "png", new File("images/map-player-ship_"+i+".png"));
	         
	         //clear output buffer
	         g2d.dispose();
		}
	}
	
	public static void main(String args[])
	{
		try
		{
			new ImageTransfomerFile();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
