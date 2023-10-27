package Utility;

import com.sun.source.tree.BreakTree;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class PointsReader {

    private final ArrayList<Point> m_points = new ArrayList<>();
    private String m_separator = " ";



    public PointsReader(String _path_to_points) throws IOException {
        ReadPoints(_path_to_points);
    }



    public ArrayList<Point> GetPoints() { return m_points; }



    public void SetCoordinateSeparator(String _separator) { m_separator = m_separator; }



    public void ReadPoints(String _path_to_points) throws IOException {

        File file = new File(_path_to_points);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            while (true) {

                String line = reader.readLine();

                if (Objects.equals(line, "\n") || Objects.equals(line, "") || line == null) {
                    break;
                }

                String[] coordinates = line.split(m_separator);

                Point point = new Point();
                point.x = Integer.parseInt(coordinates[0]);
                point.y = Integer.parseInt(coordinates[1]);

                m_points.add(point);

            }

        }
    }
}
