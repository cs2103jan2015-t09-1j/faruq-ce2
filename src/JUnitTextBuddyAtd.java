import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTextBuddyAtd {

	@Test
	public void testCommandAdd() {
		// setup environment
		UIStubImpl ui = new UIStubImpl();
		Data data = new DataStubImpl();
		
		// setup needed commands
		Command cmdAdd = new CommandAddImpl(ui, data);
		Command cmdDisplay = new CommandDisplayImpl(ui, data);
		
		// let the testing begin
		String input = "Hello World";
		
		cmdAdd.processCommand("add " + input);
		assertEquals(ui.getLastOutput(), "added to " + data.getStorageFileName() + ": \"" + input + "\"");
		
		cmdDisplay.processCommand("display");
		assertEquals(ui.getLastOutput(), "1. " + input);
		
		input = "Hello 2";
		
		cmdAdd.processCommand("add " + input);
		assertEquals(ui.getLastOutput(), "added to " + data.getStorageFileName() + ": \"" + input + "\"");
		
		cmdDisplay.processCommand("display");
		assertEquals(ui.getLastOutput(), "2. " + input);
		
		input = "Hello 3";
		
		cmdAdd.processCommand("add " + input);
		assertEquals(ui.getLastOutput(), "added to " + data.getStorageFileName() + ": \"" + input + "\"");
		
		cmdDisplay.processCommand("display");
		assertEquals(ui.getLastOutput(), "3. " + input);
	}
}
