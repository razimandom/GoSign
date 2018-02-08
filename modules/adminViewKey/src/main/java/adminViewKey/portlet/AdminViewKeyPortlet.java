package adminViewKey.portlet;

import adminViewKey.constants.AdminViewKeyPortletKeys;
import com._42Penguins.gosign.service.EntKeyLocalServiceUtil;
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
 * @author razim
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=admin.goSign",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=adminViewKey Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdminViewKeyPortletKeys.AdminViewKey,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AdminViewKeyPortlet extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(AdminViewKeyPortlet.class);
	
	/**
	 * Delete method
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	
	public void doDelKey(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		
		_log.info("###################################################");
		_log.info("#                  doDelKey log                   #");
		_log.info("###################################################");
		_log.info("START: Deleting Key Function");
		
		long userId = ParamUtil.getLong(actionRequest, "userId");
		_log.info("Deleting key for user: " + userId);
		try {
			EntKeyLocalServiceUtil.deleteEntKey(userId);
			_log.info("Key user " + userId + "has been deleted");
			SessionMessages.add(actionRequest, "request_processed", "Deleted key user " + userId);
		} catch (PortalException | SystemException e) {
			_log.error("Unable to delete key");
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