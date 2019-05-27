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

public class PicTag {

	private static PicTag INSTANCE;
	private WindowBox MainBox, SettingsBox, InteractiveBox, DevBox;
	private final Dimension MAIN_BOX_SIZE = new Dimension( 300, 300 ), SETTINGS_BOX_SIZE = new Dimension( 600, 600 ), INTERACTIVE_BOX_SIZE = new Dimension( 1000, 1000 );
    private Image[] MainAssets, SettingsAssets, InteractiveAssets, DevAssets;
    private JFileChooser Chooser;
    
	public PicTag() {
		if (!loadAssets()) {
            if (JOptionPane.showOptionDialog(null, "Failed to load assets.", "Warning!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"OK"}, "OK") == JOptionPane.OK_OPTION)
                System.exit(-1);
        }
		else {
            Chooser = new JFileChooser();
                Chooser.setFileFilter(new FileNameExtensionFilter("jpg, jpeg, gif, png", "jpg", "jpeg", "gif", "png"));
            
            createWindows();
        }
	}

	private boolean loadAssets() {
        
        MainAssets = new Image[5];
        SettingsAssets = new Image[1];
        InteractiveAssets = new Image[1];
        DevAssets = new Image[1];
        
        try {
			MainAssets[0] = ImageIO.read( new File( "img/main/0.png" ) );
			MainAssets[1] = ImageIO.read( new File( "img/main/1.png" ) );
			MainAssets[2] = ImageIO.read( new File( "img/main/2.png" ) );
			MainAssets[3] = ImageIO.read( new File( "img/main/3.png" ) );
			MainAssets[4] = ImageIO.read( new File( "img/main/4.png" ) );
			SettingsAssets[0] = ImageIO.read( new File( "img/settings/0.png" ) );
		} catch (Exception e) { return false; }
        
        return true;
	}

	private void createWindows() {
        //Start view, 5 backgrounds, 3 buttons.
		MainBox = new WindowBox(this, "PicTag", MAIN_BOX_SIZE, MainAssets, null) {  
            public void buildBox() {
                //window mouse event zones  
                //settings zone
                Zones().add(new Zone(this, new Ellipse2D.Double(135, 190, 21, 19)) {
                    @Override
                    public void entered() { Parent().changeBackground(3); }
                    
                    @Override
                    public void clicked() { Parent().tell(0); }
                });
                //files zone
                Zones().add(new Zone(this, new Rectangle(113, 104, 68, 87)) {
                    @Override
                    public void entered() { Parent().changeBackground(2); }
                    
                    @Override
                    public void clicked() { Parent().tell(1); }
                });
                //close
                Zones().add(new Zone(this, new Ellipse2D.Double(182, 46, 45, 45)) {
                    @Override
                    public void entered() { Parent().changeBackground(4); }
                    
                    @Override
                    public void clicked() { Parent().tell(2); }
                });
                
                //window customization
                this.addMouseListener( Drag() );
                this.addMouseMotionListener( Drag() );
                this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                this.setResizable( false );
                this.setUndecorated( true );
                this.setBackground( new Color( 0, 0, 0, 0 ) );
            }
            
            //Object Messaging, to trigger custom 'methods'.  
            public void tell(int n) {
                if ( Parent() == null ) return;
                switch (n) {
                    case 0:
                        Parent().startSettings();
                        break;
                    case 1:
                        Parent().pickFiles();
                        break;
                    case 2:
                        Parent().terminateProgram();
                    default:
                }
            }
            
            //Set to active background if no zone hit.
            @Override
            public void defaultMouseMovedAction() {
                changeBackground(1);
            }
        };
        
		SettingsBox = new WindowBox(this, "PicTag Settings", SETTINGS_BOX_SIZE, SettingsAssets, MainBox) {
            public void buildBox() {
                //window customization
                this.addMouseListener( Drag() );
                this.addMouseMotionListener( Drag() );
                this.setDefaultCloseOperation( WindowConstants.DO_NOTHING_ON_CLOSE );
                this.setResizable( false );
                this.setUndecorated( true );
                this.setBackground( new Color( 0, 0, 0, 0 ) );
            }
            
            public void tell(int n) { }
        };
        /*
		InteractiveBox = new WindowBox(this, "PicTag Viewer", INTERACTIVE_BOX_SIZE, InteractiveAssets, MainBox) {
            @Override
            private void buildBox() {
                
            }
            
            @Override
            private void clicked(int zne) {
                
            }
        };
        
		DevBox = new WindowBox(this, "PicTag Dev View", SETTINGS_BOX_SIZE, DevAssets, MainBox) {
            @Override
            private void buildBox() {
                
            }
            
            @Override
            private void clicked(int zne) {
                
            }
        };*/
	}
    
    //--= Helper Methods =--
    
    private void terminateProgram() {
		//main frame
		MainBox.setVisible(false);
        SettingsBox.setVisible(false);
		MainBox.dispose();
        SettingsBox.dispose();
        System.exit(0);
	}
        
    public void pickFiles() {
        if (Chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
            JOptionPane.showMessageDialog(new JFrame(), "File(s) successfully chosen~! :]");
    }
    
    public void startSettings() {
        //JOptionPane.showMessageDialog(new JFrame(), "PRETEND THIS IS A SETTINGS BOX! :D");
        SettingsBox.setLocationRelativeTo(SettingsBox.Anchor());
        MainBox.setVisible(false);
        SettingsBox.setVisible(true);
    }
    
	public static void main(String[] Args) {
        if (INSTANCE == null)
            INSTANCE = new PicTag();
	}
}

//theworldisquiethere

/*
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
*/
