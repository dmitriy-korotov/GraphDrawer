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

        int index_active_plane = m_coordinate_planes.indexOf(_plane);
        var active_top_bar_item = m_top_bar_items.get(index_active_plane);
        active_top_bar_item.setBackground(Color.GREEN);

        m_active_plane = _plane;
        add(m_active_plane, BorderLayout.CENTER);
    }



    public CoordinatePlane GetActivePlane() { return m_active_plane; }



    public void AddCoordinatePlane(CoordinatePlane _plane) {

        m_coordinate_planes.add(_plane);
        JMenuItem graph = new JMenuItem("Graph " + (m_coordinate_planes.size()));
        graph.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        m_top_bar_items.add(graph);

        graph.setActionCommand("Clicked");
        graph.addActionListener(_event -> {
            if (_event.getActionCommand().equals("Clicked")) {

                var active_plane = GetActivePlane();
                int index_prev_active_plane = m_coordinate_planes.indexOf(active_plane);
                var active_top_bar_item = m_top_bar_items.get(index_prev_active_plane);
                active_top_bar_item.setBackground(Color.WHITE);

                int index_new_active_plane = m_top_bar_items.indexOf(graph);
                graph.setBackground(Color.GREEN);
                SetupActivePlane(m_coordinate_planes.get(index_new_active_plane));

                revalidate();

            }
        });

        m_top_bar.add(graph);
        m_top_bar.revalidate();
    }



    private void Setup() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setVisible(true);

        SetupPlaneSelector();

        CoordinatePlane coordinate_plane = new CoordinatePlane(m_context);
        AddCoordinatePlane(coordinate_plane);
        SetupActivePlane(coordinate_plane);


        m_context.getContentPane().add(this, BorderLayout.CENTER);

    }



    private void SetupPlaneSelector() {

        m_top_bar = new JMenuBar();
        m_top_bar.setVisible(true);
        m_top_bar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(m_top_bar, BorderLayout.NORTH);
    }
}
