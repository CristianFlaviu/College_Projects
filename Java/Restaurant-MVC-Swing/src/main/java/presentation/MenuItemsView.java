package presentation;

import java.awt.ScrollPane;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuItemsView extends JFrame{

	

	public MenuItemsView(String[][] rowdata) {
		
		this.setBounds(100, 100, 439, 314);	
		getContentPane().setLayout(null);
		
		String [] columnNames= {"Name","Price"};
	
		DefaultTableModel aDefaultTableModel=new DefaultTableModel( rowdata,columnNames);
		JTable table=new JTable(aDefaultTableModel);
		table.setBounds(50, 40, 200, 100);
		
		JScrollPane aJScrollPane=new JScrollPane(table);
		aJScrollPane.setBounds(56, 38, 329, 207);
		aJScrollPane.setVisible(true);
		getContentPane().add(aJScrollPane);
		
		
		
		
	}
	
}
