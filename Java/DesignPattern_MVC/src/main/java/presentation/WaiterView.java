package presentation;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import businessLayer.MenuItems;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;

public class WaiterView extends JFrame {
	private JTextField TabletextField;
	private JButton ViewAllOrdersbtn;
	private JButton addToListBtn;
	private JButton orderBtn ;
	public JComboBox comboBox;
	public DefaultListModel demoList;
	public WaiterView(HashSet<MenuItems> ListaMeniu) {

		this.setBounds(100, 100, 513, 451);
	
		getContentPane().setLayout(null);
		
		 orderBtn = new JButton("Order");
		orderBtn.setBounds(60, 208, 99, 23);
		getContentPane().add(orderBtn);
		
		TabletextField = new JTextField();
		TabletextField.setBounds(60, 85, 110, 20);
		getContentPane().add(TabletextField);
		TabletextField.setColumns(10);
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setBounds(68, 60, 59, 14);
		getContentPane().add(lblTable);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(60, 136, 110, 20);
		getContentPane().add(comboBox);
		
		demoList = new DefaultListModel();
		
		JList Productslist = new JList(demoList);
		Productslist.setBounds(283, 99, 183, 160);
		getContentPane().add(Productslist);
		
		JLabel lblOrderedProduct = new JLabel("Ordered Products");
		lblOrderedProduct.setBounds(284, 60, 133, 14);
		getContentPane().add(lblOrderedProduct);
		
		addToListBtn = new JButton("Add");
		addToListBtn.setBounds(195, 135, 59, 23);
		getContentPane().add(addToListBtn);
		
		 ViewAllOrdersbtn = new JButton("View All Orders");
		ViewAllOrdersbtn.setBounds(195, 340, 148, 23);
		getContentPane().add(ViewAllOrdersbtn);
		
		
		for(MenuItems aItems:ListaMeniu)
		{
			comboBox.addItem(aItems.getName());
		}
		}
	
	
	public DefaultListModel getModel()
	{
		return demoList;
	}
	public int  getTable()
	{
		return Integer.parseInt(TabletextField.getText());
		
	}
	
	public void addToListBtn(ActionListener actionListener)
	{
		addToListBtn.addActionListener(actionListener);
	}
	public void addViewallOrdersBtn(ActionListener actionListener)
	{
		ViewAllOrdersbtn.addActionListener(actionListener);
	}
	public void addOrdesActionLinster(ActionListener actionListener)
	{
		orderBtn.addActionListener(actionListener);
	}
	
}
