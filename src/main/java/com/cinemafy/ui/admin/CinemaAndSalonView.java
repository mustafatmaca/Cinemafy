package com.cinemafy.ui.admin;

import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.models.Salon;
import com.cinemafy.backend.models.Session;
import com.cinemafy.backend.models.Ticket;
import com.cinemafy.backend.services.CinemaService;
import com.cinemafy.backend.services.SalonService;
import com.cinemafy.backend.services.SessionService;
import com.cinemafy.backend.services.TicketService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Route(value = "cinema", layout = AdminView.class)
public class CinemaAndSalonView extends VerticalLayout {

    private final CinemaService cinemaService;
    private final SalonService salonService;
    private final SessionService sessionService;
    private final TicketService ticketService;
    private Grid<Cinema> cinemaGrid = new Grid<>(Cinema.class);
    private Grid<Salon> salonGrid = new Grid<>(Salon.class);
    TextField tfNameFilter = new TextField();
    TextField tfCityFilter = new TextField();
    TextField tfCinemaFilter = new TextField();
    Dialog dialogCinema = new Dialog();
    Dialog dialogSalon = new Dialog();
    Binder<Cinema> cinemaBinder = new Binder<>();
    Binder<Salon> salonBinder = new Binder<>();
    ComboBox<Cinema> cbCinema = new ComboBox("Cinema");
    Long itemId = 0L;

    public CinemaAndSalonView(CinemaService cinemaService, SalonService salonService, SessionService sessionService, TicketService ticketService) throws FileNotFoundException {
        this.cinemaService = cinemaService;
        this.salonService = salonService;
        this.sessionService = sessionService;
        this.ticketService = ticketService;
        H1 cinema = new H1("Cinemas");
        H1 salon = new H1("Salons");

        configureCinemaDialog(dialogCinema);
        configureSalonDialog(dialogSalon);

        configureGrid();
        updateList();

        tfNameFilter.setPlaceholder("Name");
        tfCityFilter.setPlaceholder("City");
        Button btnNameFilter = new Button("Search", VaadinIcon.SEARCH.create());
        btnNameFilter.addClickListener(event -> {
            updateByNameFilter(tfNameFilter.getValue());
        });
        Button btnCityFilter = new Button("Search", VaadinIcon.SEARCH.create());
        btnCityFilter.addClickListener(event -> {
            updateByCityFilter(tfCityFilter.getValue());
        });

        HorizontalLayout cinemaFilter = new HorizontalLayout();
        cinemaFilter.add(tfNameFilter,btnNameFilter, tfCityFilter,btnCityFilter);

        tfCinemaFilter.setPlaceholder("Cinema");
        Button btnCinemaFilter = new Button("Search", VaadinIcon.SEARCH.create());
        btnCinemaFilter.addClickListener(event -> {
            updateByCinemaFilter(tfCinemaFilter.getValue());
        });

        HorizontalLayout salonFilter = new HorizontalLayout();
        salonFilter.add(tfCinemaFilter,btnCinemaFilter);

        Button btnNewCinema = new Button("New Cinema");
        btnNewCinema.addClickListener(buttonClickEvent -> {
            itemId = 0L;
            cinemaBinder.readBean(new Cinema());
            dialogCinema.open();
        });

        Button btnNewSalon = new Button("New Salon");
        btnNewSalon.addClickListener(buttonClickEvent -> {
            itemId = 0L;
            salonBinder.readBean(new Salon());
            dialogSalon.open();
        });

        add(cinema, btnNewCinema, cinemaFilter, cinemaGrid, salon, btnNewSalon, salonFilter, salonGrid);
    }

    private void configureCinemaDialog(Dialog dialog) throws FileNotFoundException {
        dialog.setModal(true);
        TextField tfName = new TextField("Name");
        TextField tfCity = new TextField("City");
        ComboBox<String> cbCity = new ComboBox<>("City");

        List<String> cities = new ArrayList<String>();
        File file = new File("C:/Users/musta/Desktop/city.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            cities.add(scanner.nextLine());
        }

        cbCity.setItems(cities);

        cinemaBinder.bind(tfName,Cinema::getName,Cinema::setName);
        cinemaBinder.bind(cbCity,Cinema::getCity,Cinema::setCity);

        FormLayout formLayout = new FormLayout();
        formLayout.add(tfName,cbCity);

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        btnSave.addClickListener(buttonClickEvent -> {

            Cinema cinema = new Cinema();
            try {
                cinemaBinder.writeBean(cinema);
            } catch (ValidationException e) {
                e.printStackTrace();
            }

            cinema.setId(itemId);
            cinemaService.save(cinema);
            updateList();
            updateDialog();
            dialog.close();
        });


        horizontalLayout.add(btnCancel,btnSave);
        dialog.add(new H3("Cinema"),formLayout,horizontalLayout);
    }

    private void configureSalonDialog(Dialog dialog) {
        dialog.setModal(true);
        Cinema cinema1 = new Cinema();
        TextField tfNumber = new TextField("Number");
        TextField tfSeatCapacity = new TextField("Seat Capacity");

        List<Cinema> cinemas = cinemaService.findAll();

        cbCinema.setItems(cinemas);
        cbCinema.setItemLabelGenerator(Cinema::getName);

        salonBinder.bind(tfNumber,Salon::getNumber,Salon::setNumber);
        salonBinder.bind(tfSeatCapacity,Salon::getSeatCapacity,Salon::setSeatCapacity);
        salonBinder.bind(cbCinema,Salon::getCinema,Salon::setCinema);

        FormLayout formLayout = new FormLayout();
        formLayout.add(tfNumber,tfSeatCapacity,cbCinema);

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        btnSave.addClickListener(buttonClickEvent -> {

            Salon salon = new Salon();
            try {
                salonBinder.writeBean(salon);
            } catch (ValidationException e) {
                e.printStackTrace();
            }

            salon.setId(itemId);
            salonService.save(salon);
            updateList();
            dialog.close();
        });


        horizontalLayout.add(btnCancel,btnSave);
        dialog.add(new H3("Salon"),formLayout,horizontalLayout);
    }

    private void updateDialog() {
        cbCinema.setItems(cinemaService.findAll());
    }

    private void updateList() {
        cinemaGrid.setItems(cinemaService.findAll());
        salonGrid.setItems(salonService.findAll());
    }

    private void updateByNameFilter(String name) {
        cinemaGrid.setItems(cinemaService.findByNameFilter(name));
    }

    private void updateByCityFilter(String city) {
        cinemaGrid.setItems(cinemaService.findByCityFilter(city));
    }

    private void updateByCinemaFilter(String cinema) {
        salonGrid.setItems(salonService.findByCinemaFilter(cinema));
    }

    private void configureGrid() {
        cinemaGrid.addClassName("cinema-grid");
        cinemaGrid.setColumns("name", "city");
        cinemaGrid.addComponentColumn(item -> createCinemaButton(cinemaGrid, item)).setHeader("Actions");

        salonGrid.addClassName("salon-grid");
        salonGrid.setColumns("cinema.name", "number", "seatCapacity");
        salonGrid.addComponentColumn(item -> createSalonButton(salonGrid, item)).setHeader("Actions");
    }

    private HorizontalLayout createCinemaButton(Grid<Cinema> grid, Cinema item) {
        @SuppressWarnings("unchecked")
        Button btnDelete = new Button("Delete");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Confirm Delete",
                    "Are you sure you want to delete?", "Delete", confirmEvent -> {
                List<Salon> salons = salonService.findByCinema(item);
                for (Salon salon:salons) {
                    List<Session> sessions = sessionService.findBySalon(salon);
                    for (Session session:sessions) {
                        List<Ticket> tickets = ticketService.findBySession(session);
                        for (Ticket ticket:tickets){
                            ticketService.delete(ticket);
                        }
                        sessionService.delete(session);
                    }
                    salonService.delete(salon);
                }
                cinemaService.delete(item);
                updateList();
                updateDialog();
            },
                    "Cancel", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("error primary");
            dialog.open();
        });

        Button btnUpdate = new Button("Update");
        btnUpdate.addClickListener(buttonClickEvent -> {
            itemId = item.getId();
            cinemaBinder.readBean(item);
            dialogCinema.open();
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(btnUpdate, btnDelete);

        return horizontalLayout;
    }

    private HorizontalLayout createSalonButton(Grid<Salon> grid, Salon item) {
        @SuppressWarnings("unchecked")
        Button btnDelete = new Button("Delete");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Confirm Delete",
                    "Are you sure you want to delete?", "Delete", confirmEvent -> {
                List<Session> sessions = sessionService.findBySalon(item);
                for (Session session:sessions) {
                    List<Ticket> tickets = ticketService.findBySession(session);
                    for (Ticket ticket:tickets){
                        ticketService.delete(ticket);
                    }
                    sessionService.delete(session);
                }
                salonService.delete(item);
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
            salonBinder.readBean(item);
            dialogSalon.open();
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(btnUpdate, btnDelete);

        return horizontalLayout;
    }
}
