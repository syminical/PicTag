
//theworldisquiethere

import javax.swing.TransferHandler;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.List;

public class DropHandler extends TransferHandler {

	PicList Pictures;

	public DropHandler( PicList Container ) { Pictures = Container; }

	@Override
	public boolean canImport( TransferHandler.TransferSupport support ) {

		for ( DataFlavor flavor : support.getDataFlavors() )

			if ( flavor.equals( DataFlavor.imageFlavor ) )

				return true;

		return false;

	}

	@Override
	public boolean importData( TransferHandler.TransferSupport support ) {

		if ( Pictures == null ) return false;

		if ( !this.canImport( support ) ) return false;

		List<File> Files;

		try {

			Files = ( List<File> ) support.getTransferable().getTransferData( DataFlavor.imageFlavor );

		} catch ( UnsupportedFlavorException | IOException ex ) { return false; }

		BufferedImage Temp = new BufferedImage(0, 0, 0);
		Pic Temp2 = new Pic();

		for ( File TempFile : Files ) {

			try {

				Temp = ImageIO.read( TempFile );

			} catch ( IOException e ) { }

			Temp2 = (Pic)Temp;

			Temp2.File( TempFile );

			Pictures.add( Temp2 );

		}

		return true;

	}

}
