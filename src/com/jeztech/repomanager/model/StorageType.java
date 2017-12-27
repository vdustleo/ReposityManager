package com.jeztech.repomanager.model;

/** 此处修改，用于记录数据的类型，而并非原来的状态 **/
public interface StorageType {

	// 缺省状态
	public static final Integer DEFAULT = 0;
	
	// 入库记录
	public static final Integer IN_STORAGE = 1;
	
	// 出库记录
	public static final Integer OUT_STORAGE = 2;
	
	// 删除
	public static final Integer REMOVE_STORAGE = 4;
}
