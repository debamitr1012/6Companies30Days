class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
    if( mountainArr.length()<=10000){
        int peak=findpeakindex( target, mountainArr);
        int firsthalf= orderagnosticbs(mountainArr,target,0,peak);
        if(firsthalf!=-1){
            return firsthalf;
        } 
        return (orderagnosticbs(mountainArr,target,peak+1,mountainArr.length()-1));}
        return -1;
    }  
    public static int findpeakindex(int target,MountainArray mountainArr)  {
        int start=0;
        int end=mountainArr.length()-1;
        while(start<end){
            int mid=(start+end)/2;
            if(mountainArr.get(mid)>mountainArr.get(mid+1)){
                end=mid;
            }
            else{
                start=mid+1;
            }
        }
        return start;
        }
    public static int orderagnosticbs(MountainArray mountainArr,int target,int start,int end){
        Boolean a=(mountainArr.get(start)<mountainArr.get(end));
        int mid=0;
        while(start<=end){
            mid=start+(end-start)/2;
            if(mountainArr.get(mid)==target){
                return mid;
            }
            if(a){
                if(mountainArr.get(mid)>target){
                    end=mid-1;
                }
                else {
                    start=mid+1;
                }
                
            }
            else{
                if(mountainArr.get(mid)>target){
                    start=mid+1;
                }
                else {
                                end=mid-1;
                }
              
            }
        }
        return -1;
    } 
}