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

import com._42Penguins.gosign.model.EntKey;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ent key service. This utility wraps {@link com._42Penguins.gosign.service.persistence.impl.EntKeyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Raziman Dom
 * @see EntKeyPersistence
 * @see com._42Penguins.gosign.service.persistence.impl.EntKeyPersistenceImpl
 * @generated
 */
@ProviderType
public class EntKeyUtil {
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
	public static void clearCache(EntKey entKey) {
		getPersistence().clearCache(entKey);
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
	public static List<EntKey> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EntKey> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EntKey> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<EntKey> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EntKey update(EntKey entKey) {
		return getPersistence().update(entKey);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EntKey update(EntKey entKey, ServiceContext serviceContext) {
		return getPersistence().update(entKey, serviceContext);
	}

	/**
	* Caches the ent key in the entity cache if it is enabled.
	*
	* @param entKey the ent key
	*/
	public static void cacheResult(EntKey entKey) {
		getPersistence().cacheResult(entKey);
	}

	/**
	* Caches the ent keies in the entity cache if it is enabled.
	*
	* @param entKeies the ent keies
	*/
	public static void cacheResult(List<EntKey> entKeies) {
		getPersistence().cacheResult(entKeies);
	}

	/**
	* Creates a new ent key with the primary key. Does not add the ent key to the database.
	*
	* @param userId the primary key for the new ent key
	* @return the new ent key
	*/
	public static EntKey create(long userId) {
		return getPersistence().create(userId);
	}

	/**
	* Removes the ent key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the ent key
	* @return the ent key that was removed
	* @throws NoSuchEntKeyException if a ent key with the primary key could not be found
	*/
	public static EntKey remove(long userId)
		throws com._42Penguins.gosign.exception.NoSuchEntKeyException {
		return getPersistence().remove(userId);
	}

	public static EntKey updateImpl(EntKey entKey) {
		return getPersistence().updateImpl(entKey);
	}

	/**
	* Returns the ent key with the primary key or throws a {@link NoSuchEntKeyException} if it could not be found.
	*
	* @param userId the primary key of the ent key
	* @return the ent key
	* @throws NoSuchEntKeyException if a ent key with the primary key could not be found
	*/
	public static EntKey findByPrimaryKey(long userId)
		throws com._42Penguins.gosign.exception.NoSuchEntKeyException {
		return getPersistence().findByPrimaryKey(userId);
	}

	/**
	* Returns the ent key with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userId the primary key of the ent key
	* @return the ent key, or <code>null</code> if a ent key with the primary key could not be found
	*/
	public static EntKey fetchByPrimaryKey(long userId) {
		return getPersistence().fetchByPrimaryKey(userId);
	}

	public static java.util.Map<java.io.Serializable, EntKey> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ent keies.
	*
	* @return the ent keies
	*/
	public static List<EntKey> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<EntKey> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<EntKey> findAll(int start, int end,
		OrderByComparator<EntKey> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<EntKey> findAll(int start, int end,
		OrderByComparator<EntKey> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ent keies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ent keies.
	*
	* @return the number of ent keies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EntKeyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EntKeyPersistence, EntKeyPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EntKeyPersistence.class);
}