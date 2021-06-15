package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.services.FilmService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;

@Route(value = "movie", layout = MainView.class)
public class MovieView extends VerticalLayout {

    private final FilmService filmService;

    public MovieView(FilmService filmService) {
        System.out.println("MovieView");
        this.filmService = filmService;

        setWidth("%100");
        setHeight("%100");
        setPadding(true);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        List<Film> films = filmService.findAll();
        VerticalLayout verticalLayout = new VerticalLayout();

        for (Film film : films) {
            H3 ctgHead = new H3("Category");
            Label ctg = new Label(film.getCategory().getGenre());
            H3 rntmHead = new H3("Runtime");
            Label rntm = new Label(film.getRuntime());
            Button ticket = new Button("Ticket");
            ticket.setWidth("200px");
            ticket.addClickListener(e -> {
                VaadinSession.getCurrent().getSession().setAttribute("TicketFilm", film.getName());
                UI.getCurrent().getPage().setLocation("/ticket");
            });
            FormLayout filmForm = new FormLayout();
            filmForm.add(new HorizontalLayout(new VerticalLayout(ctgHead,ctg), new VerticalLayout(rntmHead,rntm), new VerticalLayout(ticket)));
            Details details = new Details(film.getName(), filmForm);
            details.addThemeVariants(DetailsVariant.REVERSE, DetailsVariant.FILLED);
            String src = film.getSrc();
            Image img = new Image(src, film.getName());
            img.setHeight("192px");
            img.setWidth("128px");
            verticalLayout.add(new HorizontalLayout(img,new VerticalLayout(details)));
        }
        verticalLayout.setAlignItems(Alignment.START);

        H1 h1 = new H1("Movies");
        h1.setHeight("100px");

        HorizontalLayout title = new HorizontalLayout(h1);
        title.setAlignItems(Alignment.START);

        setAlignItems(Alignment.START);

        add(title,verticalLayout);
    }

}
