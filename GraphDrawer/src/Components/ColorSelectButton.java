package Components;

import Application.GraphDrawerApp;

import javax.swing.*;
import java.awt.*;

public class ColorSelectButton extends JButton {

    private GraphDrawerApp m_context = null;

    private Color m_selected_color = null;





    public ColorSelectButton(String _title, Color _color, GraphDrawerApp _context) {
        super(_title);
        m_context = _context;
        m_selected_color = _color;
        Initialize();
    }



    public void Initialize() {

        setVisible(true);

        setActionCommand("Clicked");
        addActionListener(_event -> {
            if (_event.getActionCommand().equals("Clicked")) {

                m_selected_color = JColorChooser.showDialog(m_context, "Select graph color", Color.BLUE);

                m_context.GetSideBar().GetCurrentGraphInstance().SetColor(m_selected_color);

            }
        });

    }
}
