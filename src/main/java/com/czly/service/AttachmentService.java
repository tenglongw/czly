package com.czly.service;

import com.czly.entity.Attachment;
import com.czly.service.base.BaseService;

public interface AttachmentService extends BaseService<Attachment> {
	
	public void addAttachment(Attachment attachment);
}
