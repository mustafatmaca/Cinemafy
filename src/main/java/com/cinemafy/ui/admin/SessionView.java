package com.cinemafy.ui.admin;

import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.models.Salon;
import com.cinemafy.backend.models.Session;
import com.cinemafy.backend.services.CinemaService;
import com.cinemafy.backend.services.FilmService;
import com.cinemafy.backend.services.SalonService;
import com.cinemafy.backend.services.SessionService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "session", layout = AdminView.class)
public class SessionView extends VerticalLayout {

    private final SessionService sessionService;
    private final CinemaService cinemaService;
    private final SalonService salonService;
    private final FilmService filmService;
    private Grid<Session> sessionGrid = new Grid<>(Session.class);;
    Dialog dialogSession = new Dialog();
    Binder<Session> sessionBinder = new Binder<>();
    Long itemId = 0L;

    public SessionView(SessionService sessionService, CinemaService cinemaService, SalonService salonService, FilmService filmService) {
        this.sessionService = sessionService;
        this.cinemaService = cinemaService;
        this.salonService = salonService;
        this.filmService = filmService;
        H1 session = new H1("Session");

        configureSessionDialog(dialogSession);

        configureGrid();
        updateList();

        Button btnNewSession = new Button("New Session");
        btnNewSession.addClickListener(buttonClickEvent -> {
            itemId = 0L;
            sessionBinder.readBean(new Session());
            dialogSession.open();
        });

        add(session, btnNewSession, sessionGrid);

    }

    private void configureSessionDialog(Dialog dialog) {
        dialog.setModal(true);
        ComboBox<Film> cbFilm = new ComboBox("Film");
        ComboBox<Salon> cbSalon = new ComboBox("Salon");
        TimePicker tpStart = new TimePicker("Start Time");
        TimePicker tpEnd = new TimePicker("End Time");tpEnd.isReadOnly();

        List<Film> films = filmService.findAll();
        List<Cinema> cinemas = cinemaService.findAll();
        List<Salon> salons = salonService.findAll();

        cbFilm.setItems(films);cbFilm.setItemLabelGenerator(Film::getName);
        cbSalon.setItems(salons);cbSalon.setItemLabelGenerator(Salon::getNumber);

        sessionBinder.bind(cbFilm,Session::getFilm,Session::setFilm);
        sessionBinder.bind(cbSalon,Session::getSalon,Session::setSalon);
        sessionBinder.bind(tpStart,Session::getStartTime,Session::setStartTime);
        sessionBinder.bind(tpEnd,Session::getEndTime,Session::setEndTime);

        FormLayout formLayout = new FormLayout();
        formLayout.add(cbFilm,cbSalon,tpStart,tpEnd);

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        btnSave.addClickListener(buttonClickEvent -> {

            Session session = new Session();
            try {
                sessionBinder.writeBean(session);
            } catch (ValidationException e) {
                e.printStackTrace();
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
}
