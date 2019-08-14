package com.kodilla.libraryfrontend;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.service.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "true")
public class ReaderAccount extends VerticalLayout {

    Button logOut = new Button("Log out");

    private BookService bookService = BookService.getInstance();
    private Grid<Book> grid = new Grid<>(Book.class);

    private TextField filter = new TextField();

    public ReaderAccount(){

        logOut.addClickListener(event -> getUI().get().navigate(String.valueOf(MainPage.class)));

        grid.setColumns("title", "author", "year", "rented","genre");
        add(grid);

    }

    private void update() {
        grid.setItems(bookService.findByTitle(filter.getValue()));
    }

    public void refresh() {
        grid.setItems(bookService.getAllBooks());
    }
}
