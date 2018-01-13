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

import DocRegistration.model.Document;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the document service. This utility wraps {@link DocRegistration.service.persistence.impl.DocumentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentPersistence
 * @see DocRegistration.service.persistence.impl.DocumentPersistenceImpl
 * @generated
 */
@ProviderType
public class DocumentUtil {
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
	public static void clearCache(Document document) {
		getPersistence().clearCache(document);
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
	public static List<Document> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Document> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Document> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Document> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Document update(Document document) {
		return getPersistence().update(document);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Document update(Document document,
		ServiceContext serviceContext) {
		return getPersistence().update(document, serviceContext);
	}

	/**
	* Returns all the documents where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching documents
	*/
	public static List<Document> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the documents where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of documents
	* @param end the upper bound of the range of documents (not inclusive)
	* @return the range of matching documents
	*/
	public static List<Document> findByUserId(long userId, int start, int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the documents where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of documents
	* @param end the upper bound of the range of documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching documents
	*/
	public static List<Document> findByUserId(long userId, int start, int end,
		OrderByComparator<Document> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the documents where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of documents
	* @param end the upper bound of the range of documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching documents
	*/
	public static List<Document> findByUserId(long userId, int start, int end,
		OrderByComparator<Document> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first document in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document
	* @throws NoSuchDocumentException if a matching document could not be found
	*/
	public static Document findByUserId_First(long userId,
		OrderByComparator<Document> orderByComparator)
		throws DocRegistration.exception.NoSuchDocumentException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first document in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document, or <code>null</code> if a matching document could not be found
	*/
	public static Document fetchByUserId_First(long userId,
		OrderByComparator<Document> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last document in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document
	* @throws NoSuchDocumentException if a matching document could not be found
	*/
	public static Document findByUserId_Last(long userId,
		OrderByComparator<Document> orderByComparator)
		throws DocRegistration.exception.NoSuchDocumentException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last document in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document, or <code>null</code> if a matching document could not be found
	*/
	public static Document fetchByUserId_Last(long userId,
		OrderByComparator<Document> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the documents before and after the current document in the ordered set where userId = &#63;.
	*
	* @param docId the primary key of the current document
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document
	* @throws NoSuchDocumentException if a document with the primary key could not be found
	*/
	public static Document[] findByUserId_PrevAndNext(long docId, long userId,
		OrderByComparator<Document> orderByComparator)
		throws DocRegistration.exception.NoSuchDocumentException {
		return getPersistence()
				   .findByUserId_PrevAndNext(docId, userId, orderByComparator);
	}

	/**
	* Removes all the documents where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of documents where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching documents
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the documents where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @return the matching documents
	*/
	public static List<Document> findBySignEmail(java.lang.String sign_email) {
		return getPersistence().findBySignEmail(sign_email);
	}

	/**
	* Returns a range of all the documents where sign_email = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sign_email the sign_email
	* @param start the lower bound of the range of documents
	* @param end the upper bound of the range of documents (not inclusive)
	* @return the range of matching documents
	*/
	public static List<Document> findBySignEmail(java.lang.String sign_email,
		int start, int end) {
		return getPersistence().findBySignEmail(sign_email, start, end);
	}

	/**
	* Returns an ordered range of all the documents where sign_email = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sign_email the sign_email
	* @param start the lower bound of the range of documents
	* @param end the upper bound of the range of documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching documents
	*/
	public static List<Document> findBySignEmail(java.lang.String sign_email,
		int start, int end, OrderByComparator<Document> orderByComparator) {
		return getPersistence()
				   .findBySignEmail(sign_email, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the documents where sign_email = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sign_email the sign_email
	* @param start the lower bound of the range of documents
	* @param end the upper bound of the range of documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching documents
	*/
	public static List<Document> findBySignEmail(java.lang.String sign_email,
		int start, int end, OrderByComparator<Document> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySignEmail(sign_email, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first document in the ordered set where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document
	* @throws NoSuchDocumentException if a matching document could not be found
	*/
	public static Document findBySignEmail_First(java.lang.String sign_email,
		OrderByComparator<Document> orderByComparator)
		throws DocRegistration.exception.NoSuchDocumentException {
		return getPersistence()
				   .findBySignEmail_First(sign_email, orderByComparator);
	}

	/**
	* Returns the first document in the ordered set where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching document, or <code>null</code> if a matching document could not be found
	*/
	public static Document fetchBySignEmail_First(java.lang.String sign_email,
		OrderByComparator<Document> orderByComparator) {
		return getPersistence()
				   .fetchBySignEmail_First(sign_email, orderByComparator);
	}

	/**
	* Returns the last document in the ordered set where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document
	* @throws NoSuchDocumentException if a matching document could not be found
	*/
	public static Document findBySignEmail_Last(java.lang.String sign_email,
		OrderByComparator<Document> orderByComparator)
		throws DocRegistration.exception.NoSuchDocumentException {
		return getPersistence()
				   .findBySignEmail_Last(sign_email, orderByComparator);
	}

	/**
	* Returns the last document in the ordered set where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching document, or <code>null</code> if a matching document could not be found
	*/
	public static Document fetchBySignEmail_Last(java.lang.String sign_email,
		OrderByComparator<Document> orderByComparator) {
		return getPersistence()
				   .fetchBySignEmail_Last(sign_email, orderByComparator);
	}

	/**
	* Returns the documents before and after the current document in the ordered set where sign_email = &#63;.
	*
	* @param docId the primary key of the current document
	* @param sign_email the sign_email
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next document
	* @throws NoSuchDocumentException if a document with the primary key could not be found
	*/
	public static Document[] findBySignEmail_PrevAndNext(long docId,
		java.lang.String sign_email,
		OrderByComparator<Document> orderByComparator)
		throws DocRegistration.exception.NoSuchDocumentException {
		return getPersistence()
				   .findBySignEmail_PrevAndNext(docId, sign_email,
			orderByComparator);
	}

	/**
	* Removes all the documents where sign_email = &#63; from the database.
	*
	* @param sign_email the sign_email
	*/
	public static void removeBySignEmail(java.lang.String sign_email) {
		getPersistence().removeBySignEmail(sign_email);
	}

	/**
	* Returns the number of documents where sign_email = &#63;.
	*
	* @param sign_email the sign_email
	* @return the number of matching documents
	*/
	public static int countBySignEmail(java.lang.String sign_email) {
		return getPersistence().countBySignEmail(sign_email);
	}

	/**
	* Caches the document in the entity cache if it is enabled.
	*
	* @param document the document
	*/
	public static void cacheResult(Document document) {
		getPersistence().cacheResult(document);
	}

	/**
	* Caches the documents in the entity cache if it is enabled.
	*
	* @param documents the documents
	*/
	public static void cacheResult(List<Document> documents) {
		getPersistence().cacheResult(documents);
	}

	/**
	* Creates a new document with the primary key. Does not add the document to the database.
	*
	* @param docId the primary key for the new document
	* @return the new document
	*/
	public static Document create(long docId) {
		return getPersistence().create(docId);
	}

	/**
	* Removes the document with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param docId the primary key of the document
	* @return the document that was removed
	* @throws NoSuchDocumentException if a document with the primary key could not be found
	*/
	public static Document remove(long docId)
		throws DocRegistration.exception.NoSuchDocumentException {
		return getPersistence().remove(docId);
	}

	public static Document updateImpl(Document document) {
		return getPersistence().updateImpl(document);
	}

	/**
	* Returns the document with the primary key or throws a {@link NoSuchDocumentException} if it could not be found.
	*
	* @param docId the primary key of the document
	* @return the document
	* @throws NoSuchDocumentException if a document with the primary key could not be found
	*/
	public static Document findByPrimaryKey(long docId)
		throws DocRegistration.exception.NoSuchDocumentException {
		return getPersistence().findByPrimaryKey(docId);
	}

	/**
	* Returns the document with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param docId the primary key of the document
	* @return the document, or <code>null</code> if a document with the primary key could not be found
	*/
	public static Document fetchByPrimaryKey(long docId) {
		return getPersistence().fetchByPrimaryKey(docId);
	}

	public static java.util.Map<java.io.Serializable, Document> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the documents.
	*
	* @return the documents
	*/
	public static List<Document> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of documents
	* @param end the upper bound of the range of documents (not inclusive)
	* @return the range of documents
	*/
	public static List<Document> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of documents
	* @param end the upper bound of the range of documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of documents
	*/
	public static List<Document> findAll(int start, int end,
		OrderByComparator<Document> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of documents
	* @param end the upper bound of the range of documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of documents
	*/
	public static List<Document> findAll(int start, int end,
		OrderByComparator<Document> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the documents from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of documents.
	*
	* @return the number of documents
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DocumentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DocumentPersistence, DocumentPersistence> _serviceTracker =
		ServiceTrackerFactory.open(DocumentPersistence.class);
}