package ensias.teams.dao;

/**
 * @author Yasser
 *
 */
public class DAOConfigurationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /*
     * Constructeurs
     */
    public DAOConfigurationException( String message ) {
        super( message );
    }

    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}
