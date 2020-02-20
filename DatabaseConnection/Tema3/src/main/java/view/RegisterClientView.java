package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RegisterClientView extends JFrame {
	private JTextField textFieldClientName;
	private JTextField textFieldAge;
	private JTextField textFieldAdress;
	private JTextField textFieldEmail;
	private JButton btnSaveBtn;
	private JButton btnUpdate;
	

	public RegisterClientView() {
	
		this.setBounds(100, 100, 300, 300);
		
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ClientName");
		lblNewLabel_1.setBounds(36, 45, 86, 26);
		getContentPane().add(lblNewLabel_1);
		
		textFieldClientName = new JTextField();
		textFieldClientName.setBounds(133, 48, 86, 20);
		getContentPane().add(textFieldClientName);
		textFieldClientName.setColumns(10);
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(133, 95, 86, 20);
		getContentPane().add(textFieldAge);
		textFieldAge.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ClientAge");
		lblNewLabel.setBounds(36, 98, 75, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldAdress = new JTextField();
		textFieldAdress.setBounds(133, 143, 86, 20);
		getContentPane().add(textFieldAdress);
		textFieldAdress.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Client Address");
		lblNewLabel_2.setBounds(36, 146, 86, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblClient = new JLabel("Client email");
		lblClient.setBounds(36, 186, 69, 14);
		getContentPane().add(lblClient);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(133, 183, 86, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		 btnSaveBtn = new JButton("Save");
		btnSaveBtn.setBounds(170, 214, 89, 23);
		getContentPane().add(btnSaveBtn);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(20, 212, 75, 26);
		getContentPane().add(btnUpdate);
		
	}


	public void setSaveBtnVisible(boolean visible)
	{
		btnSaveBtn.setVisible(visible);
	}
	public void setUpdateBtnVisible(boolean visible)
	{
		btnUpdate.setVisible(visible);
	}
	public String getTextFieldClientName() {
		return textFieldClientName.getText();
	}


	public String getTextFieldAge() {
		return textFieldAge.getText();
	}


	public String getTextAdress() {
		return textFieldAdress.getText();
	}


	public String getTextFieldEmail() {
		return textFieldEmail.getText();
	}
	
	public void addSaveBtn(final ActionListener actionListener)
	{
		btnSaveBtn.addActionListener(actionListener);
	}
	public void addUpdateBtn(final ActionListener actionListener)
	{
		btnUpdate.addActionListener(actionListener);
	}
}
