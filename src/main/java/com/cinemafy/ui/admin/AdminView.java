package com.cinemafy.ui.admin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

@Route("/administrator")
public class AdminView extends AppLayout {

    public AdminView() {

        if (VaadinSession.getCurrent().getSession().getAttribute("LoggedInUserId")==null) {
            if (UI.getCurrent() != null) {
                UI.getCurrent().getPage().setLocation("/login");
            }
        }

        setPrimarySection(AppLayout.Section.DRAWER);
        H2 title = new H2("Cinemafy");

        Tab cinema = createMenuItem("Cinema", CinemaAndSalonView.class);

        Tab film = createMenuItem("Film", FilmAndCategoryView.class);

        Tab session = createMenuItem("Session", SessionView.class);

        addToNavbar(new DrawerToggle(), title);
        Tabs tabs = new Tabs(cinema, film, session);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(tabs);
    }

    private Tab createMenuItem(String title, Class<? extends Component> target) {
        RouterLink link = new RouterLink(null,target);
        link.add(title);
        Tab tab = new Tab();
        tab.add(link);
        return tab;
    }

}
