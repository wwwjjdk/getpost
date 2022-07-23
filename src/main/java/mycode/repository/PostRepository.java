package mycode.repository;

import mycode.exceprtion.NotFoundException;
import mycode.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

//класс хранения данных
public class PostRepository {
    protected ConcurrentHashMap<Long, Post> map = new ConcurrentHashMap();
    private AtomicLong atomicLong = new AtomicLong();

    public Collection<Post> all() {
        return map.values();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(map.get(id));
    }

    public Post save(Post post) {
        if(post.getId() == 0){
          long atomicId = atomicLong.incrementAndGet();
          post.setId(atomicId);
          map.put(post.getId(), post);
        }else{
            if(map.containsKey(post.getId())){
                map.remove(post.getId());
                map.put(post.getId(), post);
            }else{
                throw new NotFoundException();
            }

        }
        return post;
    }

    public boolean removeById(long id) {
        if (map.containsKey(id)) {
            map.remove(id);
            return true;
        } else {
            return false;
        }
    }

}
