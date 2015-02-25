import java.util.Scanner;

public class UIMainImpl implements UI {
	
	private Scanner input;
	
	public UIMainImpl() {
		input = new Scanner(System.in);
	}
	
	@Override
	public void printPrompt(String msg) {
		System.out.print(msg + ": ");
	}

	@Override
	public void printMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	public void printError(String msg) {
		System.out.println("Error: " + msg);
	}

	@Override
	public String getUserInput() {
		return input.nextLine();
	}

	@Override
	public void exit() {
		input.close();
	}
	
}
