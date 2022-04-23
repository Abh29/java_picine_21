SRC = ./src/java/edu/school21/printer/app/*.java ./src/java/edu/school21/printer/logic/*.java
WHITE = "--white=."
BLACK = "--black=\#"
INPUTFILE = ./it.bmp


target:
	@echo "making target folder"
	@mkdir target

build:	target
	javac -d target $(SRC)

run: target
	java -classpath ./target edu.school21.printer.app.Main $(WHITE) $(BLACK) $(INPUTFILE)

all: run