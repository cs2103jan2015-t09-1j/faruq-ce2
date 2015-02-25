// Exit program
public class CommandExitImpl extends Command {
	
	private static String commandStart = "exit";
	
	public CommandExitImpl(UI ui, Data data) {
		super(ui, data);
	}

	@Override
	public boolean processCommand(String cmd) {
		if (!cmd.equals(commandStart)) return false;
		
		System.exit(0);
		
		return true;
	}

}