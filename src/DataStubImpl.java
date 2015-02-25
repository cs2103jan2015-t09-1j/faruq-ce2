import java.io.IOException;
import java.util.ArrayList;

public class DataStubImpl implements Data {

	private static String FAKE_FILENAME = "test.txt";
	
	private ArrayList<String> lines = new ArrayList<String>();
	
	@Override
	public String getStorageFileName() {
		return FAKE_FILENAME;
	}

	@Override
	public ArrayList<String> readFile() throws IOException {
		return lines;
	}

	@Override
	public void appendFile(String line) throws IOException {
		lines.add(line);
	}

	@Override
	public String deleteLineFromFile(int lineToDelete) throws IOException {
		if (lines.size() >= lineToDelete) {
			String line = lines.remove(lineToDelete);
			return line;
		} else {
			throw new LineNotFoundException();
		}
	}

	@Override
	public void clearFile() throws IOException {
		lines.clear();
	}

}
