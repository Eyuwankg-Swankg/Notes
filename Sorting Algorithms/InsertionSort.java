public class InsertionSort {

    public static void main(String args[]) {

        int array[] = new int[] { 9, 8, 7, 6, 4, 3, 2, 11, 123, 0 };

        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j > -1 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }

}
