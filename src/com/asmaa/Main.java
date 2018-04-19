package com.asmaa;

import java.io.IOException;

import com.asmaa.models.Detector;

/**
 * CSC 245 Main.java
 * 
 * Purpose: This program will provide experience in implementing a AVL tree
 * Determine similarity observed in a set of documents based upon the number of
 * 5- word phrases that the documents share.
 * 
 * @version 1.0 04/07/18
 * @author Asmaa khaloua
 */ 
public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

		long startTime = System.currentTimeMillis();

		System.out.println("#Processing");

		Detector d = new Detector();
		d.ReadFiles();

		System.out.println("100% \n#Processing: Done!");

		System.out.println("#the result is saved in the file 'Data/Output/result.txt'");

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Time of processing in milliseconds: "+elapsedTime);

	}
}
