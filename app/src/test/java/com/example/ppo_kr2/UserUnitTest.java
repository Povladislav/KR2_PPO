package com.example.ppo_kr2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

@RunWith(JUnit4.class)
public class UserUnitTest {

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User(0,"",0);
    }

    @Test
    public void setId_isCorrect() throws Exception {
        user.setId(5);
        assertEquals(5, user.getId());
    }

    @Test
    public void setUsername_isCorrect() throws Exception {
        user.setUsername("username");
        assertEquals("username", user.getUsername());
    }

    @Test
    public void setScore_isCorrect() throws Exception {
        user.setScore(100);
        assertEquals(100, user.getScore());
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }
}
