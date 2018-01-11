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

import java.sql.Blob;

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

		attributes.put("genkeyId", getGenkeyId());
		attributes.put("userId", getUserId());
		attributes.put("privatekey_File", getPrivatekey_File());
		attributes.put("publickey_File", getPublickey_File());
		attributes.put("publickey_Text", getPublickey_Text());
		attributes.put("key_dateCreated", getKey_dateCreated());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long genkeyId = (Long)attributes.get("genkeyId");

		if (genkeyId != null) {
			setGenkeyId(genkeyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Blob privatekey_File = (Blob)attributes.get("privatekey_File");

		if (privatekey_File != null) {
			setPrivatekey_File(privatekey_File);
		}

		Blob publickey_File = (Blob)attributes.get("publickey_File");

		if (publickey_File != null) {
			setPublickey_File(publickey_File);
		}

		String publickey_Text = (String)attributes.get("publickey_Text");

		if (publickey_Text != null) {
			setPublickey_Text(publickey_Text);
		}

		String key_dateCreated = (String)attributes.get("key_dateCreated");

		if (key_dateCreated != null) {
			setKey_dateCreated(key_dateCreated);
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
	* Returns the publickey_ text of this gen key.
	*
	* @return the publickey_ text of this gen key
	*/
	@Override
	public java.lang.String getPublickey_Text() {
		return _genKey.getPublickey_Text();
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

	@Override
	public java.lang.String toString() {
		return _genKey.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _genKey.toXmlString();
	}

	/**
	* Returns the privatekey_ file of this gen key.
	*
	* @return the privatekey_ file of this gen key
	*/
	@Override
	public Blob getPrivatekey_File() {
		return _genKey.getPrivatekey_File();
	}

	/**
	* Returns the publickey_ file of this gen key.
	*
	* @return the publickey_ file of this gen key
	*/
	@Override
	public Blob getPublickey_File() {
		return _genKey.getPublickey_File();
	}

	/**
	* Returns the genkey ID of this gen key.
	*
	* @return the genkey ID of this gen key
	*/
	@Override
	public long getGenkeyId() {
		return _genKey.getGenkeyId();
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
	* Sets the genkey ID of this gen key.
	*
	* @param genkeyId the genkey ID of this gen key
	*/
	@Override
	public void setGenkeyId(long genkeyId) {
		_genKey.setGenkeyId(genkeyId);
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
	* Sets the privatekey_ file of this gen key.
	*
	* @param privatekey_File the privatekey_ file of this gen key
	*/
	@Override
	public void setPrivatekey_File(Blob privatekey_File) {
		_genKey.setPrivatekey_File(privatekey_File);
	}

	/**
	* Sets the publickey_ file of this gen key.
	*
	* @param publickey_File the publickey_ file of this gen key
	*/
	@Override
	public void setPublickey_File(Blob publickey_File) {
		_genKey.setPublickey_File(publickey_File);
	}

	/**
	* Sets the publickey_ text of this gen key.
	*
	* @param publickey_Text the publickey_ text of this gen key
	*/
	@Override
	public void setPublickey_Text(java.lang.String publickey_Text) {
		_genKey.setPublickey_Text(publickey_Text);
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