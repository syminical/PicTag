
import javax.swing.*;
import java.util.ArrayList;

public abstract class WindowBox extends JFrame {

	private final Object INSTANCE; //This could be a generic type instead of Object.
	private WindowAtlas WindowMap;
	private ArrayList<Zone> Zones;
	private KeyAction KA1, KA2, KA3;
    private Image[] Assets;
    private int closeOp, lastImage;
    private Component Anchor;
    
    //--= Constructor(s) =--
    //...I do not want to overload constructors...too much clutter.   (._ .)
    
	public WindowBox(Object I, String N, Image[] As, Component An) {
        
        super(N);
        
		INSTANCE = I;
        SIZE = S;
        Assets = As;
        Anchor = An;
        
        burdenAtlas();
        buildBox();
        this.add(new AlphaContainer(WindowMap));
        this.pack();
        this.setLocationRelativeTo(Anchor);
        if (Anchor.equals(null))
            this.setVisible(true);
	}
    
    
    
    //--= Abstract Customization Methods =--
    
    //Customize content pane.
    private abstract void burdenAtlas();
    
    //Customize JFrame.
    private abstract void buildBox();
    
    //Customize click event responses, based on zone clicked.
    private abstract void clicked(int zne);
    
    
    
    //--= public API =--
    
    public void toggleVisiblity() { this.setVisible(!this.isVisible()); }
    
    //Apply the requested background to the window.
    public void changeBackground(int b) {
        
        if (b < 0 || b >= Assets.size)
            b = 0;
        
        if (b == lastImage) return;
        else lastImg = b;
        
        this.WindowMap.setBackground(Assets[b]);
    }
    
    //Check if ME happened in an assigned clickable zone.
    public void checkZones(MouseEvent me) {
        //move to listeners?
        for (Zone temp : Zones)
            if (temp.Shape().contains(me.getPoint())) {
                temp.trigger();
                return;
            }
        changeBackground(0);
    }
}