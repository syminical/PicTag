
//theworldisquiethere

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Pic extends BufferedImage {

	private File FileHolder;
	private ArrayList<Tag> Tags;

	public Pic() { super(0, 0, 0); }

	public void File( File Container ) { FileHolder = Container; }

	public void addTag( Tag Container ) { Tags.add( Container ); }

	public File File() { return FileHolder; }

	public ArrayList<Tag> Tags() { return Tags; }

}
