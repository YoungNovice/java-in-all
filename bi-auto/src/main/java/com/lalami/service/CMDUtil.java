package com.lalami.service;

import com.lalami.vo.ResultVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CMDUtil {

    public static ResultVO<String> callCmd(String locationCmd) {
        StringBuilder sb = new StringBuilder();
        ResultVO<String> resultVO = new ResultVO<>();
        try {
            Process child = Runtime.getRuntime().exec(locationCmd);
            InputStream in = child.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
            in.close();
            try {
                int waitResult = child.waitFor();
                resultVO.setCode(waitResult);
            } catch (InterruptedException e) {
                resultVO.setMsg(e.getMessage());
            }
        } catch (IOException e) {
            resultVO.setMsg(e.getMessage());
        }
        resultVO.setData(sb.toString());
        return resultVO;
    }
}
