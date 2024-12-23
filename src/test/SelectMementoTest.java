package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import mementomoris.SelectMemento;

public class SelectMementoTest {

    private SelectMemento selectMemento;
    private int begin;
    private int end;

    @Before
    public void setUp() {
        begin = 0;
        end = 5;
        selectMemento = new SelectMemento(begin, end);
    }

    @Test
    public void testGetBegin() {
        assertEquals(begin, selectMemento.getBegin());
    }

    @Test
    public void testGetEnd() {
        assertEquals(end, selectMemento.getEnd());
    }
}
