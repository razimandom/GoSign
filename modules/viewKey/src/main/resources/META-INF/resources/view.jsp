<%@ include file="/init.jsp" %>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<portlet:defineObjects />

<aui:form action="#" method="post" name="name" enctype="multipart/form-data">

	<aui:input label="Public Key: " name="pubKey" type="textarea" value="<%=request.getAttribute("pubKey") %>" readonly="true" />
	<aui:input label="Private Key: " name="priKey"  type="textarea" value="<%=request.getAttribute("priKey") %>" readonly="true" />
</aui:form>