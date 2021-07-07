package com.cinemafy.ui.user;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.services.CinemaService;
import com.cinemafy.backend.services.FilmService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;


@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class HomeView extends VerticalLayout {

    private final FilmService filmService;
    private final CinemaService cinemaService;

    public HomeView(FilmService filmService, CinemaService cinemaService) {
        this.filmService = filmService;
        this.cinemaService = cinemaService;

        setWidth("%100");
        setHeight("%100");
        setPadding(true);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        //CONTENT
        List<Film> films = filmService.findAll();
        HorizontalLayout horizontalLayout1 = new HorizontalLayout();

        for (Film film : films) {
            H3 name = new H3(film.getName());
            name.setHeight("40px");
            Label ctg = new Label(film.getCategory().getGenre());
            ctg.setHeight("20px");
            String src = film.getSrc();
            Image img = new Image(src, film.getName());
            img.setHeight("384px");
            img.setWidth("256px");
            horizontalLayout1.add(new VerticalLayout(img,name,ctg));
        }
        horizontalLayout1.setAlignItems(Alignment.CENTER);

        List<Cinema> cinemas = cinemaService.findAll();
        HorizontalLayout horizontalLayout2 = new HorizontalLayout();

        for (Cinema cinema : cinemas) {
            H3 name = new H3(cinema.getName());
            name.setHeight("40px");
            Label city = new Label(cinema.getCity());
            city.setHeight("20px");
            horizontalLayout2.add(new VerticalLayout(name,city));
        }
        horizontalLayout2.setAlignItems(Alignment.CENTER);

        //HEADER
        H1 h1 = new H1("Movie In Cinemas");
        h1.setHeight("100px");
        H1 h2 = new H1("Cinemas");
        h2.setHeight("100px");

        //LAYOUT
        HorizontalLayout title1 = new HorizontalLayout(h1);
        title1.setAlignItems(Alignment.START);

        HorizontalLayout title2 = new HorizontalLayout(h2);
        title2.setAlignItems(Alignment.START);

        setAlignItems(Alignment.START);
        add(title1, horizontalLayout1, title2, horizontalLayout2);
    }
}
