
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

	private static PicTag INSTANCE;
	private DragListener Drag;
	private JPanel Atlas = new JPanel();
	private PicList Pictures;
	private final Dimension PLUSLENGTH = new Dimension( 300, 300 );
	private Pic Plus;
	private BackgroundPanel MainPanel, PicturePanel;
	private JFileChooser Chooser;
	private Image BG1, BG2, BG3, BG4;
	private int lastImg;
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
        
        KA1 = new KeyAction() {
            public void trigger() {
                //PicTag.INSTANCE().PicMan().addTag("[1]");   
            }
        };

		try {

			BG1 = ImageIO.read( new File( "img/bg1.png" ) );
			BG2 = ImageIO.read( new File( "img/bg2.png" ) );
			BG3 = ImageIO.read( new File( "img/bg3.png" ) );
			BG4 = ImageIO.read( new File( "img/bg4.png" ) );

			MainPanel = new BackgroundPanel( BG1 );
				MainPanel.setBackground( new Color( 0, 0, 0, 0 ) );
				MainPanel.setVisible(true);
		} catch (Exception e) { System.out.println( "asset error" ); }

		Chooser = new JFileChooser();
			Chooser.setFileFilter(new FileNameExtensionFilter("jpg, jpeg, gif, png", "jpg", "jpeg", "gif", "png"));

	}

	//makes Atlas carry everything
	private void burdenAtlas() {

		Atlas.setPreferredSize( new Dimension( PLUSLENGTH ) );
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

		if (Chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
                fileChosen(); 

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
				MainPanel.setImage(BG2);
				break;
			case 3:
				MainPanel.setImage(BG3);
				break;
			case 4:
				MainPanel.setImage(BG4);
				break;
			default:
				MainPanel.setImage(BG1);
		}
	}

	public static PicTag INSTANCE() { return INSTANCE; }

	public PicList Pictures() { return Pictures; }

	public static void main( String[] Container ) { new PicTag(); }

}

