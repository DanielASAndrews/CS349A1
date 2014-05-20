

run:
	javac combolock/*.java
	java combolock.Main


test:
	javac -cp .:junit-4.11.jar combolock/*.java tests/LockTest.java
	java -cp .:junit-4.11.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore tests.LockTest

clean:
	rm */*.class
