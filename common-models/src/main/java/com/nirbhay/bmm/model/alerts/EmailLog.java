package com.nirbhay.bmm.model.alerts;

import com.nirbhay.bmm.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailLog extends BaseEntity {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -8033995745002014809L;

	private String sentTo;
	
	private String sentCc;
	
	private String sentBcc;
	
	private String emailSubject;
	
	private String emailContent;
	
	private String emailType;
	
	private String emailFrom;
	
	private String isAttachmentPresent;
	
	private String attachmentFileNames;
	
	private String status;

}
