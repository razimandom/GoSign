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

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the publickey_File column in GenKey.
 *
 * @author Brian Wing Shun Chan
 * @see GenKey
 * @generated
 */
@ProviderType
public class GenKeyPublickey_FileBlobModel {
	public GenKeyPublickey_FileBlobModel() {
	}

	public GenKeyPublickey_FileBlobModel(long genkeyId) {
		_genkeyId = genkeyId;
	}

	public GenKeyPublickey_FileBlobModel(long genkeyId, Blob publickey_FileBlob) {
		_genkeyId = genkeyId;
		_publickey_FileBlob = publickey_FileBlob;
	}

	public long getGenkeyId() {
		return _genkeyId;
	}

	public void setGenkeyId(long genkeyId) {
		_genkeyId = genkeyId;
	}

	public Blob getPublickey_FileBlob() {
		return _publickey_FileBlob;
	}

	public void setPublickey_FileBlob(Blob publickey_FileBlob) {
		_publickey_FileBlob = publickey_FileBlob;
	}

	private long _genkeyId;
	private Blob _publickey_FileBlob;
}