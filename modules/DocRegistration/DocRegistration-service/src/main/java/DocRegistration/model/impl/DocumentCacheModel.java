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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Document in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Document
 * @generated
 */
@ProviderType
public class DocumentCacheModel implements CacheModel<Document>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocumentCacheModel)) {
			return false;
		}

		DocumentCacheModel documentCacheModel = (DocumentCacheModel)obj;

		if (docId == documentCacheModel.docId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, docId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{docId=");
		sb.append(docId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", req_name=");
		sb.append(req_name);
		sb.append(", req_email=");
		sb.append(req_email);
		sb.append(", sign_email=");
		sb.append(sign_email);
		sb.append(", doc_type=");
		sb.append(doc_type);
		sb.append(", doc_status=");
		sb.append(doc_status);
		sb.append(", doc_deadline=");
		sb.append(doc_deadline);
		sb.append(", doc_description=");
		sb.append(doc_description);
		sb.append(", file_name=");
		sb.append(file_name);
		sb.append(", file_type=");
		sb.append(file_type);
		sb.append(", file_md5=");
		sb.append(file_md5);
		sb.append(", req_dateCreated=");
		sb.append(req_dateCreated);
		sb.append(", req_dateModified=");
		sb.append(req_dateModified);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Document toEntityModel() {
		DocumentImpl documentImpl = new DocumentImpl();

		documentImpl.setDocId(docId);
		documentImpl.setUserId(userId);
		documentImpl.setFileId(fileId);

		if (req_name == null) {
			documentImpl.setReq_name(StringPool.BLANK);
		}
		else {
			documentImpl.setReq_name(req_name);
		}

		if (req_email == null) {
			documentImpl.setReq_email(StringPool.BLANK);
		}
		else {
			documentImpl.setReq_email(req_email);
		}

		if (sign_email == null) {
			documentImpl.setSign_email(StringPool.BLANK);
		}
		else {
			documentImpl.setSign_email(sign_email);
		}

		if (doc_type == null) {
			documentImpl.setDoc_type(StringPool.BLANK);
		}
		else {
			documentImpl.setDoc_type(doc_type);
		}

		if (doc_status == null) {
			documentImpl.setDoc_status(StringPool.BLANK);
		}
		else {
			documentImpl.setDoc_status(doc_status);
		}

		if (doc_deadline == null) {
			documentImpl.setDoc_deadline(StringPool.BLANK);
		}
		else {
			documentImpl.setDoc_deadline(doc_deadline);
		}

		if (doc_description == null) {
			documentImpl.setDoc_description(StringPool.BLANK);
		}
		else {
			documentImpl.setDoc_description(doc_description);
		}

		if (file_name == null) {
			documentImpl.setFile_name(StringPool.BLANK);
		}
		else {
			documentImpl.setFile_name(file_name);
		}

		if (file_type == null) {
			documentImpl.setFile_type(StringPool.BLANK);
		}
		else {
			documentImpl.setFile_type(file_type);
		}

		if (file_md5 == null) {
			documentImpl.setFile_md5(StringPool.BLANK);
		}
		else {
			documentImpl.setFile_md5(file_md5);
		}

		if (req_dateCreated == null) {
			documentImpl.setReq_dateCreated(StringPool.BLANK);
		}
		else {
			documentImpl.setReq_dateCreated(req_dateCreated);
		}

		if (req_dateModified == null) {
			documentImpl.setReq_dateModified(StringPool.BLANK);
		}
		else {
			documentImpl.setReq_dateModified(req_dateModified);
		}

		documentImpl.resetOriginalValues();

		return documentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		docId = objectInput.readLong();

		userId = objectInput.readLong();

		fileId = objectInput.readLong();
		req_name = objectInput.readUTF();
		req_email = objectInput.readUTF();
		sign_email = objectInput.readUTF();
		doc_type = objectInput.readUTF();
		doc_status = objectInput.readUTF();
		doc_deadline = objectInput.readUTF();
		doc_description = objectInput.readUTF();
		file_name = objectInput.readUTF();
		file_type = objectInput.readUTF();
		file_md5 = objectInput.readUTF();
		req_dateCreated = objectInput.readUTF();
		req_dateModified = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(docId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(fileId);

		if (req_name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(req_name);
		}

		if (req_email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(req_email);
		}

		if (sign_email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sign_email);
		}

		if (doc_type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(doc_type);
		}

		if (doc_status == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(doc_status);
		}

		if (doc_deadline == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(doc_deadline);
		}

		if (doc_description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(doc_description);
		}

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

		if (file_md5 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(file_md5);
		}

		if (req_dateCreated == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(req_dateCreated);
		}

		if (req_dateModified == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(req_dateModified);
		}
	}

	public long docId;
	public long userId;
	public long fileId;
	public String req_name;
	public String req_email;
	public String sign_email;
	public String doc_type;
	public String doc_status;
	public String doc_deadline;
	public String doc_description;
	public String file_name;
	public String file_type;
	public String file_md5;
	public String req_dateCreated;
	public String req_dateModified;
}