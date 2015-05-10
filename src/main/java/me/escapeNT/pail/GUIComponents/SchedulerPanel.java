
package me.escapeNT.pail.GUIComponents;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.Util.Util;
import me.escapeNT.pail.scheduler.BackupTask;
import me.escapeNT.pail.scheduler.ConsoleCommandTask;
import me.escapeNT.pail.scheduler.ScheduledTask;
import me.escapeNT.pail.scheduler.Scheduler;
import me.escapeNT.pail.scheduler.ServerTask;

import javax.swing.JTextField;

/**
 * Panel for editing scheduled tasks.
 * @author escapeNT
 */
public class SchedulerPanel extends javax.swing.JPanel implements Localizable {

    private static final long serialVersionUID = -6553267508393113123L;
    private boolean newValue;

    /** Creates new form SchedulerPanel */
    public SchedulerPanel() {
        initComponents();
        translateComponent();
        Iterator<Entry<ScheduledTask, Boolean>> schtasksItr =Scheduler.getTasks().entrySet().iterator();
        while(schtasksItr.hasNext()) {
            Entry<ScheduledTask, Boolean> schtasksEntry = schtasksItr.next();
            ScheduledTask schtasks = schtasksEntry.getKey();
            Boolean enabled = schtasksEntry.getValue();
            ((DefaultTableModel)taskList.getModel()).addRow(
                    new Object [] { schtasks.getName(), enabled } 
                    );
        }
        taskList.getSelectionModel().addListSelectionListener(new TaskSelectionListener());
        updateFields();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        taskList = new JTable();
        addTask = new JButton();
        removeTask = new JButton();
        jLabel2 = new JLabel();
        taskType = new JComboBox();
        optionPanel = new OptionsTaskPanel();
        save = new JButton();
        jLabel3 = new JLabel();
        timeAmount = new JSpinner();
        intervalType = new JComboBox();
        repeating = new JCheckBox();

        taskList.setModel(new DefaultTableModel(new Object[][] {},
                new String[] { "Task Name", "Enabled" }) {
                    private static final long serialVersionUID = 1656034351530643883L;
            Class[] types = new Class[] {
                    String.class, Boolean.class, ScheduledTask.class
                    };
            boolean[] canEdit = new boolean[] {
                    false, true
                    };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        


        TableCellListener tcl = new TableCellListener(taskList, new SwingAction());
        
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

        jLabel2.setText("Type");

        taskType.setModel(new DefaultComboBoxModel(new String[] { "Server action", "World backup", "Custom command" }));
        taskType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                taskTypeItemStateChanged(evt);
            }
        });

        save.setText("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel3.setText("Execute every");

        timeAmount.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        timeAmount.setFocusable(false);

        intervalType.setModel(new DefaultComboBoxModel(new String[] { "Seconds", "Minutes", "Hours", "Days" }));
        intervalType.setSelectedIndex(1);

        repeating.setSelected(true);
        repeating.setText("Repeating");
        repeating.setFocusable(false);
        
        jLabel1 = new JLabel("Name");
        
        name = new JTextField();
        name.setColumns(10);
        name.setText("Task " + (taskList.getModel().getRowCount() + 1));

        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(removeTask, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(addTask, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(optionPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                        .addGroup(Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(timeAmount, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                            .addComponent(intervalType, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
                        .addGroup(Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(name, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                .addComponent(taskType, 0, 352, Short.MAX_VALUE)))
                        .addComponent(repeating, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                        .addComponent(save))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20)
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1)
                                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(taskType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addGap(8)
                                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timeAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(intervalType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(repeating))
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(142)
                            .addComponent(optionPanel, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(addTask, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                            .addComponent(save))
                        .addComponent(removeTask, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                    .addGap(17))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void addTaskActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addTaskActionPerformed
        ((DefaultTableModel)taskList.getModel()).addRow(new Object[] {
                "Task " + (taskList.getModel().getRowCount() + 1), Boolean.TRUE } );
        taskList.setRowSelectionInterval(taskList.getModel().getRowCount() - 1, taskList.getModel().getRowCount() - 1);
    }//GEN-LAST:event_addTaskActionPerformed

    private void removeTaskActionPerformed(ActionEvent evt) {//GEN-FIRST:event_removeTaskActionPerformed
        if(taskList.getSelectedRow() != -1) {
            Scheduler.removeTask((String) taskList.getModel().getValueAt(taskList.getSelectedRow(), 0));
            ((DefaultTableModel)taskList.getModel()).removeRow(taskList.getSelectedRow());
            updateFields();
        }
    }//GEN-LAST:event_removeTaskActionPerformed

    private void taskTypeItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_taskTypeItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED) {
        optionPanel.setTaskType((String) taskType.getSelectedItem());
        }
    }//GEN-LAST:event_taskTypeItemStateChanged
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveActionPerformed
        ScheduledTask task = null;
        if ((String) taskType.getSelectedItem() == "Server action") {
            task = new ServerTask(optionPanel.getTask(), repeating.isSelected(), (Integer) timeAmount.getValue(), (String) intervalType.getSelectedItem(), name.getText());
        } else if ((String) taskType.getSelectedItem() == "World backup") {
            task = new BackupTask(optionPanel.getWorld(), optionPanel.getBroadcast(), repeating.isSelected(), (Integer) timeAmount.getValue(), (String) intervalType.getSelectedItem(), name.getText());
        } else if ((String) taskType.getSelectedItem() == "Custom command") {
            task = new ConsoleCommandTask(optionPanel.getCommand(), repeating.isSelected(), (Integer) timeAmount.getValue(), (String) intervalType.getSelectedItem(), name.getText());
        }
        
        taskList.getModel().setValueAt(task.getName(), taskList.getSelectedRow(), 0);
        
        Scheduler.registerTask(task);
        
        updateFields();
        revalidate();
        repaint();

        JOptionPane.showMessageDialog(this, Util.translate("Task saved."), Util.translate("Success"),
                JOptionPane.INFORMATION_MESSAGE);
    }// GEN-LAST:event_saveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton addTask;
    private JComboBox intervalType;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private OptionsTaskPanel optionPanel;
    private JButton removeTask;
    private JCheckBox repeating;
    private JButton save;
    private JTable taskList;
    private JComboBox taskType;
    private JSpinner timeAmount;
    private JTextField name;
    private JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    private class TaskSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            updateFields();
        }
    }
    
    public final void translateComponent() {
        Util.translateTextComponent(addTask);
        Util.translateTextComponent(jLabel2);
        Util.translateTextComponent(removeTask);
        Util.translateTextComponent(save);
        Util.translateTextComponent(repeating);
        Util.translateTextComponent(jLabel3);
    }
    
    private void updateFields() {
        if(taskList.getSelectedRow() == -1) {
            taskType.setEnabled(false);
            timeAmount.setEnabled(false);
            intervalType.setEnabled(false);
            repeating.setEnabled(false);
            optionPanel.setEnabled(false);
            jLabel2.setEnabled(false);
            jLabel3.setEnabled(false);
            save.setEnabled(false);
            name.setEnabled(false);
            jLabel1.setEnabled(false);
            optionPanel.setDisabled();
        } else {
            taskType.setEnabled(true);
            timeAmount.setEnabled(true);
            intervalType.setEnabled(true);
            repeating.setEnabled(true);
            optionPanel.setEnabled(true);
            jLabel2.setEnabled(true);
            jLabel3.setEnabled(true);
            save.setEnabled(true);
            name.setEnabled(true);
            jLabel1.setEnabled(true);
            optionPanel.setEnabled();
        }
    }
    private class SwingAction extends AbstractAction {
        private static final long serialVersionUID = -6626190053093055156L;
        public SwingAction() {
            putValue(NAME, "CellEditListener");
            putValue(SHORT_DESCRIPTION, "Edit cell listener");
        }
        public void actionPerformed(ActionEvent e) {
            TableCellListener tcl = (TableCellListener)e.getSource();
            newValue = (Boolean) tcl.getNewValue();
            String taskName = (String) taskList.getModel().getValueAt(tcl.getRow(), 0);
            if (newValue) {
                ScheduledTask task = Scheduler.getTask(taskName);
                    
                task.setEnabled(true);
                Scheduler.registerTask(task);
                Pail.getLogger().info("skdgm");
            } else {
                ScheduledTask task = Scheduler.getTask(taskName);

                Scheduler.removeTask(taskName);

                task.setEnabled(false);
                Scheduler.registerTask(task);
                Pail.getLogger().info("2klsjdg");
            }
            
        }
    }
}