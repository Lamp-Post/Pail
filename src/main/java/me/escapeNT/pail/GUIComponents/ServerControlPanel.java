package me.escapeNT.pail.GUIComponents;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;

import org.spongepowered.api.data.manipulators.entities.HealthData;
import org.spongepowered.api.entity.player.Player;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.Util.Util;

/**
 * Panel containing the basic server controls.
 *
 * @author escapeNT
 */
public final class ServerControlPanel extends javax.swing.JPanel implements Localizable {
    private static final long serialVersionUID = 7137691231828283935L;
    private HashMap<Object, ImageIcon> onlinePlayers = new HashMap<Object, ImageIcon>();
    private IconListRenderer listModel = new IconListRenderer(onlinePlayers, true);
    private JPopupMenu playerMenu = null;
    private JMenuItem op;
    private JMenuItem deop;

    /**
     * Creates new form ServerControlPanel
     */
    public ServerControlPanel() {
        initComponents();
        Util.setServerControls(this);
        playerList.setModel(new DefaultListModel<String>());
        playerList.setCellRenderer(listModel);
        jScrollPane1.setBorder(new TitledBorder(Util.translate("Players")));

        // Load images
        ImageIcon plus = new ImageIcon(getClass().getResource("/images/plus.png"));
        ImageIcon arrow = new ImageIcon(getClass().getResource("/images/right.png"));
        ImageIcon kill = new ImageIcon(getClass().getResource("/images/kill.png"));
        ImageIcon up = new ImageIcon(getClass().getResource("/images/up.png"));
        ImageIcon down = new ImageIcon(getClass().getResource("/images/down.png"));
        ImageIcon msg = new ImageIcon(getClass().getResource("/images/msg.png"));
        ImageIcon kickico = new ImageIcon(getClass().getResource("/images/kick.png"));
        ImageIcon banico = new ImageIcon(getClass().getResource("/images/ban.png"));

        // Construct player popup menu
        playerMenu = new JPopupMenu();

        JMenuItem giveItem = new JMenuItem(Util.translate("Give Item"), plus);
        giveItem.addActionListener(new GiveItemListener());
        playerMenu.add(giveItem);

        JMenuItem tele = new JMenuItem(Util.translate("Teleport"), arrow);
        tele.addActionListener(new TeleportListener());
        playerMenu.add(tele);

        JMenuItem killPlayer = new JMenuItem(Util.translate("Kill"), kill);
        killPlayer.addActionListener(new KillPlayerListener());
        playerMenu.add(killPlayer);

        op = new JMenuItem(Util.translate("Promote to OP"), up);
        op.addActionListener(new OpPlayerListener());
        playerMenu.add(op);

        deop = new JMenuItem(Util.translate("Demote from OP"), down);
        deop.addActionListener(new DeOpPlayerListener());
        playerMenu.add(deop);

        JMenuItem message = new JMenuItem(Util.translate("Message"), msg);
        message.addActionListener(new MessagePlayerListener());
        playerMenu.add(message);

        JMenuItem kick = new JMenuItem(Util.translate("Kick"), kickico);
        kick.addActionListener(new KickPlayerListener());
        playerMenu.add(kick);

        JMenuItem ban = new JMenuItem(Util.translate("Ban"), banico);
        ban.addActionListener(new BanPlayerListener());
        playerMenu.add(ban);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        serverConsolePanel = new me.escapeNT.pail.GUIComponents.ServerConsolePanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playerList = new javax.swing.JList<String>();

        jScrollPane2.setViewportView(jEditorPane1);

        serverConsolePanel.setPreferredSize(new java.awt.Dimension(640, 450));

        playerList.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        playerList.setFocusable(false);
        playerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerListMousePressed(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playerListMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(playerList);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(serverConsolePanel, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 170,
                                GroupLayout.PREFERRED_SIZE).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout
                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(serverConsolePanel, GroupLayout.Alignment.TRAILING,
                                        GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                .addComponent( jScrollPane1, GroupLayout.Alignment.TRAILING,
                                        GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                        .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    private void playerListMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_playerListMousePressed
        showPlayerMenu(evt);
    }// GEN-LAST:event_playerListMousePressed

    private void playerListMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_playerListMouseReleased
        showPlayerMenu(evt);
    }// GEN-LAST:event_playerListMouseReleased

    private void showPlayerMenu(MouseEvent e) {
        if (e.isPopupTrigger()) {
            for (int i = 0; i < ((DefaultListModel<String>) playerList.getModel()).getSize(); i++) {
                Rectangle r = playerList.getCellBounds(i, i);
                if (r.contains(e.getPoint())) {
                    playerList.setSelectedIndex(i);

                    // Player p = Pail.getServer().getPlayer((String) playerList.getSelectedValue()).get();
                    // if (p.isOp()) {
                    op.setEnabled(false);
                    // deop.setEnabled(true); //TODO Not Implemented yet by Sponge
                    // } else {
                    // op.setEnabled(true);
                    deop.setEnabled(false);
                    // }

                    playerMenu.show(playerList, e.getX(), e.getY());
                    break;
                }
            }
        }
    }

    public void translateComponent() {
    }

    private class GiveItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new GiveItemView((String) playerList.getSelectedValue()).setVisible(true);
        }
    }

    private class TeleportListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new TeleportPlayerView(playerList.getSelectedValue().toString()).setVisible(true);
        }
    }

    private class KillPlayerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Util.dispatch(new Runnable() {
                public void run() {
                    Player p = Pail.getServer().getPlayer(playerList.getSelectedValue().toString()).get();
                    p.getOrCreate(HealthData.class).get().setHealth(0);
                    // TODO not implemented by Sponge p.setLastDamageCause(new EntityDamageEvent(p,
                    // EntityDamageEvent.DamageCause.SUICIDE, 1000));
                }
            });
        }
    }

    private class OpPlayerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Util.dispatchCommand("op " + playerList.getSelectedValue().toString());
        }
    }

    private class DeOpPlayerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Util.dispatchCommand("deop " + playerList.getSelectedValue().toString());
        }
    }

    private class MessagePlayerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String msg = JOptionPane.showInputDialog(Util.getPlugin().getMainWindow(),
                    Util.translate("Enter your message:"), Util.translate("Message Player"),
                    JOptionPane.QUESTION_MESSAGE);
            if (msg == null) {
                return;
            }
            Util.dispatchCommand(String.format("tell %s %s", playerList.getSelectedValue(), msg));
        }
    }

    private class KickPlayerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String reason = JOptionPane.showInputDialog(Util.getPlugin().getMainWindow(),
                    Util.translate("Enter kick reason:"), Util.translate("Kick Player"), JOptionPane.QUESTION_MESSAGE);
            if (reason == null) {
                return;
            }
            reason = reason.replaceAll(" ", "_");
            Util.dispatchCommand("kick " + playerList.getSelectedValue().toString() + " " + reason);
        }
    }

    private class BanPlayerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Util.dispatchCommand("ban " + playerList.getSelectedValue().toString());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> playerList;
    private me.escapeNT.pail.GUIComponents.ServerConsolePanel serverConsolePanel;

    // End of variables declaration//GEN-END:variables

    /**
     * Returns the panel containing the console controls.
     *
     * @return the serverConsolePanel
     */
    public me.escapeNT.pail.GUIComponents.ServerConsolePanel getServerConsolePanel() {
        return serverConsolePanel;
    }

    /**
     * Returns the list model for the player selection list.
     *
     * @return the listModel
     */
    public DefaultListModel<String> getListModel() {
        return (DefaultListModel<String>) playerList.getModel();
    }

    /**
     * Adds a player and their face to the list.
     *
     * @param name
     *            The player name.
     */
    public void addPlayer(String name) {
        BufferedImage skin;
        BufferedImage face;
        try {
            skin = ImageIO.read(new URL("http://www.minecraft.net/skin/" + name + ".png"));
            face = skin.getSubimage(8, 8, 8, 8);
            face = resize(face, 23, 23, true);
        } catch (Exception ex) {
            onlinePlayers.put(name, new ImageIcon());
            playerList.setCellRenderer(new IconListRenderer(onlinePlayers, true));
            getListModel().addElement(name);
            return;
        }
        onlinePlayers.put(name, new ImageIcon(face));
        playerList.setCellRenderer(new IconListRenderer(onlinePlayers, true));
        getListModel().addElement(name);
    }

    private BufferedImage resize(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}
