package Command;

/**
 * The Command_interface defines the contract for command classes in the Command pattern.
 * This interface declares the {@link #execute()} method that will be implemented by concrete command classes
 * to execute specific actions.
 * 
 * <p>The Command pattern allows actions to be encapsulated as objects, enabling parameterization of clients
 * with different requests, queuing of requests, and logging of the request executions. It also supports undoable operations.</p>
 * 
 * <p>Concrete classes that implement this interface will define the specific behavior of the command
 * in the {@link #execute()} method.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * Command_interface copyCommand = new Copinator(engine, textArea, selection);
 * copyCommand.execute();
 * </pre>
 */
public interface Command_interface {

    /**
     * Executes the command's action.
     * <p>This method encapsulates the logic of the action to be performed when the command is invoked.</p>
     */
    void execute();
}
