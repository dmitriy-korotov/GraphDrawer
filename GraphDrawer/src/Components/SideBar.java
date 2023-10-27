package Components;

import Application.GraphDrawerApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class SideBar extends JPanel implements ComponentListener {

    private GraphDrawerApp m_context = null;
    private FileInputField m_file_input_field = null;



    public SideBar(GraphDrawerApp _context) {
        m_context = _context;
        addComponentListener(this);
        SetupSideBar();
    }


    private void SetupSideBar() {

        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        m_file_input_field = new FileInputField(m_context);
        add(m_file_input_field, BorderLayout.NORTH);

        m_context.getContentPane().add(this, BorderLayout.WEST);

    }



    public void componentResized(ComponentEvent _event) {

    }

    public void componentMoved(ComponentEvent e) {

    }

    public void componentShown(ComponentEvent e) {

    }

    public void componentHidden(ComponentEvent e) {

    }
}
