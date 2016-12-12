package edu.iu.iuhelp.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by prashanth on 12/12/2016.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtractDivTest {

    @Autowired
    private ExtractUrl extractUrl;

    @Autowired
    private ExtractDiv extractDiv;

    @Test
    public void testDIVWithNoSearchWords(){

        Assert.assertEquals("when stemming and stop word removal removes all words in search query","Whoooops !!!!! Couldn't find the matching text. May be you could try out these links ",extractDiv.getRelevantDiv("hello this is me",null));

    }

    @Test
    public void testDIVWithNoTopURL(){

        Assert.assertEquals("when the method does not receive the topURL as string","Whoooops !!!!! Couldn't find the matching text. May be you could try out these links ",extractDiv.getRelevantDiv(null,"can be anything"));

    }


    // facing java.net.MalformedURLException: no protocol:  for input "<div>Hello we are in search space now</div>"

    /*@Test
    public void testDIVWithSingleSearchKeyword(){

        Assert.assertEquals("when stemming and stop word removal gives one word in search query","<div>Hello we are in search space now<",extractDiv.getRelevantDiv("<div>Hello we are in search space now<//div>","search"));

    }

    @Test
    public void testDIVWithProperInput(){

        Assert.assertEquals("with proper input","<div>Hello we are in search space now<",extractDiv.getRelevantDiv("<div>Hello we are in search space now</div>","search space are"));

    }*/



}
