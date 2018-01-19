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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for EntFileUpload. This utility wraps
 * {@link com._42Penguins.gosign.service.impl.EntFileUploadLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Raziman Dom
 * @see EntFileUploadLocalService
 * @see com._42Penguins.gosign.service.base.EntFileUploadLocalServiceBaseImpl
 * @see com._42Penguins.gosign.service.impl.EntFileUploadLocalServiceImpl
 * @generated
 */
@ProviderType
public class EntFileUploadLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com._42Penguins.gosign.service.impl.EntFileUploadLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ent file upload to the database. Also notifies the appropriate model listeners.
	*
	* @param entFileUpload the ent file upload
	* @return the ent file upload that was added
	*/
	public static com._42Penguins.gosign.model.EntFileUpload addEntFileUpload(
		com._42Penguins.gosign.model.EntFileUpload entFileUpload) {
		return getService().addEntFileUpload(entFileUpload);
	}

	/**
	* Creates a new ent file upload with the primary key. Does not add the ent file upload to the database.
	*
	* @param fileId the primary key for the new ent file upload
	* @return the new ent file upload
	*/
	public static com._42Penguins.gosign.model.EntFileUpload createEntFileUpload(
		long fileId) {
		return getService().createEntFileUpload(fileId);
	}

	/**
	* Deletes the ent file upload from the database. Also notifies the appropriate model listeners.
	*
	* @param entFileUpload the ent file upload
	* @return the ent file upload that was removed
	*/
	public static com._42Penguins.gosign.model.EntFileUpload deleteEntFileUpload(
		com._42Penguins.gosign.model.EntFileUpload entFileUpload) {
		return getService().deleteEntFileUpload(entFileUpload);
	}

	/**
	* Deletes the ent file upload with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileId the primary key of the ent file upload
	* @return the ent file upload that was removed
	* @throws PortalException if a ent file upload with the primary key could not be found
	*/
	public static com._42Penguins.gosign.model.EntFileUpload deleteEntFileUpload(
		long fileId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEntFileUpload(fileId);
	}

	public static com._42Penguins.gosign.model.EntFileUpload fetchEntFileUpload(
		long fileId) {
		return getService().fetchEntFileUpload(fileId);
	}

	/**
	* Returns the ent file upload with the primary key.
	*
	* @param fileId the primary key of the ent file upload
	* @return the ent file upload
	* @throws PortalException if a ent file upload with the primary key could not be found
	*/
	public static com._42Penguins.gosign.model.EntFileUpload getEntFileUpload(
		long fileId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEntFileUpload(fileId);
	}

	/**
	* Updates the ent file upload in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entFileUpload the ent file upload
	* @return the ent file upload that was updated
	*/
	public static com._42Penguins.gosign.model.EntFileUpload updateEntFileUpload(
		com._42Penguins.gosign.model.EntFileUpload entFileUpload) {
		return getService().updateEntFileUpload(entFileUpload);
	}

	public static com._42Penguins.gosign.model.EntFileUploadFile_blobBlobModel getFile_blobBlobModel(
		java.io.Serializable primaryKey) {
		return getService().getFile_blobBlobModel(primaryKey);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of ent file uploads.
	*
	* @return the number of ent file uploads
	*/
	public static int getEntFileUploadsCount() {
		return getService().getEntFileUploadsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com._42Penguins.gosign.model.impl.EntFileUploadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com._42Penguins.gosign.model.impl.EntFileUploadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the ent file uploads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com._42Penguins.gosign.model.impl.EntFileUploadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ent file uploads
	* @param end the upper bound of the range of ent file uploads (not inclusive)
	* @return the range of ent file uploads
	*/
	public static java.util.List<com._42Penguins.gosign.model.EntFileUpload> getEntFileUploads(
		int start, int end) {
		return getService().getEntFileUploads(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static EntFileUploadLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EntFileUploadLocalService, EntFileUploadLocalService> _serviceTracker =
		ServiceTrackerFactory.open(EntFileUploadLocalService.class);
}