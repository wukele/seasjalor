CREATE TABLE document_category(
	 id INT PRIMARY KEY  AUTO_INCREMENT  ,
	 NAME VARCHAR(200)  ,
	 parent_id INT  ,
	description VARCHAR(200)  ,
	 create_time DATE ,
	creator VARCHAR(200) ,
	upadator VARCHAR(200) ,
	update_time DATE 
	
-- )