package Components;

import Application.GraphDrawerApp;
import Utility.Graph;
import Utility.PointsReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.util.Objects;

public class FileInputComponent extends JPanel implements ComponentListener {

    private GraphDrawerApp m_context = null;

    private JButton m_button = null;

    private JTextField m_file_input_field = null;





    public FileInputComponent(GraphDrawerApp _context) {
        m_context = _context;
        addComponentListener(this);
        SetupFields();
    }



    private void SetupFields() {

        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        Font font = new Font("Arial", Font.BOLD, 15);
        JLabel label = new JLabel("Input filename:");
        label.setFont(font);
        label.setVisible(true);


        m_file_input_field = new JTextField();
        m_file_input_field.setVisible(true);

        m_button = new JButton("Download");
        m_button.setActionCommand("Clicked");
        m_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent _event) {
                if (_event.getActionCommand().equals("Clicked")) {
                    String filename = m_file_input_field.getText();
                    if (!Objects.equals(filename, "")) {
                        try {
                            PointsReader reader = new PointsReader(filename);
                            CoordinatePlane active_plane = m_context.GetCoordinatePlaneWrapper().GetActivePlane();
                            active_plane.AddGraph(new Graph(reader.GetPoints()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        add(label, BorderLayout.NORTH);
        add(m_file_input_field, BorderLayout.LINE_START);
        add(m_button, BorderLayout.CENTER);

    }

    public void componentResized(ComponentEvent _event) {
        if (m_file_input_field != null) {
            m_file_input_field.setPreferredSize(new Dimension(getWidth() / 2, 20));
        }
    }

    public void componentMoved(ComponentEvent _event) {

    }

    public void componentShown(ComponentEvent _event) {

    }

    public void componentHidden(ComponentEvent _event) {

    }
}
