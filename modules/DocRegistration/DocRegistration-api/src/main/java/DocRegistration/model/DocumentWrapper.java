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
 * This class is a wrapper for {@link Document}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Document
 * @generated
 */
@ProviderType
public class DocumentWrapper implements Document, ModelWrapper<Document> {
	public DocumentWrapper(Document document) {
		_document = document;
	}

	@Override
	public Class<?> getModelClass() {
		return Document.class;
	}

	@Override
	public String getModelClassName() {
		return Document.class.getName();
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
		attributes.put("sign_email", getSign_email());
		attributes.put("doc_type", getDoc_type());
		attributes.put("doc_status", getDoc_status());
		attributes.put("doc_deadline", getDoc_deadline());
		attributes.put("doc_description", getDoc_description());
		attributes.put("file_name", getFile_name());
		attributes.put("file_type", getFile_type());
		attributes.put("file_blob", getFile_blob());
		attributes.put("file_md5", getFile_md5());
		attributes.put("req_dateCreated", getReq_dateCreated());
		attributes.put("req_dateModified", getReq_dateModified());

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

		String sign_email = (String)attributes.get("sign_email");

		if (sign_email != null) {
			setSign_email(sign_email);
		}

		String doc_type = (String)attributes.get("doc_type");

		if (doc_type != null) {
			setDoc_type(doc_type);
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

		String file_md5 = (String)attributes.get("file_md5");

		if (file_md5 != null) {
			setFile_md5(file_md5);
		}

		String req_dateCreated = (String)attributes.get("req_dateCreated");

		if (req_dateCreated != null) {
			setReq_dateCreated(req_dateCreated);
		}

		String req_dateModified = (String)attributes.get("req_dateModified");

		if (req_dateModified != null) {
			setReq_dateModified(req_dateModified);
		}
	}

	@Override
	public DocRegistration.model.Document toEscapedModel() {
		return new DocumentWrapper(_document.toEscapedModel());
	}

	@Override
	public DocRegistration.model.Document toUnescapedModel() {
		return new DocumentWrapper(_document.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _document.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _document.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _document.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _document.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DocRegistration.model.Document> toCacheModel() {
		return _document.toCacheModel();
	}

	@Override
	public int compareTo(DocRegistration.model.Document document) {
		return _document.compareTo(document);
	}

	@Override
	public int hashCode() {
		return _document.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _document.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DocumentWrapper((Document)_document.clone());
	}

	/**
	* Returns the doc_deadline of this document.
	*
	* @return the doc_deadline of this document
	*/
	@Override
	public java.lang.String getDoc_deadline() {
		return _document.getDoc_deadline();
	}

	/**
	* Returns the doc_description of this document.
	*
	* @return the doc_description of this document
	*/
	@Override
	public java.lang.String getDoc_description() {
		return _document.getDoc_description();
	}

	/**
	* Returns the doc_status of this document.
	*
	* @return the doc_status of this document
	*/
	@Override
	public java.lang.String getDoc_status() {
		return _document.getDoc_status();
	}

	/**
	* Returns the doc_type of this document.
	*
	* @return the doc_type of this document
	*/
	@Override
	public java.lang.String getDoc_type() {
		return _document.getDoc_type();
	}

	/**
	* Returns the file_md5 of this document.
	*
	* @return the file_md5 of this document
	*/
	@Override
	public java.lang.String getFile_md5() {
		return _document.getFile_md5();
	}

	/**
	* Returns the file_name of this document.
	*
	* @return the file_name of this document
	*/
	@Override
	public java.lang.String getFile_name() {
		return _document.getFile_name();
	}

	/**
	* Returns the file_type of this document.
	*
	* @return the file_type of this document
	*/
	@Override
	public java.lang.String getFile_type() {
		return _document.getFile_type();
	}

	/**
	* Returns the req_date created of this document.
	*
	* @return the req_date created of this document
	*/
	@Override
	public java.lang.String getReq_dateCreated() {
		return _document.getReq_dateCreated();
	}

	/**
	* Returns the req_date modified of this document.
	*
	* @return the req_date modified of this document
	*/
	@Override
	public java.lang.String getReq_dateModified() {
		return _document.getReq_dateModified();
	}

	/**
	* Returns the req_email of this document.
	*
	* @return the req_email of this document
	*/
	@Override
	public java.lang.String getReq_email() {
		return _document.getReq_email();
	}

	/**
	* Returns the req_name of this document.
	*
	* @return the req_name of this document
	*/
	@Override
	public java.lang.String getReq_name() {
		return _document.getReq_name();
	}

	/**
	* Returns the sign_email of this document.
	*
	* @return the sign_email of this document
	*/
	@Override
	public java.lang.String getSign_email() {
		return _document.getSign_email();
	}

	/**
	* Returns the user uuid of this document.
	*
	* @return the user uuid of this document
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _document.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _document.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _document.toXmlString();
	}

	/**
	* Returns the file_blob of this document.
	*
	* @return the file_blob of this document
	*/
	@Override
	public Blob getFile_blob() {
		return _document.getFile_blob();
	}

	/**
	* Returns the doc ID of this document.
	*
	* @return the doc ID of this document
	*/
	@Override
	public long getDocId() {
		return _document.getDocId();
	}

	/**
	* Returns the file ID of this document.
	*
	* @return the file ID of this document
	*/
	@Override
	public long getFileId() {
		return _document.getFileId();
	}

	/**
	* Returns the primary key of this document.
	*
	* @return the primary key of this document
	*/
	@Override
	public long getPrimaryKey() {
		return _document.getPrimaryKey();
	}

	/**
	* Returns the sign ID of this document.
	*
	* @return the sign ID of this document
	*/
	@Override
	public long getSignId() {
		return _document.getSignId();
	}

	/**
	* Returns the user ID of this document.
	*
	* @return the user ID of this document
	*/
	@Override
	public long getUserId() {
		return _document.getUserId();
	}

	@Override
	public void persist() {
		_document.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_document.setCachedModel(cachedModel);
	}

	/**
	* Sets the doc ID of this document.
	*
	* @param docId the doc ID of this document
	*/
	@Override
	public void setDocId(long docId) {
		_document.setDocId(docId);
	}

	/**
	* Sets the doc_deadline of this document.
	*
	* @param doc_deadline the doc_deadline of this document
	*/
	@Override
	public void setDoc_deadline(java.lang.String doc_deadline) {
		_document.setDoc_deadline(doc_deadline);
	}

	/**
	* Sets the doc_description of this document.
	*
	* @param doc_description the doc_description of this document
	*/
	@Override
	public void setDoc_description(java.lang.String doc_description) {
		_document.setDoc_description(doc_description);
	}

	/**
	* Sets the doc_status of this document.
	*
	* @param doc_status the doc_status of this document
	*/
	@Override
	public void setDoc_status(java.lang.String doc_status) {
		_document.setDoc_status(doc_status);
	}

	/**
	* Sets the doc_type of this document.
	*
	* @param doc_type the doc_type of this document
	*/
	@Override
	public void setDoc_type(java.lang.String doc_type) {
		_document.setDoc_type(doc_type);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_document.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_document.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_document.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file ID of this document.
	*
	* @param fileId the file ID of this document
	*/
	@Override
	public void setFileId(long fileId) {
		_document.setFileId(fileId);
	}

	/**
	* Sets the file_blob of this document.
	*
	* @param file_blob the file_blob of this document
	*/
	@Override
	public void setFile_blob(Blob file_blob) {
		_document.setFile_blob(file_blob);
	}

	/**
	* Sets the file_md5 of this document.
	*
	* @param file_md5 the file_md5 of this document
	*/
	@Override
	public void setFile_md5(java.lang.String file_md5) {
		_document.setFile_md5(file_md5);
	}

	/**
	* Sets the file_name of this document.
	*
	* @param file_name the file_name of this document
	*/
	@Override
	public void setFile_name(java.lang.String file_name) {
		_document.setFile_name(file_name);
	}

	/**
	* Sets the file_type of this document.
	*
	* @param file_type the file_type of this document
	*/
	@Override
	public void setFile_type(java.lang.String file_type) {
		_document.setFile_type(file_type);
	}

	@Override
	public void setNew(boolean n) {
		_document.setNew(n);
	}

	/**
	* Sets the primary key of this document.
	*
	* @param primaryKey the primary key of this document
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_document.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_document.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the req_date created of this document.
	*
	* @param req_dateCreated the req_date created of this document
	*/
	@Override
	public void setReq_dateCreated(java.lang.String req_dateCreated) {
		_document.setReq_dateCreated(req_dateCreated);
	}

	/**
	* Sets the req_date modified of this document.
	*
	* @param req_dateModified the req_date modified of this document
	*/
	@Override
	public void setReq_dateModified(java.lang.String req_dateModified) {
		_document.setReq_dateModified(req_dateModified);
	}

	/**
	* Sets the req_email of this document.
	*
	* @param req_email the req_email of this document
	*/
	@Override
	public void setReq_email(java.lang.String req_email) {
		_document.setReq_email(req_email);
	}

	/**
	* Sets the req_name of this document.
	*
	* @param req_name the req_name of this document
	*/
	@Override
	public void setReq_name(java.lang.String req_name) {
		_document.setReq_name(req_name);
	}

	/**
	* Sets the sign ID of this document.
	*
	* @param signId the sign ID of this document
	*/
	@Override
	public void setSignId(long signId) {
		_document.setSignId(signId);
	}

	/**
	* Sets the sign_email of this document.
	*
	* @param sign_email the sign_email of this document
	*/
	@Override
	public void setSign_email(java.lang.String sign_email) {
		_document.setSign_email(sign_email);
	}

	/**
	* Sets the user ID of this document.
	*
	* @param userId the user ID of this document
	*/
	@Override
	public void setUserId(long userId) {
		_document.setUserId(userId);
	}

	/**
	* Sets the user uuid of this document.
	*
	* @param userUuid the user uuid of this document
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_document.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocumentWrapper)) {
			return false;
		}

		DocumentWrapper documentWrapper = (DocumentWrapper)obj;

		if (Objects.equals(_document, documentWrapper._document)) {
			return true;
		}

		return false;
	}

	@Override
	public Document getWrappedModel() {
		return _document;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _document.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _document.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_document.resetOriginalValues();
	}

	private final Document _document;
}