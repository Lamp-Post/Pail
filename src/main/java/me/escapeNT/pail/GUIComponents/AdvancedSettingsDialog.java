package me.escapeNT.pail.GUIComponents;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import me.escapeNT.pail.Pail;

import org.spongepowered.api.Server;
import org.spongepowered.api.entity.player.gamemode.GameModes;
import org.spongepowered.api.world.storage.WorldProperties;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AdvancedSettingsDialog extends JFrame {

    public AdvancedSettingsDialog() {
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        world = new JPanel();
        world.setToolTipText("World");
        tabbedPane.addTab("World", null, world, "World settings");
        
        forceGameMode = new JCheckBox("Force gamemode");
        
        npcs = new JCheckBox("NPC's enabled");
        
        generateStructures = new JCheckBox("Generate structures");
        
        commandBlockEnabled = new JCheckBox("Command block enabled");
        
        announcePlayerAchievements = new JCheckBox("Announce player achievements");
        
        lblLeveltype = new JLabel("Level-type");
        
        levelType = new JComboBox();
        levelType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEFAULT", "FLAT", "LARGEBIOMES", "AMPLIFIED", "CUSTOMIZED" }));
        
        lblPlayerIdleTimeout = new JLabel("Player idle timeout");
        
        playerIdleTimeout = new JSpinner(new SpinnerNumberModel(0, 0, 500, 1));
        
        lblMinutes = new JLabel("minute(s)");
        
        lblSpawnProtection = new JLabel("Spawn protection");
        
        spawnProtection = new JSpinner(new SpinnerNumberModel(0, 0, 500, 1));
        
        lblMaxWorldSize = new JLabel("Max world size");
        
        worldSize = new JSpinner(new SpinnerNumberModel(0, 0, 29999984, 1));
        
        lblMaxBuildHeight = new JLabel("Max build height");
        
        buildHeight = new JSpinner(new SpinnerNumberModel(0, 0, 700, 1));
        
        lblGeneratorSettings = new JLabel("Generator settings");
        
        generatorSettings = new JTextField();
        generatorSettings.setColumns(10);
        GroupLayout gl_world = new GroupLayout(world);
        gl_world.setHorizontalGroup(
            gl_world.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_world.createSequentialGroup()
                    .addGap(14)
                    .addGroup(gl_world.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_world.createSequentialGroup()
                            .addComponent(lblGeneratorSettings)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(generatorSettings, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(gl_world.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_world.createSequentialGroup()
                                .addComponent(lblMaxWorldSize)
                                .addContainerGap())
                            .addGroup(gl_world.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_world.createSequentialGroup()
                                    .addComponent(lblLeveltype)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(levelType, 0, 160, Short.MAX_VALUE)
                                    .addGap(261))
                                .addGroup(gl_world.createSequentialGroup()
                                    .addComponent(announcePlayerAchievements)
                                    .addContainerGap())
                                .addGroup(gl_world.createSequentialGroup()
                                    .addComponent(commandBlockEnabled)
                                    .addContainerGap())
                                .addGroup(gl_world.createSequentialGroup()
                                    .addComponent(generateStructures)
                                    .addContainerGap())
                                .addGroup(gl_world.createSequentialGroup()
                                    .addComponent(npcs)
                                    .addContainerGap())
                                .addGroup(gl_world.createSequentialGroup()
                                    .addComponent(forceGameMode)
                                    .addContainerGap())
                                .addGroup(gl_world.createSequentialGroup()
                                    .addGroup(gl_world.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblSpawnProtection)
                                        .addComponent(lblMaxBuildHeight)
                                        .addComponent(lblPlayerIdleTimeout))
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addGroup(gl_world.createParallelGroup(Alignment.LEADING)
                                        .addComponent(spawnProtection, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                        .addComponent(buildHeight, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                        .addComponent(playerIdleTimeout, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                        .addComponent(worldSize, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(lblMinutes)
                                    .addGap(236))))))
        );
        gl_world.setVerticalGroup(
            gl_world.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_world.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(forceGameMode)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(npcs)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(generateStructures)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(commandBlockEnabled)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(announcePlayerAchievements)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_world.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblLeveltype)
                        .addComponent(levelType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_world.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblSpawnProtection)
                        .addComponent(spawnProtection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_world.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblMaxBuildHeight)
                        .addComponent(buildHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_world.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPlayerIdleTimeout)
                        .addComponent(playerIdleTimeout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMinutes))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_world.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblMaxWorldSize)
                        .addComponent(worldSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_world.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblGeneratorSettings)
                        .addComponent(generatorSettings, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(182))
        );
        world.setLayout(gl_world);
        
        other = new JPanel();
        other.setToolTipText("Other");
        tabbedPane.addTab("Other", null, other, "Other settings");
        
        lblRconPort = new JLabel("Rcon port");
        
        rconPort = new JSpinner(new SpinnerNumberModel(1, 1, 65534, 1));
        rconPort.setEnabled(false);
        
        lblRconPassword = new JLabel("Rcon password");
        
        rconPassword = new JTextField();
        rconPassword.setColumns(10);  
        rconPassword.setEnabled(false);
        
        rcon = new JCheckBox("Rcon enabled");
        rcon.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                if (rcon.isSelected()) {
                    rconPort.setEnabled(true);
                    rconPassword.setEnabled(true);
                } else if (!rcon.isSelected()) {
                    rconPort.setEnabled(false);
                    rconPassword.setEnabled(false);
                }
            }
        });
        
        query = new JCheckBox("Query enabled");
        query.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                if (query.isSelected()) {
                    queryPort.setEnabled(true);
                } else if (!query.isSelected()) {
                    queryPort.setEnabled(false);
                }
            }
        });
        
        lblQueryPort = new JLabel("Query port");
        
        queryPort = new JSpinner(new SpinnerNumberModel(1, 1, 65534, 1));
        queryPort.setEnabled(false);
        
        lblResourcePack = new JLabel("Resource pack");
        
        resourcePack = new JTextField();
        resourcePack.setColumns(10);
        
        lblResourcePackHash = new JLabel("Resource pack hash");
        
        resourcePackHash = new JTextField();
        resourcePackHash.setColumns(10);
        
        lblOpPermissionLevel = new JLabel("OP permission level");
        
        opPermLevel = new JComboBox();
        opPermLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        
        snooper = new JCheckBox("Snooper enabled");
        
        lblNetworkCompressionThreshold = new JLabel("Network compression threshold");
        
        lblMaxTicktime = new JLabel("Max tick-time");
        
        netCompThreshold = new JSpinner(new SpinnerNumberModel(256, -1, 1500, 1));
        
        tickTime = new JSpinner(new SpinnerNumberModel(60000, 0, Integer.MAX_VALUE, 1));
        GroupLayout gl_other = new GroupLayout(other);
        gl_other.setHorizontalGroup(
            gl_other.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_other.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_other.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_other.createSequentialGroup()
                            .addGap(15)
                            .addComponent(rcon, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_other.createSequentialGroup()
                            .addGap(18)
                            .addGroup(gl_other.createParallelGroup(Alignment.LEADING)
                                .addComponent(query)
                                .addGroup(gl_other.createSequentialGroup()
                                    .addComponent(lblRconPort)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(rconPort, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(lblRconPassword)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(rconPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_other.createSequentialGroup()
                                    .addComponent(lblQueryPort)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(queryPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_other.createSequentialGroup()
                                    .addGroup(gl_other.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblResourcePackHash)
                                        .addComponent(lblResourcePack)
                                        .addComponent(lblOpPermissionLevel))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(gl_other.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(opPermLevel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(resourcePackHash, 320, 320, Short.MAX_VALUE)
                                        .addComponent(resourcePack)))
                                .addComponent(snooper, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                .addGroup(gl_other.createSequentialGroup()
                                    .addGroup(gl_other.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNetworkCompressionThreshold)
                                        .addComponent(lblMaxTicktime, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addGroup(gl_other.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(netCompThreshold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tickTime, GroupLayout.PREFERRED_SIZE, 89, Short.MAX_VALUE))))))
                    .addGap(1))
        );
        gl_other.setVerticalGroup(
            gl_other.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_other.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(rcon)
                    .addGap(7)
                    .addGroup(gl_other.createParallelGroup(Alignment.BASELINE)
                        .addComponent(rconPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(rconPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRconPort)
                        .addComponent(lblRconPassword))
                    .addGap(18)
                    .addComponent(query)
                    .addGap(7)
                    .addGroup(gl_other.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblQueryPort)
                        .addComponent(queryPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_other.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblResourcePack)
                        .addComponent(resourcePack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_other.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblResourcePackHash)
                        .addComponent(resourcePackHash, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_other.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblOpPermissionLevel)
                        .addComponent(opPermLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addComponent(snooper)
                    .addGap(18)
                    .addGroup(gl_other.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNetworkCompressionThreshold)
                        .addComponent(netCompThreshold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_other.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblMaxTicktime, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tickTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(1))
        );
        other.setLayout(gl_other);
        
        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        Ok.setAction(ok);
        panel.add(Ok);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setAction(cancel);
        panel.add(btnCancel);

    }

    private static final long serialVersionUID = -3672286771125595988L;
    private JTextField rconPassword;
    private JTextField resourcePack;
    private JTextField resourcePackHash;
    private JLabel label;
    private JTabbedPane tabbedPane;
    private JPanel world;
    private JCheckBox forceGameMode;
    private JCheckBox npcs;
    private JCheckBox generateStructures;
    private JCheckBox commandBlockEnabled;
    private JCheckBox announcePlayerAchievements;
    private JLabel lblLeveltype;
    private JComboBox levelType;
    private JPanel other;
    private JLabel lblRconPort;
    private JSpinner rconPort;
    private JLabel lblRconPassword;
    private JCheckBox rcon;
    private JCheckBox query;
    private JSpinner queryPort;
    private JLabel lblQueryPort;
    private JLabel lblResourcePack;
    private JLabel lblResourcePackHash;
    private JComboBox opPermLevel;
    private JCheckBox snooper;
    private JLabel lblNetworkCompressionThreshold;
    private JLabel lblOpPermissionLevel;
    private JLabel lblMaxTicktime;
    private JLabel lblPlayerIdleTimeout;
    private JSpinner playerIdleTimeout;
    private JLabel lblMinutes;
    private JLabel lblSpawnProtection;
    private JSpinner spawnProtection;
    private JLabel lblMaxWorldSize;
    private JSpinner worldSize;
    private JLabel lblMaxBuildHeight;
    private JSpinner buildHeight;
    private JLabel lblGeneratorSettings;
    private JTextField generatorSettings;
    private JPanel panel;
    private JSpinner netCompThreshold;
    private JSpinner tickTime;
    private final JButton Ok = new JButton("Ok");
    private final Action ok = new SwingAction();
    private final Action cancel = new SwingAction_1();

    public HashMap<String, String> getData(HashMap<String, String> saveData) {

        saveData.put("max-tick-time", Integer.toString((Integer) tickTime.getValue()));
        saveData.put("max-build-height", Integer.toString((Integer) buildHeight.getValue()));
        saveData.put("spawn-protection", Integer.toString((Integer) spawnProtection.getValue()));
        saveData.put("player-idle-timeout", Integer.toString((Integer) playerIdleTimeout.getValue()));
        saveData.put("network-compression-threshold", Integer.toString((Integer) netCompThreshold.getValue()));
        saveData.put("max-world-size", Integer.toString((Integer) worldSize.getValue()));
        
        saveData.put("enable-command-block", Boolean.toString(commandBlockEnabled.isSelected()));
        saveData.put("enable-query", Boolean.toString(query.isSelected()));
        saveData.put("enable-rcon", Boolean.toString(rcon.isSelected()));
        saveData.put("force-gamemode", Boolean.toString(forceGameMode.isSelected()));
        saveData.put("spawn-npcs", Boolean.toString(npcs.isSelected()));
        saveData.put("snooper-enabled", Boolean.toString(snooper.isSelected()));
        saveData.put("announce-player-achievements", Boolean.toString(announcePlayerAchievements.isSelected()));
        saveData.put("generate-structures", Boolean.toString(generateStructures.isSelected()));
        
        saveData.put("level-type", (String) levelType.getSelectedItem());
        saveData.put("op-permission-level", (String) opPermLevel.getSelectedItem());
        
        if(query.isSelected()) {
            saveData.put("query.port", Integer.toString((Integer) queryPort.getValue()));
        }
        
        if(rcon.isSelected()) {
            saveData.put("rcon.port", Integer.toString((Integer) rconPort.getValue()));
            saveData.put("rcon.password", rconPassword.getText());
        }

        saveData.put("resource-pack", resourcePack.getText());
        saveData.put("resource-pack-hash", resourcePackHash.getText());
        saveData.put("generator-settings", generatorSettings.getText());
        
        return saveData;
    }
    
    private class SwingAction extends AbstractAction {
        private static final long serialVersionUID = 3585087674104426155L;
        public SwingAction() {
            putValue(NAME, "OK");
            putValue(SHORT_DESCRIPTION, "Close the window");
        }
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
    
    private class SwingAction_1 extends AbstractAction {
        private static final long serialVersionUID = -6946563811719809076L;
        public SwingAction_1() {
            putValue(NAME, "Cancel");
            putValue(SHORT_DESCRIPTION, "Cancel");
        }
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            loadConfig();
        }
    }
    
    public void loadConfig() {
        int iTickTime = 60000;
        int iBuildHeight = 256;
        int iSpawnProtection = 16;
        int iPlayerIdleTimeout = 0;
        int iNetCompThreshold = 256;
        int iWorldSize = 29999984;
        
        boolean bCommandBlockEnabled = false;
        boolean bQuery = false;
        boolean bRcon = false;
        boolean bForceGameMode = false;
        boolean bNpcs = true;
        boolean bSnooper = true;
        boolean bAnnouncePlayerAchievements = true;
        boolean bGenerateStructures = true;
        
        String vLevelType = "DEFAULT";
        String vOpPermLevel = "4";
        
        int iQueryPort = 25565;
        int iRconPort = 25575;
        String sRconPassword = "";

        String sResourcePack = "";
        String sResourcePackHash = "";
        String sGeneratorSettings = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader("server.properties"));
            //String str;
            for (String str = in.readLine(); str != null; str = in.readLine()) {
                if (str.indexOf("max-tick-time") != -1) {
                    iTickTime = Integer.parseInt(str.split("=")[1].trim());
                } else if (str.indexOf("max-build-height") != -1) {
                    iBuildHeight = Integer.parseInt(str.split("=")[1].trim());
                } else if (str.indexOf("spawn-protection") != -1) {
                    iSpawnProtection = Integer.parseInt(str.split("=")[1].trim());
                } else if (str.indexOf("player-idle-timeout") != -1) {
                    iPlayerIdleTimeout = Integer.parseInt(str.split("=")[1].trim());
                } else if (str.indexOf("network-compression-threshold") != -1) {
                    iNetCompThreshold = Integer.parseInt(str.split("=")[1].trim());
                } else if (str.indexOf("max-world-size") != -1) {
                    iWorldSize = Integer.parseInt(str.split("=")[1].trim());
                } else if (str.indexOf("enable-command-block") != -1) {
                    bCommandBlockEnabled = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("enable-query") != -1) {
                    bQuery = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("enable-rcon") != -1) {
                    bRcon = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("force-gamemode") != -1) {
                    bForceGameMode = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("spawn-npcs") != -1) {
                    bNpcs = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("snooper-enabled") != -1) {
                    bSnooper = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("announce-player-achievements") != -1) {
                    bAnnouncePlayerAchievements = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("generate-structures") != -1) {
                    bGenerateStructures = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("level-type") != -1) {
                    vLevelType = str.split("=")[1].trim();
                } else if (str.indexOf("op-permission-level") != -1) {
                    vOpPermLevel = str.split("=")[1].trim();
                } else if (str.indexOf("query.port") != -1) {
                    iQueryPort = Integer.parseInt(str.split("=")[1].trim());
                } else if (str.indexOf("rcon.port") != -1) {
                    iRconPort = Integer.parseInt(str.split("=")[1].trim());
                } else if (str.indexOf("rcon.password") != -1) {
                    try {
                        sRconPassword = str.split("=")[1].trim();
                    }
                    catch (Exception e) {
                    }
                } else if (str.indexOf("resource-pack") != -1) {
                    try {
                         sResourcePack = str.split("=")[1].trim();
                    }
                    catch (Exception e) {
                    }
                } else if (str.indexOf("resource-pack-hash") != -1) {
                    try {
                        sResourcePackHash = str.split("=")[1].trim();
                    }
                    catch (Exception e) {
                    }
                } else if (str.indexOf("generator-settings") != -1) {
                    try {
                        sGeneratorSettings = str.split("=")[1].trim();
                    }
                    catch (Exception e) {
                    }
                }
            }
            in.close();
        } catch (Exception e) {
            Pail.getLogger().error(e.toString());
        }
        // End temp fix

        tickTime.setValue(iTickTime);
        buildHeight.setValue(iBuildHeight);
        spawnProtection.setValue(iSpawnProtection);
        playerIdleTimeout.setValue(iPlayerIdleTimeout);
        netCompThreshold.setValue(iNetCompThreshold);
        worldSize.setValue(iWorldSize);
            
        commandBlockEnabled.setSelected(bCommandBlockEnabled);
        query.setSelected(bQuery);
        rcon.setSelected(bRcon);
        forceGameMode.setSelected(bForceGameMode);
        npcs.setSelected(bNpcs);
        snooper.setSelected(bSnooper);
        announcePlayerAchievements.setSelected(bAnnouncePlayerAchievements);
        generateStructures.setSelected(bGenerateStructures);
            
        if (vLevelType.contains("DEFAULT")) {
            levelType.setSelectedIndex(0);
        } else if (vLevelType.contains("FLAT")) {
            levelType.setSelectedIndex(1);
        } else if (vLevelType.contains("LARGEBIOMES")) {
            levelType.setSelectedIndex(2);
        } else if (vLevelType.contains("AMPLIFIED")) {
            levelType.setSelectedIndex(3);
        } else if (vLevelType.contains("CUSTOMIZED")) {
            levelType.setSelectedIndex(4);
        }
        
        if (vOpPermLevel.equals("1")) {
            opPermLevel.setSelectedIndex(0);
        } else if (vOpPermLevel.equals("2")) {
            opPermLevel.setSelectedIndex(1);
        } else if (vOpPermLevel.equals("3")) {
            opPermLevel.setSelectedIndex(2);
        } else if (vOpPermLevel.equals("4")) {
            opPermLevel.setSelectedIndex(3);
        }
        
        queryPort.setValue(iQueryPort);   
        if(query.isSelected()) {
            queryPort.setEnabled(true);
        }
        
        rconPort.setValue(iRconPort);
        rconPassword.setText(sRconPassword); 
        if(rcon.isSelected()) {
            rconPort.setEnabled(true);
            rconPassword.setEnabled(true);
        }

        resourcePack.setText(sResourcePack);
        resourcePackHash.setText(sResourcePackHash);
        generatorSettings.setText(sGeneratorSettings);
    }
}