package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import mementomoris.EditorMemento;

public class EditorMementoTest {

    private EditorMemento memento;
    private StringBuilder bufferContent;
    private int beginIndex;
    private int endIndex;
    private String clipboard;

    @Before
    public void setUp() {
        bufferContent = new StringBuilder("Hello World");
        beginIndex = 0;
        endIndex = 5;
        clipboard = "Hello";
        memento = new EditorMemento(bufferContent, beginIndex, endIndex, clipboard);
    }

    @Test
    public void testGetBufferContent() {
        assertEquals(bufferContent, memento.getBufferContent());
    }

    @Test
    public void testGetClipboard() {
        assertEquals(clipboard, memento.getClipboard());
    }

    @Test
    public void testGetBeginIndex() {
        assertEquals(beginIndex, memento.getBeginIndex());
    }

    @Test
    public void testGetEndIndex() {
        assertEquals(endIndex, memento.getEndIndex());
    }
}
