package com.cinemafy.ui.admin;

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

@Route(value = "statistics", layout = AdminView.class)
public class StatisticsView extends VerticalLayout {

    private final TicketService ticketService;

    public StatisticsView(TicketService ticketService) {
        this.ticketService = ticketService;

        List<Ticket> tickets = ticketService.findAll();
        List<String> ticketFilms = new ArrayList<>();
        List<String> ticketCinemas = new ArrayList<>();
        List<String> ticketSalon = new ArrayList<>();
        List<String> ticketSession = new ArrayList<>();

        for (Ticket ticket:tickets) {
            ticketFilms.add(ticket.getSession().getFilm().getName());
            ticketCinemas.add(ticket.getSession().getSalon().getCinema().getName());
            ticketSalon.add(ticket.getSession().getSalon().getCinema().getName() + ":" + "Salon" + ticket.getSession().getSalon().getNumber() + "-" + ticket.getSession().getFilm().getName());
            ticketSession.add(ticket.getSession().getStartTime().toString());
        }

        List<Map.Entry<String, Long>> films = sortList(turnMap(ticketFilms));
        List<Map.Entry<String, Long>> cinemas = sortList(turnMap(ticketCinemas));
        List<Map.Entry<String, Long>> salons = sortList(turnMap(ticketSalon));
        List<Map.Entry<String, Long>> sessions = sortList(turnMap(ticketSession));


        H2 h2Film = new H2("Film Statistics");
        h2Film.setWidth("800px");
        H2 h2Cinema = new H2("Cinema Statistics");
        H2 h2Salon = new H2("Salon Statistics");
        h2Salon.setWidth("800px");
        H2 h2Session = new H2("Session Statistics");

        VerticalLayout verticalLayout = new VerticalLayout(h2Film);
        VerticalLayout verticalLayout1 = new VerticalLayout(h2Cinema);
        VerticalLayout verticalLayout2 = new VerticalLayout(h2Salon);
        VerticalLayout verticalLayout3 = new VerticalLayout(h2Session);

        for (int i = 1; i<films.size()+1; i++){
            Label lblFilm = new Label(i + " - " + films.get(films.size()-i).getKey() + " -> " + films.get(films.size()-i).getValue() + " Ticket");
            lblFilm.setWidth("800px");
            verticalLayout.add(lblFilm);
        }

        for (int j = 1; j<cinemas.size()+1; j++){
            Label lblCinema = new Label(j + " - " + cinemas.get(cinemas.size()-j).getKey() + " -> " + cinemas.get(cinemas.size()-j).getValue() + " Ticket");
            lblCinema.setWidth("800px");
            verticalLayout1.add(lblCinema);
        }

        for (int k = 1; k<salons.size()+1; k++){
            Label lblSalon = new Label(k + " - " + salons.get(salons.size()-k).getKey() + " -> " + salons.get(salons.size()-k).getValue() + " Ticket");
            lblSalon.setWidth("800px");
            verticalLayout2.add(lblSalon);
        }

        for (int l = 1; l<sessions.size()+1; l++){
            Label lblSession = new Label(l + " - " + sessions.get(sessions.size()-l).getKey() + " -> " + sessions.get(sessions.size()-l).getValue() + " Ticket");
            lblSession.setWidth("800px");
            verticalLayout3.add(lblSession);
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

        VerticalLayout layout = new VerticalLayout(new HorizontalLayout(verticalLayout,verticalLayout1), new HorizontalLayout(verticalLayout2,verticalLayout3));

        add(title, layout);

    }

    private List<Map.Entry<String, Long>> sortList(Map<String, Long> turnMap) {
        List<Map.Entry<String, Long>> list = new ArrayList<>(turnMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list;
    }

    private Map<String, Long> turnMap(List<String> list) {
        return list.stream().collect(Collectors.groupingBy(w -> w,Collectors.counting()));
    }
}
