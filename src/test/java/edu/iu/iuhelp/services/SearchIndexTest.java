package edu.iu.iuhelp.services;

import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sujeet on 11/27/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchIndexTest {

    @Value("${resource.indexed.folder.name}")
    private String indexedDirectoryPath;

    @Autowired
    private SearchIndex searchIndex;

    private String query;

    @Before
    public void setServiceTest(){

        this.query = "Elizabeth";
    }

    @Test
    public void getResultTest(){


        try {
            List<String> result = searchIndex.getResult(this.query);

            Assert.assertNotNull(result);
//            Assert.assertNotNull("List shouldn't be null", result);
//            Assert.assertEquals("wrong size", 3, result.size());
//            Assert.assertEquals("Wrong 1st element", "Customer1", result.get(0));
//            Assert.assertEquals("Wrong 2nd element", "Customer2", result.get(1));
//            Assert.assertEquals("Wrong 3rd element", "Customer3", result.get(2));
//
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
