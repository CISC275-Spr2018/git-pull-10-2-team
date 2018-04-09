import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.*;

//import Animation.mode;

//import Animation.mode;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

class View extends JFrame{
	final int frameCount = 10;
	int picNum = 0;
	int x;
	int y;
	Direction direction = Direction.NORTH;
	public BufferedImage[] pics;
	public BufferedImage[] picsNorth;
	public BufferedImage[] picsEast;
	public BufferedImage[] picsWest;
	final static int frameWidth = 500;
    
	final static int frameHeight = 300;
    
	final static int imgWidth = 165;
    
	final static int imgHeight = 165;
	int createFrame=0;
	JFrame frame = new JFrame();
	JButton b=new JButton("Start");
	boolean isRun=false;
	JPanel p = new JPanel();
	//Animation animation = new Animation(pics,picsNorth,picsEast,picsWest,imgWidth,imgHeight,frameCount);
	class Animated extends DrawPanel{
		public Animated() {
			BufferedImage img = createImage(Direction.SOUTH);
			BufferedImage imgNorth = createImage(Direction.NORTH);
			BufferedImage imgEast = createImage(Direction.EAST);
			BufferedImage imgWest = createImage(Direction.WEST);
		
	    	pics = new BufferedImage[10];
	    	picsNorth = new BufferedImage[10];
	    	picsEast = new BufferedImage[10];
	    	picsWest = new BufferedImage[10];
	    	
		
	    	for(int i = 0; i < frameCount; i++) {
		
	    		pics[i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    		picsNorth[i] = imgNorth.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    		picsEast[i] = imgEast.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    		picsWest[i] = imgWest.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    	}
		}
		private BufferedImage createImage(Direction direction){
			
	    	BufferedImage bufferedImage;
		
	    	try {
	    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_"+direction.toString()+".png"));
	
	    		return bufferedImage;
	    	
			} catch (IOException e) {
		
	    		e.printStackTrace();
		
	    	}
	    	
			return null;

	    	
	    	// TODO: Change this method so you can load other orc animation bitmaps

	    	}
		@Override
		public void paint(Graphics g) {
			picNum = (picNum + 1) % frameCount;
			switch(direction) {	
			case NORTH:
				g.drawImage(picsNorth[picNum], x, y, Color.gray, this);
				break;
			case SOUTH:
				g.drawImage(pics[picNum], x, y, Color.gray, this);
				break;
			case EAST:
				g.drawImage(picsEast[picNum], x, y, Color.gray, this);
				break;
			default:
				g.drawImage(picsWest[picNum], x, y, Color.gray, this);
				break;
			}
		}
		
	}
    @SuppressWarnings("serial")
	private class DrawPanel extends JPanel {
    	int picNum = 0;
    	//drawPanel.add(b);
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
	    	picNum = (picNum + 1) % frameCount;
	    	g.drawImage(pics[picNum], x, y, Color.gray, this);
		}

		public Dimension getPreferredSize() {
			return new Dimension(frameWidth, frameHeight);
		}
	}

    
	public View() {
		frame.getContentPane().add(new Animated());
		frame.setBackground(Color.gray);	  	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   	
		frame.setSize(frameWidth, frameHeight);    	
		frame.setVisible(true);
		p.setBackground(Color.gray);
		p.add(b);
		frame.add(p);
		frame.setFocusable(false);
	}

	//New method created to return the JFrame
	public JFrame getFrame() {
		return frame;
	}
	//End of new Method
	public JPanel getPanel() {
		return p;
	}
	public JButton getButton() {
		return b;
	}
	public int getWidth() {
		return frameWidth;
	}
	public int getHeight() {
		return frameHeight;
	}
	public int getImageHeight() {
		return imgHeight; 
	}
	public int getImageWidth() {
		return imgWidth;
	}

	public void update(int x, int y, Direction direction) {
		this.x=x;
		this.y=y;
		this.direction=direction;
		frame.repaint();
        try {
			
			Thread.sleep(100);		
		} 
		catch (InterruptedException e) {

            e.printStackTrace();

        }
	}
}
