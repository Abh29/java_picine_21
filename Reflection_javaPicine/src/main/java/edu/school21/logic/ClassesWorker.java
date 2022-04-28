package edu.school21.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClassesWorker {

    private final String PACKAGE_NAME;
    private final Map<String, Class> CLASS_MAP;
    private final Map<String, Method> METHOD_MAP;

    public ClassesWorker(String packageName) throws Exception {
        this.PACKAGE_NAME = packageName;
        this.CLASS_MAP = findAllClasses();
        this.METHOD_MAP = new HashMap<>();
    }


    public Map<String, Class> getClassMap() {
        return CLASS_MAP;
    }

    public Class printClassInfo(String className) throws Exception{
        Class myClass = CLASS_MAP.get(className);
        if(myClass == null)
            throw new Exception("can't find class " + className);

        System.out.println("fields :");
        for (Field f: myClass.getDeclaredFields()) {
            System.out.println("\t" + f.getType().getSimpleName() + " " + f.getName());
        }
        System.out.println("methods:");
        for (Method m : myClass.getDeclaredMethods()){
            if (m.getName().equals("toString"))
                continue;
            System.out.print("\t");
            String prototype = methodPrototype(m);
            METHOD_MAP.put(prototype.substring(prototype.indexOf(" ")).trim(), m);
            System.out.println(prototype);
        }
        return myClass;
    }

    public Object getInstanceOf(Class myClass) throws Exception {

        Object instance = myClass.getConstructor().newInstance();

        for (Field f : instance.getClass().getDeclaredFields()) {
            System.out.println(f.getName());
            setField(instance, f);
        }

        return instance;
    }

    public void updateField(Object o, String fieldName) throws Exception{

        for (Field f : o.getClass().getDeclaredFields()) {
            if (f.getName().equals(fieldName))
            {
                System.out.println("Enter " + f.getType().getSimpleName() + " value:");
                setField(o, f);
                return;
            }
        }

        throw new Exception("can't find field " + fieldName + " in " + o.getClass().getName());

    }

    public void callMethod(Object o, String methodName) throws Exception {

        Method m = METHOD_MAP.get(methodName);

        if (m == null)
            throw new NoSuchMethodException(methodName);

        Object ret = m.invoke(o, setParameters(m));
        if (!m.getReturnType().toString().equals("null"))
            System.out.println("Method returned:\n" + ret);
    }



    private Map<String, Class> findAllClasses() throws Exception{

        Map<String, Class> out = new HashMap<>();

        String path = PACKAGE_NAME.replace(".", "/");

        InputStream stream = ClassLoader
                .getSystemClassLoader()
                .getResourceAsStream(path);

        if (stream == null)
            throw new Exception("can't find package " + PACKAGE_NAME);

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        String line;
        try{
            while ((line = reader.readLine()) != null){
                if (line.endsWith(".class")){
                    line = line.substring(0, line.lastIndexOf(".class"));
                    out.put(line, Class.forName(PACKAGE_NAME + "." + line));
                }
            }
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (out.isEmpty())
            throw new Exception("no class was found in package " + PACKAGE_NAME);

        return out;
    }

    private String methodPrototype(Method m){

        StringBuilder sb = new StringBuilder("");

        sb.append(m.getReturnType().getSimpleName())
                .append(" ")
                .append(m.getName())
                .append("(");

        Parameter[] parameters = m.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            sb.append(parameters[i].getType().getSimpleName());
            if (i < parameters.length - 1)
                sb.append(", ");
        }

        sb.append(")");
        return sb.toString();
    }

    private void setField(Object instance, Field f) throws Exception{
        Scanner scanner = new Scanner(System.in);

        boolean access = f.isAccessible();
        int mod = f.getModifiers();

        f.setAccessible(true);
        Field modifier = f.getClass().getDeclaredField("modifiers");
        modifier.setAccessible(true);
        modifier.setInt(f, mod & ~Modifier.FINAL);

        switch (f.getType().getSimpleName()){
            case "int":
            case "Integer":
                f.set(instance, Integer.parseInt(scanner.nextLine()));
                break;
            case "double":
            case "Double":
                f.set(instance, Double.parseDouble(scanner.nextLine()));
                break;
            case "bool":
            case "Boolean":
                f.set(instance, Boolean.parseBoolean(scanner.nextLine()));
                break;
            case "long":
            case "Long":
                f.set(instance, Long.parseLong(scanner.nextLine()));
                break;
            case "String":
                f.set(instance, scanner.nextLine());
                break;
            default:
                throw new Exception("unsupported type was found in class " + instance.getClass().getName() + " type : " + f.getType());
        }

        modifier.setInt(f, mod);
        f.setAccessible(access);
    }

    private Object[] setParameters(Method m) throws Exception {
        Scanner scanner = new Scanner(System.in);

        m.setAccessible(true);

        Object[] out = new Object[m.getParameterCount()];
        int i = 0;

        for (Parameter p: m.getParameters()) {

            System.out.println("Enter " + p.getType().getSimpleName() + " value:");
            switch (p.getType().getSimpleName()) {
                case "int":
                case "Integer":
                    out[i++] = Integer.parseInt(scanner.nextLine());
                    break;
                case "double":
                case "Double":
                    out[i++] = Double.parseDouble(scanner.nextLine());
                    break;
                case "bool":
                case "Boolean":
                    out[i++] = Boolean.parseBoolean(scanner.nextLine());
                    break;
                case "long":
                case "Long":
                    out[i++] = Long.parseLong(scanner.nextLine());
                    break;
                case "String":
                    out[i++] = scanner.nextLine();
                    break;
                default:
                    throw new Exception("unsupported type was found as parameter for " + methodPrototype(m));

            }
        }
        return out;
    }


}
