
import java.io.IOException;
import java.util.List;

// Search for "word" in storage and display lines with "word" if found.
// {lineNumber}. {line}
public class CommandSearchImpl extends Command {
	
	private static String commandStart = "search ";

	public CommandSearchImpl(UI ui, Data data) {
		super(ui, data);
	}
	
	@Override
	public boolean processCommand(String cmd) {
		if (!cmd.startsWith(commandStart)) return false;
		
		return true;
	}
}