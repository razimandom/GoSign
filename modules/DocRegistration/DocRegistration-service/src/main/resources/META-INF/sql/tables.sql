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
	req_dateModified VARCHAR(75) null
);

create table genkey_data (
	genkeyId LONG not null primary key,
	userId LONG,
	privatekey_File BLOB,
	publickey_File BLOB,
	publickey_Text VARCHAR(75) null,
	key_dateCreated VARCHAR(75) null
);