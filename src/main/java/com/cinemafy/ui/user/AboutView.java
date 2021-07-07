package com.cinemafy.ui.user;
/**
 * @author Mustafa Atmaca
 */

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "about", layout = MainView.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        //HEADER
        H1 h1 = new H1("About");
        h1.setHeight("100px");

        //CONTENT
        Label label = new Label("Cinemafy is a movie and theaters web app. Programming with Vaadin, Spring, H2 Database.");

        //LAYOUT
        VerticalLayout title = new VerticalLayout(h1);
        title.setAlignItems(Alignment.START);

        VerticalLayout paragraph = new VerticalLayout(label);
        paragraph.setAlignItems(Alignment.CENTER);

        add(title, paragraph);
    }

}
