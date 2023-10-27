package Application;

import Components.CoordinatePlaneWrapper;
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
    private CoordinatePlaneWrapper m_coordinate_plane = null;



    public GraphDrawerApp(String _path_to_config) {
        super(_path_to_config);
        addComponentListener(this);
        LoadConfig();
        LinkComponents();
        OnWindowOpen();
    }



    private void LoadConfig() {
        Properties config = GetConfig();
    }



    private void LinkComponents() {

        m_menu = new Menu("Options", this);
        m_sidebar = new SideBar(this);
        m_coordinate_plane = new CoordinatePlaneWrapper(this);

    }



    private void OnWindowOpen() {
        setVisible(true);
        componentResized(null);
    }



    public void componentResized(ComponentEvent _event) {
        if (m_sidebar != null) {
            m_sidebar.setPreferredSize(new Dimension(getWidth() / 4, getHeight()));
        }
    }



    public void componentHidden(ComponentEvent _event) {}



    public void componentMoved(ComponentEvent _event) {}



    public void componentShown(ComponentEvent _event) {}

}
