package com.czly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czly.entity.Attachment;
import com.czly.mapper.AttachmentMapper;
import com.czly.mapper.base.BaseMapper;
import com.czly.service.AttachmentService;
import com.czly.service.base.impl.BaseServiceImpl;

@Service
public class AttachmentServiceImpl extends BaseServiceImpl<Attachment> implements
		AttachmentService {
	// 注入到父类中
	@Resource(name = "attachmentMapper")
	private AttachmentMapper attachmentMapper;
	@Override
	public BaseMapper<Attachment> getBaseMapper() {
		return attachmentMapper;
	}
	@Override
	public void addAttachment(Attachment attachment) {
		attachmentMapper.insert(attachment);
	}


}
