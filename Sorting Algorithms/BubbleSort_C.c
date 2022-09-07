#include <stdio.h>
typedef long long int lld;

void swap(lld *a,lld *b){
    
    lld temp=*a;
    *a=*b;
    *b=temp;
}

void bubble_sort(lld arr[],lld n){
  
  for(lld i=0;i<n-1;i++){
      
      for(lld j=0;j<n-i-1;j++){
          
          if(arr[j]>arr[j+1])
            swap(&arr[j],&arr[j+1]);
      }
  }  
  
}

int main()
{
    lld n;
    scanf("%lld",&n);
    
    lld arr[n];
    
    for(lld i=0;i<n;i++)
        scanf("%lld",&arr[i]);    
    
    bubble_sort(arr,n);
    
    for(lld i=0;i<n;i++)
        printf("%lld ",arr[i]);
    printf("\n");
    return 0;
}
