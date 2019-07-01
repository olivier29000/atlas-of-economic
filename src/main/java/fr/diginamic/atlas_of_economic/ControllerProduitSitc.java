package fr.diginamic.atlas_of_economic;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.diginamic.dao.ProduitSitcDao;
import fr.diginamic.model.ProduitSitc;
import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;

public abstract class ControllerProduitSitc {

	public static void insererProduits() throws IOException {

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		} catch (SQLException e) {
			throw new TechnicalException("l'appel du driver a foiré mec!", e);
		}

		File file = new File("C:/patates/Java/2019.04.16/16.04.2019/sitc_product.csv");
		List<String> listeDeLignesProduits = FileUtils.readLines(file, "utf8");
		List<String> listeDeLignes = new ArrayList();
		for (int i = 0; i < listeDeLignesProduits.size(); i++) {
			listeDeLignes.add(listeDeLignesProduits.get(i).replaceAll("\"", ""));
		}
		List<ProduitSitc> listeDeProduitsSitc = new ArrayList();

		listeDeLignes.remove(0);

		for (String ligne : listeDeLignes) {
			String[] attributsDuProduitSitc = ligne.split(",");
			ProduitSitc produitSitc = new ProduitSitc((Integer) Integer.parseInt(attributsDuProduitSitc[0]),
					attributsDuProduitSitc[1], attributsDuProduitSitc[2]);
			listeDeProduitsSitc.add(produitSitc);
		}

		ProduitSitcDao produitSitcDao = new ProduitSitcDao();
		produitSitcDao.insererProduitsSitc(listeDeProduitsSitc);

	}

}
