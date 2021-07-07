package com.cinemafy.ui.admin;

import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.models.Salon;
import com.cinemafy.backend.services.CinemaService;
import com.cinemafy.backend.services.SalonService;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "cinema", layout = AdminView.class)
public class CinemaAndSalonView extends VerticalLayout {

    private final CinemaService cinemaService;
    private final SalonService salonService;
    private Grid<Cinema> cinemaGrid = new Grid<>(Cinema.class);
    private Grid<Salon> salonGrid = new Grid<>(Salon.class);
    Dialog dialogCinema = new Dialog();
    Dialog dialogSalon = new Dialog();
    Binder<Cinema> cinemaBinder = new Binder<>();
    Binder<Salon> salonBinder = new Binder<>();

    public CinemaAndSalonView(CinemaService cinemaService, SalonService salonService) {
        this.cinemaService = cinemaService;
        this.salonService = salonService;
        H1 cinema = new H1("Cinemas");
        H1 salon = new H1("Salons");

        configureCinemaDialog(dialogCinema);
        configureSalonDialog(dialogSalon);

        configureGrid();
        updateList();

        Button btnNewCinema = new Button("New Cinema");
        btnNewCinema.addClickListener(buttonClickEvent -> {
            cinemaBinder.readBean(new Cinema());
            dialogCinema.open();
        });

        Button btnNewSalon = new Button("New Salon");
        btnNewSalon.addClickListener(buttonClickEvent -> {
            salonBinder.readBean(new Salon());
            dialogSalon.open();
        });

        add(cinema, btnNewCinema, cinemaGrid, salon, btnNewSalon, salonGrid);
    }

    private void configureCinemaDialog(Dialog dialog) {
        dialog.setModal(true);
        TextField tfName = new TextField("Name");
        TextField tfCity = new TextField("City");

        cinemaBinder.bind(tfName,Cinema::getName,Cinema::setName);
        cinemaBinder.bind(tfCity,Cinema::getCity,Cinema::setCity);

        FormLayout formLayout = new FormLayout();
        formLayout.add(tfName,tfCity);

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

            cinemaService.save(cinema);
            updateList();
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
        List<String> cinemaNames = new ArrayList<>();
        for (Cinema cinema : cinemas) {
            cinemaNames.add(cinema.getName());
        }
        ComboBox cbCinema = new ComboBox("Cinema");
        cbCinema.setItems(cinemaNames);

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

            salonService.save(salon);
            updateList();
            dialog.close();
        });


        horizontalLayout.add(btnCancel,btnSave);
        dialog.add(new H3("Salon"),formLayout,horizontalLayout);
    }

    private void updateList() {
        cinemaGrid.setItems(cinemaService.findAll());
        salonGrid.setItems(salonService.findAll());
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
                cinemaService.delete(item);
                updateList();
            },
                    "Cancel", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("error primary");
            dialog.open();
        });

        Button btnUpdate = new Button("Update");
        btnUpdate.addClickListener(buttonClickEvent -> {
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
            salonBinder.readBean(item);
            dialogSalon.open();
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(btnUpdate, btnDelete);

        return horizontalLayout;
    }
}
