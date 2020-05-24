package Gui;

import guiController.UserMainViewController;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.JButton;

public class UserMainView {


    private JFrame frame;
    private JButton houseOperationBtn;

    private JButton requesOpertaionBtn;

    private  JLabel UsernameJlabel;
    private String userName;

    private UserMainViewController userMainViewController =new UserMainViewController();

    public UserMainView(String userName) {
        this.userName=userName;
        initialize();
        buttonAddActionListener();

    }

    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Welcome Back");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblNewLabel.setBounds(32, 28, 199, 58);
        frame.getContentPane().add(lblNewLabel);

        houseOperationBtn = new JButton("House Operation");
        houseOperationBtn.setBounds(32, 168, 173, 25);
        frame.getContentPane().add(houseOperationBtn);



        requesOpertaionBtn = new JButton("Request Operation");
        requesOpertaionBtn.setBounds(241, 168, 170, 25);
        frame.getContentPane().add(requesOpertaionBtn);


        JLabel Houselabel = new JLabel("House");
        Houselabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
        Houselabel.setBounds(62, 118, 113, 30);
        frame.getContentPane().add(Houselabel);

        JLabel lblNewLabel_1 = new JLabel("Requests");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblNewLabel_1.setBounds(241, 119, 113, 28);
        frame.getContentPane().add(lblNewLabel_1);

        UsernameJlabel =new JLabel(userName);
        UsernameJlabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        UsernameJlabel.setBounds(243, 43, 300, 30);
        frame.getContentPane().add(UsernameJlabel);
        UsernameJlabel.setForeground(Color.RED);


        frame.setVisible(true);

    }

    public void buttonAddActionListener()
    {

        houseOperationBtn.addActionListener(e-> userMainViewController.houseOperation(userName));
        requesOpertaionBtn.addActionListener(e->userMainViewController.requestOperation(userName));
    }



}
