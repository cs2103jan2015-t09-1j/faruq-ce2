// CS2103T
// C01
// A0111795A
// Faruq Rasid
//
// According to user input, add, clear, delete, display data to/from file.
//
// Summarized Architecture:
// 		User -> UI -> Logic (Controller + Command + Data) -> Storage
//
// Code-wise the flow is: main() -> Controller/Command -> UI / Data
//
// TLDR; Controller (including Command class) controls the flow of the program.
//
public class TextBuddy {
	public static void main(String[] args) {
		// Hand over to our controller
		new ControllerMainImpl().startController(args);;
	}
}
