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
 * Provides a wrapper for {@link EntDocLocalService}.
 *
 * @author Raziman Dom
 * @see EntDocLocalService
 * @generated
 */
@ProviderType
public class EntDocLocalServiceWrapper implements EntDocLocalService,
	ServiceWrapper<EntDocLocalService> {
	public EntDocLocalServiceWrapper(EntDocLocalService entDocLocalService) {
		_entDocLocalService = entDocLocalService;
	}

	/**
	* Adds the ent doc to the database. Also notifies the appropriate model listeners.
	*
	* @param entDoc the ent doc
	* @return the ent doc that was added
	*/
	@Override
	public com._42Penguins.gosign.model.EntDoc addEntDoc(
		com._42Penguins.gosign.model.EntDoc entDoc) {
		return _entDocLocalService.addEntDoc(entDoc);
	}

	/**
	* Creates a new ent doc with the primary key. Does not add the ent doc to the database.
	*
	* @param docId the primary key for the new ent doc
	* @return the new ent doc
	*/
	@Override
	public com._42Penguins.gosign.model.EntDoc createEntDoc(long docId) {
		return _entDocLocalService.createEntDoc(docId);
	}

	/**
	* Deletes the ent doc from the database. Also notifies the appropriate model listeners.
	*
	* @param entDoc the ent doc
	* @return the ent doc that was removed
	*/
	@Override
	public com._42Penguins.gosign.model.EntDoc deleteEntDoc(
		com._42Penguins.gosign.model.EntDoc entDoc) {
		return _entDocLocalService.deleteEntDoc(entDoc);
	}

	/**
	* Deletes the ent doc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param docId the primary key of the ent doc
	* @return the ent doc that was removed
	* @throws PortalException if a ent doc with the primary key could not be found
	*/
	@Override
	public com._42Penguins.gosign.model.EntDoc deleteEntDoc(long docId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entDocLocalService.deleteEntDoc(docId);
	}

	@Override
	public com._42Penguins.gosign.model.EntDoc fetchEntDoc(long docId) {
		return _entDocLocalService.fetchEntDoc(docId);
	}

	/**
	* Returns the ent doc with the primary key.
	*
	* @param docId the primary key of the ent doc
	* @return the ent doc
	* @throws PortalException if a ent doc with the primary key could not be found
	*/
	@Override
	public com._42Penguins.gosign.model.EntDoc getEntDoc(long docId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entDocLocalService.getEntDoc(docId);
	}

	/**
	* Updates the ent doc in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entDoc the ent doc
	* @return the ent doc that was updated
	*/
	@Override
	public com._42Penguins.gosign.model.EntDoc updateEntDoc(
		com._42Penguins.gosign.model.EntDoc entDoc) {
		return _entDocLocalService.updateEntDoc(entDoc);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _entDocLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entDocLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _entDocLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entDocLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entDocLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of ent docs.
	*
	* @return the number of ent docs
	*/
	@Override
	public int getEntDocsCount() {
		return _entDocLocalService.getEntDocsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _entDocLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _entDocLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _entDocLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _entDocLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com._42Penguins.gosign.model.EntDoc> findBySignEmail(
		java.lang.String sign_email, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entDocLocalService.findBySignEmail(sign_email, start, end);
	}

	@Override
	public java.util.List<com._42Penguins.gosign.model.EntDoc> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entDocLocalService.findByUserId(userId, start, end);
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
	@Override
	public java.util.List<com._42Penguins.gosign.model.EntDoc> getEntDocs(
		int start, int end) {
		return _entDocLocalService.getEntDocs(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _entDocLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _entDocLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public EntDocLocalService getWrappedService() {
		return _entDocLocalService;
	}

	@Override
	public void setWrappedService(EntDocLocalService entDocLocalService) {
		_entDocLocalService = entDocLocalService;
	}

	private EntDocLocalService _entDocLocalService;
}