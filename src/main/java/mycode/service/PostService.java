package mycode.service;

import mycode.exceprtion.NotFoundException;
import mycode.model.Post;
import mycode.repository.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//класс бизнес-логики
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Collection<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public boolean removeById(long id) {
        return repository.removeById(id);
    }

}
