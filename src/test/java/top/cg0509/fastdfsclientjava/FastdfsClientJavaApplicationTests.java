package top.cg0509.fastdfsclientjava;

import java.io.*;
import java.util.List;
import java.util.UUID;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.GroupState;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.github.tobato.fastdfs.service.DefaultTrackerClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FastdfsClientJavaApplicationTests {

    public static Log logger = LogFactory.getLog(FastdfsClientJavaApplicationTests.class);

    @Autowired
    DefaultGenerateStorageClient defaultGenerateStorageClient;

    @Autowired
    DefaultTrackerClient defaultTrackerClient;

    @Test
    public void Test(){
 File file = new File("E:\\namei.jpg");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = FileUtils.openInputStream(file);
        } catch (IOException e) {
 e.printStackTrace();
        }
      //上传
       // StorePath storePath = defaultGenerateStorageClient.uploadFile("group1", fileInputStream, file.length(), "jpg"); //上传文件
 //logger.debug(storePath);
    //    System.out.println(storePath);
//StorePath [group=group1, path=M00/00/00/wKiFgFm9CRmAUTcTAAAkvvmVy_E865.png]
        //删除
       defaultGenerateStorageClient.deleteFile("group1", "M00/00/00/wKhAgFvX_vOAPMTdAAfqZVr-3og723.jpg");//删除文件

 }
    @Test
    public void Test01(){
        FileInfo fiele = defaultGenerateStorageClient.queryFileInfo("group1", "M00/00/00/wKhAgFvXxfyAMc5jAAfqZVr-3og038.jpg");//根据组名和路径查找信息
        System.out.println(fiele);//source_ip_addr = 192.168.133.128, file_size = 9406, create_timestamp = 1970-01-18 18:12:40, crc32 = -107623439
        List<GroupState> list = defaultTrackerClient.listGroups();//获取组名
        System.out.println("123"+list);
/**[GroupState [groupName=group1, totalMB=18421, freeMB=14731, trunkFreeMB=0, storageCount=1, 
 storagePort=23000, storageHttpPort=8888, activeCount=1, currentWriteServer=0, storePathCount=1, 
 subdirCountPerPath=256, currentTrunkFileId=0]]
 **/
    }

    @Test
    public void testDownload() {
        try {

            byte[] b = downloadFile("group1/M00/00/00/wKhAgFvXxfyAMc5jAAfqZVr-3og038.jpg");
            System.out.println(b);
           IOUtils.write(b, new FileOutputStream("D:/"+UUID.randomUUID().toString()+".jpg"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件URL
     * @return 文件字节
     * @throws IOException
     */
    public byte[] downloadFile(String fileUrl) throws IOException {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = defaultGenerateStorageClient.downloadFile(group, path, downloadByteArray);
        return bytes;
    }

}