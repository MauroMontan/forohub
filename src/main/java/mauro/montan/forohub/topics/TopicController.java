package mauro.montan.forohub.topics;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import mauro.montan.forohub.users.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("topics")
public class TopicController {

    private final TopicService topicService;
    private  final UserService userService;
    public TopicController(TopicService topicService, UserService userService) {
        this.topicService = topicService;
        this.userService = userService;

    }
    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<TopicResponse>> getAllTopics() {
        return ResponseEntity.ok(topicService.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponse> createTopic(@RequestBody CreationTopicDto topic , UriComponentsBuilder ucb) {

        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        var currentUser = Optional.ofNullable(userService.findByRawEmail(email));

        if (currentUser.isEmpty()) {
            throw new RuntimeException("user is not registered");
        }

        var newTopic = topicService.createTopic(topic,currentUser.get());

      URI topicLocation = ucb
                .path("topics/{id}")
                .buildAndExpand(newTopic.id())
                .toUri();

      return ResponseEntity.created(topicLocation).body(newTopic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> findById( @PathVariable("id") Long id ) {
        return ResponseEntity.ok(topicService.findById(id));
    }
}
