package com.thealgorithms.datastructures.stacks;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an integer array. The task is to find the maximum of the minimum of
 * every window size in the array. Note: Window size varies from 1 to the size
 * of the Array.
 * <p>
 * For example,
 * <p>
 * N = 7
 * arr[] = {10,20,30,50,10,70,30}
 * <p>
 * So the answer for the above would be : 70 30 20 10 10 10 10
 * <p>
 * We need to consider window sizes from 1 to length of array in each iteration.
 * So in the iteration 1 the windows would be [10], [20], [30], [50], [10],
 * [70], [30]. Now we need to check the minimum value in each window. Since the
 * window size is 1 here the minimum element would be the number itself. Now the
 * maximum out of these is the result in iteration 1. In the second iteration we
 * need to consider window size 2, so there would be [10,20], [20,30], [30,50],
 * [50,10], [10,70], [70,30]. Now the minimum of each window size would be
 * [10,20,30,10,10] and the maximum out of these is 30. Similarly we solve for
 * other window sizes.
 *
 * @author sahil
 */
public class MaximumMinimumWindow {

    // Array for manual code coverage checks
    public static int[] coverage = new int[14];

    /**
     * This function contains the logic of finding maximum of minimum for every
     * window size using Stack Data Structure.
     *
     * @param arr Array containing the numbers
     * @param n Length of the array
     * @return result array
     */
    public static int[] calculateMaxOfMin(int[] arr, int n) {

        //Return null if the size of the array is different from n
        if (n != arr.length)
            return null;
        
        //Return null if the size of the array is 0
        if (n == 0 || arr.length == 0){
            int[] ans_empty = new int[]{};
            return ans_empty;
        }
            
            
        Stack<Integer> s = new Stack<>();
        int left[] = new int[n + 1];
        int right[] = new int[n + 1];
        for (int i = 0; i < n; i++) {
            coverage[0] += 1;
            left[i] = -1;
            right[i] = n;
        }

        for (int i = 0; i < n; i++) {
            coverage[1] += 1;
            while (!s.empty() && arr[s.peek()] >= arr[i]) {
                coverage[2] += 1;
                s.pop();
            }

            if (!s.empty()) {
                coverage[3] += 1;
                left[i] = s.peek();
            } else {
                coverage[4] += 1;
            }

            s.push(i);
        }

        while (!s.empty()) {
            coverage[5] += 1;
            s.pop();
        }

        for (int i = n - 1; i >= 0; i--) {
            coverage[6] += 1;
            while (!s.empty() && arr[s.peek()] >= arr[i]) {
                coverage[7] += 1;
                s.pop();
            }

            if (!s.empty()) {
                coverage[8] += 1;
                right[i] = s.peek();
            } else {
                coverage[9] += 1;
            }

            s.push(i);
        }

        int ans[] = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            coverage[10] += 1;
            ans[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            coverage[11] += 1;
            int len = right[i] - left[i] - 1;

            ans[len] = Math.max(ans[len], arr[i]);
        }

        for (int i = n - 1; i >= 1; i--) {
            coverage[12] += 1;
            ans[i] = Math.max(ans[i], ans[i + 1]);
        }

        // Print the result
        System.out.print("Answer: [");
        for (int i = 1; i <= n; i++) {
            coverage[13] += 1;
            System.out.print(ans[i] + " ");
        }
        System.out.println("]");
        return ans;
    }

    public static void main(String args[]) {
        int[] arr = new int[]{10, 20, 30, 50, 10, 70, 30};
        int[] target = new int[]{70, 30, 20, 10, 10, 10, 10};
        int[] res = calculateMaxOfMin(arr, arr.length);
        assert Arrays.equals(target, res);
    }

}