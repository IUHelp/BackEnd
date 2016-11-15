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
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

@Service
public class SearchIndex {
	public List<String> getResult(String Query) throws IOException, ParseException{
			List<String> result=new ArrayList<String>();
		 String indexPath="C:\\Users\\sujit\\Desktop\\IndexedDocs";
			//in.startIndex("C:\\Users\\sujit\\workspace\\QueryLucene\\lib\\Corpus.txt");
			IndexReader r = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
			System.out.println("Total number of documents in the corpus:"+r.maxDoc());
			//Query Analyzer
			Analyzer a=new StandardAnalyzer();
			Similarity sim=new BM25Similarity();

			//"text" identifies which field you are searching in.
			QueryParser parser = new QueryParser("summary", a);
			org.apache.lucene.search.Query query = parser.parse(QueryParser.escape(Query));
			IndexSearcher s=new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get(indexPath))));
			s.setSimilarity(sim);
			TopDocs results = s.search(query, 10);
			ScoreDoc[] hits=results.scoreDocs;

			//int numTotalHits = results.totalHits;

			//System.out.println(numTotalHits + " total matching documents");
			for(int i=0;i<hits.length;i++){
				Document doc=s.doc(hits[i].doc);
				String temp="";
				temp+=doc.get("path");
				result.add(temp);
				System.out.println(temp);
				System.out.println(doc.get("summary"));
			}
		 return result;
	}
}
