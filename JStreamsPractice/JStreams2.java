package JStreamsPractice;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JStreams2 {
    public static void main(String[] args) {
        List<Ipl> ipl = Arrays.asList(new Ipl("CSK", "M.S Dhoni", 7, 41,246),
                new Ipl("MI", "Rohit Sharma", 45, 36,235),
                new Ipl("RCB", "Virat Kohli", 18, 34,263),
                new Ipl("GT", "Hardik Pandya", 33,29,214),
                new Ipl("KKR", "Nitish Rana", 27,29,245),
                new Ipl("CSK", "M.S Dhoni", 7, 41,246),
                new Ipl("RCB", "Virat Kohli", 18, 34,263)
            );
        // Filter
        List<Ipl> agt35 = ipl.stream().filter(a -> a.getAge() > 35).collect(Collectors.toList());
        System.out.println("\n---FILTER (AGE>35)---");
        agt35.stream().forEach(System.out::println);

        // map() is used to convert the Stream of one type to another.
        System.out.println("\n---MAP (SET OF SCORES)---");
        Set<Integer> c1=ipl.stream().map(Ipl::getScore).collect(Collectors.toSet());
        System.out.println(c1);

        // flatmap()-convert a Stream of Stream into a list of values.
        // flatMap() = Flattening (flat)+ mapping (map)  
        System.out.println("\n---FLAT MAP (CSK + MI + RCB)---");
        String[][] players=new String[][] {{"M.S Dhoni","Jadeja","Suresh Raina","Deepak Chahar"},
                                           {"Ishan Kishan","Rohit Sharma","Jasprit Bumrah"},
                                           {"Virat Kohli","Dinesh Karthik"}};
        Stream<String[]> data=Arrays.stream(players);
        Stream<String> stringStream=data.flatMap(fm->Arrays.stream(fm));
        System.out.println(stringStream.collect(Collectors.toSet()));

        // Sorted
        System.out.println("\n---SORT (AGE)---");
        List<Ipl> sortedList=ipl.stream().sorted(Comparator.comparing(Ipl::getAge)).collect(Collectors.toList());
        sortedList.forEach(System.out::println);

        // Peek -alter the inner state of an element
        System.out.println("\n---PEEK---");
        List<String> teams=Arrays.asList("csk", "rcb", "mi", "kkr","gt","lsg","srh","pbks","dc","rr");
        teams.stream().filter(e -> e.length() ==2)
        .peek(t -> System.out.println("Filtered teams: " +t ))
        .map(String::toUpperCase)
        .peek(team -> System.out.println("Mapped teams: " + team))
        .collect(Collectors.toSet());

        // Distinct 
        System.out.println("\n---DISTINCT (Captain name)---");
        List<String> distinctName=ipl.stream().map(Ipl::getCaptian).distinct().collect(Collectors.toList());
        distinctName.stream().forEach(System.out::println);

        System.out.println("\n---COUNT---"+ipl.stream().count());

        // Limit -returns a stream not longer than the requested size
        System.out.println("\n---LIMIT (size 2)---");
        ipl.stream().limit(2).forEach(System.out::println);

        // Skip-discards the first n elements of a stream
        System.out.println("\n---SKIP (skips first 3 elements)---");
        ipl.stream().skip(3).forEach(System.out::println);

        // Maximum score
        Optional<Ipl> maxScore = ipl.stream().max(Comparator.comparing(Ipl::getScore));
        System.out.println("\n---MAX SCORE---");
        System.out.println(maxScore);
        

        // Minimum order name
        System.out.println("\n---MIN (APLHABETIC ORDER MIN CAPTAIN)---");
        ipl.stream().min(Comparator.comparing(Ipl::getCaptian))
               .ifPresent(m->System.out.println(m));
        //  System.out.println(minCaptain);

        // count 
        System.out.println("\n--COUNT ENDING WITH 'a'---");
        long count = ipl.stream().filter(c->c.getCaptian().endsWith("a")).count();
        System.out.println(count);

        // ForEach & ForEachOrdered (encounter order of the stream)
        System.out.println("\n---ForEach---");
        Stream.of("Dhoni","Virat","Hardik","Shubhman").parallel().forEach(e->System.out.println(e));
        System.out.println("\n---ForEachOrdered---");        
        Stream.of("Dhoni","Virat","Hardik","Shubhman").parallel().forEachOrdered(e->System.out.println(e));

        // toArray
        System.out.println("\n---TO ARRAY---");
        Ipl[] iplPlayer=ipl.stream()
        .filter(a->a.getScore() >250)
        // .map(Ipl::getScore)
        // .distinct()
        // .collect(Collectors.toSet())
        .toArray(Ipl[]::new);
        System.out.println(Arrays.toString(iplPlayer));

        // reduce
        System.out.println("\n---REDUCE (Total cost of 3 items)---");
        double[] costOfItems={150.00,200.00,95.50};
        double sumTotal=Arrays.stream(costOfItems).reduce(0, (a,b)->b+a);
        System.out.println(sumTotal);

        // AnyMatch
        System.out.println("\n---ANYMATCH---");
        boolean b1=ipl.stream().anyMatch(b->b.getScore()>245 );
        System.out.println(b1);

        // AllMatch
        System.out.println("\n---ALLMATCH---");
        boolean b2=ipl.stream().allMatch(s->s.getScore()>200);
        System.out.println(b2);

        // NoneMatch
        System.out.println("\n---NONE MATCH---");
        boolean b3=ipl.stream().noneMatch(n->n.getCaptian().startsWith("M"));
        System.out.println(b3);

        // FindFirst
        System.out.println("\n---FIND FIRST---");
        Optional<Ipl> findTeam = ipl.stream().filter(t->t.getTeam().contains("RCB")).findFirst();
        System.out.println(findTeam);

        // FindAny
        System.out.println("\n---FIND ANY---");
        Optional<Ipl> findJersey = ipl.stream().filter(j->j.getJersey()==7).findAny();
        System.out.println(findJersey);

        // Stream Builder
        System.out.println("\n---STREAM BUILDER---");
        Stream.Builder<String> buildPlayers=Stream.builder();
        buildPlayers.accept("Shubhman Gill");
        buildPlayers.accept("Ruturaj Gaikwad");
        buildPlayers.accept("Surya Kumar Yadav");
        buildPlayers.accept("Shivam Dube");
        buildPlayers.add("Dinesh Karthik");
        Stream<String> bp=buildPlayers.build();
        // buildPlayers.accept("K L Rahul");
        // bp=buildPlayers.build();
        bp.forEach(System.out::println);
    }
    
}
// parallel stream
// mapto ...
// average

class Ipl {
    String team;
    String captain;
    Integer jerseyNo;
    Integer age;
    Integer score;

    Ipl(String team, String captain, Integer jerseyNo, Integer age,Integer score) {
        this.team = team;
        this.captain = captain;
        this.jerseyNo = jerseyNo;
        this.age = age;
        this.score=score;
    }

    String getTeam() {
        return team;
    }

    void setTeam(String team) {
        this.team = team;
    }

    String getCaptian() {
        return captain;
    }

    void setCaptain(String captain) {
        this.captain = captain;
    }

    Integer getJersey() {
        return jerseyNo;

    }

    void setJersey(Integer jerseyNo) {
        this.jerseyNo = jerseyNo;
    }

    Integer getAge() {
        return age;
    }

    void setAge(Integer age) {
        this.age = age;
    }
Integer getScore(){
    return score;
}
void setScore(Integer score){
    this.score=score;
}
    public String toString() {
        return "TEAM: " + team + "  CAPTAIN: " + captain + " JERSEY NO: " + jerseyNo + " AGE: " + age+" SCORE: "+score;
    }
}