package Services;

import Classes.Achivement;
import Classes.Subject;
import Classes.User;

import javax.servlet.http.Cookie;
import java.util.List;

public interface UserService {

    List<Subject> getAllSubjects();

    List<Subject> getAllSubjectsWithUsers(int id);

    List<Achivement> getAllAchivements();

    List<Achivement> getAllAchivementsWithUsers(int id);

    void SetSubsAndAchiv(int userId, List<Subject> subs, List<Achivement> achs);

    boolean isRegistered(String login);

    boolean saveUser(User user);

    boolean checkPassword(String login, String password);

    Cookie generateCookies();

    void saveToken(int userId, String token);

    User isExistToken(String token);

    User getUser(int id);

    User getUser(String login);

    int getAllPoint(int userId);

    int getPointInSpec(int userId, int univId, int specId);
}
