package Components;

import Application.GraphDrawerApp;
import Utility.Graph;

import javax.swing.*;

public class NewGraphButton extends JButton {

    private GraphDrawerApp m_context = null;





    public NewGraphButton(String _title, GraphDrawerApp _context) {
        super(_title);
        m_context = _context;
        Initialize();
    }



    public void Initialize() {

        setVisible(true);

        setActionCommand("Clicked");
        addActionListener(_event -> {
            if (_event.getActionCommand().equals("Clicked")) {

                var current_graph_instance = m_context.GetSideBar().GetCurrentGraphInstance();

                if (current_graph_instance != null && !current_graph_instance.GetPoints().isEmpty()) {

                    var active_plane = m_context.GetCoordinatePlaneWrapper().GetActivePlane();

                    active_plane.AddGraph(current_graph_instance);

                    m_context.GetSideBar().ResetCurrentGraphInstance();
                }
            }
        });

    }
}
