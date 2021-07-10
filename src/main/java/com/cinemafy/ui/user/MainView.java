package com.cinemafy.ui.user;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.ui.LoginTypeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@PWA(name = "Cinemafy", shortName = "Cinemafy")
@Theme(variant = Lumo.DARK)
public class MainView extends AppLayout implements AppShellConfigurator {

    public MainView() {

        if (VaadinSession.getCurrent().getSession().getAttribute("LoggedInUserId")==null) {
            if (UI.getCurrent() != null) {
                UI.getCurrent().getPage().setLocation("/login");
            }
        }

        H2 title = new H2("Cinemafy");
        title.setHeight("80px");

        Tab home = createMenuItem("Home", VaadinIcon.HOME, HomeView.class);

        Tab movies = createMenuItem("Movie", VaadinIcon.MOVIE, MovieView.class);

        Tab tickets = createMenuItem("Tickets", VaadinIcon.TICKET, TicketView.class);

        Tab statistics = createMenuItem("Statistics", VaadinIcon.ABACUS, StatisticView.class);

        Tab about = createMenuItem("About", VaadinIcon.ARCHIVE, AboutView.class);

        Tab logout = createMenuItem("Logout", VaadinIcon.EXIT, LoginTypeView.class);


        for (Tab tab : new Tab[] { home, movies, tickets, statistics, about, logout }) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }

        Tabs tabs = new Tabs(home, movies, tickets, statistics, about, logout);
        addToNavbar(title, tabs);
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