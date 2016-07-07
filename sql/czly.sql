CREATE TABLE `t_user_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caseType` varchar(100) NOT NULL COMMENT '用例类型',
  `icon` varchar(500) DEFAULT NULL COMMENT '图片路径',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `brand`  varchar(500) DEFAULT NULL ,
  `orderNum` int(11) NOT NULL COMMENT '排序',
  `displayFlag` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `createdby` varchar(50) NOT NULL,
  `updatedby` varchar(50) NOT NULL,
  `creationtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(50) NOT NULL COMMENT '登录名',
  `passwd` varchar(50) NOT NULL COMMENT '登录密码',
  `userName` varchar(100) NOT NULL COMMENT '用户名，唯一',
  `createdby` varchar(50) NOT NULL,
  `updatedby` varchar(50) NOT NULL,
  `creationtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_guest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(1) NOT NULL COMMENT '用户名称',
  `phoneNumber` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `guest` varchar(500) DEFAULT NULL COMMENT '用户留言',
  `creationtime` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

CREATE TABLE `t_weblog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL COMMENT '类型 1:用户打开，2：用户上传：3：分享用户打开',
  `createdby` varchar(50) NOT NULL,
  `updatedby` varchar(50) NOT NULL,
  `creationtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `t_sugar_medical_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caseType` varchar(100) NOT NULL COMMENT '用例类型',
  `icon` varchar(500) DEFAULT NULL COMMENT '图片路径',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `brand`  varchar(500) DEFAULT NULL ,
  `readNum` int(11) NOT NULL COMMENT '排序',
  `displayFlag` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `createdby` varchar(50) NOT NULL,
  `updatedby` varchar(50) NOT NULL,
  `creationtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

CREATE TABLE `t_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(500) DEFAULT NULL COMMENT '图片路径',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `displayFlag` int(11) NOT NULL,
  `createdby` varchar(50) NOT NULL,
  `updatedby` varchar(50) NOT NULL,
  `creationtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;


--掌上双井
CREATE TABLE `t_party_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `partyBranchName` varchar(100) NOT NULL COMMENT 'd党支部名称',
  `communityPartyCommittee` varchar(500) DEFAULT NULL COMMENT '所属社区党委',
  `partyMemberNum` varchar(500) DEFAULT NULL COMMENT '党员人数',
  `branchSecretary` varchar(500) DEFAULT NULL COMMENT '支部书记',
  `createdby` varchar(50) NOT NULL,
  `updatedby` varchar(50) NOT NULL,
  `creationtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

CREATE TABLE `t_party_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `sex` int(1) DEFAULT NULL COMMENT '性别 0：男，1：女',
  `residentialAddress` varchar(500) DEFAULT NULL COMMENT '居住地址', 
  `workAddress` varchar(500) DEFAULT NULL COMMENT '工作地址',
	`partyCommittee` varchar(500) DEFAULT NULL COMMENT '社区党委', 
  `communityBranch` varchar(500) DEFAULT NULL COMMENT '所属支部',
	`partyPosition` varchar(500) DEFAULT NULL COMMENT '党内职务', 
  `identityCard` varchar(18) DEFAULT NULL COMMENT '身份证',
	`phoneNumber` varchar(11) DEFAULT NULL COMMENT '联系电话',
	`status` int(1) DEFAULT NULL COMMENT '状态 0：申请中，1：通过，2：拒绝',
  `createdby` varchar(50) NOT NULL,
  `updatedby` varchar(50) NOT NULL,
  `creationtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;


CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(50) NOT NULL COMMENT '登录名',
  `passwd` varchar(50) NOT NULL COMMENT '登录密码',
  `userName` varchar(100) NOT NULL COMMENT '用户名，唯一',
  `createdby` varchar(50) NOT NULL,
  `updatedby` varchar(50) NOT NULL,
  `creationtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
