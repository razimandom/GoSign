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

import com._42Penguins.gosign.model.EntDoc;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing EntDoc in entity cache.
 *
 * @author Raziman Dom
 * @see EntDoc
 * @generated
 */
@ProviderType
public class EntDocCacheModel implements CacheModel<EntDoc>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntDocCacheModel)) {
			return false;
		}

		EntDocCacheModel entDocCacheModel = (EntDocCacheModel)obj;

		if (docId == entDocCacheModel.docId) {
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
		StringBundler sb = new StringBundler(41);

		sb.append("{docId=");
		sb.append(docId);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", signId=");
		sb.append(signId);
		sb.append(", req_name=");
		sb.append(req_name);
		sb.append(", req_email=");
		sb.append(req_email);
		sb.append(", sign_name=");
		sb.append(sign_name);
		sb.append(", sign_email=");
		sb.append(sign_email);
		sb.append(", doc_title=");
		sb.append(doc_title);
		sb.append(", doc_type=");
		sb.append(doc_type);
		sb.append(", doc_md5=");
		sb.append(doc_md5);
		sb.append(", doc_status=");
		sb.append(doc_status);
		sb.append(", doc_deadline=");
		sb.append(doc_deadline);
		sb.append(", doc_description=");
		sb.append(doc_description);
		sb.append(", doc_signature=");
		sb.append(doc_signature);
		sb.append(", req_dateCreated=");
		sb.append(req_dateCreated);
		sb.append(", req_dateModified=");
		sb.append(req_dateModified);
		sb.append(", req_timeCreated=");
		sb.append(req_timeCreated);
		sb.append(", req_timeModified=");
		sb.append(req_timeModified);
		sb.append(", req_accepted=");
		sb.append(req_accepted);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EntDoc toEntityModel() {
		EntDocImpl entDocImpl = new EntDocImpl();

		entDocImpl.setDocId(docId);
		entDocImpl.setFileId(fileId);
		entDocImpl.setUserId(userId);
		entDocImpl.setSignId(signId);

		if (req_name == null) {
			entDocImpl.setReq_name(StringPool.BLANK);
		}
		else {
			entDocImpl.setReq_name(req_name);
		}

		if (req_email == null) {
			entDocImpl.setReq_email(StringPool.BLANK);
		}
		else {
			entDocImpl.setReq_email(req_email);
		}

		if (sign_name == null) {
			entDocImpl.setSign_name(StringPool.BLANK);
		}
		else {
			entDocImpl.setSign_name(sign_name);
		}

		if (sign_email == null) {
			entDocImpl.setSign_email(StringPool.BLANK);
		}
		else {
			entDocImpl.setSign_email(sign_email);
		}

		if (doc_title == null) {
			entDocImpl.setDoc_title(StringPool.BLANK);
		}
		else {
			entDocImpl.setDoc_title(doc_title);
		}

		if (doc_type == null) {
			entDocImpl.setDoc_type(StringPool.BLANK);
		}
		else {
			entDocImpl.setDoc_type(doc_type);
		}

		if (doc_md5 == null) {
			entDocImpl.setDoc_md5(StringPool.BLANK);
		}
		else {
			entDocImpl.setDoc_md5(doc_md5);
		}

		if (doc_status == null) {
			entDocImpl.setDoc_status(StringPool.BLANK);
		}
		else {
			entDocImpl.setDoc_status(doc_status);
		}

		if (doc_deadline == null) {
			entDocImpl.setDoc_deadline(StringPool.BLANK);
		}
		else {
			entDocImpl.setDoc_deadline(doc_deadline);
		}

		if (doc_description == null) {
			entDocImpl.setDoc_description(StringPool.BLANK);
		}
		else {
			entDocImpl.setDoc_description(doc_description);
		}

		if (doc_signature == null) {
			entDocImpl.setDoc_signature(StringPool.BLANK);
		}
		else {
			entDocImpl.setDoc_signature(doc_signature);
		}

		if (req_dateCreated == null) {
			entDocImpl.setReq_dateCreated(StringPool.BLANK);
		}
		else {
			entDocImpl.setReq_dateCreated(req_dateCreated);
		}

		if (req_dateModified == null) {
			entDocImpl.setReq_dateModified(StringPool.BLANK);
		}
		else {
			entDocImpl.setReq_dateModified(req_dateModified);
		}

		if (req_timeCreated == null) {
			entDocImpl.setReq_timeCreated(StringPool.BLANK);
		}
		else {
			entDocImpl.setReq_timeCreated(req_timeCreated);
		}

		if (req_timeModified == null) {
			entDocImpl.setReq_timeModified(StringPool.BLANK);
		}
		else {
			entDocImpl.setReq_timeModified(req_timeModified);
		}

		entDocImpl.setReq_accepted(req_accepted);

		entDocImpl.resetOriginalValues();

		return entDocImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		docId = objectInput.readLong();

		fileId = objectInput.readLong();

		userId = objectInput.readLong();

		signId = objectInput.readLong();
		req_name = objectInput.readUTF();
		req_email = objectInput.readUTF();
		sign_name = objectInput.readUTF();
		sign_email = objectInput.readUTF();
		doc_title = objectInput.readUTF();
		doc_type = objectInput.readUTF();
		doc_md5 = objectInput.readUTF();
		doc_status = objectInput.readUTF();
		doc_deadline = objectInput.readUTF();
		doc_description = objectInput.readUTF();
		doc_signature = objectInput.readUTF();
		req_dateCreated = objectInput.readUTF();
		req_dateModified = objectInput.readUTF();
		req_timeCreated = objectInput.readUTF();
		req_timeModified = objectInput.readUTF();

		req_accepted = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(docId);

		objectOutput.writeLong(fileId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(signId);

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

		if (sign_name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sign_name);
		}

		if (sign_email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sign_email);
		}

		if (doc_title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(doc_title);
		}

		if (doc_type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(doc_type);
		}

		if (doc_md5 == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(doc_md5);
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

		if (doc_signature == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(doc_signature);
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

		if (req_timeCreated == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(req_timeCreated);
		}

		if (req_timeModified == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(req_timeModified);
		}

		objectOutput.writeBoolean(req_accepted);
	}

	public long docId;
	public long fileId;
	public long userId;
	public long signId;
	public String req_name;
	public String req_email;
	public String sign_name;
	public String sign_email;
	public String doc_title;
	public String doc_type;
	public String doc_md5;
	public String doc_status;
	public String doc_deadline;
	public String doc_description;
	public String doc_signature;
	public String req_dateCreated;
	public String req_dateModified;
	public String req_timeCreated;
	public String req_timeModified;
	public boolean req_accepted;
}