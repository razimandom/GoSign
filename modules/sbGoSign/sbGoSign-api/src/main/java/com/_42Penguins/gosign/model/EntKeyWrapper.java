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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link EntKey}.
 * </p>
 *
 * @author Raziman Dom
 * @see EntKey
 * @generated
 */
@ProviderType
public class EntKeyWrapper implements EntKey, ModelWrapper<EntKey> {
	public EntKeyWrapper(EntKey entKey) {
		_entKey = entKey;
	}

	@Override
	public Class<?> getModelClass() {
		return EntKey.class;
	}

	@Override
	public String getModelClassName() {
		return EntKey.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("key_status", getKey_status());
		attributes.put("key_dateCreated", getKey_dateCreated());
		attributes.put("privatekey_Data", getPrivatekey_Data());
		attributes.put("publickey_Data", getPublickey_Data());
		attributes.put("salt_Data", getSalt_Data());
		attributes.put("vector_Data", getVector_Data());
		attributes.put("sign_name", getSign_name());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String key_status = (String)attributes.get("key_status");

		if (key_status != null) {
			setKey_status(key_status);
		}

		String key_dateCreated = (String)attributes.get("key_dateCreated");

		if (key_dateCreated != null) {
			setKey_dateCreated(key_dateCreated);
		}

		String privatekey_Data = (String)attributes.get("privatekey_Data");

		if (privatekey_Data != null) {
			setPrivatekey_Data(privatekey_Data);
		}

		String publickey_Data = (String)attributes.get("publickey_Data");

		if (publickey_Data != null) {
			setPublickey_Data(publickey_Data);
		}

		String salt_Data = (String)attributes.get("salt_Data");

		if (salt_Data != null) {
			setSalt_Data(salt_Data);
		}

		String vector_Data = (String)attributes.get("vector_Data");

		if (vector_Data != null) {
			setVector_Data(vector_Data);
		}

		String sign_name = (String)attributes.get("sign_name");

		if (sign_name != null) {
			setSign_name(sign_name);
		}
	}

	@Override
	public EntKey toEscapedModel() {
		return new EntKeyWrapper(_entKey.toEscapedModel());
	}

	@Override
	public EntKey toUnescapedModel() {
		return new EntKeyWrapper(_entKey.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _entKey.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _entKey.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _entKey.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _entKey.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<EntKey> toCacheModel() {
		return _entKey.toCacheModel();
	}

	@Override
	public int compareTo(EntKey entKey) {
		return _entKey.compareTo(entKey);
	}

	@Override
	public int hashCode() {
		return _entKey.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entKey.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EntKeyWrapper((EntKey)_entKey.clone());
	}

	/**
	* Returns the key_date created of this ent key.
	*
	* @return the key_date created of this ent key
	*/
	@Override
	public java.lang.String getKey_dateCreated() {
		return _entKey.getKey_dateCreated();
	}

	/**
	* Returns the key_status of this ent key.
	*
	* @return the key_status of this ent key
	*/
	@Override
	public java.lang.String getKey_status() {
		return _entKey.getKey_status();
	}

	/**
	* Returns the privatekey_ data of this ent key.
	*
	* @return the privatekey_ data of this ent key
	*/
	@Override
	public java.lang.String getPrivatekey_Data() {
		return _entKey.getPrivatekey_Data();
	}

	/**
	* Returns the publickey_ data of this ent key.
	*
	* @return the publickey_ data of this ent key
	*/
	@Override
	public java.lang.String getPublickey_Data() {
		return _entKey.getPublickey_Data();
	}

	/**
	* Returns the salt_ data of this ent key.
	*
	* @return the salt_ data of this ent key
	*/
	@Override
	public java.lang.String getSalt_Data() {
		return _entKey.getSalt_Data();
	}

	/**
	* Returns the sign_name of this ent key.
	*
	* @return the sign_name of this ent key
	*/
	@Override
	public java.lang.String getSign_name() {
		return _entKey.getSign_name();
	}

	/**
	* Returns the user uuid of this ent key.
	*
	* @return the user uuid of this ent key
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _entKey.getUserUuid();
	}

	/**
	* Returns the vector_ data of this ent key.
	*
	* @return the vector_ data of this ent key
	*/
	@Override
	public java.lang.String getVector_Data() {
		return _entKey.getVector_Data();
	}

	@Override
	public java.lang.String toString() {
		return _entKey.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _entKey.toXmlString();
	}

	/**
	* Returns the primary key of this ent key.
	*
	* @return the primary key of this ent key
	*/
	@Override
	public long getPrimaryKey() {
		return _entKey.getPrimaryKey();
	}

	/**
	* Returns the user ID of this ent key.
	*
	* @return the user ID of this ent key
	*/
	@Override
	public long getUserId() {
		return _entKey.getUserId();
	}

	@Override
	public void persist() {
		_entKey.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_entKey.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_entKey.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_entKey.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_entKey.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the key_date created of this ent key.
	*
	* @param key_dateCreated the key_date created of this ent key
	*/
	@Override
	public void setKey_dateCreated(java.lang.String key_dateCreated) {
		_entKey.setKey_dateCreated(key_dateCreated);
	}

	/**
	* Sets the key_status of this ent key.
	*
	* @param key_status the key_status of this ent key
	*/
	@Override
	public void setKey_status(java.lang.String key_status) {
		_entKey.setKey_status(key_status);
	}

	@Override
	public void setNew(boolean n) {
		_entKey.setNew(n);
	}

	/**
	* Sets the primary key of this ent key.
	*
	* @param primaryKey the primary key of this ent key
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_entKey.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_entKey.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the privatekey_ data of this ent key.
	*
	* @param privatekey_Data the privatekey_ data of this ent key
	*/
	@Override
	public void setPrivatekey_Data(java.lang.String privatekey_Data) {
		_entKey.setPrivatekey_Data(privatekey_Data);
	}

	/**
	* Sets the publickey_ data of this ent key.
	*
	* @param publickey_Data the publickey_ data of this ent key
	*/
	@Override
	public void setPublickey_Data(java.lang.String publickey_Data) {
		_entKey.setPublickey_Data(publickey_Data);
	}

	/**
	* Sets the salt_ data of this ent key.
	*
	* @param salt_Data the salt_ data of this ent key
	*/
	@Override
	public void setSalt_Data(java.lang.String salt_Data) {
		_entKey.setSalt_Data(salt_Data);
	}

	/**
	* Sets the sign_name of this ent key.
	*
	* @param sign_name the sign_name of this ent key
	*/
	@Override
	public void setSign_name(java.lang.String sign_name) {
		_entKey.setSign_name(sign_name);
	}

	/**
	* Sets the user ID of this ent key.
	*
	* @param userId the user ID of this ent key
	*/
	@Override
	public void setUserId(long userId) {
		_entKey.setUserId(userId);
	}

	/**
	* Sets the user uuid of this ent key.
	*
	* @param userUuid the user uuid of this ent key
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_entKey.setUserUuid(userUuid);
	}

	/**
	* Sets the vector_ data of this ent key.
	*
	* @param vector_Data the vector_ data of this ent key
	*/
	@Override
	public void setVector_Data(java.lang.String vector_Data) {
		_entKey.setVector_Data(vector_Data);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntKeyWrapper)) {
			return false;
		}

		EntKeyWrapper entKeyWrapper = (EntKeyWrapper)obj;

		if (Objects.equals(_entKey, entKeyWrapper._entKey)) {
			return true;
		}

		return false;
	}

	@Override
	public EntKey getWrappedModel() {
		return _entKey;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entKey.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _entKey.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_entKey.resetOriginalValues();
	}

	private final EntKey _entKey;
}