package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Command.Copinator;
import Command.Cutinator;
import Command.Deletinator;
import Command.Insertinator;
import Command.Pastinator;
import Command.Replay;
import Command.Select;
import Command.Undo;
import Invoker.Invoker;
import Recorder.recorderImpl;
import engine.Engine_implementation;
import undoManagerinator.UndoManagerinatorImpl;

public class Main {

    public static void display(Engine_implementation engine, Invoker invoker, boolean isRecording) {
        System.out.println();
        System.out.println("Buffer : " + engine.getBufferContents());
        System.out.println("Begin Index : " + engine.getSelection().getBeginIndex());
        System.out.println("End Index : " + engine.getSelection().getEndIndex());
        System.out.println("Clipboard: " + engine.getClipboardContents());
        if (isRecording) {
            System.out.println("Saving...");
        }
    }

    public static void main(String[] args) {
        Engine_implementation engine = new Engine_implementation();
        recorderImpl recorder = new recorderImpl();
        UndoManagerinatorImpl undoManager = new UndoManagerinatorImpl(engine);
        Invoker invoker = new Invoker();
        
        final boolean[] isRecording = {false};
        final boolean[] running = {true};

        Scanner scanner = new Scanner(System.in);

        Map<Integer, Runnable> actions = new HashMap<>();
        
        actions.put(1, () -> {
            System.out.print("Type text to insert : ");
            String text = scanner.nextLine();
            invoker.setTextToInsert(text);
            Insertinator insertCommand = new Insertinator(engine, invoker, recorder, undoManager);
            invoker.addCommand("insert", insertCommand);
            invoker.playCommand("insert");
            System.out.println("Inserted ");
        });

        actions.put(2, () -> {
            System.out.print("Input Index start : ");
            int beginIndex = scanner.nextInt();
            System.out.print("Input index end : ");
            int endIndex = scanner.nextInt();
            invoker.setBeg(beginIndex);
            invoker.setEnd(endIndex);
            Select select = new Select(engine, invoker, recorder, undoManager);
            invoker.addCommand("select", select);
            invoker.playCommand("select");
            System.out.println("Selected");
        });

        actions.put(3, () -> {
            Copinator copy = new Copinator(engine, recorder, undoManager);
            invoker.addCommand("copy", copy);
            invoker.playCommand("copy");
            System.out.println("Paste in clipboard");
        });

        actions.put(4, () -> {
            Cutinator cut = new Cutinator(engine, recorder, undoManager);
            invoker.addCommand("cut", cut);
            invoker.playCommand("cut");
            System.out.println("Text cut");
        });

        actions.put(5, () -> {
            Pastinator past = new Pastinator(engine, recorder, undoManager);
            invoker.addCommand("paste", past);
            invoker.playCommand("paste");
            System.out.println("Succesfully glued");
        });

        actions.put(6, () -> {
            Deletinator delete = new Deletinator(engine, recorder, undoManager);
            invoker.addCommand("delete", delete);
            invoker.playCommand("delete");
            System.out.println("Txt deleted.");
        });

        actions.put(7, () -> {
            Replay replay = new Replay(recorder);
            invoker.addCommand("replay", replay);
            invoker.playCommand("replay");
        });

        actions.put(8, () -> {
            Undo undo = new Undo(undoManager);
            invoker.addCommand("undo", undo);
            invoker.playCommand("undo");
        });

        actions.put(9, () -> {
            invoker.playCommand("redo");
        });

        actions.put(42, () -> {
            running[0] = false; 
            System.out.println("Closing, thanks for using it");
        });

        while (running[0]) {
            System.out.println("\n--- Commands ---");
            System.out.println("1. Insert text");
            System.out.println("2. Select");
            System.out.println("3. Copy");
            System.out.println("4. Cut");
            System.out.println("5. Past");
            System.out.println("6. Delete");
            System.out.println("7. Replay");
            System.out.println("8. Undo");
            System.out.println("9. Redo");
            System.out.println("42. Exit");
            System.out.print("What do you want to do  : ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            if (actions.containsKey(choice)) {
                actions.get(choice).run();
            } else {
                System.out.println("Not possible, try again");
            }

            display(engine, invoker, isRecording[0]);
        }

        scanner.close();
    }
}
