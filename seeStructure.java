import java.io.*;
import java.util.*;

public class seeStructure{
	public static void main(String [] args){
		Scanner userInput = new Scanner(System.in);
		int structureChoice = -1;
		// DLB dlb0 = new DLB();
		// UserHistory userHistory0 = new UserHistory();

		System.out.println("input 0 for DLB,  1 for UserHistory, or 2 for AutoCompleter");
		//try to get an input to decide which structure to test
		try{
			structureChoice = userInput.nextInt();
		}
		catch(InputMismatchException e){
			System.out.println("invalid input, please chose an integer of either 0 or 1");
		}

		//create a new object based on the input
		switch(structureChoice){
			case 0:
				
				System.out.println("Do you want to fill this DLB with a copy of the full sized dictionary? y or n");
				try{
					char charChoice = userInput.next().charAt(0);
					switch(charChoice){
						case 'y':
							DLB dlb0 = new DLB();
							dlb0.fillDLB("dictionary.txt");
							doOperationsDLB(dlb0, userInput);
							break;
						default:
							dlb0 = new DLB();
							doOperationsDLB(dlb0, userInput);

					}
				}
				catch(InputMismatchException e){
					System.out.println("invalid input, please choose a chaaracter.");
				}
				
				break;

			case 1:
				UserHistory userHist0 = new UserHistory();
				doOperationsUserHist(userHist0, userInput);
				break;

			case 2:
				System.out.println("Is there a userHistory that should be added to this AC? y or n");
				try{
					char charChoice = userInput.next().charAt(0);
					switch(charChoice){
						case 'y':
							System.out.println("what is the name of the file this user history is stored in?");
							
							try{
								String fileChoice = userInput.next();
								AutoCompleter ac0 = new AutoCompleter("dictionary.txt", fileChoice);
								doOperationsAC(ac0, userInput);
							}
							catch(InputMismatchException e){
								System.out.println("invalid input, please enter an integer.");
							}
							break;

						default:
							AutoCompleter ac0 = new AutoCompleter("dictionary.txt");
							doOperationsAC(ac0, userInput);
					}
				}
				catch(InputMismatchException e){
					System.out.println("invalid input, please choose a character.");
				}
				break;


			default:
				System.out.println("invalid structureChoice, please chose either 0 or 1 or 2");
			
		}


	}

	private static void doOperationsAC(AutoCompleter ac0, Scanner userInput){
		boolean terminated = false;
		System.out.println("\nselect:\n " +
							"\tn for nextChar()\n" +
							"\tf for finishWord()\n" +
							"\tw for saveUserHistory()\n" +
							"\ts for userHist.serialize()\n" +
							"\to to show options\n" +
							"\tq to quit\n");

		while(!terminated){
			char functionChoice = '!';
			System.out.println("chose a function.");

			try{
				functionChoice = userInput.next().charAt(0);
			}
			catch(InputMismatchException e){
				System.out.println("invalid input, please chose a character from the list of options.");
			}

			switch(functionChoice){
				case 'n':
					System.out.println("please input a character you want to search for");
					try{
						char charChoice = userInput.next().charAt(0);
						System.out.println("\tnextChar(" + charChoice + "): " + ac0.nextChar(charChoice));
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please chose a character.");
					}
					break;

				case 'f':
					System.out.println("please type in a word to add to ac's user history");
					try{
						String wordChoice = userInput.next();
						System.out.println("how many times would you like to add this word?");
						try{
							int numOfAdds = userInput.nextInt();
							for(int i = 0; i < numOfAdds; i++){
								ac0.finishWord(wordChoice);
							}
						}
						catch(InputMismatchException e){
							System.out.println("invalid input, please enter an integer.");
						}
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please enter a string.");
					}
					break;

				case 'w':
					System.out.println("please type in name of the file to write to");
					try{
						String wordChoice = userInput.next();
						ac0.saveUserHistory(wordChoice);
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please enter a string.");
					}
					break;

				case 's':
					System.out.println("this.userHist.serialize(): " + ac0.userHist.serialize());
					break;

				case 'o':
					System.out.println("\nselect:\n " +
							"\tn for nextChar()\n" +
							"\tf for finishWord()\n" +
							"\tw for saveUserHistory()\n" +
							"\to to show options\n" +
							"\tq to quit\n");
					break;

				case 'q':
					terminated = true;

			}
		}

	}

	private static void doOperationsDLB(DLB dlb0, Scanner userInput){
		boolean terminated = false;

		System.out.println("\nselect:\n " +
							"\ta for add()\n" +
							"\tc for contains()\n" +
							"\tp for containsPrefix()\n" +
							"\tb for searchByChar()\n" +
							"\tr for resetByChar()\n" +
							"\ts for suggest()\n" +
							"\tt for traverse()\n" +
							"\tn for count()\n" +
							"\to to show options\n" +
							"\tq to quit\n");

		while(!terminated){
			char functionChoice = '!';
			System.out.println("choose a function.");
			//try to get an input to decide which function to run
			try{
				functionChoice = userInput.next().charAt(0);
			}
			catch(InputMismatchException e){
				System.out.println("invalid input, please chose a character from the list of options.");
			}

			switch(functionChoice){
				case 'a':
					System.out.println("please input a word to add.");
					try{
						String wordChoice = userInput.next();
						System.out.println("how many times would you like to add this word?");
						try{
							int numOfAdds = userInput.nextInt();
							for(int i = 0; i < numOfAdds; i++){
								dlb0.add(wordChoice);
							}
						}
						catch(InputMismatchException e){
							System.out.println("invalid input, please enter an integer.");
						}
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please enter a string.");
					}
					break;

				case 'c':
					System.out.println("please input a word to search for.");
					try{
						String wordChoice = userInput.next();
						System.out.println("\tcontains(" + wordChoice +"): " + dlb0.contains(wordChoice));
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please enter a string.");
					}
					break;

				case 'p':
					System.out.println("please input a prefix you want to search for");
					try{
						String prefixChoice = userInput.next();
						System.out.println("\tcontainsPrefix("+ prefixChoice +"): " + dlb0.containsPrefix(prefixChoice));
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please enter a String");
					}
					break;

				case 'b':
					System.out.println("please input a character you want to search for");
					try{
						char charChoice = userInput.next().charAt(0);
						System.out.println("\tsearchByChar(" + charChoice + "): " + dlb0.searchByChar(charChoice));
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please chose a character.");
					}
					break;

				case 'r':
					System.out.println("\n\tresetByChar()\n"); dlb0.resetByChar();
					break;

				case 's':
					System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
					break;

				case 't':
					System.out.println("\tdlb0.traverse(): " + dlb0.traverse());
					break;

				case 'n':
					System.out.println("\tdlb0.count(): " + dlb0.count());
					break;

				case 'o':
					showOptions();
					break;

				case 'q':
					terminated = true;

			}
		}
	}

	private static void doOperationsUserHist(UserHistory userHist0, Scanner userInput){
		boolean terminated = false;

		System.out.println("\nselect:\n " +
							"\ta for add()\n" +
							"\tc for contains()\n" +
							"\tp for containsPrefix()\n" +
							"\tb for searchByChar()\n" +
							"\tr for resetByChar()\n" +
							"\ts for suggest()\n" +
							"\tt for traverse()\n" +
							"\tn for count()\n" +
							"\tz for serialize()\n" +
							"\to to show options\n" +
							"\tq to quit\n");

		while(!terminated){
			char functionChoice = '!';
			System.out.println("choose a function.");

			//try to get an input to decide which function to run
			try{
				functionChoice = userInput.next().charAt(0);
			}
			catch(InputMismatchException e){
				System.out.println("invalid input, please chose a character from the list of options.");
			}

			switch(functionChoice){
				case 'a':
					System.out.println("please input a word to add.");
					try{
						String wordChoice = userInput.next();
						System.out.println("how many times would you like to add this word?");
						try{
							int numOfAdds = userInput.nextInt();
							for(int i = 0; i < numOfAdds; i++){
								userHist0.add(wordChoice);
							}
						}
						catch(InputMismatchException e){
							System.out.println("invalid input, please enter an integer.");
						}
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please enter a string.");
					}
					break;

				case 'c':
					System.out.println("please input a word to search for.");
					try{
						String wordChoice = userInput.next();
						System.out.println("\tcontains(" + wordChoice +"): " + userHist0.contains(wordChoice));
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please enter a string.");
					}
					break;

				case 'p':
					System.out.println("please input a prefix you want to search for");
					try{
						String prefixChoice = userInput.next();
						System.out.println("\tcontainsPrefix("+ prefixChoice +"): " + userHist0.containsPrefix(prefixChoice));
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please enter a String");
					}
					break;

				case 'b':
					System.out.println("please input a character you want to search for");
					try{
						char charChoice = userInput.next().charAt(0);
						System.out.println("\tsearchByChar(" + charChoice + "): " + userHist0.searchByChar(charChoice));
					}
					catch(InputMismatchException e){
						System.out.println("invalid input, please chose a character.");
					}
					break;

				case 'r':
					System.out.println("\n\tresetByChar()\n"); userHist0.resetByChar();
					break;

				case 's':
					System.out.println("\tuserHist0.suggest(): " + userHist0.suggest());
					break;

				case 't':
					System.out.println("\tuserHist0.traverse(): " + userHist0.traverse());
					break;

				case 'n':
					System.out.println("\tuserHist0.count(): " + userHist0.count());
					break;

				case 'z':
					System.out.println("\t\t\t" + userHist0.serialize());
					break;

				case 'o':
					showOptions();
					break;

				case 'q':
					terminated = true;

			}
		}
	}

	private static void showOptions(){
		System.out.println("\nselect:\n " +
							"\ta for add()\n" +
							"\tc for contains()\n" +
							"\tp for containsPrefix()\n" +
							"\tb for searchByChar()\n" +
							"\tr for resetByChar()\n" +
							"\ts for suggest()\n" +
							"\tt for traverse()\n" +
							"\tn for count\n" +
							"\to to show options\n" +
							"\tq to quit\n");
	}
}