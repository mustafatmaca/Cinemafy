package com.cinemafy.ui.admin;

import com.cinemafy.backend.models.Category;
import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.services.CategoryService;
import com.cinemafy.backend.services.FilmService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "film", layout = AdminView.class)
public class FilmAndCategoryView extends VerticalLayout {


    private final FilmService filmService;
    private final CategoryService categoryService;
    private Grid<Film> filmGrid = new Grid<>(Film.class);
    private Grid<Category> categoryGrid = new Grid<>(Category.class);
    Dialog dialogFilm = new Dialog();
    Dialog dialogCategory = new Dialog();
    Binder<Film> filmBinder = new Binder<>();
    Binder<Category> categoryBinder = new Binder<>();

    public FilmAndCategoryView(FilmService filmService, CategoryService categoryService) {
        this.filmService = filmService;
        this.categoryService = categoryService;
        H1 film = new H1("Films");
        H1 category = new H1("Categories");

        configureFilmDialog(dialogFilm);
        configureCategoryDialog(dialogCategory);

        configureGrid();
        updateList();

        Button btnNewFilm = new Button("New Film");
        btnNewFilm.addClickListener(buttonClickEvent -> {
            filmBinder.readBean(new Film());
            dialogFilm.open();
        });

        Button btnNewCategory = new Button("New Category");
        btnNewCategory.addClickListener(buttonClickEvent -> {
            categoryBinder.readBean(new Category());
            dialogCategory.open();
        });

        add(film, btnNewFilm, filmGrid, category, btnNewCategory, categoryGrid);
    }

    private void configureFilmDialog(Dialog dialog) {
        dialog.setModal(true);
        TextField tfName = new TextField("Name");
        TextField tfRuntime = new TextField("City(ex. '2h30m')");
        TextField tfSrc = new TextField("Image Link");
        List<Category> categories = categoryService.findAll();
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categories) {
            categoryNames.add(category.getGenre());
        }
        ComboBox cbCategory = new ComboBox("Category");
        cbCategory.setItems(categoryNames);

        filmBinder.bind(tfName,Film::getName,Film::setName);
        filmBinder.bind(tfRuntime,Film::getRuntime,Film::setRuntime);
        filmBinder.bind(tfSrc,Film::getSrc,Film::setSrc);

        FormLayout formLayout = new FormLayout();
        formLayout.add(tfName,tfRuntime,tfSrc,cbCategory);

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

            categoryService.save(category);
            updateList();
            dialog.close();
        });


        horizontalLayout.add(btnCancel,btnSave);
        dialog.add(new H3("Category"),formLayout,horizontalLayout);
    }

    private void updateList() {
        filmGrid.setItems(filmService.findAll());
        categoryGrid.setItems(categoryService.findAll());
    }

    private void configureGrid() {
        filmGrid.addClassName("film-grid");
        filmGrid.setColumns("name", "runtime", "category.genre", "src");
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
                categoryService.delete(item);
                updateList();
            },
                    "Cancel", cancelEvent -> {
            });
            dialog.setConfirmButtonTheme("error primary");
            dialog.open();
        });

        Button btnUpdate = new Button("Update");
        btnUpdate.addClickListener(buttonClickEvent -> {
            categoryBinder.readBean(item);
            dialogCategory.open();
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(btnUpdate, btnDelete);

        return horizontalLayout;
    }
}
