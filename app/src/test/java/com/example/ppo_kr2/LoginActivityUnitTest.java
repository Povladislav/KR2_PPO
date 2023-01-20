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
public class LoginActivityUnitTest {

    @Mock
    private LoginActivity loginActivity;

    @Before
    public void setUp() throws Exception {
        loginActivity = Mockito.mock(LoginActivity.class);
    }

    @Test
    public void clearInput_isCorrect() throws Exception {
        loginActivity.emptyInput();
        verify(loginActivity).emptyInput();
    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }
}
