<%@ include file="/init.jsp" %>

<portlet:actionURL name="doAction" var="doAction" />

<div class="portlet-header" align="center">
  Generate Key
</div><br>

<div class="alert alert-warning">
	<span class="glyphicon glyphicon-exclamation-sign"></span> You only can generate key once at a time.
</div>

<aui:form action="<%=doAction%>" method="post" name="name" enctype="multipart/form-data">
	<aui:input label="Input 6 alpha-numeric pin (0-9, a-z): " name="userPin" type="type" />
	<aui:input label="Action: " name="doAction" type="hidden" value="genkey" readOnly="true"/>
	<aui:button class="btn btn-primary" name="submit" type="submit" value="Generate Key" last="true" />
	<button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#reset"><span class="glyphicon glyphicon-retweet"></span>&nbsp;Reset Key</button>
</aui:form>
<br>
<div id="reset" class="collapse">
<div class="alert alert-danger">
	<strong>DO NOT</strong> reset the key unless you forgot your pin or keys have been exposed. All
	signed document can no longer be verify using new key.
</div>

<aui:form action="<%=doAction%>" method="post" name="name" enctype="multipart/form-data">
	<aui:input label="Action: " name="doAction" type="hidden" value="delkey" readOnly="true"/>
	<aui:button cssClass="btn btn-danger" name="submit" type="submit" value="Continue" last="true" 
	onClick= "return confirm('Are you sure you want to reset the key?')"/>
</aui:form>
</div>
<liferay-ui:error key="error-key-keyExist" message="You already created your keys. You only can create one key." />
<liferay-ui:error key="error-key-keyNoExist" message="No keys available." />
<liferay-ui:error key="error-key-invalidPinFormat" message="Enter your 6 digits pin." />
<liferay-ui:error key="error-key-actionError" message="System unable to process your request." />