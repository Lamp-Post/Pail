package me.escapeNT.pail.GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.Util.Util;

/**
 * Class representing the Server dropdown menu.
 *
 * @author escapeNT
 */
public class ServerMenu extends JMenu implements Localizable {
    private static final long serialVersionUID = 4309097499206528096L;

    public ServerMenu() {
        super(Util.translate("Server"));
        setMnemonic('S');

        JMenuItem stop = new JMenuItem(Util.translate("Stop"));
        stop.setIcon(new ImageIcon(getClass().getResource("/images/stop.png")));
        stop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Util.dispatchCommand("stop");
            }
        });

        JMenuItem save = new JMenuItem(Util.translate("Save All"));
        save.setIcon(new ImageIcon(getClass().getResource("/images/save.png")));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Util.dispatchCommand("save-all");
            }
        });

        add(save);
        add(stop);
    }

    public void translateComponent() {
    }
}
