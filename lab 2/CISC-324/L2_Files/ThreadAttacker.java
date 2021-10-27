import java.util.concurrent.CountDownLatch;

//The content of this file defines a Java main class named 'ThreadAttacker' 
//This class contains the main method from where the whole program (project) 
//starts its execution

public class ThreadAttacker {

	//This is the challenge value, you can modify the value if you want
	public static String challenge = "challenge_sequence";
	//This is the password. It is here to help us compute the correct response that corresponds to the challenge
	public static String password = "virus"; 
	//This is the variable that represents the captured response
	public static int captured;
	//This is the variable that will be used by the thread to inform each other that the password has been cracked
	//For instance, if a thread cracks the password, it update the value of this variable to true
	public static volatile boolean found = false;
	public static volatile CountDownLatch latch = new CountDownLatch(3);

	//The main method, here starts the execution	
	public static void main(String[] args) throws InterruptedException 
	{
		//tempx is a temporary string variable that we are using to create the concatenation of the password with the challenge 
		String tempx = password + challenge;
		//Here we create the response by computing the hash of the previously computed string object
		captured = tempx.hashCode();
		
		//Thread creation and instanciation (three threads are created)
		ThreadBots t_1 = new ThreadBots(1,'i');
		ThreadBots t_2 = new ThreadBots(2, 't');
		ThreadBots t_3 = new ThreadBots(3, 'v');
				
		//Thread trigging (starting the threads)
		//counting time
		long startTime = System.nanoTime();
		
		t_1.start();
		t_2.start();
		t_3.start();
		try {
			t_1.join();
			t_2.join();
			t_3.join();
			t_1.stop();
			t_2.stop();
			t_3.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long elaspedTime = (System.nanoTime() - startTime)/1000000;
		System.out.println("time in milis for threaded:" + elaspedTime);

		startTime = System.nanoTime();
		String pwd = nonThreaded();
		elaspedTime = (System.nanoTime() - startTime)/1000000;
		System.out.println("time in milis for non threaded: " + elaspedTime + " password: " + pwd);
	}

	public static String nonThreaded(){
		char[] firstLetters = {'i', 't', 'v'};
		char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		outerloop:
		for (int i = 0; i < firstLetters.length; i++){
			for (int j = 0; j < letters.length; j++){
				for (int k = 0; k < letters.length; k++) {
					for (int l = 0; l < letters.length; l++) {
						for (int m = 0; m < letters.length; m++){
							if ((new String(new char[] {firstLetters[i], letters[j], letters[k], letters[l], letters[m]}) + challenge).hashCode() == captured) {
								// System.out.println("found");
								return (new String(new char[] {firstLetters[i], letters[j], letters[k], letters[l], letters[m]}) + challenge);
							}
						}
					}
				}
			}
		}
		return ("Not found");
	}

}
