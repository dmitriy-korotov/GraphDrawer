package Components;

import Application.GraphDrawerApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CoordinatePlaneWrapper extends JLayeredPane {

    private GraphDrawerApp m_context = null;
    private JMenuBar m_top_bar = null;
    private final ArrayList<JMenuItem> m_top_bar_items = new ArrayList<>();
    private final ArrayList<CoordinatePlane> m_coordinate_planes = new ArrayList<>();



    public CoordinatePlaneWrapper(GraphDrawerApp _context) {
        m_context = _context;
        Setup();
    }



    private void Setup() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setVisible(true);

        CoordinatePlane coordinate_plane = new CoordinatePlane(m_context);
        coordinate_plane.setBackground(Color.BLACK);

        m_top_bar = new JMenuBar();
        JMenuItem graph1 = new JMenuItem("Graph 1");
        graph1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JMenuItem graph2 = new JMenuItem("Graph 2");
        graph2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JMenuItem graph3 = new JMenuItem("Graph 3");
        graph3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        m_top_bar.add(graph1);
        m_top_bar.add(graph2);
        m_top_bar.add(graph3);
        m_top_bar.setVisible(true);
        m_top_bar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(coordinate_plane, BorderLayout.CENTER, 1);
        add(m_top_bar, BorderLayout.NORTH, 2);

        m_context.getContentPane().add(this, BorderLayout.CENTER);

        m_coordinate_planes.add(coordinate_plane);

    }
}
