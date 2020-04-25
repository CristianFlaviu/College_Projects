package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NewProductView extends JFrame {
	private JTextField textFieldProductName;
	private JTextField textFieldProductquantity;
	private JTextField textFieldProductPrice;
	private JButton btnSaveProduct;
	private JButton btnUpdateProduct;
	
public NewProductView() {
	this.setBounds(100, 100, 308, 300);
	getContentPane().setLayout(null);
	
	JLabel lblProductname = new JLabel("ProductName");
	lblProductname.setBounds(38, 40, 79, 19);
	getContentPane().add(lblProductname);
	
	textFieldProductName = new JTextField();
	textFieldProductName.setBounds(127, 39, 86, 20);
	getContentPane().add(textFieldProductName);
	textFieldProductName.setColumns(10);
	
	JLabel lblNewLabel = new JLabel("Productquantity");
	lblNewLabel.setBounds(38, 93, 77, 14);
	getContentPane().add(lblNewLabel);
	
	textFieldProductquantity = new JTextField();
	textFieldProductquantity.setBounds(127, 90, 86, 20);
	getContentPane().add(textFieldProductquantity);
	textFieldProductquantity.setColumns(10);
	
	JLabel lblNewLabel_1 = new JLabel("ProductPrice");
	lblNewLabel_1.setBounds(38, 141, 77, 14);
	getContentPane().add(lblNewLabel_1);
	
	textFieldProductPrice = new JTextField();
	textFieldProductPrice.setBounds(127, 138, 86, 20);
	getContentPane().add(textFieldProductPrice);
	textFieldProductPrice.setColumns(10);
	
	btnSaveProduct = new JButton("New Product");
	btnSaveProduct.setBounds(168, 201, 103, 23);
	getContentPane().add(btnSaveProduct);
	
    btnUpdateProduct = new JButton("Update");
	btnUpdateProduct.setBounds(26, 201, 89, 23);
	getContentPane().add(btnUpdateProduct);
	
	
}

public String getTextFieldProductName() {
	return textFieldProductName.getText();
}

public String getTextFieldProductquantity() {
	return textFieldProductquantity.getText();
}

public String getTextFieldProductPrice() {
	return textFieldProductPrice.getText();
}

public void setVisbileBtnSaveProduct(boolean visible) {
	btnSaveProduct.setVisible(visible);
}

public void setVisibleBtnUpdateProduct(boolean visible) {
	 btnUpdateProduct.setVisible(visible);
}

public void addSaveBtnProduct(final ActionListener actionListener)
{
	btnSaveProduct.addActionListener(actionListener);
}

public void addUpdateBtnProduct(final ActionListener actionListener)
{
	btnUpdateProduct.addActionListener(actionListener);
}
public void setProductNameTextField(String message)
{
	textFieldProductName.setText(message);
}
public void setQuantityProductTextFiled(String message)
{
	textFieldProductquantity.setText(message);
}
public void setPriceProductTextField(String message)
{
	textFieldProductPrice.setText(message);
}
}
