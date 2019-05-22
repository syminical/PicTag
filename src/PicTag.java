
//theworldisquiethere

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.Ellipse2D;

public class PicTag extends JFrame {

private DragListener Drag;
private PicList Pictures;
private final Dimension MAIN_SIZE = new Dimension( 300, 300 );
private final Dimension SETTINGS_SIZE = new Dimension( 600, 600 );
private Pic Plus;
private BackgroundPanel MainPanel, SettingsPanel, PicturePanel;
private JFileChooser Chooser;
private Image MBG1, MBG2, MBG3, MBG4, MBG5, SBG1;
private int lastImg;
	private static PicTag INSTANCE;
	private JPanel Atlas = new JPanel();
   	private ArrayList<Zone> Zones;
    	private KeyAction KA1, KA2, KA3;

	public PicTag() {

		super( "PicTag" );

		INSTANCE = this;

		prepareComponents();
		burdenAtlas();
		buildAbox();

	}

	//puts components together, sets their properties
	private void prepareComponents() {
        
        Drag = new DragListener();
        Zones = new ArrayList<Zone>();
        
        Zones.add(new Zone(new Ellipse2D.Double(135, 190, 21, 19)) {
            @Override
            public void trigger() { PicTag.INSTANCE().changeBackground(4); }
        });
        Zones.add(new Zone(new Rectangle(113, 104, 68, 87)) {
            @Override
            public void trigger() { PicTag.INSTANCE().changeBackground(3); }
        });
	Zones.add(new Zone(new Ellipse2D.Double(182, 46, 45, 45)) {
	    @Override
	    public void trigger() { PicTag.INSTANCE().changeBackground(5); }
	});
        
        KA1 = new KeyAction() {
            public void trigger() {
                //PicTag.INSTANCE().PicMan().addTag("[1]");   
            }
        };

		try {

			MBG1 = ImageIO.read( new File( "img/mbg1.png" ) );
			MBG2 = ImageIO.read( new File( "img/mbg2.png" ) );
			MBG3 = ImageIO.read( new File( "img/mbg3.png" ) );
			MBG4 = ImageIO.read( new File( "img/mbg4.png" ) );
			MBG5 = ImageIO.read( new File( "img/mbg5.png" ) );
			SBG1 = ImageIO.read( new File( "img/sbg1.png" ) );

			MainPanel = new BackgroundPanel( MBG1 );
				MainPanel.setBackground( new Color( 0, 0, 0, 0 ) );
				MainPanel.setVisible(true);
		} catch (Exception e) { System.out.println( "asset error" ); }

		Chooser = new JFileChooser();
			Chooser.setFileFilter(new FileNameExtensionFilter("jpg, jpeg, gif, png", "jpg", "jpeg", "gif", "png"));

	}

	//makes Atlas carry everything
	private void burdenAtlas() {

		Atlas.setPreferredSize( new Dimension( MAIN_SIZE ) );
		Atlas.setLayout( new BoxLayout( Atlas, BoxLayout.Y_AXIS ) );
		Atlas.setBackground( new Color( 0, 0, 0, 0 ) );

		Atlas.add( new AlphaContainer( MainPanel ) );
	}

	//JFrame setup
	private void buildAbox() {

		this.addMouseListener( Drag );
		this.addMouseMotionListener( Drag );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setResizable( false );
		this.setUndecorated( true );
		this.setBackground( new Color( 0, 0, 0, 0 ) );
	this.add( new AlphaContainer( Atlas ));
	this.pack();
	this.setLocationRelativeTo( null );
    this.setVisible( true );

	}
    
    	private void fileChosen() {
        	File Ftemp = Chooser.getSelectedFile();
       	 	File Ftemp2 = new File("" + Ftemp.getParent() + Ftemp.separator + "testName.png");

      	 	if (!Ftemp2.exists())
            		Ftemp.renameTo(Ftemp2);
        	else
           	 	JOptionPane.showMessageDialog(new JFrame(), "That file already exists!");
    	}
    
	public void clicked() {
		switch (lastImg) {
			case 3: 
				if (Chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
                			fileChosen(); 
				break;
			case 4:
				//options
				changeBackground(6);
				break;
			case 5:
				terminateProgram();
			default:
		}
	}

	private void terminateProgram() {
		//main frame
		setVisible(false);
		dispose();
        system.exit();
	}
    
    	public void checkZones(MouseEvent me) {
           
        	for (Zone temp : Zones) 
            		if (temp.Shape().contains(me.getPoint())) {
                		temp.trigger();
                		return;
            		}
        	changeBackground(2);
    	}

	public void changeBackground(int container) {

		if (lastImg == container) return;
		else lastImg = container;

		switch (container) {
			case 2:
				MainPanel.setImage(MBG2);
				break;
			case 3:
				MainPanel.setImage(MBG3);
				break;
			case 4:
				MainPanel.setImage(MBG4);
				break;
			case 5:
				MainPanel.setImage(MBG5);
				break;
			case 6:
				Atlas.setPreferredSize( new Dimension( MAIN_SIZE ) );
				MainPanel.setImage(SBG1);
				break;
			default:
				Atlas.setPreferredSize( new Dimension( MAIN_SIZE ) );
				MainPanel.setImage(MBG1);
		}
	}

	public static PicTag INSTANCE() { return INSTANCE; }

	public PicList Pictures() { return Pictures; }

	public static void main( String[] Container ) { new PicTag(); }

}



public class PicTag {

	private static PicTag INSTANCE;
	private WindowBox MainBox, SettingsBox, InteractiveBox, DevBox;
	private final Dimension MAIN_BOX_SIZE = new Dimension( 300, 300 ), SETTINGS_BOX_SIZE = new Dimension( 600, 600 ), INTERACTIVE_BOX_SIZE = new Dimension( 1000, 1000 );

	public PicTag() {
		if 
            !loadAssets() JOptionPane.showMessageDialog(new JFrame(), "Failed to load Assets!");
		else {
            createWindows();
            addWindowListeners();
        }
	}

	private boolean loadAssets() {

	}

	private void createWindows() {
		MainBox = new WindowBox(INSTANCE, MAIN_WINDOW_SIZE);
		SettingsBox = new WindowBox(INSTANCE, SETTINGS_BOX_SIZE);
		InteractiveBox = new WindowBox(INSTANCE, INTERACTIVE_BOX_SIZE);
		DevBox = new WindowBox(INSTANCE, SETTINGS_BOX_SIZE);
	}

	private void addWindowListeners() {

	}
	public static void main(String[] Args) {
		INSTANCE = new PicTag();
	}
}