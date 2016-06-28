package com.theironyard.controllers;

import com.theironyard.entities.Photo;
import com.theironyard.entities.User;
import com.theironyard.services.PhotoRepository;
import com.theironyard.services.UserRepository;
import org.aspectj.weaver.patterns.IfPointcut;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;

/**
 * Created by Erik on 6/28/16.
 */
@Controller
public class IronGramController {

    @Autowired
    UserRepository users;

    @Autowired
    PhotoRepository photos;

    @PostConstruct
    public void init() throws SQLException {
        Server.createWebServer().start();
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, String recipient, Long deleteTime, boolean makePublic, HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User sender = users.findFirstByName(username);
        User rec = users.findFirstByName(recipient);

        if(sender == null || rec == null) {
            throw new Exception("Can't find sender or receiver");
        }

        if (file.getContentType().contains("image")) {

            File dir = new File("public/photos");
            dir.mkdirs();
            File photoFile = File.createTempFile("photo", file.getOriginalFilename(), dir);
            FileOutputStream fos = new FileOutputStream(photoFile);
            fos.write(file.getBytes());
            Photo photo = new Photo(sender, rec, photoFile.getName(), deleteTime, makePublic);
            photos.save(photo);

            System.out.println("Upload Successful");

        }
        else {
            throw new Exception("Invalid File Type");
//            System.out.println("Wrong File Type");
        }

        return "redirect:/";
    }

}
