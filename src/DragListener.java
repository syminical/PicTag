
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
}
