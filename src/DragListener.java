
//I did not create this, but I did edit it a little.
//I got this code from https://tips4java.wordpress.com/2009/06/14/moving-windows/

import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class DragListener extends MouseInputAdapter
{
    Point location;
    MouseEvent pressed;
    Component component;
	PicTag INSTANCE;

	public void updateINSTANCE( PicTag Container ) { INSTANCE = Container; }

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


	public void mouseEntered( MouseEvent e ) {

		if ( INSTANCE != null ) INSTANCE.toggleExtras();
		
	}

	public void mouseExited( MouseEvent e ) {

		if ( INSTANCE != null ) INSTANCE.toggleExtras();

	}

}
