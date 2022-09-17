#include <stdio.h>
typedef long long int lld;

void insertion_sort(lld arr[],lld n){
    
    for(lld i=1;i<n;i++){
        
        lld key=arr[i];
        lld j=i-1;
        
        while(j>=0 && arr[j]>key){
            arr[j+1]=arr[j];
            j--;
        }
        arr[j+1]=key;
    }
}

int main()
{
    lld n;
    scanf("%lld",&n);
    
    lld arr[n];
    
    for(lld i=0;i<n;i++)
        scanf("%lld",&arr[i]);    
    
    insertion_sort(arr,n);
    
    for(lld i=0;i<n;i++)
        printf("%lld ",arr[i]);
    printf("\n");
    return 0;
}
