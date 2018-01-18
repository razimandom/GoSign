<%@ include file="/init.jsp" %>

<portlet:actionURL name="genKey" var="genKey" />

<aui:form action="<%=genKey.toString()%>" method="post" name="name" enctype="multipart/form-data">
	
	<aui:input label="User ID: " name="currentUserId" type="type" value="<%=request.getAttribute("currentUserId") %>" readonly="true" />
	<aui:input label="Input 6 digits pin: " name="userPin" type="type" />
	
	<aui:button name="submit" type="submit" value="Generate Key" last="true" />

</aui:form>

<portlet:actionURL name="genSign" var="genSign" />

<aui:form action="<%=genSign.toString()%>" method="post" name="name" enctype="multipart/form-data">
	
	<aui:input label="User ID: " name="currentUserId" type="type" value="<%=request.getAttribute("currentUserId") %>" readonly="true" />
	<aui:input label="Input 6 digits pin: " name="userPin" type="type" />
	
	<aui:button name="submit" type="submit" value="Sign Something" last="true" />

</aui:form>