package com.asmaa.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.asmaa.DataStructure.AVLTree;
/**
 * this class help read files and check the frequency of phrases in each single document 
 * and save the result of each document in text file
 */
public class Detector {
	private List<Document> results=new ArrayList<Document>();
	private int heightAVL;// height of the avl tree generated
	final static int max_number_word=5;
	public List<Document> getResults() {
		return results;
	}

	public int getHeightAVL() {
		return heightAVL;
	}

	public void setHeightAVL(int heightAVL) {
		this.heightAVL = heightAVL;
	}

	public void setResults(List<Document> results) {
		this.results = results;
	}

	public Detector() {

	}
/*
 * load the list of the documents we need to process from the file small.txt
 * then check the similarity of each single document with the first given document
 */
	public void ReadFiles() throws IOException {
		List<String> listDocuments = new ArrayList<String>();
		AVLTree<String> tree = null;
		String l;
		int progress=0;
		//this document contain the names of the documents we want to process
		BufferedReader smallfile = new BufferedReader(new FileReader("Data/small.txt"));

		while ((l = smallfile.readLine()) != null) {
			listDocuments.add(l);
		}
		smallfile.close();

		//first line contain the name of the main document we want to compare with the rest of others documents
		Document d1 = new Document(listDocuments.get(0));
		tree = d1.createAVL();//load the first document and add it to avl tree
		this.heightAVL=tree.height(tree.getRoot());//count the height of the generated tree
		
		for (int i = 1; i < listDocuments.size(); i++) {
			progress=(i*100)/listDocuments.size();//processing progress by percentage
			
			Document d = new Document(listDocuments.get(i));
		
			d.matching_count(tree);
			this.results.add(d);//add the result of the current document to the arraylist
			
			System.out.print(progress+"% ");
	
			
		}
		saveResult("Data/Ouput/result.txt");
	}
	
	/*
	 * this method allows to save the result we had after checking the matching phrases in text file.
	 * The first line of the file contains the height of the AVL tree we generated.
	 * Each of the remaining lines contain the number of common phrases of a single document.
	 */
public void saveResult(String output) throws IOException
{
Collections.sort(results);
BufferedWriter out = new BufferedWriter(new FileWriter(output));
out.write(" Height AVL:"+this.heightAVL+"\n");
for (Document d: results) {
  out.write(d.getFileName()+" # of matching phrases:"+d.getFrequency()+"\n");
}
out.close();
}
}
