package me.escapeNT.pail.GUIComponents;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.Util.Util;
import javax.swing.JTextField;

/**
 * Panel containing options for a Server Task.
 * 
 * @author escapeNT
 */
public class ConsoleCommandTaskOptions extends javax.swing.JPanel implements Localizable {

    private static final long serialVersionUID = 8377251626017201670L;

    /** Creates new form ServerTaskOptions */
    public ConsoleCommandTaskOptions() {
        initComponents();
        translateComponent();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();

        jLabel1.setText("Command");
        
        command = new JTextField();
        command.setColumns(10);

        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(command, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(command, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(269, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents
    private JLabel jLabel1;
    private JTextField command;

    // End of variables declaration//GEN-END:variables

    public final void translateComponent() {
        Util.translateTextComponent(jLabel1);
    }

    /**
     * Command textbox.
     * 
     * @return the command
     */
    public JTextField getCommand() {
        return command;
    }

    public void setDisabled() {
        command.setEnabled(false);
        jLabel1.setEnabled(false);
    }
    
    public void setEnabled() {
        command.setEnabled(true);
        jLabel1.setEnabled(true);
    }
}
