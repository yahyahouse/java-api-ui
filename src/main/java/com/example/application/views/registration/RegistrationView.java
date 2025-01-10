package com.example.application.views.registration;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
@Route(value = "registration",autoLayout = false)
public class RegistrationView extends VerticalLayout {
    public RegistrationView() {
        setSizeFull();
        RegistrationForm registrationForm = new RegistrationForm();
        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(registrationForm);
    }
}
