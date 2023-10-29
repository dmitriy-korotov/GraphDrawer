package Application;

import Components.CoordinatePlaneWrapper;
import Components.Menu;
import Components.SideBar;
import Utility.Graph;
import Utility.PointsReader;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
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

        Initialize();
    }



    public GraphDrawerApp(String _path_to_config, String _path_to_graph) throws IOException {

        this(_path_to_config);

        PointsReader reader = new PointsReader(_path_to_graph);

        m_coordinate_plane.GetActivePlane().AddGraph(new Graph(reader.GetPoints()));
    }



    private void LoadConfig() {
        Properties config = GetConfig();
    }



    private void LinkComponents() {

        m_menu = new Menu("Options", this);
        m_sidebar = new SideBar(this);
        m_coordinate_plane = new CoordinatePlaneWrapper(this);
    }



    private void Initialize() {
        setVisible(true);
        componentResized(null);
    }



    public CoordinatePlaneWrapper GetCoordinatePlaneWrapper() {
        return m_coordinate_plane;
    }

    public Menu GetMenu() {
        return m_menu;
    }

    public SideBar GetSideBar() {
        return m_sidebar;
    }



    public void componentResized(ComponentEvent _event) {
        if (m_sidebar != null) {
            m_sidebar.setPreferredSize(new Dimension(getWidth() / 6, getHeight()));
            m_sidebar.revalidate();
        }
    }

    public void componentHidden(ComponentEvent _event) {

    }

    public void componentMoved(ComponentEvent _event) {

    }

    public void componentShown(ComponentEvent _event) {

    }

}
