package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.User;
import com.cinemafy.backend.services.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/register")
public class RegisterView extends VerticalLayout {
    private final UserService userService;

    public RegisterView(UserService userService) {
        this.userService = userService;
        H2 title = new H2("Sign Up");

        TextField firstNameField = new TextField("First Name");
        TextField lastNameField = new TextField("Last Name");
        TextField emailField = new TextField("Email");
        PasswordField passwordField = new PasswordField("Password");

        Button button = new Button("Sign Up");

        button.addClickListener(event -> {
            if (!firstNameField.getValue().isEmpty() && !lastNameField.getValue().isEmpty() && !emailField.getValue().isEmpty() && !passwordField.getValue().isEmpty()){
                register(firstNameField.getValue(), lastNameField.getValue(), emailField.getValue(), passwordField.getValue());
                UI.getCurrent().getPage().setLocation("/login");
            }
            else {
                Notification.show("Alanları Boş Bırakmayınız!");
            }
        });

        setAlignItems(Alignment.CENTER);
        setHorizontalComponentAlignment(Alignment.STRETCH, firstNameField, lastNameField,
                emailField, passwordField, button);
        setMaxWidth("380px");
        getStyle().set("margin", "0 auto");

        add(title, firstNameField, lastNameField, emailField, passwordField, button);
    }

    private void register(String fn, String ln, String mail, String pw) {
        User user = new User();
        user.setFirstName(fn);
        user.setLastName(ln);
        user.setEmail(mail);
        user.setPassword(pw);
        userService.save(user);
    }
}
