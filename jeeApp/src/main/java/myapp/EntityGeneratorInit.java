package myapp;

import myapp.dao.IDirectoryDao;
import myapp.model.Group;
import myapp.model.Person;
import myapp.model.XUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class EntityGeneratorInit {

    private IDirectoryDao dao;
    private static EntityGeneratorInit entityGeneratorInit;
    private List<Group> groups = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();

    private EntityGeneratorInit(IDirectoryDao dao){
        this.dao = dao;
    }

    public void init(){
        Group group;

        group = new Group("Group 1");
        groups.add(group);
        dao.addGroup(group);

        group = new Group("Group 2");
        groups.add(group);
        dao.addGroup(group);

        group = new Group("Group 3");
        groups.add(group);
        dao.addGroup(group);

        group = new Group("Group 4");
        groups.add(group);
        dao.addGroup(group);

        try {
            Person person;

            person = new Person("Loïc", "Fery", "password", "", "LoicFery@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("27-03-1999"));
            persons.add(person);

            person = new Person("Bastien", "Feraud", "password", "www.web.com", "BastienFeraud@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("06-04-2000"));
            persons.add(person);

            person = new Person("Alain", "Dal-Molin", "password", "", "AlainDalMolin@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("15-10-1980"));
            persons.add(person);

            person = new Person("Amine", "Hamri", "password", "", "AmineHamri@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1988"));
            persons.add(person);

            person = new Person("Desire", "Breton", "password", "", "DesireBreton@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("22-04-1947"));
            persons.add(person);

            person = new Person("Aurore", "Charpie", "password", "InventionSpecialist.fr", "AuroreCharpie@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("20-03-1981"));
            persons.add(person);

            person = new Person("Odo", "Dupuis", "password", "TechCelebrity.fr", "OdoDupuis@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("17-06-1971"));
            persons.add(person);

            person = new Person("Raoul", "Laframboise", "password", "Alizat.fr", "RaoulLaframboise@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("26-05-1983"));
            persons.add(person);

            person = new Person("Océane", "Sauriol", "password", "TalentMenu.fr", "OceaneSauriol@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("04-02-1993"));
            persons.add(person);

            person = new Person("Pascaline", "Hughes", "password", "TelevisionSplitters.fr", "PascalineHughes@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("10-08-1968"));
            persons.add(person);

            person = new Person("Clothilde", "Simard", "password", "SoapCandy.fr", "ClothildeSimard@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("07-10-1992"));
            persons.add(person);

            person = new Person("Archaimbau", "Lussier", "password", "ScooterSpecialists.fr", "ArchaimbauLussier@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("04-06-1995"));
            persons.add(person);

            person = new Person("Cammile", "Sauvvé", "password", "PreTree.fr", "CammileSauve@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("16-06-1939"));
            persons.add(person);

            person = new Person("Tearlach", "Tisserand", "password", "KnoxvilleWebhost.fr", "TearlachTisserand@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("18-03-1971"));
            persons.add(person);

            person = new Person("Javier", "Ménard", "password", "EvilSister.fr", "JavierMenard@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("17-11-1964"));
            persons.add(person);

            person = new Person("Fantina", "Morel", "password", "LacrosseRugby.fr", "FantinaMorel@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("04-12-1960"));
            persons.add(person);

            person = new Person("Vachel", "Dupont", "password", "BarRank.fr", "VachelDupont@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("16-07-1998"));
            persons.add(person);

            person = new Person("Victoire", "Rivard", "password", "ProspectingTeam.fr", "VictoireRivard@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("19-04-1971"));
            persons.add(person);

            person = new Person("Denise", "Chauvin", "password", "NewsTutorial.fr", "DeniseChauvin@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("02-10-1977"));
            persons.add(person);

            person = new Person("Marine", "Angelier", "password", "DanceSync.fr", "MarineLAngelier@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("11-05-1955"));
            persons.add(person);

            person = new Person("Grégoire", "Brousseau", "password", "FindSkins.fr", "GregoireBrousseau@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("08-07-2001"));
            persons.add(person);

            person = new Person("Iven", "Brisette", "password", "PokerClues.fr", "IvenBrisette@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("18-01-1947"));
            persons.add(person);

            person = new Person("Cinderella", "Beauchesne", "password", "ReadyHD.fr", "CinderellaBeauchesne@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("01-08-1954"));
            persons.add(person);

            person = new Person("Trinette", "Frappier", "password", "DevelopFuel.fr", "TrinetteFrappier@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("01-06-1988"));
            persons.add(person);

            person = new Person("Paigne", "Cantin", "password", "FreelanceAgreement.fr", "PaigeCantin@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("26-08-1949"));
            persons.add(person);

            person = new Person("Chappell", "Turcotte", "password", "MakeJam.fr", "ChappellTurcotte@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("14-02-1966"));
            persons.add(person);

            person = new Person("Claudette", "Laforest", "password", "MicroVerify.fr", "ClaudetteLaforest@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("02-06-1944"));
            persons.add(person);

            person = new Person("Frédérique", "Vincent", "password", "SpaRules.fr", "FrederiqueVincent@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("13-09-1987"));
            persons.add(person);

            person = new Person("Latitia", "Gagnon", "password", "ChatTickets.fr", "LaetitiaGagnon@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("19-05-1996"));
            persons.add(person);

            person = new Person("Raine", "Tougas", "password", "RugSafe.fr", "RainaTougas@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("06-07-1981"));
            persons.add(person);

            person = new Person("Mandel", "Courtois", "password", "MaleWeekend.fr", "MandelCourtois@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("29-01-1946"));
            persons.add(person);

            person = new Person("Ila", "Simon", "password", "DashboardTalk.fr", "IlaSimon@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("20-06-1980"));
            persons.add(person);

            person = new Person("Sébastien", "Poirier", "password", "ReunionBusiness.fr", "SebastienPoirier@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("07-08-1952"));
            persons.add(person);

            person = new Person("Ranger", "Leclair", "password", "RushResource.fr", "RangerLeclair@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("05-11-1980"));
            persons.add(person);

            person = new Person("Alexandre", "Laramée", "password", "MaternityMoments.fr", "AlexandreLaramee@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("10-03-1951"));
            persons.add(person);

            person = new Person("Brunella", "Brousseau", "password", "BangNames.fr", "BrunellaBrousseau@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("03-11-1970"));
            persons.add(person);

            person = new Person("Latimer", "Charette", "password", "ValleyShows.fr", "LatimerCharette@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("24-07-1959"));
            persons.add(person);

            person = new Person("Lotye", "Therriault", "password", "ParentingCounselor.fr", "LotyeTherriault@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("21-10-1977"));
            persons.add(person);

            person = new Person("Hamilton", "Bisson", "password", "RobotMarketing.fr", "HamiltonBisson@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("20-06-1972"));
            persons.add(person);

            person = new Person("Maurelle", "Montminy", "password", "SocialChronicle.fr", "MaurelleMontminy@email.com", new SimpleDateFormat("dd-MM-yyyy").parse("26-10-1940"));
            persons.add(person);

        }
        catch (ParseException parseException){
            parseException.printStackTrace();
        }

        for(Person person : persons){
            int random = (int)(Math.random() * groups.size());
            dao.findGroup(groups.get(random).getId()).addPerson(person);
            dao.addPerson(person);
            dao.addXUser(new XUser(person.getMail(),new BCryptPasswordEncoder().encode(person.getPassword()), Set.of("USER")));
        }
    }

    public static EntityGeneratorInit getEntityGeneratorInit(IDirectoryDao dao){
        if(entityGeneratorInit == null){
            entityGeneratorInit = new EntityGeneratorInit(dao);
        }
        return entityGeneratorInit;
    }
}
