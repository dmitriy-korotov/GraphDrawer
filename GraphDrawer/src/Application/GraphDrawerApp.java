package Application;

import Components.Menu;
import Components.SideBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Properties;

public class GraphDrawerApp extends Application implements ComponentListener {

    private Menu m_menu = null;
    private SideBar m_sidebar = null;
    private JPanel m_coordinate_plane = null;



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
        m_sidebar = new SideBar(this);

        m_coordinate_plane = new JPanel();
        m_coordinate_plane.setVisible(true);
        m_coordinate_plane.setSize(getWidth() * 3 / 4, getHeight());
        m_coordinate_plane.setBackground(Color.cyan);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(m_coordinate_plane, BorderLayout.EAST);

        componentResized(null);
    }



    public void componentResized(ComponentEvent _event) {
        if (m_sidebar != null) {
            m_sidebar.setSize(getWidth() / 4, getHeight());
        }
        if (m_coordinate_plane != null) {
            m_coordinate_plane.setSize(getWidth() * 3 / 4, getHeight());
        }
    }



    public void componentHidden(ComponentEvent _event) {}



    public void componentMoved(ComponentEvent _event) {}



    public void componentShown(ComponentEvent _event) {}

}
