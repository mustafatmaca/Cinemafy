package com.cinemafy.ui;

import com.cinemafy.backend.models.Cinema;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("")
public class MainView extends VerticalLayout {

    private Grid<Cinema> grid = new Grid<>(Cinema.class);

    public MainView(){
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(grid);
    }


    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("city", "name");
    }

}