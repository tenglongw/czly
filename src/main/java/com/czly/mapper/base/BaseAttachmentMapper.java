package com.czly.mapper.base;

import com.czly.entity.Attachment;

public interface BaseAttachmentMapper extends BaseMapper<Attachment> {

    int insert(Attachment attachment);
}