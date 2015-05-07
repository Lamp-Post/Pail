package me.escapeNT.pail.GUIComponents;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.Util.Util;
import me.escapeNT.pail.scheduler.BackupTask;
import me.escapeNT.pail.scheduler.ConsoleCommandTask;
import me.escapeNT.pail.scheduler.ScheduledTask;
import me.escapeNT.pail.scheduler.Scheduler;
import me.escapeNT.pail.scheduler.ServerTask;
import me.escapeNT.pail.scheduler.ServerTask.Type;

/**
 * Panel for editing scheduled tasks.
 * 
 * @author escapeNT
 */
public class SchedulerPanel extends javax.swing.JPanel implements Localizable {

    private static final long serialVersionUID = 8098219941658664956L;
    private ServerTaskOptions stOptions = new ServerTaskOptions();
    private BackupTaskOptions btOptions = new BackupTaskOptions();
    private ConsoleCommandTaskOptions cctOptions = new ConsoleCommandTaskOptions();

    /** Creates new form SchedulerPanel */
    public SchedulerPanel() {
        initComponents();
        translateComponent();
        taskList.getSelectionModel().addListSelectionListener(new TaskSelectionListener());
        taskType.setEnabled(false);
        timeAmount.setEnabled(false);
        intervalType.setEnabled(false);
        repeating.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        taskList = new JTable();
        addTask = new JButton();
        removeTask = new JButton();
        jLabel1 = new JLabel();
        taskType = new JComboBox();
        optionPanel = new JPanel();
        save = new JButton();
        jLabel2 = new JLabel();
        timeAmount = new JSpinner();
        intervalType = new JComboBox();
        repeating = new JCheckBox();

        taskList.setModel(new DefaultTableModel(new Object[][] {

        }, new String[] { "Task Name", "Enabled" }) {
            private static final long serialVersionUID = 1656034351530643883L;
            Class[] types = new Class[] { String.class, Boolean.class };
            boolean[] canEdit = new boolean[] { false, true };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(taskList);

        addTask.setFont(new Font("Lucida Grande", 0, 14));
        addTask.setText("+");
        addTask.setFocusable(false);
        addTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addTaskActionPerformed(evt);
            }
        });

        removeTask.setFont(new Font("Lucida Grande", 0, 14));
        removeTask.setText("-");
        removeTask.setFocusable(false);
        removeTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeTaskActionPerformed(evt);
            }
        });

        jLabel1.setText("Type");

        taskType.setModel(new DefaultComboBoxModel(new String[] { "Server action", "World backup", "Custom command" }));
        taskType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                taskTypeItemStateChanged(evt);
            }
        });

        optionPanel.setBorder(BorderFactory.createTitledBorder("Task Options"));

        GroupLayout optionPanelLayout = new GroupLayout(optionPanel);
        optionPanel.setLayout(optionPanelLayout);
        optionPanelLayout.setHorizontalGroup(optionPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 494,
                Short.MAX_VALUE));
        optionPanelLayout.setVerticalGroup(optionPanelLayout.createParallelGroup(Alignment.LEADING).addGap(0, 251,
                Short.MAX_VALUE));

        save.setText("Save Task");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel2.setText("Execute every");

        timeAmount.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        timeAmount.setFocusable(false);

        intervalType.setModel(new DefaultComboBoxModel(new String[] { "Seconds", "Minutes", "Hours", "Days" }));
        intervalType.setSelectedIndex(1);

        repeating.setSelected(true);
        repeating.setText("Repeating");
        repeating.setFocusable(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(Alignment.LEADING)
                .addGroup(
                        Alignment.TRAILING,
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        layout.createParallelGroup(Alignment.TRAILING)
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addComponent(removeTask, GroupLayout.PREFERRED_SIZE,
                                                                        41, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(addTask, GroupLayout.PREFERRED_SIZE, 41,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300,
                                                        GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(
                                        layout.createParallelGroup(Alignment.TRAILING)
                                                .addComponent(optionPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(
                                                        Alignment.LEADING,
                                                        layout.createSequentialGroup()
                                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 45,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(taskType, GroupLayout.PREFERRED_SIZE,
                                                                        240, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(
                                                        Alignment.LEADING,
                                                        layout.createSequentialGroup()
                                                                .addGroup(
                                                                        layout.createParallelGroup(Alignment.TRAILING,
                                                                                false)
                                                                                .addComponent(repeating,
                                                                                        Alignment.LEADING,
                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE)
                                                                                .addGroup(
                                                                                        Alignment.LEADING,
                                                                                        layout.createSequentialGroup()
                                                                                                .addComponent(
                                                                                                        jLabel2,
                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                        94,
                                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                        ComponentPlacement.RELATED)
                                                                                                .addComponent(
                                                                                                        timeAmount,
                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                        105,
                                                                                                        GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(intervalType, GroupLayout.PREFERRED_SIZE,
                                                                        182, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(save)).addContainerGap()));
        layout.setVerticalGroup(layout
                .createParallelGroup(Alignment.LEADING)
                .addGroup(
                        Alignment.TRAILING,
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        layout.createParallelGroup(Alignment.LEADING)
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addGroup(
                                                                        layout.createParallelGroup(Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(taskType,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                        GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addGroup(
                                                                        layout.createParallelGroup(Alignment.TRAILING)
                                                                                .addGroup(
                                                                                        layout.createParallelGroup(
                                                                                                Alignment.TRAILING)
                                                                                                .addComponent(
                                                                                                        timeAmount,
                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                                        GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(
                                                                                                        jLabel2,
                                                                                                        Alignment.LEADING,
                                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                                        30,
                                                                                                        GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(intervalType,
                                                                                        GroupLayout.PREFERRED_SIZE,
                                                                                        GroupLayout.DEFAULT_SIZE,
                                                                                        GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(repeating)
                                                                .addGap(16, 16, 16)
                                                                .addComponent(optionPanel, GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 391,
                                                        Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(
                                        layout.createParallelGroup(Alignment.LEADING)
                                                .addComponent(addTask, GroupLayout.PREFERRED_SIZE, 37,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(removeTask, GroupLayout.PREFERRED_SIZE, 37,
                                                        GroupLayout.PREFERRED_SIZE).addComponent(save))
                                .addGap(17, 17, 17)));
    }// </editor-fold>//GEN-END:initComponents

    private void addTaskActionPerformed(ActionEvent evt) {// GEN-FIRST:event_addTaskActionPerformed
       // ServerTask task = new ServerTask(Type.SAVE_ALL, true, 60000, "Task 1");
        
        ((DefaultTableModel)taskList.getModel()).addRow(new Object[] {
                "Task " + (taskList.getModel().getRowCount() + 1), Boolean.TRUE});
        taskList.setRowSelectionInterval(taskList.getModel().getRowCount() - 1, taskList.getModel().getRowCount() - 1);
    }// GEN-LAST:event_addTaskActionPerformed

    private void removeTaskActionPerformed(ActionEvent evt) {// GEN-FIRST:event_removeTaskActionPerformed
        if (taskList.getSelectedRow() != -1) {
            ((DefaultTableModel) taskList.getModel()).removeRow(taskList.getSelectedRow());
            taskType.setEnabled(false);
            timeAmount.setEnabled(false);
            intervalType.setEnabled(false);
            repeating.setEnabled(false);
        }
    }// GEN-LAST:event_removeTaskActionPerformed

    private void taskTypeItemStateChanged(ItemEvent evt) {// GEN-FIRST:event_taskTypeItemStateChanged
        optionPanel.removeAll();
        if (taskType.getSelectedItem().toString().equals("Server action")) {
            optionPanel.add(BorderLayout.CENTER, stOptions);
            stOptions.setVisible(true);
            invalidate();
        } else if (taskType.getSelectedItem().toString().equals("World backup")) {
            optionPanel.add(BorderLayout.CENTER, btOptions);
            stOptions.setVisible(true);
            invalidate();
        } else if (taskType.getSelectedItem().toString().equals("Custom command")) {
            optionPanel.add(BorderLayout.CENTER, cctOptions);
            stOptions.setVisible(true);
            invalidate();
        }
    }// GEN-LAST:event_taskTypeItemStateChanged
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveActionPerformed
        ScheduledTask task = null; //TODO
        if (taskType.getSelectedItem() == "Server action") {
            //ServerTask task = (ServerTask) taskList.getValueAt(taskList.getSelectedRow(), taskList.getSelectedColumn());
            task = new ServerTask(Type.SAVE_ALL, repeating.isSelected(), 10, "Task");
        } else if (taskType.getSelectedItem() == "World backup") {
            task = (BackupTask) taskList.getValueAt(taskList.getSelectedRow(), taskList.getSelectedColumn());
        } else if (taskType.getSelectedItem() == "Custom command") {
            task = (ConsoleCommandTask) taskList.getValueAt(taskList.getSelectedRow(), taskList.getSelectedColumn());
        }

        Scheduler.registerTask(task);
        //updateFields();

        JOptionPane.showMessageDialog(this, Util.translate("Task saved."), Util.translate("Success"),
                JOptionPane.INFORMATION_MESSAGE);
    }// GEN-LAST:event_saveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton addTask;
    private JComboBox intervalType;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JPanel optionPanel;
    private JButton removeTask;
    private JCheckBox repeating;
    private JButton save;
    private JTable taskList;
    private JComboBox taskType;
    private JSpinner timeAmount;

    // End of variables declaration//GEN-END:variables

    private class TaskSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            taskType.setEnabled(true);
            timeAmount.setEnabled(true);
            intervalType.setEnabled(true);
            repeating.setEnabled(true);
        }
    }

    public final void translateComponent() {
        Util.translateTextComponent(addTask);
        Util.translateTextComponent(jLabel1);
        Util.translateTextComponent(removeTask);
        Util.translateTextComponent(save);
        Util.translateTextComponent(repeating);
        Util.translateTextComponent(jLabel2);
    }
}
