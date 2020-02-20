package presentation;

import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderView extends JFrame {
	private JTable table; 
	public OrderView(String [][] rowData) {

		this.setBounds(100, 100, 423, 293);	
		getContentPane().setLayout(null);
		
		String [] columnNames= {"id","Date","Table"};
	
		DefaultTableModel aDefaultTableModel=new DefaultTableModel( rowData,columnNames);
		JTable table=new JTable(aDefaultTableModel);
		table.setBounds(50, 40, 200, 100);
		
		JScrollPane aJScrollPane=new JScrollPane(table);
		aJScrollPane.setBounds(81, 46, 278, 150);
		aJScrollPane.setVisible(true);
		getContentPane().add(aJScrollPane);
		
		
	}

}
