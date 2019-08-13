package com.kodilla.libraryfrontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainPage extends VerticalLayout {

    public MainPage(){
        setSizeFull();

        Label myLibrary = new Label("Aleksandra Library");
        myLibrary.setHeight("700");
        myLibrary.setWidth("400");

        Button createAccount = new Button("Create Account");
        createAccount.addClickListener(event -> {
            getUI().get().navigate(CreateNewAccount.class);
        });

        createAccount.setHeight("200");
        createAccount.setWidth("200");

        Button logIn = new Button("Log In");
        logIn.addClickListener(event -> {
            getUI().get().navigate(LogIn.class);
        });

        logIn.setHeight("200");
        logIn.setWidth("200");

        add(myLibrary,logIn,createAccount);

    }

}
