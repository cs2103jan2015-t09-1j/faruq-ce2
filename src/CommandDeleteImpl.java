
import java.io.IOException;

// Delete line from file
public class CommandDeleteImpl extends Command {
	
	private static int INVALID_LINE_NUMBER = -1;
	
	private static String commandStart = "delete ";
	
	public CommandDeleteImpl(UI ui, Data data) {
		super(ui, data);
	}

	@Override
	public boolean processCommand(String cmd) {
		if (!cmd.startsWith(commandStart)) return false;
		
		int lineToDelete = getLineNumberFromInput(cmd);
		// if cannot get line number to delete because of invalid entry, return false
		if (lineToDelete == INVALID_LINE_NUMBER) {
			return false;
		}
		
		try {
			// lineToDelete-1 because in ArrayList index starts from 0
			String line = data.deleteLineFromFile(lineToDelete-1);
			ui.printMessage("deleted from " + data.getStorageFileName() + ": \"" + line + "\"");
		} catch (Data.LineNotFoundException e) {
			ui.printMessage("No line " + lineToDelete + " found in " + data.getStorageFileName());
		} catch (IOException e) {
			ui.printError("Failed to read data file.");
			e.printStackTrace();
		}
		
		return true;
	}
	
	private int getLineNumberFromInput(String cmd) {
		try {
			return Integer.parseInt(cmd.substring(commandStart.length()));
		} catch (NumberFormatException e) {
			ui.printError("No line specified.");
			return INVALID_LINE_NUMBER;
		}
	}

}