package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class HomeView extends VerticalLayout {

    public HomeView() {
        Label label = new Label("Home View");

        setAlignItems(Alignment.CENTER);

        add(label);
    }
}
