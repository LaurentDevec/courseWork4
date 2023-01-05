package mavenCourseWork.app;

import lombok.ToString;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Arrays;
import java.util.Objects;

@ToString
public class Group {

        @Id
        private Integer id;
        @ManyToOne(fetch = FetchType.LAZY)
        private Mountain mountain;
        private Alpinist[] alpinists;


        public Group(Mountain mountain, int alpinistCount) {
            this.mountain = Objects.requireNonNull(mountain, "mountain");
            alpinists = new Alpinist[alpinistCount];
        }

        public  void addAlpinist(Alpinist alpinist) {
            Objects.requireNonNull(alpinist, "climber");
            for (int i = 0; i < alpinists.length; i++) {
                if (alpinists == null) {
                    alpinists[i] = alpinist;
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
                    ", alpinists=" + Arrays.toString(alpinists) +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Group)) return false;
            Group that = (Group) o;
            return Objects.equals(mountain, that.mountain) && Arrays.equals(alpinists, that.alpinists);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(mountain);
            result = 31 * result + Arrays.hashCode(alpinists);
            return result;
        }

        @Override
        public Group clone() {
            Group copy = new Group(mountain, alpinists.length);
            copy.alpinists = alpinists.clone();
            return copy;
        }
    }

