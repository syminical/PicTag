
//theworldisquiethere

import java.io.File;

public class Tag {

	private String Name;
	private File Folder;

	public Tag(String Container) { Name = Container; }

	public Tag(String Container, File Container2) { Name = Container; Folder = Container2; }

	public void Folder(File Container) { Folder = Container; }

	public File Folder() { return Folder; }

}

