package syminical.pictag;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Pic {

	private File FileHolder;
	private ArrayList<Tag> Tags;
	private BufferedImage Picture;

	public Pic() { FileHolder = null; Tags = null; Picture = null; }

	public Pic( BufferedImage Container ) { Picture = Container; }

	public Pic( BufferedImage Container, File Container2 ) { Picture = Container; FileHolder = Container2; }

	public void File( File Container ) { FileHolder = Container; }

	public void addTag( Tag Container ) { Tags.add( Container ); }

	public File File() { return FileHolder; }

	public ArrayList<Tag> Tags() { return Tags; }

	public BufferedImage Picture() { return Picture; }

}
