/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package DocRegistration.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.sql.Blob;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link DocRegistration.service.http.DocumentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see DocRegistration.service.http.DocumentServiceSoap
 * @generated
 */
@ProviderType
public class DocumentSoap implements Serializable {
	public static DocumentSoap toSoapModel(Document model) {
		DocumentSoap soapModel = new DocumentSoap();

		soapModel.setDocId(model.getDocId());
		soapModel.setFileId(model.getFileId());
		soapModel.setUserId(model.getUserId());
		soapModel.setReq_name(model.getReq_name());
		soapModel.setReq_email(model.getReq_email());
		soapModel.setSign_email(model.getSign_email());
		soapModel.setDoc_type(model.getDoc_type());
		soapModel.setDoc_status(model.getDoc_status());
		soapModel.setDoc_deadline(model.getDoc_deadline());
		soapModel.setDoc_description(model.getDoc_description());
		soapModel.setFile_name(model.getFile_name());
		soapModel.setFile_type(model.getFile_type());
		soapModel.setFile_blob(model.getFile_blob());
		soapModel.setFile_md5(model.getFile_md5());
		soapModel.setReq_dateCreated(model.getReq_dateCreated());
		soapModel.setReq_dateModified(model.getReq_dateModified());
		soapModel.setReq_signature(model.getReq_signature());
		soapModel.setSign_name(model.getSign_name());

		return soapModel;
	}

	public static DocumentSoap[] toSoapModels(Document[] models) {
		DocumentSoap[] soapModels = new DocumentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DocumentSoap[][] toSoapModels(Document[][] models) {
		DocumentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DocumentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DocumentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DocumentSoap[] toSoapModels(List<Document> models) {
		List<DocumentSoap> soapModels = new ArrayList<DocumentSoap>(models.size());

		for (Document model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DocumentSoap[soapModels.size()]);
	}

	public DocumentSoap() {
	}

	public long getPrimaryKey() {
		return _docId;
	}

	public void setPrimaryKey(long pk) {
		setDocId(pk);
	}

	public long getDocId() {
		return _docId;
	}

	public void setDocId(long docId) {
		_docId = docId;
	}

	public long getFileId() {
		return _fileId;
	}

	public void setFileId(long fileId) {
		_fileId = fileId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getReq_name() {
		return _req_name;
	}

	public void setReq_name(String req_name) {
		_req_name = req_name;
	}

	public String getReq_email() {
		return _req_email;
	}

	public void setReq_email(String req_email) {
		_req_email = req_email;
	}

	public String getSign_email() {
		return _sign_email;
	}

	public void setSign_email(String sign_email) {
		_sign_email = sign_email;
	}

	public String getDoc_type() {
		return _doc_type;
	}

	public void setDoc_type(String doc_type) {
		_doc_type = doc_type;
	}

	public String getDoc_status() {
		return _doc_status;
	}

	public void setDoc_status(String doc_status) {
		_doc_status = doc_status;
	}

	public String getDoc_deadline() {
		return _doc_deadline;
	}

	public void setDoc_deadline(String doc_deadline) {
		_doc_deadline = doc_deadline;
	}

	public String getDoc_description() {
		return _doc_description;
	}

	public void setDoc_description(String doc_description) {
		_doc_description = doc_description;
	}

	public String getFile_name() {
		return _file_name;
	}

	public void setFile_name(String file_name) {
		_file_name = file_name;
	}

	public String getFile_type() {
		return _file_type;
	}

	public void setFile_type(String file_type) {
		_file_type = file_type;
	}

	public Blob getFile_blob() {
		return _file_blob;
	}

	public void setFile_blob(Blob file_blob) {
		_file_blob = file_blob;
	}

	public String getFile_md5() {
		return _file_md5;
	}

	public void setFile_md5(String file_md5) {
		_file_md5 = file_md5;
	}

	public String getReq_dateCreated() {
		return _req_dateCreated;
	}

	public void setReq_dateCreated(String req_dateCreated) {
		_req_dateCreated = req_dateCreated;
	}

	public String getReq_dateModified() {
		return _req_dateModified;
	}

	public void setReq_dateModified(String req_dateModified) {
		_req_dateModified = req_dateModified;
	}

	public String getReq_signature() {
		return _req_signature;
	}

	public void setReq_signature(String req_signature) {
		_req_signature = req_signature;
	}

	public String getSign_name() {
		return _sign_name;
	}

	public void setSign_name(String sign_name) {
		_sign_name = sign_name;
	}

	private long _docId;
	private long _fileId;
	private long _userId;
	private String _req_name;
	private String _req_email;
	private String _sign_email;
	private String _doc_type;
	private String _doc_status;
	private String _doc_deadline;
	private String _doc_description;
	private String _file_name;
	private String _file_type;
	private Blob _file_blob;
	private String _file_md5;
	private String _req_dateCreated;
	private String _req_dateModified;
	private String _req_signature;
	private String _sign_name;
}