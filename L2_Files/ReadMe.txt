1) Thread 3 was the one to crack the password, as it was the one that started with character 'v'.

2) Cooperation between the three threads took place in terms of memory sharing, as when one thread found it, it was able to communicate through a 
	shared variable that it had successfully found the correct password to the other threads, which would allow them to end their processes early rather then go through
	all the possibilities and not finding it, then finally ending.

3) Threading proved to be faster with proof as the second block of text in the console after running the program

4) Non threaded was faster for execution with the order being i, v, then t