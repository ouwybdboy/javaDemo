package com.wyb.capt;

public class WebCaptureUtil {

	private static final String ROOT_PATH = "D:/lawSnap/";

	public static void captByPath(String url, String fileName) {
		String filePath = ROOT_PATH + fileName;
		String resource = Thread.currentThread().getContextClassLoader().getResource("phantomjs.exe").getPath()
				.substring(1).replace("phantomjs.exe", "");
		String cmd = String.format(resource+"phantomjs %s \"%s\" \"%s\"",resource+"capture.js", url, filePath);
		System.out.println("执行截图的命令为：" + cmd);
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			System.out.println("执行截图的命令出错：" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		captByPath("http://www.baidu.com", "8a94c1b65e362c0b015e366a1f430002.png");
	}
}
