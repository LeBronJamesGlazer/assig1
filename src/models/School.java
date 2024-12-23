import java.util.ArrayList;
import java.util.List;

public class School {
    private List<Person> members;

    public School() {
        this.members = new ArrayList<>();
    }

    public void addMember(Person person) {
        members.add(person);
    }

    @Override
    public String toString() {
        StringBuilder schoolInfo = new StringBuilder();
        for (Person member : members) {
            schoolInfo.append(member.toString()).append("\n");
        }
        return schoolInfo.toString();
    }

    public void sortMembersBySurname() {
        members.sort((p1, p2) -> p1.toString().split(" ")[2].compareTo(p2.toString().split(" ")[2]));
    }
}