package edu.iu.iuhelp.services;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.iu.iuhelp.models.ResultDocs;
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
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchIndex {

	class CustomComparator implements Comparator<ResultDocs> {
		public int compare(ResultDocs a, ResultDocs b) {
			if(a.getScore()<b.getScore())
				return 1;
			else
			if(a.getScore()>b.getScore())
				return -1;
			return 0;
		}
	}
	@Value("${resource.indexed.folder.name}")
	private String indexedDirectoryPath;

	public List<ResultDocs> getResult(String Query) throws IOException, ParseException{
		List<String> result=new ArrayList<String>();

		IndexReader r = DirectoryReader.open(FSDirectory.open(Paths.get(indexedDirectoryPath)));
		//Query Analyzer

		Analyzer a=new StandardAnalyzer();
		//First Similarity
		Similarity sim=new BM25Similarity();

		//"summary" identifies which field you are searching in.
		QueryParser parser = new QueryParser("summary", a);
		org.apache.lucene.search.Query query = parser.parse(QueryParser.escape(Query));

		IndexSearcher searchIndexDoc =new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get(indexedDirectoryPath))));
		searchIndexDoc.setSimilarity(sim);
		TopDocs results = searchIndexDoc.search(query, 10);
		ScoreDoc[] hits=results.scoreDocs;

		//Second Similarity
		sim = new ClassicSimilarity();
		searchIndexDoc.setSimilarity(sim);
		results=searchIndexDoc.search(query, 10);
		ScoreDoc[] hits1=results.scoreDocs;

		List<ResultDocs> l=new ArrayList<ResultDocs>();

		for(int i=0;i<hits.length;i++){
			Document doc=searchIndexDoc.doc(hits[i].doc);
			l.add(new ResultDocs(hits[i].score,doc.get("path"),doc.get("title"),doc.get("summary")));
		}
		boolean f=false;
		int j;
		for(int i=0;i<hits1.length;i++){
			f=false;
			Document doc=searchIndexDoc.doc(hits1[i].doc);
			for(j=0;j<l.size();j++){
				if(l.get(j).getLink().equals(doc.get("path"))){
					f=true;
					break;
				}
			}
			if(f)
			//	l.get(j).score+=hits1[i].score;

				l.get(j).setScore(l.get(j).getScore()+hits1[i].score);
			else
				l.add(new ResultDocs(hits1[i].score,doc.get("path"),doc.get("title"),doc.get("summary")));
		}
		Collections.sort(l,new CustomComparator());
		for(int i=0;i<l.size();i++){
			result.add(l.get(i).getLink());
		}
		return l;
	}
}
