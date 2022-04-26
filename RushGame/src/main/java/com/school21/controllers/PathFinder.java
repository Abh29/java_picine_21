package com.school21.controllers;

import com.school21.data.IntegerPair;

import java.util.*;

public class PathFinder {
    private boolean[][] pathMap;

    public PathFinder(int size){
        pathMap = new boolean[size][size];
    }

    public void setPathMap(char[][] map, char allowed) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                pathMap[0][0] = (map[i][j] == allowed);
            }
        }
    }

    private boolean isLastStep(AStarNode current, IntegerPair goal){
        double cx = current.current.getX();
        double cy = current.current.getY();
        double dx = goal.getX();
        double dy = goal.getY();

        if (cx == dx && (dy == cy +1 || dy == cy -1))
            return true;
        if (cy == dy && (dx == cx + 1 || dx == cx - 1))
            return true;
        return false;
    }

    private ArrayList<AStarNode> getNeighbors(AStarNode current, IntegerPair goal){
        ArrayList<AStarNode> out = new ArrayList<>();
        int x = current.current.getX();
        int y = current.current.getY();

        if (x > 0 && pathMap[x-1][y])
            out.add(new AStarNode(new IntegerPair(x-1, y), current, goal));
        if (x < pathMap.length - 1 && pathMap[x+1][y])
            out.add(new AStarNode(new IntegerPair(x+1, y), current, goal));
        if (y > 0 && pathMap[x][y - 1])
            out.add(new AStarNode(new IntegerPair(x, y-1), current, goal));
        if (y < pathMap[0].length - 1 && pathMap[x][y+1])
            out.add(new AStarNode(new IntegerPair(x, y+1), current, goal));

        return out;
    }


    public ArrayList<IntegerPair> aStar(IntegerPair start, IntegerPair goal){
        ArrayList<IntegerPair> path = new ArrayList<>();
        ArrayList<AStarNode> close = new ArrayList<>();
        ArrayList<AStarNode> open = new ArrayList<>();


        open.add(new AStarNode(start, null, goal));
        while (!open.isEmpty()){
           open.sort(AStarNode::compareTo);
            AStarNode current = open.remove(0);
           for (AStarNode n: getNeighbors(current, goal)) {
               if (n.current.equals(goal))
                {
                    close.add(n);
                    path = getPath(n, close);
                    return path;
                }
                if (close.contains(n))
                    continue;
                if (open.contains(n))
                {
                    AStarNode t = open.get(open.indexOf(n));
                    if (n.fCoast < t.fCoast){
                        t.gCoast = n.gCoast;
                        t.fCoast = n.fCoast;
                        t.father = n.father;
                    }
                }else
                    open.add(n);
            }
           close.add(current);
        }
        return path;
    }


    public ArrayList<IntegerPair> getPath(AStarNode end, ArrayList<AStarNode> closed){
        ArrayList<IntegerPair> out = new ArrayList<>();
        AStarNode father;
        AStarNode current;

        current = end;
        while(current != null){
            out.add(current.current);
            father = new AStarNode(current.father);
            if (closed.contains(father))
                current = closed.get(closed.indexOf(father));
            else
                current = null;
        }
        return out;
    }

    private class AStarNode implements Comparable<AStarNode>{

        private IntegerPair current = null;
        private IntegerPair father = null;
        private double  fCoast;
        private double  hCoast;
        private double  gCoast;

        AStarNode(IntegerPair current){
            this.father = current;
        }

        public AStarNode(IntegerPair current, AStarNode father, IntegerPair goal) {
            this.current = current;
            if (father != null){
                this.father = father.current;
                this.gCoast = father.gCoast + 1;
                this.hCoast = distance(current, goal);
            }else {
                this.father = new IntegerPair(-1, -1);
                this.gCoast = 0;
                this.hCoast = 0;
            }
            fCoast = gCoast + hCoast;
        }

        private double distance(IntegerPair p1, IntegerPair p2){
            return (Math.sqrt((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p2.getY() - p1.getY()) * (p2.getY() - p1.getY())));
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AStarNode aStarNode = (AStarNode) o;
            return current.equals(aStarNode.current);
        }

        @Override
        public int hashCode() {
            return Objects.hash(current);
        }


        @Override
        public int compareTo(AStarNode o) {
            if (this == o) return 0;
            if (this.fCoast == o.fCoast) return (int) (this.hCoast - o.hCoast);
            return (int) (this.fCoast - o.fCoast);
        }
    }

}
