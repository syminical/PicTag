
//theworldisquiethere

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;

public class PicTag extends JFrame {

	private DragListener drag = new DragListener();
	private JPanel atlas = new JPanel();
	private PicList Pictures;

	public PicTag() {

		super( "PicTag" );

		prepareComponents();
		burden();
		buildAbox();
		animate();

	}

	private void loadAssests() { }

	//puts components together, sets their properties
	private void prepareComponents() {

		componentListeners();

	}

	private void componentListeners() { }

	//makes atlas carry everything
	private void burden() {

		atlas.setPreferredSize( new Dimension( 600, 390 ) );
		atlas.setLayout( new BoxLayout( atlas, BoxLayout.Y_AXIS ) );
		atlas.setBackground( new Color( 0, 0, 0, 240 ) );

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

	//main loop after setup, handles all animation
	private void animate() {

		double bg = 0;
		int c = 0, max = 20;
		boolean inc = true, bgw = false;

		while ( true ) {

			//background breath, cooler window
			if ( System.currentTimeMillis() - bg >= 66 ) {

				if (bgw) {

					if ( c > 0 ) c--;
					else { bgw = false; c = 0; inc = true; }

				} else {

					if ( c < max && inc ) c++; // :D
					else c--;

					atlas.setBackground( new Color( c, c, c, 240 ) );

					if ( c == max ) inc = false;
					else if ( c == 0 ) { bgw = true; c = ( 2 * max ); }

				}

				bg = System.currentTimeMillis();

			}

			//cpu saver
			try { Thread.sleep(10); } catch( Exception e ) { System.out.println( "*" );}

		}

	}

	public PicTag box() { return this; }

	public PicList Pictures() { return Pictures; }

	public static void main( String[] Container ) {

		new PicTag();

	}

}
