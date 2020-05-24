package Gui;



import entity.User;
import guiController.LoginController;

import javax.swing.*;
import java.awt.Font;

public class NewUserView extends JFrame{

    private JFrame frame;
    private JTextField usernameField;
    private JTextField firstNametextField_1;
    private JTextField lastNametextField_2;
    private JTextField emailTextField;
    private JTextField passWordtextField;
    private JLabel lblPassword;

    private JButton NewUserButton;

    private LoginController loginController =new LoginController();

    public NewUserView() {

        initializeView();
        buttonsAddActionListener();

    }


    private void initializeView() {
        frame = new JFrame();
        frame.setBounds(100, 100, 549, 444);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel TitleLabel = new JLabel("New User");
        TitleLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
        TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TitleLabel.setBounds(29, 23, 144, 35);
        frame.getContentPane().add(TitleLabel);

        usernameField= new JTextField();
        usernameField.setBounds(170, 92, 116, 22);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JLabel UserNameLabel = new JLabel("Username");
        UserNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        UserNameLabel.setBounds(70, 95, 88, 16);
        frame.getContentPane().add(UserNameLabel);

        firstNametextField_1 = new JTextField();
        firstNametextField_1.setColumns(10);
        firstNametextField_1.setBounds(170, 141, 116, 22);
        frame.getContentPane().add(firstNametextField_1);

        lastNametextField_2 = new JTextField();
        lastNametextField_2.setColumns(10);
        lastNametextField_2.setBounds(170, 195, 116, 22);
        frame.getContentPane().add(lastNametextField_2);

        emailTextField = new JTextField();
        emailTextField.setColumns(10);
        emailTextField.setBounds(170, 246, 116, 22);
        frame.getContentPane().add(emailTextField);

        JLabel lblFirstname = new JLabel("FirstName");
        lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblFirstname.setBounds(70, 144, 88, 16);
        frame.getContentPane().add(lblFirstname);

        JLabel lblEmail = new JLabel("email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setBounds(70, 248, 88, 16);
        frame.getContentPane().add(lblEmail);

        JLabel lblLastname = new JLabel("LastName");
        lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLastname.setBounds(70, 198, 88, 16);
        frame.getContentPane().add(lblLastname);

        passWordtextField = new JTextField();
        passWordtextField.setColumns(10);
        passWordtextField.setBounds(170, 292, 116, 22);
        frame.getContentPane().add(passWordtextField);

        lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPassword.setBounds(70, 295, 88, 16);
        frame.getContentPane().add(lblPassword);

        NewUserButton = new JButton("Save");

        NewUserButton.setBounds(221, 359, 116, 25);
        frame.getContentPane().add(NewUserButton);
        frame.setVisible(true);
    }

    public void makeInvisibleView()
    {
        frame.setVisible(false);
    }
    private void buttonsAddActionListener()
    {
        NewUserButton.addActionListener(e->loginController.saveUserBtn(this,new User(usernameField.getText(),firstNametextField_1.getText(),lastNametextField_2.getText(),emailTextField.getText(),passWordtextField.getText())));
    }

    public void popUpMessage(String message)
    {
        JOptionPane.showMessageDialog(frame, message);
    }

}
