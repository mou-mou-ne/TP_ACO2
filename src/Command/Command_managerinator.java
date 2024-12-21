package Command;

import java.util.Stack;

/**
 * The Command_managerinator class is responsible for managing the execution and undoing of commands.
 * It acts as the invoker in the Command design pattern, allowing commands to be executed and stored
 * in a history stack for potential undo operations, which will be implemented later hopefully.
 * 
 * 
 */
public class Command_managerinator {

    private Stack<Command_interface> commandHistory = new Stack<>();

    /**
     * Executes a given command and stores it in the command history.
     * 
     * <p>This method calls the {@link Command_interface#execute()} method on the provided command
     * and pushes the command onto the history stack for potential future undo, but as you can tell, it is for now useless
     * because I'm not done yet with V2.</p>
     * 
     * @param command the command to be executed
     */
    public void executeCommand(Command_interface command) {
        command.execute();
        commandHistory.push(command);
    }

    /**
     * Undoes the last executed command.
     * 
     * <p>This method currently contains a placeholder for undo functionality, printing a message
     * that undo is not yet implemented. In a full implementation, this method would remove the last
     * command from the history stack and reverse its effect, but unfortnunatly, it is not yet implemented</p>
     */
    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            // Add logic to undo commands if needed
            System.out.println("Undo not implemented yet.");
        } else {
            System.out.println("No commands to undo.");
        }
    }
}
