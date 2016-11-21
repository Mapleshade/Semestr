package Services;

import Classes.*;
import DAO.*;
import Utils.MD5;
import Utils.TokenMaker;
import Utils.Verifier;
import Utils.VerifierFactory;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {

    Connection connection;
    Statement statement;
    Verifier verifier;
    SubjectsDAO subDao;
    UsersDAO userDao;
    UserPointsDAO uPDao;
    UserAchiveDAO uADao;
    AchivementsDAO achDao;
    static List<Token> tokens;


    UserServiceImpl() {
        try {
            connection = ConnectionPSQL.getConnection();
            statement = connection.createStatement();
            verifier = VerifierFactory.getVerifier();
            subDao = DAOFactory.getSubjectsDAO();
            userDao = DAOFactory.getUsersDAO();
            achDao = DAOFactory.getAchivementsDAO();
            uADao = DAOFactory.getUserAchiveDAO();
            uPDao = DAOFactory.getUserPointsDAO();
            tokens = new LinkedList<Token>();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subject> getAllSubjects() {
        return subDao.findAll();
    }

    public List<Subject> getAllSubjectsWithUsers(int id) {
        int[][] points = uPDao.find(id);
        List<Subject> subs = new LinkedList<Subject>();
        Iterator<Subject> it = subDao.findAll().iterator();
        Subject cur;
        while(it.hasNext()) {
            cur = it.next();
            for(int i=0; i < points.length; i++) {
                if(points[i][1] == cur.getId()) {
                    cur.setPoint(points[i][2]);
                    break;
                }
            }
            subs.add(cur);
        }
        return subs;
    }

    public List<Achivement> getAllAchivementsWithUsers(int id) {
        int[][] achs = uADao.find(id);
        List<Achivement> ach = new LinkedList<Achivement>();
        Iterator<Achivement> it = achDao.findAll().iterator();
        Achivement cur;
        while(it.hasNext()) {
            cur = it.next();
            for(int i=0; i < achs.length; i++) {
                if(achs[i][1] == cur.getId()) {
                    cur.setChosen(true);
                    break;
                }
            }
            ach.add(cur);
        }
        return ach;
    }

    public void setSubjectsToUser(int userId, List<Subject> subs) {

    }

    public List<Achivement> getAllAchivements() {
        return  achDao.findAll();
    }

    private Subject getSubject(int id) {
        if(verifier.existSubject(id))
            return subDao.find(id);
        else
            return null;
    }

    public boolean isRegistered(String login) {
        if (verifier.existUser(login)){
            return true;
        }
        return false;
    }

    public boolean saveUser(User user) {
        user.setPassword(MD5.getHash(user.getPassword()));
        if (verifier.existUser(user.getLogin())) {
            return false;
        }else {
            userDao.save(user);
            return true;
        }
    }

    public boolean checkPassword(String login, String password) {

        if(verifier.existUser(login)) {
            return userDao.find(login).getPassword().equals(MD5.getHash(password));
        } else
            return false;
    }

    public Cookie generateCookies() {
        Cookie cookie = new Cookie("MSiteCookie", TokenMaker.generateToken());
        return cookie;
    }

    public void SetSubsAndAchiv(int userId, List<Subject> subs, List<Achivement> achs) {
        if(verifier.existUser(userId)) {
            User user = getUser(userId);
            uPDao.delete(userId);
            uADao.delete(userId);
            Iterator<Subject> iter1 = subs.iterator();
            Subject cur;
            while(iter1.hasNext()) {
                cur = iter1.next();
                if(cur.getPoint() > 0 && cur.getPoint() <101)
                uPDao.save(userId, cur.getId(), cur.getPoint());
            }
            Iterator<Achivement> iter2 = achs.iterator();
            Achivement curr;
            while(iter2.hasNext()) {
                curr = iter2.next();
                if(curr.isChosen())
                uADao.save(userId, curr.getId());
            }
        }
    }


    public void saveToken(int userId, String token) {
        tokens.add(new Token(userId, token));
    }

    public User isExistToken(String token) {
        Iterator<Token> iterator = tokens.iterator();
        Token now;
        while(iterator.hasNext()) {
            now = iterator.next();
            if(now.getToken().equals(token)) {
                return getUser(now.getUserId());
            }
        }
        return null;
    }

    public User getUser(int id) {
        if (verifier.existUser(id)) {

            User user = userDao.find(id);
            int[][] extra;
            if(verifier.existUPoints(id)) {
                List<Subject> list = new LinkedList<>();
                extra = uPDao.find(id);
                Subject sub;
                for (int i=0; i<extra.length; i++) {
                    sub = subDao.find(extra[i][1]);
                    sub.setPoint(extra[i][2]);
                    list.add(sub);
                }
                user.setSubjects(list);
            }

            if(verifier.existUAchive(id)) {
                List<Achivement> list = new LinkedList<>();
                extra = uADao.find(id);
                Achivement ach;
                for (int i=0; i<extra.length; i++) {
                    ach = achDao.find(extra[i][1]);
                    list.add(ach);
                }
                user.setAchivements(list);
            }
            return user;
        } else {
            return null;
        }
    }

    public User getUser(String login) {
        if (verifier.existUser(login)) {

            User user = userDao.find(login);
            int[][] extra;
            if(verifier.existUPoints(user.getId())) {
                List<Subject> list = new LinkedList<>();
                extra = uPDao.find(user.getId());
                Subject sub;
                for (int i=0; i<extra.length; i++) {
                    sub = subDao.find(extra[i][1]);
                    sub.setPoint(extra[i][2]);
                    list.add(sub);
                }
                user.setSubjects(list);
            }

            if(verifier.existUAchive(user.getId())) {
                List<Achivement> list = new LinkedList<>();
                extra = uADao.find(user.getId());
                Achivement ach;
                for (int i=0; i<extra.length; i++) {
                    ach = achDao.find(extra[i][1]);
                    list.add(ach);
                }
                user.setAchivements(list);
            }
            return user;
        } else {
            return null;
        }
    }

    public int getAllPoint(int userId) {
        if(verifier.existUPoints(userId)) {
            int[][] extra = uPDao.find(userId);
            int sum = 0;
            for (int i=0; i<extra.length; i++) {
                sum+=extra[i][2];
            }
            return sum;
        } else
            return 0;
    }

    public int getPointInSpec(int userId, int univId, int specId) {
        UniversityService serv = ServiceFactory.getUniversityService();
        Speciality spec = serv.getSpecialityFromUniv(specId, univId);
        User user = getUser(userId);
        University univ = serv.getUniversity(univId, false);
        List<Subject> subs= spec.getSubjects();
        int[][] extra = uPDao.find(userId);
        int sum = 0;
        Iterator<Subject> iter = subs.iterator();
        Subject now;
        while (iter.hasNext()) {
            now = iter.next();
            for(int i=0; i < extra.length; i++) {
                if(now.getId() == extra[i][1]) {
                    sum+=extra[i][2];
                    break;
                }
            }
        }
        if (verifier.existUAchive(userId)) {
            extra = uADao.find(userId);
            List <Achivement> achs = univ.getExtra();
            Iterator<Achivement> it = achs.iterator();
            Achivement cur;
            while (it.hasNext()) {
                cur = it.next();
                for (int i = 0; i < extra.length; i++) {
                    if (cur.getId() == extra[i][1]) {
                        sum += cur.getPoints();
                        break;
                    }
                }
            }

        }
        return sum;
    }


}
