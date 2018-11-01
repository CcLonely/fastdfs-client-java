package top.cg0509.fastdfsclientjava.service;

import org.springframework.web.multipart.MultipartFile;
import top.cg0509.fastdfsclientjava.dto.FileExecution;

/**
 * @author 陈赓
 * @version 2018/11/1/001
 */
public interface FileInfoService {

  /**
   *删除文件
   * @param path
   * @return
   */
  FileExecution deleteFile(String  path);

  /**
   * 下载文件
   * @param path
   * @return
   */
  FileExecution downloadFile(String  path,String savePath);

  /**
   * 上传文件
   * param projectName
   * @param multipartFile
   * @return
   */
  FileExecution uploadFile(String projectName,MultipartFile multipartFile);

  /**
   * 获取文件的详细信息 存储节点 文件大小等信息
   * @param pathUrl
   * @return
   */
  FileExecution getFileInfo(String pathUrl);

  /**
   * 获取所有文件
   * @return
   */
  FileExecution listFile();

}
