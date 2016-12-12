package edu.iu.iuhelp.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtractUrlTest {
	 @Autowired
	    private ExtractUrl eu;
	 
	 @Test
	    public void extract(){
	    	String result = eu.getUrlContents("https://www.indiana.edu/");
	    	 Assert.assertFalse(result.isEmpty());
	    }

}
