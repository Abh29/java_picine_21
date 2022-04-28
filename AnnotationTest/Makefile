#this is how to compile the project *WITHOUT* autoservice

build_processor:
	@cd src/main/java; javac -cp . -proc:none ./school21/Html*.java

build:
	@cd src/main/java; javac -cp . -processor school21.HtmlProcessor ./school21/UserForm.java

clean:
	@cd src/main/java; rm -rf */*.class