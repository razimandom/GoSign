package uploadDocMail.portlet;

import uploadDocMail.constants.UploadDocMailPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.mail.internet.InternetAddress;
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
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=uploadDocMail Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UploadDocMailPortletKeys.UploadDocMail,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UploadDocMailPortlet extends MVCPortlet {
	
	public void sendMailMessage (ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		InternetAddress recipient = new InternetAddress();
		
	}
	
	
}