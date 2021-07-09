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
        List<String> ticketSalons = new ArrayList<>();
        List<String> ticketSessions = new ArrayList<>();

        for (Ticket ticket : tickets) {
            //ticketFilms.add(ticket.getFilm());
            //ticketCinemas.add(ticket.getCinema());
            //ticketSalons.add(ticket.getSalon());
            //ticketSessions.add(ticket.getSession());
        }

        List<Map.Entry<String, Long>> films = sortList(turnMap(ticketFilms));
        List<Map.Entry<String, Long>> cinemas = sortList(turnMap(ticketCinemas));
        List<Map.Entry<String, Long>> salons = sortList(turnMap(ticketSalons));
        List<Map.Entry<String, Long>> sessions = sortList(turnMap(ticketSessions));

        //CONTENT
        H2 h2Film = new H2("Most Viewers Film");
        h2Film.setWidth("800px");
        H2 h2Cinema = new H2("Most Viewers Cinema");
        H2 h2Salon = new H2("Most Viewers Film by Salon");
        h2Salon.setWidth("800px");
        H2 h2Session = new H2("Most Viewers Session");

        Label lblFilm1 = new Label("1st - " + films.get(films.size()-1).getKey());
        lblFilm1.setWidth("800px");
        Label lblFilm2 = new Label("2nd - " + films.get(films.size()-2).getKey());
        lblFilm2.setWidth("800px");
        Label lblFilm3 = new Label("3th - " + films.get(films.size()-3).getKey());
        lblFilm3.setWidth("800px");

        Label lblCinema1 = new Label("1st - " + cinemas.get(cinemas.size()-1).getKey());
        Label lblCinema2 = new Label("2nd - " + cinemas.get(cinemas.size()-2).getKey());
        Label lblCinema3 = new Label("3th - " + cinemas.get(cinemas.size()-3).getKey());

        Label lblSalon1 = new Label("1st - " + "Salon" + salons.get(salons.size()-1).getKey() + "-" + films.get(films.size()-1).getKey());
        lblSalon1.setWidth("800px");
        Label lblSalon2 = new Label("2nd - " + "Salon" + salons.get(salons.size()-2).getKey() + "-" + films.get(films.size()-2).getKey());
        lblSalon2.setWidth("800px");
        Label lblSalon3 = new Label("3th - " + "Salon" + salons.get(salons.size()-3).getKey() + "-" + films.get(films.size()-3).getKey());
        lblSalon3.setWidth("800px");

        Label lblSession1 = new Label("1st - " + sessions.get(sessions.size()-1).getKey());
        Label lblSession2 = new Label("2nd - " + sessions.get(sessions.size()-2).getKey());
        Label lblSession3 = new Label("3th - " + sessions.get(sessions.size()-3).getKey());

        //HEADER
        H1 h1 = new H1("Statistics");
        h1.setHeight("50px");

        //LAYOUT
        VerticalLayout title = new VerticalLayout(h1);
        title.setAlignItems(Alignment.START);

        VerticalLayout verticalLayout = new VerticalLayout(new HorizontalLayout(h2Film, h2Cinema)
                //new HorizontalLayout(lblFilm1, lblCinema1),
                //new HorizontalLayout(lblFilm2, lblCinema2),
                //new HorizontalLayout(lblFilm3, lblCinema3)
        );
        verticalLayout.setAlignItems(Alignment.BASELINE);
        setSpacing(true);

        VerticalLayout verticalLayout1 = new VerticalLayout(new HorizontalLayout(h2Salon, h2Session)
                //new HorizontalLayout(lblSalon1, lblSession1),
                //new HorizontalLayout(lblSalon2, lblSession2),
                //new HorizontalLayout(lblSalon3, lblSession3)
        );
        verticalLayout1.setAlignItems(Alignment.BASELINE);
        verticalLayout1.setSpacing(true);

        add(title, verticalLayout, verticalLayout1);
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
