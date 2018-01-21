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

package com._42Penguins.gosign.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com._42Penguins.gosign.exception.NoSuchEntKeyException;
import com._42Penguins.gosign.model.EntKey;
import com._42Penguins.gosign.model.impl.EntKeyImpl;
import com._42Penguins.gosign.model.impl.EntKeyModelImpl;
import com._42Penguins.gosign.service.persistence.EntKeyPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ent key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Raziman Dom
 * @see EntKeyPersistence
 * @see com._42Penguins.gosign.service.persistence.EntKeyUtil
 * @generated
 */
@ProviderType
public class EntKeyPersistenceImpl extends BasePersistenceImpl<EntKey>
	implements EntKeyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EntKeyUtil} to access the ent key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EntKeyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
			EntKeyModelImpl.FINDER_CACHE_ENABLED, EntKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
			EntKeyModelImpl.FINDER_CACHE_ENABLED, EntKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
			EntKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public EntKeyPersistenceImpl() {
		setModelClass(EntKey.class);
	}

	/**
	 * Caches the ent key in the entity cache if it is enabled.
	 *
	 * @param entKey the ent key
	 */
	@Override
	public void cacheResult(EntKey entKey) {
		entityCache.putResult(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
			EntKeyImpl.class, entKey.getPrimaryKey(), entKey);

		entKey.resetOriginalValues();
	}

	/**
	 * Caches the ent keies in the entity cache if it is enabled.
	 *
	 * @param entKeies the ent keies
	 */
	@Override
	public void cacheResult(List<EntKey> entKeies) {
		for (EntKey entKey : entKeies) {
			if (entityCache.getResult(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
						EntKeyImpl.class, entKey.getPrimaryKey()) == null) {
				cacheResult(entKey);
			}
			else {
				entKey.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ent keies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EntKeyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ent key.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EntKey entKey) {
		entityCache.removeResult(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
			EntKeyImpl.class, entKey.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<EntKey> entKeies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EntKey entKey : entKeies) {
			entityCache.removeResult(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
				EntKeyImpl.class, entKey.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ent key with the primary key. Does not add the ent key to the database.
	 *
	 * @param userId the primary key for the new ent key
	 * @return the new ent key
	 */
	@Override
	public EntKey create(long userId) {
		EntKey entKey = new EntKeyImpl();

		entKey.setNew(true);
		entKey.setPrimaryKey(userId);

		return entKey;
	}

	/**
	 * Removes the ent key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the ent key
	 * @return the ent key that was removed
	 * @throws NoSuchEntKeyException if a ent key with the primary key could not be found
	 */
	@Override
	public EntKey remove(long userId) throws NoSuchEntKeyException {
		return remove((Serializable)userId);
	}

	/**
	 * Removes the ent key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ent key
	 * @return the ent key that was removed
	 * @throws NoSuchEntKeyException if a ent key with the primary key could not be found
	 */
	@Override
	public EntKey remove(Serializable primaryKey) throws NoSuchEntKeyException {
		Session session = null;

		try {
			session = openSession();

			EntKey entKey = (EntKey)session.get(EntKeyImpl.class, primaryKey);

			if (entKey == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntKeyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(entKey);
		}
		catch (NoSuchEntKeyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected EntKey removeImpl(EntKey entKey) {
		entKey = toUnwrappedModel(entKey);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entKey)) {
				entKey = (EntKey)session.get(EntKeyImpl.class,
						entKey.getPrimaryKeyObj());
			}

			if (entKey != null) {
				session.delete(entKey);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (entKey != null) {
			clearCache(entKey);
		}

		return entKey;
	}

	@Override
	public EntKey updateImpl(EntKey entKey) {
		entKey = toUnwrappedModel(entKey);

		boolean isNew = entKey.isNew();

		Session session = null;

		try {
			session = openSession();

			if (entKey.isNew()) {
				session.save(entKey);

				entKey.setNew(false);
			}
			else {
				entKey = (EntKey)session.merge(entKey);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
			EntKeyImpl.class, entKey.getPrimaryKey(), entKey, false);

		entKey.resetOriginalValues();

		return entKey;
	}

	protected EntKey toUnwrappedModel(EntKey entKey) {
		if (entKey instanceof EntKeyImpl) {
			return entKey;
		}

		EntKeyImpl entKeyImpl = new EntKeyImpl();

		entKeyImpl.setNew(entKey.isNew());
		entKeyImpl.setPrimaryKey(entKey.getPrimaryKey());

		entKeyImpl.setUserId(entKey.getUserId());
		entKeyImpl.setKey_status(entKey.getKey_status());
		entKeyImpl.setKey_dateCreated(entKey.getKey_dateCreated());
		entKeyImpl.setPrivatekey_Data(entKey.getPrivatekey_Data());
		entKeyImpl.setPublickey_Data(entKey.getPublickey_Data());
		entKeyImpl.setSalt_Data(entKey.getSalt_Data());
		entKeyImpl.setVector_Data(entKey.getVector_Data());
		entKeyImpl.setSign_name(entKey.getSign_name());

		return entKeyImpl;
	}

	/**
	 * Returns the ent key with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ent key
	 * @return the ent key
	 * @throws NoSuchEntKeyException if a ent key with the primary key could not be found
	 */
	@Override
	public EntKey findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntKeyException {
		EntKey entKey = fetchByPrimaryKey(primaryKey);

		if (entKey == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntKeyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return entKey;
	}

	/**
	 * Returns the ent key with the primary key or throws a {@link NoSuchEntKeyException} if it could not be found.
	 *
	 * @param userId the primary key of the ent key
	 * @return the ent key
	 * @throws NoSuchEntKeyException if a ent key with the primary key could not be found
	 */
	@Override
	public EntKey findByPrimaryKey(long userId) throws NoSuchEntKeyException {
		return findByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns the ent key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ent key
	 * @return the ent key, or <code>null</code> if a ent key with the primary key could not be found
	 */
	@Override
	public EntKey fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
				EntKeyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		EntKey entKey = (EntKey)serializable;

		if (entKey == null) {
			Session session = null;

			try {
				session = openSession();

				entKey = (EntKey)session.get(EntKeyImpl.class, primaryKey);

				if (entKey != null) {
					cacheResult(entKey);
				}
				else {
					entityCache.putResult(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
						EntKeyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
					EntKeyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return entKey;
	}

	/**
	 * Returns the ent key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the ent key
	 * @return the ent key, or <code>null</code> if a ent key with the primary key could not be found
	 */
	@Override
	public EntKey fetchByPrimaryKey(long userId) {
		return fetchByPrimaryKey((Serializable)userId);
	}

	@Override
	public Map<Serializable, EntKey> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EntKey> map = new HashMap<Serializable, EntKey>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			EntKey entKey = fetchByPrimaryKey(primaryKey);

			if (entKey != null) {
				map.put(primaryKey, entKey);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
					EntKeyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (EntKey)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ENTKEY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (EntKey entKey : (List<EntKey>)q.list()) {
				map.put(entKey.getPrimaryKeyObj(), entKey);

				cacheResult(entKey);

				uncachedPrimaryKeys.remove(entKey.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EntKeyModelImpl.ENTITY_CACHE_ENABLED,
					EntKeyImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the ent keies.
	 *
	 * @return the ent keies
	 */
	@Override
	public List<EntKey> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<EntKey> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<EntKey> findAll(int start, int end,
		OrderByComparator<EntKey> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<EntKey> findAll(int start, int end,
		OrderByComparator<EntKey> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<EntKey> list = null;

		if (retrieveFromCache) {
			list = (List<EntKey>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ENTKEY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ENTKEY;

				if (pagination) {
					sql = sql.concat(EntKeyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EntKey>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntKey>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the ent keies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EntKey entKey : findAll()) {
			remove(entKey);
		}
	}

	/**
	 * Returns the number of ent keies.
	 *
	 * @return the number of ent keies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ENTKEY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EntKeyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ent key persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EntKeyImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ENTKEY = "SELECT entKey FROM EntKey entKey";
	private static final String _SQL_SELECT_ENTKEY_WHERE_PKS_IN = "SELECT entKey FROM EntKey entKey WHERE userId IN (";
	private static final String _SQL_COUNT_ENTKEY = "SELECT COUNT(entKey) FROM EntKey entKey";
	private static final String _ORDER_BY_ENTITY_ALIAS = "entKey.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EntKey exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(EntKeyPersistenceImpl.class);
}