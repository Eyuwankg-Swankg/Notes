#include <stdio.h>
typedef long long int lld;

// works only on sorted array
lld binary_search(lld arr[],lld l,lld r,lld searchElement){
    
    
    if(r>=l){
        lld mid=l+(r-l)/2;
        
        if(arr[mid]==searchElement)
            return mid;
        
        if(arr[mid]>searchElement)
            return binary_search(arr,l,mid-1,searchElement);
            
        if(arr[mid]<searchElement)
            return binary_search(arr,mid+1,r,searchElement);
    }
    
    return -1;
}

int main()
{
    lld n;
    scanf("%lld",&n);
    
    lld arr[n];
     
    for(lld i=0;i<n;i++)
        scanf("%lld",&arr[i]);    
    
    lld searchElement;
    scanf("%lld",&searchElement);
    
    lld position=binary_search(arr,0,n-1,searchElement);
    
    printf("%lld",position);
    
    return 0;
}
