package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Film;
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

    public HomeView(FilmService filmService) {
        this.filmService = filmService;

        List<Film> films = filmService.findAll();
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        for (Film film : films) {
            H3 name = new H3(film.getName());
            name.setHeight("40px");
            Label ctg = new Label(film.getCategory().getGenre());
            ctg.setHeight("20px");
            String src = film.getSrc();
            Image img = new Image(src, "Dune");
            img.setHeight("384px");
            img.setWidth("256px");
            horizontalLayout.add(new VerticalLayout(img,name,ctg));
        }
        horizontalLayout.setAlignItems(Alignment.CENTER);

        setWidth("%100");
        setHeight("%100");
        setPadding(true);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        H1 h1 = new H1("Movie In Theaters");
        h1.setHeight("100px");
        H1 h2 = new H1("Theaters");
        h2.setHeight("100px");

        //Label eternals = new Label("Eternals");
        //eternals.setHeight("40px");
        //Label dune = new Label("Dune");
        //dune.setHeight("40px");

        //String src = "https://tr.web.img4.acsta.net/r_1920_1080/pictures/20/04/30/20/27/2963799.jpg";
        //Image img = new Image(src, "Dune");
        //img.setHeight("384px");
        //img.setWidth("256px");

        //String src1 = "https://tr.web.img4.acsta.net/r_1920_1080/pictures/21/05/25/10/14/2918182.jpg";
        //Image img1 = new Image(src1, "Eternals");
        //img1.setHeight("384px");
        //img1.setWidth("256px");

        HorizontalLayout title1 = new HorizontalLayout(h1);
        title1.setAlignItems(Alignment.START);


        HorizontalLayout title2 = new HorizontalLayout(h2);
        title2.setAlignItems(Alignment.START);

        setAlignItems(Alignment.START);
        add(title1, horizontalLayout, title2);
    }
}
