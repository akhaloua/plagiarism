package com.asmaa.models;
/**
 * This class is the structure of the type phrase,
 * -each phrase contain number of words, 
 * -each phrase has max number of words
 */
public class Phrase  {
	private StringBuilder data;
	private int numbersWord;
	final static int max_number_word=5;
//Constructor
	public Phrase() {
		
	}

	//getters and setters
	public StringBuilder getData() {
		return data;
	}

	public void setData(StringBuilder data) {
		this.data = data;
	}

	public int getNumbersWord() {
		return numbersWord;
	}

	public void setNumbersWord(int numbersWord) {
		this.numbersWord = numbersWord;
	}
	
	/*
	 * this method help us to add word to a given phrase and each 
	 * time the phrase reach 5 words we will remove the first 
	 * word and add the new word to the end of the sentence
	 */
	public void addword(String word) {
		word = word.replaceAll("[!?,.:;)(]", "").trim();// remove punctuation
		if (data == null) {
			StringBuilder sb = new StringBuilder();
			sb.append(word);
			this.setData(sb);
			this.numbersWord += 1;
		} else {

			StringBuilder temp = this.getData();
			if (this.numbersWord < max_number_word) {
				if (temp.length() > 0) {
					temp.append(" ").append(word);

				} else {
					temp.append(word);
				}
				this.setData(temp);
				this.numbersWord += 1;

			} else {

				temp.delete(0, temp.indexOf(" ") + 1);
				temp.append(" ").append(word);
				this.setData(temp);
				this.numbersWord = max_number_word;

			}
		}
	}

	

	
}
