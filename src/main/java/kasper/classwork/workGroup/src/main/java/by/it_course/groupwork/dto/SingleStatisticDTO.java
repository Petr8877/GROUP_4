package by.it_course.groupwork.dto;

import java.util.Map;

public class SingleStatisticDTO<K,V> {
    private Map<K,V> mapResultSort;

    public SingleStatisticDTO(Map<K, V> mapResultSort) {
        this.mapResultSort = mapResultSort;
    }

    public Map<K, V> getMapResultSort() {
        return mapResultSort;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<K, V> entry : mapResultSort.entrySet()) {
            builder.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return builder.toString();
    }
}
