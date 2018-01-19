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

import com._42Penguins.gosign.exception.NoSuchEntDocException;
import com._42Penguins.gosign.model.EntDoc;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the ent doc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Raziman Dom
 * @see com._42Penguins.gosign.service.persistence.impl.EntDocPersistenceImpl
 * @see EntDocUtil
 * @generated
 */
@ProviderType
public interface EntDocPersistence extends BasePersistence<EntDoc> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntDocUtil} to access the ent doc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ent docs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching ent docs
	*/
	public java.util.List<EntDoc> findByFindUserId(long userId);

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
	public java.util.List<EntDoc> findByFindUserId(long userId, int start,
		int end);

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
	public java.util.List<EntDoc> findByFindUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator);

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
	public java.util.List<EntDoc> findByFindUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ent doc in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ent doc
	* @throws NoSuchEntDocException if a matching ent doc could not be found
	*/
	public EntDoc findByFindUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException;

	/**
	* Returns the first ent doc in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ent doc, or <code>null</code> if a matching ent doc could not be found
	*/
	public EntDoc fetchByFindUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator);

	/**
	* Returns the last ent doc in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ent doc
	* @throws NoSuchEntDocException if a matching ent doc could not be found
	*/
	public EntDoc findByFindUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException;

	/**
	* Returns the last ent doc in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ent doc, or <code>null</code> if a matching ent doc could not be found
	*/
	public EntDoc fetchByFindUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator);

	/**
	* Returns the ent docs before and after the current ent doc in the ordered set where userId = &#63;.
	*
	* @param docId the primary key of the current ent doc
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ent doc
	* @throws NoSuchEntDocException if a ent doc with the primary key could not be found
	*/
	public EntDoc[] findByFindUserId_PrevAndNext(long docId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException;

	/**
	* Removes all the ent docs where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByFindUserId(long userId);

	/**
	* Returns the number of ent docs where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching ent docs
	*/
	public int countByFindUserId(long userId);

	/**
	* Returns all the ent docs where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @return the matching ent docs
	*/
	public java.util.List<EntDoc> findByFindSignEmail(
		java.lang.String sign_email);

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
	public java.util.List<EntDoc> findByFindSignEmail(
		java.lang.String sign_email, int start, int end);

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
	public java.util.List<EntDoc> findByFindSignEmail(
		java.lang.String sign_email, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator);

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
	public java.util.List<EntDoc> findByFindSignEmail(
		java.lang.String sign_email, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ent doc in the ordered set where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ent doc
	* @throws NoSuchEntDocException if a matching ent doc could not be found
	*/
	public EntDoc findByFindSignEmail_First(java.lang.String sign_email,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException;

	/**
	* Returns the first ent doc in the ordered set where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ent doc, or <code>null</code> if a matching ent doc could not be found
	*/
	public EntDoc fetchByFindSignEmail_First(java.lang.String sign_email,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator);

	/**
	* Returns the last ent doc in the ordered set where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ent doc
	* @throws NoSuchEntDocException if a matching ent doc could not be found
	*/
	public EntDoc findByFindSignEmail_Last(java.lang.String sign_email,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException;

	/**
	* Returns the last ent doc in the ordered set where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ent doc, or <code>null</code> if a matching ent doc could not be found
	*/
	public EntDoc fetchByFindSignEmail_Last(java.lang.String sign_email,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator);

	/**
	* Returns the ent docs before and after the current ent doc in the ordered set where sign_email = &#63;.
	*
	* @param docId the primary key of the current ent doc
	* @param sign_email the sign_email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ent doc
	* @throws NoSuchEntDocException if a ent doc with the primary key could not be found
	*/
	public EntDoc[] findByFindSignEmail_PrevAndNext(long docId,
		java.lang.String sign_email,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator)
		throws NoSuchEntDocException;

	/**
	* Removes all the ent docs where sign_email = &#63; from the database.
	*
	* @param sign_email the sign_email
	*/
	public void removeByFindSignEmail(java.lang.String sign_email);

	/**
	* Returns the number of ent docs where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @return the number of matching ent docs
	*/
	public int countByFindSignEmail(java.lang.String sign_email);

	/**
	* Caches the ent doc in the entity cache if it is enabled.
	*
	* @param entDoc the ent doc
	*/
	public void cacheResult(EntDoc entDoc);

	/**
	* Caches the ent docs in the entity cache if it is enabled.
	*
	* @param entDocs the ent docs
	*/
	public void cacheResult(java.util.List<EntDoc> entDocs);

	/**
	* Creates a new ent doc with the primary key. Does not add the ent doc to the database.
	*
	* @param docId the primary key for the new ent doc
	* @return the new ent doc
	*/
	public EntDoc create(long docId);

	/**
	* Removes the ent doc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param docId the primary key of the ent doc
	* @return the ent doc that was removed
	* @throws NoSuchEntDocException if a ent doc with the primary key could not be found
	*/
	public EntDoc remove(long docId) throws NoSuchEntDocException;

	public EntDoc updateImpl(EntDoc entDoc);

	/**
	* Returns the ent doc with the primary key or throws a {@link NoSuchEntDocException} if it could not be found.
	*
	* @param docId the primary key of the ent doc
	* @return the ent doc
	* @throws NoSuchEntDocException if a ent doc with the primary key could not be found
	*/
	public EntDoc findByPrimaryKey(long docId) throws NoSuchEntDocException;

	/**
	* Returns the ent doc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param docId the primary key of the ent doc
	* @return the ent doc, or <code>null</code> if a ent doc with the primary key could not be found
	*/
	public EntDoc fetchByPrimaryKey(long docId);

	@Override
	public java.util.Map<java.io.Serializable, EntDoc> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ent docs.
	*
	* @return the ent docs
	*/
	public java.util.List<EntDoc> findAll();

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
	public java.util.List<EntDoc> findAll(int start, int end);

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
	public java.util.List<EntDoc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator);

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
	public java.util.List<EntDoc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntDoc> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ent docs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ent docs.
	*
	* @return the number of ent docs
	*/
	public int countAll();
}