import java.util.*;
// import java.lang.*;

public class merge {

    public static void main(String[] args) {
        ArrayList<Integer> a = random(Integer.parseInt(args[0]));
        System.out.println(a);
        System.out.println(sort(a));
        System.out.println(a);
    }
    
    public static ArrayList<Integer> sort(ArrayList<Integer> toSort) {
        ArrayList<ArrayList<Integer>> paired = pairs(split(toSort));
        while(paired.size() != 1) {
            paired = pairs(paired);
        }
        return paired.get(0);
    }

    public static ArrayList<ArrayList<Integer>> pairs(ArrayList<ArrayList<Integer>> toPair) {
        if(toPair.size() == 1) {
            return toPair;
        }
        ArrayList<ArrayList<Integer>> returnPairs = new ArrayList<ArrayList<Integer>>();
        int pairs = Math.floorDiv(toPair.size(),2);
        boolean even = toPair.size() % 2 == 0;

        for(int i = 0; i < (pairs*2)-1; i+=2) {
            ArrayList<Integer> currentPair = sort_combine(toPair.get(i),toPair.get(i+1));
            returnPairs.add(currentPair);
        }
       
        if(!even) {
            ArrayList<Integer> triplet = sort_combine(toPair.get(toPair.size()-1),returnPairs.get(returnPairs.size()-1));
            returnPairs.set(returnPairs.size()-1,triplet);
        }

        return returnPairs;
    }

    public static ArrayList<ArrayList<Integer>> split(ArrayList<Integer> toSplit) {
        ArrayList<ArrayList<Integer>> singles = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < toSplit.size(); i++) {
            ArrayList<Integer> single = new ArrayList<Integer>();
            single.add(toSplit.get(i));
            singles.add(single);
        }
        return singles;
    }

    public static ArrayList<Integer> random(int size) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        for(int i = 0; i < size; i++) {
            returnList.add((int)(Math.random()*100));
        }
        return returnList;
    }

    public static ArrayList<Integer> sort_combine(ArrayList<Integer> a, ArrayList<Integer> b) {
        int a_pointer = 0;
        int b_pointer = 0;
        int a_value = Integer.MAX_VALUE;
        int b_value = Integer.MAX_VALUE;
        ArrayList<Integer> combined = new ArrayList<Integer>();

        while(a.size() + b.size() != combined.size()) {
            if(a_pointer == a.size()) {
                a_value = Integer.MAX_VALUE;
            } else {
                a_value = a.get(a_pointer);
            }
            if(b_pointer == b.size()) {
                b_value = Integer.MAX_VALUE;
            } else {
                b_value = b.get(b_pointer);
            }
            if(a_value <= b_value) {
                combined.add(a_value);
                a_pointer++;
            } else {
                combined.add(b_value);
                b_pointer++;
            }
        }
        return combined;
    }
}