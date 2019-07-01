package fr.diginamic.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;

/**
 * singleton permettant de récupérer une connexion à la base de données
 * 
 * @author Kevin.s
 *
 */
public class ConnectionUtils {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(ConnectionUtils.class);

	/** conn : Connection */
	private static Connection conn = null;

	/**
	 * Constructeur
	 * 
	 */
	private ConnectionUtils() {
	}

	/**
	 * Créer une Connection si elle n'éxiste pas, puis la retourne
	 * 
	 * @return Connection
	 */
	public static Connection getInstance() {

		try {
			if (conn == null || conn.isClosed()) {

				Context initContext = new InitialContext();
				DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/gdt");
				conn = ds.getConnection();
				conn.setAutoCommit(false);
			}
			return conn;
		} catch (SQLException | NamingException e) {
			SERVICE_LOG.error("probleme de récupération de connexion", e);
			throw new TechnicalException("probleme de récupération de connexion", e);
		}

	}

	/**
	 * effectue un commit
	 */
	public static void doCommit() {

		try {
			if (conn != null || conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			SERVICE_LOG.error("impossible de commit", e);
			throw new TechnicalException("impossible de commit", e);
		}

	}

	/**
	 * effectue un rollback
	 */
	public static void doRollback() {
		try {
			if (conn != null || conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			SERVICE_LOG.error("impossible de rollback", e);
			throw new TechnicalException("impossible de rollback", e);
		}

	}

	/**
	 * ferme la connection
	 */
	public static void doClose() {
		try {
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			SERVICE_LOG.error("impossible de rollback", e);
			throw new TechnicalException("impossible de rollback", e);
		}

	}
}
