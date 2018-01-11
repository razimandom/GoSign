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

package DocRegistration.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GenKeyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see GenKeyLocalService
 * @generated
 */
@ProviderType
public class GenKeyLocalServiceWrapper implements GenKeyLocalService,
	ServiceWrapper<GenKeyLocalService> {
	public GenKeyLocalServiceWrapper(GenKeyLocalService genKeyLocalService) {
		_genKeyLocalService = genKeyLocalService;
	}

	/**
	* Adds the gen key to the database. Also notifies the appropriate model listeners.
	*
	* @param genKey the gen key
	* @return the gen key that was added
	*/
	@Override
	public DocRegistration.model.GenKey addGenKey(
		DocRegistration.model.GenKey genKey) {
		return _genKeyLocalService.addGenKey(genKey);
	}

	/**
	* Creates a new gen key with the primary key. Does not add the gen key to the database.
	*
	* @param genkeyId the primary key for the new gen key
	* @return the new gen key
	*/
	@Override
	public DocRegistration.model.GenKey createGenKey(long genkeyId) {
		return _genKeyLocalService.createGenKey(genkeyId);
	}

	/**
	* Deletes the gen key from the database. Also notifies the appropriate model listeners.
	*
	* @param genKey the gen key
	* @return the gen key that was removed
	*/
	@Override
	public DocRegistration.model.GenKey deleteGenKey(
		DocRegistration.model.GenKey genKey) {
		return _genKeyLocalService.deleteGenKey(genKey);
	}

	/**
	* Deletes the gen key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param genkeyId the primary key of the gen key
	* @return the gen key that was removed
	* @throws PortalException if a gen key with the primary key could not be found
	*/
	@Override
	public DocRegistration.model.GenKey deleteGenKey(long genkeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _genKeyLocalService.deleteGenKey(genkeyId);
	}

	@Override
	public DocRegistration.model.GenKey fetchGenKey(long genkeyId) {
		return _genKeyLocalService.fetchGenKey(genkeyId);
	}

	/**
	* Returns the gen key with the primary key.
	*
	* @param genkeyId the primary key of the gen key
	* @return the gen key
	* @throws PortalException if a gen key with the primary key could not be found
	*/
	@Override
	public DocRegistration.model.GenKey getGenKey(long genkeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _genKeyLocalService.getGenKey(genkeyId);
	}

	/**
	* Updates the gen key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param genKey the gen key
	* @return the gen key that was updated
	*/
	@Override
	public DocRegistration.model.GenKey updateGenKey(
		DocRegistration.model.GenKey genKey) {
		return _genKeyLocalService.updateGenKey(genKey);
	}

	@Override
	public DocRegistration.model.GenKeyPrivatekey_FileBlobModel getPrivatekey_FileBlobModel(
		java.io.Serializable primaryKey) {
		return _genKeyLocalService.getPrivatekey_FileBlobModel(primaryKey);
	}

	@Override
	public DocRegistration.model.GenKeyPublickey_FileBlobModel getPublickey_FileBlobModel(
		java.io.Serializable primaryKey) {
		return _genKeyLocalService.getPublickey_FileBlobModel(primaryKey);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _genKeyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _genKeyLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _genKeyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _genKeyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _genKeyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of gen keies.
	*
	* @return the number of gen keies
	*/
	@Override
	public int getGenKeiesCount() {
		return _genKeyLocalService.getGenKeiesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _genKeyLocalService.getOSGiServiceIdentifier();
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
		return _genKeyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocRegistration.model.impl.GenKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _genKeyLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocRegistration.model.impl.GenKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _genKeyLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the gen keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocRegistration.model.impl.GenKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gen keies
	* @param end the upper bound of the range of gen keies (not inclusive)
	* @return the range of gen keies
	*/
	@Override
	public java.util.List<DocRegistration.model.GenKey> getGenKeies(int start,
		int end) {
		return _genKeyLocalService.getGenKeies(start, end);
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
		return _genKeyLocalService.dynamicQueryCount(dynamicQuery);
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
		return _genKeyLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public GenKeyLocalService getWrappedService() {
		return _genKeyLocalService;
	}

	@Override
	public void setWrappedService(GenKeyLocalService genKeyLocalService) {
		_genKeyLocalService = genKeyLocalService;
	}

	private GenKeyLocalService _genKeyLocalService;
}