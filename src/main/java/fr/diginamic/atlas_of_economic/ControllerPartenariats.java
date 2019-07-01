package fr.diginamic.atlas_of_economic;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.diginamic.dao.PartenariatDao;
import fr.diginamic.model.Partenariat;
import fr.diginamic.traitement_fichier.Exceptions.TechnicalException;

public abstract class ControllerPartenariats {

	public static void insererLesPartenariats() throws IOException {

		// INSERTION DES INGREDIENTS
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		} catch (SQLException e) {
			throw new TechnicalException("l'appel du driver a foiré mec!", e);
		}
		List<Partenariat> listeDePartenariats = new ArrayList<Partenariat>();

		List<String> listeDeFichiers = new ArrayList<String>();
		listeDeFichiers.add("C:/patates/Java/2019.04.16/16.04.2019/country_partner_sitcproduct4digit_year_1962.csv");
		listeDeFichiers.add("C:/patates/Java/2019.04.16/16.04.2019/country_partner_sitcproduct4digit_year_1963.csv");
		listeDeFichiers.add("C:/patates/Java/2019.04.16/16.04.2019/country_partner_sitcproduct4digit_year_1964.csv");

		for (String fichier : listeDeFichiers) {
			File file = new File(fichier);
			List<String> lines = FileUtils.readLines(file, "utf8");
			lines.remove(0);
			for (String line : lines) {
				String[] attributsDuPartenariat = line.split(",");
				// System.out.println(attributsDuPartenariat[6] == null ? "null"
				// : "pas null");
				// System.out.println(attributsDuPartenariat[6] == "" ? "vide" :
				// "pas vide");
				// System.out.println(attributsDuPartenariat[6].isEmpty() ?
				// "empty" : "pas empty");

				Partenariat partenariat = new Partenariat(Integer.parseInt(attributsDuPartenariat[0]),
						Integer.parseInt(attributsDuPartenariat[1]), Integer.parseInt(attributsDuPartenariat[2]),
						Integer.parseInt(attributsDuPartenariat[3]), Integer.parseInt(attributsDuPartenariat[4]),
						Integer.parseInt(attributsDuPartenariat[5]),
						attributsDuPartenariat[6].isEmpty() ? 0.0 : Double.parseDouble(attributsDuPartenariat[6]),
						attributsDuPartenariat[7].isEmpty() ? 0.0 : Double.parseDouble(attributsDuPartenariat[7]));
				listeDePartenariats.add(partenariat);

			}
		}

		PartenariatDao partenariatDao = new PartenariatDao();
		partenariatDao.insererPartenariats(listeDePartenariats);

	}

}
