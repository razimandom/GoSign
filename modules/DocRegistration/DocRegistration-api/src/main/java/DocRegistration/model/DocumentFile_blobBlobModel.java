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
 * The Blob model class for lazy loading the file_blob column in Document.
 *
 * @author Brian Wing Shun Chan
 * @see Document
 * @generated
 */
@ProviderType
public class DocumentFile_blobBlobModel {
	public DocumentFile_blobBlobModel() {
	}

	public DocumentFile_blobBlobModel(long docId) {
		_docId = docId;
	}

	public DocumentFile_blobBlobModel(long docId, Blob file_blobBlob) {
		_docId = docId;
		_file_blobBlob = file_blobBlob;
	}

	public long getDocId() {
		return _docId;
	}

	public void setDocId(long docId) {
		_docId = docId;
	}

	public Blob getFile_blobBlob() {
		return _file_blobBlob;
	}

	public void setFile_blobBlob(Blob file_blobBlob) {
		_file_blobBlob = file_blobBlob;
	}

	private long _docId;
	private Blob _file_blobBlob;
}