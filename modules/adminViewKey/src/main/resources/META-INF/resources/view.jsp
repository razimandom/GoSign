<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com._42Penguins.gosign.service.EntKeyLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntKey"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>

<p>
	<b><liferay-ui:message key="adminViewKey.caption" /></b>
</p>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/view.jsp" />

</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="No uploaded document found."
	iteratorURL="<%=iteratorURL%>" delta="10" deltaConfigurable="true">


	<liferay-ui:search-container-results>

		<%
			String remoteUserId = request.getRemoteUser();
					long userId = Long.valueOf(remoteUserId);
					List<EntKey> keyList = EntKeyLocalServiceUtil.getEntKeies(-1, -1);

					//List<Document> docList = DocumentLocalServiceUtil.getDocuments(-1, -1);
					results = ListUtil.subList(keyList, searchContainer.getStart(), searchContainer.getEnd());
					//results = UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd());
					searchContainer.setTotal(keyList.size());
					searchContainer.setResults(results);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com._42Penguins.gosign.model.EntKey" modelVar="key"
		keyProperty="userId">

		<!--  
		<portlet:renderURL var="viewDocURL">
			<portlet:param name="fileId" value="${document.fileId}" />
			<portlet:param name="docId" value="${document.docId}" />
			<portlet:param name="mvcPath" value="/viewDetails.jsp" />
		</portlet:renderURL>
		
		-->

		<liferay-ui:search-container-column-text
			value="<%=String.valueOf(row.getPos() + 1)%>" name="No" />


		<liferay-ui:search-container-column-text name="User ID"
			property="userId">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Key Owner"
			property="sign_name">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Status"
			property="key_status">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Created On"
			property="key_dateCreated">
		</liferay-ui:search-container-column-text>

		<!--  
		
		<liferay-ui:search-container-column-text name="Action"
			href="${viewDocURL}" value="View">
		</liferay-ui:search-container-column-text>
		
		<portlet:actionURL var="delDocument" name="delDocument">
			<portlet:param name="docId" value="${document.docId }" />
			<portlet:param name="fileId" value="${document.fileId }" />
		</portlet:actionURL>
		
		<liferay-ui:search-container-column-text name="Delete"
			href="${delDocument}" value="Delete">
		</liferay-ui:search-container-column-text>
		-->
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator searchContainer="<%=searchContainer%>" />

</liferay-ui:search-container>