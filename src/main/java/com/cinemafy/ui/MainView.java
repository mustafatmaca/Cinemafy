package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;


@PWA(name = "Cinemafy", shortName = "Cinemafy")
public class MainView extends AppLayout implements AppShellConfigurator {

    public MainView() {
        System.out.println("MainView");

        if (VaadinSession.getCurrent().getSession().getAttribute("LoggedInUserId")==null) {
            if (UI.getCurrent() != null) {
                UI.getCurrent().getPage().setLocation("/login");
            }
        }

        String src = "https://mpng.subpng.com/20180621/ewt/kisspng-film-cinema-logo-photography-5b2c14e50ae561.8080959915296155890446.jpg";
        Image img = new Image(src, "Cinemafy");

        Tab home = createMenuItem("Home", VaadinIcon.HOME, HomeView.class);

        Tab movies = createMenuItem("Movie", VaadinIcon.MOVIE, MovieView.class);

        Tab tickets = createMenuItem("Tickets", VaadinIcon.TICKET, TicketView.class);

        Tab statistics = createMenuItem("Statistics", VaadinIcon.ABACUS, StatisticView.class);

        Tab about = createMenuItem("About", VaadinIcon.ARCHIVE, AboutView.class);


        for (Tab tab : new Tab[] { home, movies, tickets, statistics, about }) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }

        Tabs tabs = new Tabs(home, movies, tickets, statistics, about);
        img.setHeight("44px");
        addToNavbar(img, tabs);
    }

    private Tab createMenuItem(String title, VaadinIcon icon, Class<? extends Component> target) {
        RouterLink link = new RouterLink(null,target);
        if (icon != null) link.add(icon.create());
        link.add(title);
        Tab tab = new Tab();
        tab.add(link);
        return tab;
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