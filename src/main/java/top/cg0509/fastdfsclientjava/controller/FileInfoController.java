package top.cg0509.fastdfsclientjava.controller;

import com.github.tobato.fastdfs.domain.GroupState;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.cg0509.fastdfsclientjava.dto.FileExecution;
import top.cg0509.fastdfsclientjava.service.FileInfoService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈赓
 * @version 2018/11/1/001
 */
@Api(value = "文件操作Controller")
@RestController
public class FileInfoController {


    @Resource
    private FileInfoService fileService;

    @ApiOperation(value = "删除文件")
    @ApiImplicitParam(name = "path",value = "文件路径",required = true)
    @PostMapping(value = "/deleteFile")
    public Object deleteFile(@RequestParam("path") String pathUrl){
        Map<String,Object> map = new HashMap<>();
        FileExecution fileExecution = fileService.deleteFile(pathUrl);
        if (fileExecution.getState() == 0){
            map.put("success",true);
            map.put("msg",fileExecution.getState());
        }else {
            map.put("success",false);
            map.put("msg",fileExecution.getState());
        }
        return map;
    }
    @ApiOperation(value = "下载文件")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "path",value = "文件路径",required = true),
        @ApiImplicitParam(name = "savePaht",value = "保存文件路径",required = true)
    })
    @PostMapping(value = "/downloadFile")
    public Object downloadFile(@RequestParam("path") String pathUrl,@RequestParam("savePaht")String savePaht){
        Map<String,Object> map = new HashMap<>();
        FileExecution fileExecution = fileService.downloadFile(pathUrl,savePaht);
        if (fileExecution.getState() == 0){
            map.put("success",true);
            map.put("msg",fileExecution.getState());
        }else {
            map.put("success",false);
            map.put("msg",fileExecution.getState());
        }
        return map;
    }


    @ApiOperation(value = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName",value = "上传项目名称",required = true),
            @ApiImplicitParam(name = "myfile",value = "文件流",required = true)
    })
    @PostMapping(value = "/uploadFile")
    public Object uploadFile(@RequestParam("projectName")String projectName,@RequestParam("myfile") MultipartFile multipartFile){
        Map<String,Object> map = new HashMap<>();
        FileExecution fileExecution = fileService.uploadFile(projectName,multipartFile);
        if (fileExecution.getState() == 0){
            map.put("success",true);
            map.put("msg",fileExecution.getState());
        }else {
            map.put("success",false);
            map.put("msg",fileExecution.getState());
        }
        return map;
    }

    @ApiOperation(value = "文件详细信息、大小、存放IP")
    @ApiImplicitParam(name = "pathUrl",value = "文件路径",required = true)
    @PostMapping(value = "/fileInfo")
    public Object  fileInfo(@RequestParam("pathUrl")String pathUrl) {
        Map<String,Object> map = new HashMap<>();
        FileExecution fileExecution = fileService.getFileInfo(pathUrl);
        if (fileExecution.getState() == 0){
            map.put("success",true);
            map.put("msg",fileExecution.getState());
            map.put("file",fileExecution.getFile());
        }else {
            map.put("success",false);
            map.put("msg",fileExecution.getState());
        }
        return  map;
    }

    @ApiOperation(value = "查询所有文件")
    @PostMapping(value = "/listFile")
    public Object listFile(){
        Map<String,Object> map = new HashMap<>();
        FileExecution fileExecution = fileService.listFile();
        if (fileExecution.getState() == 0){
            map.put("success",true);
            map.put("msg",fileExecution.getState());
            map.put("files",fileExecution.getFiles());
        }else {
            map.put("success",false);
            map.put("msg",fileExecution.getState());
        }
        return map;
    }
}
