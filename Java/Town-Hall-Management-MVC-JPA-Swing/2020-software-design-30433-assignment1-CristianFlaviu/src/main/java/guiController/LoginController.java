package guiController;

import Gui.AdminMainView;
import Gui.LoginView;
import Gui.NewUserView;
import Gui.UserMainView;
import entity.User;
import exception.EmptyFieldException;
import exception.IllegalNameException;
import messages.RootMessages;
import service.UserService;
import validators.UserValidator;

import javax.swing.*;


public class LoginController {
    private UserService userService = new UserService();


    public void doLogin(LoginView loginView, String email, String password) {


        if (email.equals("")) {
            popUpMessage(loginView, RootMessages.NULL_FIELD + " email");

        } else if (password.equals("")) {
            popUpMessage(loginView, RootMessages.NULL_FIELD + " password");

        } else {
            User user = userService.getUSerByEmail(email);
            if (user != null) {
                if (user.getPassword().equals(password)) {

                    if(!email.contains("<admin>"))
                    {
                        loginView.makeViewInvisible();
                        UserMainView userMainView = new UserMainView(userService.getUSerByEmail(email).getId());
                        JOptionPane.showMessageDialog(null,RootMessages.SUCCES);
                    }
                    else
                    {   loginView.makeViewInvisible();
                        AdminMainView adminMainView=new AdminMainView();

                    }

                } else {

                    popUpMessage(loginView, RootMessages.WRONG_COMBIATION);
                }
            } else {
                popUpMessage(loginView, RootMessages.USERNAME_INEXISTENT);
            }
        }

    }


    public void changeNewUserWindow(LoginView loginView) {

        loginView.makeViewInvisible();
        NewUserView newUserView=new NewUserView();
    }


    public void popUpMessage(LoginView loginView, String message) {
        loginView.popUpMessage(message);
    }

    public void saveUserBtn(NewUserView newUserView, User user) {
        try {
            UserValidator.validateNewUser(user);
            String email= user.getEmail();

            if (userService.getUSerByEmail(email) == null) {

                newUserView.popUpMessage("Success,Thank you for your registration");
                userService.insertNewUser(user);

                LoginView loginView = new LoginView();
                newUserView.makeInvisibleView();

            }
            else
            {
                newUserView.popUpMessage("you already have an account on this email");

            }
        } catch (EmptyFieldException e) {
            newUserView.popUpMessage(RootMessages.NULL_FIELD);

        } catch (IllegalNameException e1) {
            newUserView.popUpMessage(RootMessages.ILLEGAL_CARACTERS_ON_USERNAME);
        }


    }


    }
