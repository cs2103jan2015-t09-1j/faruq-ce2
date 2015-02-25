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
	
	@Test
	public void testCommandSearch() {
		// setup environment
		UIStubImpl ui = new UIStubImpl();
		Data data = new DataStubImpl();
		
		// setup needed commands
		Command cmdAdd = new CommandAddImpl(ui, data);
		Command cmdDisplay = new CommandDisplayImpl(ui, data);
		Command cmdSearch = new CommandSearchImpl(ui, data);
		
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
		
		// perform a search
		ui.clearOutput();
		cmdSearch.processCommand("search hello");
		assertEquals(ui.getOutputAtLine(0), "1. Hello World");
		assertEquals(ui.getOutputAtLine(1), "2. Hello 2");
		
		ui.clearOutput();
		cmdSearch.processCommand("search world");
		assertEquals(ui.getOutputAtLine(0), "1. Hello World");
	}
	
	@Test
	public void testCommandSort() {
		// setup environment
		UIStubImpl ui = new UIStubImpl();
		Data data = new DataStubImpl();
		
		// setup needed commands
		Command cmdAdd = new CommandAddImpl(ui, data);
		Command cmdDisplay = new CommandDisplayImpl(ui, data);
		Command cmdSort = new CommandSortImpl(ui, data);
		
		// let the testing begin
		String input = "Zyx";
		
		cmdAdd.processCommand("add " + input);
		assertEquals(ui.getLastOutput(), "added to " + data.getStorageFileName() + ": \"" + input + "\"");
		
		cmdDisplay.processCommand("display");
		assertEquals(ui.getLastOutput(), "1. " + input);
		
		input = "Abc";
		
		cmdAdd.processCommand("add " + input);
		assertEquals(ui.getLastOutput(), "added to " + data.getStorageFileName() + ": \"" + input + "\"");
		
		cmdDisplay.processCommand("display");
		assertEquals(ui.getLastOutput(), "2. " + input);
		
		input = "123";
		
		cmdAdd.processCommand("add " + input);
		assertEquals(ui.getLastOutput(), "added to " + data.getStorageFileName() + ": \"" + input + "\"");
		
		cmdDisplay.processCommand("display");
		assertEquals(ui.getLastOutput(), "3. " + input);
		
		input = "Hijk";
		
		cmdAdd.processCommand("add " + input);
		assertEquals(ui.getLastOutput(), "added to " + data.getStorageFileName() + ": \"" + input + "\"");
		
		cmdDisplay.processCommand("display");
		assertEquals(ui.getLastOutput(), "4. " + input);
		
		input = "Defg";
		
		cmdAdd.processCommand("add " + input);
		assertEquals(ui.getLastOutput(), "added to " + data.getStorageFileName() + ": \"" + input + "\"");
		
		cmdDisplay.processCommand("display");
		assertEquals(ui.getLastOutput(), "5. " + input);
		
		// perform a sort
		cmdSort.processCommand("sort");
		ui.clearOutput();
		cmdDisplay.processCommand("display");
		
		assertEquals(ui.getOutputAtLine(0), "1. 123");
		assertEquals(ui.getOutputAtLine(1), "2. Abc");
		assertEquals(ui.getOutputAtLine(2), "3. Defg");
		assertEquals(ui.getOutputAtLine(3), "4. Hijk");
		assertEquals(ui.getOutputAtLine(4), "5. Zyx");
	}
}
