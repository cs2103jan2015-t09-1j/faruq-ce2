

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
import java.util.Scanner;

// CS2103T
// C01
// A0111795A
// Faruq Rasid
//
// To add a command, create a class implementing the command interface (i.e. CommandAddImpl.java).
// Then initialize it in Command[] commands array.
public class TextBuddy {
	// textFile stored as static because throughout the program there can only be one textFile
	public static File textFile;

	// Load all commands into the array
	private static Command[] commands = new Command[]{new CommandAddImpl(), new CommandDeleteImpl(), new CommandDisplayImpl(), new CommandClearImpl(), new CommandExitImpl()};
	
	// Exist as static so we can do regression
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		// Check if we have the filename as argument, quit if not
		if (!checkForAndInitArgument(args)) return;
		
		// Prompt to create file if it does not exist.
		// Exit program if file does not exist and user does not want to create it
		if (!checkIfFileExistAndCreateIfDoesNot()) return;
		
		Util.printMessage("Welcome to TextBuddy. " + textFile.getName() + " is ready for use.");
		
		// Start command selector
		commandSelector();
		
		input.close();
	}
	
	public static boolean checkForAndInitArgument(String[] args) {
		// Print error if argument is not given
		if (args.length == 0) {
			Util.printError("Please specify a text file as an argument.");
			return false;
		}
		textFile = new File(args[0]);
		
		return true;
	}
	
	public static boolean checkIfFileExistAndCreateIfDoesNot() {
		if (!textFile.exists()) {
			try {
				Files.createFile(textFile.toPath());
			} catch (IOException e) {
				Util.printError("No permission to access file.");
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	public static void commandSelector() {
		String cmd = "";
		
		while (true) {
			Util.printPrompt("command");
			
			// Wait for command
			cmd = input.nextLine();
			
			// Loop through command list to find matching command and process if it is
			int i = 0;
			for (i = 0; i < commands.length; i++) {
				Command command = commands[i];
				if (command.processCommand(cmd)) {
					break;
				}
			}
			
			// If command is not found within the array, means it is invalid
			if (i == commands.length) Util.printError("Invalid command.");
		}
	}
	
	// Command Interface
	public static interface Command {
		boolean processCommand(String cmd);
	}
	
	// Append file with the user supplied line
	// (refer to Util class for implementation)
	public static class CommandAddImpl implements Command {
		
		private static String commandStart = "add ";
		
		public CommandAddImpl() {
		}

		@Override
		public boolean processCommand(String cmd) {
			if (!cmd.startsWith(commandStart)) return false;
			
			String input = cmd.substring(commandStart.length());
			
			try {
				Util.appendFile(TextBuddy.textFile.toPath(), input);
				Util.printMessage("added to " + TextBuddy.textFile.getName() + ": \"" + input + "\"");
			} catch (IOException e) {
				Util.printError("Failed to write to file!");
				e.printStackTrace();
			}
			
			return true;
		}

	}

	// Clear file to empty state (refer to Util class)
	public static class CommandClearImpl implements Command {
		
		private static String commandStart = "clear";
		
		public CommandClearImpl() {
		}

		@Override
		public boolean processCommand(String cmd) {
			if (!cmd.equals(commandStart)) return false;
			
			try {
				Util.clearFile(TextBuddy.textFile.toPath());
				Util.printMessage("all content deleted from " + TextBuddy.textFile.getName());
			} catch (IOException e) {
				Util.printError("Failed to delete file contents.");
			}
			
			return true;
		}

	}
	
	// Delete line from file (refer to Util class for implementation)
	public static class CommandDeleteImpl implements Command {
		
		private static int INVALID_LINE_NUMBER = -1;
		
		private static String commandStart = "delete ";
		
		public CommandDeleteImpl() {
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
				String line = Util.deleteLineFromFile(TextBuddy.textFile.toPath(), lineToDelete-1);
				Util.printMessage("deleted from " + TextBuddy.textFile.getName() + ": \"" + line + "\"");
			} catch (Util.LineNotFoundException e) {
				Util.printMessage("No line " + lineToDelete + " found in " + TextBuddy.textFile.getName());
			} catch (IOException e) {
				Util.printError("Failed to read data file.");
				e.printStackTrace();
			}
			
			return true;
		}
		
		private int getLineNumberFromInput(String cmd) {
			try {
				return Integer.parseInt(cmd.substring(commandStart.length()));
			} catch (NumberFormatException e) {
				Util.printError("No line specified.");
				return INVALID_LINE_NUMBER;
			}
		}

	}
	
	// Display the data in the text file with the format
	// {lineNumber}. {line}
	public static class CommandDisplayImpl implements Command {
		
		private static String commandStart = "display";
		
		public CommandDisplayImpl() {
		}

		@Override
		public boolean processCommand(String cmd) {
			if (!cmd.equals(commandStart)) return false;
			
			try {
				List<String> data = Util.readFile(TextBuddy.textFile.toPath());
				if (data.size() == 0) {
					Util.printMessage(TextBuddy.textFile.getName() + " is empty");
				} else {
					displayLines(data);
				}
			} catch (IOException e) {
				Util.printError("Failed to read data file.");
				e.printStackTrace();
			}
			
			return true;
		}
		
		private void displayLines(List<String> data) {
			for (int i = 0; i < data.size(); i++) {
				Util.printMessage((i+1) + ". " + data.get(i));
			}
		}

	}
	
	// Exit program
	public static class CommandExitImpl implements Command {
		
		private static String commandStart = "exit";
		
		public CommandExitImpl() {
		}

		@Override
		public boolean processCommand(String cmd) {
			if (!cmd.equals(commandStart)) return false;
			
			System.exit(0);
			
			return true;
		}

	}
	
	// This class contains static methods which perform generic functions used by other classes
	public static class Util {

		// Custom Exceptions
		public static class LineNotFoundException extends IOException {
			
		}
		
		// Helper methods to print messages
		public static void printPrompt(String msg) {
			System.out.print(msg + ": ");
		}

		public static void printMessage(String msg) {
			System.out.println(msg);
		}

		public static void printError(String msg) {
			System.out.println("Error: " + msg);
		}
		
		// Helper methods to access file, used by Command implementations
		
		// Read file into an ArrayList
		public static ArrayList<String> readFile(Path file) throws IOException {
			ArrayList<String> data = new ArrayList<>();
			
			InputStream in = Files.newInputStream(file);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        data.add(line);
		    }
		    in.close();
		    
		    return data;
		}
		
		// Append to file by writing data in append mode
		public static void appendFile(Path file, String line) throws IOException {
			OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.WRITE, StandardOpenOption.APPEND));
			out.write((line + "\n").getBytes());
			out.flush();
			out.close();
		}
		
		// Delete specific line from the text file by rewriting
		// the whole file with the same contents with the exception of the line to be deleted
		public static String deleteLineFromFile(Path file, int lineToDelete) throws IOException {
			List<String> data = Util.readFile(file);
			if (lineToDelete >= data.size()) {
				throw new LineNotFoundException();
			} else {
				String line = data.get(lineToDelete);
				
				Util.clearFile(file);
				for (int i = 0; i < data.size(); i++) {
					if (i == lineToDelete) continue;
					
					Util.appendFile(file, data.get(i));
				}
				
				return line;
			}
		}
		
		// Write empty string into file in truncate mode to clear data
		public static void clearFile(Path file) throws IOException {
			OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING));
			out.write("".getBytes());
			out.flush();
			out.close();
		}

	}
}
