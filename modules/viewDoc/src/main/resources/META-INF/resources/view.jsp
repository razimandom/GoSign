<%@page import="com.liferay.portal.kernel.service.persistence.UserUtil"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>

<%@page import="javax.portlet.PortletException"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@ include file="/init.jsp"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>

<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>

<!-- Start - CSS Styles for text in table -->

<style>
.txtred {
	color: red
}

.txtgreen {
	color: green
}

.txtgrey {
	color: grey
}

.txtorange {
	color: orange
}
</style>

<!-- End - CSS Styles for text in table -->

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
		//System.out.println(userId);
		//long currentUserId = request.getAttribute("currentUserId");
		List<EntDoc> docList = EntDocLocalServiceUtil.findByUserId(userId, -1, -1);

		//List<Document> docList = DocumentLocalServiceUtil.getDocuments(-1, -1);
		results = ListUtil.subList(docList, searchContainer.getStart(), searchContainer.getEnd());
		//results = UserLocalServiceUtil.getUsers(searchContainer.getStart(), searchContainer.getEnd());
		searchContainer.setTotal(docList.size());
		searchContainer.setResults(results);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com._42Penguins.gosign.model.EntDoc" modelVar="document"
		keyProperty="docId">

		<portlet:renderURL var="viewDocURL">
			<portlet:param name="fileId" value="${document.fileId}" />
			<portlet:param name="docId" value="${document.docId}" />
			<portlet:param name="mvcPath" value="/viewDetails.jsp" />
		</portlet:renderURL>
		
		<portlet:resourceURL var="viewURL">
            <portlet:param name="dataId"
                value="<%=String.valueOf(document.getDocId())%>" />
        </portlet:resourceURL>
		
		<liferay-ui:search-container-column-text 
		value="<%=String.valueOf(row.getPos() + 1)%>" name="No" />
		
		
		<liferay-ui:search-container-column-text name="Req ID"
			property="docId">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Signer Email"
			property="sign_email">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Type"
			property="doc_type">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Deadline"
			property="doc_deadline">
		</liferay-ui:search-container-column-text>

		<c:choose>
			<c:when test="<%=document.getDoc_status().equals("Pending")%>">
				<liferay-ui:search-container-column-text cssClass="txtgrey"
					name="Status" property="doc_status">
				</liferay-ui:search-container-column-text>
			</c:when>
			<c:when test="<%=document.getDoc_status().equals("Signed")%>">
				<liferay-ui:search-container-column-text cssClass="txtgreen"
					name="Status" property="doc_status">
				</liferay-ui:search-container-column-text>
			</c:when>
			<c:when test="<%=document.getDoc_status().equals("Rejected")%>">
				<liferay-ui:search-container-column-text cssClass="txtred"
					name="Status" property="doc_status">
				</liferay-ui:search-container-column-text>
			</c:when>
			<c:when
				test="<%=document.getDoc_status().equals("Justify")%>">
				<liferay-ui:search-container-column-text cssClass="txtorange"
					name="Status" property="doc_status">
				</liferay-ui:search-container-column-text>
			</c:when>
		</c:choose>

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
		
		</liferay-ui:search-container-row>
		
	<liferay-ui:search-iterator searchContainer="<%=searchContainer%>" />

</liferay-ui:search-container>

