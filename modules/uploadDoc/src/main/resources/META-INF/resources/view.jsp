<%@page import="com.liferay.portal.kernel.service.persistence.UserUtil"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="javax.portlet.PortletException"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@ include file="/init.jsp"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ page import="java.util.Date" %>


<portlet:defineObjects />

<portlet:resourceURL var="getUsers">
	<portlet:param name="<%=Constants.CMD%>" value="get_users" />
</portlet:resourceURL>

<%Date defaultDate = new Date();%>

<!--  

<aui:script>
	AUI()
			.use(
					'autocomplete-list',
					'aui-base',
					'aui-io-request',
					'autocomplete-filters','autocomplete-highlighters',function (A) {var testData;new A.AutoCompleteList({
																allowBrowserAutocomplete : 'true',
									activateFirstItem : 'true',
									inputNode : '#<portlet:namespace />myInputNode',
									resultTextLocator : 'email',
									render : 'true',
									resultHighlighter : 'phraseMatch',
									resultFilters : [ 'phraseMatch' ],
									source : function() {
										var inputValue = A
												.one(
														"#<portlet:namespace />myInputNode")
												.get('value');
										var myAjaxRequest=A.io.request('<%=getUsers.toString()%>',{
															dataType : 'json',
															method : 'POST',
															data : {
																<portlet:namespace />userEmail : inputValue,
															},
															autoLoad : false,
															sync : false,
															on : {
																success : function() {
																	var data = this
																			.get('responseData');
																	testData = data;
																}
															}
														});
										myAjaxRequest.start();
										return testData;
									},
								});
					});

	
</aui:script>

-->

<portlet:actionURL name="addDoc" var="addDoc" />

<aui:form action="<%=addDoc.toString()%>" method="post" name="name" enctype="multipart/form-data">
	
	<aui:input label="Complete URL (Auto): " name="currentCompURL" type="hidden" value="<%=request.getAttribute("currentCompURL")%>" readonly="true" />
	
	<aui:input label="Title: " name="doc_title" helpMessage="In brief about this document/request.">
	<aui:validator name="required"/>
	</aui:input>
	
	<aui:input id="myInputNode" name="sign_email" label="Signer Email: " helpMessage="Email of the user that will sign this document">
	<aui:validator name="required"/>
	<aui:validator name="email"/>
	</aui:input>
	
	<aui:input label="Upload File: " type="file" name="file" helpMessage="Upload document that need to be sign digitally">
	<aui:validator name="required"/>
	</aui:input>
	
	<aui:input label="Description: " type="textarea" name="doc_description" helpMessage="Justification or description of signature request"/>

	<label>Deadline:</label><liferay-ui:input-date name="doc_deadline" firstEnabledDate="<%=defaultDate%>" required="true"/><br>
	
	<aui:select name="doc_type" label="Document Type: " inlineLabel="true">
	<aui:option label="Offer Letter" value="Offer Letter" selected="true"></aui:option>
	<aui:option label="Hardware/Software Request" value="Hardware/Software Request"></aui:option>
	<aui:option label="Project Sign Off" value="Project Sign Off"></aui:option>
	<aui:option label="Others" value="Others"></aui:option>
	</aui:select>
	
	<aui:button name="submit" type="submit" value="Submit" last="true" />

</aui:form>
