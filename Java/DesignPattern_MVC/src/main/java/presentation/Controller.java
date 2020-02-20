package presentation;

import java.awt.MenuItem;
import java.util.HashSet;

import javax.swing.JOptionPane;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItems;
import businessLayer.Order;
import businessLayer.Restaurant;
import dataLayer.FileToRead;

public class Controller {

	private Restaurant aRestaurant;
	private AdministratorView administratorView;
	private WaiterView waiterView;
	private FileToRead fileToRead;
	private Dashboard aDashboard;
	private ChefView aChefView;

	public Controller() {

		aRestaurant = new Restaurant();
		fileToRead = new FileToRead(aRestaurant);
		fileToRead.readFromFile();
		administratorView = new AdministratorView(aRestaurant.getMeniu());

		waiterView = new WaiterView(aRestaurant.getMeniu());
		aDashboard = new Dashboard();
		aDashboard.setVisible(true);
		aChefView = new ChefView();
		aRestaurant.addObserver(aChefView);

		initializeButtons();
	}

	public void initializeButtons() {

		administratorView.addBtnAddMenuItemACtionListener(a -> {

			String name = administratorView.getNameTextField();
			int price = 0;
			if (name.length() == 0) {
				JOptionPane.showMessageDialog(null, "Null name");
			} else {

				if (aRestaurant.getProduct(name) != null) {
					JOptionPane.showMessageDialog(null, "The product already exists");
				} else {

					try {
						 price = administratorView.getPricetextField();
						 MenuItems aBaseProduct = new BaseProduct(name, price);
							aRestaurant.addProductToMenu(aBaseProduct);
							administratorView.addItemToDeleteCombobox(name);
							administratorView.addItemToProductListComboBox(name);
							waiterView.comboBox.addItem(name);
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null,"wrong number format");
					}
					
					
					
				}
			}
		});

		administratorView.addviewBtnActionListener1(e -> {

			MenuItemsView aItemsView = new MenuItemsView(aRestaurant.getMenuProducts());
			aItemsView.setVisible(true);
		});

		administratorView.addProducttoListBtn(a -> {

			if (!administratorView.CheckIteminTheList(administratorView.getSelectedItemProductList())) {
				administratorView.addElementsToList(administratorView.getSelectedItemProductList());
			} else {
				JOptionPane.showMessageDialog(null, "the product already added to the list");
			}

		});

		administratorView.addBtnAddCompositeProduct(a -> {

			HashSet<MenuItems> aHashSet = new HashSet<>();
			for (int i = 0; i < administratorView.getLenghtList(); i++) {
				aHashSet.add(aRestaurant.getProduct(administratorView.getItemsFromListatIndex(i)));

			}

			if (aHashSet.size() == 0) {
				JOptionPane.showMessageDialog(null, "You have to add an object");
			} else {
				String name = administratorView.getCompositeProductName();

				if (aRestaurant.getProduct(name) != null) {
					JOptionPane.showMessageDialog(null, "the name is already used");
				}

				else {
					MenuItems aItems = new CompositeProduct(name, aHashSet);

					administratorView.addItemToDeleteCombobox(name);
					administratorView.addItemToProductListComboBox(name);
					waiterView.comboBox.addItem(name);

					aRestaurant.addProductToMenu(aItems);

					administratorView.DeleteList();
				}
			}

		});
		administratorView.addEditBtn(a -> {
			String name = administratorView.getCompositeProductName();
			if (aRestaurant.getProduct(name) == null) {
				JOptionPane.showMessageDialog(null, "the product is not in the list");

			} else {
				HashSet<MenuItems> aHashSet = new HashSet<>();
				for (int i = 0; i < administratorView.getLenghtList(); i++) {
					aHashSet.add(aRestaurant.getProduct(administratorView.getItemsFromListatIndex(i)));

				}

				if (aHashSet.size() == 0) {
					JOptionPane.showMessageDialog(null, "you have to add an element to the list");
				}

				aRestaurant.deleteProductFromMenu(name);
				MenuItems aItems = new CompositeProduct(name, aHashSet);
				aRestaurant.addProductToMenu(aItems);

			}
			administratorView.DeleteList();
		});
		administratorView.deleteBtnActionListener(a -> {

			String item = administratorView.getSelectedItemToDelte();
			administratorView.deleteComboDelte(item);
			administratorView.deleteComboList(item);
			waiterView.comboBox.removeItem(item);
			aRestaurant.deleteProductFromMenu(item);
		});

		waiterView.addToListBtn(a -> {
			String productName = waiterView.comboBox.getSelectedItem().toString();
			System.out.println(productName);
			waiterView.getModel().addElement(productName);

		});
		waiterView.addOrdesActionLinster(a -> {

			int table = waiterView.getTable();

			HashSet<MenuItems> ListaProduse = new HashSet<>();

			for (int i = 0; i < waiterView.getModel().getSize(); i++) {
				ListaProduse.add(aRestaurant.getProduct(waiterView.getModel().getElementAt(i).toString()));
			}
			aRestaurant.newOrder(table, ListaProduse);
			waiterView.getModel().removeAllElements();
		});

		waiterView.addViewallOrdersBtn(a -> {
			OrderView aOrderView = new OrderView(aRestaurant.getAllOrders());
			aOrderView.setVisible(true);

		});

		aDashboard.AdministratorBtnActionListener(a -> {
			administratorView.setVisible(true);
			waiterView.setVisible(false);
			aChefView.setVisible(false);

		});

		aDashboard.waiterBtnActionLister(a -> {
			waiterView.setVisible(true);
			administratorView.setVisible(false);
			aChefView.setVisible(false);

		});

		aDashboard.chefViewActionLister(a -> {
			aChefView.setVisible(true);
			administratorView.setVisible(false);
			waiterView.setVisible(false);

		});
	}

	public static void main(String[] args) {
		Controller aController = new Controller();
	}

}
