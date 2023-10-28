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
        JMenuItem full_screen_mode = new JMenuItem("Fullscreen mode");
        JMenuItem window_mode = new JMenuItem("Window mode");



        close_item.setActionCommand("Close");
        close_item.addActionListener(_event -> {
            if (_event.getActionCommand().equals("Close")) {
                m_context.Close();
            }
        });

        hide_item.setActionCommand("Close");
        hide_item.addActionListener(_event -> {
            if (_event.getActionCommand().equals("Close")) {
                m_context.HideWindow();
            }
        });

        full_screen_mode.setActionCommand("Close");
        full_screen_mode.addActionListener(_event -> {
            if (_event.getActionCommand().equals("Close")) {
                m_context.SetFullScreenMode();
            }
        });

        window_mode.setActionCommand("Close");
        window_mode.addActionListener(_event -> {
            if (_event.getActionCommand().equals("Close")) {
                m_context.SetWindowMode();
            }
        });



        add(close_item);
        add(hide_item);
        add(full_screen_mode);
        add(window_mode);

        menu_bar.add(this);

        m_context.setJMenuBar(menu_bar);

    }
}
