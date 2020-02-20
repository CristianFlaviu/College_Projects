package presentation;

import javax.swing.JFrame;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Dashboard extends JFrame {
	private JButton WaiterBtn;
	private JButton AdministratorViewBtn;
	private JButton ChefViewBtn;
	public Dashboard() {
		this.setBounds(100, 100, 615, 387);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		AdministratorViewBtn = new JButton("Administrator");
		AdministratorViewBtn.setBounds(229, 114, 140, 29);
		getContentPane().add(AdministratorViewBtn);
		
		WaiterBtn = new JButton("Waiter");
		WaiterBtn.setBounds(229, 177, 140, 34);
		getContentPane().add(WaiterBtn);
		
		ChefViewBtn = new JButton("Chef");
		ChefViewBtn.setBounds(229, 242, 140, 29);
		getContentPane().add(ChefViewBtn);
		
		JLabel lblNewLabel = new JLabel("Fancy Restaurant");
		lblNewLabel.setFont(new Font("Century", Font.BOLD, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(178, 47, 230, 34);
		getContentPane().add(lblNewLabel);
	}
	
	public void AdministratorBtnActionListener(ActionListener actionListener)
	{
		AdministratorViewBtn.addActionListener(actionListener);
	}
	public void waiterBtnActionLister(ActionListener actionListener)
	{
		WaiterBtn.addActionListener(actionListener);
	}
	public void chefViewActionLister(ActionListener actionListener)
	{
		ChefViewBtn.addActionListener(actionListener);
	}
}
