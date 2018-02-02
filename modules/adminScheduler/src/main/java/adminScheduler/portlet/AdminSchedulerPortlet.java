package adminScheduler.portlet;

import adminScheduler.constants.AdminSchedulerPortletKeys;
import com._42Penguins.gosign.model.EntDoc;
import com._42Penguins.gosign.service.EntDocLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
		"com.liferay.portlet.display-category=admin.goSign",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=adminScheduler Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdminSchedulerPortletKeys.AdminScheduler,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AdminSchedulerPortlet extends MVCPortlet {
	
	public void doAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {

	}
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		

		
		try {
			ZoneId zoneIdMYS = ZoneId.of("Asia/Kuala_Lumpur");
			LocalDateTime localDateTime = LocalDateTime.now(zoneIdMYS);
			DateTimeFormatter formatterTimeStamp = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
			DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String currentTimeStamp = localDateTime.format(formatterTimeStamp);
			
			//ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			//String currentHomeURL = themeDisplay.getURLHome();		
			
			long sleeptimesecs = 5;
			long sleeptime = sleeptimesecs * 1000;
			String path = "C:\\Company Project\\GoSign\\Workspace\\GoSign\\log\\log-deadline-reminder\\";
			
			 File files = new File(path);
		        if (!files.exists()) {
		            if (files.mkdirs()) {
		                System.out.println("Created log folder. Path: " + path);
		            } else {
		                System.out.println("Failed to create multiple directories!");
		            }
		        }			
			
			PrintStream out = new PrintStream(new FileOutputStream(path+"log-deadline-reminder-" + currentTimeStamp + ".log"));
			System.setOut(out);
			
			System.out.println("Generating log for " + currentTimeStamp);
			System.out.println("Author: Raziman Dom");
			System.out.println("\nLog Description:");
			System.out.println("This log generated everyday 12AM to send");
			System.out.println("reminder to signer if submitted task");
			System.out.println("exceeded deadline date");
			System.out.println("\n========================================");
			
			List<EntDoc> docDataList = EntDocLocalServiceUtil.getEntDocs(-1, -1);
			
			for(EntDoc listdoc:docDataList){
			    String deadlineDate = listdoc.getDoc_deadline();
			    String docStatus = listdoc.getDoc_status();
			    long docId = listdoc.getDocId();
			    LocalDate deadlineDateFormat = LocalDate.parse(deadlineDate, formatterDate);
			    LocalDate nowDate = LocalDate.now();
			    
			    //==> Check if status expired. Ignore if already expired
			    if (docStatus.equals("Expired")){
			    	System.out.println("ID: " + docId + " " + docStatus);
			    	System.out.println("========================================");
			    } else if (docStatus.equals("Signed")){
			    	System.out.println("ID: " + docId + " " + docStatus);
			    	System.out.println("========================================");
			    } else if (docStatus.equals("Verified")){
			    	System.out.println("ID: " + docId + " " + docStatus);
			    	System.out.println("========================================");
			    } else if (docStatus.equals("Rejected")){
			    	System.out.println("ID: " + docId + " " + docStatus);
			    	System.out.println("========================================");
			    } else if (docStatus.equals("Pending") || docStatus.equals("Justify")) {
			    	System.out.println("ID: " + docId + " " + docStatus);
			    	System.out.println("Checking deadline...");
			    	
			    	//==> Update status to rejected if nowDate > deadline
			    	 if (nowDate.isAfter(deadlineDateFormat)){
			    		 
					    	System.out.println("ID: " + docId + " expiring. Updating status in DB ");
					    	listdoc.setDoc_status("Expired");
					    	listdoc = EntDocLocalServiceUtil.updateEntDoc(listdoc);
					    	System.out.println("========================================");
					    	
					    } else if (nowDate.equals(deadlineDateFormat)){
					    	//==> nowDate equal deadline: send email reminder
					    	System.out.println("ID: " + docId + " last day. Sending email.");
					    	
							try {
								
								EntDoc docData = EntDocLocalServiceUtil.getEntDoc(docId);
								
								InternetAddress fromAddress = null;
								InternetAddress toAddress = null;
								
								fromAddress = new InternetAddress("noreply@42penguins.com");
								toAddress = new InternetAddress(docData.getSign_email());
								MailMessage mailMessage = new MailMessage();
								
								mailMessage.setTo(toAddress);
								mailMessage.setFrom(fromAddress);
								mailMessage.setSubject("Reminder! Last day to sign document ID " + docId);
								
								//mailMessage.setBody(body);
								
								mailMessage.setBody(""
										
								+ "<font face=\"arial\" size=\"2\">"
								+ "<p>Dear Signer,</p> "
								+ "<p>You have pending signature request that need your immediate action (Request ID: " + docId + ")</p>"
								+ "<p>This request was submitted by " + docData.getReq_name() + ". Today is last day to sign the document. "
								+ "Please go t and review as soon as possible.</p>"
								+ "If you need clarification on this request, please contact " + docData.getReq_name() + " (" + docData.getReq_email() +") for more details."
								+ "<br>"
								+ "<p>Sincerely,<br>"
								+ "GoSign Team<br>" 
								+ "</p>" + "</font>");
								
								mailMessage.setHTMLFormat(true);
								MailServiceUtil.sendEmail(mailMessage);
								System.out.println("Reminder email has been sent to signer");
								System.out.println("Delay for " + sleeptimesecs + " seconds for next process");
								
								Thread.sleep(sleeptime); 

								System.out.println("========================================");
								
							} catch (AddressException e) {
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (PortalException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	
					    } else if (nowDate.isBefore(deadlineDateFormat)){
					    	System.out.println("ID: " + docId + " deadline still valid");
					    	System.out.println("========================================");
					    } else {
					    	System.out.println("ID: " + docId + " incorrect date");
					    	System.out.println("========================================");
					    }
			    	 
				    } else {
				    	System.out.println("Status not found");
				    	System.out.println("========================================");
				    }
			    
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}