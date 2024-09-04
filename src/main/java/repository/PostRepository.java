package repository;

import model.Post;

import java.util.List;

public class PostRepository implements CrudDTO<Post> {

    @Override
    public List<Post> list() {
        return List.of();
    }

    @Override
    public Post get() {
        return null;
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
