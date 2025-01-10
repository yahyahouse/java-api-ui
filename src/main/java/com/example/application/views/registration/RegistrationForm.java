package com.example.application.views.registration;

import com.example.application.model.RegisterUser;
import com.example.application.model.Users;
import com.example.application.service.UsersService;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.stream.Stream;

//@Route(value = "/registration", autoLayout = false)
public class RegistrationForm extends FormLayout {
    @Autowired
    UsersService usersService;
    private H3 title;
    private TextField fullName;
    private TextField message;
    private EmailField email;
    private PasswordField password;
    private PasswordField passwordConfirm;
    private Select<String> role;
    private Span errorMessageField;
    private Button submitButton;
    private Button cancelButton;

    public RegistrationForm() {
        title = new H3("Signup form");
        fullName = new TextField("Full Name");
        email = new EmailField("Email");
        message = new TextField("Message");
        role = new Select<>();
        role.setLabel("Role");
        role.setItems("Admin", "User");
        role.setValue("User");
        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");
        setRequiredIndicatorVisible(fullName, role, email, password,
                passwordConfirm);
        errorMessageField = new Span();
        submitButton = new Button("Sign up");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelButton = new Button("Cancel");
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        add(title, fullName, email, message, role, password, passwordConfirm,
                errorMessageField, submitButton, cancelButton);
        setMaxWidth("500px");

        setResponsiveSteps(new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));
        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(errorMessageField, 2);
        setColspan(fullName, 2);

        submitButton.addClickListener(event -> {
            try {
                if (password.getValue().equals(passwordConfirm.getValue())) {
                    RegisterUser registerUser = new RegisterUser();
                    registerUser.setName(fullName.getValue());
                    registerUser.setEmail(email.getValue());
                    registerUser.setPassword(password.getValue());
                    registerUser.setRole(role.getValue());
                    registerUser.setMessage(message.getValue());
                    saveUsers(registerUser);
                    Notification.show("User saved successfully");
                    UI.getCurrent().navigate("login");
                }else {
                    Notification.show("Password does not match");
                }
            }catch (Exception e){
                Notification.show("Email already exists");
            }
        });

        cancelButton.addClickListener(event -> {
            UI.getCurrent().navigate("login");
        });
    }

    private void saveUsers(RegisterUser registerUser) {
        registerUser.setId(UUID.randomUUID().toString());
        usersService.saveUsers(new Users(registerUser.getId(), registerUser.getName(), registerUser.getEmail(), registerUser.getPassword(), registerUser.getRole(), registerUser.getMessage()));
    }



    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
