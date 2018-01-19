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
 * Provides the local service utility for EntDoc. This utility wraps
 * {@link com._42Penguins.gosign.service.impl.EntDocLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Raziman Dom
 * @see EntDocLocalService
 * @see com._42Penguins.gosign.service.base.EntDocLocalServiceBaseImpl
 * @see com._42Penguins.gosign.service.impl.EntDocLocalServiceImpl
 * @generated
 */
@ProviderType
public class EntDocLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com._42Penguins.gosign.service.impl.EntDocLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ent doc to the database. Also notifies the appropriate model listeners.
	*
	* @param entDoc the ent doc
	* @return the ent doc that was added
	*/
	public static com._42Penguins.gosign.model.EntDoc addEntDoc(
		com._42Penguins.gosign.model.EntDoc entDoc) {
		return getService().addEntDoc(entDoc);
	}

	/**
	* Creates a new ent doc with the primary key. Does not add the ent doc to the database.
	*
	* @param docId the primary key for the new ent doc
	* @return the new ent doc
	*/
	public static com._42Penguins.gosign.model.EntDoc createEntDoc(long docId) {
		return getService().createEntDoc(docId);
	}

	/**
	* Deletes the ent doc from the database. Also notifies the appropriate model listeners.
	*
	* @param entDoc the ent doc
	* @return the ent doc that was removed
	*/
	public static com._42Penguins.gosign.model.EntDoc deleteEntDoc(
		com._42Penguins.gosign.model.EntDoc entDoc) {
		return getService().deleteEntDoc(entDoc);
	}

	/**
	* Deletes the ent doc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param docId the primary key of the ent doc
	* @return the ent doc that was removed
	* @throws PortalException if a ent doc with the primary key could not be found
	*/
	public static com._42Penguins.gosign.model.EntDoc deleteEntDoc(long docId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEntDoc(docId);
	}

	public static com._42Penguins.gosign.model.EntDoc fetchEntDoc(long docId) {
		return getService().fetchEntDoc(docId);
	}

	/**
	* Returns the ent doc with the primary key.
	*
	* @param docId the primary key of the ent doc
	* @return the ent doc
	* @throws PortalException if a ent doc with the primary key could not be found
	*/
	public static com._42Penguins.gosign.model.EntDoc getEntDoc(long docId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEntDoc(docId);
	}

	/**
	* Updates the ent doc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entDoc the ent doc
	* @return the ent doc that was updated
	*/
	public static com._42Penguins.gosign.model.EntDoc updateEntDoc(
		com._42Penguins.gosign.model.EntDoc entDoc) {
		return getService().updateEntDoc(entDoc);
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
	* Returns the number of ent docs.
	*
	* @return the number of ent docs
	*/
	public static int getEntDocsCount() {
		return getService().getEntDocsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com._42Penguins.gosign.model.impl.EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com._42Penguins.gosign.model.impl.EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com._42Penguins.gosign.model.EntDoc> findBySignEmail(
		java.lang.String sign_email, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().findBySignEmail(sign_email, start, end);
	}

	public static java.util.List<com._42Penguins.gosign.model.EntDoc> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().findByUserId(userId, start, end);
	}

	/**
	* Returns a range of all the ent docs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com._42Penguins.gosign.model.impl.EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ent docs
	* @param end the upper bound of the range of ent docs (not inclusive)
	* @return the range of ent docs
	*/
	public static java.util.List<com._42Penguins.gosign.model.EntDoc> getEntDocs(
		int start, int end) {
		return getService().getEntDocs(start, end);
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

	public static EntDocLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EntDocLocalService, EntDocLocalService> _serviceTracker =
		ServiceTrackerFactory.open(EntDocLocalService.class);
}