package attendance.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Repository {
    private final ArrayList<Tuple> database = new ArrayList<>();

    public void add(Tuple tuple) {
        database.add(tuple);
    }

    public void addAll(List<Tuple> tuples) {
        database.addAll(tuples);
    }

    public void modify(Tuple tuple) {
        Tuple target = database.stream()
                .filter(item -> Objects.equals(item.getName(), tuple.getName()) && Objects.equals(item.getDate(),
                        tuple.getDate()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("404, Not Found."));
        target.setTime(tuple.getTime());
    }

    public Tuple findOne(String name, String date) {
        return database.stream()
                .filter(item -> Objects.equals(item.getName(), name) && Objects.equals(item.getDate(), date))
                .findFirst()
                .orElse(null);
    }

    public List<Tuple> findAll(String name) {
        return database.stream()
                .filter(item -> Objects.equals(item.getName(), name))
                .collect(Collectors.toList());
    }
}
