
import java.io.IOException;
import java.util.List;

// Display the data in the text file with the format
// {lineNumber}. {line}
public class CommandDisplayImpl extends Command {
	
	private static String commandStart = "display";

	public CommandDisplayImpl(UI ui, Data data) {
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
				displayLines(lines);
			}
		} catch (IOException e) {
			ui.printError("Failed to read data file.");
			e.printStackTrace();
		}
		
		return true;
	}
	
	private void displayLines(List<String> lines) {
		for (int i = 0; i < lines.size(); i++) {
			ui.printMessage((i+1) + ". " + lines.get(i));
		}
	}

}