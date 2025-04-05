package ca.sheridancollege.suranim.controller;

import ca.sheridancollege.suranim.model.DiscussionThread;
import ca.sheridancollege.suranim.model.Post;
import ca.sheridancollege.suranim.repository.DiscussionThreadRepository;
import ca.sheridancollege.suranim.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class ForumController {

    @Autowired private DiscussionThreadRepository threadRepo;
    @Autowired private PostRepository postRepo;

    @GetMapping("/")
    public String home() {
        return "redirect:/threads";
    }

    @GetMapping("/threads")
    public String viewThreads(Model model) {
        model.addAttribute("threads", threadRepo.findAll());
        return "threads";
    }

    @PostMapping("/threads")
    public String addThread(@RequestParam String title) {
        DiscussionThread thread = new DiscussionThread();
        thread.setTitle(title);
        threadRepo.save(thread);
        return "redirect:/threads";
    }

    @GetMapping("/threads/{id}")
    public String viewPosts(@PathVariable int id, Model model) {
        DiscussionThread thread = threadRepo.findById(id).orElse(null);
        model.addAttribute("thread", thread);
        model.addAttribute("posts", postRepo.findByThreadId(id));
        return "post";
    }

    @PostMapping("/threads/{id}/post")
    public String postReply(@PathVariable int id, @RequestParam String content, Principal principal) {
        DiscussionThread thread = threadRepo.findById(id).orElse(null);
        if (thread == null) {
            return "redirect:/threads";
        }

        Post post = new Post();
        post.setThread(thread);
        post.setContent(content);
        post.setAuthor(principal.getName());
        post.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        postRepo.save(post);
        return "redirect:/threads/" + id;
    }
}
