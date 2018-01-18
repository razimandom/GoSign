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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link GenKey}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GenKey
 * @generated
 */
@ProviderType
public class GenKeyWrapper implements GenKey, ModelWrapper<GenKey> {
	public GenKeyWrapper(GenKey genKey) {
		_genKey = genKey;
	}

	@Override
	public Class<?> getModelClass() {
		return GenKey.class;
	}

	@Override
	public String getModelClassName() {
		return GenKey.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("key_version", getKey_version());
		attributes.put("key_dateCreated", getKey_dateCreated());
		attributes.put("privatekey_Data", getPrivatekey_Data());
		attributes.put("publickey_Data", getPublickey_Data());
		attributes.put("salt_Data", getSalt_Data());
		attributes.put("vector_Data", getVector_Data());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long key_version = (Long)attributes.get("key_version");

		if (key_version != null) {
			setKey_version(key_version);
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
	}

	@Override
	public DocRegistration.model.GenKey toEscapedModel() {
		return new GenKeyWrapper(_genKey.toEscapedModel());
	}

	@Override
	public DocRegistration.model.GenKey toUnescapedModel() {
		return new GenKeyWrapper(_genKey.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _genKey.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _genKey.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _genKey.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _genKey.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DocRegistration.model.GenKey> toCacheModel() {
		return _genKey.toCacheModel();
	}

	@Override
	public int compareTo(DocRegistration.model.GenKey genKey) {
		return _genKey.compareTo(genKey);
	}

	@Override
	public int hashCode() {
		return _genKey.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _genKey.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new GenKeyWrapper((GenKey)_genKey.clone());
	}

	/**
	* Returns the key_date created of this gen key.
	*
	* @return the key_date created of this gen key
	*/
	@Override
	public java.lang.String getKey_dateCreated() {
		return _genKey.getKey_dateCreated();
	}

	/**
	* Returns the privatekey_ data of this gen key.
	*
	* @return the privatekey_ data of this gen key
	*/
	@Override
	public java.lang.String getPrivatekey_Data() {
		return _genKey.getPrivatekey_Data();
	}

	/**
	* Returns the publickey_ data of this gen key.
	*
	* @return the publickey_ data of this gen key
	*/
	@Override
	public java.lang.String getPublickey_Data() {
		return _genKey.getPublickey_Data();
	}

	/**
	* Returns the salt_ data of this gen key.
	*
	* @return the salt_ data of this gen key
	*/
	@Override
	public java.lang.String getSalt_Data() {
		return _genKey.getSalt_Data();
	}

	/**
	* Returns the user uuid of this gen key.
	*
	* @return the user uuid of this gen key
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _genKey.getUserUuid();
	}

	/**
	* Returns the vector_ data of this gen key.
	*
	* @return the vector_ data of this gen key
	*/
	@Override
	public java.lang.String getVector_Data() {
		return _genKey.getVector_Data();
	}

	@Override
	public java.lang.String toString() {
		return _genKey.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _genKey.toXmlString();
	}

	/**
	* Returns the key_version of this gen key.
	*
	* @return the key_version of this gen key
	*/
	@Override
	public long getKey_version() {
		return _genKey.getKey_version();
	}

	/**
	* Returns the primary key of this gen key.
	*
	* @return the primary key of this gen key
	*/
	@Override
	public long getPrimaryKey() {
		return _genKey.getPrimaryKey();
	}

	/**
	* Returns the user ID of this gen key.
	*
	* @return the user ID of this gen key
	*/
	@Override
	public long getUserId() {
		return _genKey.getUserId();
	}

	@Override
	public void persist() {
		_genKey.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_genKey.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_genKey.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_genKey.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_genKey.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the key_date created of this gen key.
	*
	* @param key_dateCreated the key_date created of this gen key
	*/
	@Override
	public void setKey_dateCreated(java.lang.String key_dateCreated) {
		_genKey.setKey_dateCreated(key_dateCreated);
	}

	/**
	* Sets the key_version of this gen key.
	*
	* @param key_version the key_version of this gen key
	*/
	@Override
	public void setKey_version(long key_version) {
		_genKey.setKey_version(key_version);
	}

	@Override
	public void setNew(boolean n) {
		_genKey.setNew(n);
	}

	/**
	* Sets the primary key of this gen key.
	*
	* @param primaryKey the primary key of this gen key
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_genKey.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_genKey.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the privatekey_ data of this gen key.
	*
	* @param privatekey_Data the privatekey_ data of this gen key
	*/
	@Override
	public void setPrivatekey_Data(java.lang.String privatekey_Data) {
		_genKey.setPrivatekey_Data(privatekey_Data);
	}

	/**
	* Sets the publickey_ data of this gen key.
	*
	* @param publickey_Data the publickey_ data of this gen key
	*/
	@Override
	public void setPublickey_Data(java.lang.String publickey_Data) {
		_genKey.setPublickey_Data(publickey_Data);
	}

	/**
	* Sets the salt_ data of this gen key.
	*
	* @param salt_Data the salt_ data of this gen key
	*/
	@Override
	public void setSalt_Data(java.lang.String salt_Data) {
		_genKey.setSalt_Data(salt_Data);
	}

	/**
	* Sets the user ID of this gen key.
	*
	* @param userId the user ID of this gen key
	*/
	@Override
	public void setUserId(long userId) {
		_genKey.setUserId(userId);
	}

	/**
	* Sets the user uuid of this gen key.
	*
	* @param userUuid the user uuid of this gen key
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_genKey.setUserUuid(userUuid);
	}

	/**
	* Sets the vector_ data of this gen key.
	*
	* @param vector_Data the vector_ data of this gen key
	*/
	@Override
	public void setVector_Data(java.lang.String vector_Data) {
		_genKey.setVector_Data(vector_Data);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GenKeyWrapper)) {
			return false;
		}

		GenKeyWrapper genKeyWrapper = (GenKeyWrapper)obj;

		if (Objects.equals(_genKey, genKeyWrapper._genKey)) {
			return true;
		}

		return false;
	}

	@Override
	public GenKey getWrappedModel() {
		return _genKey;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _genKey.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _genKey.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_genKey.resetOriginalValues();
	}

	private final GenKey _genKey;
}