package com.example.application.views.login;

import com.example.application.model.Login;
import com.example.application.model.Users;
import com.example.application.service.JWTService;
import com.example.application.service.UsersService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "/login", autoLayout = false)
@Slf4j
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
    @Autowired
    UsersService usersService;
    @Autowired
    private JWTService jwtUtil;
    private final LoginForm login = new LoginForm();

    public LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        H1 title = new H1("Welcome to My App");

        // Login Form
        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(createLoginI18n()); // Custom text
        loginForm.setForgotPasswordButtonVisible(true);

        // Handle login logic
        loginForm.addLoginListener(event -> {
            String email = event.getUsername();
            String password = event.getPassword();
            Users user = usersService.findByEmailAndPassword(email, password);

            if (user != null) {
                String token = jwtUtil.generateToken(user);
                VaadinSession.getCurrent().setAttribute("user", user.getEmail());
                VaadinSession.getCurrent().setAttribute("jwt", token);
                Login.setLoggedIn(true); // Set login state
                Notification.show("Login successful!", 2000, Notification.Position.BOTTOM_CENTER);
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
        String token = (String) VaadinSession.getCurrent().getAttribute("jwt");
        String email = (String) VaadinSession.getCurrent().getAttribute("user");

        if (token == null || email == null) {
            beforeEnterEvent.forwardTo("login");
            return;
        }

        Users user = usersService.findByEmail(email);

        try {
            if (user == null || !jwtUtil.isTokenValid(token, user)) {
                beforeEnterEvent.forwardTo("login");
            }
        } catch (IllegalStateException e) {
            // Handle token expiration gracefully
            VaadinSession.getCurrent().setAttribute("jwt", null);
            VaadinSession.getCurrent().setAttribute("user", null);
            beforeEnterEvent.forwardTo("login");
        }
    }

}
