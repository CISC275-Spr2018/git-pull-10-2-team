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
	final int frameCountJump=8;
	int picNum = 0;
	int picNumJump=0;
	int x;
	int y;
	Direction direction = Direction.NORTH;
	Mode mode = Mode.FORWARD;
	public BufferedImage[] pics;
	public BufferedImage[] picsNorth;
	public BufferedImage[] picsEast;
	public BufferedImage[] picsWest;
	public BufferedImage[] picsJump;
	public BufferedImage[] picsNorthJump;
	public BufferedImage[] picsEastJump;
	public BufferedImage[] picsWestJump;
	final static int frameWidth = 500;
    
	final static int frameHeight = 300;
    
	final static int imgWidth = 165;
    
	final static int imgHeight = 165;
	
	final static int imgWidthJump = 165;
    
	final static int imgHeightJump = 165;
	int createFrame=0;
	JFrame frame = new JFrame();
	JButton b=new JButton("Start");
	JPanel p = new JPanel();
	class Animated extends DrawPanel{
		public Animated() {
			BufferedImage img = createImage(Direction.SOUTH);
			BufferedImage imgNorth = createImage(Direction.NORTH);
			BufferedImage imgEast = createImage(Direction.EAST);
			BufferedImage imgWest = createImage(Direction.WEST);
			
			BufferedImage imgJump = createImageJump(Direction.SOUTH);
			BufferedImage imgNorthJump = createImageJump(Direction.NORTH);
			BufferedImage imgEastJump = createImageJump(Direction.EAST);
			BufferedImage imgWestJump = createImageJump(Direction.WEST);
		
	    	pics = new BufferedImage[10];
	    	picsNorth = new BufferedImage[10];
	    	picsEast = new BufferedImage[10];
	    	picsWest = new BufferedImage[10];
	    	
	    	picsJump = new BufferedImage[8];
	    	picsNorthJump = new BufferedImage[8];
	    	picsEastJump = new BufferedImage[8];
	    	picsWestJump = new BufferedImage[8];
	    	
		
	    	for(int i = 0; i < frameCount; i++) {
		
	    		pics[i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    		picsNorth[i] = imgNorth.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    		picsEast[i] = imgEast.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    		picsWest[i] = imgWest.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    	}
	    	for(int i = 0; i < frameCountJump; i++) {	    		
	    		picsJump[i] = imgJump.getSubimage(imgWidthJump*i, 0, imgWidthJump, imgHeightJump);
	    		picsNorthJump[i] = imgNorthJump.getSubimage(imgWidthJump*i, 0, imgWidthJump, imgHeightJump);
	    		picsEastJump[i] = imgEastJump.getSubimage(imgWidthJump*i, 0, imgWidthJump, imgHeightJump);
	    		picsWestJump[i] = imgWestJump.getSubimage(imgWidthJump*i, 0, imgWidthJump, imgHeightJump);
	    	}
		}
		
		private BufferedImage createImageJump(Direction direction) {
			BufferedImage bufferedImage;
			
	    	try {
	    		bufferedImage = ImageIO.read(new File("images/orc/orc_jump_"+direction.toString()+".png"));
	
	    		return bufferedImage;
	    	
			} catch (IOException e) {
		
	    		e.printStackTrace();
		
	    	}
			return null;
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
			if(mode==Mode.FORWARD) {
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
			}else if(mode==Mode.JUMP) {
				picNumJump=(picNumJump+1)%frameCountJump;
				switch(direction) {	
				case NORTH:
					g.drawImage(picsNorthJump[picNumJump], x, y, Color.gray, this);
					break;
				case SOUTH:
					g.drawImage(picsJump[picNumJump], x, y, Color.gray, this);
					break;
				case EAST:
					g.drawImage(picsEastJump[picNumJump], x, y, Color.gray, this);
					break;
				default:
					g.drawImage(picsWestJump[picNumJump], x, y, Color.gray, this);
					break;
				}
			}else {
				
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

	public void update(int x, int y, Direction direction,Mode mode) {
		this.x=x;
		this.y=y;
		this.direction=direction;
		this.mode=mode;
		frame.repaint();
        try {
			
			Thread.sleep(100);		
		} 
		catch (InterruptedException e) {

            e.printStackTrace();

        }
	}
}
