package pi.project.languageApp.controller;

import algebra.hr.bll.blModels.BLForumComment;
import algebra.hr.bll.blModels.BLForumPost;
import algebra.hr.bll.serviceImplementation.ForumCommentServiceImpl;
import algebra.hr.bll.serviceImplementation.ForumPostServiceImpl;
import algebra.hr.bll.serviceImplementation.UserServiceImpl;
import algebra.hr.dal.entity.ForumComment;
import algebra.hr.dal.entity.ForumPost;
import algebra.hr.dal.entity.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("forums")
public class ForumController {
    private final ForumPostServiceImpl _forumPostService;
    private final ForumCommentServiceImpl _forumCommentService;
    private final UserServiceImpl _userService;

    public ForumController(ForumPostServiceImpl forumPostService, ForumCommentServiceImpl forumCommentService, UserServiceImpl userService) {
        _forumPostService = forumPostService;
        _forumCommentService = forumCommentService;
        _userService = userService;
    }

    @GetMapping("/list")
    public String listForumPosts(Model theModel) {

        List<ForumPost> posts = _forumPostService.findAll();

        // add to the spring model
        theModel.addAttribute("posts", posts);

        return "forums/list-forums";
    }

    @GetMapping("/showFormForAddPost")
    public String showFormForAddForum(Model theModel){

        //create the model attribute to bind form data
        BLForumPost post =  new BLForumPost();
        theModel.addAttribute("post", post);

        return "forums/postForum-form";
    }

    @GetMapping("/showFormForUpdatePost")
    public String showFormForUpdateForumPost(@RequestParam("forumPostID") int theId, Model theModel){
        //get the forum post from the service
        ForumPost forumPost = _forumPostService.findById(theId);

        BLForumPost post = new BLForumPost();

        post.setForumPostID(forumPost.getForumPostID());
        post.setContent(forumPost.getContent());

        theModel.addAttribute("post", post);

        return "forums/postForum-form";
    }


    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("post") BLForumPost forumPost, BindingResult bindingResult, Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "customTasks/customTask-form";
        }

        ForumPost forumPostToAdd = new ForumPost();

        if (forumPost.getForumPostID() > 0) {
            forumPostToAdd = _forumPostService.findById(forumPost.getForumPostID());
            forumPostToAdd.setContent(forumPost.getContent());
            forumPostToAdd.setTimeStamp(LocalDateTime.now());
        } else {
            Set<User> receivers = new HashSet<>();
            List<User> allUsers = _userService.findAll();
            receivers.addAll(allUsers);
            forumPostToAdd = new ForumPost(receivers, forumPost.getContent(), LocalDateTime.now());
        }

        _forumPostService.save(forumPostToAdd);

        return "redirect:/forums/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("forumPostID") int theId){
        //first delete all comments
        List<ForumComment> comments = _forumCommentService.findByForumPostId(theId);

        for (ForumComment comment : comments){
            _forumCommentService.deleteById(comment.getForumCommentID());
        }

        //delete the post
        _forumPostService.deleteById(theId);

        //redirect to the /xxxx/list
        return "redirect:/forums/list";
    }

    //DISPLAY FORUM POSTS AND COMMENTS
    @GetMapping("/showPost")
    public String showPost(@RequestParam("forumPostID") int theId, Model theModel){
        // Get the forum post from the service
        ForumPost forumPost = _forumPostService.findById(theId);

        BLForumPost post = new BLForumPost();
        post.setForumPostID(forumPost.getForumPostID());
        post.setContent(forumPost.getContent());
        post.setReceivers(forumPost.getReceivers().stream()
                .map(User::getUsername)
                .collect(Collectors.toSet()));
        post.setTimeStamp(forumPost.getTimeStamp());

        List<ForumComment> commentsDB = _forumCommentService.findByForumPostId(theId);

        //would be easier if i used a mapper
        List<BLForumComment> comments = new ArrayList<>();
        for (ForumComment comment : commentsDB){
            BLForumComment blComment = new BLForumComment();
            blComment.setForumCommentID(comment.getForumCommentID());
            blComment.setForumPost(comment.getForumPost().getForumPostID());
            blComment.setUsernamePoster(comment.getUsernamePoster().getUsername());
            blComment.setContent(comment.getContent());
            blComment.setTimeStamp(comment.getTimeStamp());

            comments.add(blComment);
        }

        theModel.addAttribute("post", post);
        theModel.addAttribute("comments", comments);

        return "forums/postForum-display";
    }

    @PostMapping("/addComment")
    public String addComment(@RequestParam("postId") int postId, @RequestParam("content") String content, Principal principal){
        // Find the forum post
        ForumPost forumPost = _forumPostService.findById(postId);

        // Get the username of the currently logged-in user
        String username = principal.getName();

        User user = _userService.findByUsername(username);

        ForumComment forumComment = new ForumComment();
        forumComment.setForumPost(forumPost);
        forumComment.setUsernamePoster(user);
        forumComment.setContent(content);
        forumComment.setTimeStamp(LocalDateTime.now());

        _forumCommentService.save(forumComment);

        // Redirect to the post view page
        return "redirect:/forums/showPost?forumPostID=" + postId;
    }
}
