package edu.iu.iuhelp.services;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
import org.apache.lucene.store.NIOFSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class SearchIndex {

	@Value("${resource.indexed.folder.name}")
	private String indexedDirectoryName;

	public List<String> getResult(String Query) throws IOException, ParseException{
			List<String> result=new ArrayList<String>();

//        File file2 = new ClassPathResource("application.properties").getFile();
//
//        System.out.println(" 000000000---------------------------------------------------------------------000000000000000000000000000");

        ClassPathResource classPathResource = new ClassPathResource(indexedDirectoryName);



			String indexedDirectory =classPathResource.getURL().getPath();

        System.out.println("---1-- "+indexedDirectory);
        System.out.println("NIOFSDirectory.open(Paths.get(indexedDirectoryName)) == " + NIOFSDirectory.open(Paths.get(indexedDirectoryName)));


        File file = new File(FSDirectory.open(Paths.get(indexedDirectoryName)).getDirectory().toString());

		File tobeCopied = new File(indexedDirectory);

    //    URL resource = this.getClass().getClassLoader().getResource("segments_1");

//
//		FileUtils.copyDirectory(tobeCopied,file);
//      //  FileUtils.copyDirectory(new File(indexedDirectoryName+"murRU"),file);
//        if(!file.exists()){
//            System.out.println("aaya ");
//			FileUtils.copyDirectory(tobeCopied,file);
//         //   FileUtils.copyDirectory(new File(indexedDirectoryName),file);
//        }


//	"	MMapDirectory@/Users/Manish/Documents/Academic/OOSD/IUHelp/indexeddocs' "

      IndexReader r = DirectoryReader.open(FSDirectory.open(Paths.get("//Users//Manish//Desktop//indexeddocs")));
        System.out.println("v--v> ");
  //      IndexReader r = DirectoryReader.open(NIOFSDirectory.open(Paths.get(indexedDirectoryName)));

        //IndexReader r = DirectoryReader.open(FSDirectory.open(Paths));

        //Query Analyzer

			Analyzer a=new StandardAnalyzer();
			Similarity sim=new BM25Similarity();

			//"summary" identifies which field you are searching in.
			QueryParser parser = new QueryParser("summary", a);
			org.apache.lucene.search.Query query = parser.parse(QueryParser.escape(Query));
			IndexSearcher searchIndexDoc =new IndexSearcher(DirectoryReader.open(FSDirectory.open(Paths.get(indexedDirectory))));
            searchIndexDoc.setSimilarity(sim);
			TopDocs results = searchIndexDoc.search(query, 10);
			ScoreDoc[] hits=results.scoreDocs;


			//int numTotalHits = results.totalHits;

			//System.out.println(numTotalHits + " total matching documents");
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
