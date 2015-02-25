
import java.io.IOException;

// Clear file to empty state
public class CommandClearImpl extends Command {
	
	private static String commandStart = "clear";
	
	public CommandClearImpl(UI ui, Data data) {
		super(ui, data);
	}

	@Override
	public boolean processCommand(String cmd) {
		if (!cmd.equals(commandStart)) return false;
		
		try {
			data.clearFile();
			ui.printMessage("all content deleted from " + data.getStorageFileName());
		} catch (IOException e) {
			ui.printError("Failed to delete file contents.");
		}
		
		return true;
	}

}