package nfr;

import java.util.ArrayList;

public class Node {
	
	String word1, word2;
	double ssm;
	ArrayList linkid= new ArrayList();
	public void add_data(String node1, String node2, double value)
	{
		word1=node1;
		word2=node2;
		ssm=value;
	}
	public void show_data()
	{
		System.out.println("Word 1: "+ word1);
		System.out.println("Word 2: "+ word2);
		System.out.println("Score: "+ ssm);
	}
	
	public void add_link(int data)
	{
		linkid.add(data);
	}

}
