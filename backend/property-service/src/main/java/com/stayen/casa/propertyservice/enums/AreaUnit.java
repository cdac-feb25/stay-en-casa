package com.stayen.casa.propertyservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AreaUnit {
	
	SQ_M,
	SQ_FT,
	SQ_YARD,
	HECTARE,
	ACRE

}
