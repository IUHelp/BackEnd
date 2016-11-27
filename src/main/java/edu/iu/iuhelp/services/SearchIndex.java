package edu.iu.iuhelp.services;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchIndex {

	@Value("${resource.indexed.folder.name}")
	private String indexedDirectoryPath;

	public List<String> getResult(String Query) throws IOException, ParseException{
			List<String> result=new ArrayList<String>();

      IndexReader r = DirectoryReader.open(FSDirectory.open(Paths.get(indexedDirectoryPath)));
		//Query Analyzer

		Analyzer a=new StandardAnalyzer();
		Similarity sim=new BM25Similarity();

			//"summary" identifies which field you are searching in.
		QueryParser parser = new QueryParser("summary", a);
		org.apache.lucene.search.Query query = parser.parse(QueryParser.escape(Query));
		IndexSearcher searchIndexDoc =new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get(indexedDirectoryPath))));
		searchIndexDoc.setSimilarity(sim);
		TopDocs results = searchIndexDoc.search(query, 10);
		ScoreDoc[] hits=results.scoreDocs;

			for(int i=0;i<hits.length;i++){
				Document doc=searchIndexDoc.doc(hits[i].doc);
				String temp="";
				temp+=doc.get("path");
				result.add(temp);
				System.out.println(temp);
			}
		 return result;
	}
}
