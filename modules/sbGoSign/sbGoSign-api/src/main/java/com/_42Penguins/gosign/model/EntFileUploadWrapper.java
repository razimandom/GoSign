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

import java.sql.Blob;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link EntFileUpload}.
 * </p>
 *
 * @author Raziman Dom
 * @see EntFileUpload
 * @generated
 */
@ProviderType
public class EntFileUploadWrapper implements EntFileUpload,
	ModelWrapper<EntFileUpload> {
	public EntFileUploadWrapper(EntFileUpload entFileUpload) {
		_entFileUpload = entFileUpload;
	}

	@Override
	public Class<?> getModelClass() {
		return EntFileUpload.class;
	}

	@Override
	public String getModelClassName() {
		return EntFileUpload.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fileId", getFileId());
		attributes.put("docId", getDocId());
		attributes.put("file_name", getFile_name());
		attributes.put("file_type", getFile_type());
		attributes.put("file_blob", getFile_blob());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long fileId = (Long)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		Long docId = (Long)attributes.get("docId");

		if (docId != null) {
			setDocId(docId);
		}

		String file_name = (String)attributes.get("file_name");

		if (file_name != null) {
			setFile_name(file_name);
		}

		String file_type = (String)attributes.get("file_type");

		if (file_type != null) {
			setFile_type(file_type);
		}

		Blob file_blob = (Blob)attributes.get("file_blob");

		if (file_blob != null) {
			setFile_blob(file_blob);
		}
	}

	@Override
	public EntFileUpload toEscapedModel() {
		return new EntFileUploadWrapper(_entFileUpload.toEscapedModel());
	}

	@Override
	public EntFileUpload toUnescapedModel() {
		return new EntFileUploadWrapper(_entFileUpload.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _entFileUpload.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _entFileUpload.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _entFileUpload.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _entFileUpload.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<EntFileUpload> toCacheModel() {
		return _entFileUpload.toCacheModel();
	}

	@Override
	public int compareTo(EntFileUpload entFileUpload) {
		return _entFileUpload.compareTo(entFileUpload);
	}

	@Override
	public int hashCode() {
		return _entFileUpload.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entFileUpload.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EntFileUploadWrapper((EntFileUpload)_entFileUpload.clone());
	}

	/**
	* Returns the file_name of this ent file upload.
	*
	* @return the file_name of this ent file upload
	*/
	@Override
	public java.lang.String getFile_name() {
		return _entFileUpload.getFile_name();
	}

	/**
	* Returns the file_type of this ent file upload.
	*
	* @return the file_type of this ent file upload
	*/
	@Override
	public java.lang.String getFile_type() {
		return _entFileUpload.getFile_type();
	}

	@Override
	public java.lang.String toString() {
		return _entFileUpload.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _entFileUpload.toXmlString();
	}

	/**
	* Returns the file_blob of this ent file upload.
	*
	* @return the file_blob of this ent file upload
	*/
	@Override
	public Blob getFile_blob() {
		return _entFileUpload.getFile_blob();
	}

	/**
	* Returns the doc ID of this ent file upload.
	*
	* @return the doc ID of this ent file upload
	*/
	@Override
	public long getDocId() {
		return _entFileUpload.getDocId();
	}

	/**
	* Returns the file ID of this ent file upload.
	*
	* @return the file ID of this ent file upload
	*/
	@Override
	public long getFileId() {
		return _entFileUpload.getFileId();
	}

	/**
	* Returns the primary key of this ent file upload.
	*
	* @return the primary key of this ent file upload
	*/
	@Override
	public long getPrimaryKey() {
		return _entFileUpload.getPrimaryKey();
	}

	@Override
	public void persist() {
		_entFileUpload.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_entFileUpload.setCachedModel(cachedModel);
	}

	/**
	* Sets the doc ID of this ent file upload.
	*
	* @param docId the doc ID of this ent file upload
	*/
	@Override
	public void setDocId(long docId) {
		_entFileUpload.setDocId(docId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_entFileUpload.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_entFileUpload.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_entFileUpload.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file ID of this ent file upload.
	*
	* @param fileId the file ID of this ent file upload
	*/
	@Override
	public void setFileId(long fileId) {
		_entFileUpload.setFileId(fileId);
	}

	/**
	* Sets the file_blob of this ent file upload.
	*
	* @param file_blob the file_blob of this ent file upload
	*/
	@Override
	public void setFile_blob(Blob file_blob) {
		_entFileUpload.setFile_blob(file_blob);
	}

	/**
	* Sets the file_name of this ent file upload.
	*
	* @param file_name the file_name of this ent file upload
	*/
	@Override
	public void setFile_name(java.lang.String file_name) {
		_entFileUpload.setFile_name(file_name);
	}

	/**
	* Sets the file_type of this ent file upload.
	*
	* @param file_type the file_type of this ent file upload
	*/
	@Override
	public void setFile_type(java.lang.String file_type) {
		_entFileUpload.setFile_type(file_type);
	}

	@Override
	public void setNew(boolean n) {
		_entFileUpload.setNew(n);
	}

	/**
	* Sets the primary key of this ent file upload.
	*
	* @param primaryKey the primary key of this ent file upload
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_entFileUpload.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_entFileUpload.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntFileUploadWrapper)) {
			return false;
		}

		EntFileUploadWrapper entFileUploadWrapper = (EntFileUploadWrapper)obj;

		if (Objects.equals(_entFileUpload, entFileUploadWrapper._entFileUpload)) {
			return true;
		}

		return false;
	}

	@Override
	public EntFileUpload getWrappedModel() {
		return _entFileUpload;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entFileUpload.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _entFileUpload.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_entFileUpload.resetOriginalValues();
	}

	private final EntFileUpload _entFileUpload;
}