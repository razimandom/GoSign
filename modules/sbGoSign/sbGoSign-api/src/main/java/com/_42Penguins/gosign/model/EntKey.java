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
 * The extended model interface for the EntKey service. Represents a row in the &quot;genkey_data&quot; database table, with each column mapped to a property of this class.
 *
 * @author Raziman Dom
 * @see EntKeyModel
 * @see com._42Penguins.gosign.model.impl.EntKeyImpl
 * @see com._42Penguins.gosign.model.impl.EntKeyModelImpl
 * @generated
 */
@ImplementationClassName("com._42Penguins.gosign.model.impl.EntKeyImpl")
@ProviderType
public interface EntKey extends EntKeyModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com._42Penguins.gosign.model.impl.EntKeyImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<EntKey, Long> USER_ID_ACCESSOR = new Accessor<EntKey, Long>() {
			@Override
			public Long get(EntKey entKey) {
				return entKey.getUserId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<EntKey> getTypeClass() {
				return EntKey.class;
			}
		};
}