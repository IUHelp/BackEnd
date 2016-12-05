package edu.iu.iuhelp.services;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sujeet on 12/3/16.
 */

@Service
public class StopWordRemoval {


    public  String removeStopWords(String textFile) throws Exception {

        String text = "Lucene is a simple yet powerful java based search library.";

        Analyzer analyzer = new StandardAnalyzer();

        List ss= tokenizeString(analyzer, text);

        System.out.print("==>"+ss+" \n");

        return "";

    }


    public  List tokenizeString(Analyzer analyzer, String str) {

        List result = new ArrayList<>();

        try {

            TokenStream stream  = analyzer.tokenStream(null, new StringReader(str));

            stream.reset();

            while (stream.incrementToken()) {

                result.add(stream.getAttribute(CharTermAttribute.class).toString());

            }

        } catch (IOException e) {
            // not thrown b/c we're using a string reader...
            throw new RuntimeException(e);
        }

        return result;

    }

}
