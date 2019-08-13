package com.kodilla.libraryfrontend;

import com.kodilla.librarybackend.domain.Reader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class CreateNewAccount extends VerticalLayout {

    public CreateNewAccount(){

        TextField name = new TextField("Name");
        TextField phoneNumber = new TextField("Phone Number");
        TextField emailAddress = new TextField("Email Address");
        Button createNewAccount = new Button("Create an Account");

        createNewAccount.addClickListener(event -> {


        });
    }
}
