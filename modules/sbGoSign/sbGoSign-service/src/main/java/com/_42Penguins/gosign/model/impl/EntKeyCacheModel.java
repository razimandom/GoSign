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

package com._42Penguins.gosign.model.impl;

import aQute.bnd.annotation.ProviderType;

import com._42Penguins.gosign.model.EntKey;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing EntKey in entity cache.
 *
 * @author Raziman Dom
 * @see EntKey
 * @generated
 */
@ProviderType
public class EntKeyCacheModel implements CacheModel<EntKey>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntKeyCacheModel)) {
			return false;
		}

		EntKeyCacheModel entKeyCacheModel = (EntKeyCacheModel)obj;

		if (userId == entKeyCacheModel.userId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{userId=");
		sb.append(userId);
		sb.append(", key_version=");
		sb.append(key_version);
		sb.append(", key_dateCreated=");
		sb.append(key_dateCreated);
		sb.append(", privatekey_Data=");
		sb.append(privatekey_Data);
		sb.append(", publickey_Data=");
		sb.append(publickey_Data);
		sb.append(", salt_Data=");
		sb.append(salt_Data);
		sb.append(", vector_Data=");
		sb.append(vector_Data);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EntKey toEntityModel() {
		EntKeyImpl entKeyImpl = new EntKeyImpl();

		entKeyImpl.setUserId(userId);
		entKeyImpl.setKey_version(key_version);

		if (key_dateCreated == null) {
			entKeyImpl.setKey_dateCreated(StringPool.BLANK);
		}
		else {
			entKeyImpl.setKey_dateCreated(key_dateCreated);
		}

		if (privatekey_Data == null) {
			entKeyImpl.setPrivatekey_Data(StringPool.BLANK);
		}
		else {
			entKeyImpl.setPrivatekey_Data(privatekey_Data);
		}

		if (publickey_Data == null) {
			entKeyImpl.setPublickey_Data(StringPool.BLANK);
		}
		else {
			entKeyImpl.setPublickey_Data(publickey_Data);
		}

		if (salt_Data == null) {
			entKeyImpl.setSalt_Data(StringPool.BLANK);
		}
		else {
			entKeyImpl.setSalt_Data(salt_Data);
		}

		if (vector_Data == null) {
			entKeyImpl.setVector_Data(StringPool.BLANK);
		}
		else {
			entKeyImpl.setVector_Data(vector_Data);
		}

		entKeyImpl.resetOriginalValues();

		return entKeyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userId = objectInput.readLong();

		key_version = objectInput.readLong();
		key_dateCreated = objectInput.readUTF();
		privatekey_Data = objectInput.readUTF();
		publickey_Data = objectInput.readUTF();
		salt_Data = objectInput.readUTF();
		vector_Data = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(userId);

		objectOutput.writeLong(key_version);

		if (key_dateCreated == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(key_dateCreated);
		}

		if (privatekey_Data == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(privatekey_Data);
		}

		if (publickey_Data == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publickey_Data);
		}

		if (salt_Data == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(salt_Data);
		}

		if (vector_Data == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(vector_Data);
		}
	}

	public long userId;
	public long key_version;
	public String key_dateCreated;
	public String privatekey_Data;
	public String publickey_Data;
	public String salt_Data;
	public String vector_Data;
}