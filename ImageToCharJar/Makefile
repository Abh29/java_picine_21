SRC = ./src/java/edu/school21/printer/app/*.java ./src/java/edu/school21/printer/logic/*.java
WHITE = "--white=."
BLACK = "--black=\#"
RESOURCES = resources/image.bmp

target:
	@echo "making target folder"
	@mkdir target

build:	target
	javac -d target $(SRC)

run: target
	java -classpath ./target edu.school21.printer.app.Main $(WHITE) $(BLACK)

all: run

jar:	build
	cp -r ./src/resources ./target
	cd target
	jar cfev imageToChar.jar edu/school21/printer/app/Main edu/school21/printer/app/Main.class edu/school21/printer/logic/MYBMPPrinter.class edu/school21/printer/logic/MYBMPReader.class resources/image.bmp
	cd ..


