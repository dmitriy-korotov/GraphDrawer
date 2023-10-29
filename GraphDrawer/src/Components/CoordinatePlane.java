package Components;

import Utility.Graph;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class CoordinatePlane extends JComponent implements MouseInputListener, MouseWheelListener {

    private String m_x_title = "X";
    private String m_y_title = "Y";

    private Integer m_scale = 50;

    private final Integer m_left_padding = 200;
    private final Integer m_bottom_padding = 100;

    private Point m_last_cursor_position = new Point();

    private Double m_max_x_value = Double.MIN_VALUE;
    private Double m_max_y_value = Double.MIN_VALUE;
    private Double m_min_x_value = Double.MAX_VALUE;
    private Double m_min_y_value = Double.MAX_VALUE;

    private boolean m_is_log_scale = false;

    private final ArrayList<Graph> m_graphs = new ArrayList<>();





    public CoordinatePlane() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }



    public void EnableLogScale() {

        m_is_log_scale = true;
        m_x_title = "Log(" + m_x_title + ")";
        m_y_title = "Log(" + m_y_title + ")";
        repaint();
    }

    public void DisableLogScale() {

        m_is_log_scale = false;
        int begin_x_arg = m_x_title.indexOf("(");
        m_x_title = m_x_title.substring(begin_x_arg + 1, m_x_title.indexOf(")"));
        int begin_y_arg = m_y_title.indexOf("(");
        m_y_title = m_y_title.substring(begin_y_arg + 1, m_y_title.indexOf(")"));
        repaint();
    }

    public boolean IsLogScaling() { return m_is_log_scale; }



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

        DrawPlaneWithGraphs(ctx);
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

        double max_x_val = m_is_log_scale ? Math.log(m_max_x_value) : m_max_x_value;
        double min_x_val = m_is_log_scale ? Math.log(m_min_x_value) : m_min_x_value;
        double max_y_val = m_is_log_scale ? Math.log(m_max_y_value) : m_max_y_value;
        double min_y_val = m_is_log_scale ? Math.log(m_min_y_value) : m_min_y_value;

        for (Point point:
             _points) {

            Point transformed_point = new Point();

            double point_x = m_is_log_scale ? Math.log(point.getX()) : point.getX();
            double point_y = m_is_log_scale ? Math.log(point.getY()) : point.getY();

            double max_val_interval_x = max_x_val - min_x_val;
            double point_val_interval_x = point_x - min_x_val;

            int x_pos = (int)((getWidth() - m_left_padding) * (point_val_interval_x / max_val_interval_x));
            x_pos += m_left_padding;

            double max_val_interval_y = max_y_val - min_y_val;
            double point_val_interval_y = point_y - min_y_val;

            int y_pos = (int)((getHeight() - m_bottom_padding) * (1 - point_val_interval_y / max_val_interval_y));


            transformed_point.setLocation(x_pos, y_pos);

            transformed_points.add(transformed_point);
        }

        return transformed_points;
    }



    private void DrawPlaneWithGraphs(Graphics2D _ctx) {

        _ctx.setColor(Color.LIGHT_GRAY);

        _ctx.fillRect(m_left_padding, 0, getWidth() - m_left_padding, getHeight() - m_bottom_padding);
        DrawGrid(_ctx, m_scale);

        DrawGraphs(_ctx);

        DrawPaddings(_ctx);

        DrawAxisX(_ctx, m_left_padding, m_bottom_padding, getWidth() - m_left_padding);
        DrawAxisY(_ctx, m_left_padding, m_bottom_padding, getHeight() - m_bottom_padding);

        DrawValues(_ctx);
    }



    private void DrawPaddings(Graphics2D _ctx) {

        _ctx.setColor(Color.WHITE);
        _ctx.fillRect(0, 0, m_left_padding, getHeight());
        _ctx.fillRect(0, getHeight() - m_bottom_padding, getWidth(), m_bottom_padding);
    }



    private void DrawAxisX(Graphics2D _ctx, int _x, int _y, int _length) {

        _ctx.setColor(Color.BLACK);

        int line_height = 6;
        int line_end_x = _x + _length;
        int line_end_y = getHeight() - _y;
        _ctx.fillRect(_x, getHeight() - _y, _length, line_height);



        Polygon cross = new Polygon(new int[]{line_end_x - 50, line_end_x - 50, line_end_x},
                new int[]{line_end_y - 12,
                        line_end_y + 12 + line_height / 2,
                        line_end_y + line_height / 2},
                3);

        Font font = new Font("Arial", Font.ITALIC, 30);
        _ctx.setFont(font);

        if (!m_is_log_scale) {
            _ctx.drawString(m_x_title, line_end_x - 50 , line_end_y + 50);
        }
        else {
            _ctx.drawString(m_x_title, line_end_x - 120 , line_end_y + 50);
        }

        _ctx.fillPolygon(cross);
    }


    private void DrawAxisY(Graphics2D _ctx, int _x, int _y, int _length) {

        _ctx.setColor(Color.BLACK);

        int line_width = 6;
        int line_end_y = getHeight() - _y - _length;
        _ctx.fillRect(_x - line_width, line_end_y, line_width, _length + 6);



        Polygon cross = new Polygon(new int[]{_x - 3 * line_width,
                _x + line_width * 3 / 2,
                _x - line_width / 2},
                new int[]{line_end_y + 50,
                        line_end_y + 50,
                        line_end_y},
                3);

        Font font = new Font("Arial", Font.ITALIC, 30);
        _ctx.setFont(font);

        if (!m_is_log_scale) {
            _ctx.drawString(m_y_title, _x - 50, line_end_y + 50);
        }
        else {
            _ctx.drawString(m_y_title, _x - 130, line_end_y + 50);
        }

        _ctx.fillPolygon(cross);
    }


   private void DrawValues(Graphics2D _ctx) {

       double max_x_val = m_is_log_scale ? Math.log(m_max_x_value) : m_max_x_value;
       double min_x_val = m_is_log_scale ? Math.log(m_min_x_value) : m_min_x_value;
       double max_y_val = m_is_log_scale ? Math.log(m_max_y_value) : m_max_y_value;
       double min_y_val = m_is_log_scale ? Math.log(m_min_y_value) : m_min_y_value;


       double step_x_val = ((max_x_val - min_x_val) / (double) 5);
       double step_y_val = ((max_y_val - min_y_val) / (double) 5);

       if (m_graphs.isEmpty()) {
           step_x_val = 0.;
           step_y_val = 0.;
       }

       int step_x_coord = ((getWidth() - m_left_padding) / 5);
       int step_y_coord = ((getHeight() - m_bottom_padding) / 5);

       double x_val = min_x_val;
       double y_val = min_y_val;

       if (m_graphs.isEmpty()) {
           x_val = 0.;
           y_val = 0.;
       }

        int x_coord = m_left_padding;
        int y_coord = getHeight() - m_bottom_padding;

        Font font = new Font("Arial", Font.ITALIC, 15);
        _ctx.setFont(font);

        for (int i = 0; i < 5; i++)
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

        for (int i = 0; i < 5 * scale; i++)
        {
            int coll_height = (i % scale) == 0 ? 10 : 5;
            int coll_width = 2;

            _ctx.fillRect(x_coord, getHeight() - m_bottom_padding - coll_height,
                          coll_width, coll_height);

            _ctx.fillRect(m_left_padding, y_coord, coll_height, coll_width);

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



    private void MoveGraphView(int _x_offset, int _y_offset) {

        double max_x_val = m_is_log_scale ? Math.log(m_max_x_value) : m_max_x_value;
        double min_x_val = m_is_log_scale ? Math.log(m_min_x_value) : m_min_x_value;
        double max_y_val = m_is_log_scale ? Math.log(m_max_y_value) : m_max_y_value;
        double min_y_val = m_is_log_scale ? Math.log(m_min_y_value) : m_min_y_value;

        double scale = m_is_log_scale ? 10 : 1000;

        double x_interval = max_x_val - min_x_val;

        m_min_x_value += _x_offset * (x_interval / scale);
        m_max_x_value += _x_offset * (x_interval / scale);

        double y_interval = max_y_val - min_y_val;

        m_min_y_value += _y_offset * (y_interval / scale);
        m_max_y_value += _y_offset * (y_interval / scale);
    }



    private void ScaleGraphView(int _wheel_rotation) {

        double max_x_val = m_is_log_scale ? Math.log(m_max_x_value) : m_max_x_value;
        double min_x_val = m_is_log_scale ? Math.log(m_min_x_value) : m_min_x_value;
        double max_y_val = m_is_log_scale ? Math.log(m_max_y_value) : m_max_y_value;
        double min_y_val = m_is_log_scale ? Math.log(m_min_y_value) : m_min_y_value;

        if (_wheel_rotation > 0 && m_scale <= 4) {
            return;
        }
        if (_wheel_rotation < 0 && m_scale >= 100) {
            return;
        }

        double x_interval = max_x_val - min_x_val;

        m_min_x_value -= _wheel_rotation * (x_interval / 10);
        m_max_x_value += _wheel_rotation * (x_interval / 10);

        double y_interval = max_y_val - min_y_val;

        m_min_y_value -= _wheel_rotation * (y_interval / 10);
        m_max_y_value += _wheel_rotation * (y_interval / 10);

        m_scale -= 2 * _wheel_rotation;

        repaint();
    }



    public void mouseClicked(MouseEvent _event) {

    }

    public void mousePressed(MouseEvent _event) {

    }

    public void mouseReleased(MouseEvent _event) {

    }

    public void mouseEntered(MouseEvent _event) {

    }

    public void mouseExited(MouseEvent _event) {

    }

    public void mouseDragged(MouseEvent _event) {

        int x_offset = m_last_cursor_position.x - _event.getX();
        int y_offset = m_last_cursor_position.y - _event.getY();

        MoveGraphView(x_offset, -y_offset);

        m_last_cursor_position = new Point(_event.getX(), _event.getY());

        repaint();
    }

    public void mouseMoved(MouseEvent _event) {
        m_last_cursor_position = new Point(_event.getX(), _event.getY());
    }

    public void mouseWheelMoved(MouseWheelEvent _event) {
        int rotation = _event.getWheelRotation();
        ScaleGraphView(rotation);
    }
}
