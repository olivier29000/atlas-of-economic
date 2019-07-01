package fr.diginamic.model;

public class ProduitSitc {

	Integer productId;
	String productCode;
	String productNameShortEn;

	/**
	 * Constructeur
	 * 
	 * @param productId
	 * @param productCode
	 * @param productNameShortEn
	 * @param productType
	 * @param productTypeString
	 */
	public ProduitSitc(Integer productId, String productCode, String productNameShortEn) {
		super();
		this.productId = productId;
		this.productCode = productCode;
		this.productNameShortEn = productNameShortEn;

	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * Setter
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * Setter
	 * 
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the productNameShortEn
	 */
	public String getProductNameShortEn() {
		return productNameShortEn;
	}

	/**
	 * Setter
	 * 
	 * @param productNameShortEn
	 *            the productNameShortEn to set
	 */
	public void setProductNameShortEn(String productNameShortEn) {
		this.productNameShortEn = productNameShortEn;
	}

}
