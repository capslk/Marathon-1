/*******************************************************************************
 *  
 *  Copyright (C) 2010 Jalian Systems Private Ltd.
 *  Copyright (C) 2010 Contributors to Marathon OSS Project
 * 
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Library General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Library General Public License for more details.
 * 
 *  You should have received a copy of the GNU Library General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * 
 *  Project website: http://www.marathontesting.com
 *  Help: Marathon help forum @ http://groups.google.com/group/marathon-testing
 * 
 *******************************************************************************/
package net.sourceforge.marathon.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class SampleApp extends JApplet {
    private static final long serialVersionUID = 1L;

    public static class SampleAppDialog extends JDialog {

        public final class JExtendedLabel extends JLabel {
            private static final long serialVersionUID = 1L;

            private JExtendedLabel(String text) {
                super(text);
            }

            public Map<String, Object> getAMap() {
                Map<String, Object> m = new HashMap<String, Object>();
                m.put("System", System.getProperties());
                m.put("Env", System.getenv());
                return m;
            }

            public Set<Entry<Object, Object>> getASet() {
                return System.getProperties().entrySet();
            }
        }

        class SampleAppDialogTableModel extends AbstractTableModel {

            private static final long serialVersionUID = 1L;

            private List<SampleData> dataList;
            private String[] columnNames = { "Name", "Age", "Marital Status" };

            public SampleAppDialogTableModel() {
                dataList = new ArrayList<SampleData>();
            }

            public void removeRow(int selectedRow) {
                dataList.remove(selectedRow);
                fireTableDataChanged();
            }

            public int getColumnCount() {
                return 3;
            }

            public int getRowCount() {
                return dataList.size();
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                SampleData data = dataList.get(rowIndex);

                switch (columnIndex) {
                case 0:
                    return data.getName();
                case 1:
                    return data.getAge();
                case 2:
                    return data.isMarried();
                default:
                    return "";
                }
            }

            public String getColumnName(int column) {
                return columnNames[column];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return true;
            }

            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                case 0:
                default:
                    return String.class;
                case 1:
                    return Integer.class;
                case 2:
                    return Boolean.class;
                }
            }

            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                SampleData data = dataList.get(rowIndex);
                switch (columnIndex) {
                case 0:
                    data.setName(aValue.toString());
                    break;
                case 1:
                    data.setAge((Integer) aValue);
                    break;
                case 2:
                    data.setMarried((Boolean) aValue);
                    break;
                }
            }

            public void addRow() {
                dataList.add(new SampleData());
                fireTableDataChanged();
            }

            public void addRow(String name, int age, boolean married) {
                dataList.add(new SampleData(name, age, married));
                fireTableDataChanged();
            }

        }

        class SampleData {
            private String name;
            private Integer age;
            private Boolean married;

            public SampleData() {
                name = "";
                age = new Integer(0);
                married = Boolean.FALSE;
            }

            public SampleData(String name, int age, boolean married) {
                this.name = name;
                this.age = new Integer(age);
                this.married = Boolean.valueOf(married);
            }

            public Integer getAge() {
                return age;
            }

            public Boolean isMarried() {
                return married;
            }

            public String getName() {
                return name;
            }

            public void setAge(Integer age) {
                this.age = age;
            }

            public void setMarried(Boolean married) {
                this.married = married;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        private static final long serialVersionUID = 1L;
        private GridBagConstraints gbc;
        private JPanel simpleWidgetsPanel;

        public SampleAppDialog() {
            setModal(true);
            setTitle("Marathon Demo");
            final JTabbedPane pane = new JTabbedPane();
            pane.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    String name = pane.getTitleAt(pane.getSelectedIndex());
                    setTitle(name);
                }
            });
            pane.setName("tabbedpane");
            pane.addTab("�rbol de demostraci�n", new JScrollPane(createSimpleWidgetPanel()));
            pane.addTab("Table Demo", new JScrollPane(createTablePanel()));
            pane.addTab("Tree Demo", new JScrollPane(createTreePanel()));
            pane.addTab("Chooser Demo", new JScrollPane(createChooserPanel()));
            final JToolBar toolbar = new JToolBar();
            toolbar.setFloatable(false);
            URL url = getClass().getClassLoader().getResource("images/save.gif");
            Icon icon = new ImageIcon(url);

            JButton saveButton = new JButton(icon);
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    JOptionPane.showMessageDialog(null, "To save or not to save is the question ?", "Save",
                            JOptionPane.QUESTION_MESSAGE);
                }
            });
            saveButton.setName("Save");
            url = getClass().getClassLoader().getResource("images/load.gif");
            icon = new ImageIcon(url);
            JButton loadButton = new JButton(icon);
            loadButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    JOptionPane.showMessageDialog(SampleAppDialog.this, "Open the Pandora's Box", "Load",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });

            loadButton.setName("Load");
            toolbar.add(saveButton);
            toolbar.add(loadButton);
            Timer timer = new Timer("sleepy-button");
            timer.schedule(new TimerTask() {
                @Override public void run() {
                    JButton comp = new JButton("Sleepy");
                    comp.setToolTipText("This is a sleepy button");
                    comp.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            findPrecedingLabels();
                        }

                    });
                    toolbar.add(comp);
                }
            }, 3000);
            getContentPane().add(toolbar, BorderLayout.NORTH);
            getContentPane().add(pane);
            setJMenuBar(createMenuBar());
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                    System.exit(0);
                }
            });
            pack();
        }

        private void findPrecedingLabels() {
            Component[] components = simpleWidgetsPanel.getComponents();
            for (Component component : components) {
                findPrecedingLabel(component, simpleWidgetsPanel);
            }
        }

        private void findPrecedingLabel(Component component, Container parent) {
            if (component instanceof JLabel)
                return;
            Component[] allComponents = parent.getComponents();
            // Find labels in the same row
            // In the same row: labelx < componentx, labely >= componenty
            for (Component label : allComponents) {
                if (label instanceof JLabel) {
                    if (label.getX() < component.getX() && label.getY() >= component.getY()
                            && label.getY() <= component.getY() + component.getHeight()) {
                        String identity = component.getClass().getName();
                        if (component.getName() != null)
                            identity = component.getName();
                        System.out.println(((JLabel) label).getText() + " for " + identity);
                    }
                }
            }
        }

        private JMenuBar createMenuBar() {
            JMenuBar bar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            fileMenu.setMnemonic('f');

            JMenuItem item = new JMenuItem("Exit");
            item.setMnemonic('e');
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }

            });
            fileMenu.add(item);
            bar.add(fileMenu);

            JMenu helpMenu = new JMenu("Help");
            helpMenu.setMnemonic('h');
            item = new JMenuItem("About");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(SampleAppDialog.this, "Marathon Test Demo version 1.0", "About Marathon Demo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
            helpMenu.add(item);

            bar.add(helpMenu);

            return bar;

        }

        private JPanel createTablePanel() {
            JPanel panel = new JPanel(new BorderLayout());
            final JTable table = new JTable();
            table.setRowHeight(20);
            table.setName("Table");
            final SampleAppDialogTableModel model = new SampleAppDialogTableModel();
            model.addRow();
            table.setModel(model);
            table.setColumnSelectionAllowed(true);
            table.setRowSelectionAllowed(true);
            table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

            JPanel optionsPanel = new JPanel(new BorderLayout());
            ButtonGroup selectionModeGroup = new ButtonGroup();

            JPanel smPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            final JRadioButton smSingle = new JRadioButton("Single", false);
            final JRadioButton smSingleInterval = new JRadioButton("Single Interval", false);
            final JRadioButton smMultipleInterval = new JRadioButton("Multiple Interval", true);
            smPanel.add(smSingle);
            smPanel.add(smSingleInterval);
            smPanel.add(smMultipleInterval);
            optionsPanel.add(smPanel, BorderLayout.NORTH);

            selectionModeGroup.add(smSingle);
            selectionModeGroup.add(smSingleInterval);
            selectionModeGroup.add(smMultipleInterval);
            ActionListener smAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == smSingle)
                        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    else if (e.getSource() == smSingleInterval)
                        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                    else
                        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                }
            };
            smSingle.addActionListener(smAction);
            smSingleInterval.addActionListener(smAction);
            smMultipleInterval.addActionListener(smAction);

            JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            final JCheckBox selColumnSelection = new JCheckBox("Column Selection", true);
            final JCheckBox selRowSelection = new JCheckBox("Row Selection", true);
            selectionPanel.add(selRowSelection);
            selectionPanel.add(selColumnSelection);
            optionsPanel.add(selectionPanel, BorderLayout.SOUTH);
            ActionListener selectionAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == selColumnSelection)
                        table.setColumnSelectionAllowed(selColumnSelection.isSelected());
                    else if (e.getSource() == selRowSelection)
                        table.setRowSelectionAllowed(selRowSelection.isSelected());
                }
            };
            selColumnSelection.addActionListener(selectionAction);
            selRowSelection.addActionListener(selectionAction);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            final JButton populateButton = new JButton("Populate");
            populateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    populateButton.setEnabled(false);
                    new Thread() {
                        public void run() {
                            updateTableWithNotReallyRandom(model);
                        }
                    }.start();
                }

                private void updateTableWithNotReallyRandom(final SampleAppDialogTableModel model) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e1) {
                        }
                        model.addRow(getRandomName(i), (int) (Math.random() * 100), getRandomBoolean());
                    }
                    try {
                        SwingUtilities.invokeAndWait(new Runnable() {
                            public void run() {
                                populateButton.setEnabled(true);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

                private String getRandomName(int i2) {
                    return "RandomNameDoesNotHelp" + i2;
                }

                private boolean getRandomBoolean() {
                    return Math.random() < 0.5;
                }
            });
            populateButton.setMnemonic(KeyEvent.VK_N);
            populateButton.setName("noActionButton");
            JButton addButton = new JButton("Add");
            addButton.setName("addButton");
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    model.addRow();
                }
            });

            final JButton removeButton = new JButton("Remove");
            removeButton.setEnabled(false);
            removeButton.setName("removeButton");
            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1)
                        model.removeRow(selectedRow);
                }
            });

            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting())
                        return;
                    int row = table.getSelectedRow();
                    removeButton.setEnabled(row != -1);
                }
            });
            buttonPanel.add(populateButton);
            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            panel.add(new JScrollPane(table));
            panel.add(buttonPanel, BorderLayout.SOUTH);
            panel.add(optionsPanel, BorderLayout.NORTH);
            return panel;
        }

        private JPanel createSimpleWidgetPanel() {

            JPanel topPanel = new JPanel(new BorderLayout());

            simpleWidgetsPanel = new JPanel(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(3, 3, 3, 3);

            JLabel label = new JExtendedLabel("First Name: ");
            label.setDisplayedMnemonic('i');
            addToPanel(0, 0, 0.0f, 1, 1, GridBagConstraints.NONE, simpleWidgetsPanel, label);
            JTextField textField = new JTextField(15);
            // textField.setName("firstname");
            label.setLabelFor(textField);
            textField.addKeyListener(new KeyListener() {
                public void keyPressed(KeyEvent e) {
                }

                public void keyReleased(KeyEvent e) {
                }

                public void keyTyped(KeyEvent e) {
                }
            });
            addToPanel(1, 0, 1.0f, 2, 1, GridBagConstraints.NONE, simpleWidgetsPanel, textField);

            label = new JLabel("Password: ");
            label.setBorder(BorderFactory.createLineBorder(Color.CYAN));
            label.setDisplayedMnemonic('P');
            addToPanel(0, 1, 0.0f, 1, 1, GridBagConstraints.NONE, simpleWidgetsPanel, label);
            JPasswordField passwordField = new JPasswordField(15);
            // passwordField.setName("Password");
            label.setLabelFor(passwordField);
            addToPanel(1, 1, 1.0f, 2, 1, GridBagConstraints.NONE, simpleWidgetsPanel, passwordField);

            label = new JLabel("Email: ");
            label.setDisplayedMnemonic('E');
            addToPanel(0, 2, 0.0f, 1, 1, GridBagConstraints.NONE, simpleWidgetsPanel, label);
            textField = new JTextField(20);
            textField.setName("email");
            label.setLabelFor(textField);
            addToPanel(1, 2, 1.0f, 2, 1, GridBagConstraints.HORIZONTAL, simpleWidgetsPanel, textField);

            label = new JLabel("Address: ");
            addToPanel(0, 3, 0.0f, 1, 1, GridBagConstraints.NONE, simpleWidgetsPanel, label);
            JTextArea area = new JTextArea();
            area.setName("address");
            JScrollPane pane = new JScrollPane(area);
            pane.setPreferredSize(new Dimension(150, 80));
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            label.setLabelFor(area);
            addToPanel(1, 3, 1.0f, 2, 1, GridBagConstraints.BOTH, simpleWidgetsPanel, pane);

            label = new JLabel("Country: ");
            addToPanel(0, 4, 0.0f, 1, 1, GridBagConstraints.NONE, simpleWidgetsPanel, label);
            final JComboBox countryBox = new JComboBox();
            countryBox.setEditable(false);
            countryBox.setName("country");
            label.setLabelFor(countryBox);
            addToPanel(1, 4, 1.0f, 2, 1, GridBagConstraints.NONE, simpleWidgetsPanel, countryBox);

            label = new JLabel("Gender: ");
            addToPanel(0, 5, 0.0f, 1, 1, GridBagConstraints.NONE, simpleWidgetsPanel, label);

            JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JRadioButton maleButton = new JRadioButton("Male");
            JRadioButton femaleButton = new JRadioButton("Female");
            maleButton.setSelected(true);
            ButtonGroup grp = new ButtonGroup();
            grp.add(maleButton);
            grp.add(femaleButton);
            radioPanel.add(maleButton);
            radioPanel.add(femaleButton);

            addToPanel(1, 5, 1.0f, 2, 1, GridBagConstraints.NONE, simpleWidgetsPanel, radioPanel);

            label = new JLabel("Annual Income ($)");
            addToPanel(0, 6, 0.0f, 1, 1, GridBagConstraints.NONE, simpleWidgetsPanel, label);

            final JList list;
            if (System.getProperty("checkboxlist", "false").equals("false"))
                list = new JList();
            else
                list = new JCheckBoxList();
            Timer timer = new Timer("list-populate");
            timer.schedule(new TimerTask() {
                public void run() {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            DefaultListModel model = new DefaultListModel();
                            String[] income = { "<10K", "Between 10K and 20K", "Between 20K and 30K", "Between 30K and 50K",
                                    "Between 50K and 70K", "Between 70K and 90K", "Between 90K and 100K", "Between 100K and 120K",
                                    "Between 120K and 140K", "Between 140K and 160K" };
                            for (String string : income) {
                                model.addElement(string);
                            }
                            list.setModel(model);
                            String[] country = { "India", "Mexico", "USA", "Pakistan", "Srilanka", "France", "England" };
                            countryBox.setModel(new DefaultComboBoxModel(country));
                        }
                    });
                }
            }, 5000);
            list.setDragEnabled(true);
            label.setLabelFor(list);
            addToPanel(1, 6, 1.0f, 2, 1, GridBagConstraints.HORIZONTAL, simpleWidgetsPanel, new JScrollPane(list));
            label = new JLabel("Languages: ");
            addToPanel(0, 7, 0.0f, 1, 1, GridBagConstraints.NONE, simpleWidgetsPanel, label);

            JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            String[] languages = { "English", "Hindi", "French", "German", "Urdu" };
            for (int i = 0; i < languages.length; i++) {
                JCheckBox box = new JCheckBox(languages[i]);
                checkBoxPanel.add(box);
            }

            addToPanel(1, 7, 1.0f, 2, 1, GridBagConstraints.HORIZONTAL, simpleWidgetsPanel, checkBoxPanel);

            final JProgressBar progressBar = new JProgressBar(0, 100);

            final JSlider slider = new JSlider(1, 100, 10);

            label = new JLabel("Password Expiry: ");
            addToPanel(0, 8, 0.0f, 1, 1, GridBagConstraints.NONE, simpleWidgetsPanel, label);
            JPanel spinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            SpinnerNumberModel model = new SpinnerNumberModel(10, 1, 100, 1);
            progressBar.setValue(10);
            final JSpinner spinner = new JSpinner(model);
            spinner.setName("passwordexpiry");
            spinnerPanel.add(spinner);
            spinnerPanel.add(new JLabel(" days"));
            addToPanel(1, 8, 1.0f, 2, 1, GridBagConstraints.HORIZONTAL, simpleWidgetsPanel, spinnerPanel);

            addToPanel(1, 9, 1.0f, 2, 1, GridBagConstraints.HORIZONTAL, simpleWidgetsPanel, progressBar);

            addToPanel(1, 10, 1.0f, 2, 1, GridBagConstraints.HORIZONTAL, simpleWidgetsPanel, slider);

            spinner.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    progressBar.setValue(((Number) spinner.getValue()).intValue());
                    slider.setValue(((Number) spinner.getValue()).intValue());
                }
            });
            slider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    spinner.setValue(new Integer(slider.getValue()));
                }
            });
            topPanel.add(simpleWidgetsPanel, BorderLayout.NORTH);
            return topPanel;
        }

        private JPanel createTreePanel() {
            JPanel panel = new JPanel(new BorderLayout());
            DefaultMutableTreeNode root = createRoot();

            final JTree tree = new JTree(root);
            final JLabel status = new JLabel("Root");
            tree.setEditable(true);
            tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                    TreePath path = e.getPath();
                    Object[] data = path.getPath();
                    String statusText = "";
                    for (int i = 0; i < data.length - 1; i++) {
                        statusText += data[i].toString() + "/";
                    }
                    statusText += data[data.length - 1].toString();

                    status.setText(statusText);
                }

            });
            panel.add(new JScrollPane(tree));

            MouseListener ml = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    int selRow = tree.getRowForLocation(e.getX(), e.getY());
                    if (selRow == -1)
                        return;
                    TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());

                    if (((TreeNode) selPath.getLastPathComponent()).getChildCount() == 0 && selRow != -1) {
                        if (e.getClickCount() == 2) {
                            tree.startEditingAtPath(selPath);
                        }
                    }
                }
            };
            tree.addMouseListener(ml);
            tree.setRootVisible(true);

            JPanel statusPanel = new JPanel(new BorderLayout());
            status.setName("status");
            JPanel options = new JPanel(new FlowLayout(FlowLayout.LEFT));
            final JCheckBox cb = new JCheckBox("Toggle with doubleClick", true);
            cb.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    tree.setToggleClickCount(cb.isSelected() ? 2 : 0);
                }
            });
            options.add(cb);
            final JCheckBox cb2 = new JCheckBox("Root Visible", true);
            cb2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    tree.setRootVisible(cb2.isSelected());
                }
            });
            options.add(cb2);

            statusPanel.add(options, BorderLayout.NORTH);
            statusPanel.add(status);

            panel.add(statusPanel, BorderLayout.SOUTH);
            return panel;
        }

        private DefaultMutableTreeNode createRoot() {
            final DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override public void run() {
                    addChild(root, "Colors", new String[] { "Red", "Blue", "Green", "Yello", "Magneta", "Cyan"});

                    DefaultMutableTreeNode child = new DefaultMutableTreeNode("Sports");
                    DefaultMutableTreeNode individual = new DefaultMutableTreeNode("Individual Sports");
                    individual.add(new DefaultMutableTreeNode("Tennis"));
                    individual.add(new DefaultMutableTreeNode("Badminton"));

                    DefaultMutableTreeNode team = new DefaultMutableTreeNode("Team Sports");
                    team.add(new DefaultMutableTreeNode("Cricket"));
                    team.add(new DefaultMutableTreeNode("Hockey"));
                    team.add(new DefaultMutableTreeNode("Football"));
                    team.add(new DefaultMutableTreeNode("Throwball"));
                    team.add(new DefaultMutableTreeNode("Volley Ball"));
                    child.add(individual);
                    child.add(team);
                    root.add(child);

                    addChild(root, "Special Characters", "Comma, In node name", "Forward/Slash in node name");
                }

                private void addChild(final DefaultMutableTreeNode root, String nodeName, String... values) {
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(nodeName);
                    for (String color : values) {
                        child.add(new DefaultMutableTreeNode(color));
                    }
                    root.add(child);
                }
            }, 1000);
            return root;
        }

        private JPanel createChooserPanel() {

            JPanel topPanel = new JPanel(new BorderLayout());

            JPanel panel = new JPanel(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(3, 3, 3, 3);

            JLabel label = new JLabel("File(s) Selected: ");
            label.setDisplayedMnemonic('i');
            addToPanel(0, 0, 0.0f, 1, 1, GridBagConstraints.NONE, panel, label);

            final JTextField selectedFiles = new JTextField(15);
            label.setLabelFor(selectedFiles);
            selectedFiles.setEditable(false);
            addToPanel(1, 0, 1.0f, 2, 1, GridBagConstraints.HORIZONTAL, panel, selectedFiles);

            JButton browse = new JButton("Browse");
            browse.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    int ret = chooser.showSaveDialog(SampleAppDialog.this);
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        selectedFiles.setText(chooser.getSelectedFile().toString());
                    } else {
                        selectedFiles.setText("<None>");
                    }
                }
            });
            addToPanel(3, 0, 0.0f, 1, 1, GridBagConstraints.NONE, panel, browse);

            label = new JLabel("Color Selected: ");
            label.setDisplayedMnemonic('C');
            addToPanel(0, 1, 0.0f, 1, 1, GridBagConstraints.NONE, panel, label);

            final JTextField selectedColor = new JTextField(15);
            label.setLabelFor(selectedColor);
            selectedColor.setEditable(false);
            addToPanel(1, 1, 1.0f, 2, 1, GridBagConstraints.HORIZONTAL, panel, selectedColor);

            JButton color = new JButton("Choose");
            color.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Color color = JColorChooser.showDialog(SampleAppDialog.this, "Chose a Color", Color.BLACK);
                    if (color != null) {
                        selectedColor.setText(color.toString());
                    } else {
                        selectedColor.setText("<None>");
                    }
                }
            });
            addToPanel(3, 1, 0.0f, 1, 1, GridBagConstraints.NONE, panel, color);

            topPanel.add(panel, BorderLayout.NORTH);
            return topPanel;
        }

        private void addToPanel(int x, int y, float weightx, int gridWidth, int gridHeight, int fill, JPanel parent, JComponent comp) {
            gbc.gridx = x;
            gbc.gridy = y;
            gbc.weightx = weightx;
            gbc.gridwidth = gridWidth;
            gbc.gridheight = gridHeight;
            gbc.fill = fill;
            parent.add(comp, gbc);
        }

    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            System.out.println("arg[" + i + "] = " + args[i]);
        }
        System.out.println("Working Directory: " + System.getProperty("user.dir"));
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                Enumeration<Object> keys = System.getProperties().keys();
                while (keys.hasMoreElements()) {
                    String key = (String) keys.nextElement();
                    if (key.startsWith("test."))
                        System.out.println(key + " = " + System.getProperty(key));
                }
                System.out.println("Environment");
                Set<Entry<String, String>> env = System.getenv().entrySet();
                for (Entry<String, String> e : env) {
                    if (e.getKey().startsWith("test."))
                        System.out.println(e.getKey() + " = " + e.getValue());
                }
                SampleAppDialog dialog = new SampleAppDialog();
                dialog.setVisible(true);
            }
        });
    }

    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    System.out.println("Message from SampleAppDialog using SwingUtilities.invokeAndWait");
                    System.out.println("The property value of test.SampleAppDialog = " + getParameter("test.SampleAppDialog"));
                    SampleAppDialog dialog = new SampleAppDialog();
                    setContentPane(dialog.getContentPane());
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
