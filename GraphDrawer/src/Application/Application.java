package Application;

import Utility.ConfigLoader;
import Utility.ConsoleLogger;
import Utility.ILogger;
import Utility.SeverityLevel;

import javax.swing.*;
import java.io.IOException;
import java.util.Properties;

public class Application extends JFrame {

    private static final ILogger m_logger = new ConsoleLogger();

    private Properties m_config = null;





    protected Application() { SetDefaultSettings(); }



    public Application(String _path_to_config) {
        this();
        LoadConfig(_path_to_config);
    }



    protected Properties GetConfig() { return m_config; }



    private void LoadConfig(String _path_to_config) {
        ConfigLoader loader = new ConfigLoader();

        try {
            loader.load(_path_to_config);
        }
        catch (IOException _ex) {
            m_logger.Log(_ex.getMessage(), SeverityLevel.ERROR);
        }

        m_config = loader.GetConfig();

        if (m_config.containsKey("width") && m_config.containsKey("height")) {
            int width = Integer.parseInt(m_config.getProperty("width"));
            int height = Integer.parseInt(m_config.getProperty("height"));

            setSize(width, height);
        }
        if (m_config.containsKey("title")) {
            setTitle(m_config.getProperty("title"));
        }
    }



    private void SetDefaultSettings() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }



    public void Close() {
        setVisible(false);
        dispose();
    }
}
