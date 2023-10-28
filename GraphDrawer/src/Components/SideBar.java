package Components;

import Application.GraphDrawerApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class SideBar extends JPanel implements ComponentListener {

    private GraphDrawerApp m_context = null;

    private FileInputComponent m_file_input_field = null;





    public SideBar(GraphDrawerApp _context) {
        m_context = _context;
        addComponentListener(this);
        SetupSideBar();
    }


    private void SetupSideBar() {

        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        m_file_input_field = new FileInputComponent(m_context);
        add(m_file_input_field, BorderLayout.NORTH);

        JButton button = new JButton("Add new plane");
        button.setVisible(true);

        button.setActionCommand("Clicked");
        button.addActionListener(_event -> {
            if (_event.getActionCommand().equals("Clicked")) {

                CoordinatePlane new_plane = new CoordinatePlane(m_context);

                var wrapper = m_context.GetCoordinatePlaneWrapper();

                wrapper.AddCoordinatePlane(new_plane);
            }
        });


        /*String[] courses = { "core java","advance java", "java servlet"};
        JComboBox c = new JComboBox(courses);
        c.setBounds(40,40,90,20);
        add(c, BorderLayout.NORTH);*/

        add(button, BorderLayout.SOUTH);

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
