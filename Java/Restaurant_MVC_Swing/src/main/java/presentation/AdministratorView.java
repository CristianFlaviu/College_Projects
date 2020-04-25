package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import businessLayer.MenuItems;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.HashSet;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;

public class AdministratorView extends JFrame {
	private JPanel panel;
	
	private JTextField NametextField;
	private JButton AddBtn;
	private JLabel lblMenuitemprice;
	private JTextField PricetextField;
	private JLabel lblDeleteAMenuitem;
	private JComboBox DeletecomboBox;
	private JButton DeleteBtn;
	private JButton ViewAllITems;
	private JTextField textFieldName;
	private JLabel CompositeProductNamelbl;
	private JComboBox ProductListBox;
	private JButton addToListbtn;
	private JButton addComositePrBtn;
	private  DefaultListModel demoList;
	private JButton EditBtn;
	
	
	public AdministratorView(HashSet<MenuItems> listaDeMeniuri) {
		
		this.setBounds(100, 100, 675, 451);
		
		getContentPane().setLayout(null);
		
		NametextField = new JTextField();
		NametextField.setBounds(49, 119, 98, 23);
		getContentPane().add(NametextField);
		NametextField.setColumns(10);
		
		JLabel lblAddNewMenu = new JLabel(" MenuItem Name");
		lblAddNewMenu.setBounds(49, 75, 110, 33);
		getContentPane().add(lblAddNewMenu);
		
		AddBtn = new JButton("AddBase");
		AddBtn.setBounds(49, 238, 98, 23);
		getContentPane().add(AddBtn);
		
		lblMenuitemprice = new JLabel("MenuItemPrice");
		lblMenuitemprice.setBounds(49, 167, 132, 14);
		getContentPane().add(lblMenuitemprice);
		
		PricetextField = new JTextField();
		PricetextField.setBounds(49, 201, 98, 20);
		getContentPane().add(PricetextField);
		PricetextField.setColumns(10);
		
		lblDeleteAMenuitem = new JLabel("Delete a MenuItem");
		lblDeleteAMenuitem.setBounds(539, 84, 110, 14);
		getContentPane().add(lblDeleteAMenuitem);
		
		DeletecomboBox = new JComboBox();
		DeletecomboBox.setBounds(539, 120, 94, 20);
		getContentPane().add(DeletecomboBox);
		
		DeleteBtn = new JButton("Delete");
		DeleteBtn.setBounds(539, 238, 94, 23);
		getContentPane().add(DeleteBtn);
		
		ViewAllITems = new JButton("View MenuItems");
		ViewAllITems.setBounds(235, 357, 231, 23);
		getContentPane().add(ViewAllITems);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(211, 120, 86, 20);
		getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		CompositeProductNamelbl = new JLabel("CompositeProductName");
		CompositeProductNamelbl.setBounds(202, 84, 140, 14);
		getContentPane().add(CompositeProductNamelbl);
		
		ProductListBox = new JComboBox();
		ProductListBox.setBounds(211, 166, 86, 20);
		getContentPane().add(ProductListBox);
		
		addToListbtn = new JButton("add");
		addToListbtn.setBounds(317, 163, 61, 23);
		getContentPane().add(addToListbtn);
		
		addComositePrBtn = new JButton("AddComposite");
		addComositePrBtn.setBounds(211, 213, 101, 23);
		getContentPane().add(addComositePrBtn);
		
		demoList = new DefaultListModel();
		
	
		 
		JList lista = new JList(demoList);
		lista.setBounds(399, 105, 98, 156);
		getContentPane().add(lista);
		
		JLabel JLabel22 = new JLabel("Products added ");
		JLabel22.setBounds(399, 84, 98, 14);
		getContentPane().add(JLabel22);
		
		 EditBtn = new JButton("Edit");
		EditBtn.setBounds(211, 247, 101, 23);
		getContentPane().add(EditBtn);

		for(MenuItems aItems:listaDeMeniuri)
		{
			DeletecomboBox.addItem(aItems.getName());
			ProductListBox.addItem(aItems.getName());
		}
		
	}
	
	public void DeleteList()
	{
		demoList.removeAllElements();
	}
	public void deleteComboList(String name)
	{
		ProductListBox.removeItem(name);
	}
	public void deleteComboDelte(String name)
	{
		DeletecomboBox.removeItem(name);
	}
	public String getSelectedItemToDelte()
	{
		return DeletecomboBox.getSelectedItem().toString();
	}
	public String getCompositeProductName()
	{
		return textFieldName.getText();
	}
	public int getLenghtList()
	{
		return demoList.getSize();
	}
	public String getItemsFromListatIndex(int i)
	{
		return demoList.getElementAt(i).toString();
	}
	public boolean CheckIteminTheList(String aString)
	{
		
		for(int i=0;i<demoList.getSize();i++)
			if(demoList.getElementAt(i).equals(aString))
			{
				System.out.println("already in the list");
				return true;
			}
		return false;
	}
	public void addElementsToList(String element)
	{
		demoList.addElement(element);		
	}
	
	public String getNameTextField()
	{
		return NametextField.getText();
	}
	public int getPricetextField()
	{
		return Integer.parseInt(PricetextField.getText());
	}
	
	public void addItemToDeleteCombobox(String item)
	{
		DeletecomboBox.addItem(item);
	}
	public void addItemToProductListComboBox(String item)
	{
		ProductListBox.addItem(item);
	}
	public String getSelectedItemProductList()
	{
		return ProductListBox.getSelectedItem().toString();
	}
	
	public void  addBtnAddMenuItemACtionListener(ActionListener actionListener)
	{
		AddBtn.addActionListener(actionListener);
	}
	public void deleteBtnActionListener(ActionListener actionListener)
	{
		DeleteBtn.addActionListener(actionListener);
	}
	public void addviewBtnActionListener1(ActionListener actionListener)
	{
		ViewAllITems.addActionListener(actionListener);
	}
	
	public void addProducttoListBtn(ActionListener actionListener)
	{
			addToListbtn.addActionListener(actionListener);
	}
	
	public void addBtnAddCompositeProduct(ActionListener actionListener)
	{
		addComositePrBtn.addActionListener(actionListener);
	}
	public void addEditBtn(ActionListener actionListener)
	{
		EditBtn.addActionListener(actionListener);
	}
}
