package mycode.repository;

import mycode.exceprtion.NotFoundException;
import mycode.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//класс хранения данных
public class PostRepository {
    protected ConcurrentHashMap<Long, String> map = new ConcurrentHashMap();
    //вывод всех мапы
    public ConcurrentHashMap<Long, String> all() {
        return map;//Collections.emptyList(); //заглушка, возвращает пустой список
    }

    public Optional<String> getById(long id) {
        //return Optional.ofNullable(map.get(id));
        if(map.containsKey(id)){
            return Optional.of(map.get(id));
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        if(post.getId()==0){
            post.setId((long)Math.floor(Math.random()*10000));
            map.put(post.getId(), post.getContent());
        }else if(post.getId()!=0){
            if(map.containsKey(post.getId())){
                map.remove(post.getId());
                map.put(post.getId(), post.getContent());
            }else{
                throw new NotFoundException("Post not saved {id:" + post.getId() + "}");
            }
        }
        return post;
    }

    public void removeById(long id) {
        if(map.containsKey(id)){
            map.remove(id);
        }else{
            throw new NotFoundException("Post not found {id:" + id + "}");
        }
    }

}
