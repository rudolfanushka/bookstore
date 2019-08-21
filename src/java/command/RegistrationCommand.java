package command;

import dao.UserDAOImpl;
import data.User;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {

    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_REPEAT_PASSWORD = "repeat_password";

    private String name;
    private String login;
    private String password;
    private String repeat_password;
    private String page;

    @Override
    public String execute(HttpServletRequest request) {
        name = request.getParameter(PARAM_NAME_NAME);
        login = request.getParameter(PARAM_NAME_LOGIN);
        password = request.getParameter(PARAM_NAME_PASSWORD);
        repeat_password = request.getParameter(PARAM_NAME_REPEAT_PASSWORD);

        if (password.equals(repeat_password)) {
            User user = new User(name, login, password);
            UserDAOImpl impl = null;
            impl = new UserDAOImpl();
            impl.addNewUser(user);
            page = new AllBooksCommand().execute(request);
        }
        return page;
    }
}
