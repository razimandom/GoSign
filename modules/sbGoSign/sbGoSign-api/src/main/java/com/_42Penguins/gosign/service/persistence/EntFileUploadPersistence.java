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

package com._42Penguins.gosign.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com._42Penguins.gosign.exception.NoSuchEntFileUploadException;
import com._42Penguins.gosign.model.EntFileUpload;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the ent file upload service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Raziman Dom
 * @see com._42Penguins.gosign.service.persistence.impl.EntFileUploadPersistenceImpl
 * @see EntFileUploadUtil
 * @generated
 */
@ProviderType
public interface EntFileUploadPersistence extends BasePersistence<EntFileUpload> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntFileUploadUtil} to access the ent file upload persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ent file upload in the entity cache if it is enabled.
	*
	* @param entFileUpload the ent file upload
	*/
	public void cacheResult(EntFileUpload entFileUpload);

	/**
	* Caches the ent file uploads in the entity cache if it is enabled.
	*
	* @param entFileUploads the ent file uploads
	*/
	public void cacheResult(java.util.List<EntFileUpload> entFileUploads);

	/**
	* Creates a new ent file upload with the primary key. Does not add the ent file upload to the database.
	*
	* @param fileId the primary key for the new ent file upload
	* @return the new ent file upload
	*/
	public EntFileUpload create(long fileId);

	/**
	* Removes the ent file upload with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileId the primary key of the ent file upload
	* @return the ent file upload that was removed
	* @throws NoSuchEntFileUploadException if a ent file upload with the primary key could not be found
	*/
	public EntFileUpload remove(long fileId)
		throws NoSuchEntFileUploadException;

	public EntFileUpload updateImpl(EntFileUpload entFileUpload);

	/**
	* Returns the ent file upload with the primary key or throws a {@link NoSuchEntFileUploadException} if it could not be found.
	*
	* @param fileId the primary key of the ent file upload
	* @return the ent file upload
	* @throws NoSuchEntFileUploadException if a ent file upload with the primary key could not be found
	*/
	public EntFileUpload findByPrimaryKey(long fileId)
		throws NoSuchEntFileUploadException;

	/**
	* Returns the ent file upload with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileId the primary key of the ent file upload
	* @return the ent file upload, or <code>null</code> if a ent file upload with the primary key could not be found
	*/
	public EntFileUpload fetchByPrimaryKey(long fileId);

	@Override
	public java.util.Map<java.io.Serializable, EntFileUpload> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ent file uploads.
	*
	* @return the ent file uploads
	*/
	public java.util.List<EntFileUpload> findAll();

	/**
	* Returns a range of all the ent file uploads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntFileUploadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ent file uploads
	* @param end the upper bound of the range of ent file uploads (not inclusive)
	* @return the range of ent file uploads
	*/
	public java.util.List<EntFileUpload> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ent file uploads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntFileUploadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ent file uploads
	* @param end the upper bound of the range of ent file uploads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ent file uploads
	*/
	public java.util.List<EntFileUpload> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntFileUpload> orderByComparator);

	/**
	* Returns an ordered range of all the ent file uploads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntFileUploadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ent file uploads
	* @param end the upper bound of the range of ent file uploads (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ent file uploads
	*/
	public java.util.List<EntFileUpload> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntFileUpload> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ent file uploads from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ent file uploads.
	*
	* @return the number of ent file uploads
	*/
	public int countAll();
}