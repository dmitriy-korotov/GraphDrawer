import Application.GraphDrawerApp;
import Utility.PointsReader;

import javax.swing.*;

public class Main {

    public static void main(String[] _args) {
        try {
            if (_args.length > 0) {
                GraphDrawerApp application = new GraphDrawerApp("res/config.ini", _args[1]);
                ImageIcon icon = new ImageIcon("res/graph-report.png");
                application.setIconImage(icon.getImage());
            }
            else {
                GraphDrawerApp application = new GraphDrawerApp("res/config.ini");
                ImageIcon icon = new ImageIcon("res/graph-report.png");
                application.setIconImage(icon.getImage());
            }
        }
        catch (Exception _ex) {
            System.out.printf("EXCEPTION: %s\n", _ex.getMessage());
        }
    }

}
