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

package DocRegistration.model.impl;

import DocRegistration.model.Document;
import DocRegistration.model.DocumentFile_blobBlobModel;
import DocRegistration.model.DocumentModel;
import DocRegistration.model.DocumentSoap;

import DocRegistration.service.DocumentLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Blob;
import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Document service. Represents a row in the &quot;document_data&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link DocumentModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DocumentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentImpl
 * @see Document
 * @see DocumentModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class DocumentModelImpl extends BaseModelImpl<Document>
	implements DocumentModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document model instance should use the {@link Document} interface instead.
	 */
	public static final String TABLE_NAME = "document_data";
	public static final Object[][] TABLE_COLUMNS = {
			{ "docId", Types.BIGINT },
			{ "fileId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "req_name", Types.VARCHAR },
			{ "req_email", Types.VARCHAR },
			{ "sign_email", Types.VARCHAR },
			{ "doc_type", Types.VARCHAR },
			{ "doc_status", Types.VARCHAR },
			{ "doc_deadline", Types.VARCHAR },
			{ "doc_description", Types.VARCHAR },
			{ "file_name", Types.VARCHAR },
			{ "file_type", Types.VARCHAR },
			{ "file_blob", Types.BLOB },
			{ "file_md5", Types.VARCHAR },
			{ "req_dateCreated", Types.VARCHAR },
			{ "req_dateModified", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("docId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("req_name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("req_email", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sign_email", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("doc_type", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("doc_status", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("doc_deadline", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("doc_description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("file_name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("file_type", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("file_blob", Types.BLOB);
		TABLE_COLUMNS_MAP.put("file_md5", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("req_dateCreated", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("req_dateModified", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table document_data (docId LONG not null primary key,fileId LONG,userId LONG,req_name VARCHAR(75) null,req_email VARCHAR(75) null,sign_email VARCHAR(75) null,doc_type VARCHAR(75) null,doc_status VARCHAR(75) null,doc_deadline VARCHAR(75) null,doc_description VARCHAR(75) null,file_name VARCHAR(75) null,file_type VARCHAR(75) null,file_blob BLOB,file_md5 VARCHAR(75) null,req_dateCreated VARCHAR(75) null,req_dateModified VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table document_data";
	public static final String ORDER_BY_JPQL = " ORDER BY document.docId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY document_data.docId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(DocRegistration.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.DocRegistration.model.Document"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(DocRegistration.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.DocRegistration.model.Document"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(DocRegistration.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.DocRegistration.model.Document"),
			true);
	public static final long SIGN_EMAIL_COLUMN_BITMASK = 1L;
	public static final long USERID_COLUMN_BITMASK = 2L;
	public static final long DOCID_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Document toModel(DocumentSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Document model = new DocumentImpl();

		model.setDocId(soapModel.getDocId());
		model.setFileId(soapModel.getFileId());
		model.setUserId(soapModel.getUserId());
		model.setReq_name(soapModel.getReq_name());
		model.setReq_email(soapModel.getReq_email());
		model.setSign_email(soapModel.getSign_email());
		model.setDoc_type(soapModel.getDoc_type());
		model.setDoc_status(soapModel.getDoc_status());
		model.setDoc_deadline(soapModel.getDoc_deadline());
		model.setDoc_description(soapModel.getDoc_description());
		model.setFile_name(soapModel.getFile_name());
		model.setFile_type(soapModel.getFile_type());
		model.setFile_blob(soapModel.getFile_blob());
		model.setFile_md5(soapModel.getFile_md5());
		model.setReq_dateCreated(soapModel.getReq_dateCreated());
		model.setReq_dateModified(soapModel.getReq_dateModified());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Document> toModels(DocumentSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Document> models = new ArrayList<Document>(soapModels.length);

		for (DocumentSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(DocRegistration.service.util.ServiceProps.get(
				"lock.expiration.time.DocRegistration.model.Document"));

	public DocumentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _docId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDocId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _docId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

	@JSON
	@Override
	public long getDocId() {
		return _docId;
	}

	@Override
	public void setDocId(long docId) {
		_docId = docId;
	}

	@JSON
	@Override
	public long getFileId() {
		return _fileId;
	}

	@Override
	public void setFileId(long fileId) {
		_fileId = fileId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@JSON
	@Override
	public String getReq_name() {
		if (_req_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _req_name;
		}
	}

	@Override
	public void setReq_name(String req_name) {
		_req_name = req_name;
	}

	@JSON
	@Override
	public String getReq_email() {
		if (_req_email == null) {
			return StringPool.BLANK;
		}
		else {
			return _req_email;
		}
	}

	@Override
	public void setReq_email(String req_email) {
		_req_email = req_email;
	}

	@JSON
	@Override
	public String getSign_email() {
		if (_sign_email == null) {
			return StringPool.BLANK;
		}
		else {
			return _sign_email;
		}
	}

	@Override
	public void setSign_email(String sign_email) {
		_columnBitmask |= SIGN_EMAIL_COLUMN_BITMASK;

		if (_originalSign_email == null) {
			_originalSign_email = _sign_email;
		}

		_sign_email = sign_email;
	}

	public String getOriginalSign_email() {
		return GetterUtil.getString(_originalSign_email);
	}

	@JSON
	@Override
	public String getDoc_type() {
		if (_doc_type == null) {
			return StringPool.BLANK;
		}
		else {
			return _doc_type;
		}
	}

	@Override
	public void setDoc_type(String doc_type) {
		_doc_type = doc_type;
	}

	@JSON
	@Override
	public String getDoc_status() {
		if (_doc_status == null) {
			return StringPool.BLANK;
		}
		else {
			return _doc_status;
		}
	}

	@Override
	public void setDoc_status(String doc_status) {
		_doc_status = doc_status;
	}

	@JSON
	@Override
	public String getDoc_deadline() {
		if (_doc_deadline == null) {
			return StringPool.BLANK;
		}
		else {
			return _doc_deadline;
		}
	}

	@Override
	public void setDoc_deadline(String doc_deadline) {
		_doc_deadline = doc_deadline;
	}

	@JSON
	@Override
	public String getDoc_description() {
		if (_doc_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _doc_description;
		}
	}

	@Override
	public void setDoc_description(String doc_description) {
		_doc_description = doc_description;
	}

	@JSON
	@Override
	public String getFile_name() {
		if (_file_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _file_name;
		}
	}

	@Override
	public void setFile_name(String file_name) {
		_file_name = file_name;
	}

	@JSON
	@Override
	public String getFile_type() {
		if (_file_type == null) {
			return StringPool.BLANK;
		}
		else {
			return _file_type;
		}
	}

	@Override
	public void setFile_type(String file_type) {
		_file_type = file_type;
	}

	@JSON
	@Override
	public Blob getFile_blob() {
		if (_file_blobBlobModel == null) {
			try {
				_file_blobBlobModel = DocumentLocalServiceUtil.getFile_blobBlobModel(getPrimaryKey());
			}
			catch (Exception e) {
			}
		}

		Blob blob = null;

		if (_file_blobBlobModel != null) {
			blob = _file_blobBlobModel.getFile_blobBlob();
		}

		return blob;
	}

	@Override
	public void setFile_blob(Blob file_blob) {
		if (_file_blobBlobModel == null) {
			_file_blobBlobModel = new DocumentFile_blobBlobModel(getPrimaryKey(),
					file_blob);
		}
		else {
			_file_blobBlobModel.setFile_blobBlob(file_blob);
		}
	}

	@JSON
	@Override
	public String getFile_md5() {
		if (_file_md5 == null) {
			return StringPool.BLANK;
		}
		else {
			return _file_md5;
		}
	}

	@Override
	public void setFile_md5(String file_md5) {
		_file_md5 = file_md5;
	}

	@JSON
	@Override
	public String getReq_dateCreated() {
		if (_req_dateCreated == null) {
			return StringPool.BLANK;
		}
		else {
			return _req_dateCreated;
		}
	}

	@Override
	public void setReq_dateCreated(String req_dateCreated) {
		_req_dateCreated = req_dateCreated;
	}

	@JSON
	@Override
	public String getReq_dateModified() {
		if (_req_dateModified == null) {
			return StringPool.BLANK;
		}
		else {
			return _req_dateModified;
		}
	}

	@Override
	public void setReq_dateModified(String req_dateModified) {
		_req_dateModified = req_dateModified;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Document.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Document toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Document)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DocumentImpl documentImpl = new DocumentImpl();

		documentImpl.setDocId(getDocId());
		documentImpl.setFileId(getFileId());
		documentImpl.setUserId(getUserId());
		documentImpl.setReq_name(getReq_name());
		documentImpl.setReq_email(getReq_email());
		documentImpl.setSign_email(getSign_email());
		documentImpl.setDoc_type(getDoc_type());
		documentImpl.setDoc_status(getDoc_status());
		documentImpl.setDoc_deadline(getDoc_deadline());
		documentImpl.setDoc_description(getDoc_description());
		documentImpl.setFile_name(getFile_name());
		documentImpl.setFile_type(getFile_type());
		documentImpl.setFile_md5(getFile_md5());
		documentImpl.setReq_dateCreated(getReq_dateCreated());
		documentImpl.setReq_dateModified(getReq_dateModified());

		documentImpl.resetOriginalValues();

		return documentImpl;
	}

	@Override
	public int compareTo(Document document) {
		long primaryKey = document.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Document)) {
			return false;
		}

		Document document = (Document)obj;

		long primaryKey = document.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		DocumentModelImpl documentModelImpl = this;

		documentModelImpl._originalUserId = documentModelImpl._userId;

		documentModelImpl._setOriginalUserId = false;

		documentModelImpl._originalSign_email = documentModelImpl._sign_email;

		documentModelImpl._file_blobBlobModel = null;

		documentModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Document> toCacheModel() {
		DocumentCacheModel documentCacheModel = new DocumentCacheModel();

		documentCacheModel.docId = getDocId();

		documentCacheModel.fileId = getFileId();

		documentCacheModel.userId = getUserId();

		documentCacheModel.req_name = getReq_name();

		String req_name = documentCacheModel.req_name;

		if ((req_name != null) && (req_name.length() == 0)) {
			documentCacheModel.req_name = null;
		}

		documentCacheModel.req_email = getReq_email();

		String req_email = documentCacheModel.req_email;

		if ((req_email != null) && (req_email.length() == 0)) {
			documentCacheModel.req_email = null;
		}

		documentCacheModel.sign_email = getSign_email();

		String sign_email = documentCacheModel.sign_email;

		if ((sign_email != null) && (sign_email.length() == 0)) {
			documentCacheModel.sign_email = null;
		}

		documentCacheModel.doc_type = getDoc_type();

		String doc_type = documentCacheModel.doc_type;

		if ((doc_type != null) && (doc_type.length() == 0)) {
			documentCacheModel.doc_type = null;
		}

		documentCacheModel.doc_status = getDoc_status();

		String doc_status = documentCacheModel.doc_status;

		if ((doc_status != null) && (doc_status.length() == 0)) {
			documentCacheModel.doc_status = null;
		}

		documentCacheModel.doc_deadline = getDoc_deadline();

		String doc_deadline = documentCacheModel.doc_deadline;

		if ((doc_deadline != null) && (doc_deadline.length() == 0)) {
			documentCacheModel.doc_deadline = null;
		}

		documentCacheModel.doc_description = getDoc_description();

		String doc_description = documentCacheModel.doc_description;

		if ((doc_description != null) && (doc_description.length() == 0)) {
			documentCacheModel.doc_description = null;
		}

		documentCacheModel.file_name = getFile_name();

		String file_name = documentCacheModel.file_name;

		if ((file_name != null) && (file_name.length() == 0)) {
			documentCacheModel.file_name = null;
		}

		documentCacheModel.file_type = getFile_type();

		String file_type = documentCacheModel.file_type;

		if ((file_type != null) && (file_type.length() == 0)) {
			documentCacheModel.file_type = null;
		}

		documentCacheModel.file_md5 = getFile_md5();

		String file_md5 = documentCacheModel.file_md5;

		if ((file_md5 != null) && (file_md5.length() == 0)) {
			documentCacheModel.file_md5 = null;
		}

		documentCacheModel.req_dateCreated = getReq_dateCreated();

		String req_dateCreated = documentCacheModel.req_dateCreated;

		if ((req_dateCreated != null) && (req_dateCreated.length() == 0)) {
			documentCacheModel.req_dateCreated = null;
		}

		documentCacheModel.req_dateModified = getReq_dateModified();

		String req_dateModified = documentCacheModel.req_dateModified;

		if ((req_dateModified != null) && (req_dateModified.length() == 0)) {
			documentCacheModel.req_dateModified = null;
		}

		return documentCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{docId=");
		sb.append(getDocId());
		sb.append(", fileId=");
		sb.append(getFileId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", req_name=");
		sb.append(getReq_name());
		sb.append(", req_email=");
		sb.append(getReq_email());
		sb.append(", sign_email=");
		sb.append(getSign_email());
		sb.append(", doc_type=");
		sb.append(getDoc_type());
		sb.append(", doc_status=");
		sb.append(getDoc_status());
		sb.append(", doc_deadline=");
		sb.append(getDoc_deadline());
		sb.append(", doc_description=");
		sb.append(getDoc_description());
		sb.append(", file_name=");
		sb.append(getFile_name());
		sb.append(", file_type=");
		sb.append(getFile_type());
		sb.append(", file_md5=");
		sb.append(getFile_md5());
		sb.append(", req_dateCreated=");
		sb.append(getReq_dateCreated());
		sb.append(", req_dateModified=");
		sb.append(getReq_dateModified());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("DocRegistration.model.Document");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>docId</column-name><column-value><![CDATA[");
		sb.append(getDocId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileId</column-name><column-value><![CDATA[");
		sb.append(getFileId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>req_name</column-name><column-value><![CDATA[");
		sb.append(getReq_name());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>req_email</column-name><column-value><![CDATA[");
		sb.append(getReq_email());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sign_email</column-name><column-value><![CDATA[");
		sb.append(getSign_email());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>doc_type</column-name><column-value><![CDATA[");
		sb.append(getDoc_type());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>doc_status</column-name><column-value><![CDATA[");
		sb.append(getDoc_status());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>doc_deadline</column-name><column-value><![CDATA[");
		sb.append(getDoc_deadline());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>doc_description</column-name><column-value><![CDATA[");
		sb.append(getDoc_description());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>file_name</column-name><column-value><![CDATA[");
		sb.append(getFile_name());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>file_type</column-name><column-value><![CDATA[");
		sb.append(getFile_type());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>file_md5</column-name><column-value><![CDATA[");
		sb.append(getFile_md5());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>req_dateCreated</column-name><column-value><![CDATA[");
		sb.append(getReq_dateCreated());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>req_dateModified</column-name><column-value><![CDATA[");
		sb.append(getReq_dateModified());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Document.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Document.class
		};
	private long _docId;
	private long _fileId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _req_name;
	private String _req_email;
	private String _sign_email;
	private String _originalSign_email;
	private String _doc_type;
	private String _doc_status;
	private String _doc_deadline;
	private String _doc_description;
	private String _file_name;
	private String _file_type;
	private DocumentFile_blobBlobModel _file_blobBlobModel;
	private String _file_md5;
	private String _req_dateCreated;
	private String _req_dateModified;
	private long _columnBitmask;
	private Document _escapedModel;
}