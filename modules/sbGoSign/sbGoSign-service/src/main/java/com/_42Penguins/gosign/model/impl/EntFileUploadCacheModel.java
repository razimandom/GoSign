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

import com._42Penguins.gosign.model.EntFileUpload;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing EntFileUpload in entity cache.
 *
 * @author Raziman Dom
 * @see EntFileUpload
 * @generated
 */
@ProviderType
public class EntFileUploadCacheModel implements CacheModel<EntFileUpload>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntFileUploadCacheModel)) {
			return false;
		}

		EntFileUploadCacheModel entFileUploadCacheModel = (EntFileUploadCacheModel)obj;

		if (fileId == entFileUploadCacheModel.fileId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fileId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{fileId=");
		sb.append(fileId);
		sb.append(", docId=");
		sb.append(docId);
		sb.append(", file_name=");
		sb.append(file_name);
		sb.append(", file_type=");
		sb.append(file_type);

		return sb.toString();
	}

	@Override
	public EntFileUpload toEntityModel() {
		EntFileUploadImpl entFileUploadImpl = new EntFileUploadImpl();

		entFileUploadImpl.setFileId(fileId);
		entFileUploadImpl.setDocId(docId);

		if (file_name == null) {
			entFileUploadImpl.setFile_name(StringPool.BLANK);
		}
		else {
			entFileUploadImpl.setFile_name(file_name);
		}

		if (file_type == null) {
			entFileUploadImpl.setFile_type(StringPool.BLANK);
		}
		else {
			entFileUploadImpl.setFile_type(file_type);
		}

		entFileUploadImpl.resetOriginalValues();

		return entFileUploadImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		fileId = objectInput.readLong();

		docId = objectInput.readLong();
		file_name = objectInput.readUTF();
		file_type = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(fileId);

		objectOutput.writeLong(docId);

		if (file_name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(file_name);
		}

		if (file_type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(file_type);
		}
	}

	public long fileId;
	public long docId;
	public String file_name;
	public String file_type;
}