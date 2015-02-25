import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTextBuddyAtd {

	private void addLineAndAssert(UIStubImpl ui, Data data, int index, String line) {
		Command cmdAdd = new CommandAddImpl(ui, data);
		Command cmdDisplay = new CommandDisplayImpl(ui, data);
		
		cmdAdd.processCommand("add " + line);
		assertEquals(ui.getLastOutput(), "added to " + data.getStorageFileName() + ": \"" + line + "\"");
		
		cmdDisplay.processCommand("display");
		assertEquals(ui.getLastOutput(), index + ". " + line);
	}
	
	@Test
	public void testCommandAdd() {
		// setup environment
		UIStubImpl ui = new UIStubImpl();
		Data data = new DataStubImpl();
		
		// setup needed commands
		Command cmdDisplay = new CommandDisplayImpl(ui, data);
		
		// let the testing begin
		addLineAndAssert(ui, data, 1, "Hello World");
		addLineAndAssert(ui, data, 2, "Hello 2");
		addLineAndAssert(ui, data, 3, "Hello 3");
	}
	
	@Test
	public void testCommandSearch() {
		// setup environment
		UIStubImpl ui = new UIStubImpl();
		Data data = new DataStubImpl();
		
		// setup needed commands
		Command cmdDisplay = new CommandDisplayImpl(ui, data);
		Command cmdSearch = new CommandSearchImpl(ui, data);
		
		// let the testing begin
		addLineAndAssert(ui, data, 1, "Hello World");
		addLineAndAssert(ui, data, 2, "Hello 2");
		
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
		Command cmdDisplay = new CommandDisplayImpl(ui, data);
		Command cmdSort = new CommandSortImpl(ui, data);
		
		addLineAndAssert(ui, data, 1, "Zyx");
		addLineAndAssert(ui, data, 2, "abc");
		addLineAndAssert(ui, data, 3, "123");
		addLineAndAssert(ui, data, 4, "Hijk");
		addLineAndAssert(ui, data, 5, "Defg");
		
		// perform a sort
		cmdSort.processCommand("sort");
		ui.clearOutput();
		cmdDisplay.processCommand("display");
		
		assertEquals(ui.getOutputAtLine(0), "1. 123");
		assertEquals(ui.getOutputAtLine(1), "2. abc");
		assertEquals(ui.getOutputAtLine(2), "3. Defg");
		assertEquals(ui.getOutputAtLine(3), "4. Hijk");
		assertEquals(ui.getOutputAtLine(4), "5. Zyx");
	}
}
