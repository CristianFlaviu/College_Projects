package Gui;



import guiController.AdminController;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainView {

    private JFrame frame;
    private JButton viewUsersButton;
    private JButton documentsButton;
    private JButton viewRequestButton;
    private JButton sortFilterButton;


    private AdminController adminController=new AdminController();


    public AdminMainView() {

        initializeView();
        buttonsAddActionListeners();
    }


    private void initializeView() {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
        frame.setBounds(100, 100, 474, 396);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel TitleLabel = new JLabel("Admin");
        TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TitleLabel.setBounds(142, 33, 161, 42);
        frame.getContentPane().add(TitleLabel);

        viewUsersButton = new JButton("View Users");
        viewUsersButton.setBounds(129, 142, 161, 25);
        frame.getContentPane().add(viewUsersButton);

        documentsButton = new JButton("Add/Del Documents");

        documentsButton.setBounds(129, 182, 161, 25);
        frame.getContentPane().add(documentsButton);

        viewRequestButton = new JButton("View Request");
        viewRequestButton.setBounds(129, 230, 161, 25);

        sortFilterButton=new JButton("SortFilterRequest");
        sortFilterButton.setBounds(129, 270, 161, 25);

        frame.getContentPane().add(viewRequestButton);
        frame.getContentPane().add(sortFilterButton);
        frame.setVisible(true);
    }

    public void buttonsAddActionListeners(){
        viewUsersButton.addActionListener(e->adminController.mainViewViewUsers());
        documentsButton.addActionListener(e->adminController.mainViewDocumentsBtn());
        viewRequestButton.addActionListener(e->adminController.mainViewRequestBtn());
        sortFilterButton.addActionListener(e->adminController.mainViewSortFilterBtn());
    }

}

