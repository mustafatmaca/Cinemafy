package com.cinemafy.backend.bootstrap;
/**
 * @author Mustafa Atmaca
 */

import com.cinemafy.backend.models.*;
import com.cinemafy.backend.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

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
        interstellar.setMinute(169);
        interstellar.setSrc("https://tr.web.img3.acsta.net/r_1920_1080/img/08/fe/08feaecbc56c480c082003c632f3bc2f.jpg");
        interstellar.setCategory(scifi);
        filmService.save(interstellar);

        Film lotr = new Film();
        lotr.setName("Lord Of The Rings: Two Tower");
        lotr.setMinute(179);
        lotr.setSrc("https://tr.web.img4.acsta.net/r_1920_1080/img/6f/b4/6fb4b6e04af5e72f61b265be07143748.jpg");
        lotr.setCategory(fantasy);
        filmService.save(lotr);

        Film dune = new Film();
        dune.setName("Dune");
        dune.setMinute(130);
        dune.setSrc("https://tr.web.img4.acsta.net/r_1920_1080/pictures/20/04/30/20/27/2963799.jpg");
        dune.setCategory(scifi);
        filmService.save(dune);

        Film eternals = new Film();
        eternals.setName("Eternals");
        eternals.setMinute(116);
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

        //SESSION

        Session session = new Session();
        session.setStartTime(LocalTime.of(9,0));
        session.setEndTime(session.getStartTime().plus(Duration.ofMinutes(dune.getMinute())).truncatedTo(ChronoUnit.HOURS));
        session.setFilm(dune);
        session.setSalon(salon1);
        sessionService.save(session);

        Session session1 = new Session();
        session1.setStartTime(session.getEndTime());
        session1.setEndTime(session1.getStartTime().plus(Duration.ofMinutes(interstellar.getMinute())).truncatedTo(ChronoUnit.HOURS));
        session1.setFilm(interstellar);
        session1.setSalon(salon1);
        sessionService.save(session1);

        Session session2 = new Session();
        session2.setStartTime(LocalTime.of(9,0));
        session2.setEndTime(session2.getStartTime().plus(Duration.ofMinutes(eternals.getMinute())).truncatedTo(ChronoUnit.HOURS));
        session2.setFilm(eternals);
        session2.setSalon(sln1);
        sessionService.save(session2);


        //USER
        User user = new User();
        user.setFirstName("Mustafa");
        user.setLastName("Atmaca");
        user.setEmail("mustafaatmaca362@gmail.com");
        user.setPassword("123456");
        user.setUserType("User");
        userService.save(user);

        User user1 = new User();
        user1.setFirstName("user");
        user1.setLastName("user");
        user1.setEmail("user");
        user1.setPassword("password");
        user1.setUserType("User");
        userService.save(user1);

        User user2 = new User();
        user2.setFirstName("Hello");
        user2.setLastName("World");
        user2.setEmail("hello");
        user2.setPassword("world");
        user2.setUserType("User");
        userService.save(user2);

        User user3 = new User();
        user3.setFirstName("admin");
        user3.setLastName("admin");
        user3.setEmail("admin");
        user3.setPassword("admin");
        user3.setUserType("Admin");
        userService.save(user3);

        //TICKET
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setDate(LocalDate.now());
        ticket.setSession(session);
        ticketService.save(ticket);

        Ticket ticket1 = new Ticket();
        ticket1.setUser(user);
        ticket1.setDate(LocalDate.now());
        ticket1.setSession(session1);
        ticketService.save(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setUser(user1);
        ticket2.setDate(LocalDate.now());
        ticket2.setSession(session2);
        ticketService.save(ticket2);

        Ticket ticket3 = new Ticket();
        ticket3.setUser(user1);
        ticket3.setDate(LocalDate.now());
        ticket3.setSession(session1);
        ticketService.save(ticket3);

        Ticket ticket4 = new Ticket();
        ticket4.setUser(user2);
        ticket4.setDate(LocalDate.now());
        ticket4.setSession(session);
        ticketService.save(ticket4);

        Ticket ticket5 = new Ticket();
        ticket5.setUser(user2);
        ticket5.setDate(LocalDate.now());
        ticket5.setSession(session1);
        ticketService.save(ticket5);
    }
}
