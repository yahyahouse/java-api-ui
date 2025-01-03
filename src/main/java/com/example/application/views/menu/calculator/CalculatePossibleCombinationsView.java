package com.example.application.views.menu.calculator;

import com.example.application.model.ResultPossibility;
import com.example.application.service.CalculatorService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Calculate Possible Combinations")
@Route(value = "calculate-possible-combinations")
@Menu(order = 3, icon = LineAwesomeIconUrl.CALCULATOR_SOLID)
public class CalculatePossibleCombinationsView extends Composite<VerticalLayout> {
    @Autowired
    CalculatorService calculatorService;

    public CalculatePossibleCombinationsView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        Paragraph textMedium = new Paragraph();
        textMedium.setText(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        FormLayout formLayout2Col = new FormLayout();
        TextField word = new TextField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        TextField result = new TextField();
        result.setReadOnly(true);
        Grid<TablePossibleCombinations> resultPossibleCombinations = new Grid<>(TablePossibleCombinations.class, false);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Calculator");
        h3.setWidth("min-content");
        formLayout2Col.setWidth("100%");
        word.setLabel("Word");
        word.setWidth("min-content");
        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        buttonPrimary.setText("Calculate");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Clear");
        buttonSecondary.setWidth("min-content");
        result.setLabel("Result");
        result.setWidth("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(textMedium);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(word);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);
        layoutColumn2.add(result);
        layoutColumn2.add(resultPossibleCombinations);
        buttonPrimary.addClickListener(e -> {
            ResultPossibility resultPossibility = calculatorService.calculatePossibleCombinations(word.getValue());
            Notification.show("success", 3000, Notification.Position.BOTTOM_CENTER);
            result.setValue(String.valueOf(resultPossibility.getResult()));

            List<TablePossibleCombinations> rows = new ArrayList<>();
            List<String> possibilities = resultPossibility.getPossibilities();
            for (int i = 0; i < possibilities.size(); i++) {
                rows.add(new TablePossibleCombinations(String.valueOf(i + 1), possibilities.get(i)));
            }
            resultPossibleCombinations.addColumn(TablePossibleCombinations::getNumber).setHeader("Number");
            resultPossibleCombinations.addColumn(TablePossibleCombinations::getCombination).setHeader("Possible Combinations");
            resultPossibleCombinations.setItems(rows);
        });
        buttonSecondary.addClickListener(e -> {
            word.clear();
            result.clear();
            resultPossibleCombinations.removeAllColumns();
        });
    }

}
