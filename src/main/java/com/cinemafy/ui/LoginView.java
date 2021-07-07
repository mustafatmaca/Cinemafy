package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.User;
import com.cinemafy.backend.services.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("/userlogin")
public class LoginView extends VerticalLayout {

    private final UserService userService;

    public LoginView(UserService userService) {
        this.userService = userService;

        //HEADER
        H1 header = new H1("Cinemafy");

        VerticalLayout title = new VerticalLayout(header);
        title.setAlignItems(Alignment.START);

        //LOGINFORM
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(createI18n());

        loginForm.addLoginListener(e -> {
            User result = userService.login(e.getUsername(),e.getPassword());

            if (result.getId()!=null)
            {
                if (VaadinSession.getCurrent().getSession().getAttribute("LoginType").toString().equals("Admin")){
                    VaadinSession.getCurrent().getSession().setAttribute("LoggedInUserId",result.getId());
                    UI.getCurrent().getPage().setLocation("/administrator");
                }
                else if (VaadinSession.getCurrent().getSession().getAttribute("LoginType").toString().equals("User")){
                    VaadinSession.getCurrent().getSession().setAttribute("LoggedInUserId",result.getId());
                    UI.getCurrent().getPage().setLocation("/");
                }
            }else
            {
                loginForm.setError(true);
            }
        });

        //SIGNUP BUTTON
        Button button = new Button("Sign Up");
        button.addClickListener(e -> {
           navigateToSignUpPage();
        });

        setAlignItems(Alignment.CENTER);

        add(title, loginForm, button);
    }

    //LoginForm Content
    private LoginI18n createI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());
        i18n.getForm().setTitle("User Login");
        i18n.getForm().setSubmit("Login");
        i18n.getForm().setUsername("Email");
        return i18n;
    }

    //Navigate SignUp Page
    private void navigateToSignUpPage() {
        UI.getCurrent().navigate("/register");
    }

}
