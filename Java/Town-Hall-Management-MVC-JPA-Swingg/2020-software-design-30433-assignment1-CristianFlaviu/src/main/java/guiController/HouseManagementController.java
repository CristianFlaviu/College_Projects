package guiController;

import Gui.HouseManagementView;
import entity.House;
import entity.Request;
import entity.User;
import exception.DuplicateKeyException;
import exception.EmptyFieldException;
import messages.RootMessages;
import repository.HouseRepo;
import service.HouseService;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class HouseManagementController {


    HouseService houseService = new HouseService();
    UserService userService = new UserService();

    public void AddNewHouse(HouseManagementView houseManagementView, JTable a, String house_id, String house_adress) {

        User current_user = userService.getUser(houseManagementView.getUsername());
        House house = new House(house_id, house_adress, current_user);

        try {
            houseService.insertNewHouse(house);
            DefaultTableModel model = (DefaultTableModel) a.getModel();
            model.insertRow(model.getRowCount(), new Object[]{house_id, house_adress});

        } catch (DuplicateKeyException e) {

            JOptionPane.showMessageDialog(null, RootMessages.DUPLICATE_ENTRY_HOUSE);

        }
        catch (EmptyFieldException e)
        {
            JOptionPane.showMessageDialog(null,RootMessages.NULL_FIELD);
        }

    }

    public void removeHouse(JTable table, String houseId) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int row=table.getSelectedRow();


        try {
            houseService.removeHouse(houseId);
            model.removeRow(row);
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,RootMessages.ILLEGAL_DELETE);
        }

    }

}
