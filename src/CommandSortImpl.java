
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Sort the lines in storage
public class CommandSortImpl extends Command {
	
	private static String commandStart = "sort";

	public CommandSortImpl(UI ui, Data data) {
		super(ui, data);
	}
	
	@Override
	public boolean processCommand(String cmd) {
		if (!cmd.equals(commandStart)) return false;
		
		try {
			List<String> lines = data.readFile();
			if (lines.size() == 0) {
				ui.printMessage(data.getStorageFileName() + " is empty");
			} else {
				ArrayList<String> sortedLines = sort(lines);
				data.clearFile();
				for (String line : sortedLines) {
					data.appendFile(line);
				}
			}
		} catch (IOException e) {
			ui.printError("Failed to read data file.");
			e.printStackTrace();
		}
		
		return true;
	}
	
	private ArrayList<String> sort(List<String> lines) {
		ArrayList<String> sortedLines = new ArrayList<String>();
		sortedLines.addAll(lines);
		Collections.sort(sortedLines);
		
		return sortedLines;
	}
	
}