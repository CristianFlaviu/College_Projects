package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import dao.GeneralDao;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OrderView extends JFrame {
	private JTextField textField;
	private JComboBox comboBoxClient ;
	private JComboBox comboBoxProduct;
	private JButton btnNewOrder;
	
	public OrderView() {
		
		this.setBounds(100, 100, 399, 419);

		
		getContentPane().setLayout(null);
		
		JLabel lblSelectclient = new JLabel("SelectClient");
		lblSelectclient.setBounds(134, 75, 87, 23);
		getContentPane().add(lblSelectclient);
		
		String[] aStrings3 = new String[GeneralDao.getNrRows("Client")];
		String[][] aStrings4=GeneralDao.getAllRows("Client");
		for(int i=0;i<GeneralDao.getNrRows("Client");i++)
		{
			aStrings3[i]=aStrings4[i][0] +" "+aStrings4[i][1];
		}
		
		 comboBoxClient = new JComboBox(aStrings3);
		comboBoxClient.setBounds(134, 110, 138, 23);
		getContentPane().add(comboBoxClient);
		
		JLabel lblSelectproduct = new JLabel("SelectProduct");
		lblSelectproduct.setBounds(134, 144, 87, 14);
		getContentPane().add(lblSelectproduct);
		
		String[] aStrings = new String[GeneralDao.getNrRows("Product")];
		String[][] aStrings2=GeneralDao.getAllRows("Product");
		for(int i=0;i<GeneralDao.getNrRows("Product");i++)
		{
			aStrings[i]=aStrings2[i][0] +" "+aStrings2[i][1];
		}
		
		
		
		comboBoxProduct = new JComboBox(aStrings);
		comboBoxProduct.setBounds(134, 169, 135, 23);
		getContentPane().add(comboBoxProduct);
		
		JLabel lblSelectquantity = new JLabel("SelectQuantity");
		lblSelectquantity.setBounds(141, 215, 94, 14);
		getContentPane().add(lblSelectquantity);
		
		textField = new JTextField();
		textField.setBounds(134, 240, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		 btnNewOrder = new JButton("NewOrder");
		btnNewOrder.setBounds(205, 285, 89, 23);
		getContentPane().add(btnNewOrder);

	}
	
	public JComboBox getClientComboBox()
	{
		return comboBoxClient;
	}
	public JComboBox getProductComboBox()
	{
		return comboBoxProduct;
	}
	
	public String getStringQuantity()
	{
		return textField.getText();
	}
	public void addBtnNewOrder(final ActionListener actionListener)
	{
		btnNewOrder.addActionListener(actionListener);
	}
	public static void main(String[] args)
	{
		OrderView aOrderView=new OrderView();
		aOrderView.setVisible(true);
	}
	}
