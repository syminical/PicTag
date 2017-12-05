
//theworldisquiethere

import javax.swing.*;
import javax.swing.event.*;
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
	private JPanel atlas = new JPanel();
	private PicList Pictures;
	private final Dimension plusLength = new Dimension( 100, 100 );
	private Pic Plus;
	private BackgroundPanel AddFrame;
	private BackgroundPanel ExtrasFrame;
	private BackgroundPanel PictureFrame;
	private Color UserColor = new Color( 0, 0, 255, 255 );

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

			AddFrame = new BackgroundPanel( ImageIO.read( new File( "plus2.png" ) ) );
			ExtrasFrame = new BackgroundPanel( ImageIO.read( new File( "plus.png" ) ) );

		} catch (Exception e) { System.out.println( "asset error" ); }

		AddFrame.setBackground( UserColor );
		AddFrame.add( new AlphaContainer( ExtrasFrame ) );
			ExtrasFrame.setBackground( new Color( 0, 0, 0, 0 ) );
		componentListeners();

	}

	private void componentListeners() { }

	//makes atlas carry everything
	private void burdenAtlas() {

		atlas.setPreferredSize( new Dimension( plusLength ) );
		atlas.setLayout( new BoxLayout( atlas, BoxLayout.Y_AXIS ) );
		atlas.setBackground( new Color( 0, 0, 0, 0 ) );

		atlas.add( new AlphaContainer( AddFrame ) );
	}

	//JFrame setup
	private void buildAbox() {

		this.addMouseListener( drag );
		this.addMouseMotionListener( drag );

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setResizable( false );
		this.setUndecorated( true );
		this.setBackground( new Color( 0, 0, 0, 0 ) );
		this.add( new AlphaContainer( atlas ));
		this.pack();
		this.setLocationRelativeTo( null );
		this.setVisible( true );

	}

	public void toggleExtras() { ExtrasFrame.setVisible( !ExtrasFrame.isVisible() ); }

	public void changeColor( Color Container ) { AddFrame.setBackground( Container ); } //USERCOLOR

	public static PicTag INSTANCE() { return INSTANCE; }

	public PicList Pictures() { return Pictures; }

	public static void main( String[] Container ) { new PicTag(); }

}

