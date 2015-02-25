
import java.io.IOException;
import java.util.List;

// Sort the lines in storage
public class CommandSortImpl extends Command {
	
	private static String commandStart = "sort";

	public CommandSortImpl(UI ui, Data data) {
		super(ui, data);
	}
	
	@Override
	public boolean processCommand(String cmd) {
		if (!cmd.equals(commandStart)) return false;
		
		return true;
	}
	
}