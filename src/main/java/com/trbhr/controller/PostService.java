package com.trbhr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trhbr.model.Comment;
import com.trhbr.model.Post;
import com.trhbr.model.PostResult;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostService {
    private static final String GET_COMMENTS = "https://jsonplaceholder.typicode.com/comments";
    private static final String GET_POSTS = "https://jsonplaceholder.typicode.com/posts";
    private static final String GET_POST_BY_ID = "https://jsonplaceholder.typicode.com/posts/";

    RestTemplate restTemplate = new RestTemplate();

    public List<Post> getPosts() {
        ResponseEntity<Post[]> response = restTemplate.getForEntity(GET_POSTS, Post[].class);
        List<Post> posts = Arrays.asList(response.getBody());
        return posts;
    }

    public List<Comment> getComments() {
        ResponseEntity<Comment[]> response = restTemplate.getForEntity(GET_COMMENTS, Comment[].class);
        List<Comment> comments = Arrays.asList(response.getBody());
        return comments;
    }

    public Post getPostById(int postId) {
        ResponseEntity<Post> response = restTemplate.getForEntity(GET_POST_BY_ID + postId, Post.class);
        return response.getBody();
    }

    public List<PostResult> getTopPosts() {
        List<PostResult> postResults = new ArrayList<>();
        List<Post> posts = getPosts();
        List<Comment> comments = getComments();
        List<PostResult> finalPostResults = postResults;
        posts.forEach(post -> {
            PostResult postResult = new PostResult(post.getId(), post.getTitle(), post.getBody(), 0);
            int commentCount = comments.stream().filter(comment -> comment.getPostId() == post.getId())
                    .collect(Collectors.toList()).size();
            postResult.setTotal_number_of_comments(commentCount);
            finalPostResults.add(postResult);
        });

        postResults = finalPostResults.stream().
                sorted(Comparator.comparing(PostResult::getTotal_number_of_comments).reversed())
                .collect(Collectors.toList());
        return postResults;
    }

    public List<Comment> getCommentsByFilter(String filter) {
        List<Comment> comments = getComments();
        List<Comment> filteredComments = comments.stream().filter(
                comment -> comment.getBody().contains(filter)
                        || comment.getEmail().contains(filter)
                        || comment.getName().contains(filter)
                        || String.valueOf(comment.getId()).contains(filter)
                        || String.valueOf(comment.getPostId()).contains(filter)).collect(Collectors.toList());
        return filteredComments;
    }
}
