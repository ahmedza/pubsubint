package com.gcaa.nm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gcaa.nm.types.NmMessageType;

@Entity
@Table(name="NM_MESSAGE")
public class NmMessageEntity {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
 	private Long Id;
	private String receivedMessage;
	private NmMessageType messageType;
	private Date receivedDate;
	private Date processedDate;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getReceivedMessage() {
		return receivedMessage;
	}
	public void setReceivedMessage(String receivedMessage) {
		this.receivedMessage = receivedMessage;
	}
	public NmMessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(NmMessageType messageType) {
		this.messageType = messageType;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Date getProcessedDate() {
		return processedDate;
	}
	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}
}
