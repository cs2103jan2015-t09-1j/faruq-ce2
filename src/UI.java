
public interface UI {
	
	public void printPrompt(String msg);

	public void printMessage(String msg);

	public void printError(String msg);
	
	public String getUserInput();
	
	public void exit();
	
}
