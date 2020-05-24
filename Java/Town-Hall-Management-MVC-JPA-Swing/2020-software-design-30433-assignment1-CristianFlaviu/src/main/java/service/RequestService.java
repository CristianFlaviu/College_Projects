package service;

import entity.Request;
import repository.RequestRepo;

import java.util.List;
import java.util.Set;

public class RequestService {

    private RequestRepo requestRepo=new RequestRepo();

    public void insertNewRequest(Request request)
    {
        requestRepo.insertNewRequest(request);
    }

    public Set<Request> getAllRequestByUserId(String user_Id)
    {
        return requestRepo.findAllRequestByUserId(user_Id);
    }

    public void removeRequest(String requestID)
    {
        requestRepo.removeRequest(requestID);
    }

    public  void updateRequest(Request request)
    {
        requestRepo.updateRequest(request);
    }

    public Request getById(String request_id)
    {
       return requestRepo.findById(request_id);
    }
    public Set<Request> getAllrequest()
    {
        return  requestRepo.findAllRequest();
    }

    public List<Request>  getAllRequestSortedByDate()
    {
        return  requestRepo.findAllRequestSortByDate();
    }

    public List<Request> getAllRequestFilteredByDate(int year,int date)
    {
        return  requestRepo.findAllRequestFileteredByDate(year,date);
    }

    public List<Request> getAllRequestFilteredByDocumentTitle()
    {
        return  requestRepo.findAllRequestSortedByDocumentType();
    }

}
