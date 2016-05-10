package com.czly.task;

import org.springframework.stereotype.Component;

/**
 * 每60秒检测一次网络,写入固定的磁盘文件夹.每天定时将文件清除掉
 */
@Component
public class NetWorkConnectTask {
	public void run() {
	}
}