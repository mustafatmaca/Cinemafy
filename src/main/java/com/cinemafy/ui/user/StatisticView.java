package com.cinemafy.ui.user;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.Ticket;
import com.cinemafy.backend.services.TicketService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Route(value = "statistic", layout = MainView.class)
public class StatisticView extends VerticalLayout {

    private final TicketService ticketService;

    public StatisticView(TicketService ticketService) {
        this.ticketService = ticketService;

        List<Ticket> tickets = ticketService.findAll();
        List<String> ticketFilms = new ArrayList<>();
        List<String> ticketCinemas = new ArrayList<>();

        for (Ticket ticket : tickets) {
            ticketFilms.add(ticket.getSession().getFilm().getName());
            ticketCinemas.add(ticket.getSession().getSalon().getCinema().getName());
        }

        List<Map.Entry<String, Long>> films = sortList(turnMap(ticketFilms));
        List<Map.Entry<String, Long>> cinemas = sortList(turnMap(ticketCinemas));

        //CONTENT
        H2 h2Film = new H2("Most Viewers Film");
        h2Film.setWidth("800px");
        H2 h2Cinema = new H2("Most Viewers Cinema");

        VerticalLayout verticalLayout = new VerticalLayout(h2Cinema);
        VerticalLayout verticalLayout1 = new VerticalLayout(h2Film);

        for (int j = 1; j<cinemas.size()+1; j++){
            Label lblCinema = new Label(j + " - " + cinemas.get(cinemas.size()-j).getKey());
            lblCinema.setWidth("800px");
            verticalLayout.add(lblCinema);
        }

        for (int i = 1; i<films.size()+1; i++){
            Label lblFilm = new Label(i + " - " + films.get(films.size()-i).getKey());
            lblFilm.setWidth("800px");
            verticalLayout1.add(lblFilm);
        }

        //HEADER
        H1 h1 = new H1("Statistics");
        h1.setHeight("50px");

        //LAYOUT
        VerticalLayout title = new VerticalLayout(h1);
        title.setAlignItems(Alignment.START);

        verticalLayout.setAlignItems(Alignment.BASELINE);
        verticalLayout.setSpacing(true);

        verticalLayout1.setAlignItems(Alignment.BASELINE);
        verticalLayout1.setSpacing(true);

        HorizontalLayout horizontalLayout = new HorizontalLayout(verticalLayout,verticalLayout1);

        add(title, horizontalLayout);
    }

    //Sorting list by frequency
    private List<Map.Entry<String, Long>> sortList(Map<String, Long> turnMap) {
        List<Map.Entry<String, Long>> list = new ArrayList<>(turnMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list;
    }

    //Turn list to map
    private Map<String, Long> turnMap(List<String> list) {
        return list.stream().collect(Collectors.groupingBy(w -> w,Collectors.counting()));
    }

}
