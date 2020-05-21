package com.trbhr.controller;

import com.trhbr.model.Comment;
import com.trhbr.model.Filter;
import com.trhbr.model.PostResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.sql.ResultSet;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping(value = "/getTopPosts", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity getTopPosts() {
        List<PostResult> postResults = postService.getTopPosts();
        return new ResponseEntity(postResults, HttpStatus.OK);
    }

    @PostMapping(value = "/getCommentsByFilter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity getCommentsByFilter(@RequestBody Filter filter) {
        List<Comment> filteredComments = postService.getCommentsByFilter(filter.getFilter());
        return new ResponseEntity(filteredComments, HttpStatus.OK);
    }
}
