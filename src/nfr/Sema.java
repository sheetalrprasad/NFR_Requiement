package nfr;
 
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import edu.cmu.lti.jawjaw.pobj.POS;
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.lexical_db.data.Concept;
import edu.cmu.lti.ws4j.Relatedness;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.HirstStOnge;
import edu.cmu.lti.ws4j.impl.JiangConrath;
import edu.cmu.lti.ws4j.impl.LeacockChodorow;
import edu.cmu.lti.ws4j.impl.Lesk;
import edu.cmu.lti.ws4j.impl.Lin;
import edu.cmu.lti.ws4j.impl.Path;
import edu.cmu.lti.ws4j.impl.Resnik;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;
 
public class Sema {
	
	//static String filename = null;
	private static ILexicalDatabase db = new NictWordNet();
	 static RelatednessCalculator rc = new WuPalmer(db);
	/*
	//available options of metrics
	private static RelatednessCalculator[] rcs = { new HirstStOnge(db),
			new LeacockChodorow(db), new Lesk(db), new WuPalmer(db),
			new Resnik(db), new JiangConrath(db), new Lin(db), new Path(db) };
	*/
	 

	 
	 public static double wordSimilarity(String word1, POS posWord1, String word2, POS posWord2) {
		    double maxScore = 0D;
		    
		    try {
				WS4JConfiguration.getInstance().setMFS(true);
				ArrayList<Concept> synsets1 = (ArrayList<Concept>) db.getAllConcepts(word1, posWord1.name());
				ArrayList<Concept> synsets2 = (ArrayList<Concept>) db.getAllConcepts(word2, posWord2.name());
				for (Concept synset1 : synsets1) {
					for (Concept synset2 : synsets2) {
						Relatedness relatedness = rc.calcRelatednessOfSynset(synset1, synset2);
						double score = relatedness.getScore();
						if (score > maxScore) {
							maxScore = score;
						}
					}
				}
				System.out.println("Similarity score of " + word1 + " & " + word2 + " : " + maxScore);
				
    	
		    } catch (Exception e) {
					//logger.error("Exception : ", e);
				}
				return maxScore;
			}
	 
	private static double compute(String word1, String word2) {
		WS4JConfiguration.getInstance().setMFS(true);
		double s = new WuPalmer(db).calcRelatednessOfWords(word1, word2);
		return s;
	}
 
	public static void main(String[] args) throws FileNotFoundException {
			
		ArrayList<Node> list= new ArrayList<Node>();
		 
		
		String PATH="E:\\College Sheetal\\BE Project\\Code1\\NFRProject\\Project_NFR\\output\\cafeteria.txt";	
		String writepath="E:\\College Sheetal\\BE Project\\Code1\\NFRProject\\Project_NFR\\out.txt";		    
	    FileWriter fw =null;
	    BufferedWriter bw=null;
	    try {
			fw= new FileWriter(writepath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	bw= new BufferedWriter(fw);
		
		FileReader file = null;
			ArrayList<String> words=new ArrayList();
		   BufferedReader br=null;
		    try {
		    	String str;
		    	file = new FileReader(PATH);
		    	br= new BufferedReader(file);
		    	while((str=br.readLine())!=null)
				{
					words.add(str);
				//	System.out.println(words.get(words.size()-1));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println(words.size());
			//Word Similarity
		    int count=0;
		    double dc=0.0;
		    while(count!=(words.size()-1))
		    {
		    	for(int i=1; i< words.size(); i++)
		    	{
		    		if((i)< words.size())
		    		{
		    			dc= wordSimilarity(words.get(count),POS.n,words.get(i),POS.n);
		    
		    			Node point= new Node();
						point.add_data(words.get(count), words.get(i), dc);
						list.add(point);
						
		    			
		    			try {
							bw.write("Similarity score of " + words.get(count) + " & " + words.get(i) + " : " + dc);
							bw.newLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    
		    		}
		    	}
		    	count++;
				
		    }
		    
		    int j=0;
		    while(j < list.size())
		    {
		   // 	list.get(j).show_data();
		    	j++;
		    }
		}
}