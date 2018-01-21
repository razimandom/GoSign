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

import java.sql.Blob;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com._42Penguins.gosign.service.http.EntFileUploadServiceSoap}.
 *
 * @author Raziman Dom
 * @see com._42Penguins.gosign.service.http.EntFileUploadServiceSoap
 * @generated
 */
@ProviderType
public class EntFileUploadSoap implements Serializable {
	public static EntFileUploadSoap toSoapModel(EntFileUpload model) {
		EntFileUploadSoap soapModel = new EntFileUploadSoap();

		soapModel.setFileId(model.getFileId());
		soapModel.setFile_name(model.getFile_name());
		soapModel.setFile_type(model.getFile_type());
		soapModel.setFile_blob(model.getFile_blob());

		return soapModel;
	}

	public static EntFileUploadSoap[] toSoapModels(EntFileUpload[] models) {
		EntFileUploadSoap[] soapModels = new EntFileUploadSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntFileUploadSoap[][] toSoapModels(EntFileUpload[][] models) {
		EntFileUploadSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EntFileUploadSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntFileUploadSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntFileUploadSoap[] toSoapModels(List<EntFileUpload> models) {
		List<EntFileUploadSoap> soapModels = new ArrayList<EntFileUploadSoap>(models.size());

		for (EntFileUpload model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntFileUploadSoap[soapModels.size()]);
	}

	public EntFileUploadSoap() {
	}

	public long getPrimaryKey() {
		return _fileId;
	}

	public void setPrimaryKey(long pk) {
		setFileId(pk);
	}

	public long getFileId() {
		return _fileId;
	}

	public void setFileId(long fileId) {
		_fileId = fileId;
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

	private long _fileId;
	private String _file_name;
	private String _file_type;
	private Blob _file_blob;
}