package fr.diginamic.model;

public class Partenariat {
	
	public int locationId;
	public int partenaireId;
	public int productId;
	public int year;
	public int exportValue;
	public int importValue;
	public double SitcEci;
	public double SitcCoi;
	
	
	
	
	
	public Partenariat(int locationId, int partenaireId, int productId, int year, int exportValue, int importValue,
			double sitcEci, double sitcCoi) {
		super();
		this.locationId = locationId;
		this.partenaireId = partenaireId;
		this.productId = productId;
		this.year = year;
		this.exportValue = exportValue;
		this.importValue = importValue;
		SitcEci = sitcEci;
		SitcCoi = sitcCoi;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getPartenaireId() {
		return partenaireId;
	}
	public void setPartenaireId(int partenaireId) {
		this.partenaireId = partenaireId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getExportValue() {
		return exportValue;
	}
	public void setExportValue(int exportValue) {
		this.exportValue = exportValue;
	}
	public int getImportValue() {
		return importValue;
	}
	public void setImportValue(int importValue) {
		this.importValue = importValue;
	}
	public double getSitcEci() {
		return SitcEci;
	}
	public void setSitcEci(double sitcEci) {
		SitcEci = sitcEci;
	}
	public double getSitcCoi() {
		return SitcCoi;
	}
	public void setSitcCoi(double sitcCoi) {
		SitcCoi = sitcCoi;
	}
	
	

}
