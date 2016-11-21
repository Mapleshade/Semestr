import Services.ServiceFactory;
import Services.UniversityService;
import Services.UniversityServiceImpl;

/**
 * Created by Elina on 20.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        UniversityService serv = ServiceFactory.getUniversityService();
        System.out.println(serv.getUniversity(1,true));
    }
}
