import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickHull {

    public List<Point> quickHull(List<Point> points) {
        if (points.size() <= 1) {
            return points;
        }
        
        // Find the points with the minimum and maximum x-coordinates
        Point A = points.stream().min((p1, p2) -> Integer.compare(p1.x, p2.x)).get();
        Point B = points.stream().max((p1, p2) -> Integer.compare(p1.x, p2.x)).get();
        
        List<Point> leftSet = new ArrayList<>();
        List<Point> rightSet = new ArrayList<>();
        
        for (Point p : points) {
            if (isLeft(A, B, p)) {
                leftSet.add(p);
            } else if (isLeft(B, A, p)) {
                rightSet.add(p);
            }
        }
        
        List<Point> hull = new ArrayList<>();
        hull.addAll(findHull(leftSet, A, B));
        hull.addAll(findHull(rightSet, B, A));
        
        return hull;
    }

    private List<Point> findHull(List<Point> points, Point P, Point Q) {
        List<Point> hull = new ArrayList<>();
        if (points.isEmpty()) {
            hull.add(Q);
            return hull;
        }
        
        // Find the point C that is the farthest from the line PQ
        Point C = points.stream().max((p1, p2) -> Double.compare(distanceFromLine(P, Q, p1), distanceFromLine(P, Q, p2))).get();
        
        List<Point> leftSetPC = new ArrayList<>();
        List<Point> leftSetCQ = new ArrayList<>();
        
        for (Point p : points) {
            if (isLeft(P, C, p)) {
                leftSetPC.add(p);
            } else if (isLeft(C, Q, p)) {
                leftSetCQ.add(p);
            }
        }
        
        hull.addAll(findHull(leftSetPC, P, C));
        hull.addAll(findHull(leftSetCQ, C, Q));
        
        return hull;
    }

    private double distanceFromLine(Point P, Point Q, Point R) {
        return Math.abs((Q.y - P.y) * R.x - (Q.x - P.x) * R.y + Q.x * P.y - Q.y * P.x);
    }

    private boolean isLeft(Point P, Point Q, Point R) {
        return (Q.x - P.x) * (R.y - P.y) - (Q.y - P.y) * (R.x - P.x) > 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Point> points = new ArrayList<>();
        
        System.out.println("Enter the number of points:");
        int n = scanner.nextInt();
        
        System.out.println("Enter the points as x y pairs:");
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add(new Point(x, y));
        }
        
        QuickHull qh = new QuickHull();
        
        // Measure the time taken to execute the algorithm
        long startTime = System.currentTimeMillis();
        List<Point> hull = qh.quickHull(points);
        long endTime = System.currentTimeMillis();
        
        // Display the time taken
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        
        System.out.println("Convex Hull:");
        for (Point p : hull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
        
        scanner.close();
    }
}
