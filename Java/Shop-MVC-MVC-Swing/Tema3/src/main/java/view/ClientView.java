package view;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;




import com.mysql.cj.conf.StringProperty;

import bll.ClientBll;
import bll.OrdersBll;
import bll.ProductBll;
import model.*;


import dao.GeneralDao;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.plaf.ToolTipUI;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;


public class ClientView extends JFrame {


	private JButton btnDelete;
	private JButton btnNewClient;
	private JButton btnUpdateClient;
	private JButton btnNewProduct;
	private JComboBox comboBocDelete;
	private DefaultTableModel modelProduct;
	private DefaultTableModel modelOrders;
	private DefaultTableModel modelClient;

	public JScrollPane scrollPaneClient;
	public JScrollPane scrollPaneProduct;
	public JScrollPane ScrollPaneOrders;
	private JComboBox comboBoxProduct;
	private JButton btnDeleteProduct;
	private JComboBox comboBoxUpdateClient;
	private JButton btnUpdateProduct;
	private JComboBox comboBoxUpdateProduct;
	private JButton btnViewProduct;
	private JButton btnViewClient;
	private JButton btnViewOrders;
	private JButton btnButtonNewOrder;
	

	
	public ClientView() {

		this.setBounds(100, 100, 865, 451);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		//scrollPaneClient = new JScrollPane(createTable(new Client(), GeneralDao.getAllRows("Client")));
		scrollPaneClient = new JScrollPane(createTable1( GeneralDao.selectALL(new Client())));

		scrollPaneClient.setSize(551, 197);
		scrollPaneClient.setLocation(10, 98);
		scrollPaneClient.setVisible(true);
		getContentPane().add(scrollPaneClient);

		//scrollPaneProduct = new JScrollPane(createTable(new Product(), GeneralDao.getAllRows("Product")));
		scrollPaneProduct = new JScrollPane(createTable1(GeneralDao.selectALL(new Product())));
		scrollPaneProduct.setSize(551, 197);
		scrollPaneProduct.setLocation(10, 98);
		scrollPaneProduct.setVisible(true);
		getContentPane().add(scrollPaneProduct);

		//ScrollPaneOrders = new JScrollPane(createTable(new Orders(), GeneralDao.getAllRows("Orders")));
		ScrollPaneOrders = new JScrollPane(createTable1(GeneralDao.selectALL(new Orders())));
		ScrollPaneOrders.setSize(551, 197);
		ScrollPaneOrders.setLocation(10, 98);
		ScrollPaneOrders.setVisible(true);
		getContentPane().add(ScrollPaneOrders);

		btnDelete = new JButton("DeleteClient");
		/*btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});*/
		btnDelete.setBounds(62, 319, 138, 23);
		getContentPane().add(btnDelete);

		btnNewClient = new JButton("New Client");
		btnNewClient.setBounds(656, 71, 125, 23);
		getContentPane().add(btnNewClient);

		btnUpdateClient = new JButton("update Client");
		btnUpdateClient.setBounds(428, 319, 125, 23);
		getContentPane().add(btnUpdateClient);

		String[] aStrings =new String[GeneralDao.getNrRows("Client")];
		String[][] aStrings2=GeneralDao.getAllRows("Client");
		System.out.println(aStrings2[0][1]);
		System.out.println(GeneralDao.getNrRows("Client"));
		for(int i=0;i<GeneralDao.getNrRows("Client");i++)
		{
			aStrings[i]=aStrings2[i][0];
			
		}
		
		comboBocDelete = new JComboBox(aStrings);
		comboBocDelete.setBounds(62, 353, 138, 33);
		getContentPane().add(comboBocDelete);
		
		comboBoxUpdateClient = new JComboBox(aStrings);
		comboBoxUpdateClient.setBounds(428, 353, 125, 29);
		getContentPane().add(comboBoxUpdateClient);
		
		btnNewProduct = new JButton("NewProduct");
		btnNewProduct.setBounds(656, 114, 125, 23);
		getContentPane().add(btnNewProduct);
		
		String[] aStrings3 = new String[GeneralDao.getNrRows("Product")];
		String[][] aStrings4=GeneralDao.getAllRows("Product");
		for(int i=0;i<GeneralDao.getNrRows("Product");i++)
		{
			aStrings3[i]=aStrings4[i][0];
		}
			
		comboBoxProduct = new JComboBox(aStrings3);
		comboBoxProduct.setBounds(241, 353, 118, 33);
		getContentPane().add(comboBoxProduct);
		
		btnDeleteProduct = new JButton("DeleteProduct");
		btnDeleteProduct.setBounds(241, 319, 118, 23);
		getContentPane().add(btnDeleteProduct);
		
	
		
		btnUpdateProduct = new JButton("update Product");
		btnUpdateProduct.setBounds(603, 319, 107, 23);
		getContentPane().add(btnUpdateProduct);
		
		comboBoxUpdateProduct = new JComboBox(aStrings3);
		comboBoxUpdateProduct.setBounds(603, 353, 107, 26);
		getContentPane().add(comboBoxUpdateProduct);
		
		btnViewProduct = new JButton("Product");
		btnViewProduct.setBounds(75, 39, 89, 23);
		getContentPane().add(btnViewProduct);
		
		btnViewClient = new JButton("Client");
		btnViewClient.setBounds(192, 39, 89, 23);
		getContentPane().add(btnViewClient);
		
		btnViewOrders = new JButton("Orders");
		btnViewOrders.setBounds(316, 39, 89, 23);
		getContentPane().add(btnViewOrders);
		
		btnButtonNewOrder = new JButton("NewOrder");
		btnButtonNewOrder.setBounds(656, 163, 125, 23);
		getContentPane().add(btnButtonNewOrder);
		

	}

	public JTable createTable1(ArrayList<Object> aList)
	{
		JTable table=null;
		java.lang.reflect.Field[] attributes = aList.get(0).getClass().getDeclaredFields();
		String[] columnNames = new String[attributes.length];
		
		for (int i = 0; i < attributes.length; i++) {
			System.out.println("ATTRIBUTE NAME: " + attributes[i].getName());
			columnNames[i] = attributes[i].getName();			
		}
		String aux=columnNames[columnNames.length-1];
		for (int i = attributes.length-1; i >0; i--) {
			
			columnNames[i] = columnNames[i-1];			
		}
		columnNames[0]=attributes[attributes.length-1].getName();
		
		if(aList.get(0).getClass().getSimpleName().equals("Client")) {
		table = new JTable(modelClient);
		modelClient = new DefaultTableModel(columnNames,0);
		for(int i=0;i<aList.size();i++)
		{
			modelClient.addRow(((Client)aList.get(i)).toArrayString());			
		
		}
		
		
		table.setModel(modelClient);}
		
		if(aList.get(0).getClass().getSimpleName().equals("Product"))
		{
			table = new JTable(modelProduct);
			modelProduct = new DefaultTableModel(columnNames,0);
			for(int i=0;i<aList.size();i++)
			{
				modelProduct.addRow(((Product)aList.get(i)).toArrayString());			
			
			}
			
			table.setModel(modelProduct);
		}
		if(aList.get(0).getClass().getSimpleName().equals("Orders"))
		{
			table = new JTable(modelOrders);
			modelOrders = new DefaultTableModel(columnNames,0);
			for(int i=0;i<aList.size();i++)
			{
				modelOrders.addRow(((Orders)aList.get(i)).toArrayString());			
			
			}
			
			table.setModel(modelOrders);
		}
		return table;
		
		
	}
	public JTable createTable(Object aObject, Object[][] bObjects) {
		JTable table = null;

		java.lang.reflect.Field[] attributes = aObject.getClass().getDeclaredFields();
		String[] columnNames = new String[attributes.length];

		for (int i = 0; i < attributes.length; i++) {
			System.out.println("ATTRIBUTE NAME: " + attributes[i].getName());
			columnNames[i] = attributes[i].getName();			
		}
		String aux=columnNames[columnNames.length-1];
		for (int i = attributes.length-1; i >0; i--) {
			
			columnNames[i] = columnNames[i-1];			
		}
		columnNames[0]=attributes[attributes.length-1].getName();
		
		
		if (aObject.getClass().getSimpleName().equals("Client")) {
			modelClient = new DefaultTableModel((String[][]) bObjects, columnNames);
			table = new JTable(modelClient);
			table.setModel(modelClient);
		} else {
			if (aObject.getClass().getSimpleName().equals("Product")) {
				modelProduct = new DefaultTableModel((String[][]) bObjects, columnNames);
				table = new JTable(modelProduct);
				table.setModel(modelProduct);
			}
			else
			{
				if(aObject.getClass().getSimpleName().equals("Orders"))
				{modelOrders = new DefaultTableModel((String[][]) bObjects, columnNames);
				table = new JTable(modelOrders);
				table.setModel(modelOrders);
					
				}
			}
		
		}

		table.setBounds(109, 121, 299, 162);

		return table;

	}

	public JComboBox getClientCombo() {
		return comboBocDelete;
		
	}
	public JComboBox getProductCombo() {
		
		return comboBoxProduct;
	}
	public JComboBox getClientUpdateCombo() {
		return comboBoxUpdateClient;
	}
	public JComboBox getProductUpdateCombo() {
		return comboBoxUpdateProduct;
	}

	public int  addRowClient(Object object) {
	

		if (object.getClass().getSimpleName().equals("Client")) {
			System.out.println("add a client");
			ClientBll aBll = new ClientBll();
			int a=aBll.insertClient((Client) object);
		
			comboBocDelete.addItem(a+"");
			comboBoxUpdateClient.addItem(a+"");
			while (modelClient.getRowCount() > 0) {
				modelClient.removeRow(0);
			}

			String[][] aStrings = GeneralDao.getAllRows(object.getClass().getSimpleName());
			int row = GeneralDao.getNrRows(object.getClass().getSimpleName());
			for (int i = 0; i < row; i++) {
				modelClient.addRow(aStrings[i]);
			}
			return a;

		} else {
			if (object.getClass().getSimpleName().equals("Product")) {

				System.out.println("add a Product");
				ProductBll aBll = new ProductBll();
				int a=aBll.insertProduct((Product) object);
			
				comboBoxProduct.addItem(a+"");
				comboBoxUpdateProduct.addItem(a+"");
				while (modelProduct.getRowCount() > 0) {
					modelProduct.removeRow(0);
				}

				String[][] aStrings = GeneralDao.getAllRows(object.getClass().getSimpleName());
				int row = GeneralDao.getNrRows(object.getClass().getSimpleName());
				for (int i = 0; i < row; i++) {
					modelProduct.addRow(aStrings[i]);
				}
				return a;
			}
			else
			{
				if (object.getClass().getSimpleName().equals("Orders"))
				{
					System.out.println("add a Order");
					OrdersBll aBll = new OrdersBll();
					int a=aBll.insertOrders((Orders) object);

					while (modelOrders.getRowCount() > 0) {
						modelOrders.removeRow(0);
					}

					String[][] aStrings = GeneralDao.getAllRows(object.getClass().getSimpleName());
					int row = GeneralDao.getNrRows(object.getClass().getSimpleName());
					for (int i = 0; i < row; i++) {
						modelOrders.addRow(aStrings[i]);
						
						return a;
					}
				}
			}

		}
		
		return 0;

	}
	
	public void refreshProductAndOrders()
	{
		while (modelProduct.getRowCount() > 0) {
			modelProduct.removeRow(0);
		}

		String[][] aStrings = GeneralDao.getAllRows("Product");
		int row = GeneralDao.getNrRows("Product");
		for (int i = 0; i < row; i++) {
			modelProduct.addRow(aStrings[i]);
		}
		
		while (modelOrders.getRowCount() > 0) {
			modelOrders.removeRow(0);
		}

		String[][] a1Strings = GeneralDao.getAllRows("Orders");
		int row1 = GeneralDao.getNrRows("Orders");
		for (int i = 0; i < row1; i++) {
			modelOrders.addRow(a1Strings[i]);
		}
		
	}

	public void deleteRow(int id,String ClassName) {

	int row=1;
		if(ClassName.equals("Client"))
		{
			for(int i=0;i<GeneralDao.getNrRows("Client");i++)
			{
				if(modelClient.getValueAt(i, 0).equals(""+id))
					{row=i;
					break;}
			}
			
			System.out.println("row to delete"+row);
		modelClient.removeRow(row);
		ClientBll aBll=new ClientBll();
		aBll.DeleteClient(id);
		comboBocDelete.removeItemAt(row);
		comboBoxUpdateClient.removeItemAt(row);
		}
		else
		{
			if(ClassName.equals("Product"))
			{
				for(int i=0;i<GeneralDao.getNrRows("Product");i++)
				{
					if(modelProduct.getValueAt(i, 0).equals(""+id))
						{row=i;
						break;}
				}
				
				System.out.println("row to delete   "+  row);
			modelProduct.removeRow(row);
			ProductBll aBll=new ProductBll();
			aBll.DeleteProduct(id);
			comboBoxProduct.removeItemAt(row);
			comboBoxUpdateProduct.removeItemAt(row);;
			}
		}

		

	}

	public void updateRow(Object object) {

		if(object.getClass().getSimpleName().equals("Client"))
		{	ClientBll aBll = new ClientBll();
		aBll.updateClient((Client)object);
		
		while (modelClient.getRowCount() > 0) {
			modelClient.removeRow(0);
		}

		String[][] aStrings = GeneralDao.getAllRows("Client");
		int row = GeneralDao.getNrRows("Client");
		for (int i = 0; i < row; i++) {
			modelClient.addRow(aStrings[i]);
		}
		}
		else
		{
			ProductBll aBll=new ProductBll();
			aBll.uptadeProduct((Product) object);
			System.out.println("update la produs");
			while (modelProduct.getRowCount() > 0) {
				modelProduct.removeRow(0);
			}

			String[][] aStrings = GeneralDao.getAllRows("Product");
			int row = GeneralDao.getNrRows("Product");
			for (int i = 0; i < row; i++) {
				modelProduct.addRow(aStrings[i]);
			}
		}

	}

	public void addBtnListener(final ActionListener a) {
		btnDelete.addActionListener(a);
	}

	public void addBtnNewClien(final ActionListener actionListener) {
		btnNewClient.addActionListener(actionListener);
	}

	public void addBtnUpdateClient(final ActionListener actionListener) {
		btnUpdateClient.addActionListener(actionListener);

	}
	public void addBtnUpdateProduct(final ActionListener actionListener) {
		btnUpdateProduct.addActionListener(actionListener);
	}
	
	public void addBtnNewProduct(final ActionListener actionListener)
	{
		btnNewProduct.addActionListener(actionListener);
	}
	public void addBtnDeleteProduct(final ActionListener actionListener)
	{
		btnDeleteProduct.addActionListener(actionListener);
	}
	
	public void addBtnViewBtnClient(final ActionListener actionListener)
	{
		btnViewClient.addActionListener(actionListener);
	}

	public void addBtnViewProduct(final ActionListener actionListener)
	{
		btnViewProduct.addActionListener(actionListener);
	}
	public void addBtnViewOrders(final ActionListener actionListener)
	{
		btnViewOrders.addActionListener(actionListener);
	}
	public void addBtnNewOrders(final ActionListener actionListener)
	{
		btnButtonNewOrder.addActionListener(actionListener);
	}
	
	
	
}
