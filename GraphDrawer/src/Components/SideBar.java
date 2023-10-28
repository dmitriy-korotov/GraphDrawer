package Components;

import Application.GraphDrawerApp;
import Utility.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class SideBar extends JPanel implements ComponentListener {

    private GraphDrawerApp m_context = null;

    private Graph m_current_graph_instance = new Graph();

    private FileInputComponent m_file_input_button = null;
    private NewPlaneButton m_new_plane_button = null;
    private NewGraphButton m_new_graph_button = null;
    private ColorSelectButton m_color_select_button = null;





    public SideBar(GraphDrawerApp _context) {
        m_context = _context;
        addComponentListener(this);
        Initialize();
    }



    public Graph GetCurrentGraphInstance() {
        return m_current_graph_instance;
    }



    public void ResetCurrentGraphInstance() {
        m_current_graph_instance = new Graph();
    }



    private void Initialize() {

        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Box box = Box.createVerticalBox();

        m_file_input_button = new FileInputComponent("Select graph file", m_context);
        box.add(m_file_input_button);

        m_color_select_button = new ColorSelectButton("Select graph color", Color.BLUE, m_context);
        box.add(m_color_select_button);

        m_new_graph_button = new NewGraphButton("Add graph on this plane", m_context);
        box.add(m_new_graph_button);

        add(box, BorderLayout.CENTER);

        m_new_plane_button = new NewPlaneButton("Add new plane", m_context);
        add(m_new_plane_button, BorderLayout.SOUTH);

        m_context.getContentPane().add(this, BorderLayout.WEST);
    }



    public void componentResized(ComponentEvent _event) {

    }

    public void componentMoved(ComponentEvent _event) {

    }

    public void componentShown(ComponentEvent _event) {

    }

    public void componentHidden(ComponentEvent _event) {

    }
}
