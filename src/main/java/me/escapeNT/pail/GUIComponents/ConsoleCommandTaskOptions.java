package me.escapeNT.pail.GUIComponents;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.Util.Util;
import me.escapeNT.pail.scheduler.ServerTask;
import me.escapeNT.pail.scheduler.ServerTask.Type;

/**
 * Panel containing options for a Server Task.
 * 
 * @author escapeNT
 */
public class ConsoleCommandTaskOptions extends javax.swing.JPanel implements Localizable {
    private static final long serialVersionUID = 2081205318146112392L;

    /** Creates new form ServerTaskOptions */
    public ConsoleCommandTaskOptions() {
        initComponents();
        translateComponent();
        for (ServerTask.Type t : ServerTask.Type.values()) {
            ((DefaultComboBoxModel<Type>) action.getModel()).addElement(t);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();
        action = new JComboBox<Type>();

        jLabel1.setText("Action");

        action.setModel(new DefaultComboBoxModel<Type>());

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
                        .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(action, 0, 393, Short.MAX_VALUE)
                        .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(action, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)).addContainerGap(212, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox<Type> action;
    private JLabel jLabel1;

    // End of variables declaration//GEN-END:variables

    public final void translateComponent() {
        Util.translateTextComponent(jLabel1);
    }

    /**
     * Type selector.
     * 
     * @return the action
     */
    public JComboBox<Type> getAction() {
        return action;
    }
}
