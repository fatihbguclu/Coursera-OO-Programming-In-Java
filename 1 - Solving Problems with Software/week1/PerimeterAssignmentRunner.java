import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numOfPoints = 0;
        for (Point pt : s.getPoints()) {
            numOfPoints += 1;
        }
        return numOfPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double numOfPoints = (double)getNumPoints(s);
        double perimeter = getPerimeter(s);
        
        return (perimeter / numOfPoints);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double largestSide = 0;
        for (Point currPt : s.getPoints()){
            double side = prevPt.distance(currPt);
            if(side >= largestSide){
                largestSide = side;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            if(prevPt.getX() > currPt.getX()){
                largestX = prevPt.getX();
            }else{
                largestX = currPt.getX();
            }
            prevPt = currPt;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        //FileResource fr = new FileResource();
        double largestPerimeter = 0.0;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(perimeter > largestPerimeter){
                largestPerimeter = perimeter;
            }
        }
        
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        File temp = null;    // replace this code
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(perimeter > largestPerimeter){
                largestPerimeter = perimeter;
                temp = f;
            }
        }
        
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        int numOfPoints = getNumPoints(s);
        double length = getPerimeter(s);
        double averageLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double LargestX = getLargestX(s);
        
        System.out.println("perimeter = " + length);
        System.out.println("Number Of Points = " + numOfPoints);
        System.out.println("Average Length = " + averageLength);
        System.out.println("Largest Side = " + largestSide);
        System.out.println("Largest X = " + LargestX);
        
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largest = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter in Multiple Test Files : " + largest);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String file = getFileWithLargestPerimeter();
        System.out.println("File with Largest Perimeter is " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
        //pr.testPerimeter();
    }
}
