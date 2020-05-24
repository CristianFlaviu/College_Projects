package guiController;

import Gui.AdminDocumentManagerView;
import Gui.AdminRequestView;
import Gui.AdminSortFilterRequest;
import Gui.AdminViewUserView;
import Utils.ConvertReqStateToString;
import Utils.RequestState;
import entity.Document;
import entity.Request;

import exception.DuplicateKeyException;
import exception.EmptyFieldException;
import messages.RootMessages;
import service.DocumentService;
import service.RequestService;
import service.UserService;

import javax.persistence.RollbackException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

public class AdminController {

    UserService userService = new UserService();
    DocumentService documentService = new DocumentService();
    RequestService requestService = new RequestService();


    public void addNewDocumentBtn(DefaultTableModel model, Document document) {


        try {
            documentService.insertNewDocument(document);
            model.insertRow(model.getRowCount(), new Object[]{document.getId(), document.getNume(), document.getType()});
        } catch (DuplicateKeyException e) {
            JOptionPane.showMessageDialog(null, RootMessages.DUPLICATE_ENTRY_DOCUMENT);
        } catch (EmptyFieldException e) {
            JOptionPane.showMessageDialog(null, RootMessages.NULL_FIELD);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "null data");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void deleteDocummentBtn(JTable table) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();

        int row = table.getSelectedRow();
        if (row >= 0) {
            String documentId = (String) defaultTableModel.getValueAt(row, 0);

            try {
                documentService.removeDocument(documentId);
                defaultTableModel.removeRow(row);
            } catch (RollbackException e) {

                {
                    JOptionPane.showMessageDialog(null, RootMessages.ILLEGAL_DELETE);

                }

            }

        }

    }

    public void updateDocumentBtn(JTable table, Document document) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();

        int currentRow = table.getSelectedRow();

        if (currentRow >= 0) {

            documentService.updateDocument(document);

            defaultTableModel.setValueAt(document.getId(), currentRow, 0);
            defaultTableModel.setValueAt(document.getNume(), currentRow, 1);
            defaultTableModel.setValueAt(document.getType(), currentRow, 2);
        }

    }


    public void mainViewViewUsers() {
        new AdminViewUserView(userService.getAllUser());
    }

    public void mainViewDocumentsBtn() {
        new AdminDocumentManagerView(documentService.getAllDocuments());
    }

    public void mainViewRequestBtn() {
        new AdminRequestView(requestService.getAllrequest(), documentService.getAllDocuments());
    }

    public void deleteBtnRequest(JTable table) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();

        int selected_row = table.getSelectedRow();

        if (selected_row > 0) {
            String requestID = (String) defaultTableModel.getValueAt(selected_row, 0);

            defaultTableModel.removeRow(selected_row);
            requestService.removeRequest(requestID);

        }

    }


    public void validateBtn(JTable table, Boolean bool) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        int selected_row = table.getSelectedRow();

        if (selected_row >= 0) {
            String requestId = (String) defaultTableModel.getValueAt(selected_row, 0);
            Request request = requestService.getById(requestId);
            if (bool) {
                request.setRequestState(RequestState.Approved);
                defaultTableModel.setValueAt("Approved", selected_row, 5);

            } else {
                request.setRequestState(RequestState.NOTApproved);
                defaultTableModel.setValueAt("NotApproved", selected_row, 5);
            }

            requestService.updateRequest(request);

        }

    }

    public void sortByDateBtn(JTable table) {
        List<Request> requests = requestService.getAllRequestSortedByDate();
        String[] columnNames = {"RequestID", "Title", "Date", "User", "Document", "RequestState"};

        DefaultTableModel defaultTableModel= (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);

        String[][] data = new String[requests.size()][6];
        int row = 0;
        for (Request a : requests) {
            data[row][0] = a.getId();
            data[row][1] = a.getTitle();

            data[row][2] = new SimpleDateFormat("MM-dd-yyyy").format(a.getDate());
            data[row][3] = a.getUser().getFirstName();
            data[row][4] = a.getDocument().getNume();
            data[row][5] = ConvertReqStateToString.convert(a.getRequestState());

            defaultTableModel.addRow(data[row]);
            row++;

        }

        TableModel tableModel = new DefaultTableModel(data, columnNames);


        table = new JTable(tableModel) ;

    }


    public void sortByDocumettype(JTable table) {
        List<Request> requests = requestService.getAllRequestFilteredByDocumentTitle();
        String[] columnNames = {"RequestID", "Title", "Date", "User", "Document", "RequestState"};

        DefaultTableModel defaultTableModel= (DefaultTableModel) table.getModel();
        defaultTableModel.setRowCount(0);

        String[][] data = new String[requests.size()][6];
        int row = 0;
        for (Request a : requests) {
            data[row][0] = a.getId();
            data[row][1] = a.getTitle();

            data[row][2] = new SimpleDateFormat("MM-dd-yyyy").format(a.getDate());
            data[row][3] = a.getUser().getFirstName();
            data[row][4] = a.getDocument().getNume();
            data[row][5] = ConvertReqStateToString.convert(a.getRequestState());

            defaultTableModel.addRow(data[row]);
            row++;

        }

        TableModel tableModel = new DefaultTableModel(data, columnNames);


        table = new JTable(tableModel) ;

    }

    public void filterByDateBtn(JTable table,String  date)
    {
        try {
            int month=Integer.valueOf(date.substring(0,2));
            int year=Integer.valueOf(date.substring(4,7));
            System.out.println(month+" "+year);

            List<Request> requests = requestService.getAllRequestFilteredByDate(year,month);
            String[] columnNames = {"RequestID", "Title", "Date", "User", "Document", "RequestState"};

            DefaultTableModel defaultTableModel= (DefaultTableModel) table.getModel();
            defaultTableModel.setRowCount(0);

            String[][] data = new String[requests.size()][6];
            int row = 0;
            for (Request a : requests) {
                data[row][0] = a.getId();
                data[row][1] = a.getTitle();

                data[row][2] = new SimpleDateFormat("MM-dd-yyyy").format(a.getDate());
                data[row][3] = a.getUser().getFirstName();
                data[row][4] = a.getDocument().getNume();
                data[row][5] = ConvertReqStateToString.convert(a.getRequestState());

                defaultTableModel.addRow(data[row]);
                row++;

            }

            TableModel tableModel = new DefaultTableModel(data, columnNames);


            table = new JTable(tableModel) ;
        }catch (StringIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(null,"too short format");
        }catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,"wrong format for the date");
        }
    }

    public void mainViewSortFilterBtn()
    {
        new AdminSortFilterRequest(requestService.getAllrequest(),documentService.getAllDocuments());
    }
}
