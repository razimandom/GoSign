<%@ include file="/init.jsp" %>

<portlet:actionURL name="doAction" var="doAction" />

<p><liferay-ui:message key="genKey.View.Instruction.caption"/></p>

<aui:form action="<%=doAction%>" method="post" name="name" enctype="multipart/form-data">
	<aui:input label="Input 6 digits pin: " name="userPin" type="type" />
	<aui:input label="Action: " name="doAction" type="hidden" value="genkey" readOnly="true"/>
	<aui:button name="submit" type="submit" value="Generate Key" last="true" />
</aui:form>
<br>
<p><liferay-ui:message key="genKey.View.Warning.caption"/></p>
<aui:form action="<%=doAction%>" method="post" name="name" enctype="multipart/form-data">
	<aui:input label="Action: " name="doAction" type="hidden" value="delkey" readOnly="true"/>
	<aui:button name="submit" type="submit" value="Reset Key" last="true" 
	onClick= "return confirm('Are you sure you want to reset the key? All previous documents that have been signed cannot be verify using the new public key. You need to resign the document with new pin.')"/>
</aui:form>

<liferay-ui:error key="error-key-keyExist" message="You already created your keys. You only can create one key." />
<liferay-ui:error key="error-key-keyNoExist" message="No keys available." />
<liferay-ui:error key="error-key-invalidPinFormat" message="Enter your 6 digits pin." />
<liferay-ui:error key="error-key-actionError" message="System unable to process your request." />