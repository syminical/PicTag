
//theworldisquiethere

import javax.swing.TransferHandler;

public class DropHolder extends TransferHandler {

	@Override
	public boolean canImport(TransferHandler.TransferSupport support) {

		for (DataFlavor flavor : support.getDataFlavors())

			if (flavor.equals(DataFlavor.imageFlavor))

				return true;

	}

	@Override
	public boolean importData(TransferHandler.TransferSupport support) {

		if (!this.canImport(support)) return false;

		List<File> Files;

		try {

			files = (List<File>) support.getTransferable().getTranferData(DataFlavor.imageFlavor);

		} catch (UnsupportedFlavorException | IOException ex) { return false; }

		for (File file : Files) { }

	}

}
