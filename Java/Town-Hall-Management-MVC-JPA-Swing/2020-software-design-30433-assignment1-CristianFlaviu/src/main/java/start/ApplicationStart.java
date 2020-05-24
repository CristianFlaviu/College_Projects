package start;


import Gui.AdminMainView;
import Gui.AdminRequestView;
import Gui.AdminSortFilterRequest;
import Gui.LoginView;
import entity.Request;
import repository.DocumentRepo;
import repository.RequestRepo;

import java.util.List;


public class ApplicationStart {


    static  boolean checkPalindrome(String myString)
    {
        int len=myString.length()-1;

        for(int i=0;i<=myString.length()/2;i++)
        {
            if(myString.charAt(i) !=myString.charAt(len-i))
            {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        ApplicationStart applicationStart=new ApplicationStart();
        System.out.println(ApplicationStart.checkPalindrome("abba"));
       // LoginView loginView=new LoginView();
       // AdminSortFilterRequest adminRequestView=new AdminSortFilterRequest(new RequestRepo().findAllRequest(),new DocumentRepo().findAllDocuments());


    }
}
