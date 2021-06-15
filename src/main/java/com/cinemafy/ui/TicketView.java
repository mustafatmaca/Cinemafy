package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.models.Salon;
import com.cinemafy.backend.models.Session;
import com.cinemafy.backend.services.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;

@Route(value = "ticket", layout = MainView.class)
public class TicketView extends VerticalLayout {

    private final UserService userService;
    private final FilmService filmService;
    private final CinemaService cinemaService;
    private final SalonService salonService;
    private final SessionService sessionService;

    public TicketView(UserService userService, FilmService filmService, CinemaService cinemaService, SalonService salonService, SessionService sessionService) {
        System.out.println("TicketView");
        this.userService = userService;
        this.filmService = filmService;
        this.cinemaService = cinemaService;
        this.salonService = salonService;
        this.sessionService = sessionService;
        String ticketFilm = new String();

        setWidth("%100");
        setHeight("%100");
        setPadding(true);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        if (VaadinSession.getCurrent().getSession().getAttribute("TicketFilm") != null){
            System.out.println(VaadinSession.getCurrent().getSession().getAttribute("TicketFilm").toString());
            ticketFilm = VaadinSession.getCurrent().getSession().getAttribute("TicketFilm").toString();
        }

        List<Film> films = filmService.findAll();
        List<Cinema> cinemas = cinemaService.findAll();
        List<Salon> salons = salonService.findAll();
        List<Session> sessions = sessionService.findAll();
        ComboBox<String> cbFilm = new ComboBox<>();
        cbFilm.setLabel("Film");
        cbFilm.setWidth("400px");
        ComboBox<String> cbCinema = new ComboBox<>();
        cbCinema.setLabel("Cinema");
        cbCinema.setWidth("400px");
        ComboBox<String> cbSalon = new ComboBox<>();
        cbSalon.setLabel("Salon");
        cbSalon.setWidth("400px");
        ComboBox<String> cbSession = new ComboBox<>();
        cbSession.setLabel("Session");
        cbSession.setWidth("400px");

        List<String> filmName = new ArrayList<>();
        for (Film film : films) {
            filmName.add(film.getName());
        }
        cbFilm.setItems(filmName);
        cbFilm.setValue(ticketFilm);
        VaadinSession.getCurrent().getSession().setAttribute("TicketFilm", null);
        ticketFilm = "";

        List<String> cinemaName = new ArrayList<>();
        for (Cinema cinema : cinemas) {
            cinemaName.add(cinema.getName());
        }
        cbCinema.setItems(cinemaName);

        List<String> salonNumber = new ArrayList<>();
        for (Salon salon : salons) {
            salonNumber.add(salon.getNumber());
        }
        cbSalon.setItems(salonNumber);

        List<String> sessionTime = new ArrayList<>();
        for (Session session : sessions) {
            sessionTime.add(session.getTime());
        }
        cbSession.setItems(sessionTime);

        H1 h1 = new H1("Ticket");
        h1.setHeight("100px");

        Button btBuy = new Button("Buy a Ticket");
        btBuy.setWidth("200px");
        btBuy.addClickListener(e -> {
            Notification.show("Bilet Alındı");
        });

        VerticalLayout title = new VerticalLayout(h1);
        title.setAlignItems(Alignment.START);

        VerticalLayout verticalLayout = new VerticalLayout(new HorizontalLayout(cbFilm,cbCinema), new HorizontalLayout(cbSalon, cbSession));
        verticalLayout.setAlignItems(Alignment.CENTER);

        VerticalLayout button = new VerticalLayout(btBuy);
        button.setAlignItems(Alignment.CENTER);

        add(title, verticalLayout, button);
    }

}
