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

import DocRegistration.model.GenKey;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the gen key service. This utility wraps {@link DocRegistration.service.persistence.impl.GenKeyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GenKeyPersistence
 * @see DocRegistration.service.persistence.impl.GenKeyPersistenceImpl
 * @generated
 */
@ProviderType
public class GenKeyUtil {
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
	public static void clearCache(GenKey genKey) {
		getPersistence().clearCache(genKey);
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
	public static List<GenKey> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GenKey> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GenKey> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<GenKey> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GenKey update(GenKey genKey) {
		return getPersistence().update(genKey);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GenKey update(GenKey genKey, ServiceContext serviceContext) {
		return getPersistence().update(genKey, serviceContext);
	}

	/**
	* Caches the gen key in the entity cache if it is enabled.
	*
	* @param genKey the gen key
	*/
	public static void cacheResult(GenKey genKey) {
		getPersistence().cacheResult(genKey);
	}

	/**
	* Caches the gen keies in the entity cache if it is enabled.
	*
	* @param genKeies the gen keies
	*/
	public static void cacheResult(List<GenKey> genKeies) {
		getPersistence().cacheResult(genKeies);
	}

	/**
	* Creates a new gen key with the primary key. Does not add the gen key to the database.
	*
	* @param genkeyId the primary key for the new gen key
	* @return the new gen key
	*/
	public static GenKey create(long genkeyId) {
		return getPersistence().create(genkeyId);
	}

	/**
	* Removes the gen key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param genkeyId the primary key of the gen key
	* @return the gen key that was removed
	* @throws NoSuchGenKeyException if a gen key with the primary key could not be found
	*/
	public static GenKey remove(long genkeyId)
		throws DocRegistration.exception.NoSuchGenKeyException {
		return getPersistence().remove(genkeyId);
	}

	public static GenKey updateImpl(GenKey genKey) {
		return getPersistence().updateImpl(genKey);
	}

	/**
	* Returns the gen key with the primary key or throws a {@link NoSuchGenKeyException} if it could not be found.
	*
	* @param genkeyId the primary key of the gen key
	* @return the gen key
	* @throws NoSuchGenKeyException if a gen key with the primary key could not be found
	*/
	public static GenKey findByPrimaryKey(long genkeyId)
		throws DocRegistration.exception.NoSuchGenKeyException {
		return getPersistence().findByPrimaryKey(genkeyId);
	}

	/**
	* Returns the gen key with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param genkeyId the primary key of the gen key
	* @return the gen key, or <code>null</code> if a gen key with the primary key could not be found
	*/
	public static GenKey fetchByPrimaryKey(long genkeyId) {
		return getPersistence().fetchByPrimaryKey(genkeyId);
	}

	public static java.util.Map<java.io.Serializable, GenKey> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the gen keies.
	*
	* @return the gen keies
	*/
	public static List<GenKey> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<GenKey> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<GenKey> findAll(int start, int end,
		OrderByComparator<GenKey> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<GenKey> findAll(int start, int end,
		OrderByComparator<GenKey> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the gen keies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of gen keies.
	*
	* @return the number of gen keies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static GenKeyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GenKeyPersistence, GenKeyPersistence> _serviceTracker =
		ServiceTrackerFactory.open(GenKeyPersistence.class);
}