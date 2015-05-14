package me.escapeNT.pail.GUIComponents;

import com.feildmaster.bukkit.api.BukkitAPI;
import com.feildmaster.bukkit.api.BukkitArtifact;
import com.google.api.translate.Language;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import me.escapeNT.pail.Pail;
import me.escapeNT.pail.Util.Localizable;
import me.escapeNT.pail.Util.UpdateHandler;
import me.escapeNT.pail.Util.Util;
import me.escapeNT.pail.config.General;
import me.escapeNT.pail.config.PanelConfig;
import me.escapeNT.pail.config.ServerConfigHandler;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import org.spongepowered.api.Server;
import org.spongepowered.api.entity.player.gamemode.GameModes;
import org.spongepowered.api.world.storage.WorldProperties;

/**
 * Panel for editing server settings.
 *
 * @author escapeNT
 */
public class SettingsPanel extends javax.swing.JPanel implements Localizable {
    private static final long serialVersionUID = 7939463125089561533L;
    private WaypointEditPanel waypointEditor;
    private SchedulerPanel schedulerPanel;
    private AdvancedSettingsDialog advancedOptions;
    private boolean sel = true;

    /**
     * Creates new form SettingsPanel
     */
    public SettingsPanel() {
        initComponents();

        // load languages
        for (Language l : Language.class.getEnumConstants()) {
            if (!l.toString().equals("")) {
                language.addItem(l.getFullName());
                if (General.getLang().toString().equals(l.toString())) {
                    language.setSelectedItem(l.getFullName());
                }
            }
        }

        waypointEditor = new WaypointEditPanel();
        schedulerPanel = new SchedulerPanel();
        advancedOptions = new AdvancedSettingsDialog();
        advancedOptions.setVisible(false);

        craftVersion.setText(Util.translate("Craftbukkit version: ") + parseCraftVersion());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                parseCraftUpdate();
            }
        });
        pailVersion.setText(Util.translate("Pail version: ") + Pail.PLUGIN_VERSION);

        autoUpdate.setSelected(General.isAutoUpdate());
        loadConfig();
        advancedOptions.loadConfig();

        settingsTabs.add(Util.translate("Waypoints"), waypointEditor);
        settingsTabs.add(Util.translate("Scheduler"), schedulerPanel);

        autoUpdate.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                General.setAutoUpdate(autoUpdate.isSelected());
                General.save();

                if (autoUpdate.isSelected() && UpdateHandler.isUpToDate() != null && !UpdateHandler.isUpToDate()) {
                    new UpdateView().setVisible(true);
                }
            }
        });

        for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            try {
                Class<?> feel = Class.forName(laf.getClassName());
                if (((LookAndFeel) feel.newInstance()).isSupportedLookAndFeel()) {
                    themes.addItem(laf.getName());
                    if (laf.getClassName().equals(General.getLookAndFeel())) {
                        themes.setSelectedItem(laf.getName());
                    }
                }
            } catch (Exception ex) {
                Pail.getLogger().error(null, ex);
            }
        }

        translateComponent();

        tabActivationPanel.setLayout(new GridLayout(Util.getInterfaceComponents().keySet().size() / 2, 2));
    }

    private String parseCraftVersion() {
        try {
            String v = Pail.getGame().getApiVersion();
            return v.substring(v.indexOf("jnks") - 4, v.indexOf("jnks"));
        } catch (Exception ex) {
            return "Unknown";
        }
    }

    private void parseCraftUpdate() {
        try {
            final BukkitArtifact artifact = BukkitAPI.getLatest(BukkitAPI.Project.craftbukkit, BukkitAPI.Channel.rb);
            final String version = String.format("%s (%s)", artifact.getVersion(), artifact.getBuildNumber());

            update.setText(Util.translate("Latest recommended build: ") + version);

            try {
                String cv = parseCraftVersion();
                if (Integer.parseInt(cv) >= artifact.getBuildNumber()) {
                    update.setForeground(new Color(13, 190, 17));
                } else {
                    update.setForeground(Color.red);
                }
            } catch (NumberFormatException ex) {
            }
        } catch (Exception ex) {
            update.setText(Util.translate("Latest recommended build: Unknown"));
            update.setForeground(new Color(255, 200, 33));
        }
    }

    /**
     * Loads the stored values from the server configuration.
     */
    private void loadConfig() {
        String smotd = "A Minecraft Server";
        String sIP = "";
        String sSeed = "";
        String sWorld = "world";
        boolean bNether = true;
        boolean bMonsters = true;
        boolean bAnimals = true;
        boolean bFlight = false;
        boolean bPVP = true;
        int iViewDistance = 10;
        try {
            BufferedReader in = new BufferedReader(new FileReader("server.properties"));
            String str;
            while ((str = in.readLine()) != null) {
                if (str.indexOf("allow-nether") != -1) {
                    bNether = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("spawn-monsters") != -1) {
                    bMonsters = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("spawn-animals") != -1) {
                    bAnimals = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("allow-flight") != -1) {
                    bFlight = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("pvp") != -1) {
                    bPVP = Boolean.parseBoolean(str.split("=")[1].trim());
                } else if (str.indexOf("view-distance") != -1) {
                    iViewDistance = Integer.parseInt(str.split("=")[1].trim());
                } else if (str.indexOf("motd") != -1) {
                    smotd = str.split("=")[1].trim();
                } else if (str.indexOf("server-ip") != -1) {
                    try {
                        sIP = str.split("=")[1].trim();
                    }
                    catch (Exception e) {
                    }
                } else if (str.indexOf("level-seed") != -1) {
                    try {
                        sSeed = str.split("=")[1].trim();
                    }
                    catch (Exception e) {
                    }
                } else if (str.indexOf("level-name") != -1) {
                    sWorld = str.split("=")[1].trim();
                }
            }
            in.close();
        } catch (Exception e) {
        }
        // End temp fix

        Server s = Pail.getServer();
        Collection<WorldProperties>  worldProperties = s.getAllWorldProperties();

        for(WorldProperties p : worldProperties) {
          worldName.setText(sWorld);
          seed.setText(sSeed);
          ip.setText(sIP);
          
          nether.setSelected(bNether);
          spawnMonsters.setSelected(bMonsters);
          spawnAnimals.setSelected(bAnimals);
          flight.setSelected(bFlight);
          pvp.setSelected(bPVP);
          online.setSelected(s.getOnlineMode());
          whitelist.setSelected(s.hasWhitelist());
          hardcore.setSelected(p.isHardcore());
          
          viewDistance.setValue(iViewDistance);
          port.setValue(s.getBoundAddress().get().getPort());
          maxPlayers.setValue(s.getMaxPlayers());
          
          motd.setText(smotd);
          difficulty.setSelectedItem(p.getDifficulty());
          
          boolean c = p.getGameMode() == GameModes.CREATIVE; creative.setSelected(c);
          survival.setSelected(!c);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameMode = new javax.swing.ButtonGroup();
        settingsTabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        worldName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ip = new javax.swing.JTextField();
        nether = new javax.swing.JCheckBox();
        spawnMonsters = new javax.swing.JCheckBox();
        spawnAnimals = new javax.swing.JCheckBox();
        online = new javax.swing.JCheckBox();
        pvp = new javax.swing.JCheckBox();
        whitelist = new javax.swing.JCheckBox();
        flight = new javax.swing.JCheckBox();
        viewDistance = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        port = new javax.swing.JSpinner();
        revert = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        maxPlayers = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        seed = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        survival = new javax.swing.JRadioButton();
        creative = new javax.swing.JRadioButton();
        adventure = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        difficulty = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        motd = new javax.swing.JTextField();
        craftVersion = new javax.swing.JLabel();
        pailVersion = new javax.swing.JLabel();
        update = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        tabActivationPanel = new me.escapeNT.pail.GUIComponents.TabActivationPanel();
        reload = new javax.swing.JButton();
        autoUpdate = new javax.swing.JCheckBox();
        themes = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        language = new javax.swing.JComboBox();
        applyLang = new javax.swing.JButton();

        settingsTabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        settingsTabs.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        settingsTabs.setFocusable(false);

        jPanel1.setFocusable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(Util.translate("Server Properties")));

        jLabel1.setText("World name");

        worldName.setToolTipText("The name of the default world on the server");

        jLabel2.setText("World seed");

        ip.setToolTipText("Set this if you want the server to bind to a particular IP.");

        nether.setText("Allow nether");
        nether.setToolTipText("Allow portal transport to the nether.");

        spawnMonsters.setText("Spawn monsters");
        spawnMonsters.setToolTipText("Spawn hostile monsters.");

        spawnAnimals.setText("Spawn animals");
        spawnAnimals.setToolTipText("Spawn non-hostile animals.");

        online.setText("Online mode");
        online.setToolTipText("Server checks connecting players against minecraft's account database.");

        pvp.setText("Enable PVP");
        pvp.setToolTipText("Enable player verses player damage.");

        whitelist.setText("Whitelist enabled");
        whitelist.setToolTipText("With a whitelist enabled, users not on the list will be unable to connect.");

        flight.setText("Allow flight");
        flight.setToolTipText("Will allow users to use flight/no-clip on the server.");

        viewDistance.setToolTipText("The number of chunks sent to the client. (3-15)");

        jLabel3.setText("View distance");

        jLabel4.setText("Server port");

        port.setToolTipText("Port on which the server is running.");

        revert.setText("Revert");
        revert.setFocusable(false);
        revert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revertActionPerformed(evt);
            }
        });

        save.setText("Save");
        save.setFocusable(false);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel5.setText("Max players");
        jLabel5.setToolTipText("The maximum number of players allowed to connect.");

        jLabel6.setText("Server IP");

        seed.setToolTipText("The seed used in generating new terrain.");

        jLabel9.setText("Game mode");

        gameMode.add(survival);
        survival.setText("Survival");

        gameMode.add(creative);
        creative.setText("Creative");
        creative.setActionCommand("Creative");
        
        gameMode.add(adventure);
        adventure.setText("Adventure");
        adventure.setActionCommand("Adventure");

        jLabel10.setText("Difficulty");

        difficulty
                .setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Peaceful", "Easy", "Normal", "Hard" }));

        jLabel11.setText("MOTD");
        
        advanced = new JButton("Advanced...");
        advanced.setAction(adv_settings);
        
        hardcore = new JCheckBox("Hardcore enabled");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(Alignment.TRAILING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(187)
                    .addComponent(revert)
                    .addGap(191))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(spawnMonsters, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                            .addComponent(online, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(viewDistance, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                            .addGap(15)
                            .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(maxPlayers, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(port, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(jLabel10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
                                .addComponent(save)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(survival)
                                            .addGap(28)
                                            .addComponent(creative))
                                        .addComponent(difficulty, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
                                    .addGap(10)
                                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(adventure)
                                            .addGap(16))
                                        .addComponent(hardcore)))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(nether, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                            .addComponent(pvp, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(spawnAnimals, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addComponent(flight, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(whitelist, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addComponent(advanced))))
                    .addGap(88))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(jLabel11, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(motd)
                        .addComponent(ip)
                        .addComponent(seed)
                        .addComponent(worldName, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                    .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addComponent(worldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(seed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addComponent(ip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(14)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(motd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(nether)
                        .addComponent(pvp))
                    .addGap(17)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(spawnMonsters)
                        .addComponent(online))
                    .addGap(17)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(spawnAnimals)
                        .addComponent(whitelist))
                    .addGap(13)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(flight)
                        .addComponent(advanced, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(viewDistance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(maxPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(20)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(17)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(survival)
                        .addComponent(adventure)
                        .addComponent(creative, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(difficulty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(hardcore))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(revert)
                        .addComponent(save))
                    .addContainerGap())
        );
        jPanel2.setLayout(jPanel2Layout);

        craftVersion.setText("Craftbukkit version:");

        pailVersion.setText("Pail version:");
        pailVersion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pailVersionMouseClicked(evt);
            }
        });

        update.setText("Latest recommended build:");

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(Util.translate("Active Tabs")));

        GroupLayout tabActivationPanelLayout = new GroupLayout(tabActivationPanel);
        tabActivationPanel.setLayout(tabActivationPanelLayout);
        tabActivationPanelLayout.setHorizontalGroup(tabActivationPanelLayout.createParallelGroup(
                GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
        tabActivationPanelLayout.setVerticalGroup(tabActivationPanelLayout.createParallelGroup(
                GroupLayout.Alignment.LEADING).addGap(0, 270, Short.MAX_VALUE));

        tabActivationPanel.setBounds(10, 20, 400, 270);
        jLayeredPane1.add(tabActivationPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        reload.setText("Save");
        reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadActionPerformed(evt);
            }
        });
        reload.setBounds(320, 290, 80, 30);
        jLayeredPane1.add(reload, javax.swing.JLayeredPane.DEFAULT_LAYER);

        autoUpdate.setText("Automatically check for updates");
        autoUpdate.setFocusable(false);

        themes.setFocusable(false);
        themes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                themesItemStateChanged(evt);
            }
        });

        jLabel7.setText("Skin");

        jLabel8.setText("Language");

        language.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                languageItemStateChanged(evt);
            }
        });

        applyLang.setText("Apply language");
        applyLang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyLangActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(craftVersion, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                            .addGap(10))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(update, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                            .addGap(10))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(160)
                            .addComponent(autoUpdate, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(pailVersion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(290))
                        .addComponent(applyLang, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addPreferredGap(ComponentPlacement.UNRELATED))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(45)))
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                                .addComponent(themes, 0, 292, Short.MAX_VALUE)
                                .addComponent(language, 0, 292, Short.MAX_VALUE))
                            .addGap(11))
                        .addComponent(jLayeredPane1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                    .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(craftVersion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                            .addComponent(update, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(autoUpdate)
                                .addComponent(pailVersion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                            .addGap(17)
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addComponent(themes, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                            .addGap(10)
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addComponent(language, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                            .addGap(10)
                            .addComponent(applyLang, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(jLayeredPane1))
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
                    .addContainerGap())
        );
        jPanel1.setLayout(jPanel1Layout);

        settingsTabs.addTab("General", jPanel1);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(settingsTabs,
                GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(settingsTabs,
                GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    private void reloadActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_reloadActionPerformed
        for (JCheckBox b : tabActivationPanel.getBoxes().values()) {
            PanelConfig.getPanelsActivated().put(b.getText(), b.isSelected());
        }
        PanelConfig.save();
        Util.getPlugin().getMainWindow().loadPanels();
    }// GEN-LAST:event_reloadActionPerformed

    private void pailVersionMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_pailVersionMouseClicked
        new AboutView().setVisible(true);
    }// GEN-LAST:event_pailVersionMouseClicked

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveActionPerformed
        HashMap<String, String> saveData = new HashMap<String, String>();

        saveData.put("server-ip", ip.getText());
        saveData.put("level-name", worldName.getText());
        saveData.put("level-seed", seed.getText());
        saveData.put("motd", motd.getText());

        saveData.put("allow-nether", Boolean.toString(nether.isSelected()));
        saveData.put("spawn-monsters", Boolean.toString(spawnMonsters.isSelected()));
        saveData.put("spawn-animals", Boolean.toString(spawnAnimals.isSelected()));
        saveData.put("allow-flight", Boolean.toString(flight.isSelected()));
        saveData.put("pvp", Boolean.toString(pvp.isSelected()));
        saveData.put("online-mode", Boolean.toString(online.isSelected()));
        saveData.put("white-list", Boolean.toString(whitelist.isSelected()));

        saveData.put("view-distance", viewDistance.getValue().toString());
        saveData.put("server-port", port.getValue().toString());
        saveData.put("max-players", maxPlayers.getValue().toString());
        
        saveData.put("hardcore", Boolean.toString(hardcore.isSelected()));

        saveData.put("difficulty", Integer.toString(diffStringToInt(difficulty.getSelectedItem().toString())));
        saveData.put("gamemode", Integer.toString((survival.isSelected() ? 0 : creative.isSelected() ? 1 : 2)));
        
        saveData = advancedOptions.getData(saveData);

        ServerConfigHandler.save(saveData);

        JOptionPane.showMessageDialog(Util.getPlugin().getMainWindow(),
                "Server config saved!\nRestart the server to apply.", "Config Saved", JOptionPane.INFORMATION_MESSAGE);
    }// GEN-LAST:event_saveActionPerformed

    private void revertActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_revertActionPerformed
        loadConfig();
    }// GEN-LAST:event_revertActionPerformed

    private void themesItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_themesItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED && getThemes().getSelectedItem() != null) {
            for (final LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                if (laf.getName().equals((String) getThemes().getSelectedItem())) {
                    try {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    UIManager.setLookAndFeel((LookAndFeel) Class.forName(laf.getClassName())
                                            .newInstance());
                                } catch (Exception ex) {
                                    Pail.getLogger().error(null, ex);
                                }
                                UIManager.getLookAndFeelDefaults().put("ClassLoader", getClass().getClassLoader());
                                Util.getPlugin().getMainWindow().getRootPane().updateUI();
                                Util.getPlugin().getMainWindow().getJMenuBar().updateUI();
                                SwingUtilities.updateComponentTreeUI(Util.getPlugin().getMainWindow());
                            }
                        });
                    } catch (Exception ex) {
                        Pail.getLogger().error(null, ex);
                    }
                }
            }
        }
    }// GEN-LAST:event_themesItemStateChanged

    private void languageItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_languageItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED && !sel) {
            General.setLang(Language.fromString((String) language.getSelectedItem()));
        } else {
            sel = false;
        }
    }// GEN-LAST:event_languageItemStateChanged

    private void applyLangActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_applyLangActionPerformed
        Util.getPlugin().saveState();
        Util.dispatchCommand("reload");
    }// GEN-LAST:event_applyLangActionPerformed
     // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JButton applyLang;
    private javax.swing.JCheckBox autoUpdate;
    private javax.swing.JLabel craftVersion;
    private javax.swing.JRadioButton creative;
    private javax.swing.JRadioButton adventure;
    private javax.swing.JComboBox difficulty;
    private javax.swing.JCheckBox flight;
    private javax.swing.ButtonGroup gameMode;
    private javax.swing.JTextField ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox language;
    private javax.swing.JSpinner maxPlayers;
    private javax.swing.JTextField motd;
    private javax.swing.JCheckBox nether;
    private javax.swing.JCheckBox online;
    private javax.swing.JLabel pailVersion;
    private javax.swing.JSpinner port;
    private javax.swing.JCheckBox pvp;
    private javax.swing.JButton reload;
    private javax.swing.JButton revert;
    private javax.swing.JButton save;
    private javax.swing.JTextField seed;
    private javax.swing.JTabbedPane settingsTabs;
    private javax.swing.JCheckBox spawnAnimals;
    private javax.swing.JCheckBox spawnMonsters;
    private javax.swing.JRadioButton survival;
    private me.escapeNT.pail.GUIComponents.TabActivationPanel tabActivationPanel;
    private javax.swing.JComboBox themes;
    private javax.swing.JLabel update;
    private javax.swing.JSpinner viewDistance;
    private javax.swing.JCheckBox whitelist;
    private javax.swing.JTextField worldName;
    private javax.swing.JButton advanced;
    private final Action adv_settings = new SwingAction();
    private javax.swing.JCheckBox hardcore;

    // End of variables declaration//GEN-END:variables

    /**
     * @return the waypointEditor
     */
    public WaypointEditPanel getWaypointEditor() {
        return waypointEditor;
    }
    
    /**
     * @return the schedulerPanel
     */
    public SchedulerPanel getSchedulerPanel() {
        return schedulerPanel;
    }

    /**
     * @return the theme
     */
    public javax.swing.JComboBox getThemes() {
        return themes;
    }

    public final void translateComponent() {
        Util.translateTextComponent(jLabel1);
        Util.translateTextComponent(jLabel2);
        Util.translateTextComponent(jLabel3);
        Util.translateTextComponent(jLabel4);
        Util.translateTextComponent(jLabel5);
        Util.translateTextComponent(jLabel6);
        Util.translateTextComponent(jLabel7);
        Util.translateTextComponent(jLabel8);
        Util.translateTextComponent(jLabel9);
        Util.translateTextComponent(jLabel10);
        Util.translateTextComponent(jLabel11);
        Util.translateTextComponent(applyLang);
        Util.translateTextComponent(autoUpdate);
        Util.translateTextComponent(flight);
        Util.translateTextComponent(nether);
        Util.translateTextComponent(online);
        Util.translateTextComponent(pvp);
        Util.translateTextComponent(reload);
        Util.translateTextComponent(revert);
        Util.translateTextComponent(save);
        Util.translateTextComponent(spawnAnimals);
        Util.translateTextComponent(spawnMonsters);
        Util.translateTextComponent(whitelist);
        Util.translateTextComponent(creative);
        Util.translateTextComponent(survival);
        Util.translateTextComponent(adventure);
        Util.translateTextComponent(hardcore);
        Util.translateTextComponent(advanced);
    }

    private int diffStringToInt(String d) {
        Integer diff = null;
        if (d.equals("Peaceful")) {
            diff = 0;
        } else if (d.equals("Easy")) {
            return 1;
        } else if (d.equals("Normal")) {
            return 2;
        } else if (d.equals("Hard")) {
            return 3;
        }
        return diff;
    }
    private class SwingAction extends AbstractAction {
        public SwingAction() {
            putValue(NAME, "Advanced...");
            putValue(SHORT_DESCRIPTION, "Open the advanced settings");
        }
        public void actionPerformed(ActionEvent e) {
            advancedOptions.setVisible(true);
            advancedOptions.setSize(500, 600);
        }
    }
}
