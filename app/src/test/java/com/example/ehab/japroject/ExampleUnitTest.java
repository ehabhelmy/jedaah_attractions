package com.example.ehab.japroject;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ehab.japroject.util.ValidationUtils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import  org.hamcrest.MatcherAssert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    Context context;

    SharedPreferences sharedPreferences;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        sharedPreferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    private static final String name = "asdasdas";
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        //assertTrue(ValidationUtils.isValidEmail("ehab@gmail.com"));
    }

    @Test
    public void readFrom_sharedPrefernces(){

    }

    @Test
    public void save_sharedPrefernces(){

    }
}