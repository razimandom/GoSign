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

package DocRegistration.service.persistence;

import DocRegistration.exception.NoSuchGenKeyException;

import DocRegistration.model.GenKey;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the gen key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocRegistration.service.persistence.impl.GenKeyPersistenceImpl
 * @see GenKeyUtil
 * @generated
 */
@ProviderType
public interface GenKeyPersistence extends BasePersistence<GenKey> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GenKeyUtil} to access the gen key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the gen key in the entity cache if it is enabled.
	*
	* @param genKey the gen key
	*/
	public void cacheResult(GenKey genKey);

	/**
	* Caches the gen keies in the entity cache if it is enabled.
	*
	* @param genKeies the gen keies
	*/
	public void cacheResult(java.util.List<GenKey> genKeies);

	/**
	* Creates a new gen key with the primary key. Does not add the gen key to the database.
	*
	* @param genkeyId the primary key for the new gen key
	* @return the new gen key
	*/
	public GenKey create(long genkeyId);

	/**
	* Removes the gen key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param genkeyId the primary key of the gen key
	* @return the gen key that was removed
	* @throws NoSuchGenKeyException if a gen key with the primary key could not be found
	*/
	public GenKey remove(long genkeyId) throws NoSuchGenKeyException;

	public GenKey updateImpl(GenKey genKey);

	/**
	* Returns the gen key with the primary key or throws a {@link NoSuchGenKeyException} if it could not be found.
	*
	* @param genkeyId the primary key of the gen key
	* @return the gen key
	* @throws NoSuchGenKeyException if a gen key with the primary key could not be found
	*/
	public GenKey findByPrimaryKey(long genkeyId) throws NoSuchGenKeyException;

	/**
	* Returns the gen key with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param genkeyId the primary key of the gen key
	* @return the gen key, or <code>null</code> if a gen key with the primary key could not be found
	*/
	public GenKey fetchByPrimaryKey(long genkeyId);

	@Override
	public java.util.Map<java.io.Serializable, GenKey> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the gen keies.
	*
	* @return the gen keies
	*/
	public java.util.List<GenKey> findAll();

	/**
	* Returns a range of all the gen keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GenKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gen keies
	* @param end the upper bound of the range of gen keies (not inclusive)
	* @return the range of gen keies
	*/
	public java.util.List<GenKey> findAll(int start, int end);

	/**
	* Returns an ordered range of all the gen keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GenKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gen keies
	* @param end the upper bound of the range of gen keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gen keies
	*/
	public java.util.List<GenKey> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GenKey> orderByComparator);

	/**
	* Returns an ordered range of all the gen keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GenKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gen keies
	* @param end the upper bound of the range of gen keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gen keies
	*/
	public java.util.List<GenKey> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GenKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the gen keies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of gen keies.
	*
	* @return the number of gen keies
	*/
	public int countAll();
}