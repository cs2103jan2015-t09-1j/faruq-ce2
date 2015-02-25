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
		
		cmdAdd.processCommand("add Hello World");
		cmdDisplay.processCommand("display");
		
		assertEquals(ui.getLastOutput(), "1. Hello World");
	}
}
