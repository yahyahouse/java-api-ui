package com.example.application.views.login;

import com.example.application.model.Login;
import com.example.application.model.Users;
import com.example.application.service.UsersService;
import com.example.application.views.menu.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "login",autoLayout = false)
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
    @Autowired
    UsersService usersService;
    private final LoginForm login = new LoginForm();

    public LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        H1 title = new H1("Welcome to Vaadin App");

        // Login Form
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(createLoginI18n()); // Custom text
        loginForm.setForgotPasswordButtonVisible(true);

        // Handle login logic
        loginForm.addLoginListener(event -> {
            String email = event.getUsername();
            String password = event.getPassword();
            Users user = usersService.findByEmailAndPassword(email, password);

            // Replace with your actual authentication logic
            if (user != null) {
                Login.setLoggedIn(true); // Set login state
                Notification.show("Login successful!");
                loginForm.getUI().ifPresent(ui -> ui.navigate("hello-world")); // Redirect to home
            } else {
                loginForm.setError(true); // Show error message
            }
        });
        // Forgot Password Button Action
        Button forgotPasswordButton = new Button("Forgot Password");
        forgotPasswordButton.addClickListener(event -> Notification.show("Reset password link sent!"));

        forgotPasswordButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // Add components to layout
        add(title, loginForm, forgotPasswordButton);
    }

    private LoginI18n createLoginI18n() {
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Vaadin App");
        i18n.getHeader().setDescription("Login with your credentials.");
        i18n.getForm().setUsername("Email");
        i18n.getForm().setPassword("Password");
        i18n.getForm().setSubmit("Login");
        i18n.getForm().setForgotPassword("Forgot password?");
        i18n.getErrorMessage().setTitle("Invalid credentials");
        i18n.getErrorMessage().setMessage("Check your username and password and try again.");
        return i18n;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (!Login.isLoggedIn()) {
            beforeEnterEvent.forwardTo("login"); // Redirect to login if not logged in
        }
    }
}
