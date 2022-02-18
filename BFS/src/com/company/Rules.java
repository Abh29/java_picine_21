package com.company;

public class Rules {


    //can add any rule (function Z->Z)
    //ps: the name variable is used to get the name of the method in printing the path after we found it ;



    public static int add_1(int in,StringBuilder name){
        PrimitiveAction.setName(name);
        return ++in;
    }
    public static int add_3(int in ,StringBuilder name){

        PrimitiveAction.setName(name);
        return in+3;
    }
    public static int multiply_4(int in , StringBuilder name){

        PrimitiveAction.setName(name);
        return 4*in;
    }
    public static int multiply_16(int in , StringBuilder name){

        PrimitiveAction.setName(name);
        return 16*in;
    }
    public static int multiply_minus_1(int in , StringBuilder name){

        PrimitiveAction.setName(name);
        return -1*in;
    }
    public static int add_12(int in , StringBuilder name){
        PrimitiveAction.setName(name);
        return in+12 ;
    }
    public static int add_9(int in , StringBuilder name){
        PrimitiveAction.setName(name);
        return in+9 ;
    }
    public static int add_0(int in , StringBuilder name){
        PrimitiveAction.setName(name);
        return in ;
    }
    public static int sub_1(int in , StringBuilder name){
        PrimitiveAction.setName(name);
        return --in ;
    }
    public static int sub_15(int in , StringBuilder name){
        PrimitiveAction.setName(name);
        return in - 15 ;
    }
    public static int divide_2(int in , StringBuilder name){
        PrimitiveAction.setName(name);
        if (in % 2 == 0)
            return in/2 ;
        else
            return in;
    }
    public static int divide_8(int in , StringBuilder name){
        PrimitiveAction.setName(name);
        if (in % 8 == 0)
            return in/8 ;
        else
            return in;
    }
    public static int power_2(int in , StringBuilder name){
        PrimitiveAction.setName(name);
        return in*in;
    }           //not to use with big numbers !
    public static int power_3(int in , StringBuilder name){
        PrimitiveAction.setName(name);
        return in*in*in;
    }           //not to use with big numbers !
}
