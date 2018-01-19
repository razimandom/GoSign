<%@ include file="/init.jsp" %>

<portlet:actionURL name="doAction" var="doAction" />

<aui:form action="<%=doAction%>" method="post" name="name" enctype="multipart/form-data">
	<aui:input label="Input 6 digits pin: " name="userPin" type="type" />
	<aui:button name="submit" type="submit" value="Generate Key" last="true" />

</aui:form>

<liferay-ui:error key="error-key-keyExist" message="You only can generate key once. If you need to reset your key, contact system admin." />
<liferay-ui:error key="error-key-invalidPinFormat" message="Error! Please enter your 6 digits pin." />