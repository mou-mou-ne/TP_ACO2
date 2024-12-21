package Command;

/**
 * The Command_interface defines the contract for command classes in the Command pattern.
 * This interface declares the {@link #execute()} method that will be implemented by concrete command classes
 * to execute specific actions, such as for exemple copy (implemented in the copinator *doofenshmirtz voice*)
 * 
 */
public interface Command_interface {

    /**
     * Executes the command's action.
     * 
     */
    void execute();
}
