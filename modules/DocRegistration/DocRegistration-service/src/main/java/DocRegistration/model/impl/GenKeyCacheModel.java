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

import DocRegistration.model.GenKey;

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
 * The cache model class for representing GenKey in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see GenKey
 * @generated
 */
@ProviderType
public class GenKeyCacheModel implements CacheModel<GenKey>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GenKeyCacheModel)) {
			return false;
		}

		GenKeyCacheModel genKeyCacheModel = (GenKeyCacheModel)obj;

		if (genkeyId == genKeyCacheModel.genkeyId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, genkeyId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{genkeyId=");
		sb.append(genkeyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", publickey_Text=");
		sb.append(publickey_Text);
		sb.append(", key_dateCreated=");
		sb.append(key_dateCreated);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GenKey toEntityModel() {
		GenKeyImpl genKeyImpl = new GenKeyImpl();

		genKeyImpl.setGenkeyId(genkeyId);
		genKeyImpl.setUserId(userId);

		if (publickey_Text == null) {
			genKeyImpl.setPublickey_Text(StringPool.BLANK);
		}
		else {
			genKeyImpl.setPublickey_Text(publickey_Text);
		}

		if (key_dateCreated == null) {
			genKeyImpl.setKey_dateCreated(StringPool.BLANK);
		}
		else {
			genKeyImpl.setKey_dateCreated(key_dateCreated);
		}

		genKeyImpl.resetOriginalValues();

		return genKeyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		genkeyId = objectInput.readLong();

		userId = objectInput.readLong();
		publickey_Text = objectInput.readUTF();
		key_dateCreated = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(genkeyId);

		objectOutput.writeLong(userId);

		if (publickey_Text == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(publickey_Text);
		}

		if (key_dateCreated == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(key_dateCreated);
		}
	}

	public long genkeyId;
	public long userId;
	public String publickey_Text;
	public String key_dateCreated;
}