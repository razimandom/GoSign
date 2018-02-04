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
 * This class is a wrapper for {@link EntDoc}.
 * </p>
 *
 * @author Raziman Dom
 * @see EntDoc
 * @generated
 */
@ProviderType
public class EntDocWrapper implements EntDoc, ModelWrapper<EntDoc> {
	public EntDocWrapper(EntDoc entDoc) {
		_entDoc = entDoc;
	}

	@Override
	public Class<?> getModelClass() {
		return EntDoc.class;
	}

	@Override
	public String getModelClassName() {
		return EntDoc.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("docId", getDocId());
		attributes.put("fileId", getFileId());
		attributes.put("userId", getUserId());
		attributes.put("signId", getSignId());
		attributes.put("req_name", getReq_name());
		attributes.put("req_email", getReq_email());
		attributes.put("sign_name", getSign_name());
		attributes.put("sign_email", getSign_email());
		attributes.put("doc_title", getDoc_title());
		attributes.put("doc_type", getDoc_type());
		attributes.put("doc_md5", getDoc_md5());
		attributes.put("doc_status", getDoc_status());
		attributes.put("doc_deadline", getDoc_deadline());
		attributes.put("doc_description", getDoc_description());
		attributes.put("doc_signature", getDoc_signature());
		attributes.put("req_dateCreated", getReq_dateCreated());
		attributes.put("req_dateModified", getReq_dateModified());
		attributes.put("req_timeCreated", getReq_timeCreated());
		attributes.put("req_timeModified", getReq_timeModified());
		attributes.put("req_accepted", getReq_accepted());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long docId = (Long)attributes.get("docId");

		if (docId != null) {
			setDocId(docId);
		}

		Long fileId = (Long)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long signId = (Long)attributes.get("signId");

		if (signId != null) {
			setSignId(signId);
		}

		String req_name = (String)attributes.get("req_name");

		if (req_name != null) {
			setReq_name(req_name);
		}

		String req_email = (String)attributes.get("req_email");

		if (req_email != null) {
			setReq_email(req_email);
		}

		String sign_name = (String)attributes.get("sign_name");

		if (sign_name != null) {
			setSign_name(sign_name);
		}

		String sign_email = (String)attributes.get("sign_email");

		if (sign_email != null) {
			setSign_email(sign_email);
		}

		String doc_title = (String)attributes.get("doc_title");

		if (doc_title != null) {
			setDoc_title(doc_title);
		}

		String doc_type = (String)attributes.get("doc_type");

		if (doc_type != null) {
			setDoc_type(doc_type);
		}

		String doc_md5 = (String)attributes.get("doc_md5");

		if (doc_md5 != null) {
			setDoc_md5(doc_md5);
		}

		String doc_status = (String)attributes.get("doc_status");

		if (doc_status != null) {
			setDoc_status(doc_status);
		}

		String doc_deadline = (String)attributes.get("doc_deadline");

		if (doc_deadline != null) {
			setDoc_deadline(doc_deadline);
		}

		String doc_description = (String)attributes.get("doc_description");

		if (doc_description != null) {
			setDoc_description(doc_description);
		}

		String doc_signature = (String)attributes.get("doc_signature");

		if (doc_signature != null) {
			setDoc_signature(doc_signature);
		}

		String req_dateCreated = (String)attributes.get("req_dateCreated");

		if (req_dateCreated != null) {
			setReq_dateCreated(req_dateCreated);
		}

		String req_dateModified = (String)attributes.get("req_dateModified");

		if (req_dateModified != null) {
			setReq_dateModified(req_dateModified);
		}

		String req_timeCreated = (String)attributes.get("req_timeCreated");

		if (req_timeCreated != null) {
			setReq_timeCreated(req_timeCreated);
		}

		String req_timeModified = (String)attributes.get("req_timeModified");

		if (req_timeModified != null) {
			setReq_timeModified(req_timeModified);
		}

		Boolean req_accepted = (Boolean)attributes.get("req_accepted");

		if (req_accepted != null) {
			setReq_accepted(req_accepted);
		}
	}

	@Override
	public EntDoc toEscapedModel() {
		return new EntDocWrapper(_entDoc.toEscapedModel());
	}

	@Override
	public EntDoc toUnescapedModel() {
		return new EntDocWrapper(_entDoc.toUnescapedModel());
	}

	/**
	* Returns the req_accepted of this ent doc.
	*
	* @return the req_accepted of this ent doc
	*/
	@Override
	public boolean getReq_accepted() {
		return _entDoc.getReq_accepted();
	}

	@Override
	public boolean isCachedModel() {
		return _entDoc.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _entDoc.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _entDoc.isNew();
	}

	/**
	* Returns <code>true</code> if this ent doc is req_accepted.
	*
	* @return <code>true</code> if this ent doc is req_accepted; <code>false</code> otherwise
	*/
	@Override
	public boolean isReq_accepted() {
		return _entDoc.isReq_accepted();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _entDoc.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<EntDoc> toCacheModel() {
		return _entDoc.toCacheModel();
	}

	@Override
	public int compareTo(EntDoc entDoc) {
		return _entDoc.compareTo(entDoc);
	}

	@Override
	public int hashCode() {
		return _entDoc.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entDoc.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EntDocWrapper((EntDoc)_entDoc.clone());
	}

	/**
	* Returns the doc_deadline of this ent doc.
	*
	* @return the doc_deadline of this ent doc
	*/
	@Override
	public java.lang.String getDoc_deadline() {
		return _entDoc.getDoc_deadline();
	}

	/**
	* Returns the doc_description of this ent doc.
	*
	* @return the doc_description of this ent doc
	*/
	@Override
	public java.lang.String getDoc_description() {
		return _entDoc.getDoc_description();
	}

	/**
	* Returns the doc_md5 of this ent doc.
	*
	* @return the doc_md5 of this ent doc
	*/
	@Override
	public java.lang.String getDoc_md5() {
		return _entDoc.getDoc_md5();
	}

	/**
	* Returns the doc_signature of this ent doc.
	*
	* @return the doc_signature of this ent doc
	*/
	@Override
	public java.lang.String getDoc_signature() {
		return _entDoc.getDoc_signature();
	}

	/**
	* Returns the doc_status of this ent doc.
	*
	* @return the doc_status of this ent doc
	*/
	@Override
	public java.lang.String getDoc_status() {
		return _entDoc.getDoc_status();
	}

	/**
	* Returns the doc_title of this ent doc.
	*
	* @return the doc_title of this ent doc
	*/
	@Override
	public java.lang.String getDoc_title() {
		return _entDoc.getDoc_title();
	}

	/**
	* Returns the doc_type of this ent doc.
	*
	* @return the doc_type of this ent doc
	*/
	@Override
	public java.lang.String getDoc_type() {
		return _entDoc.getDoc_type();
	}

	/**
	* Returns the req_date created of this ent doc.
	*
	* @return the req_date created of this ent doc
	*/
	@Override
	public java.lang.String getReq_dateCreated() {
		return _entDoc.getReq_dateCreated();
	}

	/**
	* Returns the req_date modified of this ent doc.
	*
	* @return the req_date modified of this ent doc
	*/
	@Override
	public java.lang.String getReq_dateModified() {
		return _entDoc.getReq_dateModified();
	}

	/**
	* Returns the req_email of this ent doc.
	*
	* @return the req_email of this ent doc
	*/
	@Override
	public java.lang.String getReq_email() {
		return _entDoc.getReq_email();
	}

	/**
	* Returns the req_name of this ent doc.
	*
	* @return the req_name of this ent doc
	*/
	@Override
	public java.lang.String getReq_name() {
		return _entDoc.getReq_name();
	}

	/**
	* Returns the req_time created of this ent doc.
	*
	* @return the req_time created of this ent doc
	*/
	@Override
	public java.lang.String getReq_timeCreated() {
		return _entDoc.getReq_timeCreated();
	}

	/**
	* Returns the req_time modified of this ent doc.
	*
	* @return the req_time modified of this ent doc
	*/
	@Override
	public java.lang.String getReq_timeModified() {
		return _entDoc.getReq_timeModified();
	}

	/**
	* Returns the sign_email of this ent doc.
	*
	* @return the sign_email of this ent doc
	*/
	@Override
	public java.lang.String getSign_email() {
		return _entDoc.getSign_email();
	}

	/**
	* Returns the sign_name of this ent doc.
	*
	* @return the sign_name of this ent doc
	*/
	@Override
	public java.lang.String getSign_name() {
		return _entDoc.getSign_name();
	}

	/**
	* Returns the user uuid of this ent doc.
	*
	* @return the user uuid of this ent doc
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _entDoc.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _entDoc.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _entDoc.toXmlString();
	}

	/**
	* Returns the doc ID of this ent doc.
	*
	* @return the doc ID of this ent doc
	*/
	@Override
	public long getDocId() {
		return _entDoc.getDocId();
	}

	/**
	* Returns the file ID of this ent doc.
	*
	* @return the file ID of this ent doc
	*/
	@Override
	public long getFileId() {
		return _entDoc.getFileId();
	}

	/**
	* Returns the primary key of this ent doc.
	*
	* @return the primary key of this ent doc
	*/
	@Override
	public long getPrimaryKey() {
		return _entDoc.getPrimaryKey();
	}

	/**
	* Returns the sign ID of this ent doc.
	*
	* @return the sign ID of this ent doc
	*/
	@Override
	public long getSignId() {
		return _entDoc.getSignId();
	}

	/**
	* Returns the user ID of this ent doc.
	*
	* @return the user ID of this ent doc
	*/
	@Override
	public long getUserId() {
		return _entDoc.getUserId();
	}

	@Override
	public void persist() {
		_entDoc.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_entDoc.setCachedModel(cachedModel);
	}

	/**
	* Sets the doc ID of this ent doc.
	*
	* @param docId the doc ID of this ent doc
	*/
	@Override
	public void setDocId(long docId) {
		_entDoc.setDocId(docId);
	}

	/**
	* Sets the doc_deadline of this ent doc.
	*
	* @param doc_deadline the doc_deadline of this ent doc
	*/
	@Override
	public void setDoc_deadline(java.lang.String doc_deadline) {
		_entDoc.setDoc_deadline(doc_deadline);
	}

	/**
	* Sets the doc_description of this ent doc.
	*
	* @param doc_description the doc_description of this ent doc
	*/
	@Override
	public void setDoc_description(java.lang.String doc_description) {
		_entDoc.setDoc_description(doc_description);
	}

	/**
	* Sets the doc_md5 of this ent doc.
	*
	* @param doc_md5 the doc_md5 of this ent doc
	*/
	@Override
	public void setDoc_md5(java.lang.String doc_md5) {
		_entDoc.setDoc_md5(doc_md5);
	}

	/**
	* Sets the doc_signature of this ent doc.
	*
	* @param doc_signature the doc_signature of this ent doc
	*/
	@Override
	public void setDoc_signature(java.lang.String doc_signature) {
		_entDoc.setDoc_signature(doc_signature);
	}

	/**
	* Sets the doc_status of this ent doc.
	*
	* @param doc_status the doc_status of this ent doc
	*/
	@Override
	public void setDoc_status(java.lang.String doc_status) {
		_entDoc.setDoc_status(doc_status);
	}

	/**
	* Sets the doc_title of this ent doc.
	*
	* @param doc_title the doc_title of this ent doc
	*/
	@Override
	public void setDoc_title(java.lang.String doc_title) {
		_entDoc.setDoc_title(doc_title);
	}

	/**
	* Sets the doc_type of this ent doc.
	*
	* @param doc_type the doc_type of this ent doc
	*/
	@Override
	public void setDoc_type(java.lang.String doc_type) {
		_entDoc.setDoc_type(doc_type);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_entDoc.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_entDoc.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_entDoc.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file ID of this ent doc.
	*
	* @param fileId the file ID of this ent doc
	*/
	@Override
	public void setFileId(long fileId) {
		_entDoc.setFileId(fileId);
	}

	@Override
	public void setNew(boolean n) {
		_entDoc.setNew(n);
	}

	/**
	* Sets the primary key of this ent doc.
	*
	* @param primaryKey the primary key of this ent doc
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_entDoc.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_entDoc.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this ent doc is req_accepted.
	*
	* @param req_accepted the req_accepted of this ent doc
	*/
	@Override
	public void setReq_accepted(boolean req_accepted) {
		_entDoc.setReq_accepted(req_accepted);
	}

	/**
	* Sets the req_date created of this ent doc.
	*
	* @param req_dateCreated the req_date created of this ent doc
	*/
	@Override
	public void setReq_dateCreated(java.lang.String req_dateCreated) {
		_entDoc.setReq_dateCreated(req_dateCreated);
	}

	/**
	* Sets the req_date modified of this ent doc.
	*
	* @param req_dateModified the req_date modified of this ent doc
	*/
	@Override
	public void setReq_dateModified(java.lang.String req_dateModified) {
		_entDoc.setReq_dateModified(req_dateModified);
	}

	/**
	* Sets the req_email of this ent doc.
	*
	* @param req_email the req_email of this ent doc
	*/
	@Override
	public void setReq_email(java.lang.String req_email) {
		_entDoc.setReq_email(req_email);
	}

	/**
	* Sets the req_name of this ent doc.
	*
	* @param req_name the req_name of this ent doc
	*/
	@Override
	public void setReq_name(java.lang.String req_name) {
		_entDoc.setReq_name(req_name);
	}

	/**
	* Sets the req_time created of this ent doc.
	*
	* @param req_timeCreated the req_time created of this ent doc
	*/
	@Override
	public void setReq_timeCreated(java.lang.String req_timeCreated) {
		_entDoc.setReq_timeCreated(req_timeCreated);
	}

	/**
	* Sets the req_time modified of this ent doc.
	*
	* @param req_timeModified the req_time modified of this ent doc
	*/
	@Override
	public void setReq_timeModified(java.lang.String req_timeModified) {
		_entDoc.setReq_timeModified(req_timeModified);
	}

	/**
	* Sets the sign ID of this ent doc.
	*
	* @param signId the sign ID of this ent doc
	*/
	@Override
	public void setSignId(long signId) {
		_entDoc.setSignId(signId);
	}

	/**
	* Sets the sign_email of this ent doc.
	*
	* @param sign_email the sign_email of this ent doc
	*/
	@Override
	public void setSign_email(java.lang.String sign_email) {
		_entDoc.setSign_email(sign_email);
	}

	/**
	* Sets the sign_name of this ent doc.
	*
	* @param sign_name the sign_name of this ent doc
	*/
	@Override
	public void setSign_name(java.lang.String sign_name) {
		_entDoc.setSign_name(sign_name);
	}

	/**
	* Sets the user ID of this ent doc.
	*
	* @param userId the user ID of this ent doc
	*/
	@Override
	public void setUserId(long userId) {
		_entDoc.setUserId(userId);
	}

	/**
	* Sets the user uuid of this ent doc.
	*
	* @param userUuid the user uuid of this ent doc
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_entDoc.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntDocWrapper)) {
			return false;
		}

		EntDocWrapper entDocWrapper = (EntDocWrapper)obj;

		if (Objects.equals(_entDoc, entDocWrapper._entDoc)) {
			return true;
		}

		return false;
	}

	@Override
	public EntDoc getWrappedModel() {
		return _entDoc;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entDoc.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _entDoc.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_entDoc.resetOriginalValues();
	}

	private final EntDoc _entDoc;
}