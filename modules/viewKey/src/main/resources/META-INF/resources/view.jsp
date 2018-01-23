<%@ include file="/init.jsp" %>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<portlet:defineObjects />

<aui:form action="#" method="post" name="name" enctype="multipart/form-data">
    <p>Public key can be use to verify your signature after you have signed a document.<br>You can share your public key to other user.</p><br>
	<aui:input label="Public Key: " name="pubKey" type="textarea" value="<%=request.getAttribute("pubKey") %>" readonly="true" />
	<div class="alert alert-danger">
    <strong>DO NOT</strong> share your private key publicly. If you have shared, please reset your keys immediately!
  	</div>
	<aui:input label="Private Key: " name="priKey"  type="textarea" value="<%=request.getAttribute("priKey") %>" readonly="true" />
</aui:form>