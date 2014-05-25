

run:
	javac combolock/*.java
	java combolock.Main 33 44 99

alt:	
	javac combolock/*.java
	java combolock.Main 99 44 33

test:
	javac -cp .:junit-4.11.jar combolock/*.java tests/LockTest.java
	java -cp .:junit-4.11.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore tests.LockTest

clean:
	rm */*.class
