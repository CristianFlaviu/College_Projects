package view;

import java.awt.Desktop;
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.mysql.cj.xdevapi.Table;

import bll.OrdersBll;
import bll.ProductBll;
import dao.GeneralDao;
import model.Client;
import model.Orders;
import model.Product;

public class Controller {
	private ClientView aClientView;
	private RegisterClientView aRegisterClientView;
	private NewProductView aNewProductView;
	private OrderView aOrderView;

	public Controller() {
		aClientView = new ClientView();
		aClientView.setVisible(true);
		
		aRegisterClientView = new RegisterClientView();
		aRegisterClientView.setVisible(false);
			
		aNewProductView=new NewProductView();
		aNewProductView.setVisible(false);
		
		aOrderView=new OrderView();
		aOrderView.setVisible(false);
		
		initializeButtons();

	}

	public void initializeButtons() {
		
		
		aClientView.addBtnUpdateProduct(e->{
			int id=Integer.parseInt( (String) aClientView.getProductUpdateCombo().getSelectedItem());
			Product aProduct=(Product) GeneralDao.findById(id, "Product");
			
			aNewProductView.setProductNameTextField(aProduct.getProductName());
			aNewProductView.setQuantityProductTextFiled(aProduct.getQuantity()+"");
			aNewProductView.setPriceProductTextField(aProduct.getPrice()+"");
			aNewProductView.setVisible(true);
			aNewProductView.setVisbileBtnSaveProduct(false);
			aNewProductView.setVisibleBtnUpdateProduct(true);});
		
		
		
		aClientView.addBtnNewOrders(e->{
			aOrderView.setVisible(true);
		});
		
		aClientView.addBtnViewBtnClient(e->{
			aClientView.scrollPaneClient.setVisible(true);
			aClientView.ScrollPaneOrders.setVisible(false);
			aClientView.scrollPaneProduct.setVisible(false);
		});
		
		aClientView.addBtnViewOrders(e->{
		aClientView.scrollPaneClient.setVisible(false);
		aClientView.ScrollPaneOrders.setVisible(true);
		aClientView.scrollPaneProduct.setVisible(false);
			
		});
		aClientView.addBtnViewProduct(e->{
			aClientView.scrollPaneClient.setVisible(false);
			aClientView.ScrollPaneOrders.setVisible(false);
			aClientView.scrollPaneProduct.setVisible(true);
		});
		aClientView.addBtnDeleteProduct(e->{
			int id=Integer.parseInt((String) aClientView.getProductCombo().getSelectedItem());
			System.out.println("selected value "+id+"---");
			aClientView.deleteRow(id,"Product");
			
		});
		
		aClientView.addBtnListener(e -> {
			
		int id=Integer.parseInt((String) aClientView.getClientCombo().getSelectedItem());
		System.out.println("selected value"+id+"---");
		aClientView.deleteRow(id,"Client");
	
		});
		aClientView.addBtnNewProduct(e->{
			
			aNewProductView.setVisible(true);
			aNewProductView.setVisbileBtnSaveProduct(true);
			aNewProductView.setVisibleBtnUpdateProduct(false);
		});
		
		aClientView.addBtnNewClien(e -> {
			
			aRegisterClientView.setVisible(true);
			aRegisterClientView.setSaveBtnVisible(true);
			aRegisterClientView.setUpdateBtnVisible(false);
			
			
			
		});

		aClientView.addBtnUpdateClient(e -> {

			aRegisterClientView.setVisible(true);
			aRegisterClientView.setSaveBtnVisible(false);
			aRegisterClientView.setUpdateBtnVisible(true);

		});
		aRegisterClientView.addUpdateBtn(e -> {
			
			Client aClient = new Client();
		
			aClient.setName(aRegisterClientView.getTextFieldClientName());
			aClient.setAge(Integer.parseInt(aRegisterClientView.getTextFieldAge()));
			aClient.setAdress(aRegisterClientView.getTextAdress());
			aClient.setEmail(aRegisterClientView.getTextFieldEmail());
	
			
	
			int id=Integer.parseInt((String) aClientView.getClientUpdateCombo().getSelectedItem());
			aClient.setIdClient(id);
			aClientView.updateRow(aClient);
			
			
			
			
		});
		aRegisterClientView.addSaveBtn(e -> {
			
			try {
			Client aClient = new Client(1, aRegisterClientView.getTextFieldClientName(),aRegisterClientView.getTextAdress(), aRegisterClientView.getTextFieldEmail(),Integer.parseInt(aRegisterClientView.getTextFieldAge()));
			int a=aClientView.addRowClient(aClient);
			System.out.println("register view-------"+aClient+"register View-------");
			aRegisterClientView.setVisible(false);
			aClientView.scrollPaneClient.setVisible(true);
			aClientView.scrollPaneProduct.setVisible(false);
			aClientView.ScrollPaneOrders.setVisible(false);
			
			aClient.setIdClient(a);
			aOrderView.getClientComboBox().addItem(aClient.getIdClient()+" "+aClient.getName());
			}
			catch(Exception ab)
			{
				consoleMessage("wrong input format");
			}
			
		});
		aNewProductView.addSaveBtnProduct(e->{
			try {
			Product aProduct=new Product();
			
			aProduct.setPrice(Integer.parseInt(aNewProductView.getTextFieldProductPrice()));
			aProduct.setProductName(aNewProductView.getTextFieldProductName());
			aProduct.setQuantity(Integer.parseInt(aNewProductView.getTextFieldProductquantity()));
			
			
			aNewProductView.setVisible(false);
			int a=aClientView.addRowClient(aProduct);
			
			aClientView.scrollPaneClient.setVisible(false);
			aClientView.scrollPaneProduct.setVisible(true);
			aClientView.ScrollPaneOrders.setVisible(false);
			aProduct.setIdProduct(a);
			aOrderView.getProductComboBox().addItem(aProduct.getIdProduct()+" "+aProduct.getProductName());
			
			}
			catch(Exception aException)
			{
			aException.printStackTrace();
				consoleMessage(aException.getClass().getSimpleName());
			}
		
		});
		aNewProductView.addUpdateBtnProduct(e->{
		
			Product aProduct=new Product();
			
			aProduct.setPrice(Integer.parseInt(aNewProductView.getTextFieldProductPrice()));
			aProduct.setProductName(aNewProductView.getTextFieldProductName());
			aProduct.setQuantity(Integer.parseInt(aNewProductView.getTextFieldProductquantity()));
			
			int id=Integer.parseInt((String) aClientView.getProductUpdateCombo().getSelectedItem());
			aProduct.setIdProduct(id);
			aClientView.updateRow(aProduct);
			
			
		});
		
		aOrderView.addBtnNewOrder(e->{
			
			try
			{
		int quantity=Integer.parseInt(aOrderView.getStringQuantity());
		String StringIdClient=(String) aOrderView.getClientComboBox().getSelectedItem();
		int idClient=Integer.parseInt(StringIdClient.split(" ")[0]);
		
		String StringProduct=(String)aOrderView.getProductComboBox().getSelectedItem();
		int idProduct=Integer.parseInt(StringProduct.split(" ")[0]);
		System.out.println(idClient);
		System.out.println(idProduct);
		System.out.println(quantity);
		
		OrdersBll aBll=new OrdersBll();
		
		ProductBll productbll=new ProductBll();
		Product apProduct=productbll.findProductById(idProduct);
	
		
		
		System.out.println(apProduct.toString());
		printDocument(new Orders(idClient,idProduct,quantity,aBll.insertOrders(new Orders(idClient,idProduct,quantity,1))));
		
		
		apProduct.setQuantity(apProduct.getQuantity()-quantity);
		productbll.uptadeProduct(apProduct);
		aClientView.refreshProductAndOrders();
		
			}
			catch (Exception ab) {
				ab.printStackTrace();
				consoleMessage("wrong order format");
			}
		});
		
		
	}
	public void printDocument(Orders aOrders)
	{
		Document adocument = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(adocument, new FileOutputStream("OrderBill" +aOrders.getOrderId() + ".pdf"));
			Paragraph aParagraph = new Paragraph(aOrders.ForPrint() );
			adocument.open();
			adocument.add(aParagraph);
			
			
			adocument.close();
			writer.close();
			
			File file = new File("C:\\Users\\Flaviu\\Desktop\\PT_assig\\pt2019_30423_cristian_flaviu_assignment_3\\Tema3\\OrderBill" + aOrders.getOrderId()+".pdf");
			Desktop desktop = Desktop.getDesktop();
			if (file.exists())
				desktop.open(file);
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void consoleMessage(String message)
	{
	JOptionPane.showMessageDialog(null, message);	
	}

	public static void main(String[] args) {
		Controller aController = new Controller();
	
	}
}
