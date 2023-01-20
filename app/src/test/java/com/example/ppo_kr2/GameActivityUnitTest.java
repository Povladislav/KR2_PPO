package com.example.ppo_kr2;

import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class GameActivityUnitTest {

    @Mock
    private GameActivity gameActivity;

    @Before
    public void setUp() throws Exception {
        gameActivity = Mockito.mock(GameActivity.class);
    }

    @Test
    public void createSensors_isCorrect() throws Exception {
        gameActivity.createSensors();
        verify(gameActivity).createSensors();
    }

    @Test
    public void isTarget_isCorrect() throws Exception {
        gameActivity.isTarget();
        verify(gameActivity).isTarget();
    }

    @After
    public void tearDown() throws Exception {
        gameActivity = null;
    }
}
