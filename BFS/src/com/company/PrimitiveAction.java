package com.company;

public interface PrimitiveAction {

    int action (int v,StringBuilder name);

    static void setName(StringBuilder name) {
        if (name != null){
            name.delete(0,name.length());
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            name.append(stackTrace[1].getMethodName());
        }
    }

}
