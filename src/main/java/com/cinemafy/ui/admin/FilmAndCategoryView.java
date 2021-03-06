package com.cinemafy.ui.admin;

import com.cinemafy.backend.models.Category;
import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.models.Session;
import com.cinemafy.backend.models.Ticket;
import com.cinemafy.backend.services.CategoryService;
import com.cinemafy.backend.services.FilmService;
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
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "film", layout = AdminView.class)
public class FilmAndCategoryView extends VerticalLayout {


    private final FilmService filmService;
    private final CategoryService categoryService;
    private final SessionService sessionService;
    private final TicketService ticketService;
    private Grid<Film> filmGrid = new Grid<>(Film.class);
    private Grid<Category> categoryGrid = new Grid<>(Category.class);
    TextField tfNameFilter = new TextField();
    TextField tfCategoryFilter = new TextField();
    TextField tfGenreFilter = new TextField();
    Dialog dialogFilm = new Dialog();
    Dialog dialogCategory = new Dialog();
    Binder<Film> filmBinder = new Binder<>();
    Binder<Category> categoryBinder = new Binder<>();
    ComboBox<Category> cbCategory = new ComboBox("Category");
    Long itemId = 0L;

    public FilmAndCategoryView(FilmService filmService, CategoryService categoryService, SessionService sessionService, TicketService ticketService) {
        this.filmService = filmService;
        this.categoryService = categoryService;
        this.sessionService = sessionService;
        this.ticketService = ticketService;
        H1 film = new H1("Films");
        H1 category = new H1("Categories");

        configureFilmDialog(dialogFilm);
        configureCategoryDialog(dialogCategory);

        configureGrid();
        updateList();

        //FILTER
        tfNameFilter.setPlaceholder("Name");
        tfCategoryFilter.setPlaceholder("Category");
        Button btnNameFilter = new Button("Search", VaadinIcon.SEARCH.create());
        btnNameFilter.addClickListener(event -> {
            updateByNameFilter(tfNameFilter.getValue());
        });
        Button btnCategoryFilter = new Button("Search", VaadinIcon.SEARCH.create());
        btnCategoryFilter.addClickListener(event -> {
            updateByCategoryFilter(tfCategoryFilter.getValue());
        });

        HorizontalLayout filmFilter = new HorizontalLayout();
        filmFilter.add(tfNameFilter,btnNameFilter,tfCategoryFilter,btnCategoryFilter);

        tfGenreFilter.setPlaceholder("Genre");
        Button btnGenreFilter = new Button("Search", VaadinIcon.SEARCH.create());
        btnGenreFilter.addClickListener(event -> {
            updateByGenreFilter(tfGenreFilter.getValue());
        });

        HorizontalLayout categoryFilter = new HorizontalLayout();
        categoryFilter.add(tfGenreFilter,btnGenreFilter);

        //NEW
        Button btnNewFilm = new Button("New Film");
        btnNewFilm.addClickListener(buttonClickEvent -> {
            itemId = 0L;
            filmBinder.readBean(new Film());
            dialogFilm.open();
        });

        Button btnNewCategory = new Button("New Category");
        btnNewCategory.addClickListener(buttonClickEvent -> {
            itemId = 0L;
            categoryBinder.readBean(new Category());
            dialogCategory.open();
        });

        add(film, btnNewFilm, filmFilter, filmGrid, category, btnNewCategory, categoryFilter, categoryGrid);
    }


    private void configureFilmDialog(Dialog dialog) {
        dialog.setModal(true);
        TextField tfName = new TextField("Name");
        TextField tfMinute = new TextField("Minute(ex. '120')");
        TextField tfSrc = new TextField("Image Link");
        List<Category> categories = categoryService.findAll();

        cbCategory.setItems(categories);
        cbCategory.setItemLabelGenerator(Category::getGenre);

        filmBinder.bind(tfName,Film::getName,Film::setName);
        filmBinder.forField(tfMinute).withConverter(new StringToLongConverter("Must enter minute")).bind(Film::getMinute,Film::setMinute);
        filmBinder.bind(tfSrc,Film::getSrc,Film::setSrc);
        filmBinder.bind(cbCategory,Film::getCategory,Film::setCategory);

        FormLayout formLayout = new FormLayout();
        formLayout.add(tfName,tfMinute,tfSrc,cbCategory);

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        btnSave.addClickListener(buttonClickEvent -> {

            Film film = new Film();
            try {
                filmBinder.writeBean(film);
            } catch (ValidationException e) {
                e.printStackTrace();
            }

            film.setId(itemId);
            filmService.save(film);
            updateList();
            dialog.close();
        });


        horizontalLayout.add(btnCancel,btnSave);
        dialog.add(new H3("Film"),formLayout,horizontalLayout);
    }

    private void configureCategoryDialog(Dialog dialog) {
        dialog.setModal(true);
        TextField tfGenre = new TextField("Genre");

        categoryBinder.bind(tfGenre,Category::getGenre,Category::setGenre);

        FormLayout formLayout = new FormLayout();
        formLayout.add(tfGenre);

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");
        btnCancel.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        btnSave.addClickListener(buttonClickEvent -> {

            Category category = new Category();
            try {
                categoryBinder.writeBean(category);
            } catch (ValidationException e) {
                e.printStackTrace();
            }

            category.setId(itemId);
            categoryService.save(category);
            updateList();
            updateDialog();
            dialog.close();
        });


        horizontalLayout.add(btnCancel,btnSave);
        dialog.add(new H3("Category"),formLayout,horizontalLayout);
    }

    private void updateDialog() {
        cbCategory.setItems(categoryService.findAll());
    }

    private void updateList() {
        filmGrid.setItems(filmService.findAll());
        categoryGrid.setItems(categoryService.findAll());
    }


    private void updateByNameFilter(String name) {
        filmGrid.setItems(filmService.findByNameFilter(name));
    }

    private void updateByCategoryFilter(String category) {
        filmGrid.setItems(filmService.findByCategoryFilter(category));
    }

    private void updateByGenreFilter(String genre) {
        categoryGrid.setItems(categoryService.findByGenreFilter(genre));
    }

    private void configureGrid() {
        filmGrid.addClassName("film-grid");
        filmGrid.setColumns("name", "minute", "category.genre");
        filmGrid.addComponentColumn(i -> {
            Image image = new Image(i.getSrc(), i.getName());
            image.setHeight("192px");
            image.setWidth("128px");
            return image;
        }).setHeader("Image");
        filmGrid.addComponentColumn(item -> createFilmButton(filmGrid, item)).setHeader("Actions");

        categoryGrid.addClassName("category-grid");
        categoryGrid.setColumns("genre");
        categoryGrid.addComponentColumn(item -> createCategoryButton(categoryGrid, item)).setHeader("Actions");
    }

    private HorizontalLayout createFilmButton(Grid<Film> grid, Film item) {
        @SuppressWarnings("unchecked")
        Button btnDelete = new Button("Delete");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Confirm Delete",
                    "Are you sure you want to delete?", "Delete", confirmEvent -> {
                List<Session> sessions = sessionService.findByFilm(item);
                for (Session session:sessions) {
                    List<Ticket> tickets = ticketService.findBySession(session);
                    for (Ticket ticket:tickets){
                        ticketService.delete(ticket);
                    }
                    sessionService.delete(session);
                }
                filmService.delete(item);
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
            filmBinder.readBean(item);
            dialogFilm.open();
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(btnUpdate, btnDelete);

        return horizontalLayout;
    }

    private HorizontalLayout createCategoryButton(Grid<Category> grid, Category item) {
        @SuppressWarnings("unchecked")
        Button btnDelete = new Button("Delete");
        btnDelete.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialog = new ConfirmDialog("Confirm Delete",
                    "Are you sure you want to delete?", "Delete", confirmEvent -> {
                List<Film> films = filmService.findByCategory(item);
                for (Film film:films) {
                    List<Session> sessions = sessionService.findByFilm(film);
                    for (Session session:sessions) {
                        List<Ticket> tickets = ticketService.findBySession(session);
                        for (Ticket ticket:tickets){
                            ticketService.delete(ticket);
                        }
                        sessionService.delete(session);
                    }
                    filmService.delete(film);
                }
                categoryService.delete(item);
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
            categoryBinder.readBean(item);
            dialogCategory.open();
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(btnUpdate, btnDelete);

        return horizontalLayout;
    }
}
