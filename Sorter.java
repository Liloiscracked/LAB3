package LAB3;
// test branch
public class Sorter {
    public static void main(String[] args) {
        int [] intarray = {5,2,22,1,0,-11,98,88};
        selectionSort(intarray);
        for(int elem : intarray)
            System.out.println(elem);
    }
    public static void sort(int[] arr) {
        selectionSort(arr);
    }

    public void Sort(int[] arr){
        InsertionSort(arr);
    }
    
    public static void selectionSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++){
            int index = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[index]){
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }

    public void InsertionSort(int [] array) {
            int n = array.length;
            for (int i = 1; i < n; ++i) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;
                }
                array[j + 1] = key;
            }
    }
}
