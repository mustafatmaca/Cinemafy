package com.cinemafy.ui;
/**
 * @author Mustafa Atmaca
 */

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "statistic", layout = MainView.class)
public class StatisticView extends VerticalLayout {

    public StatisticView() {
        System.out.println("StatisticsView");

        Label label = new Label("Statistic View");

        setAlignItems(Alignment.CENTER);

        add(label);
    }

}
