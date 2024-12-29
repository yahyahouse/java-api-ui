package com.example.application.views.menu.personform;

import com.example.application.model.Users;
import com.example.application.service.UsersService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;


@PageTitle("Person Form")
@Route("person-form")
@Menu(order = 1, icon = LineAwesomeIconUrl.USER)
public class PersonFormView extends Composite<VerticalLayout> {
    @Autowired
    private UsersService usersService;

    public PersonFormView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField name = new TextField();
        TextField message = new TextField();
        PasswordField passwordField = new PasswordField();
        EmailField emailField = new EmailField();
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems("Admin", "User");
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Personal Information");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        name.setLabel("Name");
        emailField.setLabel("Email");
        passwordField.setLabel("Password");
        comboBox.setLabel("Role");
        message.setLabel("Message");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Save");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancel");
        buttonSecondary.setWidth("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(name);
        formLayout2Col.add(emailField);
        formLayout2Col.add(passwordField);
        formLayout2Col.add(comboBox);
        formLayout2Col.add(message);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);
        buttonPrimary.addClickListener(event -> {
            try {
                Users users = new Users();
                users.setName(name.getValue());
                users.setEmail(emailField.getValue());
                users.setPassword(passwordField.getValue());
                users.setRole(comboBox.getValue());
                users.setMessage(message.getValue());

                usersService.saveUsers(users);
                Notification.show("User saved successfully");

            } catch (RuntimeException e) {
                Notification.show("Email already exists");
            }
        });

    }
}
