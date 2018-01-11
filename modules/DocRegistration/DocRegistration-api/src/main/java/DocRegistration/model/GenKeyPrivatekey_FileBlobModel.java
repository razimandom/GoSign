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
 * The Blob model class for lazy loading the privatekey_File column in GenKey.
 *
 * @author Brian Wing Shun Chan
 * @see GenKey
 * @generated
 */
@ProviderType
public class GenKeyPrivatekey_FileBlobModel {
	public GenKeyPrivatekey_FileBlobModel() {
	}

	public GenKeyPrivatekey_FileBlobModel(long genkeyId) {
		_genkeyId = genkeyId;
	}

	public GenKeyPrivatekey_FileBlobModel(long genkeyId,
		Blob privatekey_FileBlob) {
		_genkeyId = genkeyId;
		_privatekey_FileBlob = privatekey_FileBlob;
	}

	public long getGenkeyId() {
		return _genkeyId;
	}

	public void setGenkeyId(long genkeyId) {
		_genkeyId = genkeyId;
	}

	public Blob getPrivatekey_FileBlob() {
		return _privatekey_FileBlob;
	}

	public void setPrivatekey_FileBlob(Blob privatekey_FileBlob) {
		_privatekey_FileBlob = privatekey_FileBlob;
	}

	private long _genkeyId;
	private Blob _privatekey_FileBlob;
}