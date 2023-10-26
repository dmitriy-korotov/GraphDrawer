import Utility.ConfigLoader;
import Utility.ConsoleLogger;
import Utility.ILogger;
import Utility.SeverityLevel;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class Application extends JFrame {

    private static final ILogger m_logger = new ConsoleLogger();



    public Application(Path _path_to_config) {
        LoadConfig(_path_to_config);
    }



    private void LoadConfig(Path _path_to_config) {
        ConfigLoader loader = new ConfigLoader();

        try {
            loader.load(_path_to_config);
        }
        catch (IOException _ex) {
            m_logger.Log(_ex.getMessage(), SeverityLevel.ERROR);
        }

        Properties config = loader.GetConfig();

        if (config.containsKey("width") && config.containsKey("height")) {
            int width = Integer.parseInt(config.getProperty("width"));
            int height = Integer.parseInt(config.getProperty("height"));

            setSize(width, height);
        }
        if (config.containsKey("title")) {
            setTitle(config.getProperty("title"));
        }
    }
}
