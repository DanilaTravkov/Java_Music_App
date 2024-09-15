package controller;

import model.Post;
import service.PostService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PostController {
    private final PostService postService;

    public PostController(Connection connection) {
        this.postService = new PostService(connection);
    }

    public List<Post> listPosts() {
        try {
            return postService.listPosts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public Post getPost(int id) {
        try {
            return postService.getPost(id);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
