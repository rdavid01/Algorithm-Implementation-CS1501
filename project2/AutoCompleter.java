import java.io.*;
import java.util.*;

public class AutoCompleter implements AutoComplete_Inter{

	public static Scanner keyReader;
	DLB dlb = null;
	UserHistory userHist = null;

	public AutoCompleter(String dictionary){
		//file not found exception is handled in fillDLB()
		dlb = new DLB(); // store the information from the dictionary file parameter
		fillDLB(dlb, dictionary);
		userHist = new UserHistory(); //start off with an empty user history
	}

	public AutoCompleter(String dictionary, String history){
		//file not found exception is handled in fillDLB() and fillHist()
		dlb = new DLB(); //store info from dictionary file parameter
		fillDLB(dlb, dictionary);
		userHist = new UserHistory(); //store info from history file parameter
		fillHist(userHist, history);
	}

//-------------------------------------------------------------------------------

	public ArrayList<String> nextChar(char next){
		
		dlb.searchByChar(next);
		userHist.searchByChar(next); //perform searchByChar() searches inside the dlb and userHist

		ArrayList<String> dlbList = dlb.suggest();
		ArrayList<String> histList = userHist.suggest(); //get suggestions from the dlb and userHist

		// System.out.println("dlbList: " + dlbList);
		// System.out.println("histList: " + histList);
		
		//want to provide suggestions based on the most frequently used words
		//if there are less than 5 suggestions that match the current by char search in userHist
			//then we will use suggestions from the dlb by char search to fill histList up to a size of 5
				//we fill histList because histList has the most frequent words at the beginning
		//the loop goes from 0 to dlbList.size() to account for cases where there are less then 5 suggestions
		for(int i=0; i < dlbList.size(); i++){ 
			String dlbListEntry = dlbList.get(i); //dlbListEntry is the string at index i in dlbList

			if(histList.size() < 5 && !(histList.contains(dlbListEntry))){ //only add dlbListEntry if histList's size is less than 5, 
																		   //and dlbListEntry is not already part of the list (this is the case when dlbListEntry has already been returned by userHist.suggest())
				histList.add(dlbListEntry);
			}
		}
		return histList;
	}

//--------------------------------------------------------------------------------
	public void finishWord(String cur){ 
		userHist.add(cur); //add cur to the user History
		resetSearches(dlb, userHist); //reset the by char searches used by dlb and userHist

	}

//--------------------------------------------------------------------------------
	public void saveUserHistory(String fname){
		StringBuilder word = new StringBuilder(""); //holds the prefix/word that will be added to the file

		try{
			PrintWriter outputUserHist = new PrintWriter(fname);
			addToHistFile(userHist.getRoot(), outputUserHist, word);
			outputUserHist.close();
		}
		catch(FileNotFoundException e){
			System.out.println("file not found");
		}

		
		
	}

	private void addToHistFile(HistNode curr, PrintWriter outputUserHist, StringBuilder word){
			
		while(curr != null){
			word.append(curr.getLet()); //add curr's char so that word matches the prefix/word the traversal is currently at
			if(curr.getFrequency() != 0){ //a key is stored since frequency is >0
				String entryWord = word.toString();
				int entryFreq = curr.getFrequency();

				outputUserHist.println(entryWord + " " + entryFreq); //at the word and it's frequency separated by a space
																			 //when reading the file that is created, we can read determine how many times to add each word by reading the key and then its frequency
			}
			if(curr.getDown() != null){ //if possible, recurse down list of children
				addToHistFile(curr.getDown(), outputUserHist, word);
			}
							
			word.deleteCharAt(word.length()-1);// remove the char that was just added before moving to its sibling
			curr = curr.getRight(); //move across list of siblings
		}

				
	}

//==================================================================================
//==================================================================================
//below are functions that weren't specified in the interface but that were helpful to write

	//resets the current by char searches in dlb and userHist
	public static void resetSearches(DLB dlb, UserHistory userHist){
		dlb.resetByChar();
		userHist.resetByChar();
	}

	//fills the autocompleter's DLB with contents of the file stored at the location provided by the argument passed into the dictionary parameter
	private static void fillDLB(DLB dlb, String dictionary){
		try{
			Scanner keyReader = new Scanner(new File(dictionary)); //created a scanner to read through the file
			while(keyReader.hasNext()){ //while there is another thing to read in the file
				
				String storedWord = keyReader.nextLine(); //read the next line in the file
				dlb.add(storedWord); //add this line from file to the dlb
			}
			keyReader.close(); //close the scanner
		}
		catch(FileNotFoundException e){
			System.out.println("dictionary file not found -> " + dictionary);
		}
	}

	//fills the autocompleter's UserHistory with the contents of the file stored at the location provided by the argument passed into the history parameter
	private static void fillHist(UserHistory userHist, String history){
		// System.out.println("!!!!!!!!!!!   " + history + "   !!!!!!!!!!!");
		try{
			Scanner keyReader = new Scanner(new File(history)); //created a scanner to read through the file
			while(keyReader.hasNext()){ //while there is another thing to read from the file
				String storedWord = keyReader.next(); //read the next thing (should be the word) and store it as a string in storedWord
				int storedFreq = keyReader.nextInt(); //read the next thing as an int (should be the frequency) and store it as an int in storedFreq
				for(int i=0; i < storedFreq; i++){
						userHist.add(storedWord); //add the string from stored word as many times as storedFreq tells us to
														//ex. if storedWord = "hello"  and  storedFreq = 23
														//then "hello" will be added to the user history 23 times
				}
			}
			keyReader.close(); //close the scanner
		}
		catch(FileNotFoundException e){
			System.out.println("user history file not found -> " + history);
		}
		// System.out.println("\n this.userHist.serialize(): " + userHist.serialize());
		//need to add frequencies
	}

}