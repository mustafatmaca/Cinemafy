package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


@Route(value = "home", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class HomeView extends VerticalLayout {

    public HomeView() {
        setWidth("%100");
        setHeight("%100");
        setPadding(true);
        setJustifyContentMode(JustifyContentMode.BETWEEN);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        H1 h1 = new H1("Movie In Theaters");
        h1.setHeight("100px");
        H1 h2 = new H1("Theaters");
        h2.setHeight("100px");

        Label eternals = new Label("Eternals");
        eternals.setHeight("40px");
        Label dune = new Label("Dune");
        dune.setHeight("40px");

        Button button1 = new Button("Button1");
        Button button2 = new Button("Button2");

        String src = "https://tr.web.img4.acsta.net/r_1920_1080/pictures/20/04/30/20/27/2963799.jpg";
        Image img = new Image(src, "Dune");
        img.setHeight("384px");
        img.setWidth("256px");

        String src1 = "https://tr.web.img4.acsta.net/r_1920_1080/pictures/21/05/25/10/14/2918182.jpg";
        Image img1 = new Image(src1, "Eternals");
        img1.setHeight("384px");
        img1.setWidth("256px");

        HorizontalLayout title1 = new HorizontalLayout(h1);
        title1.setAlignItems(Alignment.START);

        VerticalLayout verticalLayout1 = new VerticalLayout(img,dune);
        VerticalLayout verticalLayout2 = new VerticalLayout(img1,eternals);

        HorizontalLayout title2 = new HorizontalLayout(h2);
        title2.setAlignItems(Alignment.START);

        HorizontalLayout horizontalLayout = new HorizontalLayout(verticalLayout1, verticalLayout2);
        horizontalLayout.setAlignItems(Alignment.CENTER);


        setAlignItems(Alignment.START);
        add(title1, horizontalLayout, title2);
    }
}
