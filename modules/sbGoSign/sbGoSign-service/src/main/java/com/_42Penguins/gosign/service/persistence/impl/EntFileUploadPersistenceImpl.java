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

import com._42Penguins.gosign.exception.NoSuchEntFileUploadException;
import com._42Penguins.gosign.model.EntFileUpload;
import com._42Penguins.gosign.model.impl.EntFileUploadImpl;
import com._42Penguins.gosign.model.impl.EntFileUploadModelImpl;
import com._42Penguins.gosign.service.persistence.EntFileUploadPersistence;

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
 * The persistence implementation for the ent file upload service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Raziman Dom
 * @see EntFileUploadPersistence
 * @see com._42Penguins.gosign.service.persistence.EntFileUploadUtil
 * @generated
 */
@ProviderType
public class EntFileUploadPersistenceImpl extends BasePersistenceImpl<EntFileUpload>
	implements EntFileUploadPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EntFileUploadUtil} to access the ent file upload persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EntFileUploadImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
			EntFileUploadModelImpl.FINDER_CACHE_ENABLED,
			EntFileUploadImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
			EntFileUploadModelImpl.FINDER_CACHE_ENABLED,
			EntFileUploadImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
			EntFileUploadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public EntFileUploadPersistenceImpl() {
		setModelClass(EntFileUpload.class);
	}

	/**
	 * Caches the ent file upload in the entity cache if it is enabled.
	 *
	 * @param entFileUpload the ent file upload
	 */
	@Override
	public void cacheResult(EntFileUpload entFileUpload) {
		entityCache.putResult(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
			EntFileUploadImpl.class, entFileUpload.getPrimaryKey(),
			entFileUpload);

		entFileUpload.resetOriginalValues();
	}

	/**
	 * Caches the ent file uploads in the entity cache if it is enabled.
	 *
	 * @param entFileUploads the ent file uploads
	 */
	@Override
	public void cacheResult(List<EntFileUpload> entFileUploads) {
		for (EntFileUpload entFileUpload : entFileUploads) {
			if (entityCache.getResult(
						EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
						EntFileUploadImpl.class, entFileUpload.getPrimaryKey()) == null) {
				cacheResult(entFileUpload);
			}
			else {
				entFileUpload.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ent file uploads.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EntFileUploadImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ent file upload.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EntFileUpload entFileUpload) {
		entityCache.removeResult(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
			EntFileUploadImpl.class, entFileUpload.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<EntFileUpload> entFileUploads) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EntFileUpload entFileUpload : entFileUploads) {
			entityCache.removeResult(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
				EntFileUploadImpl.class, entFileUpload.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ent file upload with the primary key. Does not add the ent file upload to the database.
	 *
	 * @param fileId the primary key for the new ent file upload
	 * @return the new ent file upload
	 */
	@Override
	public EntFileUpload create(long fileId) {
		EntFileUpload entFileUpload = new EntFileUploadImpl();

		entFileUpload.setNew(true);
		entFileUpload.setPrimaryKey(fileId);

		return entFileUpload;
	}

	/**
	 * Removes the ent file upload with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileId the primary key of the ent file upload
	 * @return the ent file upload that was removed
	 * @throws NoSuchEntFileUploadException if a ent file upload with the primary key could not be found
	 */
	@Override
	public EntFileUpload remove(long fileId)
		throws NoSuchEntFileUploadException {
		return remove((Serializable)fileId);
	}

	/**
	 * Removes the ent file upload with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ent file upload
	 * @return the ent file upload that was removed
	 * @throws NoSuchEntFileUploadException if a ent file upload with the primary key could not be found
	 */
	@Override
	public EntFileUpload remove(Serializable primaryKey)
		throws NoSuchEntFileUploadException {
		Session session = null;

		try {
			session = openSession();

			EntFileUpload entFileUpload = (EntFileUpload)session.get(EntFileUploadImpl.class,
					primaryKey);

			if (entFileUpload == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntFileUploadException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(entFileUpload);
		}
		catch (NoSuchEntFileUploadException nsee) {
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
	protected EntFileUpload removeImpl(EntFileUpload entFileUpload) {
		entFileUpload = toUnwrappedModel(entFileUpload);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entFileUpload)) {
				entFileUpload = (EntFileUpload)session.get(EntFileUploadImpl.class,
						entFileUpload.getPrimaryKeyObj());
			}

			if (entFileUpload != null) {
				session.delete(entFileUpload);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (entFileUpload != null) {
			clearCache(entFileUpload);
		}

		return entFileUpload;
	}

	@Override
	public EntFileUpload updateImpl(EntFileUpload entFileUpload) {
		entFileUpload = toUnwrappedModel(entFileUpload);

		boolean isNew = entFileUpload.isNew();

		Session session = null;

		try {
			session = openSession();

			if (entFileUpload.isNew()) {
				session.save(entFileUpload);

				entFileUpload.setNew(false);
			}
			else {
				session.evict(entFileUpload);
				session.saveOrUpdate(entFileUpload);
			}

			session.flush();
			session.clear();
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

		entityCache.putResult(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
			EntFileUploadImpl.class, entFileUpload.getPrimaryKey(),
			entFileUpload, false);

		entFileUpload.resetOriginalValues();

		return entFileUpload;
	}

	protected EntFileUpload toUnwrappedModel(EntFileUpload entFileUpload) {
		if (entFileUpload instanceof EntFileUploadImpl) {
			return entFileUpload;
		}

		EntFileUploadImpl entFileUploadImpl = new EntFileUploadImpl();

		entFileUploadImpl.setNew(entFileUpload.isNew());
		entFileUploadImpl.setPrimaryKey(entFileUpload.getPrimaryKey());

		entFileUploadImpl.setFileId(entFileUpload.getFileId());
		entFileUploadImpl.setFile_name(entFileUpload.getFile_name());
		entFileUploadImpl.setFile_type(entFileUpload.getFile_type());
		entFileUploadImpl.setFile_blob(entFileUpload.getFile_blob());

		return entFileUploadImpl;
	}

	/**
	 * Returns the ent file upload with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ent file upload
	 * @return the ent file upload
	 * @throws NoSuchEntFileUploadException if a ent file upload with the primary key could not be found
	 */
	@Override
	public EntFileUpload findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntFileUploadException {
		EntFileUpload entFileUpload = fetchByPrimaryKey(primaryKey);

		if (entFileUpload == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntFileUploadException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return entFileUpload;
	}

	/**
	 * Returns the ent file upload with the primary key or throws a {@link NoSuchEntFileUploadException} if it could not be found.
	 *
	 * @param fileId the primary key of the ent file upload
	 * @return the ent file upload
	 * @throws NoSuchEntFileUploadException if a ent file upload with the primary key could not be found
	 */
	@Override
	public EntFileUpload findByPrimaryKey(long fileId)
		throws NoSuchEntFileUploadException {
		return findByPrimaryKey((Serializable)fileId);
	}

	/**
	 * Returns the ent file upload with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ent file upload
	 * @return the ent file upload, or <code>null</code> if a ent file upload with the primary key could not be found
	 */
	@Override
	public EntFileUpload fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
				EntFileUploadImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		EntFileUpload entFileUpload = (EntFileUpload)serializable;

		if (entFileUpload == null) {
			Session session = null;

			try {
				session = openSession();

				entFileUpload = (EntFileUpload)session.get(EntFileUploadImpl.class,
						primaryKey);

				if (entFileUpload != null) {
					cacheResult(entFileUpload);
				}
				else {
					entityCache.putResult(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
						EntFileUploadImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
					EntFileUploadImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return entFileUpload;
	}

	/**
	 * Returns the ent file upload with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileId the primary key of the ent file upload
	 * @return the ent file upload, or <code>null</code> if a ent file upload with the primary key could not be found
	 */
	@Override
	public EntFileUpload fetchByPrimaryKey(long fileId) {
		return fetchByPrimaryKey((Serializable)fileId);
	}

	@Override
	public Map<Serializable, EntFileUpload> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EntFileUpload> map = new HashMap<Serializable, EntFileUpload>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			EntFileUpload entFileUpload = fetchByPrimaryKey(primaryKey);

			if (entFileUpload != null) {
				map.put(primaryKey, entFileUpload);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
					EntFileUploadImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (EntFileUpload)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ENTFILEUPLOAD_WHERE_PKS_IN);

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

			for (EntFileUpload entFileUpload : (List<EntFileUpload>)q.list()) {
				map.put(entFileUpload.getPrimaryKeyObj(), entFileUpload);

				cacheResult(entFileUpload);

				uncachedPrimaryKeys.remove(entFileUpload.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EntFileUploadModelImpl.ENTITY_CACHE_ENABLED,
					EntFileUploadImpl.class, primaryKey, nullModel);
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
	 * Returns all the ent file uploads.
	 *
	 * @return the ent file uploads
	 */
	@Override
	public List<EntFileUpload> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ent file uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntFileUploadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent file uploads
	 * @param end the upper bound of the range of ent file uploads (not inclusive)
	 * @return the range of ent file uploads
	 */
	@Override
	public List<EntFileUpload> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ent file uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntFileUploadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent file uploads
	 * @param end the upper bound of the range of ent file uploads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ent file uploads
	 */
	@Override
	public List<EntFileUpload> findAll(int start, int end,
		OrderByComparator<EntFileUpload> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ent file uploads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntFileUploadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent file uploads
	 * @param end the upper bound of the range of ent file uploads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ent file uploads
	 */
	@Override
	public List<EntFileUpload> findAll(int start, int end,
		OrderByComparator<EntFileUpload> orderByComparator,
		boolean retrieveFromCache) {
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

		List<EntFileUpload> list = null;

		if (retrieveFromCache) {
			list = (List<EntFileUpload>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ENTFILEUPLOAD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ENTFILEUPLOAD;

				if (pagination) {
					sql = sql.concat(EntFileUploadModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EntFileUpload>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntFileUpload>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the ent file uploads from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EntFileUpload entFileUpload : findAll()) {
			remove(entFileUpload);
		}
	}

	/**
	 * Returns the number of ent file uploads.
	 *
	 * @return the number of ent file uploads
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ENTFILEUPLOAD);

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
		return EntFileUploadModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ent file upload persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EntFileUploadImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ENTFILEUPLOAD = "SELECT entFileUpload FROM EntFileUpload entFileUpload";
	private static final String _SQL_SELECT_ENTFILEUPLOAD_WHERE_PKS_IN = "SELECT entFileUpload FROM EntFileUpload entFileUpload WHERE fileId IN (";
	private static final String _SQL_COUNT_ENTFILEUPLOAD = "SELECT COUNT(entFileUpload) FROM EntFileUpload entFileUpload";
	private static final String _ORDER_BY_ENTITY_ALIAS = "entFileUpload.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EntFileUpload exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(EntFileUploadPersistenceImpl.class);
}