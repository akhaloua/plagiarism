package com.asmaa.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import com.asmaa.DataStructure.AVLTree;
/**
 * This class have 3 variables and contains list
 *  of methods  to help us read and check each 
 *  document that we have in the data folder
 */
public class Document implements Comparable<Document> {
	private String fileName;
	private Phrase phrase;
	private int frequency;
	final static int max_number_word=5;
	//constructors
	public Document() {

	}

	public Document(String fileName) {

		this.fileName = fileName;
		this.frequency = 0;

	}

	public Document(String fileName, int frequency) {

		this.fileName = fileName;
		this.frequency = frequency;
	}
	
//getters and setters
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Phrase getPhrase() {
		return phrase;
	}

	public void setPhrase(Phrase phrase) {
		this.phrase = phrase;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/*
	 * this method help us to split a file into phrases and 
	 * add insert them into AVL tree
	 */
	public AVLTree<String> createAVL() throws IOException {
		Scanner input = new Scanner(new File("Data/" + this.getFileName()));
		Phrase p = new Phrase();
		AVLTree<String> tree = new AVLTree<String>();
		while (input.hasNext()) {
			String word = input.next().toLowerCase();
			p.addword(word);
			//insert every five words in new node
			if (p.getNumbersWord() == max_number_word) {
				tree.setRoot(tree.insert(tree.getRoot(), new String(p.getData())));

			}
		}

		input.close();
		return tree;

	}

	/*
	 *this method  read an avl tree and count the frequency of phrases in 
	 * the current document
	 */
	public void matching_count(AVLTree<String> tree) throws FileNotFoundException

	{
		Scanner input = new Scanner(new File("Data/" + this.getFileName()));

		Phrase p = new Phrase();
		
		int frequency =0;
		while (input.hasNext()) {

			String word = input.next().toLowerCase();
			p.addword(word);
			// check if the avl tree contain the phrase of 5 words 
			if (p.getNumbersWord() == max_number_word) {

				if (tree.find(tree, p.getData().toString().trim()) != null) {

					frequency++;//increment the frequency if we find matching phrase in the AVL Tree
					this.setFrequency(frequency);

				}
			}
		}

		input.close();
	}
//compare documents by the number  of frequency
	@Override
	public int compareTo(Document d1) {
		if (d1.getFrequency() > this.getFrequency()) {
			return 1;
		} else if (d1.getFrequency() < this.getFrequency()) {
			return -1;
		}
		return 0;
	}

}
