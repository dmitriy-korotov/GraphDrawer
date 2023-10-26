package Application;

import Components.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Properties;

public class GraphDrawerApp extends Application implements ComponentListener {

    private Menu m_menu = null;
    private JPanel m_panel = null;



    public GraphDrawerApp(String _path_to_config) {
        super(_path_to_config);
        addComponentListener(this);
        LoadConfig();
        LinkComponents();
    }



    private void LoadConfig() {
        Properties config = GetConfig();
    }



    private void LinkComponents() {

        m_menu = new Menu("Options", this);



        m_panel = new JPanel();
        m_panel.setSize(getContentPane().getWidth() / 2, getContentPane().getHeight());
        m_panel.setVisible(true);

        m_panel.setBackground(Color.GRAY);

        add(m_panel);

    }



    public void componentResized(ComponentEvent _event) {
        if (m_panel != null) {
            m_panel.setSize(getWidth() / 4, getHeight());
        }
    }



    public void componentHidden(ComponentEvent e) {}



    public void componentMoved(ComponentEvent e) {}



    public void componentShown(ComponentEvent e) {}
}
