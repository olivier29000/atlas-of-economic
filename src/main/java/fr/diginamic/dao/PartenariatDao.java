package fr.diginamic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.model.Partenariat;
import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;
import fr.diginamic.utils.ConnectionUtils;

public class PartenariatDao {

	public List<Partenariat> recupererLesPartenariats(Integer pagination) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Partenariat> listeDesPartenariats = new ArrayList<>();

		try {
			preparedStatement = ConnectionUtils.getInstance()
					.prepareStatement("select * from partenariats limit " + pagination);
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();

			while (resultSet.next()) {

				Integer locationId = resultSet.getInt("location_id");
				Integer partenaireId = resultSet.getInt("partner_id");
				Integer productId = resultSet.getInt("product_id");
				Integer year = resultSet.getInt("year");
				Integer exportValue = resultSet.getInt("export_value");
				Integer importValue = resultSet.getInt("import_value");

				double SitcEci = resultSet.getDouble("sitc_eci");
				double SitcCoi = resultSet.getDouble("sitc_coi");

				listeDesPartenariats.add(new Partenariat(locationId, partenaireId, productId, year, exportValue,
						importValue, SitcEci, SitcCoi));
			}

			return listeDesPartenariats;
		} catch (SQLException e) {
			// SERVICE_LOG.error("probleme de selection en base", e);
			throw new TechnicalException("probleme de selection en base", e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// SERVICE_LOG.error("impossible de fermer le resultSet",
					// e);
					throw new TechnicalException("impossible de fermer le resultSet", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// SERVICE_LOG.error("impossible de fermer le statement",
					// e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
			ConnectionUtils.doClose();
		}

	}

	public void insererPartenariats(List<Partenariat> listeDePartenariats) {

		// code pour inserer produit dans la table produits de MySQL

		Connection maConnection = null;
		PreparedStatement stat = null;

		// Récupération des informations dans database.properties
		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String user = monFichierConf.getString("database.user");
		String url = monFichierConf.getString("database.url");
		String password = monFichierConf.getString("database.password");

		try {
			maConnection = DriverManager.getConnection(url, user, password);
			maConnection.setAutoCommit(false);

			Statement monStatement = maConnection.createStatement();

			String requeteSql = "INSERT INTO partenariats (location_id,partner_id,product_id,year,export_value,import_value,sitc_eci,sitc_coi) VALUES ";
			for (Partenariat partenariat : listeDePartenariats) {
				requeteSql = requeteSql + " ,(" + partenariat.getLocationId() + "," + partenariat.getPartenaireId()
						+ "," + partenariat.getProductId() + "," + partenariat.getYear() + ","
						+ partenariat.getExportValue() + "," + partenariat.getImportValue() + ","
						+ partenariat.getSitcEci() + "," + partenariat.getSitcCoi() + ")";
			}
			for (int i = 0; i < listeDePartenariats.size(); i++) {
				if (i == 0) {
					requeteSql = requeteSql + " (" + listeDePartenariats.get(i).getLocationId() + ","
							+ listeDePartenariats.get(i).getPartenaireId() + ","
							+ listeDePartenariats.get(i).getProductId() + "," + listeDePartenariats.get(i).getYear()
							+ "," + listeDePartenariats.get(i).getExportValue() + ","
							+ listeDePartenariats.get(i).getImportValue() + ","
							+ listeDePartenariats.get(i).getSitcEci() + "," + listeDePartenariats.get(i).getSitcCoi()
							+ ")";
				} else {
					requeteSql = requeteSql + " ,(" + listeDePartenariats.get(i).getLocationId() + ","
							+ listeDePartenariats.get(i).getPartenaireId() + ","
							+ listeDePartenariats.get(i).getProductId() + "," + listeDePartenariats.get(i).getYear()
							+ "," + listeDePartenariats.get(i).getExportValue() + ","
							+ listeDePartenariats.get(i).getImportValue() + ","
							+ listeDePartenariats.get(i).getSitcEci() + "," + listeDePartenariats.get(i).getSitcCoi()
							+ ")";
				}
			}

			stat = maConnection.prepareStatement(requeteSql);

			stat.executeUpdate();

			// int nb1 = monStatement.executeUpdate("INSERT INTO produits
			// (nom_produit,id_categorie,id_marque) VALUES('"
			// + nomProduit + "'" + idCategorie + "'" + idMarque + ")");

			maConnection.commit();
		} catch (SQLException e) {
			// throw new TechnicalException("une exception est apparut", e);
		} finally {
			try {
				maConnection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// throw new TechnicalException("boooom", e);
			}
		}

	}
}
