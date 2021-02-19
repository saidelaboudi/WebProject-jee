package ensias.teams.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ensias.teams.dao.DAOFactory;


/**
 * @author Yasser
 *
 */
@WebListener
public class InitialisationDaoFactory implements ServletContextListener {

	private static final String ATT_DAO_FACTORY = "daofactory";

    private DAOFactory          daoFactory;
    

    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext servletContext = event.getServletContext();
    	
    	this.daoFactory = DAOFactory.getInstance();
    	//Garder la dao dans une attribut pour porté toute l'application
    	servletContext.setAttribute(ATT_DAO_FACTORY,this.daoFactory);
    }
	
    public void contextDestroyed(ServletContextEvent event)  { 
    	
    }

	
}
