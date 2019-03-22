package com.jpdou.m2review.view;

import org.springframework.web.servlet.View;

import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@Component
public class AbstractView implements View {

    private String template;

    private Map<String, String> vars;

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String tpl = this.loadTemplateFile();
        for (String name : this.vars.keySet()) {
            tpl = tpl.replace(name, this.vars.get(name));
        }
        httpServletResponse.getWriter().write(tpl);
    }

    public void assign(String name, String value)
    {
        this.vars.put(name, value);
    }

    private String loadTemplateFile()
    {
        String contents = "";
        if (this.template != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream("E:\\idea\\m2review\\src\\main\\resources\\templates\\admin\\page.html");
                byte[] b = new byte[512];
                int len;
                while (true) {
                    len = fileInputStream.read(b);
                    if (len >= 0) {
                        contents = contents.concat(new String(b));
                    } else {
                        break;
                    }
                }
                System.out.println(contents);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return contents;
    }

}
