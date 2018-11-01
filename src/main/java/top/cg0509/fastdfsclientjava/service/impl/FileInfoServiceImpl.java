package top.cg0509.fastdfsclientjava.service.impl;

import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.GroupState;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.github.tobato.fastdfs.service.DefaultTrackerClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.cg0509.fastdfsclientjava.dao.FileMapper;
import top.cg0509.fastdfsclientjava.dto.FileExecution;
import top.cg0509.fastdfsclientjava.entity.FileInformation;
import top.cg0509.fastdfsclientjava.enums.FileEnum;
import top.cg0509.fastdfsclientjava.service.FileInfoService;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author 陈赓
 * @version 2018/11/1/001
 */
@Service
@Transactional
public class FileInfoServiceImpl implements FileInfoService {

    @Autowired
    DefaultGenerateStorageClient defaultGenerateStorageClient;

    @Autowired
    DefaultTrackerClient defaultTrackerClient;

    @Resource
    private FileMapper fileMapper;

    @Override
    public FileExecution deleteFile(String fileUrl) {
        FileExecution fileExecution = null;
        int result = 0;
        try{
            String group = fileUrl.substring(0, fileUrl.indexOf("/"));
            String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
            defaultGenerateStorageClient.deleteFile(group, path);//删除文件
            result = fileMapper.deleteFileById(fileUrl);
            if (result >0){
                fileExecution = new FileExecution(FileEnum.SUCCESS);
            }else {
                fileExecution = new FileExecution(FileEnum.ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            fileExecution = new FileExecution(FileEnum.ERROR);
        }
        return fileExecution;
    }

    @Override
    public FileExecution downloadFile(String fileUrl,String savePath) {
        FileExecution fileExecution = null;
        try{
            byte[] b = download(fileUrl);
            String suffix = fileUrl.substring(fileUrl.lastIndexOf("."));
            System.out.println(suffix);
            IOUtils.write(b, new FileOutputStream(savePath+ File.separator+UUID.randomUUID().toString()+".jpg"));
            fileExecution = new FileExecution(FileEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            fileExecution = new FileExecution(FileEnum.ERROR);
        }
        return fileExecution;
    }
    @Override
    public FileExecution uploadFile(String projectName,MultipartFile multipartFile) {
        FileExecution fileExecution = null;
        FileInformation file = null;
        int result = 0;
        try {
            long length = multipartFile.getSize();
            String fileName = multipartFile.getOriginalFilename();
            System.out.println("fileName"+fileName);
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
            System.out.println("suffix"+suffix);
            InputStream inpu = multipartFile.getInputStream();
            StorePath storePath = defaultGenerateStorageClient.uploadFile("group1", inpu, length, suffix); //上传文件
            file = new FileInformation(projectName,fileName,suffix,storePath.getFullPath());
            result = fileMapper.inserFile(file);
            if(result > 0){
                fileExecution = new FileExecution(FileEnum.SUCCESS);
            }else {
                fileExecution = new FileExecution(FileEnum.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            fileExecution = new FileExecution(FileEnum.ERROR);
         }catch (Exception e){
            fileExecution = new FileExecution(FileEnum.ERROR);
         }
        return fileExecution;
    }

    @Override
    public FileExecution getFileInfo(String pathUrl) {


        FileExecution fileExecution = null;
        FileInformation fileInformation = null;

        try {
            String group = pathUrl.substring(0, pathUrl.indexOf("/"));
            String path = pathUrl.substring(pathUrl.indexOf("/") + 1);
            FileInfo fiele = defaultGenerateStorageClient.queryFileInfo(group, path);//根据组名和路径查找信息
            List<GroupState> list = defaultTrackerClient.listGroups();//获取组名
            fileInformation = new FileInformation();
            fileInformation.setGroupStates(list);
            fileExecution = new FileExecution(FileEnum.SUCCESS,fileInformation);
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileExecution;
    }

    @Override
    public FileExecution listFile() {
        FileExecution fileExecution = null;
        List<FileInformation> fileInformations = null;

        try{
            fileInformations = fileMapper.listFile();
            fileExecution = new FileExecution(FileEnum.SUCCESS,fileInformations);

        }catch (Exception e){
          e.printStackTrace();
          fileExecution = new FileExecution(FileEnum.ERROR);
        }
        return fileExecution;
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件URL
     * @return 文件字节
     * @throws IOException
     */
    private byte[] download(String fileUrl) throws IOException {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = defaultGenerateStorageClient.downloadFile(group, path, downloadByteArray);
        return bytes;
    }
}
