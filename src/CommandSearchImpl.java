
import java.io.IOException;
import java.util.List;

// Search for "word" in storage and display lines with "word" if found.
// {lineNumber}. {line}
public class CommandSearchImpl extends Command {
	
	private static String commandStart = "search ";

	public CommandSearchImpl(UI ui, Data data) {
		super(ui, data);
	}
	
	@Override
	public boolean processCommand(String cmd) {
		if (!cmd.startsWith(commandStart)) return false;
		
		String word = cmd.substring(commandStart.length());
		
		if (word.trim().equals("")) {
			ui.printError("Please specify the word to search for.");
			return true;
		}
		
		// ensure we only take the first word found into account
		word = word.split(" ")[0].trim();
		
		// read data from storage, then perform a search
		try {
			List<String> lines = data.readFile();
			if (lines.size() == 0) {
				ui.printMessage(data.getStorageFileName() + " is empty");
			} else {
				searchForWordAndPrint(lines, word);
			}
		} catch (IOException e) {
			ui.printError("Failed to read data file.");
			e.printStackTrace();
		}
		
		return true;
	}
	
	private void searchForWordAndPrint(List<String> lines, String word) {
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).toLowerCase().contains(word.toLowerCase())) {
				ui.printMessage((i+1) + ". " + lines.get(i));
			}
		}
	}
}