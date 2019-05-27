import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public  class WindowAtlas extends JPanel {

	private WindowBox Parent; //This could maybe be better.
    private Dimension Size;
    private Image Asset;
    private BackgroundPanel BackgroundPane;
    
    //--= Constructor(s) =--
    //...I do not want to overload constructors...too much clutter.   (._ .)
    
	public WindowAtlas( WindowBox P, Dimension S, Image Im ) {
        
        super();
        
		Parent = P;
        Asset = Im;
        Size = S;

        //Background Image Panel
        BackgroundPane = new BackgroundPanel( Asset );
        BackgroundPane.setBackground( new Color( 0, 0, 0, 0 ) );
        BackgroundPane.setVisible( true );
        
        //Atlas settings
        this.setPreferredSize( Size );
        this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
        this.setBackground( new Color( 0, 0, 0, 0 ) );
        this.add( new AlphaContainer( BackgroundPane ) );
	}
    
    
    
    //--= public API =--
    
    //Apply the requested background to the window.
    public void changeBackground(Image I) { BackgroundPane.setImage(I); }
    
    public WindowBox Parent() { return Parent; }
}