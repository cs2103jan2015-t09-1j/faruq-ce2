
// Command Interface
public abstract class Command {
	
	protected UI ui;
	
	protected Data data;
	
	public Command(UI ui, Data data) {
		this.ui = ui;
		this.data = data;
	}
	
	public abstract boolean processCommand(String cmd);
}
