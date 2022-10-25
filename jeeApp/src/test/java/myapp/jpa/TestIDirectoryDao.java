package myapp.jpa;

import myapp.Starter;
import myapp.dao.IDirectoryDao;
import myapp.model.Group;
import myapp.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Starter.class)
public class TestIDirectoryDao {

    @Autowired
    IDirectoryDao dao;

    @Test
    public void testAddAndFindPerson(){
        var p1 = new Person("loic","fery","password");
        Group gr1 = new Group("groupe1");
        gr1.addPerson(p1);
        p1 = dao.addPerson(p1);

        assertTrue(p1.getId() > 0);

        var p2 = dao.findPerson(p1.getId());
        assertEquals(p1.getId(),p2.getId());

        assertEquals(p2.getGroup().getId(),gr1.getId());
    }

    @Test
    public void testRemovePerson(){
        var p1 = new Person("loic","fery","password");
        p1 = dao.addPerson(p1);
        assertTrue(p1.getId() > 0);

        dao.removePerson(p1.getId());
        assertNull(dao.findPerson(p1.getId()));
    }

    @Test
    public void testUpdatePerson(){
        var p1 = new Person("loic","fery","password");
        p1 = dao.addPerson(p1);
        assertTrue(p1.getId() > 0);

        p1.setFirstName("Pierre");
        dao.updatePerson(p1);
        var p2 = dao.findPerson(p1.getId());
        assertEquals(p1.getFirstName(),p2.getFirstName());
    }

    @Test
    public void testAddAndFindGroup(){
        var gr1 = new Group("groupe1");
        gr1 = dao.addGroup(gr1);

        var gr2 = dao.findGroup(gr1.getId());
        assertEquals(gr1.getId(),gr2.getId());
    }

    @Test
    public void testFindAllGroup(){
        List<Group> groups;
        var gr1 = new Group("groupe1");
        var gr2 = new Group("groupe2");
        gr1 = dao.addGroup(gr1);
        gr2 = dao.addGroup(gr2);

        groups = dao.findAllGroups();
        Iterator<Group> groupIterator = groups.iterator();

        assertTrue(groupIterator.hasNext());
        assertEquals(gr1.getId(),groupIterator.next().getId());
        assertTrue(groupIterator.hasNext());
        assertEquals(gr2.getId(),groupIterator.next().getId());
        assertFalse(groupIterator.hasNext());
    }
}
