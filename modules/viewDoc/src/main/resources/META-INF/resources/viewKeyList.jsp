<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com._42Penguins.gosign.service.EntKeyLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntKey"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</liferay-portlet:renderURL>

<div class="container panel panel-default">

<div class="panel-body">

<liferay-ui:search-container
	emptyResultsMessage="No registered key found."
	iteratorURL="<%=iteratorURL%>" delta="10" deltaConfigurable="true">

	<liferay-ui:search-container-results>
		<%
			List<EntKey> keyList = EntKeyLocalServiceUtil.getEntKeies(-1, -1);
					results = ListUtil.subList(keyList, searchContainer.getStart(), searchContainer.getEnd());
					searchContainer.setTotal(keyList.size());
					searchContainer.setResults(results);
		%>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com._42Penguins.gosign.model.EntKey" modelVar="keyData"
		keyProperty="userId">

		<portlet:renderURL var="viewKeyURL">
			<portlet:param name="userId" value="${keyData.userId}" />
			<portlet:param name="mvcPath" value="/viewKeyListDetails.jsp" />
		</portlet:renderURL>

		<portlet:actionURL var="doDelKey" name="doDelKey">
			<portlet:param name="userId" value="${keyData.userId}" />
		</portlet:actionURL>

		<liferay-ui:search-container-column-text
			value="<%=String.valueOf(row.getPos() + 1)%>" name="No" />

		<liferay-ui:search-container-column-text name="User ID"
			property="userId">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Key Owner"
			property="sign_name">
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Created On"
			property="key_dateCreated">
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="Action">
			<a href="${viewKeyURL}" data-toggle="tooltip" title="View keys"><span
				class="glyphicon glyphicon-lock text-orange"></span></a>
		</liferay-ui:search-container-column-text>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator searchContainer="<%=searchContainer%>" />

</liferay-ui:search-container>

</div>
</div>