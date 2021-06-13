package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "movie", layout = MainView.class)
public class MovieView extends VerticalLayout {

    public MovieView() {
        Label label = new Label("Movie View");

        setAlignItems(Alignment.CENTER);

        add(label);
    }

}
