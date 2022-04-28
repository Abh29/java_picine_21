package school21;


import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Set;


@SupportedAnnotationTypes({"school21.HtmlInput", "school21.HtmlForm"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

       Set<? extends Element> forms =  roundEnv.getElementsAnnotatedWith(HtmlForm.class);
       Set<? extends Element> fields =  roundEnv.getElementsAnnotatedWith(HtmlInput.class);


       forms.forEach(element -> {

           HtmlForm form =  element.getAnnotation(HtmlForm.class);
           String fileName = form.fileName();
           StringBuilder sb = new StringBuilder("");

           sb.append("<form action = \"")
                   .append(form.action())
                   .append("\" method = \"")
                   .append(form.method())
                   .append("\">\n");


           String className = element.getEnclosedElements().get(0).toString().replace("()", "").trim();

           fields.forEach(field -> {


               if (field.getEnclosingElement().getAnnotation(HtmlForm.class) == null)
                   return;

               String containingClass = field.getEnclosingElement().toString();

               containingClass = containingClass.substring(containingClass.lastIndexOf('.') + 1).trim();

               if (!containingClass.equals(className))
                   return;

               HtmlInput input = field.getAnnotation(HtmlInput.class);

               sb.append("<input type = \"")
                       .append(input.type())
                       .append("\" name = \"")
                       .append(input.name())
                       .append("\" placeholder = \"")
                       .append(input.placeholder()).append("\">\n");

           });

           sb.append("<input type = \"submit\" value = \"Send\">\n</form>\n");


            try{
                writeFile("../resources/" + fileName, sb);
            }catch (IOException e){
                processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, e.getMessage());
            }
       });

        return true;
    }

    private void writeFile(String fileName, StringBuilder stringBuilder) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName, false));
        writer.write(stringBuilder.toString());
        writer.flush();
        writer.close();
    }


}
