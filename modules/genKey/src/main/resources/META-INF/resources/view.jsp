<%@ include file="/init.jsp" %>

<portlet:actionURL name="genKeyComplete" var="genKeyComplete" />

<aui:form action="<%=genKeyComplete.toString()%>" method="post" name="name" enctype="multipart/form-data">
	
	<aui:input label="User ID: " name="currentUserId" type="type" value="<%=request.getAttribute("currentUserId") %>" readonly="true" />
	<aui:input label="Key ID: " name="currentUserId" type="type" readonly="true" />
	<aui:input label="Input 6 digits: " name="currentUserId" type="type" readonly="true" />
	
	<aui:button name="submit" type="submit" value="Generate Key" last="true" />

</aui:form>