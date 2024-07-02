package mauro.montan.forohub.topics;

import mauro.montan.forohub.users.User;
import mauro.montan.forohub.users.UserProfileResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService( TopicRepository topicRepository ) {
        this.topicRepository = topicRepository;
    }

    public TopicResponse createTopic( CreationTopicDto topic, User currentUser ) {
        var newTopic = topicRepository.save(new Topic(topic,currentUser));
        return newTopic.toResponse();
    }
    public List<TopicResponse> findAll() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream().map(Topic::toResponse).toList();
    }

    public Topic findById(Long id) {
        var newTopic = topicRepository.findById(id);

        if(newTopic.isEmpty()) {
            throw new RuntimeException("el usuario no existe");
        }
        return newTopic.get();
    }

}
