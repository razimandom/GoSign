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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the EntKey service. Represents a row in the &quot;genkey_data&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com._42Penguins.gosign.model.impl.EntKeyModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com._42Penguins.gosign.model.impl.EntKeyImpl}.
 * </p>
 *
 * @author Raziman Dom
 * @see EntKey
 * @see com._42Penguins.gosign.model.impl.EntKeyImpl
 * @see com._42Penguins.gosign.model.impl.EntKeyModelImpl
 * @generated
 */
@ProviderType
public interface EntKeyModel extends BaseModel<EntKey> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ent key model instance should use the {@link EntKey} interface instead.
	 */

	/**
	 * Returns the primary key of this ent key.
	 *
	 * @return the primary key of this ent key
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ent key.
	 *
	 * @param primaryKey the primary key of this ent key
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the user ID of this ent key.
	 *
	 * @return the user ID of this ent key
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this ent key.
	 *
	 * @param userId the user ID of this ent key
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ent key.
	 *
	 * @return the user uuid of this ent key
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ent key.
	 *
	 * @param userUuid the user uuid of this ent key
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the key_status of this ent key.
	 *
	 * @return the key_status of this ent key
	 */
	@AutoEscape
	public String getKey_status();

	/**
	 * Sets the key_status of this ent key.
	 *
	 * @param key_status the key_status of this ent key
	 */
	public void setKey_status(String key_status);

	/**
	 * Returns the key_date created of this ent key.
	 *
	 * @return the key_date created of this ent key
	 */
	@AutoEscape
	public String getKey_dateCreated();

	/**
	 * Sets the key_date created of this ent key.
	 *
	 * @param key_dateCreated the key_date created of this ent key
	 */
	public void setKey_dateCreated(String key_dateCreated);

	/**
	 * Returns the privatekey_ data of this ent key.
	 *
	 * @return the privatekey_ data of this ent key
	 */
	@AutoEscape
	public String getPrivatekey_Data();

	/**
	 * Sets the privatekey_ data of this ent key.
	 *
	 * @param privatekey_Data the privatekey_ data of this ent key
	 */
	public void setPrivatekey_Data(String privatekey_Data);

	/**
	 * Returns the publickey_ data of this ent key.
	 *
	 * @return the publickey_ data of this ent key
	 */
	@AutoEscape
	public String getPublickey_Data();

	/**
	 * Sets the publickey_ data of this ent key.
	 *
	 * @param publickey_Data the publickey_ data of this ent key
	 */
	public void setPublickey_Data(String publickey_Data);

	/**
	 * Returns the salt_ data of this ent key.
	 *
	 * @return the salt_ data of this ent key
	 */
	@AutoEscape
	public String getSalt_Data();

	/**
	 * Sets the salt_ data of this ent key.
	 *
	 * @param salt_Data the salt_ data of this ent key
	 */
	public void setSalt_Data(String salt_Data);

	/**
	 * Returns the vector_ data of this ent key.
	 *
	 * @return the vector_ data of this ent key
	 */
	@AutoEscape
	public String getVector_Data();

	/**
	 * Sets the vector_ data of this ent key.
	 *
	 * @param vector_Data the vector_ data of this ent key
	 */
	public void setVector_Data(String vector_Data);

	/**
	 * Returns the sign_name of this ent key.
	 *
	 * @return the sign_name of this ent key
	 */
	@AutoEscape
	public String getSign_name();

	/**
	 * Sets the sign_name of this ent key.
	 *
	 * @param sign_name the sign_name of this ent key
	 */
	public void setSign_name(String sign_name);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(EntKey entKey);

	@Override
	public int hashCode();

	@Override
	public CacheModel<EntKey> toCacheModel();

	@Override
	public EntKey toEscapedModel();

	@Override
	public EntKey toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}