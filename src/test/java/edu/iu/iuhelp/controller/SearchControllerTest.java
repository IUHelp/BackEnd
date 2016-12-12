package edu.iu.iuhelp.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.iu.iuhelp.controllers.SearchController;

/**
 * Created by Sujeet on 11/27/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchControllerTest {
	
	SearchController sc = new SearchController();

    @Test
    public void searchTest(){
    	String result = sc.generateContent("https://www.indiana.edu/");
    	 Assert.assertFalse(result.isEmpty());
    }

}


