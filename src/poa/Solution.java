package poa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Solution {
    public double[] arr;

    public Solution(int n) {
        arr = new double[n];
    }

    public void randomize(Random random){
        for(int i = 0; i < arr.length; i++){
            arr[i] = random.nextDouble();
        }
    }

    public int[] returnJobOrder() {
        Integer[] order = new Integer[arr.length];

        for (int i = 0; i < arr.length; i++) {
            order[i] = i;
        }

        Arrays.sort(order, Comparator.comparingDouble(i -> arr[i]));

        int[] jobOrder = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            jobOrder[i] = order[i];
        }

        return jobOrder;
    }
}