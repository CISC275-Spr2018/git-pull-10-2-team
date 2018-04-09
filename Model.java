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
	final int yIncr = 4;
	Direction direction = Direction.NORTH;
	
	//New code added to implement keystrokes changing the direction of the orc
	JFrame frame;
	JPanel j;
	public Model(int width,int height,int imageWidth,int imageHeight, JFrame frame, JPanel j) {
		this.frame=frame;
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
