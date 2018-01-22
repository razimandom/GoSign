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

import com._42Penguins.gosign.exception.NoSuchEntDocException;
import com._42Penguins.gosign.model.EntDoc;
import com._42Penguins.gosign.model.impl.EntDocImpl;
import com._42Penguins.gosign.model.impl.EntDocModelImpl;
import com._42Penguins.gosign.service.persistence.EntDocPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the ent doc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Raziman Dom
 * @see EntDocPersistence
 * @see com._42Penguins.gosign.service.persistence.EntDocUtil
 * @generated
 */
@ProviderType
public class EntDocPersistenceImpl extends BasePersistenceImpl<EntDoc>
	implements EntDocPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EntDocUtil} to access the ent doc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EntDocImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocModelImpl.FINDER_CACHE_ENABLED, EntDocImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocModelImpl.FINDER_CACHE_ENABLED, EntDocImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FINDUSERID =
		new FinderPath(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocModelImpl.FINDER_CACHE_ENABLED, EntDocImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFindUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDUSERID =
		new FinderPath(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocModelImpl.FINDER_CACHE_ENABLED, EntDocImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFindUserId",
			new String[] { Long.class.getName() },
			EntDocModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FINDUSERID = new FinderPath(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFindUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the ent docs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching ent docs
	 */
	@Override
	public List<EntDoc> findByFindUserId(long userId) {
		return findByFindUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the ent docs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of ent docs
	 * @param end the upper bound of the range of ent docs (not inclusive)
	 * @return the range of matching ent docs
	 */
	@Override
	public List<EntDoc> findByFindUserId(long userId, int start, int end) {
		return findByFindUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ent docs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of ent docs
	 * @param end the upper bound of the range of ent docs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ent docs
	 */
	@Override
	public List<EntDoc> findByFindUserId(long userId, int start, int end,
		OrderByComparator<EntDoc> orderByComparator) {
		return findByFindUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ent docs where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of ent docs
	 * @param end the upper bound of the range of ent docs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ent docs
	 */
	@Override
	public List<EntDoc> findByFindUserId(long userId, int start, int end,
		OrderByComparator<EntDoc> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDUSERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FINDUSERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<EntDoc> list = null;

		if (retrieveFromCache) {
			list = (List<EntDoc>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (EntDoc entDoc : list) {
					if ((userId != entDoc.getUserId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ENTDOC_WHERE);

			query.append(_FINDER_COLUMN_FINDUSERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntDocModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<EntDoc>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntDoc>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first ent doc in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ent doc
	 * @throws NoSuchEntDocException if a matching ent doc could not be found
	 */
	@Override
	public EntDoc findByFindUserId_First(long userId,
		OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException {
		EntDoc entDoc = fetchByFindUserId_First(userId, orderByComparator);

		if (entDoc != null) {
			return entDoc;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntDocException(msg.toString());
	}

	/**
	 * Returns the first ent doc in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ent doc, or <code>null</code> if a matching ent doc could not be found
	 */
	@Override
	public EntDoc fetchByFindUserId_First(long userId,
		OrderByComparator<EntDoc> orderByComparator) {
		List<EntDoc> list = findByFindUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ent doc in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ent doc
	 * @throws NoSuchEntDocException if a matching ent doc could not be found
	 */
	@Override
	public EntDoc findByFindUserId_Last(long userId,
		OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException {
		EntDoc entDoc = fetchByFindUserId_Last(userId, orderByComparator);

		if (entDoc != null) {
			return entDoc;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntDocException(msg.toString());
	}

	/**
	 * Returns the last ent doc in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ent doc, or <code>null</code> if a matching ent doc could not be found
	 */
	@Override
	public EntDoc fetchByFindUserId_Last(long userId,
		OrderByComparator<EntDoc> orderByComparator) {
		int count = countByFindUserId(userId);

		if (count == 0) {
			return null;
		}

		List<EntDoc> list = findByFindUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ent docs before and after the current ent doc in the ordered set where userId = &#63;.
	 *
	 * @param docId the primary key of the current ent doc
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ent doc
	 * @throws NoSuchEntDocException if a ent doc with the primary key could not be found
	 */
	@Override
	public EntDoc[] findByFindUserId_PrevAndNext(long docId, long userId,
		OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException {
		EntDoc entDoc = findByPrimaryKey(docId);

		Session session = null;

		try {
			session = openSession();

			EntDoc[] array = new EntDocImpl[3];

			array[0] = getByFindUserId_PrevAndNext(session, entDoc, userId,
					orderByComparator, true);

			array[1] = entDoc;

			array[2] = getByFindUserId_PrevAndNext(session, entDoc, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EntDoc getByFindUserId_PrevAndNext(Session session,
		EntDoc entDoc, long userId,
		OrderByComparator<EntDoc> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTDOC_WHERE);

		query.append(_FINDER_COLUMN_FINDUSERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntDocModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entDoc);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EntDoc> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ent docs where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByFindUserId(long userId) {
		for (EntDoc entDoc : findByFindUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entDoc);
		}
	}

	/**
	 * Returns the number of ent docs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching ent docs
	 */
	@Override
	public int countByFindUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FINDUSERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTDOC_WHERE);

			query.append(_FINDER_COLUMN_FINDUSERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FINDUSERID_USERID_2 = "entDoc.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FINDSIGNEMAIL =
		new FinderPath(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocModelImpl.FINDER_CACHE_ENABLED, EntDocImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFindSignEmail",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDSIGNEMAIL =
		new FinderPath(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocModelImpl.FINDER_CACHE_ENABLED, EntDocImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFindSignEmail",
			new String[] { String.class.getName() },
			EntDocModelImpl.SIGN_EMAIL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FINDSIGNEMAIL = new FinderPath(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFindSignEmail",
			new String[] { String.class.getName() });

	/**
	 * Returns all the ent docs where sign_email = &#63;.
	 *
	 * @param sign_email the sign_email
	 * @return the matching ent docs
	 */
	@Override
	public List<EntDoc> findByFindSignEmail(String sign_email) {
		return findByFindSignEmail(sign_email, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ent docs where sign_email = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sign_email the sign_email
	 * @param start the lower bound of the range of ent docs
	 * @param end the upper bound of the range of ent docs (not inclusive)
	 * @return the range of matching ent docs
	 */
	@Override
	public List<EntDoc> findByFindSignEmail(String sign_email, int start,
		int end) {
		return findByFindSignEmail(sign_email, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ent docs where sign_email = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sign_email the sign_email
	 * @param start the lower bound of the range of ent docs
	 * @param end the upper bound of the range of ent docs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ent docs
	 */
	@Override
	public List<EntDoc> findByFindSignEmail(String sign_email, int start,
		int end, OrderByComparator<EntDoc> orderByComparator) {
		return findByFindSignEmail(sign_email, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the ent docs where sign_email = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param sign_email the sign_email
	 * @param start the lower bound of the range of ent docs
	 * @param end the upper bound of the range of ent docs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ent docs
	 */
	@Override
	public List<EntDoc> findByFindSignEmail(String sign_email, int start,
		int end, OrderByComparator<EntDoc> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDSIGNEMAIL;
			finderArgs = new Object[] { sign_email };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FINDSIGNEMAIL;
			finderArgs = new Object[] { sign_email, start, end, orderByComparator };
		}

		List<EntDoc> list = null;

		if (retrieveFromCache) {
			list = (List<EntDoc>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (EntDoc entDoc : list) {
					if (!Objects.equals(sign_email, entDoc.getSign_email())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ENTDOC_WHERE);

			boolean bindSign_email = false;

			if (sign_email == null) {
				query.append(_FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_1);
			}
			else if (sign_email.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_3);
			}
			else {
				bindSign_email = true;

				query.append(_FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntDocModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSign_email) {
					qPos.add(sign_email);
				}

				if (!pagination) {
					list = (List<EntDoc>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntDoc>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first ent doc in the ordered set where sign_email = &#63;.
	 *
	 * @param sign_email the sign_email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ent doc
	 * @throws NoSuchEntDocException if a matching ent doc could not be found
	 */
	@Override
	public EntDoc findByFindSignEmail_First(String sign_email,
		OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException {
		EntDoc entDoc = fetchByFindSignEmail_First(sign_email, orderByComparator);

		if (entDoc != null) {
			return entDoc;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sign_email=");
		msg.append(sign_email);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntDocException(msg.toString());
	}

	/**
	 * Returns the first ent doc in the ordered set where sign_email = &#63;.
	 *
	 * @param sign_email the sign_email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ent doc, or <code>null</code> if a matching ent doc could not be found
	 */
	@Override
	public EntDoc fetchByFindSignEmail_First(String sign_email,
		OrderByComparator<EntDoc> orderByComparator) {
		List<EntDoc> list = findByFindSignEmail(sign_email, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ent doc in the ordered set where sign_email = &#63;.
	 *
	 * @param sign_email the sign_email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ent doc
	 * @throws NoSuchEntDocException if a matching ent doc could not be found
	 */
	@Override
	public EntDoc findByFindSignEmail_Last(String sign_email,
		OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException {
		EntDoc entDoc = fetchByFindSignEmail_Last(sign_email, orderByComparator);

		if (entDoc != null) {
			return entDoc;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("sign_email=");
		msg.append(sign_email);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntDocException(msg.toString());
	}

	/**
	 * Returns the last ent doc in the ordered set where sign_email = &#63;.
	 *
	 * @param sign_email the sign_email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ent doc, or <code>null</code> if a matching ent doc could not be found
	 */
	@Override
	public EntDoc fetchByFindSignEmail_Last(String sign_email,
		OrderByComparator<EntDoc> orderByComparator) {
		int count = countByFindSignEmail(sign_email);

		if (count == 0) {
			return null;
		}

		List<EntDoc> list = findByFindSignEmail(sign_email, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ent docs before and after the current ent doc in the ordered set where sign_email = &#63;.
	 *
	 * @param docId the primary key of the current ent doc
	 * @param sign_email the sign_email
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ent doc
	 * @throws NoSuchEntDocException if a ent doc with the primary key could not be found
	 */
	@Override
	public EntDoc[] findByFindSignEmail_PrevAndNext(long docId,
		String sign_email, OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException {
		EntDoc entDoc = findByPrimaryKey(docId);

		Session session = null;

		try {
			session = openSession();

			EntDoc[] array = new EntDocImpl[3];

			array[0] = getByFindSignEmail_PrevAndNext(session, entDoc,
					sign_email, orderByComparator, true);

			array[1] = entDoc;

			array[2] = getByFindSignEmail_PrevAndNext(session, entDoc,
					sign_email, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EntDoc getByFindSignEmail_PrevAndNext(Session session,
		EntDoc entDoc, String sign_email,
		OrderByComparator<EntDoc> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTDOC_WHERE);

		boolean bindSign_email = false;

		if (sign_email == null) {
			query.append(_FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_1);
		}
		else if (sign_email.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_3);
		}
		else {
			bindSign_email = true;

			query.append(_FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntDocModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSign_email) {
			qPos.add(sign_email);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entDoc);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EntDoc> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ent docs where sign_email = &#63; from the database.
	 *
	 * @param sign_email the sign_email
	 */
	@Override
	public void removeByFindSignEmail(String sign_email) {
		for (EntDoc entDoc : findByFindSignEmail(sign_email, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entDoc);
		}
	}

	/**
	 * Returns the number of ent docs where sign_email = &#63;.
	 *
	 * @param sign_email the sign_email
	 * @return the number of matching ent docs
	 */
	@Override
	public int countByFindSignEmail(String sign_email) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FINDSIGNEMAIL;

		Object[] finderArgs = new Object[] { sign_email };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTDOC_WHERE);

			boolean bindSign_email = false;

			if (sign_email == null) {
				query.append(_FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_1);
			}
			else if (sign_email.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_3);
			}
			else {
				bindSign_email = true;

				query.append(_FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSign_email) {
					qPos.add(sign_email);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_1 = "entDoc.sign_email IS NULL";
	private static final String _FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_2 = "entDoc.sign_email = ?";
	private static final String _FINDER_COLUMN_FINDSIGNEMAIL_SIGN_EMAIL_3 = "(entDoc.sign_email IS NULL OR entDoc.sign_email = '')";

	public EntDocPersistenceImpl() {
		setModelClass(EntDoc.class);
	}

	/**
	 * Caches the ent doc in the entity cache if it is enabled.
	 *
	 * @param entDoc the ent doc
	 */
	@Override
	public void cacheResult(EntDoc entDoc) {
		entityCache.putResult(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocImpl.class, entDoc.getPrimaryKey(), entDoc);

		entDoc.resetOriginalValues();
	}

	/**
	 * Caches the ent docs in the entity cache if it is enabled.
	 *
	 * @param entDocs the ent docs
	 */
	@Override
	public void cacheResult(List<EntDoc> entDocs) {
		for (EntDoc entDoc : entDocs) {
			if (entityCache.getResult(EntDocModelImpl.ENTITY_CACHE_ENABLED,
						EntDocImpl.class, entDoc.getPrimaryKey()) == null) {
				cacheResult(entDoc);
			}
			else {
				entDoc.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ent docs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EntDocImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ent doc.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EntDoc entDoc) {
		entityCache.removeResult(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocImpl.class, entDoc.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<EntDoc> entDocs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EntDoc entDoc : entDocs) {
			entityCache.removeResult(EntDocModelImpl.ENTITY_CACHE_ENABLED,
				EntDocImpl.class, entDoc.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ent doc with the primary key. Does not add the ent doc to the database.
	 *
	 * @param docId the primary key for the new ent doc
	 * @return the new ent doc
	 */
	@Override
	public EntDoc create(long docId) {
		EntDoc entDoc = new EntDocImpl();

		entDoc.setNew(true);
		entDoc.setPrimaryKey(docId);

		return entDoc;
	}

	/**
	 * Removes the ent doc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docId the primary key of the ent doc
	 * @return the ent doc that was removed
	 * @throws NoSuchEntDocException if a ent doc with the primary key could not be found
	 */
	@Override
	public EntDoc remove(long docId) throws NoSuchEntDocException {
		return remove((Serializable)docId);
	}

	/**
	 * Removes the ent doc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ent doc
	 * @return the ent doc that was removed
	 * @throws NoSuchEntDocException if a ent doc with the primary key could not be found
	 */
	@Override
	public EntDoc remove(Serializable primaryKey) throws NoSuchEntDocException {
		Session session = null;

		try {
			session = openSession();

			EntDoc entDoc = (EntDoc)session.get(EntDocImpl.class, primaryKey);

			if (entDoc == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntDocException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(entDoc);
		}
		catch (NoSuchEntDocException nsee) {
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
	protected EntDoc removeImpl(EntDoc entDoc) {
		entDoc = toUnwrappedModel(entDoc);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entDoc)) {
				entDoc = (EntDoc)session.get(EntDocImpl.class,
						entDoc.getPrimaryKeyObj());
			}

			if (entDoc != null) {
				session.delete(entDoc);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (entDoc != null) {
			clearCache(entDoc);
		}

		return entDoc;
	}

	@Override
	public EntDoc updateImpl(EntDoc entDoc) {
		entDoc = toUnwrappedModel(entDoc);

		boolean isNew = entDoc.isNew();

		EntDocModelImpl entDocModelImpl = (EntDocModelImpl)entDoc;

		Session session = null;

		try {
			session = openSession();

			if (entDoc.isNew()) {
				session.save(entDoc);

				entDoc.setNew(false);
			}
			else {
				entDoc = (EntDoc)session.merge(entDoc);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!EntDocModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { entDocModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FINDUSERID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDUSERID,
				args);

			args = new Object[] { entDocModelImpl.getSign_email() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FINDSIGNEMAIL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDSIGNEMAIL,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((entDocModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { entDocModelImpl.getOriginalUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FINDUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDUSERID,
					args);

				args = new Object[] { entDocModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FINDUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDUSERID,
					args);
			}

			if ((entDocModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDSIGNEMAIL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entDocModelImpl.getOriginalSign_email()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FINDSIGNEMAIL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDSIGNEMAIL,
					args);

				args = new Object[] { entDocModelImpl.getSign_email() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FINDSIGNEMAIL,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDSIGNEMAIL,
					args);
			}
		}

		entityCache.putResult(EntDocModelImpl.ENTITY_CACHE_ENABLED,
			EntDocImpl.class, entDoc.getPrimaryKey(), entDoc, false);

		entDoc.resetOriginalValues();

		return entDoc;
	}

	protected EntDoc toUnwrappedModel(EntDoc entDoc) {
		if (entDoc instanceof EntDocImpl) {
			return entDoc;
		}

		EntDocImpl entDocImpl = new EntDocImpl();

		entDocImpl.setNew(entDoc.isNew());
		entDocImpl.setPrimaryKey(entDoc.getPrimaryKey());

		entDocImpl.setDocId(entDoc.getDocId());
		entDocImpl.setFileId(entDoc.getFileId());
		entDocImpl.setUserId(entDoc.getUserId());
		entDocImpl.setSignId(entDoc.getSignId());
		entDocImpl.setReq_name(entDoc.getReq_name());
		entDocImpl.setReq_email(entDoc.getReq_email());
		entDocImpl.setSign_name(entDoc.getSign_name());
		entDocImpl.setSign_email(entDoc.getSign_email());
		entDocImpl.setDoc_title(entDoc.getDoc_title());
		entDocImpl.setDoc_type(entDoc.getDoc_type());
		entDocImpl.setDoc_md5(entDoc.getDoc_md5());
		entDocImpl.setDoc_status(entDoc.getDoc_status());
		entDocImpl.setDoc_deadline(entDoc.getDoc_deadline());
		entDocImpl.setDoc_description(entDoc.getDoc_description());
		entDocImpl.setDoc_signature(entDoc.getDoc_signature());
		entDocImpl.setReq_dateCreated(entDoc.getReq_dateCreated());
		entDocImpl.setReq_dateModified(entDoc.getReq_dateModified());
		entDocImpl.setReq_timeCreated(entDoc.getReq_timeCreated());
		entDocImpl.setReq_timeModified(entDoc.getReq_timeModified());

		return entDocImpl;
	}

	/**
	 * Returns the ent doc with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ent doc
	 * @return the ent doc
	 * @throws NoSuchEntDocException if a ent doc with the primary key could not be found
	 */
	@Override
	public EntDoc findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntDocException {
		EntDoc entDoc = fetchByPrimaryKey(primaryKey);

		if (entDoc == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntDocException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return entDoc;
	}

	/**
	 * Returns the ent doc with the primary key or throws a {@link NoSuchEntDocException} if it could not be found.
	 *
	 * @param docId the primary key of the ent doc
	 * @return the ent doc
	 * @throws NoSuchEntDocException if a ent doc with the primary key could not be found
	 */
	@Override
	public EntDoc findByPrimaryKey(long docId) throws NoSuchEntDocException {
		return findByPrimaryKey((Serializable)docId);
	}

	/**
	 * Returns the ent doc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ent doc
	 * @return the ent doc, or <code>null</code> if a ent doc with the primary key could not be found
	 */
	@Override
	public EntDoc fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EntDocModelImpl.ENTITY_CACHE_ENABLED,
				EntDocImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		EntDoc entDoc = (EntDoc)serializable;

		if (entDoc == null) {
			Session session = null;

			try {
				session = openSession();

				entDoc = (EntDoc)session.get(EntDocImpl.class, primaryKey);

				if (entDoc != null) {
					cacheResult(entDoc);
				}
				else {
					entityCache.putResult(EntDocModelImpl.ENTITY_CACHE_ENABLED,
						EntDocImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EntDocModelImpl.ENTITY_CACHE_ENABLED,
					EntDocImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return entDoc;
	}

	/**
	 * Returns the ent doc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param docId the primary key of the ent doc
	 * @return the ent doc, or <code>null</code> if a ent doc with the primary key could not be found
	 */
	@Override
	public EntDoc fetchByPrimaryKey(long docId) {
		return fetchByPrimaryKey((Serializable)docId);
	}

	@Override
	public Map<Serializable, EntDoc> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EntDoc> map = new HashMap<Serializable, EntDoc>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			EntDoc entDoc = fetchByPrimaryKey(primaryKey);

			if (entDoc != null) {
				map.put(primaryKey, entDoc);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EntDocModelImpl.ENTITY_CACHE_ENABLED,
					EntDocImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (EntDoc)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ENTDOC_WHERE_PKS_IN);

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

			for (EntDoc entDoc : (List<EntDoc>)q.list()) {
				map.put(entDoc.getPrimaryKeyObj(), entDoc);

				cacheResult(entDoc);

				uncachedPrimaryKeys.remove(entDoc.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EntDocModelImpl.ENTITY_CACHE_ENABLED,
					EntDocImpl.class, primaryKey, nullModel);
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
	 * Returns all the ent docs.
	 *
	 * @return the ent docs
	 */
	@Override
	public List<EntDoc> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ent docs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent docs
	 * @param end the upper bound of the range of ent docs (not inclusive)
	 * @return the range of ent docs
	 */
	@Override
	public List<EntDoc> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ent docs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent docs
	 * @param end the upper bound of the range of ent docs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ent docs
	 */
	@Override
	public List<EntDoc> findAll(int start, int end,
		OrderByComparator<EntDoc> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ent docs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntDocModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent docs
	 * @param end the upper bound of the range of ent docs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ent docs
	 */
	@Override
	public List<EntDoc> findAll(int start, int end,
		OrderByComparator<EntDoc> orderByComparator, boolean retrieveFromCache) {
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

		List<EntDoc> list = null;

		if (retrieveFromCache) {
			list = (List<EntDoc>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ENTDOC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ENTDOC;

				if (pagination) {
					sql = sql.concat(EntDocModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EntDoc>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntDoc>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the ent docs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EntDoc entDoc : findAll()) {
			remove(entDoc);
		}
	}

	/**
	 * Returns the number of ent docs.
	 *
	 * @return the number of ent docs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ENTDOC);

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
		return EntDocModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ent doc persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EntDocImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ENTDOC = "SELECT entDoc FROM EntDoc entDoc";
	private static final String _SQL_SELECT_ENTDOC_WHERE_PKS_IN = "SELECT entDoc FROM EntDoc entDoc WHERE docId IN (";
	private static final String _SQL_SELECT_ENTDOC_WHERE = "SELECT entDoc FROM EntDoc entDoc WHERE ";
	private static final String _SQL_COUNT_ENTDOC = "SELECT COUNT(entDoc) FROM EntDoc entDoc";
	private static final String _SQL_COUNT_ENTDOC_WHERE = "SELECT COUNT(entDoc) FROM EntDoc entDoc WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "entDoc.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EntDoc exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EntDoc exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EntDocPersistenceImpl.class);
}