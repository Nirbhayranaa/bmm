package com.nirbhay.bmm.model.alert;

import lombok.Data;

import java.util.List;

@Data
public class EmailDetails {
	
	private String username; 
	
	private String fromEmailAddress; 
	
	private String password;
	
	private List<String> to; 
	
	private List<String> cc;
	
	private List<String> bcc;
	
	private String subject;
	
	private String message;
	
	private String attachmentFilePath;
	
	private String attachmentFileNameInEmail;
	
	private boolean isAttachmentPresent;
	
	private boolean status;
	
	private boolean onlyToNoCCBCC;


}
