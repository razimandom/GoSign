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

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the file_blob column in EntFileUpload.
 *
 * @author Raziman Dom
 * @see EntFileUpload
 * @generated
 */
@ProviderType
public class EntFileUploadFile_blobBlobModel {
	public EntFileUploadFile_blobBlobModel() {
	}

	public EntFileUploadFile_blobBlobModel(long fileId) {
		_fileId = fileId;
	}

	public EntFileUploadFile_blobBlobModel(long fileId, Blob file_blobBlob) {
		_fileId = fileId;
		_file_blobBlob = file_blobBlob;
	}

	public long getFileId() {
		return _fileId;
	}

	public void setFileId(long fileId) {
		_fileId = fileId;
	}

	public Blob getFile_blobBlob() {
		return _file_blobBlob;
	}

	public void setFile_blobBlob(Blob file_blobBlob) {
		_file_blobBlob = file_blobBlob;
	}

	private long _fileId;
	private Blob _file_blobBlob;
}