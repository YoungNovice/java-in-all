package com.lalami.controller;

import com.lalami.service.CMDUtil;
import com.lalami.vo.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@RequestMapping("/execute")
public class ExecuteCommandController {

    @Value("${batfile.path}")
    private String cmdPath;

    @GetMapping("/bat")
    public Object executeBatFile(
            @RequestParam(required = false, name = "path", defaultValue = "") String path,
            @RequestParam(required = false, name = "fileName", defaultValue = "") String fileName) {
        if (!"".equals(path)) {
            cmdPath = path;
        }
        ResultVO<String> resultVO = CMDUtil.callCmd(cmdPath + "/" + fileName);
        return resultVO;
    }

}
