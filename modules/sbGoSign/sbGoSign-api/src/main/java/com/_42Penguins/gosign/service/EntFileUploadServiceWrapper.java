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

package com._42Penguins.gosign.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntFileUploadService}.
 *
 * @author Raziman Dom
 * @see EntFileUploadService
 * @generated
 */
@ProviderType
public class EntFileUploadServiceWrapper implements EntFileUploadService,
	ServiceWrapper<EntFileUploadService> {
	public EntFileUploadServiceWrapper(
		EntFileUploadService entFileUploadService) {
		_entFileUploadService = entFileUploadService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _entFileUploadService.getOSGiServiceIdentifier();
	}

	@Override
	public EntFileUploadService getWrappedService() {
		return _entFileUploadService;
	}

	@Override
	public void setWrappedService(EntFileUploadService entFileUploadService) {
		_entFileUploadService = entFileUploadService;
	}

	private EntFileUploadService _entFileUploadService;
}