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

package com._42Penguins.gosign.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com._42Penguins.gosign.service.http.EntDocServiceSoap}.
 *
 * @author Raziman Dom
 * @see com._42Penguins.gosign.service.http.EntDocServiceSoap
 * @generated
 */
@ProviderType
public class EntDocSoap implements Serializable {
	public static EntDocSoap toSoapModel(EntDoc model) {
		EntDocSoap soapModel = new EntDocSoap();

		soapModel.setDocId(model.getDocId());
		soapModel.setFileId(model.getFileId());
		soapModel.setUserId(model.getUserId());
		soapModel.setSignId(model.getSignId());
		soapModel.setReq_name(model.getReq_name());
		soapModel.setReq_email(model.getReq_email());
		soapModel.setSign_name(model.getSign_name());
		soapModel.setSign_email(model.getSign_email());
		soapModel.setDoc_title(model.getDoc_title());
		soapModel.setDoc_type(model.getDoc_type());
		soapModel.setDoc_md5(model.getDoc_md5());
		soapModel.setDoc_status(model.getDoc_status());
		soapModel.setDoc_deadline(model.getDoc_deadline());
		soapModel.setDoc_description(model.getDoc_description());
		soapModel.setDoc_signature(model.getDoc_signature());
		soapModel.setReq_dateCreated(model.getReq_dateCreated());
		soapModel.setReq_dateModified(model.getReq_dateModified());
		soapModel.setReq_timeCreated(model.getReq_timeCreated());
		soapModel.setReq_timeModified(model.getReq_timeModified());
		soapModel.setReq_accepted(model.getReq_accepted());

		return soapModel;
	}

	public static EntDocSoap[] toSoapModels(EntDoc[] models) {
		EntDocSoap[] soapModels = new EntDocSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntDocSoap[][] toSoapModels(EntDoc[][] models) {
		EntDocSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EntDocSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntDocSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntDocSoap[] toSoapModels(List<EntDoc> models) {
		List<EntDocSoap> soapModels = new ArrayList<EntDocSoap>(models.size());

		for (EntDoc model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntDocSoap[soapModels.size()]);
	}

	public EntDocSoap() {
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

	public long getSignId() {
		return _signId;
	}

	public void setSignId(long signId) {
		_signId = signId;
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

	public String getSign_name() {
		return _sign_name;
	}

	public void setSign_name(String sign_name) {
		_sign_name = sign_name;
	}

	public String getSign_email() {
		return _sign_email;
	}

	public void setSign_email(String sign_email) {
		_sign_email = sign_email;
	}

	public String getDoc_title() {
		return _doc_title;
	}

	public void setDoc_title(String doc_title) {
		_doc_title = doc_title;
	}

	public String getDoc_type() {
		return _doc_type;
	}

	public void setDoc_type(String doc_type) {
		_doc_type = doc_type;
	}

	public String getDoc_md5() {
		return _doc_md5;
	}

	public void setDoc_md5(String doc_md5) {
		_doc_md5 = doc_md5;
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

	public String getDoc_signature() {
		return _doc_signature;
	}

	public void setDoc_signature(String doc_signature) {
		_doc_signature = doc_signature;
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

	public String getReq_timeCreated() {
		return _req_timeCreated;
	}

	public void setReq_timeCreated(String req_timeCreated) {
		_req_timeCreated = req_timeCreated;
	}

	public String getReq_timeModified() {
		return _req_timeModified;
	}

	public void setReq_timeModified(String req_timeModified) {
		_req_timeModified = req_timeModified;
	}

	public boolean getReq_accepted() {
		return _req_accepted;
	}

	public boolean isReq_accepted() {
		return _req_accepted;
	}

	public void setReq_accepted(boolean req_accepted) {
		_req_accepted = req_accepted;
	}

	private long _docId;
	private long _fileId;
	private long _userId;
	private long _signId;
	private String _req_name;
	private String _req_email;
	private String _sign_name;
	private String _sign_email;
	private String _doc_title;
	private String _doc_type;
	private String _doc_md5;
	private String _doc_status;
	private String _doc_deadline;
	private String _doc_description;
	private String _doc_signature;
	private String _req_dateCreated;
	private String _req_dateModified;
	private String _req_timeCreated;
	private String _req_timeModified;
	private boolean _req_accepted;
}