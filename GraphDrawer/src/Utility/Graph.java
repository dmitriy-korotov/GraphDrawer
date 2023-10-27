package Utility;

import java.awt.*;
import java.util.ArrayList;

public class Graph {

    private Double m_min_x = 0.;
    private Double m_max_x = 0.;
    private Double m_min_y = 0.;
    private Double m_max_y = 0.;

    private ArrayList<Point> m_points = new ArrayList<>();





    public Graph(ArrayList<Point> _points) {
        SetPoints(_points);
    }



    public void SetPoints(ArrayList<Point> _points) {

        m_points = _points;
        m_max_x = __getMaxCoordinateX();
        m_min_x = __getMinCoordinateX();
        m_max_y = __getMaxCoordinateY();
        m_min_y = __getMinCoordinateY();
    }



    public ArrayList<Point> GetPoints() { return m_points; }



    public double __getMinCoordinateX() {

        double min_coord_x = Double.MAX_VALUE;
        for (Point point:
                m_points) {
            min_coord_x = Math.min(min_coord_x, point.x);
        }
        return min_coord_x;
    }

    public double GetMinCoordinateX() { return m_min_x; }



    public double __getMaxCoordinateX() {

        double max_coord_x = Double.MIN_VALUE;
        for (Point point:
                m_points) {
            max_coord_x = Math.max(max_coord_x, point.x);
        }
        return max_coord_x;
    }

    public double GetMaxCoordinateX() { return m_max_x; }



    public double __getMinCoordinateY() {

        double min_coord_y = Double.MAX_VALUE;
        for (Point point:
                m_points) {
            min_coord_y = Math.min(min_coord_y, point.y);
        }
        return min_coord_y;
    }

    public double GetMinCoordinateY() { return m_min_y; }



    public double __getMaxCoordinateY() {

        double max_coord_y = Double.MIN_VALUE;
        for (Point point:
                m_points) {
            max_coord_y = Math.max(max_coord_y, point.y);
        }
        return max_coord_y;
    }

    public double GetMaxCoordinateY() { return m_max_y; }

}
