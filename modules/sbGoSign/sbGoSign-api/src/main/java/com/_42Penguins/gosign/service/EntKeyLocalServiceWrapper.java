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
 * Provides a wrapper for {@link EntKeyLocalService}.
 *
 * @author Raziman Dom
 * @see EntKeyLocalService
 * @generated
 */
@ProviderType
public class EntKeyLocalServiceWrapper implements EntKeyLocalService,
	ServiceWrapper<EntKeyLocalService> {
	public EntKeyLocalServiceWrapper(EntKeyLocalService entKeyLocalService) {
		_entKeyLocalService = entKeyLocalService;
	}

	/**
	* Adds the ent key to the database. Also notifies the appropriate model listeners.
	*
	* @param entKey the ent key
	* @return the ent key that was added
	*/
	@Override
	public com._42Penguins.gosign.model.EntKey addEntKey(
		com._42Penguins.gosign.model.EntKey entKey) {
		return _entKeyLocalService.addEntKey(entKey);
	}

	/**
	* Creates a new ent key with the primary key. Does not add the ent key to the database.
	*
	* @param userId the primary key for the new ent key
	* @return the new ent key
	*/
	@Override
	public com._42Penguins.gosign.model.EntKey createEntKey(long userId) {
		return _entKeyLocalService.createEntKey(userId);
	}

	/**
	* Deletes the ent key from the database. Also notifies the appropriate model listeners.
	*
	* @param entKey the ent key
	* @return the ent key that was removed
	*/
	@Override
	public com._42Penguins.gosign.model.EntKey deleteEntKey(
		com._42Penguins.gosign.model.EntKey entKey) {
		return _entKeyLocalService.deleteEntKey(entKey);
	}

	/**
	* Deletes the ent key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the ent key
	* @return the ent key that was removed
	* @throws PortalException if a ent key with the primary key could not be found
	*/
	@Override
	public com._42Penguins.gosign.model.EntKey deleteEntKey(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entKeyLocalService.deleteEntKey(userId);
	}

	@Override
	public com._42Penguins.gosign.model.EntKey fetchEntKey(long userId) {
		return _entKeyLocalService.fetchEntKey(userId);
	}

	/**
	* Returns the ent key with the primary key.
	*
	* @param userId the primary key of the ent key
	* @return the ent key
	* @throws PortalException if a ent key with the primary key could not be found
	*/
	@Override
	public com._42Penguins.gosign.model.EntKey getEntKey(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entKeyLocalService.getEntKey(userId);
	}

	/**
	* Updates the ent key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entKey the ent key
	* @return the ent key that was updated
	*/
	@Override
	public com._42Penguins.gosign.model.EntKey updateEntKey(
		com._42Penguins.gosign.model.EntKey entKey) {
		return _entKeyLocalService.updateEntKey(entKey);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _entKeyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entKeyLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _entKeyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entKeyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entKeyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of ent keies.
	*
	* @return the number of ent keies
	*/
	@Override
	public int getEntKeiesCount() {
		return _entKeyLocalService.getEntKeiesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _entKeyLocalService.getOSGiServiceIdentifier();
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
		return _entKeyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com._42Penguins.gosign.model.impl.EntKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _entKeyLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com._42Penguins.gosign.model.impl.EntKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _entKeyLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the ent keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com._42Penguins.gosign.model.impl.EntKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ent keies
	* @param end the upper bound of the range of ent keies (not inclusive)
	* @return the range of ent keies
	*/
	@Override
	public java.util.List<com._42Penguins.gosign.model.EntKey> getEntKeies(
		int start, int end) {
		return _entKeyLocalService.getEntKeies(start, end);
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
		return _entKeyLocalService.dynamicQueryCount(dynamicQuery);
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
		return _entKeyLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public EntKeyLocalService getWrappedService() {
		return _entKeyLocalService;
	}

	@Override
	public void setWrappedService(EntKeyLocalService entKeyLocalService) {
		_entKeyLocalService = entKeyLocalService;
	}

	private EntKeyLocalService _entKeyLocalService;
}