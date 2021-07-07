package com.cinemafy.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("/login")
public class LoginTypeView extends VerticalLayout {

    public LoginTypeView() {


        //HEADER
        H1 header = new H1("Cinemafy");

        VerticalLayout title = new VerticalLayout(header);
        title.setAlignItems(Alignment.START);


        Button systemUserBtn = new Button("Admin Login");
        systemUserBtn.setWidth("400px");
        systemUserBtn.addClickListener(event -> {
            VaadinSession.getCurrent().getSession().setAttribute("LoginType", "Admin");
            UI.getCurrent().getPage().setLocation("/userlogin");
        });

        Button userBtn = new Button("User Login");
        userBtn.setWidth("400px");
        userBtn.addClickListener(event -> {
            VaadinSession.getCurrent().getSession().setAttribute("LoginType", "User");
            UI.getCurrent().getPage().setLocation("/userlogin");
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(systemUserBtn, userBtn);
        horizontalLayout.setAlignItems(Alignment.CENTER);

        setAlignItems(Alignment.CENTER);

        add(header, horizontalLayout);
    }
}
