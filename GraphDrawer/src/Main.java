import Application.GraphDrawerApp;
import Utility.PointsReader;

public class Main {

    public static void main(String[] _args) {
        try {
            if (_args.length > 0) {
                GraphDrawerApp application = new GraphDrawerApp("res/config.ini", _args[1]);
            }
            else {
                GraphDrawerApp application = new GraphDrawerApp("res/config.ini");
            }
        }
        catch (Exception _ex) {
            System.out.printf("EXCEPTION: %s\n", _ex.getMessage());
        }
    }

}
