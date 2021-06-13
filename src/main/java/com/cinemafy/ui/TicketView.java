package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "ticket", layout = MainView.class)
public class TicketView extends VerticalLayout {

    public TicketView() {
        Label label = new Label("Ticket View");

        setAlignItems(Alignment.CENTER);

        add(label);
    }

}
