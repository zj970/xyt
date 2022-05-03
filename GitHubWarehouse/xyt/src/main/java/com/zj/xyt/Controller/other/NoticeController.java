package com.zj.xyt.Controller.other;

import com.zj.xyt.Entity.Notice;
import com.zj.xyt.Server.NoticeService;
import com.zj.xyt.utils.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 用于控制公告
 * @author zj970
 * @Date:2021 5/3
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @RequestMapping("/index")
    @RequiresPermissions("notice:query")
    public ModelAndView index() throws Exception{
        return new ModelAndView("/system/notice/adminNoticeList");
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> getNoticeList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue="")String searchKey) throws Exception {
        Map<String,Object> map = new HashMap<>();
        //一共三个权限 获取全部的行数
        int type = 3;
        int count = noticeService.getCountByType(type,searchKey);

        PageUtil pageUtil = new PageUtil(page,limit);
        List<Notice> list = noticeService.getNoticeListByType(type,searchKey,pageUtil);


        map.put("count",count);
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");

        return map;
    }

    @RequestMapping("/look")
    public ModelAndView showNotice(){
        return new ModelAndView("/system/notice/notice");
    }

    @RequestMapping("/addPage")
    public ModelAndView toAddPage() {
        return new ModelAndView("/system/notice/noticeAdd");
    }


    @RequestMapping("/addNotice")
    @ResponseBody
    public Map<String, Object> addNotice(@RequestParam(defaultValue="2")Integer opType, Notice notice) throws Exception {
        Map<String, Object> map = new HashMap<>();
//        System.out.println("通知id:"+notice.getId());
//        System.out.println("opType为:"+opType);
//        System.out.println("content为:"+notice.getContent());

        int res = 0;
        //opType等于0是添加 1是更新
        if (opType == 0) {
            try {
                res = noticeService.addNotice(notice);
            } catch (Exception e) {
//                System.out.println("添加失败！");
                map.put("result",false);
            }
            if (res > 0){
                map.put("result",true);
            }else{
                map.put("result",false);
            }

        } else if (opType == 1) {
            res = noticeService.updateNotice(notice);
            if (res > 0) {
                map.put("result",true);
            }else{
                map.put("result",false);
            }

        };

        return map;
    }

    @ResponseBody
    @RequestMapping("/deleteNotice")
    public Map<String, Object> deleteNotice(Notice notice) {
        Map<String, Object> map = new HashMap<>();
        if (noticeService.deleteNotice(notice) > 0) {
            map.put("result",true);
            map.put("msg","删除成功");
        }else {
            map.put("result",false);
            map.put("msg","删除失败");
        }
        return map;
    }

    /**
     * 批量删除通知
     * @param nIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteList")
    public Map<String, Object> deleteNoticeList(String nIds) {
        Map<String, Object> map = new HashMap<>();
        List<Integer> list = new ArrayList<Integer>();
        try {
            String[] ids = nIds.split(",");
            for (String id: ids) {
                list.add(Integer.parseInt(id));
            }
            if (noticeService.deleteNoticeList(list) > 0) {
                map.put("result",true);
                map.put("msg","批量删除成功");
            }else {
                map.put("result",false);
                map.put("msg","批量删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result",false);
            map.put("msg","批量删除失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value="/uploadImg")
    public String uploadImg(MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("comming!");
        String path = request.getSession().getServletContext().getRealPath("/images");
        System.out.println("path>>"+path);
        //获取上传图片的名称
        String fileName = file.getOriginalFilename();
        System.out.println("fileName>>"+fileName);
//        //获取图片的后缀 例:.jpg
//        fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
//        System.out.println("fileName1>>"+fileName);
//        //生成图片名称
//        fileName = System.currentTimeMillis() + fileName; //System.currentTimeMillis()产生一个当前的毫秒 +文件后缀 例.jpg
        System.out.println("fileName2>>"+fileName);
        File dir = new File(path, fileName);
        System.out.println("File>>"+dir);
        if(!dir.exists()){
            dir.mkdirs();
        }
//      MultipartFile自带的解析方法
        file.transferTo(dir);

        String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + null + ",\"data\":" + "{\"src\":\"" + "/images/" + fileName + "\"}" + "}";

        return jsonStr;
    }
}
