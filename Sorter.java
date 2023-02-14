package LAB3;
// test branch
public class Sorter {
    public static void main(String[] args) {
        int [] intarray = {5,2,22,1,0,-11,98,88};
        selectionSort(intarray);
        for(int elem : intarray)
            System.out.println(elem);
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
}
