import java.lang.*;
import java.util.*;

public class BinarySearch {

    // (array,LeftMostPosition,RightMostPosition,SearchElement)
    public int binarySearch(int array[], int l, int r, int searchElement) {

        while (r >= l) {

            int mid = l + (r - l) / 2;

            if (array[mid] == searchElement)
                return mid;
            else if (array[mid] > searchElement)
                return binarySearch(array, l, mid - 1, searchElement);
            else if (array[mid] < searchElement)
                return binarySearch(array, mid + 1, r, searchElement);
        }

        return -1;

    }

    public static void main(String args[]) {

        BinarySearch bs = new BinarySearch();
        int array[] = new int[] { 20,30,214,314,23,234,234,23121,0,1,9};

        // binary search only works on sorted array
        Arrays.sort(array);

        // (array,LeftMostPosition,RightMostPosition,SearchElement)
        int position = bs.binarySearch(array, 0, 10, 23);

        System.out.println(position);

    }
}