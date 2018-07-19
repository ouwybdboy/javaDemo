package com.wyb.capt;


import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *截屏工具类
 */
public class CaptUtil {

	/**
	 * 截屏
	 * 
	 * @param url 目标网址
	 * @param filePath 截屏图片保存路径
	 */
	private static void captByPath(String url, String filePath) {
		try {
			System.out.println("截屏开始");
			String BLANK = "  ";
			Random random = new Random();
			String resource = Thread.currentThread().getContextClassLoader().getResource("phantomjs.exe").getPath()
					.substring(1).replace("phantomjs.exe", "");
			final String newPhantomjsFileName = random.nextInt(10000) + "_phantomjs.exe";
			final File newPhantomjsFile = new File(resource + newPhantomjsFileName);
			FileUtils.copyFile(new File(resource + "phantomjs.exe"), newPhantomjsFile);
			String cmd = newPhantomjsFile.getAbsolutePath() + BLANK // phantomjs.exe路径
					+ resource + "capt.js" + BLANK // javascript脚本的存放路径
					+ url + BLANK // 你的目标url地址
					+ filePath;// 你的图片输出路径
			System.out.println("执行截图的命令为：" + cmd);
			Process process = Runtime.getRuntime().exec(cmd);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(process.getInputStream(), "utf-8"));
			String line;
			// 等待结束
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				if (line.contains("渲染成功")) {
					break;
				}
			}
			System.out.println("加载完成...");
			// 删除临时文件
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							Runtime.getRuntime().exec("taskkill /f /im " + newPhantomjsFileName);
							FileUtils.forceDelete(newPhantomjsFile);
							System.out.println(newPhantomjsFileName + "已删除");
							return;
						} catch (IOException e) {
							System.out.println(newPhantomjsFileName + "还没关闭");
							try {
								Thread.sleep(200);
							} catch (InterruptedException e1) {
								System.out.println("captByPath出错 "+e1);
							}
						} catch (Exception e) {
							System.out.println("captByPath出错 "+e);
						}
					}
				}
			}).start();
		} catch (Exception e) {
			System.out.println("captByPath出错 "+e);
		}
	}


	public static void main(String[] args) {
		String url = "https://zefannuo.tmall.com/?spm=a220o.1000855.1997427721.d4918089.55e9577e8rpPUm";
		String filePath="D:/pico/test2.jpg";
		captByPath(url,filePath);
	}
}
