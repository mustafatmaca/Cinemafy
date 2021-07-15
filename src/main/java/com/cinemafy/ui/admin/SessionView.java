package com.cinemafy.ui.admin;

import com.cinemafy.backend.models.*;
import com.cinemafy.backend.services.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Route(value = "session", layout = AdminView.class)
public class SessionView extends VerticalLayout {

    private final SessionService sessionService;
    private final CinemaService cinemaService;
    private final SalonService salonService;
    private final FilmService filmService;
    private final TicketService ticketService;
    private Grid<Session> sessionGrid = new Grid<>(Session.class);
    TextField tfFilmFilter = new TextField();
    TextField tfCinemaFilter = new TextField();
    Dialog dialogSession = new Dialog();
    Dialog dialogNewSession = new Dialog();
    Binder<Session> sessionBinder = new Binder<>();
    Binder<Session> sessionBinder2 = new Binder<>();
    Long itemId = 0L;

    public SessionView(SessionService sessionService, CinemaService cinemaService, SalonService salonService, FilmService filmService, TicketService ticketService) {
        this.sessionService = sessionService;
        this.cinemaService = cinemaService;
        this.salonService = salonService;
        this.filmService = filmService;
        this.ticketService = ticketService;
        H1 session = new H1("Session");

        configureSessionDialog(dialogSession);
        configureNewSessionDialog(dialogNewSession);

        configureGrid();
        updateList();

        //FILTER
        tfFilmFilter.setPlaceholder("Film");
        tfCinemaFilter.setPlaceholder("Cinema");
        Button btnFilmFilter = new Button("Search", VaadinIcon.SEARCH.create());
        btnFilmFilter.addClickListener(event -> {
            updateByFilmFilter(tfFilmFilter.getValue());
        });
        Button btnCinemaFilter = new Button("Search", VaadinIcon.SEARCH.create());
        btnCinemaFilter.addClickListener(event -> {
            updateByCinemaFilter(tfCinemaFilter.getValue());
        });

        HorizontalLayout sessionFilter = new HorizontalLayout();
        sessionFilter.add(tfFilmFilter,btnFilmFilter, tfCinemaFilter,btnCinemaFilter);

        Button btnNewSession = new Button("New Session");
        btnNewSession.addClickListener(buttonClickEvent -> {
            itemId = 0L;
            sessionBinder2.readBean(new Session());
            dialogNewSession.open();
        });

        add(session, btnNewSession, sessionFilter, sessionGrid);

    }

    private void configureNewSessionDialog(Dialog dialog) {
        dialog.setModal(true);
        ComboBox<Film> cbFilm = new ComboBox("Film");
        ComboBox<Salon> cbSalon = new ComboBox("Salon");
        ComboBox<Cinema> cbCinema = new ComboBox("Cinema");
        TimePicker tpStart = new TimePicker("Start Time");tpStart.setMinTime(LocalTime.parse("09:00"));
        TimePicker tpEnd = new TimePicker("End Time");tpEnd.isReadOnly();tpEnd.setMaxTime(LocalTime.parse("23:00"));

        List<Film> films = filmService.findAll();
        List<Cinema> cinemas = cinemaService.findAll();
        List<Salon> salons = salonService.findAll();

        cbFilm.setItems(films);cbFilm.setItemLabelGenerator(Film::getName);
        cbSalon.setItems(salons);cbSalon.setItemLabelGenerator(Salon::getNumber);
        cbCinema.setItems(cinemas);cbCinema.setItemLabelGenerator(Cinema::getName);

        sessionBinder2.bind(cbFilm,Session::getFilm,Session::setFilm);
        sessionBinder2.bind(cbSalon,Session::getSalon,Session::setSalon);
        sessionBinder2.bind(tpStart,Session::getStartTime,Session::setStartTime);
        sessionBinder2.bind(tpEnd,Session::getEndTime,Session::setEndTime);

        cbCinema.addValueChangeListener(e -> {
            cbSalon.setEnabled(true);
            List<Salon> salonNumber = salonService.findByCinema(e.getValue());
            cbSalon.setItems(salonNumber);
            cbSalon.setItemLabelGenerator(salon -> salon.getNumber());
        });

        cbFilm.addValueChangeListener(e -> {
            Film film = e.getValue();
            tpStart.addValueChangeListener(event -> {
                tpEnd.setValue(event.getValue().plus(Duration.ofMinutes(film.getMinute()+60)).truncatedTo(ChronoUnit.HOURS));
            });
        });

        FormLayout formLayout = new FormLayout();
        formLayout.add(cbFilm,cbCinema,cbSalon,tpStart,tpEnd);

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(buttonClickEvent -> {
            dialog.close();
            cbCinema.setValue(null);
            tpStart.setValue(null);
            tpEnd.setValue(null);
        });

        btnSave.addClickListener(buttonClickEvent -> {

            Session session = new Session();
            try {
                sessionBinder2.writeBean(session);
            } catch (ValidationException e) {
                e.printStackTrace();
            }

            List<Session> sessions = sessionService.findBySalon(session.getSalon());

            for (Session s:sessions) {
                if (itemId != s.getId()){
                    if ( session.getStartTime().isBefore(s.getEndTime()) && session.getStartTime().isAfter(s.getStartTime()) || session.getEndTime().isBefore(s.getEndTime()) && session.getEndTime().isAfter(s.getStartTime()) || session.getStartTime().equals(s.getStartTime())){
                        Notification.show("Another session in this time");
                        session = null;
                        break;
                    }
                }
            }

            session.setId(itemId);
            sessionService.save(session);
            updateList();
            dialog.close();
            cbCinema.setValue(null);
            tpStart.setValue(null);
            tpEnd.setValue(null);
        });


        horizontalLayout.add(btnCancel,btnSave);
        dialog.add(new H3("Session"),formLayout,horizontalLayout);
    }

    private void configureSessionDialog(Dialog dialog) {
        dialog.setModal(true);
        ComboBox<Film> cbFilm = new ComboBox("Film");
        ComboBox<Salon> cbSalon = new ComboBox("Salon");
        ComboBox<Cinema> cbCinema = new ComboBox("Cinema");
        TimePicker tpStart = new TimePicker("Start Time");tpStart.setMinTime(LocalTime.parse("09:00"));
        TimePicker tpEnd = new TimePicker("End Time");tpEnd.isReadOnly();tpEnd.setMaxTime(LocalTime.parse("23:00"));

        List<Film> films = filmService.findAll();
        List<Cinema> cinemas = cinemaService.findAll();
        List<Salon> salons = salonService.findAll();

        cbFilm.setItems(films);cbFilm.setItemLabelGenerator(Film::getName);
        cbSalon.setItems(salons);cbSalon.setItemLabelGenerator(Salon::getNumber);
        cbCinema.setItems(cinemas);cbCinema.setItemLabelGenerator(Cinema::getName);

        if (!itemId.equals(0)){
            ReadOnlyHasValue<Session> cinema = new ReadOnlyHasValue<>(
                    session -> cbCinema.setValue(session.getSalon().getCinema()));

            sessionBinder.forField(cinema).bind(session -> session,null);
        }


        sessionBinder.bind(cbFilm,Session::getFilm,Session::setFilm);
        sessionBinder.bind(cbSalon,Session::getSalon,Session::setSalon);
        sessionBinder.bind(tpStart,Session::getStartTime,Session::setStartTime);
        sessionBinder.bind(tpEnd,Session::getEndTime,Session::setEndTime);

        cbCinema.addValueChangeListener(e -> {
            cbSalon.setEnabled(true);
            List<Salon> salonNumber = salonService.findByCinema(e.getValue());
            cbSalon.setItems(salonNumber);
            cbSalon.setItemLabelGenerator(salon -> salon.getNumber());
        });

        cbFilm.addValueChangeListener(e -> {
            Film film = e.getValue();
            tpStart.addValueChangeListener(event -> {
                tpEnd.setValue(event.getValue().plus(Duration.ofMinutes(film.getMinute()+60)).truncatedTo(ChronoUnit.HOURS));
            });
        });

        FormLayout formLayout = new FormLayout();
        formLayout.add(cbFilm,cbCinema,cbSalon,tpStart,tpEnd);

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(buttonClickEvent -> {
            dialog.close();
            cbCinema.setValue(null);
        });

        btnSave.addClickListener(buttonClickEvent -> {

            Session session = new Session();
            try {
                sessionBinder.writeBean(session);
            } catch (ValidationException e) {
                e.printStackTrace();
            }

            List<Session> sessions = sessionService.findBySalon(session.getSalon());

            for (Session s:sessions) {
                if (itemId != s.getId()){
                    if ( session.getStartTime().isBefore(s.getEndTime()) && session.getStartTime().isAfter(s.getStartTime()) || session.getEndTime().isBefore(s.getEndTime()) && session.getEndTime().isAfter(s.getStartTime()) || session.getStartTime().equals(s.getStartTime())){
                        Notification.show("Another session in this time");
                        session = null;
                        break;
                    }
                }
            }

            session.setId(itemId);
            sessionService.save(session);
            updateList();
            dialog.close();
        });


        horizontalLayout.add(btnCancel,btnSave);
        dialog.add(new H3("Session"),formLayout,horizontalLayout);
    }

    private void updateList() {
        sessionGrid.setItems(sessionService.findAll());
    }

    private void updateByFilmFilter(String film) {
        sessionGrid.setItems(sessionService.findByFilmFilter(film));
    }

    private void updateByCinemaFilter(String cinema) {
        sessionGrid.setItems(sessionService.findByCinemaFilter(cinema));
    }

    private void configureGrid() {
        sessionGrid.addClassName("session-grid");
        sessionGrid.setColumns("film.name", "salon.cinema.name", "salon.number", "startTime", "endTime");
        sessionGrid.addComponentColumn(item -> createSessionButton(sessionGrid, item)).setHeader("Actions");
    }

    private HorizontalLayout createSessionButton(Grid<Session> grid, Session item) {
        @SuppressWarnings("unchecked")
        Button btnDelete = new Button("Delete");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Confirm Delete",
                    "Are you sure you want to delete?", "Delete", confirmEvent -> {
                List<Ticket> tickets = ticketService.findBySession(item);
                for (Ticket ticket:tickets){
                    ticketService.delete(ticket);
                }
                sessionService.delete(item);
                updateList();
            },
                    "Cancel", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("error primary");
            dialog.open();
        });

        Button btnUpdate = new Button("Update");
        btnUpdate.addClickListener(buttonClickEvent -> {
            itemId = item.getId();
            sessionBinder.readBean(item);
            dialogSession.open();
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(btnUpdate, btnDelete);

        return horizontalLayout;
    }

    /*private void configureSessionDialog(Dialog dialog) {
        dialog.setModal(true);
        ComboBox<Film> cbFilm = new ComboBox("Film");
        ComboBox<Salon> cbSalon = new ComboBox("Salon");
        ComboBox<Cinema> cbCinema = new ComboBox("Cinema");
        TimePicker tpStart = new TimePicker("Start Time");tpStart.setMinTime(LocalTime.parse("09:00"));
        TimePicker tpEnd = new TimePicker("End Time");tpEnd.isReadOnly();tpEnd.setMaxTime(LocalTime.parse("23:00"));

        List<Film> films = filmService.findAll();
        List<Cinema> cinemas = cinemaService.findAll();
        List<Salon> salons = salonService.findAll();

        cbFilm.setItems(films);cbFilm.setItemLabelGenerator(Film::getName);
        cbSalon.setItems(salons);cbSalon.setItemLabelGenerator(Salon::getNumber);
        cbCinema.setItems(cinemas);cbCinema.setItemLabelGenerator(Cinema::getName);

        sessionBinder.bind(cbFilm,Session::getFilm,Session::setFilm);
        sessionBinder.bind(cbSalon,Session::getSalon,Session::setSalon);
        sessionBinder.bind(tpStart,Session::getStartTime,Session::setStartTime);
        sessionBinder.bind(tpEnd,Session::getEndTime,Session::setEndTime);

        if (itemId != 0L){
            cbCinema.setValue(cbSalon.getValue().getCinema());
        }

        cbCinema.addValueChangeListener(e -> {
            cbSalon.setEnabled(true);
            List<Salon> salonNumber = salonService.findByCinema(e.getValue());
            cbSalon.setItems(salonNumber);
            cbSalon.setItemLabelGenerator(salon -> salon.getNumber());
        });

        cbFilm.addValueChangeListener(e -> {
            Film film = e.getValue();
            tpStart.addValueChangeListener(event -> {
                tpEnd.setValue(event.getValue().plus(Duration.ofMinutes(film.getMinute()+60)).truncatedTo(ChronoUnit.HOURS));
            });
        });

        FormLayout formLayout = new FormLayout();
        formLayout.add(cbFilm,cbCinema,cbSalon,tpStart,tpEnd);

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(buttonClickEvent -> {
            dialog.close();
            cbCinema.setValue(null);
        });

        btnSave.addClickListener(buttonClickEvent -> {

            Session session = new Session();
            try {
                sessionBinder.writeBean(session);
            } catch (ValidationException e) {
                e.printStackTrace();
            }

            List<Session> sessions = sessionService.findBySalon(session.getSalon());

            for (Session s:sessions) {
                if (itemId != s.getId()){
                    if ( session.getStartTime().isBefore(s.getEndTime()) && session.getStartTime().isAfter(s.getStartTime()) || session.getEndTime().isBefore(s.getEndTime()) && session.getEndTime().isAfter(s.getStartTime())){
                        Notification.show("Another session in this time");
                        tpStart.setValue(null);
                        break;
                    }
                }
            }

            session.setId(itemId);
            sessionService.save(session);
            updateList();
            dialog.close();
            cbCinema.setValue(null);
        });


        horizontalLayout.add(btnCancel,btnSave);
        dialog.add(new H3("Session"),formLayout,horizontalLayout);
    }*/
}
