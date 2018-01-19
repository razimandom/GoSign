package viewKey.portlet;

import viewKey.constants.ViewKeyPortletKeys;

import com._42Penguins.gosign.model.EntKey;
import com._42Penguins.gosign.service.EntKeyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author razim
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=goSign",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=viewKey Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ViewKeyPortletKeys.ViewKey,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ViewKeyPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		long userId = currentUser.getUserId();

		try { 
			
			EntKey genkey = EntKeyLocalServiceUtil.getEntKey(userId);
			String pubKey = genkey.getPublickey_Data();
			String priKey = genkey.getPrivatekey_Data();
			String keyError = "Error generating key. Please regenerate your key.";
			
			if (pubKey != null && priKey != null){
				renderRequest.setAttribute("pubKey", pubKey);
				renderRequest.setAttribute("priKey", priKey);
			} else {
				renderRequest.setAttribute("pubKey", keyError);
				renderRequest.setAttribute("priKey", keyError);
			}

		} catch (PortalException e) {
			// TODO Auto-generated catch block
			String noKey = "No key available. Please generate your key.";
			renderRequest.setAttribute("pubKey", noKey);
			renderRequest.setAttribute("priKey", noKey);
			e.printStackTrace();

		}
		super.doView(renderRequest, renderResponse);
	}
	
	
}