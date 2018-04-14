/**
 * Do not modify this file without permission from your TA.
 **/

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

import java.awt.EventQueue;

public class Controller{

	private Model model;
	static private View view;
    
    final int drawDelay = 30; //msec
    Action updateProgram;
    static boolean isRun=false;
    JButton b;
	public Controller(){
        
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight(), view.getPanel());
        
        updateProgram = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			//increment the x and y coordinates, alter direction if necessary
                
                //update the view
                if(isRun==true) {
                	model.updateLocationAndDirection();
                }
                view.update(model.getX(), model.getY(), model.getDirect(), model.getMode());
    		}
    	};
    	b=view.getButton();
    	b.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			            if(isRun==false) {
			            	isRun=true; 
			            	b.setText("Stop");
			            	model.setMode(Mode.FORWARD);
			            } else {
			            	isRun=false;
			            	b.setText("Start");
			            	model.setMode(Mode.IDLE);
			            }
			}
			    }); 
        
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
					Controller c = new Controller();
					Timer t = new Timer(c.drawDelay, c.updateProgram);
					t.start();
					}
				});
			}	
}
