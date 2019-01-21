package com.hengmall.user.service.qcloud;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.CreateFolderRequest;
import com.qcloud.cos.request.DelFileRequest;
import com.qcloud.cos.request.DelFolderRequest;
import com.qcloud.cos.request.GetFileLocalRequest;
import com.qcloud.cos.request.ListFolderRequest;
import com.qcloud.cos.request.MoveFileRequest;
import com.qcloud.cos.request.StatFileRequest;
import com.qcloud.cos.request.StatFolderRequest;
import com.qcloud.cos.request.UpdateFolderRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

/**
 * 对象存储 COS - 腾讯云
 */
//此处把@service去掉  暂时不使用该文件
public class CosService {

	String bucketName = "test";

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	COSClient cosClient = null;

	// 使用默认bucketName = test
	public CosService() {
		load();
	}

	public void load() {

		Properties prop = new Properties();
		try {
			// 加载properties配置文件
			prop.load(CosService.class.getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 初始化秘钥信息
		Credentials cred = new Credentials(Integer.parseInt(prop.getProperty("cos.appId")),
				prop.getProperty("cos.secretId"), prop.getProperty("cos.secretKey"));
		// 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
		// 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
		clientConfig.setRegion(prop.getProperty("cos.region"));
		// 初始化cosClient
		cosClient = new COSClient(clientConfig, cred);
	}

	/**
	 * 上传 通过硬盘
	 *
	 * @param cosPath
	 *            腾讯云cos文件路径
	 * @param localPath
	 *            本地文件路径
	 * @return
	 */
	public String upload(String cosPath, String localPath) {
		System.out.println("bucketName名称：" + bucketName);
		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, cosPath, localPath);
		uploadFileRequest.setEnableShaDigest(false);
		String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		return uploadFileRet;
	}

	/**
	 * 上传 通过硬盘
	 *
	 * @param cosPath
	 * @param localPath
	 * @param bizAttr
	 * @return
	 */
	public String upload(String cosPath, String localPath, String bizAttr) {
		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, cosPath, localPath);
		uploadFileRequest.setBizAttr(bizAttr);
		String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		return uploadFileRet;
	}

	/**
	 * 上传 通过流
	 *
	 * @param cosPath
	 * @param in
	 * @return
	 */
	public String uploadByIo(String cosPath, InputStream in) {
		String uploadFileRet = "";
		try {
			byte[] b = getByte(in);
			UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, cosPath, b);
			uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uploadFileRet;
	}

	/**
	 * 上传 通过流
	 *
	 * @param cosPath
	 * @param in
	 * @param bizAttr
	 *            文件信息属性
	 * @return
	 */
	public String uploadByIo(String cosPath, InputStream in, String bizAttr) {
		String uploadFileRet = "";
		try {
			byte[] b = getByte(in);
			UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, cosPath, b);
			uploadFileRequest.setBizAttr(bizAttr);
			uploadFileRet = cosClient.uploadFile(uploadFileRequest);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uploadFileRet;
	}

	public byte[] getByte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		inStream.close();
		swapStream.close();
		return in2b;
	}

	/**
	 * 下载
	 *
	 * @param cosPath
	 * @param localPath
	 * @return
	 */
	public String down(String cosPath, String localPath) {
		String localPathDown = localPath;
		GetFileLocalRequest getFileLocalRequest = new GetFileLocalRequest(bucketName, cosPath, localPathDown);
		getFileLocalRequest.setUseCDN(false);
		getFileLocalRequest.setReferer("*.myweb.cn");
		String getFileResult = cosClient.getFileLocal(getFileLocalRequest);
		return getFileResult;
	}

	/**
	 * 移动
	 *
	 * @param cosPath
	 * @param dstCosPath
	 * @return
	 */
	public String move(String cosPath, String dstCosPath) {
		String cosFilePath = cosPath;
		String dstCosFilePath = dstCosPath;
		MoveFileRequest moveRequest = new MoveFileRequest(bucketName, cosFilePath, dstCosFilePath);
		String moveFileResult = cosClient.moveFile(moveRequest);
		return moveFileResult;
	}

	/**
	 * 获取文件属性
	 *
	 * @param cosPath
	 * @return
	 */
	public String getProperty(String cosPath) {
		StatFileRequest statFileRequest = new StatFileRequest(bucketName, cosPath);
		String statFileRet = cosClient.statFile(statFileRequest);
		return statFileRet;
	}

	/**
	 * 删除文件
	 *
	 * @param cosPath
	 *            bucketName列表中文件的路径如： bucketName为test下sample_file.txt cosPath =
	 *            /sample_file.txt
	 * @return
	 */
	public String delete(String cosPath) {
		DelFileRequest delFileRequest = new DelFileRequest(bucketName, cosPath);
		String delFileRet = cosClient.delFile(delFileRequest);
		return delFileRet;
	}

	/**
	 * 创建目录
	 *
	 * @param cosPath
	 * @return
	 */
	public String createFolder(String cosPath) {
		CreateFolderRequest createFolderRequest = new CreateFolderRequest(bucketName, cosPath);
		String createFolderRet = cosClient.createFolder(createFolderRequest);
		return createFolderRet;
	}

	/**
	 * 创建目录
	 *
	 * @param cosPath
	 * @param bizAttr
	 * @return
	 */
	public String createFolder(String cosPath, String bizAttr) {
		CreateFolderRequest createFolderRequest = new CreateFolderRequest(bucketName, cosPath);
		createFolderRequest.setBizAttr(bizAttr);
		String createFolderRet = cosClient.createFolder(createFolderRequest);
		return createFolderRet;
	}

	/**
	 * 获取目录属性
	 *
	 * @param cosPath
	 * @return
	 */
	public String getFolderProperty(String cosPath) {
		StatFolderRequest statFolderRequest = new StatFolderRequest(bucketName, cosPath);
		String statFolderRet = cosClient.statFolder(statFolderRequest);
		return statFolderRet;
	}

	/**
	 * 更新目录属性
	 *
	 * @param cosPath
	 * @param bizAttr
	 * @return
	 */
	public String updateFolderProperty(String cosPath, String bizAttr) {
		UpdateFolderRequest updateFolderRequest = new UpdateFolderRequest(bucketName, cosPath);
		updateFolderRequest.setBizAttr(bizAttr);
		String updateFolderRet = cosClient.updateFolder(updateFolderRequest);
		return updateFolderRet;
	}

	/**
	 * 获取目录列表
	 *
	 * @param cosPath
	 * @return
	 */
	public String getFolderList(String cosPath) {
		ListFolderRequest listFolderRequest = new ListFolderRequest(bucketName, cosPath);
		String listFolderRet = cosClient.listFolder(listFolderRequest);
		return listFolderRet;
	}

	/**
	 * 获取目录列表
	 *
	 * @param cosPath
	 * @param num
	 * @return
	 */
	public String getFolderList(String cosPath, int num) {
		ListFolderRequest listFolderRequest = new ListFolderRequest(bucketName, cosPath);
		listFolderRequest.setNum(num);
		String listFolderRet = cosClient.listFolder(listFolderRequest);
		return listFolderRet;
	}

	/**
	 * 获取目录列表
	 *
	 * @param cosPath
	 * @param prefix
	 * @return
	 */
	public String getFolderList(String cosPath, String prefix) {
		ListFolderRequest listFolderRequest = new ListFolderRequest(bucketName, cosPath);
		listFolderRequest.setPrefix(prefix);
		String listFolderRet = cosClient.listFolder(listFolderRequest);
		return listFolderRet;
	}

	/**
	 * 获取目录列表
	 *
	 * @param cosPath
	 * @param num
	 * @param prefix
	 * @return
	 */
	public String getFolderList(String cosPath, int num, String prefix) {
		ListFolderRequest listFolderRequest = new ListFolderRequest(bucketName, cosPath);
		listFolderRequest.setNum(num);
		listFolderRequest.setPrefix(prefix);
		String listFolderRet = cosClient.listFolder(listFolderRequest);
		return listFolderRet;
	}

	/**
	 * 删除目录
	 *
	 * @param cosPath
	 * @return
	 */
	public String deleteFolder(String cosPath) {
		DelFolderRequest delFolderRequest = new DelFolderRequest(bucketName, cosPath);
		String delFolderRet = cosClient.delFolder(delFolderRequest);
		return delFolderRet;
	}

	public COSClient getCosClient() {
		return cosClient;
	}
}
