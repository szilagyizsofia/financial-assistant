package coinster.security;

import coinster.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class AuthenticatedUser {
    private User user;

    public AuthenticatedUser() {
    }

    public AuthenticatedUser(User user) {
        this.user = user;
    }

    void setUser(User user) {
        this.user = user;
    }
}
