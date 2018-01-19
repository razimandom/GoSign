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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the EntFileUpload service. Represents a row in the &quot;fileupload_data&quot; database table, with each column mapped to a property of this class.
 *
 * @author Raziman Dom
 * @see EntFileUploadModel
 * @see com._42Penguins.gosign.model.impl.EntFileUploadImpl
 * @see com._42Penguins.gosign.model.impl.EntFileUploadModelImpl
 * @generated
 */
@ImplementationClassName("com._42Penguins.gosign.model.impl.EntFileUploadImpl")
@ProviderType
public interface EntFileUpload extends EntFileUploadModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com._42Penguins.gosign.model.impl.EntFileUploadImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<EntFileUpload, Long> FILE_ID_ACCESSOR = new Accessor<EntFileUpload, Long>() {
			@Override
			public Long get(EntFileUpload entFileUpload) {
				return entFileUpload.getFileId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<EntFileUpload> getTypeClass() {
				return EntFileUpload.class;
			}
		};
}