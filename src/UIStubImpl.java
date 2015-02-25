import java.util.ArrayList;

public class UIStubImpl implements UI {

	private String userInput;
	
	private ArrayList<String> output = new ArrayList<String>();
	
	@Override
	public void printPrompt(String msg) {
	}

	@Override
	public void printMessage(String msg) {
		output.add(msg);
	}

	@Override
	public void printError(String msg) {
		output.add(msg);
	}

	@Override
	public String getUserInput() {
		String input = userInput;
		userInput = "";
		return input;
	}
	
	public void setUserInput(String userInput) {
		this.userInput = userInput + "\n";
	}
	
	public String getLastOutput() {
		return output.get(output.size()-1);
	}
	
	public String getOutputAtLine(int i) {
		if (i >= output.size()) return null;
		return output.get(i);
	}
	
	public ArrayList<String> getOutput() {
		return output;
	}
	
	public void clearOutput() {
		output.clear();
	}

	@Override
	public void exit() {
	}

}
