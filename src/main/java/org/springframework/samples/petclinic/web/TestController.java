package org.springframework.samples.petclinic.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/test")
    public String showTestPage(HttpServletRequest request, Model model) {
        try {
            InetAddress inet = InetAddress.getLocalHost();
            model.addAttribute("message", "Hello World. From JSP test page Tomcat is running.");
            model.addAttribute("currentDate", new Date());
            model.addAttribute("serverInfo", request.getServletContext().getServerInfo());
            model.addAttribute("hostName", inet.getHostName());
            model.addAttribute("hostAddress", inet.getHostAddress());
            model.addAttribute("clientIp", request.getRemoteAddr());
            model.addAttribute("clientForwardedIp", request.getHeader("x-forwarded-for"));

            // 모든 헤더 수집
            Map<String, String> headers = new LinkedHashMap<>();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                headers.put(name, request.getHeader(name));
            }
            model.addAttribute("headers", headers);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "test"; // /WEB-INF/jsp/test.jsp 로 이동
    }
}