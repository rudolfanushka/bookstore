package dao;

import data.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {

    List<Book> allBooks() throws SQLException;

}
