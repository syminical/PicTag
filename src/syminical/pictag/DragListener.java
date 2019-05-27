package syminical.pictag;

//I did not create this, but I did edit it a little.
//My changes let it handle all mouse events for the program.
//I got this code from https://tips4java.wordpress.com/2009/06/14/moving-windows/

import javax.swing.event.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class DragListener extends MouseInputAdapter
{
    private WindowBox Parent;
    private Point location;
    private MouseEvent pressed;
    private Component component;

    public DragListener(WindowBox P) {
        super();
        
        Parent = P;
    }

    public void mouseClicked(MouseEvent E) {
        if ( Parent != null ) Parent.mouseClicked(E);
        //JOptionPane.showMessageDialog(new JFrame(), "" + E.getPoint());
    }

    public void mousePressed(MouseEvent me)
    {
        pressed = me;
    }
 
    public void mouseDragged(MouseEvent me)
    {
        component = me.getComponent();
        location = component.getLocation(location);
        int x = location.x - pressed.getX() + me.getX();
        int y = location.y - pressed.getY() + me.getY();
        component.setLocation(x, y);
     }


	public void mouseEntered( MouseEvent E ) {
		if ( Parent != null ) Parent.mouseEntered();
	}

	public void mouseExited( MouseEvent E ) {
		if ( Parent != null ) Parent.mouseExited();
	}
    
    public void mouseMoved( MouseEvent E ) {
        if ( Parent != null ) Parent.mouseMoved(E);
    }

}
