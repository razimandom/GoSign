<%@ include file="/init.jsp" %>

<div class="portlet-header">
Manual Deadline Check
</div>
<portlet:defineObjects />
<portlet:actionURL name="doAction" var="doAction" />
<br>

<div class="panel-box-green">
<p>This job will compare if deadline date exceeded today's date.<br>
If deadline > today : Document set to expired<br>
If deadline = today : Send reminder email to signer<br>
If deadline < today : Ignore</p>
<p>This job run automatically once per day 12AM-3AM</p>
</div>
<br>
	<aui:form action="<%=doAction%>" method="post" name="name">
		<aui:button cssClass="btn btn-success" name="Check Deadline Status" type="submit"
			value="Run Manual Job" last="true" />
	</aui:form>