package service;

import model.Post;
import model.User;
import repository.PostRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PostService {

    private final PostRepository postRepository;

    public PostService(Connection connection) {
        this.postRepository = new PostRepository(connection);
    }

    public List<Post> listPosts() throws SQLException {
        return postRepository.list();
    }

    public Post getPost(int id) throws SQLException {
        return postRepository.get(id);
    }

    public void createPost(Post post) throws SQLException {
        postRepository.create(post);
    }

    public void updatePost(Post post, int id) throws SQLException {
        postRepository.update(post, id);
    }

    public void deletePost(int id) throws SQLException {
        postRepository.delete(id);
    }
}
