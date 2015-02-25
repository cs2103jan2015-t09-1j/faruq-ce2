import java.io.File;

public interface Controller {
	
	public void startController(String[] args);
	
	public void commandSelector();
	
	public File checkForAndInitArgument(String[] args);
	
	public boolean checkIfFileExistAndCreateIfDoesNot(File file);
}
