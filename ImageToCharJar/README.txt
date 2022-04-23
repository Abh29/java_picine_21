this is a program that prints a black and white image.bmp to console

to compile the program run
make build
to run the program rur
make run

you can configure the name of the image file and the characters representing
white and black from the Makefile provided


alternatively you can run
mkdir target
javac ./src/java/edu/school21/printer/app/*.java ./src/java/edu/school21/printer/logic/*.java
java -classpath ./target edu.school21.printer.app.Main --white=<arg1> --black=<black>

replace <white> <black> with a single character each that conrrespond to white/black pixels respectively

you can create a jar file by running:
make jar

to run the jar file:
java -jar target/imageToChar.jar --white=<char> --black=<char>