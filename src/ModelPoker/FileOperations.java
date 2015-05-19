package ModelPoker;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOperations {

	private String filename;
	private File file;
	private FileReader fr;
	private String[] cards;

	// Constructor for class
	public FileOperations(String startFilename) throws IOException {
		// Create file object
		setFilename(startFilename);
		file = new File(filename);
	}
	
	// Open file and read content (cards) into a string array
	public String[] openFile() throws IOException {
		fr = new FileReader(file);

		char[] a = new char[(int) file.length()];
		fr.read(a); 
		cards = new String(a).split(" ");
		fr.close();
		return cards;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
