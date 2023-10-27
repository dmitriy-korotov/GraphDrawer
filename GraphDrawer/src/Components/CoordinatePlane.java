package Components;

import Application.GraphDrawerApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CoordinatePlane extends JComponent {

    private String m_x_title = "X";
    private String m_y_title = "Y";
    private Integer m_scale = 50;
    private Integer m_left_padding = 100;
    private Integer m_bottom_padding = 100;
    private Double m_max_x_value = Double.MIN_VALUE;
    private Double m_max_y_value = Double.MIN_VALUE;
    //private ArrayList<IGraphDrawer2D> m_drawers = new ArrayList<>();
    private GraphDrawerApp m_context = null;



    public CoordinatePlane(GraphDrawerApp _context) {
        m_context = _context;
    }



    public void setTitleX(String _x_title) { m_x_title = _x_title; }
    public void setTitleY(String _y_title) { m_y_title = _y_title; }



    /*public void addDrawer(IGraphDrawer2D _graph_drawer)
    {
        m_max_x_value = Math.max(_graph_drawer.getMaxCoordinateX(), m_max_x_value);
        m_max_y_value = Math.max(_graph_drawer.getMaxCoordinateY(), m_max_y_value);
        transfomPoints(_graph_drawer);
        m_drawers.add(_graph_drawer);
    }*/



    protected void paintComponent(Graphics _draw_context) {
        super.paintComponent(_draw_context);
        Graphics2D ctx = (Graphics2D)_draw_context;
        ctx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawPlane(ctx);

        /*for (IGraphDrawer2D drawer:
                m_drawers) {
            drawer.draw(ctx);
        }*/
    }


    private void drawPlane(Graphics2D _ctx) {

        _ctx.setBackground(Color.GRAY);

        _ctx.fillRect(m_left_padding, 0, getWidth() - m_left_padding, getHeight() - m_bottom_padding);

        drawGrid(_ctx, m_scale);

        drawAxisX(_ctx, m_left_padding, m_bottom_padding, getWidth() - m_left_padding);
        drawAxisY(_ctx, m_left_padding, m_bottom_padding, getHeight() - m_bottom_padding);

        //drawValues(_ctx, 5);
    }


    private void drawAxisX(Graphics2D _ctx, int _x, int _y, int _length) {

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


    private void drawAxisY(Graphics2D _ctx, int _x, int _y, int _length) {

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


   /* private void drawValues(Graphics2D _ctx, int _count)
    {
        Double max_x = m_max_x_value;
        Double max_y = m_max_y_value;

        Double step_x_val = (max_x / (double) _count);
        Double step_y_val = (max_y / (double) _count);

        Integer step_x_coord = (int)((getWidth() - m_padding_x) / _count);
        Integer step_y_coord = (int)((getHeight() - m_padding_x) / _count);

        Double x_val = 0.;
        Double y_val = 0.;
        Integer x_coord = m_padding_x;
        Integer y_coord = getHeight() - m_padding_y;

        Font font = new Font("Arial", 3, 15);
        _ctx.setFont(font);

        for (int i = 0; i < _count; i++)
        {
            _ctx.drawString(String.format("%.1f", x_val), x_coord, getHeight() - m_padding_y + 30);
            _ctx.drawString(String.format("%.1f", y_val), m_padding_x - String.format("%.1f", y_val).length() * 10, y_coord);

            x_val += step_x_val;
            y_val += step_y_val;

            x_coord += step_x_coord;
            y_coord -= step_y_coord;
        }

        x_coord = m_padding_x;
        y_coord = getHeight() - m_padding_y;

        int scale = 10;

        step_x_coord /= scale;
        step_y_coord /= scale;

        for (int i = 0; i < _count * scale; i++)
        {
            int coll_hegiht = (i % scale) == 0 ? 15 : 10;
            int coll_width = (i % scale) == 0 ? 3 : 3;

            _ctx.fillRect(x_coord, getHeight() - m_padding_y - coll_hegiht + 5, coll_width, coll_hegiht - 5);

            _ctx.fillRect(m_padding_x, y_coord, coll_hegiht, coll_width);

            x_coord += step_x_coord;
            y_coord -= step_y_coord;
        }
    }
*/

    private void drawGrid(Graphics2D _ctx, int _step) {

        _ctx.setColor(Color.WHITE);

        int start = m_left_padding + _step;
        for (; start < getWidth(); start += _step)
            _ctx.drawLine(start, getHeight() - m_bottom_padding, start, 0);

        start = getHeight() - m_bottom_padding - _step;
        for (; start > 0; start -= _step)
            _ctx.drawLine(m_left_padding, start, getWidth(), start);
    }


/*
    private void transfomPoints(IGraphDrawer2D _drawer)
    {
        double max_x = _drawer.getMaxCoordinateX();
        double max_y = _drawer.getMaxCoordinateY();

        for (Point point:
                _drawer.getPoints()) {

            point.x = (int)(point.x / max_x * (getWidth() - m_padding_x - 50 )) + 15;
            point.y = (int)(point.y / max_y * (getHeight() - m_padding_y - 15 )) + 15;

            point.x += m_padding_x;
            point.y = getHeight() - point.y - m_padding_y;
        }
    }*/
}
