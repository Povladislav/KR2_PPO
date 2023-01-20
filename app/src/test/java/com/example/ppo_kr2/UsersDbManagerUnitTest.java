package com.example.ppo_kr2;

import static org.mockito.Mockito.verify;

import com.example.ppo_kr2.SQLite.UsersDbManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class UsersDbManagerUnitTest {

    @Mock
    private UsersDbManager usersDbManager;

    @Before
    public void setUp() throws Exception {
        usersDbManager = Mockito.mock(UsersDbManager.class);
    }

    @Test
    public void openDb_isCorrect() throws Exception {
        usersDbManager.openDb();
        verify(usersDbManager).openDb();
    }

    @Test
    public void insertToDb_isCorrect() throws Exception {
        usersDbManager.insertToDb("username", 0);
        verify(usersDbManager).insertToDb("username", 0);
    }

    @Test
    public void checkDb_isCorrect() throws Exception {
        usersDbManager.checkDb("username");
        verify(usersDbManager).checkDb("username");
    }

    @Test
    public void readFromDb_isCorrect() throws Exception {
        usersDbManager.readFromDb();
        verify(usersDbManager).readFromDb();
    }

    @Test
    public void updateDb_isCorrect() throws Exception {
        usersDbManager.updateDb(0,"username", 0);
        verify(usersDbManager).updateDb(0,"username", 0);
    }

    @Test
    public void closeDb_isCorrect() throws Exception {
        usersDbManager.closeDb();
        verify(usersDbManager).closeDb();
    }

    @After
    public void tearDown() throws Exception {
        usersDbManager = null;
    }
}
