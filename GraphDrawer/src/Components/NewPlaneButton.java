package Components;

import Application.GraphDrawerApp;

import javax.swing.*;

public class NewPlaneButton extends JButton {

    GraphDrawerApp m_context = null;





    public NewPlaneButton(String _title, GraphDrawerApp _context) {
        super(_title);
        m_context = _context;
        Initialize();
    }



    private void Initialize() {

        setVisible(true);

        setActionCommand("Clicked");
        addActionListener(_event -> {
            if (_event.getActionCommand().equals("Clicked")) {

                CoordinatePlane new_plane = new CoordinatePlane();

                var wrapper = m_context.GetCoordinatePlaneWrapper();

                wrapper.AddCoordinatePlane(new_plane);
            }
        });
    }
}
