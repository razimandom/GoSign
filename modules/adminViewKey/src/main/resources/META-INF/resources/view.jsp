<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com._42Penguins.gosign.service.EntKeyLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntKey"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>

<p>
	<liferay-ui:message key="adminViewKey.caption" />
</p>

<div class="alert alert-danger">
	<span class="glyphicon glyphicon-exclamation-sign">&nbsp;</span><strong>WARNING!
		DO NOT</strong> remove the key unless it is necessary!
</div>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</liferay-portlet:renderURL>

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
			<portlet:param name="mvcPath" value="/viewDetails.jsp" />
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
				class="glyphicon glyphicon-briefcase"></span></a>
			    &nbsp;
			<a href="${doDelKey}" data-toggle="tooltip" title="Delete keys"
				onclick="return confirm('WARNING! Are you sure you want remove this key? User need generate the key again!')"><span
				class="glyphicon glyphicon-remove"></span></a>
		</liferay-ui:search-container-column-text>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator searchContainer="<%=searchContainer%>" />

</liferay-ui:search-container>