package fr.diginamic.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;

/**
 * Classe contenant des méthodes permettant d'éxécuter des requetes
 * update,insert, delete à partir d'une requete sous forme de String
 * 
 * @author Kevin.s
 *
 */
public class QueryUtils {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(QueryUtils.class);

	/**
	 * Constructeur
	 * 
	 */
	private QueryUtils() {
		super();
	}

	/**
	 * execute une requete executeUpdate à partir de la requete passée en
	 * parametre
	 * 
	 * @param query
	 *            requete SQL
	 */
	public static void updateQuery(String query) {
		Statement myStatement = null;
		Connection conn = ConnectionUtils.getInstance();
		try {
			myStatement = conn.createStatement();
			myStatement.executeUpdate(query);
			ConnectionUtils.doCommit();
		} catch (SQLException e) {
			ConnectionUtils.doRollback();
			SERVICE_LOG.error("probleme avec la requete " + query, e);
			throw new TechnicalException("probleme technique avec la base de données ", e);
		} finally {
			if (myStatement != null) {
				try {
					myStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement ", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
			ConnectionUtils.doClose();
		}
	}

}
