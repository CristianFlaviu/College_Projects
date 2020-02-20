package presentation;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Label;
import java.awt.Font;
import java.awt.Toolkit;

public class ChefView extends JFrame implements Observer{

	
	public ChefView() {
		this.setBounds(100, 100, 284, 252);		
		getContentPane().setLayout(null);
		
		Label label = new Label("I m a Chef");
		label.setAlignment(Label.CENTER);
		label.setFont(new Font("Arial Black", Font.PLAIN, 22));
		label.setBounds(43, 10, 158, 110);
		getContentPane().add(label);
		setLocationRelativeTo(null);
	}
	public void update(Observable arg0, Object arg1) {
	
	this.setVisible(true);
	showMessage("i got an order");
	
		
	}

	public void showMessage(String message)
	{
		JOptionPane.showMessageDialog(null,message);
	}
}
