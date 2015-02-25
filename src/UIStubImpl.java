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
	
	public ArrayList<String> getOutput() {
		return output;
	}

	@Override
	public void exit() {
	}

}
