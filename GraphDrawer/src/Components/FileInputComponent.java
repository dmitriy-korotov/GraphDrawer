package Components;

import Application.GraphDrawerApp;
import Utility.PointsReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

public class FileInputComponent extends JButton implements ComponentListener {

    private GraphDrawerApp m_context = null;





    public FileInputComponent(String _title, GraphDrawerApp _context) {
        super(_title);
        m_context = _context;
        Initialize();
    }



    private void Initialize() {

        setActionCommand("Clicked");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent _event) {
                if (_event.getActionCommand().equals("Clicked")) {

                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graph files", "gph");
                    chooser.setFileFilter(filter);

                    int returnVal = chooser.showOpenDialog(m_context);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {

                        String directory = chooser.getCurrentDirectory().toString();
                        String filename = chooser.getSelectedFile().getName();
                        String path = directory + "\\" + filename;

                        try {
                            PointsReader reader = new PointsReader(path);

                            var current_graph_instance = m_context.GetSideBar().GetCurrentGraphInstance();

                            current_graph_instance.SetPoints(reader.GetPoints());

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
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
