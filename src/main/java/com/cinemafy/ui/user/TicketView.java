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
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;
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
    FormLayout ticketForm = new FormLayout();
    Binder<Ticket> ticketBinder = new Binder<>();
    Film ticketFilm;
    User user;

    public TicketView(UserService userService, FilmService filmService, CinemaService cinemaService, SalonService salonService, SessionService sessionService, TicketService ticketService) {
        this.userService = userService;
        this.filmService = filmService;
        this.cinemaService = cinemaService;
        this.salonService = salonService;
        this.sessionService = sessionService;
        this.ticketService = ticketService;

        setWidth("%100");
        setHeight("%100");
        setPadding(true);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        if (VaadinSession.getCurrent().getSession().getAttribute("TicketFilm") != null){
            ticketFilm = filmService.findByName(VaadinSession.getCurrent().getSession().getAttribute("TicketFilm").toString());
        }

        if (VaadinSession.getCurrent().getSession().getAttribute("LoggedInUserId") != null){
            user = userService.findUser(Long.valueOf(VaadinSession.getCurrent().getSession().getAttribute("LoggedInUserId").toString())).get();
        }

        configureForm(ticketForm, user);

        //COMBOBOX
        List<Film> films = filmService.findAll();
        List<Cinema> cinemas = cinemaService.findAll();

        ComboBox<Film> cbFilm = new ComboBox<>("Film");
        cbFilm.setWidth("400px");
        cbFilm.setItems(films);
        cbFilm.setItemLabelGenerator(Film::getName);
        ComboBox<Cinema> cbCinema = new ComboBox<>("Cinema");
        cbCinema.setWidth("400px");
        cbCinema.setItems(cinemas);
        cbCinema.setItemLabelGenerator(Cinema::getName);
        ComboBox<Salon> cbSalon = new ComboBox<>("Salon");cbSalon.setEnabled(false);
        cbSalon.setWidth("400px");
        ComboBox<Session> cbSession = new ComboBox<>("Session");cbSession.setEnabled(false);
        cbSession.setWidth("400px");

        //setValue to cbFilm, film's names
        cbFilm.setValue(ticketFilm);
        VaadinSession.getCurrent().getSession().setAttribute("TicketFilm", null);

        //setValue to cbSalon, if cinema selected
        cbCinema.addValueChangeListener(e -> {
            cbSalon.setEnabled(true);
            List<Salon> salonNumber = salonService.findByCinema(e.getValue());
            cbSalon.setItems(salonNumber);
            cbSalon.setItemLabelGenerator(salon -> salon.getNumber());
        });

        //setValue to cbSession, if salon selected
        cbSalon.addValueChangeListener(e -> {
            cbSession.setEnabled(true);
            List<Session> sessions = sessionService.findBySalonAndFilm(e.getValue(), cbFilm.getValue());
            cbSession.setItems(sessions);
            cbSession.setItemLabelGenerator(session -> session.getStartTime().toString());
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
            if (!cbFilm.getValue().toString().isEmpty() && !cbCinema.getValue().toString().isEmpty() && !cbSalon.getValue().toString().isEmpty() && !cbSession.getValue().toString().isEmpty() && !valueDatePicker.isEmpty()){
                Ticket ticket = new Ticket();
                ticket.setDate(valueDatePicker.getValue());
                ticket.setSession(cbSession.getValue());
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

        add(title, ticketForm, title1, ticketGrid);
    }

    private void configureForm(FormLayout ticketForm, User user) {
        ComboBox<Film> cbFilm = new ComboBox<>("Film");
        cbFilm.setItemLabelGenerator(Film::getName);
        ComboBox<Cinema> cbCinema = new ComboBox<>("Cinema");cbCinema.setEnabled(false);
        cbCinema.setItemLabelGenerator(Cinema::getName);
        ComboBox<Salon> cbSalon = new ComboBox<>("Salon");cbSalon.setEnabled(false);
        cbSalon.setItemLabelGenerator(Salon::getNumber);
        ComboBox<Session> cbSession = new ComboBox<>("Session");cbSession.setEnabled(false);
        cbSession.setItemLabelGenerator(session -> session.getStartTime().toString());
        DatePicker datePicker = new DatePicker();
        datePicker.setMin(LocalDate.now());
        LocalDate now = LocalDate.now();
        datePicker.setValue(now);

        cbFilm.setItems(filmService.findAll());
        cbFilm.addValueChangeListener(e -> {
            cbCinema.setEnabled(true);
            cbCinema.setItems(cinemaService.findAll());
        });
        cbCinema.setItems(cinemaService.findAll());
        cbCinema.addValueChangeListener(cinema -> {
            cbSalon.setEnabled(true);
            List<Salon> salonNumber = salonService.findByCinema(cinema.getValue());
            cbSalon.setItems(salonNumber);
        });
        cbSalon.addValueChangeListener(salon -> {
            cbSession.setEnabled(true);
            List<Session> sessions =sessionService.findBySalonAndFilm(salon.getValue(), cbFilm.getValue());
            cbSession.setItems(sessions);
        });

        ticketBinder.forField(cbSession).bind(Ticket::getSession, Ticket::setSession);
        ticketBinder.forField(datePicker).bind(Ticket::getDate, Ticket::setDate);


        Button btnBuy = new Button("Buy a Ticket");
        btnBuy.setWidth("200px");
        btnBuy.addClickListener(e -> {
            Ticket ticket = new Ticket();

            try {
                ticketBinder.writeBean(ticket);
            } catch (ValidationException validationException) {
                validationException.printStackTrace();
            }

            ticket.setUser(user);
            ticketService.save(ticket);
            updateList(user);
        });

        ticketForm.add(cbFilm,cbCinema,cbSalon,cbSession, datePicker, btnBuy);

    }

    private void updateList(User user) {
        ticketGrid.setItems(ticketService.findByUserId(user));
    }

    private void configureGrid() {
        ticketGrid.addClassName("ticket-grid");
        ticketGrid.setColumns("session.film.name", "session.salon.cinema.name", "session.salon.number", "session.startTime", "session.endTime", "date");
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
