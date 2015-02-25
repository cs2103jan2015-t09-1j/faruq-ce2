import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class DataMainImpl implements Data {
	
	private File storageFile;
	
	public DataMainImpl(File storageFile) {
		this.storageFile = storageFile;
	}
	
	public String getStorageFileName() {
		return storageFile.getName();
	}
	
	public ArrayList<String> readFile() throws IOException {
		ArrayList<String> data = new ArrayList<>();
		
		InputStream in = Files.newInputStream(storageFile.toPath());
	    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	        data.add(line);
	    }
	    in.close();
	    
	    return data;
	}
	
	public void appendFile(String line) throws IOException {
		OutputStream out = new BufferedOutputStream(Files.newOutputStream(storageFile.toPath(), StandardOpenOption.WRITE, StandardOpenOption.APPEND));
		out.write((line + "\n").getBytes());
		out.flush();
		out.close();
	}
	
	public String deleteLineFromFile(int lineToDelete) throws IOException {
		List<String> data = readFile();
		if (lineToDelete >= data.size()) {
			throw new LineNotFoundException();
		} else {
			String line = data.get(lineToDelete);
			
			clearFile();
			for (int i = 0; i < data.size(); i++) {
				if (i == lineToDelete) continue;
				
				appendFile(data.get(i));
			}
			
			return line;
		}
	}
	
	public void clearFile() throws IOException {
		OutputStream out = new BufferedOutputStream(Files.newOutputStream(storageFile.toPath(), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING));
		out.write("".getBytes());
		out.flush();
		out.close();
	}
}
