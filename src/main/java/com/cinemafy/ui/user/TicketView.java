package com.cinemafy.ui.user;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.*;
import com.cinemafy.backend.services.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Route(value = "ticket", layout = MainView.class)
public class TicketView extends VerticalLayout {

    private final UserService userService;
    private final FilmService filmService;
    private final CinemaService cinemaService;
    private final SalonService salonService;
    private final SessionService sessionService;
    private final TicketService ticketService;
    private Grid<Ticket> ticketGrid = new Grid<>(Ticket.class);

    public TicketView(UserService userService, FilmService filmService, CinemaService cinemaService, SalonService salonService, SessionService sessionService, TicketService ticketService) {
        this.userService = userService;
        this.filmService = filmService;
        this.cinemaService = cinemaService;
        this.salonService = salonService;
        this.sessionService = sessionService;
        this.ticketService = ticketService;
        String ticketFilm = new String();
        User user = new User();

        setWidth("%100");
        setHeight("%100");
        setPadding(true);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        if (VaadinSession.getCurrent().getSession().getAttribute("TicketFilm") != null){
            ticketFilm = VaadinSession.getCurrent().getSession().getAttribute("TicketFilm").toString();
        }

        if (VaadinSession.getCurrent().getSession().getAttribute("LoggedInUserId") != null){
            user = userService.findUser(Long.valueOf(VaadinSession.getCurrent().getSession().getAttribute("LoggedInUserId").toString())).get();
        }

        //COMBOBOX
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

        //setValue to cbFilm, film's names
        List<String> filmName = new ArrayList<>();
        for (Film film : films) {
            filmName.add(film.getName());
        }
        cbFilm.setItems(filmName);
        cbFilm.setValue(ticketFilm);
        VaadinSession.getCurrent().getSession().setAttribute("TicketFilm", null);

        //setValue to cbCinema, cinema's names
        List<String> cinemaName = new ArrayList<>();
        for (Cinema cinema : cinemas) {
            cinemaName.add(cinema.getName());
        }
        cbCinema.setItems(cinemaName);

        //setValue to cbSession, sessions if film selected
        cbFilm.addValueChangeListener(e -> {
            List<String> sessionTime = new ArrayList<>();
            for (Session session : sessions) {
                sessionTime.add(session.getTime());
            }
            cbSession.setItems(sessionTime);
        });

        //setValue to cbSession and cbSalon, if cinema selected
        cbSalon.setEnabled(false);
        cbSession.setEnabled(false);
        cbCinema.addValueChangeListener(e -> {
            cbSalon.setEnabled(true);
            cbSession.setEnabled(true);

            List<String> sessionTime = new ArrayList<>();
            for (Session session : sessions) {
                sessionTime.add(session.getTime());
            }
            cbSession.setItems(sessionTime);

            List<String> salonNumber = new ArrayList<>();
            for (Salon salon : salons) {
                if (salon.getCinema().getName() == e.getValue())
                    salonNumber.add(salon.getNumber());
            }
            cbSalon.setItems(salonNumber);
        });

        //setValue to cbSession, if salon selected
        cbSalon.addValueChangeListener(e -> {
            List<String> sessionTime = new ArrayList<>();
            for (Session session : sessions) {
                if (cbFilm.getValue().equals("Dune")){
                    if (e.getValue().equals("1") && session.getTime().equals("9:00-12:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("3") && session.getTime().equals("12:00-15:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("2") && session.getTime().equals("15:00-18:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("4") && session.getTime().equals("18:00-21:00")){
                        sessionTime.add(session.getTime());
                    }
                }
                else if (cbFilm.getValue().equals("Interstellar")){
                    if (e.getValue().equals("1") && session.getTime().equals("12:00-15:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("3") && session.getTime().equals("9:00-12:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("2") && session.getTime().equals("18:00-21:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("4") && session.getTime().equals("15:00-18:00")){
                        sessionTime.add(session.getTime());
                    }
                }
                else if (cbFilm.getValue().equals("Lord Of The Rings: Two Tower")){
                    if (e.getValue().equals("1") && session.getTime().equals("15:00-18:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("3") && session.getTime().equals("18:00-21:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("2") && session.getTime().equals("9:00-12:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("4") && session.getTime().equals("12:00-15:00")){
                        sessionTime.add(session.getTime());
                    }
                }
                else if (cbFilm.getValue().equals("Eternals")){
                    if (e.getValue().equals("1") && session.getTime().equals("18:00-21:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("3") && session.getTime().equals("15:00-18:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("2") && session.getTime().equals("12:00-15:00")){
                        sessionTime.add(session.getTime());
                    }
                    else if (e.getValue().equals("4") && session.getTime().equals("9:00-12:00")){
                        sessionTime.add(session.getTime());
                    }
                }
            }
            cbSession.setItems(sessionTime);
        });

        //HEADER
        H1 h1 = new H1("Ticket");
        h1.setHeight("100px");

        H2 h2 = new H2("Your Tickets");
        h1.setHeight("80px");

        //DATEPICKER
        DatePicker valueDatePicker = new DatePicker();
        valueDatePicker.setWidth("400px");
        valueDatePicker.setMin(LocalDate.now());
        LocalDate now = LocalDate.now();
        valueDatePicker.setValue(now);

        //BUTTON
        Button btBuy = new Button("Buy a Ticket");
        btBuy.setWidth("200px");
        User finalUser = user;
        btBuy.addClickListener(e -> {
            if (!cbFilm.getValue().isEmpty() && !cbCinema.getValue().isEmpty() && !cbSalon.getValue().isEmpty() && !cbSession.getValue().isEmpty() && !valueDatePicker.isEmpty()){
                Ticket ticket = new Ticket();
                ticket.setFilm(cbFilm.getValue());
                ticket.setCinema(cbCinema.getValue());
                ticket.setSalon(cbSalon.getValue());
                ticket.setSession(cbSession.getValue());
                ticket.setDate(valueDatePicker.getValue());
                userService.save(finalUser);
                ticket.setUser(finalUser);
                ticketService.save(ticket);
                updateList(finalUser);
            }
        });

        //TICKET GRID
        configureGrid();
        updateList(user);

        //LAYOUT
        VerticalLayout title = new VerticalLayout(h1);
        title.setAlignItems(Alignment.START);

        VerticalLayout title1 = new VerticalLayout(h2);
        title1.setAlignItems(Alignment.START);

        VerticalLayout verticalLayout = new VerticalLayout(new HorizontalLayout(cbFilm,cbCinema), new HorizontalLayout(cbSalon, cbSession), new HorizontalLayout(valueDatePicker));
        verticalLayout.setAlignItems(Alignment.CENTER);

        VerticalLayout button = new VerticalLayout(btBuy);
        button.setAlignItems(Alignment.CENTER);

        add(title, verticalLayout, button, title1, ticketGrid);
    }

    private void updateList(User user) {
        ticketGrid.setItems(ticketService.findByUserId(user));
    }

    private void configureGrid() {
        ticketGrid.addClassName("ticket-grid");
        ticketGrid.setColumns("film", "cinema", "salon", "session", "date");
        ticketGrid.addComponentColumn(item -> createDeleteButton(ticketGrid, item)).setHeader("Actions");
    }

    private Button createDeleteButton(Grid<Ticket> ticketGrid, Ticket item) {
        @SuppressWarnings("unchecked")
        Button btnDelete = new Button("Delete");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Confirm Delete",
                    "Are you sure you want to cancel this ticket?", "Delete", confirmEvent -> {
                ticketService.delete(item);
                updateList(item.getUser());
            },
                    "Cancel", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("error primary");
            dialog.open();
        });
        return btnDelete;
    }

}