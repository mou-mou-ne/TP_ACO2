package Command;

import Recorder.recorderImpl;

/**
 * The Start class implements the Command_interface and is responsible for
 * starting the recorder.
 * 
 * <p>This class is part of the Command design pattern and encapsulates the 
 * action of starting a recorder.</p>
 * 
 * <p>It contains a reference to a Recorder object and calls its start method 
 * when the execute method is invoked.</p>
 * 
 * @author Salmaa
 */
public class Start implements Command_interface {
	
	private recorderImpl recorder;
	
	
	/**
	 * Constructor for the Start class.
	 *
	 * @param recorder the Recorder instance to be associated with this Start instance
	 */
	public Start (recorderImpl recorder) {
		
		this.recorder = recorder;
	}

	/**
	 * Executes the start command by invoking the start method on the recorder.
	 */
	@Override
	public void execute() {
		
		this.recorder.start();
		
	}

}
