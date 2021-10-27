//The content of this file defines a Java class named 'ThreadBot' 
//This class inherits from the predefined Java class Thread.

public class ThreadBots extends Thread
{

	int Identity; //This integer variable will be the thread identifier
    char init_char;//This character will be used by each thread as the first letter in the password


	//Here we redefine the default constructor of this class.
	//By default it has no arguments, but in this example
	//We are using two arguments
	public ThreadBots(int id, char c) 
	{
		//Here we retrieve the value of the identity passed by the main class
		Identity = id;
		//Here we retrieve the value of the character passed by the main class
		init_char = c;
	}
	
	public void run()
	{
		
		String curPassword = "";
		char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		outerloop:
		for (int i = 0; i < letters.length; i++) {
			for (int j = 0; j < letters.length; j++) {
				for (int k = 0; k < letters.length; k++) {
					for (int l = 0; l < letters.length; l++){
						if (ThreadAttacker.found) {
							// System.out.println("Thread " + this.Identity + " finished with values " + Integer.toString(i) + " " + Integer.toString(j) + " " + Integer.toString(k) + " " + Integer.toString(l));
							break outerloop;
						}
						
						curPassword = new String(new char[] {init_char, letters[i], letters[j], letters[k], letters[l]}) + ThreadAttacker.challenge;
						// System.out.println(curPassword);
						if (curPassword.hashCode() == ThreadAttacker.captured){
							System.out.println("Thread" + this.Identity + " creacked the password with password: " + curPassword);
							ThreadAttacker.found = true;
							break outerloop;
							
						}

						
						

						
					}
				}
			}
		}			
	}

}
