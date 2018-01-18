<%@ include file="/init.jsp" %>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="DocRegistration.model.GenKey"%>
<%@page import="DocRegistration.service.GenKeyLocalServiceUtil"%>

<%
long userId = ParamUtil.getLong(request, "userId");
GenKey genkey = GenKeyLocalServiceUtil.getGenKey(20156);
request.setAttribute("genkey", genkey);
String redirect = ParamUtil.getString(request, "backURL");
%>

<portlet:actionURL name="doAction" var="doAction" />

<aui:form action="<%=doAction%>" method="post" name="name" enctype="multipart/form-data">
	<aui:input label="Input 6 digits pin: " name="userPin" type="type" />
	<aui:button name="submit" type="submit" value="Generate Key" last="true" />

</aui:form>
<br>
<portlet:actionURL name="genSign" var="genSign" />

<aui:form action="#" method="post" name="name" enctype="multipart/form-data">

	<aui:input label="Your generated public key: " name="pubKey" type="textarea" value="${genkey.publickey_Data}"  readonly="true"/>
	
	
</aui:form>

<liferay-ui:error key="error-key-keyExist" message="You only can generate key once. If you need to reset your key, contact system admin." />
<liferay-ui:error key="error-key-invalidPinFormat" message="Error! Please enter your 6 digits pin." />