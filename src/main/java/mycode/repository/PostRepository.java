package mycode.repository;

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
        return Optional.ofNullable(map.get(id));
    }

    public Post save(Post post) {
        map.put(post.getId(), post.getContent());
        return post;
    }

    public void removeById(long id) {
        map.remove(id);
    }

}
