package mauro.montan.forohub.topics;


import jakarta.persistence.*;
import mauro.montan.forohub.users.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;


@Entity
@Table(name = "topics")
public class Topic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String message;

    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;

    private String status;

    @ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    private User author;

    public Topic(){}

    public Topic(CreationTopicDto topic, User currentUser){
        this.status = topic.status();
        this.title = topic.title();
        this.message = topic.message();
        this.author = currentUser;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setAuthorFromUserDetails(UserDetails author) {
        var newAuthor = new User();
        newAuthor.setEmail(author.getUsername());
        this.author = newAuthor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TopicResponse toResponse() {
        return new TopicResponse(id,getAuthor().toProfileResponse(),title,message,status);
    }
}
