class Solution {
    double x0, y0, r; 
    public Solution(double radius, double x_center, double y_center) {
        x0 = x_center; y0 = y_center; r = radius;
    } 
    public double[] randPoint() {
        double x = -r + 2*r*Math.random();
        double y = -r + 2*r*Math.random();
        while (x * x + y * y > r * r) {
          x = -r + 2*r*Math.random();
          y = -r + 2*r*Math.random();
        }
        return new double[]{x0 + x , y0 + y};
    }}