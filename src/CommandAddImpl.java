import java.io.IOException;

// Append file with the user supplied line
public class CommandAddImpl extends Command {
	
	private static String commandStart = "add ";
	
	public CommandAddImpl(UI ui, Data data) {
		super(ui, data);
	}
	
	@Override
	public boolean processCommand(String cmd) {
		if (!cmd.startsWith(commandStart)) return false;
		
		String input = cmd.substring(commandStart.length());
		
		try {
			data.appendFile(input);
			ui.printMessage("added to " + data.getStorageFileName() + ": \"" + input + "\"");
		} catch (IOException e) {
			ui.printError("Failed to write to file!");
			e.printStackTrace();
		}
		
		return true;
	}

}