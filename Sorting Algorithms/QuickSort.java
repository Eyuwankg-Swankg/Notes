public class QuickSort{

    public static int partition(int array[],int low,int high){

        int pivot=array[high];
        int i=low-1;

        for(int j=low;j<high;j++){
            if(array[j]<pivot){
                i++;
                int temp=array[i];
                array[i]=array[j];
                array[j]=temp;
            }
        }
        int temp=array[i+1];
        array[i+1]=array[high];
        array[high]=temp;

        return i+1;
    }
    public static void quickSort(int array[],int low,int high){

        if(low<high){

            int pivot=partition(array,low,high);

            quickSort(array, low, pivot-1);
            quickSort(array, pivot+1,high);
        }
        
    }
    public static void main(String args[]) {

        int array[] = new int[] { 9, 8, 7, 6, 4, 3, 2, 11, 123, 0 };

        quickSort(array,0,array.length-1);

        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }
    
}
