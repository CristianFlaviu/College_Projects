package Gui;

import entity.House;
import entity.Request;
import guiController.HouseManagementController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class HouseManagementView {

    public JFrame frame;
    private JPanel mainPane;
    private JPanel topPane;
    private JPanel tablePane;
    private JPanel bottomPane;

    private JLabel HouseTitle;
    private JLabel userNameLabel;
    private JLabel homeWorldLabel;

    private JTextField HouseIdFiled;
    private JTextField HouseLocationFiled;


    private JButton addButton;
    private JButton deleteButton;

    private JTable table;
    private DefaultTableModel model;

    private JScrollPane scroll;
    private HouseManagementController houseManagementController = new HouseManagementController();

    private String username;

    private Set<House> houses;

    public HouseManagementView(Set<House> setHouse, String userName) {
        this.houses = setHouse;
        this.username = userName;
        initializeTable();
        initializeView();

        buttonsAddActionListener();
    }

    public void initializeView() {
        frame = new JFrame("House");


        HouseTitle = new JLabel("House Management");
        userNameLabel = new JLabel("HouseID");


        HouseIdFiled = new JTextField(10);

        topPane = new JPanel();
        topPane.setLayout(new BorderLayout());

        topPane.add(HouseTitle, BorderLayout.WEST);


        tablePane = new JPanel();
        tablePane.add(scroll);

        bottomPane = new JPanel();
        bottomPane.setLayout(new GridLayout(3, 3));

        bottomPane.add(userNameLabel);
        bottomPane.add(HouseIdFiled);

        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));

        frame.getContentPane().add(topPane, BorderLayout.NORTH);
        frame.getContentPane().add(tablePane, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPane, BorderLayout.SOUTH);

        homeWorldLabel = new JLabel("HouseLocatiom");
        bottomPane.add(homeWorldLabel);

        //HouseLocationFiled = new JPasswordField(10);
        HouseLocationFiled = new JTextField();
        bottomPane.add(HouseLocationFiled);

        addButton = new JButton("Add");
        bottomPane.add(addButton);
        deleteButton = new JButton("Del");
        bottomPane.add(deleteButton);

        frame.pack();
        frame.setVisible(true);


        model = (DefaultTableModel) table.getModel();


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                int i = table.getSelectedRow();
                if (i >= 0) {
                    HouseIdFiled.setText((String) model.getValueAt(i, 0));
                    HouseLocationFiled.setText((String) model.getValueAt(i, 1));
                }
            }
        });

    }

    private void initializeTable() {

        int row = 0;
        String[][] data = new String[houses.size()][2];
        for (House a : houses) {
            data[row][0] = a.getId();
            data[row][1] = a.getAdress();
            row++;
        }


        String[] columnNames = {"HouseID", "HouseLocation"};

        TableModel tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel) {

            public boolean isCellEditable(int data, int columns) {
                return false;
            }
        };

        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        table.setPreferredScrollableViewportSize(new Dimension(420, 250));
        table.setFillsViewportHeight(true);
    }

    private void buttonsAddActionListener() {
        addButton.addActionListener(e -> houseManagementController.AddNewHouse(this, table, HouseIdFiled.getText(), HouseLocationFiled.getText()));
        deleteButton.addActionListener(e -> houseManagementController.removeHouse(table, (String) model.getValueAt(table.getSelectedRow(), 0)));
    }

    public String getUsername() {
        return username;
    }

}

