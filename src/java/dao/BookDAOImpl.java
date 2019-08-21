package dao;

import data.Book;
import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    public static final String ALL_BOOKS = "SELECT * FROM BOOK";

    public List<Book> allBooks() {
        Connection con = null;
        ConnectionPool pool = null;
        PreparedStatement ps = null;

        try {
            pool = ConnectionPool.getInstance();
            con = pool.getConnection();
            ps = con.prepareStatement(ALL_BOOKS);
            ResultSet rs = ps.executeQuery();
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getInt("price"));
                book.setYear(rs.getInt("year"));
                book.setPublisher(rs.getString("publisher"));
                book.setGenre(rs.getString("genre"));
                books.add(book);
            }
            return books;
        } catch (InterruptedException | IllegalAccessException | InstantiationException e) {
        } catch (SQLException e) {
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            pool.returnConnection(con);
        }
        return null;
    }
}
