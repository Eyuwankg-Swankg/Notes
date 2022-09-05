#include <stdio.h>
typedef long long int lld;

void swap(lld *a,lld *b){
    lld temp=*a;
    *a=*b;
    *b=temp;
}

// choose left most element as pivot
lld partition(lld arr[],lld low,lld high){
    
    // take last element as pivot 
    lld pivot=arr[high];
    lld i=low-1;
    // traverse from left to right
    // elements less tha pivot gets swaped with 'i'
    // finally pivot replaced with 'i' as left of pivot is lower and right is higher
    for(lld j=low;j<=high;j++){
        if(arr[j]<pivot){
            //takes all smaller element and place it to left
            i++;
            swap(&arr[j],&arr[i]);
        }
    }
    //change pivot to center
    swap(&arr[i+1],&arr[high]);
    //return pivot potision   
    return i+1;
}


void quick_sort(lld arr[],lld low,lld high){
    
    if(low<high){
        lld pivot=partition(arr,low,high);
        
        quick_sort(arr,low,pivot-1);
        quick_sort(arr,pivot+1,high);
    }
}

int main()
{
    lld arr[1000],n;
    scanf("%lld",&n);
 
    // get input   
    for(lld i=0;i<n;i++)
        scanf("%lld",&arr[i]);
    quick_sort(arr,0,n-1);
    for(lld i=0;i<n;i++)
        printf("%lld ",arr[i]);
    printf("\n");
    return 0;
}
