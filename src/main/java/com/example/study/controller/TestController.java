package com.example.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class TestController {

    @GetMapping(path = "/test/download")
    public void download(HttpServletResponse response) throws IOException {
        File temp = File.createTempFile("temp_", ".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
        bw.write("Hello world!");
        bw.write(temp.getAbsolutePath());
        bw.flush();

        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(temp.getName(), StandardCharsets.UTF_8));

        FileInputStream fis = new FileInputStream(temp);
        BufferedInputStream bis = new BufferedInputStream(fis);
        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int i = bis.read(buffer);
        while (i != -1) {
            os.write(buffer, 0, i);
            i = bis.read(buffer);
        }
        fis.close();
        os.close();
        temp.delete();
    }
}
