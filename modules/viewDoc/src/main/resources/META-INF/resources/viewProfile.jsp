<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@ include file="/init.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@page import="com._42Penguins.gosign.service.EntKeyLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntKey"%>
<%@page import="com._42Penguins.gosign.service.EntDocLocalServiceUtil"%>
<%@page import="com._42Penguins.gosign.model.EntDoc"%>
<%@page import="com.liferay.portal.kernel.model.User" %>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="com.liferay.portal.kernel.model.User" %>

<%
long userId = ParamUtil.getLong(request, "userId");
long docId = ParamUtil.getLong(request, "docId");
EntDoc docData = EntDocLocalServiceUtil.getEntDoc(docId);
User userData = UserLocalServiceUtil.getUser(userId);
request.setAttribute("docData", docData);

%>

<style>

@media (min-width: 1200px) {
    .container{
        max-width: 800px;
    }
}

td{
	padding:5px}
	
.btn {
	border: none; /* Remove borders */
	color: white; /* Add a text color */
	padding: 8px 28px; /* Add some padding */
	cursor: pointer; /* Add a pointer cursor on mouse-over */
}
	
</style>

<div class="container panel">

  <div class="panel-body">
  
  	<div align="center"><table><tr><td>
  	
  	<liferay-ui:user-display
        markupView="lexicon"
        showUserDetails="false"
        showUserName="false"
		userId="${docData.userId}"
        userName="${docData.sign_name}"/>
  	
  	</td>
	</tr></table>   </div>  
  <table class="table table-hover">
    <tbody>
<tr>
	<td width="150">User ID:</td>
	<td>${docData.signId}</td>
</tr>
<tr>
	<td>Full Name:</td>
	<td>${docData.sign_name}</td>
</tr>
<tr>
	<td>Email:</td>
	<td>${docData.sign_email}</td>
</tr>
<tr>
	<td>Registered On:</td>
	<td><%=userData.getLastLoginDate()%></td>
</tr>
<tr>
	<td>Last Login:</td>
	<td>${userData.getLastLoginDate()}</td>
</tr>
    </tbody>
  </table>
	
</div> </div>

