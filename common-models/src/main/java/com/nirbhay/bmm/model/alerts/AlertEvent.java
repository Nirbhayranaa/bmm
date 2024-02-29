package com.nirbhay.bmm.model.alerts;


import com.nirbhay.bmm.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlertEvent extends BaseEntity {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -7607336521171204059L;
	
	private String alertType;
	
	private String eventTypeConstant;
	
	private String dbLable;
	
	private String keyLable;
	
	private String subject;
	
	private String template;
	
	private String isUserSpecific;
	
	private String isSubScribedByCurrentUser;
	
	private String senderText;
	

}
