package nfr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.cmu.lti.jawjaw.pobj.POS;
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.lexical_db.data.Concept;
import edu.cmu.lti.ws4j.Relatedness;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.Resnik;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;


public class WuPalmerAlgo{

	
	private static ILexicalDatabase db = new NictWordNet();
	 static RelatednessCalculator rc = new WuPalmer(db);

	 private static double compute(String word1, String word2) {
			WS4JConfiguration.getInstance().setMFS(false);
			double s = rc.calcRelatednessOfWords(word1, word2);
			return s;
		}

	 
 
	public static void main(String[] args) throws FileNotFoundException {
			
		ArrayList<Node> list= new ArrayList<Node>();
		 
		
		final String PATH="E:\\College Sheetal\\BE Project\\Work In Progress\\restaurant1.txt";	
		final String writepath="E:\\College Sheetal\\BE Project\\Work In Progress\\pink.txt";		    
	    
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
		    int i=0;
		    double dc=0.0;
		    //RelatednessCalculator res = new Resnik(db);
		    while(i!=(words.size()-1))
		    {
		    	for(int j=1; j< words.size(); j++)
		    	{
		    		if((i)< words.size())
		    		{
		    			double distance = compute(words.get(i), words.get(j));
						System.out.println(words.get(i) +" & " +  words.get(j) + " = " + distance);
			 		
						Node point= new Node();
						point.add_data(words.get(i), words.get(j), dc);
						list.add(point);
						
		    			
		    			try {
							bw.write("Similarity score of " + words.get(i) + " & " + words.get(j) + " : " + distance);
							bw.newLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    
		    		}
		    	}
		    	i++;
				
		    }
		    
		    int count=0;
		    System.out.println("graph nodes");
		    while(count < list.size())
		    {
		    	list.get(count).show_data();
		    	count++;
		    }
		}
}
