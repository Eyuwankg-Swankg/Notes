public class BubbleSort {

    public static void main(String[] args) {

        int array[] = new int[] { 9, 8, 7, 6, 4, 3, 2, 11, 123, 0 };

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

            }
        }

        for(int i=0;i<array.length;i++)
            System.out.print(array[i]+" ");
        System.out.println();
    }
}
