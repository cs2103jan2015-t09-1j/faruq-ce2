import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public interface Controller {
	
	public void startController(String[] args);
	
	public void commandSelector();
	
	public File checkForAndInitArgument(String[] args);
	
	public boolean checkIfFileExistAndCreateIfDoesNot(File file);
}
