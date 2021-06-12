package com.cinemafy.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;


@Route("")
public class MainView extends AppLayout {

    public MainView() {
        String src = "https://mpng.subpng.com/20180621/ewt/kisspng-film-cinema-logo-photography-5b2c14e50ae561.8080959915296155890446.jpg";
        Image img = new Image(src, "Cinemafy");
        Tabs tabs = new Tabs(new Tab("Home"), new Tab("Movies"), new Tab("Tickets"),
                new Tab("Statistics"), new Tab("About"));
        img.setHeight("44px");
        addToNavbar(img, tabs);
    }

}

/*
    private final CinemaService cinemaService;
    private Grid<Cinema> grid = new Grid<>(Cinema.class);

    public MainView(CinemaService cinemaService){
        this.cinemaService = cinemaService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(grid);
        updateList();
    }

    private void updateList() {
        grid.setItems(cinemaService.findAll());
    }


    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("city", "name");
    }*/