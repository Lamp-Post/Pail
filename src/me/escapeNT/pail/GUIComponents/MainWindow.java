
package me.escapeNT.pail.GUIComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import me.escapeNT.pail.Pail;
import me.escapeNT.pail.config.PanelConfig;

import me.escapeNT.pail.util.Util;

/**
 * Class containing the main JFrame of the plugin.
 * @author escapeNT
 */
public class MainWindow extends JFrame {

    private JTabbedPane tabPane;
    private ServerControlPanel serverControls;

    /**
     * Called by the constructor to initialize the GUI components.
     */
    private void initComponents() { 
        tabPane = new JTabbedPane();

        // Register server control panel
        serverControls = new ServerControlPanel();
        Util.getPlugin().loadInterfaceComponent("Server Control", getServerControls());

        // Load all registered GUI components
        loadPanels();
        add(getTabPane());
    }

    /**
     * Loads all queued panels into the interface.
     */
    public void loadPanels() {
        PanelConfig.load();
        getTabPane().removeAll();
        List<String> t = new ArrayList<String>();
        List<JPanel> p = new ArrayList<JPanel>();
        getTabPane().add(Util.getInterfaceComponents().get("Server Control"), "Server Control");
        for (String title : Util.getInterfaceComponents().keySet()) {
            t.add(title);
            p.add(Util.getInterfaceComponents().get(title));
        }
        for(int i = t.size() - 1; i >= 0; i--) {
            if(!PanelConfig.getPanelsActivated().containsKey(t.get(i))) {
                PanelConfig.getPanelsActivated().put(t.get(i), true);
            }
            if(!t.get(i).equals("Server Control") && PanelConfig.getPanelsActivated().get(t.get(i))) {
                getTabPane().add(p.get(i),t.get(i));
            }
        }
        validate();
    }

    /**
     * Constructs a new main window for the GUI.
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * Returns the server control panel.
     * @return the serverControls
     */
    public ServerControlPanel getServerControls() {
        return serverControls;
    }

    /**
     * @return the tabPane
     */
    public JTabbedPane getTabPane() {
        return tabPane;
    }
}