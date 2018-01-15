<%@ include file="/init.jsp" %>

<portlet:actionURL name="#" var="addDoc" />

<aui:form action="#" method="post" name="name" enctype="multipart/form-data">
	
	<aui:input label="User ID: " name="currentUserId" type="type" readonly="true" />
	<aui:input label="Key ID: " name="currentUserId" type="type" readonly="true" />
	<aui:input label="Insert public key" name="currentUserId" type="textarea" />
	
	<aui:button name="submit" type="submit" value="Verify Key" last="true" />

</aui:form>