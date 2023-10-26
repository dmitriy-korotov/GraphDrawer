package Components;

import Application.GraphDrawerApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class SideBar extends JPanel implements ComponentListener {

    private GraphDrawerApp m_context = null;
    private JTextField m_file_input_field = null;



    public SideBar(GraphDrawerApp _context) {
        m_context = _context;
        addComponentListener(this);
        SetupSideBar();
    }


    private void SetupSideBar() {

        setBackground(Color.GRAY);

        JLabel label = new JLabel("Input filename");
        label.setVisible(true);
        label.setSize(200, 30);
        m_file_input_field = new JTextField();


        m_context.getContentPane().add(this);

        add(label);
        add(m_file_input_field);

        m_file_input_field.setVisible(true);

    }



    public void componentResized(ComponentEvent _event) {
        if (m_file_input_field != null) {
            m_file_input_field.setSize(getWidth(), 30);
        }
    }

    public void componentMoved(ComponentEvent e) {

    }

    public void componentShown(ComponentEvent e) {

    }

    public void componentHidden(ComponentEvent e) {

    }
}
