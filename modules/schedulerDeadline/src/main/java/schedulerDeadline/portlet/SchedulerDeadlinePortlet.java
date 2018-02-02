package schedulerDeadline.portlet;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import com._42Penguins.gosign.model.EntDoc;
import com._42Penguins.gosign.service.EntDocLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;

@Component(immediate = true, service = SchedulerDeadlinePortlet.class)
public class SchedulerDeadlinePortlet extends BaseSchedulerEntryMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		
		try {
			ZoneId zoneIdMYS = ZoneId.of("Asia/Kuala_Lumpur");
			LocalDateTime localDateTime = LocalDateTime.now(zoneIdMYS);
			DateTimeFormatter formatterTimeStamp = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
			DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String currentTimeStamp = localDateTime.format(formatterTimeStamp);
			
			//ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			//String currentHomeURL = themeDisplay.getURLHome();		
			
			long sleeptimesecs = 10;
			long sleeptime = sleeptimesecs * 1000;
			String path = "C:\\Company Project\\GoSign\\Workspace\\GoSign\\log\\log-deadline-reminder\\";
			
			PrintStream out = new PrintStream(new FileOutputStream(path+"log-deadline-reminder-" + currentTimeStamp + ".log"));
			System.setOut(out);
			
			System.out.println("Generating log for " + currentTimeStamp);
			System.out.println("Author: Raziman Dom");
			System.out.println("Log Description:");
			System.out.println("This log generated everyday 12AM to send");
			System.out.println("reminder to signer if submitted task");
			System.out.println("exceeded deadline date");
			System.out.println("========================================");
			
			List<EntDoc> docDataList = EntDocLocalServiceUtil.getEntDocs(-1, -1);
			
			for(EntDoc listdoc:docDataList){
			    String deadlineDate = listdoc.getDoc_deadline();
			    String docStatus = listdoc.getDoc_status();
			    long docId = listdoc.getDocId();
			    LocalDate deadlineDateFormat = LocalDate.parse(deadlineDate, formatterDate);
			    LocalDate nowDate = LocalDate.now();
			    
			    //==> Check if status expired. Ignore if already expired
			    
			    if (docStatus.equals("Expired")){
			    	System.out.println("ID: " + docId + " Expired");
			    	System.out.println("========================================");
			    	
			    	
			    //==> Else, calculate others	
			    } else {
			    	
			    	//==> Update status to rejected if nowDate > deadline
			    	 if (nowDate.isAfter(deadlineDateFormat)){
			    		 
					    	System.out.println("ID: " + docId + " Expiring. Updating status in DB ");
					    	listdoc.setDoc_status("Expired");
					    	listdoc = EntDocLocalServiceUtil.updateEntDoc(listdoc);
					    	
					    	System.out.println("========================================");
					    } else if (nowDate.equals(deadlineDateFormat)){
					    	//==> nowDate equal deadline: send email reminder
					    	System.out.println("ID: " + docId + " Last day");
					    	System.out.println("Proceed to send email reminder");
					    	
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
								
								Thread.sleep(sleeptime); 
								
								System.out.println("\nDelay for " + sleeptimesecs + " seconds for next process");
								System.out.println("========================================\n");
								
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
					    	System.out.println("ID: " + docId + " Valid");
					    	System.out.println("========================================");
					    } 
			    	
			    }
			    
			   
			    
			    
			    
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 5, TimeUnit.MINUTE));
		//schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), new Date(),  "00 00 * * * ?"));

		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	
	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	}

	private SchedulerEngineHelper _schedulerEngineHelper;

}