create table document_data (
	docId LONG not null primary key,
	fileId LONG,
	userId LONG,
	req_name VARCHAR(75) null,
	req_email VARCHAR(75) null,
	sign_email VARCHAR(75) null,
	doc_type VARCHAR(75) null,
	doc_status VARCHAR(75) null,
	doc_deadline VARCHAR(75) null,
	doc_description VARCHAR(75) null,
	file_name VARCHAR(75) null,
	file_type VARCHAR(75) null,
	file_blob BLOB,
	file_md5 VARCHAR(75) null,
	req_dateCreated VARCHAR(75) null,
	req_dateModified VARCHAR(75) null,
	req_signature VARCHAR(75) null,
	sign_name VARCHAR(75) null
);

create table genkey_data (
	userId LONG not null primary key,
	key_version LONG,
	key_dateCreated VARCHAR(75) null,
	privatekey_Data VARCHAR(75) null,
	publickey_Data VARCHAR(75) null,
	salt_Data VARCHAR(75) null,
	vector_Data VARCHAR(75) null
);