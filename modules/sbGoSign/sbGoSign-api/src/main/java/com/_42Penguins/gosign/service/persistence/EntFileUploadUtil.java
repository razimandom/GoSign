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

import com._42Penguins.gosign.model.EntFileUpload;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ent file upload service. This utility wraps {@link com._42Penguins.gosign.service.persistence.impl.EntFileUploadPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Raziman Dom
 * @see EntFileUploadPersistence
 * @see com._42Penguins.gosign.service.persistence.impl.EntFileUploadPersistenceImpl
 * @generated
 */
@ProviderType
public class EntFileUploadUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(EntFileUpload entFileUpload) {
		getPersistence().clearCache(entFileUpload);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EntFileUpload> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EntFileUpload> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EntFileUpload> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EntFileUpload> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EntFileUpload update(EntFileUpload entFileUpload) {
		return getPersistence().update(entFileUpload);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EntFileUpload update(EntFileUpload entFileUpload,
		ServiceContext serviceContext) {
		return getPersistence().update(entFileUpload, serviceContext);
	}

	/**
	* Caches the ent file upload in the entity cache if it is enabled.
	*
	* @param entFileUpload the ent file upload
	*/
	public static void cacheResult(EntFileUpload entFileUpload) {
		getPersistence().cacheResult(entFileUpload);
	}

	/**
	* Caches the ent file uploads in the entity cache if it is enabled.
	*
	* @param entFileUploads the ent file uploads
	*/
	public static void cacheResult(List<EntFileUpload> entFileUploads) {
		getPersistence().cacheResult(entFileUploads);
	}

	/**
	* Creates a new ent file upload with the primary key. Does not add the ent file upload to the database.
	*
	* @param fileId the primary key for the new ent file upload
	* @return the new ent file upload
	*/
	public static EntFileUpload create(long fileId) {
		return getPersistence().create(fileId);
	}

	/**
	* Removes the ent file upload with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileId the primary key of the ent file upload
	* @return the ent file upload that was removed
	* @throws NoSuchEntFileUploadException if a ent file upload with the primary key could not be found
	*/
	public static EntFileUpload remove(long fileId)
		throws com._42Penguins.gosign.exception.NoSuchEntFileUploadException {
		return getPersistence().remove(fileId);
	}

	public static EntFileUpload updateImpl(EntFileUpload entFileUpload) {
		return getPersistence().updateImpl(entFileUpload);
	}

	/**
	* Returns the ent file upload with the primary key or throws a {@link NoSuchEntFileUploadException} if it could not be found.
	*
	* @param fileId the primary key of the ent file upload
	* @return the ent file upload
	* @throws NoSuchEntFileUploadException if a ent file upload with the primary key could not be found
	*/
	public static EntFileUpload findByPrimaryKey(long fileId)
		throws com._42Penguins.gosign.exception.NoSuchEntFileUploadException {
		return getPersistence().findByPrimaryKey(fileId);
	}

	/**
	* Returns the ent file upload with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileId the primary key of the ent file upload
	* @return the ent file upload, or <code>null</code> if a ent file upload with the primary key could not be found
	*/
	public static EntFileUpload fetchByPrimaryKey(long fileId) {
		return getPersistence().fetchByPrimaryKey(fileId);
	}

	public static java.util.Map<java.io.Serializable, EntFileUpload> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ent file uploads.
	*
	* @return the ent file uploads
	*/
	public static List<EntFileUpload> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<EntFileUpload> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<EntFileUpload> findAll(int start, int end,
		OrderByComparator<EntFileUpload> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<EntFileUpload> findAll(int start, int end,
		OrderByComparator<EntFileUpload> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ent file uploads from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ent file uploads.
	*
	* @return the number of ent file uploads
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EntFileUploadPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EntFileUploadPersistence, EntFileUploadPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EntFileUploadPersistence.class);
}