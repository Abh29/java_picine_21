package com.company;


/**
  This class contains the solution to this problem :
 * given a set of arithmetic functions (Z->Z) , what is the shortest
 * number of operations from this set to get from a giver integer a
 * to another one b and what are these operations;
 *
 * @author BOUTIFOUR ABDELHAK
 *
 * **/


import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class BFS_Search {

    private static Map<Integer,Integer> adjacencyList(int node , PrimitiveAction... rules){

        Map<Integer,Integer> out = new HashMap<>(rules.length);

        for (PrimitiveAction rule : rules) {
            out.putIfAbsent(rule.action(node , null),node);
        }

        return out;
    }

    private static boolean outOfBound(int start,int end , int current ,PrimitiveAction... rules) {
        List<Integer> extremes = new ArrayList<>(rules.length * 2 +2);
        int min , max ;

        extremes.add(start);
        extremes.add(end);

        for (PrimitiveAction rule:rules) {
            extremes.add(rule.action(start,null));
            extremes.add(rule.action(end,null));
        }

        //noinspection OptionalGetWithoutIsPresent
        max = extremes.stream().max(Integer::compareTo).get();
        min = extremes.stream().min(Integer::compareTo).get();

        return current > max || current < min;
    }

    private static String getRule(int before , int after , PrimitiveAction... rules){

        StringBuilder name = new StringBuilder();

        for (PrimitiveAction rule: rules) {
            if (rule.action(before,name) == after){
                return name.toString();
            }
        }
        return null;
    }

    public static int ShortestPath(int start , int end , PrimitiveAction... rules){

        ArrayDeque<Integer> childrenQueue = new ArrayDeque<>();
        Hashtable<Integer, Integer> pere = new Hashtable<>();
        int current = 0 ; int father ; int steps = -1 ;
        AtomicBoolean found = new AtomicBoolean(false);
        StringBuilder out = new StringBuilder();
        Map<Integer,Integer> adjStream;


        if (start == end){
            System.out.println(start +" => "+end +" in 0 steps there is no transformation needed !");
            return 0;
        }

        childrenQueue.add(start);

        while(!childrenQueue.isEmpty() && !found.get()){

            current = childrenQueue.pop();

            if(outOfBound(start, end , current , rules)){
                continue;
            }

            adjStream=  adjacencyList(current, rules)
                    .entrySet().stream().filter(entry -> {

                        if (entry.getKey() == end){
                            found.set(true);
                        }
                        return !pere.containsKey(entry.getKey());

                    })
                    .collect(Collectors.toMap(Map.Entry::getKey , Map.Entry::getValue));

            childrenQueue.addAll(adjStream.keySet());
            pere.putAll(adjStream);

        }

        if (!found.get()){
            StringBuilder ruleName = new StringBuilder();
            System.out.print("there is no path from " + start +" to "+ end +" using these rules (");
            for (PrimitiveAction rule: rules) {
                rule.action(start, ruleName);
                System.out.print(ruleName + ", ");
            }
            System.out.println(")");

        }else {

            out.insert(0,getRule(current,end , rules)+" ");
            steps = 1 ;

            while (current != start){
                father = pere.get(current);
                out.insert(0,getRule(father,current , rules)+" , ");
                current = father;
                steps++;
            }

            out.insert(0, start + " =>  ").append(" => ").append(end).append(" ( in ").append(steps).append(" steps )");

            System.out.println(out);

        }

        return steps;

    }

}
