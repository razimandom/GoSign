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
 * This class is used by SOAP remote services, specifically {@link com._42Penguins.gosign.service.http.EntKeyServiceSoap}.
 *
 * @author Raziman Dom
 * @see com._42Penguins.gosign.service.http.EntKeyServiceSoap
 * @generated
 */
@ProviderType
public class EntKeySoap implements Serializable {
	public static EntKeySoap toSoapModel(EntKey model) {
		EntKeySoap soapModel = new EntKeySoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setKey_version(model.getKey_version());
		soapModel.setKey_dateCreated(model.getKey_dateCreated());
		soapModel.setPrivatekey_Data(model.getPrivatekey_Data());
		soapModel.setPublickey_Data(model.getPublickey_Data());
		soapModel.setSalt_Data(model.getSalt_Data());
		soapModel.setVector_Data(model.getVector_Data());

		return soapModel;
	}

	public static EntKeySoap[] toSoapModels(EntKey[] models) {
		EntKeySoap[] soapModels = new EntKeySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntKeySoap[][] toSoapModels(EntKey[][] models) {
		EntKeySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EntKeySoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntKeySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntKeySoap[] toSoapModels(List<EntKey> models) {
		List<EntKeySoap> soapModels = new ArrayList<EntKeySoap>(models.size());

		for (EntKey model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntKeySoap[soapModels.size()]);
	}

	public EntKeySoap() {
	}

	public long getPrimaryKey() {
		return _userId;
	}

	public void setPrimaryKey(long pk) {
		setUserId(pk);
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getKey_version() {
		return _key_version;
	}

	public void setKey_version(long key_version) {
		_key_version = key_version;
	}

	public String getKey_dateCreated() {
		return _key_dateCreated;
	}

	public void setKey_dateCreated(String key_dateCreated) {
		_key_dateCreated = key_dateCreated;
	}

	public String getPrivatekey_Data() {
		return _privatekey_Data;
	}

	public void setPrivatekey_Data(String privatekey_Data) {
		_privatekey_Data = privatekey_Data;
	}

	public String getPublickey_Data() {
		return _publickey_Data;
	}

	public void setPublickey_Data(String publickey_Data) {
		_publickey_Data = publickey_Data;
	}

	public String getSalt_Data() {
		return _salt_Data;
	}

	public void setSalt_Data(String salt_Data) {
		_salt_Data = salt_Data;
	}

	public String getVector_Data() {
		return _vector_Data;
	}

	public void setVector_Data(String vector_Data) {
		_vector_Data = vector_Data;
	}

	private long _userId;
	private long _key_version;
	private String _key_dateCreated;
	private String _privatekey_Data;
	private String _publickey_Data;
	private String _salt_Data;
	private String _vector_Data;
}