class Solution {
    public int totalFruit(int[] fruits) {
    int lastfruit=-1;
    int secondlastfruit=-1;
    int lastfruitcount=0;
    int currentmax=0;
    int  max=0;
    for(int fruit:fruits){
        if(fruit == lastfruit || fruit == secondlastfruit){
            currentmax+=1;}
        else{
            currentmax=lastfruitcount+1;}
        if(fruit==lastfruit){
            lastfruitcount+=1;}
        else{
            lastfruitcount=1;}
        if(fruit!=lastfruit){
            secondlastfruit=lastfruit;
            lastfruit=fruit;}
        max=Math.max(currentmax,max);}
    return max;
    }}