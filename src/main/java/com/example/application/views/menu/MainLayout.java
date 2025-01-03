package com.example.application.views.menu;

import com.example.application.model.Login;
import com.example.application.model.Users;
import com.example.application.service.JWTService;
import com.example.application.service.UsersService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



@Layout
public class MainLayout extends AppLayout implements BeforeEnterObserver{

    @Autowired
    private UsersService usersService;
    @Autowired
    private JWTService jwtUtil;
    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Span appName = new Span("Java-api");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        List<MenuEntry> menuEntries = MenuConfiguration.getMenuEntries();
        menuEntries.forEach(entry -> {
            if (entry.icon() != null) {
                nav.addItem(new SideNavItem(entry.title(), entry.path(), new SvgIcon(entry.icon())));
            } else {
                nav.addItem(new SideNavItem(entry.title(), entry.path()));
            }
        });

        return nav;
    }

    private Footer createFooter() {
        Footer footer = new Footer();

        Button logoutButton = new Button("Logout");
        logoutButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        logoutButton.addClickListener(e -> {
            UI.getCurrent().getSession().close();
            UI.getCurrent().navigate("login");
            VaadinSession.getCurrent().setAttribute("user", null);
            VaadinSession.getCurrent().setAttribute("jwt", null);

            Login.setLoggedIn(false);
            VaadinSession.getCurrent().setAttribute("jwt", null); // Clears JWT token from the Vaadin session
            VaadinSession.getCurrent().close();
        });
        logoutButton.addClassName("logout-button");
        footer.add(logoutButton);

        return footer;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        return MenuConfiguration.getPageHeader(getContent()).orElse("");
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
        if (user == null || !jwtUtil.isTokenValid(token, user)) {
            beforeEnterEvent.forwardTo("login");
        }
    }
}
