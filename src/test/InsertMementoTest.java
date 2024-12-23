package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import mementomoris.InsertMemento;

public class InsertMementoTest {

    private InsertMemento insertMemento;
    private String textToInsert;

    @Before
    public void setUp() {
        textToInsert = "Sample Text";
        insertMemento = new InsertMemento(textToInsert);
    }

    @Test
    public void testGetTextToInsert() {
        assertEquals(textToInsert, insertMemento.getTextToInsert());
    }
}
