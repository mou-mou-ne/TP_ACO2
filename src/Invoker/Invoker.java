package Invoker;

import java.util.HashMap;
import java.util.Map;

import Command.Command_interface;

/**
 * The Invoker class is responsible for managing and executing commands.
 * It maintains a list of commands and provides methods to add and execute them.
 */
public class Invoker {

    private Map<String, Command_interface> commandList;
    private String textToInsert;
    private int begin;
    private int end;

    /**
     * Constructor for the Invoker class.
     * Initializes the command list and sets the initial values for begin, end, and textToInsert.
     */
    public Invoker() {
        this.commandList = new HashMap<>();
        this.begin = 0;
        this.end = 0;
        this.textToInsert = "";
    }

    /**
     * Adds a command to the command list with the specified identifier.
     *
     * @param id the identifier for the command
     * @param c  the command to be added
     */
    public void addCommand(String id, Command_interface c) {
        this.commandList.put(id, c);
    }

    /**
     * Executes the command associated with the given identifier.
     *
     * @param id the identifier of the command to be executed
     * @throws IllegalArgumentException if the command with the specified identifier does not exist
     */
    public void playCommand(String id) {
        if (!commandList.containsKey(id)) {
            throw new IllegalArgumentException("No command found with ID: " + id);
        }
        commandList.get(id).execute();
    }

    /**
     * Returns the value of the begin field.
     *
     * @return the value of the begin field
     */
    public int getBegin() {
        return this.begin;
    }

    /**
     * Sets the beginning value.
     *
     * @param begin the value to set as the beginning
     */
    public void setBeg(int begin) {
        this.begin = begin;
    }

    /**
     * Returns the value of the end field.
     *
     * @return the value of the end field
     */
    public int getEnd() {
        return this.end;
    }

    /**
     * Sets the end value.
     *
     * @param end the value to set as the end
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * Retrieves the text to be inserted.
     *
     * @return the text to insert
     */
    public String getTextToInsert() {
        return this.textToInsert;
    }

    /**
     * Sets the text to be inserted.
     *
     * @param s the text to insert
     */
    public void setTextToInsert(String s) {
        this.textToInsert = s;
    }

    /**
     * Retrieves the list of commands.
     *
     * @return a map containing command names as keys and their corresponding
     * Command_interface implementations as values.
     */
    public Map<String, Command_interface> getCommandList() {
        return this.commandList;
    }
}
