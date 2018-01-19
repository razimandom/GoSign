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

import com._42Penguins.gosign.exception.NoSuchEntKeyException;
import com._42Penguins.gosign.model.EntKey;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the ent key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Raziman Dom
 * @see com._42Penguins.gosign.service.persistence.impl.EntKeyPersistenceImpl
 * @see EntKeyUtil
 * @generated
 */
@ProviderType
public interface EntKeyPersistence extends BasePersistence<EntKey> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntKeyUtil} to access the ent key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ent key in the entity cache if it is enabled.
	*
	* @param entKey the ent key
	*/
	public void cacheResult(EntKey entKey);

	/**
	* Caches the ent keies in the entity cache if it is enabled.
	*
	* @param entKeies the ent keies
	*/
	public void cacheResult(java.util.List<EntKey> entKeies);

	/**
	* Creates a new ent key with the primary key. Does not add the ent key to the database.
	*
	* @param userId the primary key for the new ent key
	* @return the new ent key
	*/
	public EntKey create(long userId);

	/**
	* Removes the ent key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the ent key
	* @return the ent key that was removed
	* @throws NoSuchEntKeyException if a ent key with the primary key could not be found
	*/
	public EntKey remove(long userId) throws NoSuchEntKeyException;

	public EntKey updateImpl(EntKey entKey);

	/**
	* Returns the ent key with the primary key or throws a {@link NoSuchEntKeyException} if it could not be found.
	*
	* @param userId the primary key of the ent key
	* @return the ent key
	* @throws NoSuchEntKeyException if a ent key with the primary key could not be found
	*/
	public EntKey findByPrimaryKey(long userId) throws NoSuchEntKeyException;

	/**
	* Returns the ent key with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userId the primary key of the ent key
	* @return the ent key, or <code>null</code> if a ent key with the primary key could not be found
	*/
	public EntKey fetchByPrimaryKey(long userId);

	@Override
	public java.util.Map<java.io.Serializable, EntKey> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ent keies.
	*
	* @return the ent keies
	*/
	public java.util.List<EntKey> findAll();

	/**
	* Returns a range of all the ent keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ent keies
	* @param end the upper bound of the range of ent keies (not inclusive)
	* @return the range of ent keies
	*/
	public java.util.List<EntKey> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ent keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ent keies
	* @param end the upper bound of the range of ent keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ent keies
	*/
	public java.util.List<EntKey> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntKey> orderByComparator);

	/**
	* Returns an ordered range of all the ent keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ent keies
	* @param end the upper bound of the range of ent keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ent keies
	*/
	public java.util.List<EntKey> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ent keies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ent keies.
	*
	* @return the number of ent keies
	*/
	public int countAll();
}