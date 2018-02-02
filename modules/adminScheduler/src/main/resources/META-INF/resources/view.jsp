<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="adminScheduler.caption"/></b>
</p>
<portlet:defineObjects />
<portlet:actionURL name="doAction" var="doAction" />

	<aui:form action="<%=doAction%>" method="post" name="name">
		<aui:button cssClass="btn btn-success" name="Check Deadline Status" type="submit"
			value="Check Deadline Status" last="true" />

	</aui:form>