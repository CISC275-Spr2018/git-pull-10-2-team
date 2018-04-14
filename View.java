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
	final int frameCountIdle=4;
	int picNum = 0;
	int picNumJump=0;
	int picNumIdle=0;
	int x;
	int y;
	Direction direction = Direction.EAST;
	Mode mode = Mode.IDLE;
	public BufferedImage[] pics;
	public BufferedImage[] picsNorth;
	public BufferedImage[] picsEast;
	public BufferedImage[] picsWest;
	public BufferedImage[] picsJump;
	public BufferedImage[] picsNorthJump;
	public BufferedImage[] picsEastJump;
	public BufferedImage[] picsWestJump;
	public BufferedImage[] picsEastIdle;
	public BufferedImage[] picsWestIdle;
	public BufferedImage[] picsNorthIdle;
	public BufferedImage[] picsSouthIdle;
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
			BufferedImage img = createImage(Direction.SOUTH, Mode.FORWARD);
			BufferedImage imgNorth = createImage(Direction.NORTH, Mode.FORWARD);
			BufferedImage imgEast = createImage(Direction.EAST, Mode.FORWARD);
			BufferedImage imgWest = createImage(Direction.WEST, Mode.FORWARD);
			
			BufferedImage imgJump = createImage(Direction.SOUTH, Mode.JUMP);
			BufferedImage imgNorthJump = createImage(Direction.NORTH, Mode.JUMP);
			BufferedImage imgEastJump = createImage(Direction.EAST, Mode.JUMP);
			BufferedImage imgWestJump = createImage(Direction.WEST, Mode.JUMP);
			
			BufferedImage imgIdle = createImage(Direction.ALL, Mode.IDLE);
			
	    	pics = new BufferedImage[10];
	    	picsNorth = new BufferedImage[10];
	    	picsEast = new BufferedImage[10];
	    	picsWest = new BufferedImage[10];
	    	
	    	picsJump = new BufferedImage[8];
	    	picsNorthJump = new BufferedImage[8];
	    	picsEastJump = new BufferedImage[8];
	    	picsWestJump = new BufferedImage[8];
	    	
	    	picsEastIdle = new BufferedImage[4];
	    	picsWestIdle = new BufferedImage[4];
	    	picsNorthIdle = new BufferedImage[4];
	    	picsSouthIdle = new BufferedImage[4];
	    	
		
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
	    	
	    	for(int i = 0; i < frameCountIdle; i++) {	    		
	    		picsEastIdle[i] = imgIdle.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
	    		picsWestIdle[i] = imgIdle.getSubimage(imgWidth*i, imgHeight, imgWidth, imgHeight);
	    		picsNorthIdle[i] = imgIdle.getSubimage(imgWidth*i, imgHeight*2, imgWidth, imgHeight);
	    		picsSouthIdle[i] = imgIdle.getSubimage(imgWidth*i, imgHeight*3, imgWidth, imgHeight);
	    	}
		}	
		
		private BufferedImage createImage(Direction direction, Mode mode){
			
	    	BufferedImage bufferedImage;
		
	    	try { //Please look into; For some reason direction.toString() was giving me an error for Direction.ALL. May just be my computer.
	    		if (mode!=mode.IDLE)
	    			bufferedImage = ImageIO.read(new File("images/orc/orc_"+mode.toString()+"_"+direction.toString()+".png"));
	    		else
	    			bufferedImage = ImageIO.read(new File("images/orc/orc_"+mode.toString()+"_ewns.png")); 
	
	    		return bufferedImage;
	    	
			} catch (IOException e) {
		
	    		e.printStackTrace();
		
	    	}
	    	
			return null;
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
			}else if (mode==Mode.IDLE) {
				picNumIdle=(picNumIdle+1)%frameCountIdle;
				switch(direction) {	
				case NORTH:
					g.drawImage(picsNorthIdle[picNumIdle], x, y, Color.gray, this);
					break;
				case SOUTH:
					g.drawImage(picsSouthIdle[picNumIdle], x, y, Color.gray, this);
					break;
				case EAST:
					g.drawImage(picsEastIdle[picNumIdle], x, y, Color.gray, this);
					break;
				default:
					g.drawImage(picsWestIdle[picNumIdle], x, y, Color.gray, this);
					break;
				}
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
