import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Main {

    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_0);

    public static void main(String args[]) throws Exception {

        MyObjectsManager objectsManager = new MyObjectsManager("fileName");

        List<Object> objects = objectsManager.getObjectsFromFile();

        String objectName = "object";

        String templatePath = "template.ftl";

        Map<String, Object> input = new HashMap<>();
        for (Object o : objects) {
            input.put(objectName, o);
        }

        String json = generateJsonByTemplate(templatePath, input);

        System.out.println(json);
    }



    public static String generateJsonByTemplate(String templateName, Map<String, Object> input) throws Exception
    {
        String jsonString = null;

        try
        {
            Template template   = CONFIGURATION.getTemplate(templateName);
            StringWriter writer = new StringWriter();

            template.process(input, writer);
            jsonString = writer.toString();
        }
        catch (Exception exception)
        {
            System.err.println("coult not process date with this template " + templateName);
           throw new IllegalArgumentException(exception);
        }

        return jsonString;
    }

    public static class MyObjectsManager {

        private final String fileName;

        MyObjectsManager(String fileName)
        {
            this.fileName = fileName;
        }

       public List<Object> getObjectsFromFile()
       {
           return null;
       }

    }

}