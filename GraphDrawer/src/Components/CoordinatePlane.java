package Components;

import Application.GraphDrawerApp;
import Utility.Graph;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CoordinatePlane extends JComponent {

    private String m_x_title = "X";
    private String m_y_title = "Y";

    private Integer m_scale = 50;

    private final Integer m_left_padding = 100;
    private final Integer m_bottom_padding = 100;

    private Double m_max_x_value = Double.MIN_VALUE;
    private Double m_max_y_value = Double.MIN_VALUE;
    private Double m_min_x_value = Double.MAX_VALUE;
    private Double m_min_y_value = Double.MAX_VALUE;

    private final ArrayList<Graph> m_graphs = new ArrayList<>();

    private GraphDrawerApp m_context = null;





    public CoordinatePlane(GraphDrawerApp _context) {
        m_context = _context;
    }



    public void SetScale(int _scale) { m_scale = _scale; }



    public void SetTitleX(String _x_title) { m_x_title = _x_title; }
    public void SetTitleY(String _y_title) { m_y_title = _y_title; }



    public void AddGraph(Graph _graph) {

        m_max_x_value = Math.max(_graph.GetMaxCoordinateX(), m_max_x_value);
        m_max_y_value = Math.max(_graph.GetMaxCoordinateY(), m_max_y_value);
        m_min_x_value = Math.min(_graph.GetMinCoordinateX(), m_min_x_value);
        m_min_y_value = Math.min(_graph.GetMinCoordinateY(), m_min_y_value);
        m_graphs.add(_graph);
        repaint();
    }



    protected void paintComponent(Graphics _draw_context) {

        super.paintComponent(_draw_context);
        Graphics2D ctx = (Graphics2D)_draw_context;
        ctx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        DrawPlane(ctx);

        DrawGraphs(ctx);
    }



    private void DrawGraphs(Graphics2D _ctx) {
        for (Graph graph:
             m_graphs) {
            DrawGraph(_ctx, graph);
        }
    }



    private void DrawGraph(Graphics2D _ctx, Graph _graph) {

        ArrayList<Point> points = TransformPoints(_graph.GetPoints());

        if (points.isEmpty())
            return;

        _ctx.setColor(_graph.GetColor());

        int radius = 6;

        Point prev_point = points.get(0);
        for (Point point:
             points) {
            _ctx.drawLine(prev_point.x, prev_point.y, point.x, point.y);
            _ctx.fillOval(point.x - radius / 2, point.y - radius / 2, radius, radius);

            prev_point = point;
        }
    }



    private ArrayList<Point> TransformPoints(ArrayList<Point> _points) {

        ArrayList<Point> transformed_points = new ArrayList<>();

        for (Point point:
             _points) {

            Point transformed_point = new Point();

            if (point.x < m_min_x_value || point.x > m_max_x_value) {
                continue;
            }
            if (point.y < m_min_y_value || point.y > m_max_y_value) {
                continue;
            }

            double max_val_interval_x = m_max_x_value - m_min_x_value;
            double point_val_interval_x = point.x - m_min_x_value;

            int x_pos = (int)((getWidth() - m_left_padding) * (point_val_interval_x / max_val_interval_x));
            x_pos += m_left_padding;

            double max_val_interval_y = m_max_y_value - m_min_y_value;
            double point_val_interval_y = point.y - m_min_y_value;

            int y_pos = (int)((getHeight() - m_bottom_padding) * (1 - point_val_interval_y / max_val_interval_y));


            transformed_point.x = x_pos;
            transformed_point.y = y_pos;

            transformed_points.add(transformed_point);
        }

        return transformed_points;
    }



    private void DrawPlane(Graphics2D _ctx) {

        _ctx.setColor(Color.LIGHT_GRAY);

        _ctx.fillRect(m_left_padding, 0, getWidth() - m_left_padding, getHeight() - m_bottom_padding);

        DrawGrid(_ctx, m_scale);

        DrawAxisX(_ctx, m_left_padding, m_bottom_padding, getWidth() - m_left_padding);
        DrawAxisY(_ctx, m_left_padding, m_bottom_padding, getHeight() - m_bottom_padding);

        DrawValues(_ctx, 5);
    }


    private void DrawAxisX(Graphics2D _ctx, int _x, int _y, int _length) {

        _ctx.setColor(Color.BLACK);

        int line_width = 6;
        int line_end_x = _x + _length;
        int line_end_y = getHeight() - _y;
        _ctx.fillRect(_x, getHeight() - _y, _length, line_width);



        Polygon cross = new Polygon(new int[]{line_end_x - 50, line_end_x - 50, line_end_x},
                new int[]{line_end_y - 12,
                        line_end_y + 12 + line_width / 2,
                        line_end_y + line_width / 2},
                3);

        Font font = new Font("Arial", Font.ITALIC, 30);
        _ctx.setFont(font);
        _ctx.drawString(m_x_title, line_end_x - 50, line_end_y + 50);

        _ctx.fillPolygon(cross);
    }


    private void DrawAxisY(Graphics2D _ctx, int _x, int _y, int _length) {

        _ctx.setColor(Color.BLACK);

        int line_width = 6;
        int line_end_x = _x;
        int line_end_y = getHeight() - _y - _length;
        _ctx.fillRect(_x - line_width, line_end_y, line_width, _length + 6);



        Polygon cross = new Polygon(new int[]{line_end_x - 3 * line_width,
                line_end_x + line_width * 3 / 2,
                line_end_x - line_width / 2},
                new int[]{line_end_y + 50,
                        line_end_y + 50,
                        line_end_y},
                3);

        Font font = new Font("Arial", Font.ITALIC, 30);
        _ctx.setFont(font);
        _ctx.drawString(m_y_title, line_end_x - 50, line_end_y + 50);

        _ctx.fillPolygon(cross);
    }


   private void DrawValues(Graphics2D _ctx, int _count) {

        double step_x_val = ((m_max_x_value - m_min_x_value) / (double) _count);
        double step_y_val = ((m_max_y_value - m_min_y_value) / (double) _count);

        if (m_graphs.isEmpty()) {
            step_x_val = 0.;
            step_y_val = 0.;
        }

        int step_x_coord = ((getWidth() - m_left_padding) / _count);
        int step_y_coord = ((getHeight() - m_bottom_padding) / _count);

        double x_val = m_min_x_value;
        double y_val = m_min_y_value;

       if (m_graphs.isEmpty()) {
           x_val = 0.;
           y_val = 0.;
       }

        int x_coord = m_left_padding;
        int y_coord = getHeight() - m_bottom_padding;

        Font font = new Font("Arial", Font.ITALIC, 15);
        _ctx.setFont(font);

        for (int i = 0; i < _count; i++)
        {
            _ctx.drawString(String.format("%.1f", x_val),
                            x_coord, getHeight() - m_bottom_padding + 30);
            _ctx.drawString(String.format("%.1f", y_val),
                            m_left_padding - String.format("%.1f", y_val).length() * 12, y_coord);

            x_val += step_x_val;
            y_val += step_y_val;

            x_coord += step_x_coord;
            y_coord -= step_y_coord;
        }

        x_coord = m_left_padding;
        y_coord = getHeight() - m_bottom_padding;

        int scale = 10;

        step_x_coord /= scale;
        step_y_coord /= scale;

        for (int i = 0; i < _count * scale; i++)
        {
            int coll_hegiht = (i % scale) == 0 ? 10 : 5;
            int coll_width = 2;

            _ctx.fillRect(x_coord, getHeight() - m_bottom_padding - coll_hegiht,
                          coll_width, coll_hegiht);

            _ctx.fillRect(m_left_padding, y_coord, coll_hegiht, coll_width);

            x_coord += step_x_coord;
            y_coord -= step_y_coord;
        }
    }



    private void DrawGrid(Graphics2D _ctx, int _step) {

        _ctx.setColor(Color.WHITE);

        int start = m_left_padding + _step;
        for (; start < getWidth(); start += _step)
            _ctx.drawLine(start, getHeight() - m_bottom_padding, start, 0);

        start = getHeight() - m_bottom_padding - _step;
        for (; start > 0; start -= _step)
            _ctx.drawLine(m_left_padding, start, getWidth(), start);
    }
}
