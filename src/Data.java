import java.io.IOException;
import java.util.ArrayList;

public interface Data {
	
	// Custom Exceptions
	public static class LineNotFoundException extends IOException{};
	
	// Get filename of storageFile
	public String getStorageFileName();
	
	// Read file into an ArrayList
	public ArrayList<String> readFile() throws IOException;
	
	// Append to file by writing data in append mode
	public void appendFile(String line) throws IOException;
	
	// Delete specific line from the text file by rewriting
	// the whole file with the same contents with the exception of the line to be deleted
	public String deleteLineFromFile(int lineToDelete) throws IOException;
	
	// Write empty string into file in truncate mode to clear data
	public void clearFile() throws IOException;
}
