package guiController;

import Utils.ConvertReqStateToString;
import Utils.DateFormatter;
import entity.Document;
import entity.House;
import entity.Request;

import entity.User;
import exception.EmptyFieldException;
import messages.RootMessages;
import service.DocumentService;
import service.RequestService;
import service.UserService;
import validators.RequestValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;


public class RequestManagementCotroller {

    DocumentService documentService = new DocumentService();
    RequestService requestService = new RequestService();
    UserService userService = new UserService();


    public Set<Request> getAllRequestByUserId(String user_Id) {
        return requestService.getAllRequestByUserId(user_Id);
    }

    public void addBtnRequest(DefaultTableModel table, String title, String userID, Document document, House house) {


        User currrent_user = userService.getUser(userID);
        Request request = new Request(title, currrent_user, document, house);

        try {
            RequestValidator.requestValidator(request);
            int year = DateFormatter.getYear(request.getDate());

            House reqHouse = request.getHouse();
            int count = 0;
            for (Request a : currrent_user.getRequests()) {
                int a_year = DateFormatter.getYear(a.getDate());


                if (a_year == year && reqHouse.getId().equals(a.getHouse().getId())) {
                    count++;

                }
            }

            if (count >= 3) {
                JOptionPane.showMessageDialog(null, "to many request for this house");

            } else {
                table.insertRow(table.getRowCount(), new Object[]{request.getId(), request.getTitle(), new SimpleDateFormat("MM-dd-yyyy").format(request.getDate()), request.getUser().getFirstName(), request.getDocument().getNume(), ConvertReqStateToString.convert(request.getRequestState()), house.getAdress()});
                requestService.insertNewRequest(request);
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "NULL Request");
        } catch (EmptyFieldException e1) {
            JOptionPane.showMessageDialog(null, RootMessages.NULL_FIELD);
        }


    }

    public void deleteBtnRequest(JTable table) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();

        int selected_row = table.getSelectedRow();

        if (selected_row >= 0) {
            String requestID = (String) defaultTableModel.getValueAt(selected_row, 0);


            try {
                requestService.removeRequest(requestID);
                defaultTableModel.removeRow(selected_row);
            }catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,RootMessages.ILLEGAL_DELETE);
            }



        }

    }

    public void updateBtn(JTable table, String title, Document document, House house) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();

        int row = table.getSelectedRow();
        int currentRow = 0;
        System.out.println(row);
        if (row >= 0) {
            String requestID = (String) defaultTableModel.getValueAt(row, 0);

            Request request = requestService.getById(requestID);
            request.setDocument(document);
            request.setTitle(title);
            request.setHouse(house);
            requestService.updateRequest(request);

            defaultTableModel.setValueAt(title, row, 1);
            defaultTableModel.setValueAt(document.getNume(), row, 4);
            defaultTableModel.setValueAt(house.getAdress(), row, 6);

        }


    }


}
