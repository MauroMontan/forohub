package mauro.montan.forohub.topics;

import mauro.montan.forohub.users.UserProfileResponse;

public record TopicResponse(Long id ,UserProfileResponse user,String title, String message,String status) {
}
