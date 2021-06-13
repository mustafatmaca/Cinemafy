package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/login")
public class LoginView extends VerticalLayout {


    public LoginView() {
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(createI18n());

        loginForm.addLoginListener(e -> {
            boolean isAuthenticated = authenticate(e);
            if (isAuthenticated) {
                navigateToMainPage();
            } else {
                loginForm.setError(true);
            }
        });

        Button button = new Button("Sign Up");
        button.addClickListener(e -> {
           navigateToSignUpPage();
        });

        setAlignItems(Alignment.CENTER);

        add(loginForm, button);
    }

    private LoginI18n createI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());
        i18n.getForm().setTitle("Login");
        i18n.getForm().setSubmit("Login");
        i18n.getForm().setUsername("Email");
        return i18n;
    }

    private void navigateToSignUpPage() {
        UI.getCurrent().navigate("/register");
    }

    private boolean authenticate(AbstractLogin.LoginEvent e) {
        if (e.getUsername().equals("user") && e.getPassword().equals("password")){
            return true;
        }else {
            return false;
        }
    }

    private void navigateToMainPage() {
        UI.getCurrent().navigate("");
    }


}
