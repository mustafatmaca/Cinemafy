package com.cinemafy.backend.bootstrap;

import com.cinemafy.backend.models.Category;
import com.cinemafy.backend.models.Cinema;
import com.cinemafy.backend.models.Film;
import com.cinemafy.backend.models.User;
import com.cinemafy.backend.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BootstrapData implements CommandLineRunner {

    private final CategoryService categoryService;
    private final CinemaService cinemaService;
    private final FilmService filmService;
    private final SalonService salonService;
    private final SessionService sessionService;
    private final UserService userService;

    public BootstrapData(CategoryService categoryService, CinemaService cinemaService, FilmService filmService,
                         SalonService salonService, SessionService sessionService, UserService userService) {
        this.categoryService = categoryService;
        this.cinemaService = cinemaService;
        this.filmService = filmService;
        this.salonService = salonService;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        //USER
        User user = new User();
        user.setFirstName("Mustafa");
        user.setLastName("Atmaca");
        user.setEmail("mustafaatmaca362@gmail.com");
        user.setPassword("123456");
        userService.save(user);


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

        //CINEMA
        cinemaService.saveAll(
                Stream.of("Edirne-Erasta AVM", "İstanbul-Forum İstanbul", "Kayseri-Forum Kayseri",
                        "İzmir-Erasta İzmir AVM", "İstanbul-Mall of İstanbul", "Edirne-Kipa AVM")
                        .map(name -> {
                            String[] split = name.split("-");
                            Cinema cinema = new Cinema();
                            cinema.setCity(split[0]);
                            cinema.setName(split[1]);
                            return cinema;
                        }).collect(Collectors.toList()));

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
    }
}
