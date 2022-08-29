package io.nzbee.domain.promotion;

import java.time.LocalDateTime;

public class BuyNGetNFree extends Promotion {

	public BuyNGetNFree(String promotionCode, String promotionTypeCode, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt) {
		super(promotionCode, promotionTypeCode, promotionStartDt, promotionEndDt);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void compute() {
		// TODO Auto-generated method stub
		
	}

}
