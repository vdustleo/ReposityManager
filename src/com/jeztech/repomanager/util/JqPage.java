package com.jeztech.repomanager.util;

import lombok.Data;

@Data
public class JqPage {
	private Integer rows = 10;
	private Integer page = 1;
	private Integer totalPage = 0;
	private Integer record = 0;
	
	private String sord = "";
	private String nd = "";
	private String sidx = "";
	
	private String search = "";
}
