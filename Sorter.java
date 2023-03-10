package LAB3;

import java.util.LinkedList;
import java.util.Queue;

public class Sorter {
    public static void main(String[] args) {
        System.out.println("v2");
    }

    public static void sort(int[] arr) {
        sort(arr);
    }

public class InsertionSort {
	/*Function to sort array using insertion sort*/
	void sort(int arr[])
	{
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			int j = i - 1;

			/* Move elements of arr[0..i-1], that are
			greater than key, to one position ahead
			of their current position */
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}

/* This code is contributed by Rajat Mishra. */
}
}
