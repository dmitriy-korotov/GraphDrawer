package Components;

import Application.GraphDrawerApp;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenu {

    private GraphDrawerApp m_context = null;





    public Menu(String _title, GraphDrawerApp _context) {
        super(_title);
        m_context = _context;
        SetupMenu();
    }



    private void SetupMenu() {

        JMenuBar menu_bar = new JMenuBar();

        JMenuItem close_item = new JMenuItem("Close Graph Drawer");
        JMenuItem hide_item = new JMenuItem("Hide Graph Drawer");

        close_item.setActionCommand("Close");
        close_item.addActionListener(_event -> {
            if (_event.getActionCommand().equals("Close")) {
                m_context.Close();
            }
        });

        add(close_item);
        add(hide_item);

        menu_bar.add(this);

        m_context.setJMenuBar(menu_bar);

    }
}
