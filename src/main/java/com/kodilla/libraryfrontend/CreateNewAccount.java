package com.kodilla.libraryfrontend;


import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.domain.ReaderDto;
import com.kodilla.librarybackend.service.ReaderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route
public class CreateNewAccount extends VerticalLayout {

    TextField name = new TextField("Name");
    TextField phoneNumber = new TextField("Phone Number");
    TextField emailAddress = new TextField("Email Address");
    Button createNewAccount = new Button("Create an Account");

    private Binder<Reader> binder = new Binder<>(Reader.class);

    private ReaderService readerService = new ReaderService();

    private void save() {
        Reader reader = binder.getBean();
        readerService.createReader(reader);
    }

    public CreateNewAccount(){
        HorizontalLayout buttons = new HorizontalLayout(createNewAccount);
        add(name,phoneNumber,emailAddress,createNewAccount);
        binder.bindInstanceFields(this);

        createNewAccount.addClickListener(event -> save());

    }

}
