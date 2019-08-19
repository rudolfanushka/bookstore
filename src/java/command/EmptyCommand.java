package command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    
    public String execute(HttpServletRequest request) {
		
		String page = ConfigurationManager.getProperty("path.page.index");
                request.getSession().invalidate();
		return page;
	}
}
