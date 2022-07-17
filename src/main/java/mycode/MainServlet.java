package mycode;

import mycode.controller.PostController;
import mycode.repository.PostRepository;
import mycode.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private PostController controller;

    @Override
    public void init() {
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    // protected private public
    protected void service(HttpServletRequest req, HttpServletResponse res) {
        /*res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        res.getWriter().print("h3110 4m my m1nd");*/
        try {
            final var path = req.getRequestURI(); //uri url urn
            final var method = req.getMethod(); //get post etc

            if (method.equals("GET") && path.equals("/api/posts")) {
                controller.all(res);
                return;
            }
            if (method.equals("GET") && path.matches("/api/posts/\\d+")) {
                // easy way
                final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
                controller.getById(id, res);
                return;
            }
            if (method.equals("POST") && path.equals("/api/posts")) {
                controller.save(req.getReader(), res);
                return;
            }
            if (method.equals("DELETE") && path.matches("/api/posts/\\d+")) {
                // easy way
                final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
                controller.removeById(id, res);
                return;
            }
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
