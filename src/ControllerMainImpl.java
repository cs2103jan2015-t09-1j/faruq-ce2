import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ControllerMainImpl implements Controller {
	
	private UI ui;
	
	private Data data;
	
	public ControllerMainImpl() {
	}
	
	public void startController(String[] args) {
		// Initialize our UI
		ui = new UIMainImpl();
		
		// Get file from argument
		File storageFile = checkForAndInitArgument(args);
		
		// Check if we have the filename from the argument, quit if not
		if (storageFile == null) {
			ui.exit();
			return;
		}
		
		// Prompt to create file if it does not exist.
		// Exit program if file does not exist and user does not want to create it
		if (!checkIfFileExistAndCreateIfDoesNot(storageFile)) {
			ui.exit();
			return;
		}
		
		// Initialize our Data
		data = new DataMainImpl(storageFile);
		
		ui.printMessage("Welcome to TextBuddy. " + storageFile.getName() + " is ready for use.");
		
		// Start command selector
		// This is a blocking call, program will only exit if user issue exit command
		commandSelector();
	}
	
	public void commandSelector() {
		// Load all commands into the array
		Command[] commands = new Command[]{
				new CommandAddImpl(ui, data),
				new CommandDeleteImpl(ui, data),
				new CommandDisplayImpl(ui, data),
				new CommandClearImpl(ui, data),
				new CommandSearchImpl(ui, data),
				new CommandExitImpl(ui, data)
		};	
		
		String cmd = "";
		
		while (true) {
			ui.printPrompt("command");
			
			// Wait for command
			cmd = ui.getUserInput();
			
			// Loop through command list to find matching command and process if it is
			int i = 0;
			for (i = 0; i < commands.length; i++) {
				Command command = commands[i];
				if (command.processCommand(cmd)) {
					break;
				}
			}
			
			// If command is not found within the array, means it is invalid
			if (i == commands.length) ui.printError("Invalid command.");
		}
	}
	
	public File checkForAndInitArgument(String[] args) {
		// Print error if argument is not given
		if (args.length == 0) {
			ui.printError("Please specify a text file as an argument.");
			return null;
		}
		
		return new File(args[0]);
	}
	
	public boolean checkIfFileExistAndCreateIfDoesNot(File file) {
		if (!file.exists()) {
			try {
				Files.createFile(file.toPath());
			} catch (IOException e) {
				ui.printError("No permission to access file.");
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
}
