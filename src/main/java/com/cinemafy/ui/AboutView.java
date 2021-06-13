package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "about", layout = MainView.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        Label label = new Label("About View");

        setAlignItems(Alignment.CENTER);

        add(label);
    }

}
