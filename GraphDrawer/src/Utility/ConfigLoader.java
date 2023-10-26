package Utility;

import java.io.*;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigLoader {

    private Properties m_props = null;



    public ConfigLoader() { }



    public ConfigLoader(Path _path_to_config) throws IOException {
        load(_path_to_config);
    }



    public Properties GetConfig() { return m_props; }



    public void load(Path _path_to_config) throws IOException {

        File config_file = new File(_path_to_config.toString());

        try (FileInputStream inputStream = new FileInputStream(config_file)) {

            m_props = new Properties();
            m_props.load(inputStream);

        }
    }


    public boolean IsLoaded() { return m_props != null; }

}
