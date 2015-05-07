package me.escapeNT.pail.GUIComponents;

import javax.swing.GroupLayout;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.Util.Util;

/**
 * Pail about window.
 * 
 * @author escapeNT
 */
public class AboutView extends javax.swing.JDialog {

    private static final long serialVersionUID = -5615741640015951383L;
    /** Creates new form AboutView */
    public AboutView() {
        initComponents();
        setModal(true);
        pack();
        setSize(225, 230);
        setLocationRelativeTo(Util.getPlugin().getMainWindow());
        setResizable(false);
        version.setText("Version " + Pail.PLUGIN_VERSION + " by escape");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        version = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        title.setFont(new java.awt.Font("Lucida Grande", 1, 26));
        title.setText("Pail");

        version.setText("Version ");

        icon.setIcon(new javax.swing.ImageIcon(getClass()
                .getResource("/images/pail.png"))); // NOI18N

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout
                        .createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 150,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout
                        .createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(version, GroupLayout.PREFERRED_SIZE, 170,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout
                        .createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(icon, GroupLayout.PREFERRED_SIZE, 150,
                                GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(title)
                        .addGap(8, 8, 8)
                        .addComponent(version)
                        .addGap(4, 4, 4)
                        .addComponent(icon, GroupLayout.PREFERRED_SIZE, 130,
                                GroupLayout.PREFERRED_SIZE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon;
    private javax.swing.JLabel title;
    private javax.swing.JLabel version;
    // End of variables declaration//GEN-END:variables
}
