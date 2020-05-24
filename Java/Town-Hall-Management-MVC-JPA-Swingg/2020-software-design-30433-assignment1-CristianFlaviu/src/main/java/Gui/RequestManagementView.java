package Gui;

import Utils.ConvertReqStateToString;
import entity.Document;
import entity.House;
import entity.Request;
import guiController.RequestManagementCotroller;

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

public class RequestManagementView {

    public JFrame frame;
    private JPanel mainPane;
    private JPanel topPane;
    private JPanel tablePane;
    private JPanel bottomPane;

    private JLabel HouseTitle;
    private JLabel requesTitledLabel;
    private JLabel documentType;

    private JTextField requestTileField;


    private JButton deleteBtn;
    private JButton addButton;
    private JButton updateBtutton;

    private JTable table;
    private DefaultTableModel defaultTableModel;

    private JComboBox documentcomboBox;

    private JScrollPane scroll;
    private RequestManagementCotroller requestManagementCotroller = new RequestManagementCotroller();

    private String username;
    private Map<String, Document> documentMap;
    private Map<String, House> houseMap;
    private Set<Document> documents;
    private Set<House> houses;
    private JComboBox houseIdcomboBox;
    private JLabel HouseIdLabel = new JLabel("HouseId");

    public RequestManagementView(String username, Set<Document> setDoc, Set<House> houses) {
        this.username = username;
        this.documents = setDoc;
        this.houses = houses;

        initializeTabel();

        initializeJComboBox();
        createGui();
        addActionListeners();
    }

    public void createGui() {
        frame = new JFrame("Request");

        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        table.setPreferredScrollableViewportSize(new Dimension(720, 250));
        table.setFillsViewportHeight(true);

        HouseTitle = new JLabel("Request Management");
        requesTitledLabel = new JLabel("RequestTitle");

        deleteBtn = new JButton("Delete");

        requestTileField = new JTextField(10);

        topPane = new JPanel();
        topPane.setLayout(new BorderLayout());

        topPane.add(HouseTitle, BorderLayout.WEST);
        topPane.add(deleteBtn, BorderLayout.EAST);

        tablePane = new JPanel();
        tablePane.add(scroll);

        bottomPane = new JPanel();
        bottomPane.setLayout(new GridLayout(4, 3));

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
        bottomPane.add(HouseIdLabel);


        bottomPane.add(houseIdcomboBox);

        addButton = new JButton("Add");
        bottomPane.add(addButton);
        updateBtutton = new JButton("Update");
        bottomPane.add(updateBtutton);

        frame.pack();
        frame.setVisible(true);


        defaultTableModel = (DefaultTableModel) table.getModel();


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                if (i >= 0) {
                    requestTileField.setText((String) defaultTableModel.getValueAt(i, 1));

                }
            }
        });

    }

    public void initializeTabel() {
        Set<Request> listRequest = requestManagementCotroller.getAllRequestByUserId(username);
        int row = 0;
        String[][] data = new String[listRequest.size()][7];
        for (Request a : listRequest) {
            data[row][0] = a.getId();
            data[row][1] = a.getTitle();

            data[row][2] = new SimpleDateFormat("MM-dd-yyyy").format(a.getDate());
            data[row][3] = a.getUser().getFirstName();
            data[row][4] = a.getDocument().getNume();
            data[row][5] = ConvertReqStateToString.convert(a.getRequestState());
            data[row][6] = a.getHouse().getAdress();

            row++;

        }
        String[] columnNames = {"RequestID", "Title", "Date", "User", "Document", "RequestState", "RequestIdHouse"};

        TableModel tableModel = new DefaultTableModel(data, columnNames);


        table = new JTable(tableModel) {

            public boolean isCellEditable(int data, int columns) {
                return false;
            }
        };
    }

    public void initializeJComboBox() {


        documentMap = new HashMap<>();


        for (Document document : documents) {
            documentMap.put(document.getNume(), document);

        }

        String[] array = documentMap.keySet().toArray(new String[0]);
        documentcomboBox = new JComboBox(array);


        houseMap = new HashMap<>();
        for (House house : houses) {
            houseMap.put(house.getAdress(), house);

        }

        String[] array2 = houseMap.keySet().toArray(new String[0]);
        documentcomboBox = new JComboBox(array);

        houseIdcomboBox = new JComboBox(array2);
    }

    public void addActionListeners() {
        addButton.addActionListener(e -> requestManagementCotroller.addBtnRequest(defaultTableModel, requestTileField.getText(), username, documentMap.get((String) documentcomboBox.getSelectedItem()), houseMap.get((String) houseIdcomboBox.getSelectedItem())));
        deleteBtn.addActionListener(e -> requestManagementCotroller.deleteBtnRequest(table));
        updateBtutton.addActionListener(e -> requestManagementCotroller.updateBtn(table, requestTileField.getText(), documentMap.get((String) documentcomboBox.getSelectedItem()), houseMap.get((String) houseIdcomboBox.getSelectedItem())));
    }


}

