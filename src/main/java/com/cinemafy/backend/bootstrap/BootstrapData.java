package com.cinemafy.backend.bootstrap;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.*;
import com.cinemafy.backend.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    private final CategoryService categoryService;
    private final CinemaService cinemaService;
    private final FilmService filmService;
    private final SalonService salonService;
    private final SessionService sessionService;
    private final UserService userService;
    private final TicketService ticketService;

    public BootstrapData(CategoryService categoryService, CinemaService cinemaService, FilmService filmService,
                         SalonService salonService, SessionService sessionService, UserService userService, TicketService ticketService) {
        this.categoryService = categoryService;
        this.cinemaService = cinemaService;
        this.filmService = filmService;
        this.salonService = salonService;
        this.sessionService = sessionService;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @Override
    public void run(String... args) throws Exception {

        //CATEGORY
        Category action = new Category();
        action.setGenre("Action");
        categoryService.save(action);

        Category drama = new Category();
        drama.setGenre("Drama");
        categoryService.save(drama);

        Category historical = new Category();
        historical.setGenre("Historical");
        categoryService.save(historical);

        Category comedy = new Category();
        comedy.setGenre("Comedy");
        categoryService.save(comedy);

        Category crime = new Category();
        crime.setGenre("Crime and Mystery");
        categoryService.save(crime);

        Category fantasy = new Category();
        fantasy.setGenre("Fantasy");
        categoryService.save(fantasy);

        Category horror = new Category();
        horror.setGenre("Horror");
        categoryService.save(horror);

        Category romance = new Category();
        romance.setGenre("Romance");
        categoryService.save(romance);

        Category scifi = new Category();
        scifi.setGenre("Sci-fi");
        categoryService.save(scifi);

        Category western = new Category();
        western.setGenre("Western");
        categoryService.save(western);


        //FILM
        Film interstellar = new Film();
        interstellar.setName("Interstellar");
        interstellar.setRuntime("2h49m");
        interstellar.setSrc("https://tr.web.img3.acsta.net/r_1920_1080/img/08/fe/08feaecbc56c480c082003c632f3bc2f.jpg");
        interstellar.setCategory(scifi);
        filmService.save(interstellar);

        Film lotr = new Film();
        lotr.setName("Lord Of The Rings: Two Tower");
        lotr.setRuntime("2h59m");
        lotr.setSrc("https://tr.web.img4.acsta.net/r_1920_1080/img/6f/b4/6fb4b6e04af5e72f61b265be07143748.jpg");
        lotr.setCategory(fantasy);
        filmService.save(lotr);

        Film dune = new Film();
        dune.setName("Dune");
        dune.setRuntime("2h10m");
        dune.setSrc("https://tr.web.img4.acsta.net/r_1920_1080/pictures/20/04/30/20/27/2963799.jpg");
        dune.setCategory(scifi);
        filmService.save(dune);

        Film eternals = new Film();
        eternals.setName("Eternals");
        eternals.setRuntime("1h56m");
        eternals.setSrc("https://tr.web.img4.acsta.net/r_1920_1080/pictures/21/05/25/10/14/2918182.jpg");
        eternals.setCategory(fantasy);
        filmService.save(eternals);


        //CINEMA
        Cinema cinema = new Cinema();
        cinema.setName("Erasta AVM");
        cinema.setCity("Edirne");
        cinemaService.save(cinema);

        Cinema cinema1 = new Cinema();
        cinema1.setName("Kipa AVM");
        cinema1.setCity("Edirne");
        cinemaService.save(cinema1);

        Cinema cinema2 = new Cinema();
        cinema2.setName("Forum İstanbul");
        cinema2.setCity("İstanbul");
        cinemaService.save(cinema2);

        Cinema cinema3 = new Cinema();
        cinema3.setName("Mall of İstanbul");
        cinema3.setCity("İstanbul");
        cinemaService.save(cinema3);

        Cinema cinema4 = new Cinema();
        cinema4.setName("Venezia AVM");
        cinema4.setCity("İstanbul");
        cinemaService.save(cinema4);

        Cinema cinema5 = new Cinema();
        cinema5.setName("Erasta İzmir AVM");
        cinema5.setCity("İzmir");
        cinemaService.save(cinema5);

        Cinema cinema6 = new Cinema();
        cinema6.setName("Forum Kayseri");
        cinema6.setCity("Kayseri");
        cinemaService.save(cinema6);


        //SALON
        Salon salon1 = new Salon();
        salon1.setNumber("1");
        salon1.setSeatCapacity("45");
        salon1.setCinema(cinema);
        salonService.save(salon1);

        Salon salon2 = new Salon();
        salon2.setNumber("2");
        salon2.setSeatCapacity("60");
        salon2.setCinema(cinema);
        salonService.save(salon2);

        Salon salon3 = new Salon();
        salon3.setNumber("3");
        salon3.setSeatCapacity("30");
        salon3.setCinema(cinema);
        salonService.save(salon3);

        Salon salon4 = new Salon();
        salon4.setNumber("4");
        salon4.setSeatCapacity("90");
        salon4.setCinema(cinema);
        salonService.save(salon4);

        Salon s1 = new Salon();
        s1.setNumber("1");
        s1.setSeatCapacity("45");
        s1.setCinema(cinema1);
        salonService.save(s1);

        Salon s2 = new Salon();
        s2.setNumber("2");
        s2.setSeatCapacity("60");
        s2.setCinema(cinema1);
        salonService.save(s2);

        Salon s3 = new Salon();
        s3.setNumber("3");
        s3.setSeatCapacity("30");
        s3.setCinema(cinema1);
        salonService.save(s3);

        Salon s4 = new Salon();
        s4.setNumber("4");
        s4.setSeatCapacity("90");
        s4.setCinema(cinema1);
        salonService.save(s4);

        Salon sl1 = new Salon();
        sl1.setNumber("1");
        sl1.setSeatCapacity("45");
        sl1.setCinema(cinema2);
        salonService.save(sl1);

        Salon sl2 = new Salon();
        sl2.setNumber("2");
        sl2.setSeatCapacity("60");
        sl2.setCinema(cinema2);
        salonService.save(sl2);

        Salon sl3 = new Salon();
        sl3.setNumber("3");
        sl3.setSeatCapacity("30");
        sl3.setCinema(cinema2);
        salonService.save(sl3);

        Salon sl4 = new Salon();
        sl4.setNumber("4");
        sl4.setSeatCapacity("90");
        sl4.setCinema(cinema2);
        salonService.save(sl4);

        Salon sln1 = new Salon();
        sln1.setNumber("1");
        sln1.setSeatCapacity("45");
        sln1.setCinema(cinema3);
        salonService.save(sln1);

        Salon sln2 = new Salon();
        sln2.setNumber("2");
        sln2.setSeatCapacity("60");
        sln2.setCinema(cinema3);
        salonService.save(sln2);

        Salon sln3 = new Salon();
        sln3.setNumber("3");
        sln3.setSeatCapacity("30");
        sln3.setCinema(cinema3);
        salonService.save(sln3);

        Salon sln4 = new Salon();
        sln4.setNumber("4");
        sln4.setSeatCapacity("90");
        sln4.setCinema(cinema3);
        salonService.save(sln4);

        Salon sal1 = new Salon();
        sal1.setNumber("1");
        sal1.setSeatCapacity("45");
        sal1.setCinema(cinema4);
        salonService.save(sal1);

        Salon sal2 = new Salon();
        sal2.setNumber("2");
        sal2.setSeatCapacity("60");
        sal2.setCinema(cinema4);
        salonService.save(sal2);

        Salon sal3 = new Salon();
        sal3.setNumber("3");
        sal3.setSeatCapacity("30");
        sal3.setCinema(cinema4);
        salonService.save(sal3);

        Salon sal4 = new Salon();
        sal4.setNumber("4");
        sal4.setSeatCapacity("90");
        sal4.setCinema(cinema4);
        salonService.save(sal4);

        Salon sn1 = new Salon();
        sn1.setNumber("1");
        sn1.setSeatCapacity("45");
        sn1.setCinema(cinema5);
        salonService.save(sn1);

        Salon sn2 = new Salon();
        sn2.setNumber("2");
        sn2.setSeatCapacity("60");
        sn2.setCinema(cinema5);
        salonService.save(sn2);

        Salon sn3 = new Salon();
        sn3.setNumber("3");
        sn3.setSeatCapacity("30");
        sn3.setCinema(cinema5);
        salonService.save(sn3);

        Salon sn4 = new Salon();
        sn4.setNumber("4");
        sn4.setSeatCapacity("90");
        sn4.setCinema(cinema5);
        salonService.save(sn4);

        Salon ss1 = new Salon();
        ss1.setNumber("1");
        ss1.setSeatCapacity("45");
        ss1.setCinema(cinema6);
        salonService.save(ss1);

        Salon ss2 = new Salon();
        ss2.setNumber("2");
        ss2.setSeatCapacity("60");
        ss2.setCinema(cinema6);
        salonService.save(ss2);

        Salon ss3 = new Salon();
        ss3.setNumber("3");
        ss3.setSeatCapacity("30");
        ss3.setCinema(cinema6);
        salonService.save(ss3);

        Salon ss4 = new Salon();
        ss4.setNumber("4");
        ss4.setSeatCapacity("90");
        ss4.setCinema(cinema6);
        salonService.save(ss4);


        //SESSION
        List<String> times = new ArrayList<>();
        times.add("9:00-12:00");
        times.add("12:00-15:00");
        times.add("15:00-18:00");
        times.add("18:00-21:00");


        for (String time : times) {
            Session session = new Session();
            session.setTime(time);
            sessionService.save(session);
        }


        //USER
        User user = new User();
        user.setFirstName("Mustafa");
        user.setLastName("Atmaca");
        user.setEmail("mustafaatmaca362@gmail.com");
        user.setPassword("123456");
        userService.save(user);

        User user1 = new User();
        user1.setFirstName("user");
        user1.setLastName("user");
        user1.setEmail("user");
        user1.setPassword("password");
        userService.save(user1);

        User user2 = new User();
        user2.setFirstName("Hello");
        user2.setLastName("World");
        user2.setEmail("hello");
        user2.setPassword("world");
        userService.save(user2);


        //TICKET
        Ticket ticket = new Ticket();
        ticket.setFilm("Dune");
        ticket.setCinema("Forum Kayseri");
        ticket.setSalon("4");
        ticket.setSession("18:00-21:00");
        ticket.setDate(LocalDate.now());
        ticket.setUser(user);
        ticketService.save(ticket);

        Ticket ticket1 = new Ticket();
        ticket1.setFilm("Eternals");
        ticket1.setCinema("Mall of İstanbul ");
        ticket1.setSalon("3");
        ticket1.setSession("15:00-18:00");
        ticket1.setDate(LocalDate.now());
        ticket1.setUser(user1);
        ticketService.save(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setFilm("Lord Of The Rings: Two Tower");
        ticket2.setCinema("Venezia AVM");
        ticket2.setSalon("2");
        ticket2.setSession("9:00-12:00");
        ticket2.setDate(LocalDate.now());
        ticket2.setUser(user1);
        ticketService.save(ticket2);

        Ticket ticket3 = new Ticket();
        ticket3.setFilm("Interstellar");
        ticket3.setCinema("Mall of İstanbul");
        ticket3.setSalon("1");
        ticket3.setSession("12:00-15:00");
        ticket3.setDate(LocalDate.now());
        ticket3.setUser(user2);
        ticketService.save(ticket3);
    }
}
