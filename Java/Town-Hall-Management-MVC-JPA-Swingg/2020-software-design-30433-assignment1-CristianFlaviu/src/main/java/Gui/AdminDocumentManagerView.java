package Gui;


import entity.Document;
import guiController.AdminController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminDocumentManagerView {

    public JFrame frame;
    private JPanel mainPane;
    private JPanel topPane;
    private JPanel tablePane;
    private JPanel bottomPane;

    private JLabel HouseTitle;
    private JLabel DocumentIDLabel;
    private JLabel documentNameLabel;

    private JTextField documentIdField;
    private JTextField documentNameField;

    private JButton deleteDocBtn;
    private JButton addButton;
    private JButton updadeButton;

    private JTable table;
    private DefaultTableModel model;

    private JScrollPane scroll;
    private JLabel lblNewLabel;
    private JTextField documentTypeField;
    private AdminController adminController = new AdminController();
    private Set<Document> documents;

    public AdminDocumentManagerView(Set<Document> documents) {
        this.documents=documents;
        initializeTabel();
        createAndShowGui();
        initializeButtons();
    }

    public void initializeTabel() {

        int row = 0;
        String[][] data = new String[documents.size()][3];
        for (Document a : documents) {
            data[row][0] = a.getId();
            data[row][1] = a.getNume();
            data[row][2] = a.getType();
            row++;

        }
        String[] columnNames = {"DocumentId", "Name", "Type"};

        TableModel tableModel = new DefaultTableModel(data, columnNames);


        table = new JTable(tableModel) {

            public boolean isCellEditable(int data, int columns) {
                return false;
            }
        };
    }

    public void createAndShowGui() {
        frame = new JFrame("AdminDocuments");


        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        table.setPreferredScrollableViewportSize(new Dimension(420, 250));
        table.setFillsViewportHeight(true);

        HouseTitle = new JLabel("House Management");
        DocumentIDLabel = new JLabel("DocumentID");

        deleteDocBtn = new JButton("Delete");

        documentIdField = new JTextField(10);

        topPane = new JPanel();
        topPane.setLayout(new BorderLayout());

        topPane.add(HouseTitle, BorderLayout.WEST);
        topPane.add(deleteDocBtn, BorderLayout.EAST);

        tablePane = new JPanel();
        tablePane.add(scroll);

        bottomPane = new JPanel();
        bottomPane.setLayout(new GridLayout(4, 3));

        bottomPane.add(DocumentIDLabel);
        bottomPane.add(documentIdField);

        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));

        frame.getContentPane().add(topPane, BorderLayout.NORTH);
        frame.getContentPane().add(tablePane, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPane, BorderLayout.SOUTH);

        documentNameLabel = new JLabel("Name");
        bottomPane.add(documentNameLabel);

        //HouseLocationFiled = new JPasswordField(10);
        documentNameField = new JTextField();
        bottomPane.add(documentNameField);

        lblNewLabel = new JLabel("Type");
        bottomPane.add(lblNewLabel);

        documentTypeField = new JTextField();
        bottomPane.add(documentTypeField);
        documentTypeField.setColumns(10);

        addButton = new JButton("Add");
        bottomPane.add(addButton);
        updadeButton = new JButton("Update");

        bottomPane.add(updadeButton);

        frame.pack();
        frame.setVisible(true);


        model = (DefaultTableModel) table.getModel();


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if(row>=0) {

                    documentIdField.setText((String) model.getValueAt(row, 0));
                    documentNameField.setText((String) model.getValueAt(row, 1));
                }
                else {
                    System.out.println("OUT OF TABLE " + row);
                }
            }
        });


    }

  public void initializeButtons()
  {
      addButton.addActionListener(e->adminController.addNewDocumentBtn(model,new Document(documentIdField.getText(),documentNameField.getText(),documentTypeField.getText())));
      deleteDocBtn.addActionListener(e->adminController.deleteDocummentBtn(table));
      updadeButton.addActionListener(e->adminController.updateDocumentBtn(table,new Document(documentIdField.getText(),documentNameField.getText(),documentTypeField.getText())));

  }



}

