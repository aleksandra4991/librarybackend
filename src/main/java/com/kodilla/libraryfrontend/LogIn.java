package com.kodilla.libraryfrontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class LogIn extends VerticalLayout {

    public LogIn(){

        TextField name = new TextField("Name");
        TextField password = new TextField("Password");
        Button logIn = new Button("Log In");

        logIn.addClickListener(event -> {
            getUI().get().navigate(String.valueOf(ReaderAccount.class));
        });

        add(name,password,logIn);

    }
}
