package Components;

import Application.GraphDrawerApp;

import javax.swing.*;
import java.awt.*;

public class LogScaleButton extends JButton {

    private static final String m_enable_log_scale_text = "Enable log scale";
    private static final String m_disable_log_scale_text = "Disable log scale";

    private GraphDrawerApp m_context = null;





    public LogScaleButton(GraphDrawerApp _context) {
        super(m_enable_log_scale_text);
        m_context = _context;
        Initialize();
    }



    private void Initialize() {
        setVisible(true);

        setActionCommand("Clicked");
        addActionListener(_event -> {
            if (_event.getActionCommand().equals("Clicked")) {
                boolean is_log_scaling = m_context.GetCoordinatePlaneWrapper().GetActivePlane().IsLogScaling();

                if (is_log_scaling) {
                    m_context.GetCoordinatePlaneWrapper().GetActivePlane().DisableLogScale();
                    setText(m_enable_log_scale_text);
                }
                else {
                    m_context.GetCoordinatePlaneWrapper().GetActivePlane().EnableLogScale();
                    setText(m_disable_log_scale_text);
                }
            }
        });

    }
}
