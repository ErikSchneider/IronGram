package com.theironyard.controllers;

import com.theironyard.services.PhotoRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Erik on 6/28/16.
 */
@RestController
public class IronGramRestController {

    @Autowired
    UserRepository users;

    @Autowired
    PhotoRepository photos;
}
