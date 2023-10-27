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

    private CoordinatePlane m_active_plane = null;





    public CoordinatePlaneWrapper(GraphDrawerApp _context) {
        m_context = _context;
        Setup();
    }



    public void SetupActivePlane(CoordinatePlane _plane) {

        if (m_active_plane != null) {
            remove(m_active_plane);
        }

        m_active_plane = _plane;
        add(m_active_plane, BorderLayout.CENTER);
    }



    public CoordinatePlane GetActivePlane() { return m_active_plane; }



    public void AddCoordinatePlane(CoordinatePlane _plane) {

        m_coordinate_planes.add(_plane);
        JMenuItem graph = new JMenuItem("Graph");
        graph.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        m_top_bar.add(graph);
        m_top_bar.invalidate();
    }



    private void Setup() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setVisible(true);

        CoordinatePlane coordinate_plane = new CoordinatePlane(m_context);
        m_coordinate_planes.add(coordinate_plane);
        SetupActivePlane(coordinate_plane);

        SetupPlaneSelector();

        m_context.getContentPane().add(this, BorderLayout.CENTER);

    }



    private void SetupPlaneSelector() {

        m_top_bar = new JMenuBar();

        for (int i = 0; i < m_coordinate_planes.size(); i++) {
            JMenuItem graph = new JMenuItem("Graph " + (i + 1));
            graph.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            if (m_coordinate_planes.get(i) == m_active_plane) {
                graph.setBackground(Color.GREEN);
            }

            m_top_bar.add(graph);
        }

        m_top_bar.setVisible(true);

        m_top_bar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(m_top_bar, BorderLayout.NORTH);
    }
}
