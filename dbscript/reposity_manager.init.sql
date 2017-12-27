USE repo_manager;

-- 初始化苹果类型
INSERT INTO dic_apple_type(name, value) VALUES(1,  '富士' );
INSERT INTO dic_apple_type(name, value) VALUES(2,  '秦冠' );

-- 初始化苹果尺寸
INSERT INTO dic_apple_size(name, value) VALUES(1,  '60' );
INSERT INTO dic_apple_size(name, value) VALUES(2,  '65' );
INSERT INTO dic_apple_size(name, value) VALUES(3,  '70' );
INSERT INTO dic_apple_size(name, value) VALUES(4,  '75' );
INSERT INTO dic_apple_size(name, value) VALUES(5,  '80' );
INSERT INTO dic_apple_size(name, value) VALUES(6,  '85' );
INSERT INTO dic_apple_size(name, value) VALUES(7,  '90' );

-- 初始化库存记录状态表
INSERT INTO dic_storage_status(name, value) VALUES(0,  'default' );
INSERT INTO dic_storage_status(name, value) VALUES(1,  'in storage' );
INSERT INTO dic_storage_status(name, value) VALUES(2,  'out storage' );
INSERT INTO dic_storage_status(name, value) VALUES(4,  'remove storage' );

-- 初始化果农信息记录状态表
INSERT INTO dic_farmer_status(name, value) VALUES(0,  'deleted' );
INSERT INTO dic_farmer_status(name, value) VALUES(1,  'default' );

-- 系统参数表
INSERT INTO tbl_sys_para(name, value) VALUES('repo_name',  '我的仓库' );
