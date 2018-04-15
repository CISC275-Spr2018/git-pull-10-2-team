//import Animation.mode;

//New import statements added to implement key adaptor
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
//End of new import statements

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
class Model{
	int width;
	int height;
	int imageWidth;
	int imageHeight;
	int xloc = 0;
	int yloc = 0;  
	final int xIncr = 8;    
	final int yIncr = 8;
	Direction direction = Direction.EAST;
	Mode mode=Mode.IDLE;
	int count=0;
	
	//New code added to implement keystrokes changing the direction of the orc
	
	JPanel j;
	public Model(int width,int height,int imageWidth,int imageHeight, JPanel j) {
		
		this.j=j;
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), "up");
		j.getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	direction=Direction.NORTH;
            }
        });
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "down");
		j.getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	direction=Direction.SOUTH;
            }
        });
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "left");
		j.getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	direction=Direction.WEST;
            }
        });
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "right");
		j.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	direction=Direction.EAST;
            }
        });
		
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_J,0),"j");
		j.getActionMap().put("j", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mode=Mode.JUMP;
            }
        });
		
		j.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F,0),"f");
		j.getActionMap().put("f", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	mode=Mode.FIRE;
            }
        });
		this.width=width;
		this.height=height;
		this.imageWidth=imageWidth;
		this.imageHeight=imageHeight;
	}	
	public void updateLocationAndDirection() {
		if(xloc>=width*.7) {
			direction=Direction.WEST;
		}
		if(xloc<=-30) {
			direction=Direction.EAST;
		}
		if(yloc>=height*.4) {
			direction=Direction.NORTH;
		}
		if(yloc<=-30) {
			direction=Direction.SOUTH;
		}
		switch(direction) {
		case NORTH:
			yloc-=yIncr;
			break;
		case SOUTH:
			yloc+=yIncr;
			break;
		case EAST:
			xloc+=xIncr;
			break;
		default:
			xloc-=xIncr;
			break;
		}
	}	
	public Mode getMode() {
		if(mode==Mode.JUMP) {
			count++;
		}
		if(mode==Mode.FIRE) {
			count++;
		}
		if(count==9) {
			mode=Mode.FORWARD;
			count=0;
		}
		return mode;
	}
	public void setMode(Mode m) {
		mode = m;
	}
	public int getX() {
		return xloc;
	}
	public int getY() {
		return yloc;
	}
	public Direction getDirect() {
		return direction;
	}
}
