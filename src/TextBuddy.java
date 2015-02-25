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
