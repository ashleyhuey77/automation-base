package com.warnermedia.data.loki;

import com.warnermedia.utils.TestUtils;

public class Video {
	
	private final Loki loki;
	
	protected Video(Loki loki) {
		this.loki = loki;
	}
	
	public String url() {
		return loki.mockValuesService().fetchString("video.url");
	}
	
	public String filePath() {
		return TestUtils.getRelativePath() + loki.mockValuesService().fetchString("video.file_path");
	}

}
