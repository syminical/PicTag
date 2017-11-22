
//theworldisquiethere

import javax.swing.TransferHandler;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.List;

public class DropHandler extends TransferHandler {

	@Override
	public boolean canImport(TransferHandler.TransferSupport support) {

		for (DataFlavor flavor : support.getDataFlavors())

			if (flavor.equals(DataFlavor.imageFlavor))

				return true;

		return false;

	}

	@Override
	public boolean importData(TransferHandler.TransferSupport support) {

		if (!this.canImport(support)) return false;

		List<File> Files;

		try {

			Files = (List<File>) support.getTransferable().getTransferData(DataFlavor.imageFlavor);

		} catch (UnsupportedFlavorException | IOException ex) { return false; }

		for (File file : Files) { }

		return true;

	}

}
