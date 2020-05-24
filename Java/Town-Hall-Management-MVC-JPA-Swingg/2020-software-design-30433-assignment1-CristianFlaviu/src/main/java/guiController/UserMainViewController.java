package guiController;

import Gui.HouseManagementView;
import Gui.RequestManagementView;
import repository.DocumentRepo;
import repository.HouseRepo;
import service.HouseService;
import service.UserService;


public class UserMainViewController {


    public void houseOperation(String usernanme)
    {
        HouseManagementView houseManagementView=new HouseManagementView(new UserService().getAllHouses(usernanme),usernanme);

    }

    public void requestOperation(String usernanme)
    {
        RequestManagementView requestManagementView=new RequestManagementView(usernanme,new DocumentRepo().findAllDocuments(),new HouseService().getAllHouseByUserId(usernanme));

    }
}
