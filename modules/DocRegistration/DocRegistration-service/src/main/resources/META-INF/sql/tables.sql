create table document_data (
	docId LONG not null primary key,
	userId LONG,
	fileId LONG,
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