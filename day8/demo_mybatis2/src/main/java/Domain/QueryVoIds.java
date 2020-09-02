package Domain;

import java.util.List;

public class QueryVoIds {
   List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "QueryVoIds{" +
                "ids=" + ids +
                '}';
    }
}
