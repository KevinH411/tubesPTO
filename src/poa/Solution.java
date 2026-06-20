package poa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Solution {
    //Arr adalah angka angka yang dimasukin ke equation-equationnya.
    //Buat diubah jadi informasi beneran(job order), pake returnJobOrder
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