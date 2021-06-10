package com.cinemafy.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("/main")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("Hello");
        add(button);
    }

}
