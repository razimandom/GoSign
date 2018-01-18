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

package DocRegistration.service.persistence.impl;

import DocRegistration.exception.NoSuchGenKeyException;

import DocRegistration.model.GenKey;

import DocRegistration.model.impl.GenKeyImpl;
import DocRegistration.model.impl.GenKeyModelImpl;

import DocRegistration.service.persistence.GenKeyPersistence;

import aQute.bnd.annotation.ProviderType;

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
 * The persistence implementation for the gen key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GenKeyPersistence
 * @see DocRegistration.service.persistence.GenKeyUtil
 * @generated
 */
@ProviderType
public class GenKeyPersistenceImpl extends BasePersistenceImpl<GenKey>
	implements GenKeyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GenKeyUtil} to access the gen key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GenKeyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
			GenKeyModelImpl.FINDER_CACHE_ENABLED, GenKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
			GenKeyModelImpl.FINDER_CACHE_ENABLED, GenKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
			GenKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public GenKeyPersistenceImpl() {
		setModelClass(GenKey.class);
	}

	/**
	 * Caches the gen key in the entity cache if it is enabled.
	 *
	 * @param genKey the gen key
	 */
	@Override
	public void cacheResult(GenKey genKey) {
		entityCache.putResult(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
			GenKeyImpl.class, genKey.getPrimaryKey(), genKey);

		genKey.resetOriginalValues();
	}

	/**
	 * Caches the gen keies in the entity cache if it is enabled.
	 *
	 * @param genKeies the gen keies
	 */
	@Override
	public void cacheResult(List<GenKey> genKeies) {
		for (GenKey genKey : genKeies) {
			if (entityCache.getResult(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
						GenKeyImpl.class, genKey.getPrimaryKey()) == null) {
				cacheResult(genKey);
			}
			else {
				genKey.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all gen keies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GenKeyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the gen key.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GenKey genKey) {
		entityCache.removeResult(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
			GenKeyImpl.class, genKey.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GenKey> genKeies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GenKey genKey : genKeies) {
			entityCache.removeResult(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
				GenKeyImpl.class, genKey.getPrimaryKey());
		}
	}

	/**
	 * Creates a new gen key with the primary key. Does not add the gen key to the database.
	 *
	 * @param userId the primary key for the new gen key
	 * @return the new gen key
	 */
	@Override
	public GenKey create(long userId) {
		GenKey genKey = new GenKeyImpl();

		genKey.setNew(true);
		genKey.setPrimaryKey(userId);

		return genKey;
	}

	/**
	 * Removes the gen key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the gen key
	 * @return the gen key that was removed
	 * @throws NoSuchGenKeyException if a gen key with the primary key could not be found
	 */
	@Override
	public GenKey remove(long userId) throws NoSuchGenKeyException {
		return remove((Serializable)userId);
	}

	/**
	 * Removes the gen key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the gen key
	 * @return the gen key that was removed
	 * @throws NoSuchGenKeyException if a gen key with the primary key could not be found
	 */
	@Override
	public GenKey remove(Serializable primaryKey) throws NoSuchGenKeyException {
		Session session = null;

		try {
			session = openSession();

			GenKey genKey = (GenKey)session.get(GenKeyImpl.class, primaryKey);

			if (genKey == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGenKeyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(genKey);
		}
		catch (NoSuchGenKeyException nsee) {
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
	protected GenKey removeImpl(GenKey genKey) {
		genKey = toUnwrappedModel(genKey);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(genKey)) {
				genKey = (GenKey)session.get(GenKeyImpl.class,
						genKey.getPrimaryKeyObj());
			}

			if (genKey != null) {
				session.delete(genKey);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (genKey != null) {
			clearCache(genKey);
		}

		return genKey;
	}

	@Override
	public GenKey updateImpl(GenKey genKey) {
		genKey = toUnwrappedModel(genKey);

		boolean isNew = genKey.isNew();

		Session session = null;

		try {
			session = openSession();

			if (genKey.isNew()) {
				session.save(genKey);

				genKey.setNew(false);
			}
			else {
				genKey = (GenKey)session.merge(genKey);
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

		entityCache.putResult(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
			GenKeyImpl.class, genKey.getPrimaryKey(), genKey, false);

		genKey.resetOriginalValues();

		return genKey;
	}

	protected GenKey toUnwrappedModel(GenKey genKey) {
		if (genKey instanceof GenKeyImpl) {
			return genKey;
		}

		GenKeyImpl genKeyImpl = new GenKeyImpl();

		genKeyImpl.setNew(genKey.isNew());
		genKeyImpl.setPrimaryKey(genKey.getPrimaryKey());

		genKeyImpl.setUserId(genKey.getUserId());
		genKeyImpl.setKey_version(genKey.getKey_version());
		genKeyImpl.setKey_dateCreated(genKey.getKey_dateCreated());
		genKeyImpl.setPrivatekey_Data(genKey.getPrivatekey_Data());
		genKeyImpl.setPublickey_Data(genKey.getPublickey_Data());
		genKeyImpl.setSalt_Data(genKey.getSalt_Data());
		genKeyImpl.setVector_Data(genKey.getVector_Data());

		return genKeyImpl;
	}

	/**
	 * Returns the gen key with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the gen key
	 * @return the gen key
	 * @throws NoSuchGenKeyException if a gen key with the primary key could not be found
	 */
	@Override
	public GenKey findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGenKeyException {
		GenKey genKey = fetchByPrimaryKey(primaryKey);

		if (genKey == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGenKeyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return genKey;
	}

	/**
	 * Returns the gen key with the primary key or throws a {@link NoSuchGenKeyException} if it could not be found.
	 *
	 * @param userId the primary key of the gen key
	 * @return the gen key
	 * @throws NoSuchGenKeyException if a gen key with the primary key could not be found
	 */
	@Override
	public GenKey findByPrimaryKey(long userId) throws NoSuchGenKeyException {
		return findByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns the gen key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the gen key
	 * @return the gen key, or <code>null</code> if a gen key with the primary key could not be found
	 */
	@Override
	public GenKey fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
				GenKeyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GenKey genKey = (GenKey)serializable;

		if (genKey == null) {
			Session session = null;

			try {
				session = openSession();

				genKey = (GenKey)session.get(GenKeyImpl.class, primaryKey);

				if (genKey != null) {
					cacheResult(genKey);
				}
				else {
					entityCache.putResult(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
						GenKeyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
					GenKeyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return genKey;
	}

	/**
	 * Returns the gen key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the gen key
	 * @return the gen key, or <code>null</code> if a gen key with the primary key could not be found
	 */
	@Override
	public GenKey fetchByPrimaryKey(long userId) {
		return fetchByPrimaryKey((Serializable)userId);
	}

	@Override
	public Map<Serializable, GenKey> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GenKey> map = new HashMap<Serializable, GenKey>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GenKey genKey = fetchByPrimaryKey(primaryKey);

			if (genKey != null) {
				map.put(primaryKey, genKey);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
					GenKeyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GenKey)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_GENKEY_WHERE_PKS_IN);

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

			for (GenKey genKey : (List<GenKey>)q.list()) {
				map.put(genKey.getPrimaryKeyObj(), genKey);

				cacheResult(genKey);

				uncachedPrimaryKeys.remove(genKey.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(GenKeyModelImpl.ENTITY_CACHE_ENABLED,
					GenKeyImpl.class, primaryKey, nullModel);
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
	 * Returns all the gen keies.
	 *
	 * @return the gen keies
	 */
	@Override
	public List<GenKey> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<GenKey> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<GenKey> findAll(int start, int end,
		OrderByComparator<GenKey> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<GenKey> findAll(int start, int end,
		OrderByComparator<GenKey> orderByComparator, boolean retrieveFromCache) {
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

		List<GenKey> list = null;

		if (retrieveFromCache) {
			list = (List<GenKey>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_GENKEY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GENKEY;

				if (pagination) {
					sql = sql.concat(GenKeyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<GenKey>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GenKey>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the gen keies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GenKey genKey : findAll()) {
			remove(genKey);
		}
	}

	/**
	 * Returns the number of gen keies.
	 *
	 * @return the number of gen keies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GENKEY);

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
		return GenKeyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the gen key persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(GenKeyImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_GENKEY = "SELECT genKey FROM GenKey genKey";
	private static final String _SQL_SELECT_GENKEY_WHERE_PKS_IN = "SELECT genKey FROM GenKey genKey WHERE userId IN (";
	private static final String _SQL_COUNT_GENKEY = "SELECT COUNT(genKey) FROM GenKey genKey";
	private static final String _ORDER_BY_ENTITY_ALIAS = "genKey.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GenKey exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(GenKeyPersistenceImpl.class);
}