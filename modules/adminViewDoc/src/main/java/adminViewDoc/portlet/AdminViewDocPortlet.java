package adminViewDoc.portlet;

import adminViewDoc.constants.AdminViewDocPortletKeys;
import com._42Penguins.gosign.service.EntDocLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raziman Dom
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=admin.goSign",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=adminViewDoc Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdminViewDocPortletKeys.AdminViewDoc,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AdminViewDocPortlet extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(AdminViewDocPortlet.class);
	
	/**
	 * Delete method
	 * @param docId
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	
	public void doDelDoc(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		
		_log.info("###################################################");
		_log.info("#                  doDelDoc log                   #");
		_log.info("###################################################");
		_log.info("START: Deleting Document Function");
		
		long docId = ParamUtil.getLong(actionRequest, "docId");
		try {
			EntDocLocalServiceUtil.deleteEntDoc(docId);
			_log.info("Document " + docId + "has been deleted");
			SessionMessages.add(actionRequest, "request_processed", "Deleted request ID: " + docId);
		} catch (PortalException | SystemException e) {
			_log.error("Unable to delete document");
			e.printStackTrace();
		}
	}
	
	/**
	 * Back Method
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */

	public void doBack(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws IOException, PortletException {		
		actionResponse.setRenderParameter("mvcPath", "/view.jsp");
		
	}
	
}