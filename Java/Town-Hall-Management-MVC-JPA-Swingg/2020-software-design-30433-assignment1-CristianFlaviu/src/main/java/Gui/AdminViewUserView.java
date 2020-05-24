package Gui;





import entity.Request;
import entity.User;
import guiController.AdminController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLOutput;
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

public class AdminViewUserView  {

    public JFrame frame;
    private JPanel mainPane;
    private JPanel topPane;
    private JPanel tablePane;
    private JPanel bottomPane;

    private JLabel HouseTitle;

    private JTable table;
    private DefaultTableModel model;

    private JScrollPane scroll;
    private Set<User> usersData;

    private AdminController adminController=new AdminController();


    public AdminViewUserView(Set<User> users)
    {
        this.usersData=users;

        initializeTabel();
        createAndShowGui();
    }
    public void initializeTabel()
    {

        int row=0;
        String[][] data = new String[usersData.size()][4];
        for(User a:usersData)
        {
            data[row][0]=a.getId();
            data[row][1]=a.getFirstName();
            data[row][2]=a.getLastName();
            data[row][3]=a.getEmail();

            row++;

        }
        String[] columnNames = { "UserID", "FirstName","LastName","email" };

        TableModel tableModel = new DefaultTableModel(data,columnNames);


        table = new JTable(tableModel){

            public boolean isCellEditable(int data, int columns)
            {
                return false;
            }
        };
    }
    public void createAndShowGui() {
        frame = new JFrame("Admin");

        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        table.setPreferredScrollableViewportSize(new Dimension(620, 250));
        table.setFillsViewportHeight(true);

        HouseTitle = new JLabel("Request Management");

        topPane = new JPanel();
        topPane.setLayout(new BorderLayout());

        topPane.add(HouseTitle, BorderLayout.WEST);

        tablePane = new JPanel();
        tablePane.add(scroll);

        bottomPane = new JPanel();
        bottomPane.setLayout(new GridLayout(3,3));

        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));

        frame.getContentPane().add(topPane, BorderLayout.NORTH);
        frame.getContentPane().add(tablePane, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPane, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);


        model= (DefaultTableModel) table.getModel();




    }



}


