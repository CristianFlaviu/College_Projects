package Gui;

import Utils.ConvertReqStateToString;
import entity.Document;
import entity.Request;
import guiController.AdminController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminSortFilterRequest  {

    public JFrame frame;
    private JPanel mainPane;
    private JPanel topPane;
    private JPanel tablePane;
    private JPanel bottomPane;

    private JLabel HouseTitle;
    private JLabel requesTitledLabel;
    private JLabel documentType;

    private JTextField requestTileField;


    private JButton SortBtn;
    private JButton sortByTitle;
    private JButton sortByDocumetnTypeBtn;

    private JTable table;
    private DefaultTableModel defaultTableModel;

    private JComboBox documentcomboBox;

    private JScrollPane scroll;
    private AdminController adminController;
    private Set<Document> documents;
    private Set<Request> requests;

    private Map<String,Document> documentMap;

    public AdminSortFilterRequest(Set<Request> requests, Set<Document> documents)
    {
        this.documents=documents;
        this.requests=requests;
        this.adminController=new AdminController();

        initializeJComboBox();
        initializeTabel();
        createGui();
        addActionListeners();
    }
    public void createGui() {
        frame = new JFrame("Request");

        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        table.setPreferredScrollableViewportSize(new Dimension(620, 250));
        table.setFillsViewportHeight(true);

        HouseTitle = new JLabel("Request Management");
        requesTitledLabel = new JLabel("Filter Date (Expected format MM-YYYY)");

        SortBtn = new JButton("SortByDate");
        SortBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        requestTileField = new JTextField(10);

        topPane = new JPanel();
        topPane.setLayout(new BorderLayout());

        topPane.add(HouseTitle, BorderLayout.WEST);
        topPane.add(SortBtn, BorderLayout.EAST);

        tablePane = new JPanel();
        tablePane.add(scroll);

        bottomPane = new JPanel();
        bottomPane.setLayout(new GridLayout(3,3));

        bottomPane.add(requesTitledLabel);
        bottomPane.add(requestTileField);

        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));

        frame.getContentPane().add(topPane, BorderLayout.NORTH);
        frame.getContentPane().add(tablePane, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPane, BorderLayout.SOUTH);

        documentType = new JLabel("DocumentType");
        bottomPane.add(documentType);


        bottomPane.add(documentcomboBox);

        sortByTitle = new JButton("FilterByDate");
        bottomPane.add(sortByTitle);
        sortByDocumetnTypeBtn = new JButton("SortByDocType");
        sortByDocumetnTypeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        bottomPane.add(sortByDocumetnTypeBtn);

        frame.pack();
        frame.setVisible(true);


        defaultTableModel = (DefaultTableModel) table.getModel();


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                int i=table.getSelectedRow();
                if(i>=0) {
                    requestTileField.setText((String) defaultTableModel.getValueAt(i, 1));
                }
            }
        });

    }
    public void initializeTabel()
    {

        int row=0;
        String[][] data = new String[requests.size()][6];
        for(Request a:requests)
        {
            data[row][0]=a.getId();
            data[row][1]=a.getTitle();

            data[row][2]= new SimpleDateFormat("MM-dd-yyyy").format(a.getDate());
            data[row][3]=a.getUser().getFirstName();
            data[row][4]=a.getDocument().getNume();
            data[row][5]= ConvertReqStateToString.convert(a.getRequestState());
            row++;

        }
        String[] columnNames = { "RequestID","Title","Date","User","Document","RequestState" };

        TableModel tableModel = new DefaultTableModel(data,columnNames);


        table = new JTable(tableModel){

            public boolean isCellEditable(int data, int columns)
            {
                return false;
            }
        };
    }

    public void initializeJComboBox()
    {    documentMap=new HashMap<>();

        for(Document document:documents)
        {
            documentMap.put(document.getNume(),document);

        }

        String[] array= documentMap.keySet().toArray(new String[0]);
        documentcomboBox =new JComboBox(array);
    }

    public void addActionListeners()
    {
   SortBtn.addActionListener(e->adminController.sortByDateBtn(table));
   sortByDocumetnTypeBtn.addActionListener(e->adminController.sortByDocumettype(table));
   sortByTitle.addActionListener(e->adminController.filterByDateBtn(table,requestTileField.getText()));
    }





}

