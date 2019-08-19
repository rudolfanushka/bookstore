package command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import dao.BookDAOImpl;
import data.Book;


public class AllBooksCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
      
        String page;
        BookDAOImpl bk =  new BookDAOImpl();
        List<Book> book = bk.allBooks();
        request.setAttribute("allBooks", book);
        page = ConfigurationManager.getProperty("all_books");
        return page;
    }
}
