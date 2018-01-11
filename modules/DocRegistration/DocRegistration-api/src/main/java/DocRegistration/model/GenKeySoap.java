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
 * This class is used by SOAP remote services, specifically {@link DocRegistration.service.http.GenKeyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see DocRegistration.service.http.GenKeyServiceSoap
 * @generated
 */
@ProviderType
public class GenKeySoap implements Serializable {
	public static GenKeySoap toSoapModel(GenKey model) {
		GenKeySoap soapModel = new GenKeySoap();

		soapModel.setGenkeyId(model.getGenkeyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setPrivatekey_File(model.getPrivatekey_File());
		soapModel.setPublickey_File(model.getPublickey_File());
		soapModel.setPublickey_Text(model.getPublickey_Text());
		soapModel.setKey_dateCreated(model.getKey_dateCreated());

		return soapModel;
	}

	public static GenKeySoap[] toSoapModels(GenKey[] models) {
		GenKeySoap[] soapModels = new GenKeySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GenKeySoap[][] toSoapModels(GenKey[][] models) {
		GenKeySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new GenKeySoap[models.length][models[0].length];
		}
		else {
			soapModels = new GenKeySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GenKeySoap[] toSoapModels(List<GenKey> models) {
		List<GenKeySoap> soapModels = new ArrayList<GenKeySoap>(models.size());

		for (GenKey model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GenKeySoap[soapModels.size()]);
	}

	public GenKeySoap() {
	}

	public long getPrimaryKey() {
		return _genkeyId;
	}

	public void setPrimaryKey(long pk) {
		setGenkeyId(pk);
	}

	public long getGenkeyId() {
		return _genkeyId;
	}

	public void setGenkeyId(long genkeyId) {
		_genkeyId = genkeyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Blob getPrivatekey_File() {
		return _privatekey_File;
	}

	public void setPrivatekey_File(Blob privatekey_File) {
		_privatekey_File = privatekey_File;
	}

	public Blob getPublickey_File() {
		return _publickey_File;
	}

	public void setPublickey_File(Blob publickey_File) {
		_publickey_File = publickey_File;
	}

	public String getPublickey_Text() {
		return _publickey_Text;
	}

	public void setPublickey_Text(String publickey_Text) {
		_publickey_Text = publickey_Text;
	}

	public String getKey_dateCreated() {
		return _key_dateCreated;
	}

	public void setKey_dateCreated(String key_dateCreated) {
		_key_dateCreated = key_dateCreated;
	}

	private long _genkeyId;
	private long _userId;
	private Blob _privatekey_File;
	private Blob _publickey_File;
	private String _publickey_Text;
	private String _key_dateCreated;
}