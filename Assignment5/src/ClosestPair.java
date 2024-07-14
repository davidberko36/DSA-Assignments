import java.util.Arrays;
import java.util.Comparator;

class Point {
    double x, y;
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosestPair {
    
    // A utility function to find the distance between two points
    static double dist(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    // A utility function to find the minimum of two double values
    static double min(double x, double y) {
        return (x < y) ? x : y;
    }

    // A utility function to find the distance between the closest points of strip of given size
    static double stripClosest(Point strip[], int size, double d) {
        double min = d; // Initialize the minimum distance as d

        // Pick all points one by one and try the next points until the difference between y coordinates is smaller than d.
        for (int i = 0; i < size; ++i)
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j)
                if (dist(strip[i], strip[j]) < min)
                    min = dist(strip[i], strip[j]);

        return min;
    }

    // A recursive function to find the smallest distance in the specified range of points
    static double closestUtil(Point Px[], Point Py[], int n) {
        // If there are 2 or 3 points, then use brute force
        if (n <= 3)
            return bruteForce(Px, n);

        // Find the middle point
        int mid = n / 2;
        Point midPoint = Px[mid];

        // Divide points in y sorted array around the vertical line
        Point Pyl[] = new Point[mid]; // y sorted points on the left of the vertical line
        Point Pyr[] = new Point[n - mid]; // y sorted points on the right of the vertical line

        int li = 0, ri = 0; // indexes of left and right subarrays
        for (int i = 0; i < n; i++) {
            if (Py[i].x <= midPoint.x && li < mid)
                Pyl[li++] = Py[i];
            else
                Pyr[ri++] = Py[i];
        }

        // Consider the vertical line passing through the middle point calculate the smallest distance dl in the left of the middle point and dr in the right side
        double dl = closestUtil(Arrays.copyOfRange(Px, 0, mid), Pyl, mid);
        double dr = closestUtil(Arrays.copyOfRange(Px, mid, n), Pyr, n - mid);

        // Find the smaller of two distances
        double d = min(dl, dr);

        // Build an array strip[] that contains points close (closer than d) to the line passing through the middle point
        Point strip[] = new Point[n];
        int j = 0;
        for (int i = 0; i < n; i++)
            if (Math.abs(Py[i].x - midPoint.x) < d)
                strip[j] = Py[i];
        j++;

        // Find the closest points in strip. Return the minimum of d and closest distance in strip[]
        return min(d, stripClosest(strip, j, d));
    }

    // A function that finds the smallest distance between points in the specified array
    static double closest(Point P[], int n) {
        Point Px[] = new Point[n];
        Point Py[] = new Point[n];
        for (int i = 0; i < n; i++) {
            Px[i] = P[i];
            Py[i] = P[i];
        }

        Arrays.sort(Px, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                return Double.compare(a.x, b.x);
            }
        });
        
        Arrays.sort(Py, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                return Double.compare(a.y, b.y);
            }
        });

        return closestUtil(Px, Py, n);
    }

    // A utility function to find the distance between two points when the number of points is small
    static double bruteForce(Point P[], int n) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                if (dist(P[i], P[j]) < min)
                    min = dist(P[i], P[j]);
        return min;
    }

    public static void main(String[] args) {
        Point P[] = { new Point(2, 3), new Point(12, 30), new Point(40, 50), new Point(5, 1), new Point(12, 10), new Point(3, 4) };
        int n = P.length;
        System.out.println("The smallest distance is " + closest(P, n));
    }
}
