package mavenCourseWork.app;

import lombok.ToString;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.*;

@ToString
public class Group {

        @Id
        private Integer id;
        @ManyToOne(fetch = FetchType.LAZY)
        private Mountain mountain;
        @ManyToMany(fetch = FetchType.LAZY)
        private List alpinists;


        public Group(Mountain mountain, int alpinistCount) {
            this.mountain = Objects.requireNonNull(mountain, "mountain");
            alpinists = Collections.singletonList(new Alpinist[alpinistCount]);
        }

        public  void addAlpinist(Alpinist alpinist) {
            Objects.requireNonNull(alpinist, "alpinist");
            for (int i = 0; i < alpinists.size(); i++) {
                if (alpinists == null) {
                    alpinists.set(i, alpinist);
                    return; // в воид методах для заершения работы метода
                }
            }
            System.out.println("Мест нет" +
                    "" +
                    "");
        }

        @Override
        public String toString() {
            return "Group{" +
                    "mountain=" + mountain +
                    ", alpinists=" + alpinists +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Group)) return false;
            Group that = (Group) o;
            return Objects.equals(mountain, that.mountain) && alpinists.equals(alpinists);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(mountain);
            result = 31 * result + Arrays.hashCode(new List[]{alpinists});
            return result;
        }

        @Override
        public Group clone() {
            Group copy = new Group(mountain, alpinists.size());

            List<String> cloned_alpinists = new ArrayList<String>(alpinists);

            return copy;
        }
    }

