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

    public ConcurrentHashMap<Long, String> all() {
        return repository.all();
    }

    public String getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
    public void searchAndChange(Post post){
        if(post.getId() ==0){
            post.setId((long)Math.floor(Math.random()*10000));
        }else if(post.getId() !=0){
          if(repository.all().containsKey(post.getId())){
             repository.removeById(post.getId());
          }
        }
    }
}
