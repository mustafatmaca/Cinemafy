package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/register")
public class RegisterView extends VerticalLayout {

    public RegisterView() {
        String src = "https://mpng.subpng.com/20180621/ewt/kisspng-film-cinema-logo-photography-5b2c14e50ae561.8080959915296155890446.jpg";
        Image img = new Image(src, "Cinemafy");
        H2 title = new H2("Sign Up");

        TextField firstNameField = new TextField("First Name");
        TextField lastNameField = new TextField("Last Name");
        TextField emailField = new TextField("Email");
        PasswordField passwordField = new PasswordField("Password");

        Button button = new Button("Sign Up");

        setAlignItems(Alignment.CENTER);
        setHorizontalComponentAlignment(Alignment.STRETCH, firstNameField, lastNameField,
                emailField, passwordField, button);
        setMaxWidth("380px");
        getStyle().set("margin", "0 auto");

        add(img, title, firstNameField, lastNameField, emailField, passwordField, button);
    }
}
