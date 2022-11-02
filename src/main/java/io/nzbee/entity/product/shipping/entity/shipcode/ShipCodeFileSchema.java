package io.nzbee.entity.product.shipping.entity.shipcode;
public class ShipCodeFileSchema {
	
	private String shp_cde;
	
	private String shp_nme_en;
	
	private String shp_nme_hk;	
	
	private String shp_nme_cn;	
	
	private String shp_sts;	
	
	private String shp_max_wgt;
	
	
	public String getShp_cde() {
		return shp_cde;
	}

	public void setShp_cde(String shp_cde) {
		this.shp_cde = shp_cde;
	}

	public String getShp_nme_en() {
		return shp_nme_en;
	}

	public void setShp_nme_en(String shp_nme_en) {
		this.shp_nme_en = shp_nme_en;
	}

	public String getShp_nme_hk() {
		return shp_nme_hk;
	}

	public void setShp_nme_hk(String shp_nme_hk) {
		this.shp_nme_hk = shp_nme_hk;
	}

	public String getShp_nme_cn() {
		return shp_nme_cn;
	}

	public void setShp_nme_cn(String shp_nme_cn) {
		this.shp_nme_cn = shp_nme_cn;
	}

	public String getShp_sts() {
		return shp_sts;
	}

	public void setShp_sts(String shp_sts) {
		this.shp_sts = shp_sts;
	}

	public String getShp_max_wgt() {
		return shp_max_wgt;
	}

	public void setShp_max_wgt(String shp_max_wgt) {
		this.shp_max_wgt = shp_max_wgt;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", Ship Code = ").append(shp_cde)
               .append("]");
        return builder.toString();
        
    }
	
}
