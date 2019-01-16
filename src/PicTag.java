
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

public class PicTag extends JFrame {

	private static PicTag INSTANCE;
	private DragListener drag = new DragListener();
	private JPanel Atlas = new JPanel();
	private PicList Pictures;
	private final Dimension plusLength = new Dimension( 300, 300 );
	private Pic Plus;
	private BackgroundPanel MainPanel, PicturePanel;
	private JFileChooser chooser;
	private Image BG1, BG2, BG3, BG4;
	private int lastImg;

	public PicTag() {

		super( "PicTag" );

		INSTANCE = this;
		drag.updateINSTANCE( INSTANCE );

		prepareComponents();
		burdenAtlas();
		buildAbox();

	}

	//puts components together, sets their properties
	private void prepareComponents() {

		try {

			BG1 = ImageIO.read( new File( "img/test.png" ) );
			BG2 = ImageIO.read( new File( "img/test2.png" ) );
			BG3 = ImageIO.read( new File( "img/test3.png" ) );
			BG4 = ImageIO.read( new File( "img/test4.png" ) );

			MainPanel = new BackgroundPanel( BG1 );
				MainPanel.setBackground( new Color( 0, 0, 0, 0 ) );
				MainPanel.setVisible(true);
		} catch (Exception e) { System.out.println( "asset error" ); }

		chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("jpg, jpeg, gif, png", "jpg", "jpeg", "gif", "png"));

	}

	//makes Atlas carry everything
	private void burdenAtlas() {

		Atlas.setPreferredSize( new Dimension( plusLength ) );
		Atlas.setLayout( new BoxLayout( Atlas, BoxLayout.Y_AXIS ) );
		Atlas.setBackground( new Color( 0, 0, 0, 0 ) );

		Atlas.add( new AlphaContainer( MainPanel ) );
	}

	//JFrame setup
	private void buildAbox() {

		this.addMouseListener( drag );
		this.addMouseMotionListener( drag );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setResizable( false );
		this.setUndecorated( true );
		this.setBackground( new Color( 0, 0, 0, 0 ) );
		this.add( new AlphaContainer( Atlas ));
		this.pack();
		this.setLocationRelativeTo( null );
		this.setVisible( true );

	}

	public void clicked() {

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) MainPanel.setBackground( new Color( 0, 255, 0 ) );

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

