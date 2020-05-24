package Gui;



import guiController.LoginController;


import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame{

    private JFrame frame;
    private JTextField usernameTextField;
    private JPasswordField textField;

    private JLabel LoginLabel;
    private JLabel userNameNewLabel;
    private JLabel passwordLabel;

    private JButton LoginButton;
    private JButton createAccButton;

    private LoginController userController=new LoginController();



    public LoginView() {

        initializeView();
        buttonsAddActionListener();
        frame.setVisible(true);
    }


    private void initializeView() {
        frame = new JFrame();

        frame.setBounds(100, 100, 472, 321);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        LoginLabel = new JLabel("Login");
        LoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        LoginLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
        LoginLabel.setBounds(146, 24, 130, 46);
        frame.getContentPane().add(LoginLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(160, 112, 116, 29);
        frame.getContentPane().add(usernameTextField);
        usernameTextField.setColumns(10);

        userNameNewLabel = new JLabel("email");
        userNameNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userNameNewLabel.setBounds(47, 115, 101, 22);
        frame.getContentPane().add(userNameNewLabel);

        textField = new JPasswordField();
        textField.setBounds(160, 154, 116, 29);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        passwordLabel = new JLabel("Password");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setBounds(57, 160, 75, 16);
        frame.getContentPane().add(passwordLabel);

        LoginButton = new JButton("Login");
        LoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        LoginButton.setBounds(246, 215, 97, 25);
        frame.getContentPane().add(LoginButton);

        createAccButton = new JButton("New Account");
        createAccButton.setBounds(113, 215, 121, 25);
        frame.getContentPane().add(createAccButton);



        frame.setVisible(true);
    }

    private void buttonsAddActionListener()
    {

        createAccButton.addActionListener(e-> userController.changeNewUserWindow(this));
        LoginButton.addActionListener(e->userController.doLogin(this,usernameTextField.getText(),textField.getText()));
    }
    public void makeViewInvisible()
    {
        frame.setVisible(false);
    }

    public void popUpMessage(String message)
    {
        JOptionPane.showMessageDialog(frame, message);
    }


}

