
package com.gcaa.nm.wss.client;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessageCreationException;

import com.gcaa.nm.eurocontrol._2_5_0.FlightPlanMessageFilter;
import com.gcaa.nm.eurocontrol._2_5_0.FlightSetDefinitionElement;
import com.gcaa.nm.eurocontrol._2_5_0.FlightSetDefinitionElement.AnuIds;
import com.gcaa.nm.eurocontrol._2_5_0.Subscription;
import com.gcaa.nm.eurocontrol._2_5_0.SubscriptionCreationReply;
import com.gcaa.nm.eurocontrol._2_5_0.SubscriptionCreationRequest;
import com.gcaa.nm.eurocontrol._2_5_0.SubscriptionDeletionRequest;
import com.gcaa.nm.eurocontrol._2_5_0.SubscriptionListReply;
import com.gcaa.nm.eurocontrol._2_5_0.SubscriptionListRequest;
import com.gcaa.nm.eurocontrol._2_5_0.SubscriptionPauseRequest;
import com.gcaa.nm.eurocontrol._2_5_0.SubscriptionResumeReply;
import com.gcaa.nm.eurocontrol._2_5_0.SubscriptionResumeRequest;

public class NmSubscriptionClientImpl extends WebServiceGatewaySupport implements NmSubscriptionClient{

	private static final Logger lOGGER = LoggerFactory.getLogger(NmSubscriptionClientImpl.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	
	public List<Subscription> getSubscriptionList(List<String> states) {
		SubscriptionListRequest subsList = new SubscriptionListRequest();
		subsList.setSendTime(sdf.format(new Date()));
		if(null != states){
			subsList.getStates().getItem().addAll(states);
		}
		JAXBElement<SubscriptionListReply> subListResponseObj = null;
		try{
			subListResponseObj = (JAXBElement<SubscriptionListReply>)getWebServiceTemplate().marshalSendAndReceive(subsList);
		}catch(SoapMessageCreationException ssajException){
			lOGGER.error("NM List Subscription - Request - SOAP Error: \n "+ssajException.getMessage());
			return null;
		}
		
		if(null != subListResponseObj){
			lOGGER.debug("Response Received");
			SubscriptionListReply subListResponse = (SubscriptionListReply) subListResponseObj.getValue();
			if(null != subListResponse.getData() && null != subListResponse.getData().getSubscriptions()){
				return subListResponse.getData().getSubscriptions().getItem();
			}
		}
		return null;
	}
	
	@Override
	public void resumeSubscription(String subUuid){
		SubscriptionResumeRequest resumeRequest = new SubscriptionResumeRequest();
		resumeRequest.setUuid(subUuid);
		resumeRequest.setSendTime(sdf.format(new Date()));
		JAXBElement<SubscriptionResumeReply> subListResponseObj = (JAXBElement<SubscriptionResumeReply>)getWebServiceTemplate().marshalSendAndReceive(resumeRequest);
	}
	
	@Override
	public void pauseSubscription(String subUuid){
		SubscriptionPauseRequest pauseRequest = new SubscriptionPauseRequest();
		pauseRequest.setUuid(subUuid);
		pauseRequest.setSendTime(sdf.format(new Date()));

		JAXBElement<SubscriptionResumeReply> subListResponseObj = (JAXBElement<SubscriptionResumeReply>)getWebServiceTemplate().marshalSendAndReceive(pauseRequest);
	}
	
	public void deleteSubscription(String subscriptionUuid) {
		SubscriptionDeletionRequest subsList = new SubscriptionDeletionRequest();
	
		subsList.setSendTime(sdf.format(new Date()));
		subsList.setUuid(subscriptionUuid);
		Object subDeleteionResponse = getWebServiceTemplate().marshalSendAndReceive(subsList);
		if(null != subDeleteionResponse){
			lOGGER.debug("Response Received");
		}
	}

	public void createSubscription() {
		SubscriptionCreationRequest subsCreateReq = getSubsCreateReq();

		JAXBElement<SubscriptionCreationReply> subListResponseObj 
			= (JAXBElement<SubscriptionCreationReply>) getWebServiceTemplate()
				.marshalSendAndReceive(subsCreateReq);

		if (null != subListResponseObj) {
			lOGGER.debug("Response Received");
			SubscriptionCreationReply subCreateResponse = (SubscriptionCreationReply) subListResponseObj.getValue();
			if (null != subCreateResponse.getData() && null != subCreateResponse.getData().getSubscription()) {
				Subscription subscrip = subCreateResponse.getData().getSubscription();
				String uuid = subscrip.getUuid();
				lOGGER.debug("Subscription UUID = " + uuid);

			}
		}
	}

	private SubscriptionCreationRequest getSubsCreateReq() {
		SubscriptionCreationRequest subsCreateReq = new SubscriptionCreationRequest();
		subsCreateReq.setSendTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		subsCreateReq.setTopic("FLIGHT_PLANS");
		
		FlightPlanMessageFilter filter = new FlightPlanMessageFilter();
		FlightSetDefinitionElement flightSetDefElement = new FlightSetDefinitionElement();
		
		AnuIds anuIds = new FlightSetDefinitionElement.AnuIds();
		anuIds.getItem().add("EH009204");
		anuIds.getItem().add("OMAE");
		
		flightSetDefElement.setAnuIds(anuIds);
		
		FlightPlanMessageFilter.FlightSet flightSet = new FlightPlanMessageFilter.FlightSet();
		flightSet.getItem().add(flightSetDefElement);
		filter.setFlightSet(flightSet);

		subsCreateReq.setMessageFilterFlightPlanMessageFilter(filter);
		subsCreateReq.setQueueName("EH009204.c5009204.FLIGHT_PLANS.20.5.0.a3820a61-8a8a-4228-916a-499a50840401");
		return subsCreateReq;
	}

}