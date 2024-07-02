package mauro.montan.forohub;

import mauro.montan.forohub.topics.Topic;
import mauro.montan.forohub.topics.TopicRepository;
import mauro.montan.forohub.users.User;
import mauro.montan.forohub.users.UserAlreadyExistException;
import mauro.montan.forohub.users.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForohubApplication implements CommandLineRunner {

	private final TopicRepository topicRepository;
	public ForohubApplication(TopicRepository topicRepository){
		this.topicRepository = topicRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ForohubApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		topicRepository.findAll().forEach(topic -> {
			System.out.println(topic.getAuthor().toProfileResponse());
		});
	}
}
