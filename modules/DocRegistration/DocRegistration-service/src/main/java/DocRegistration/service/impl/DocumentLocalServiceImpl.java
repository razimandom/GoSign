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

package DocRegistration.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;

import DocRegistration.model.Document;
import DocRegistration.service.base.DocumentLocalServiceBaseImpl;

/**
 * The implementation of the document local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link DocRegistration.service.DocumentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentLocalServiceBaseImpl
 * @see DocRegistration.service.DocumentLocalServiceUtil
 */
public class DocumentLocalServiceImpl extends DocumentLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link DocRegistration.service.DocumentLocalServiceUtil} to access the document local service.
	 */
	
	public List<Document> findByUserId(long userId, int start, int end) throws SystemException{
		return this.documentPersistence.findByUserId(userId, start, end);
	}
	
	public List<Document> findBySignId(long signId, int start, int end) throws SystemException{
		return this.documentPersistence.findBySignId(signId, start, end);
	}
}