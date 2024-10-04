package puzzle.util;

import java.util.ArrayList;
import java.util.List;

public class ObjectUtil {
    public static <T extends Copyable<T>> List<T> copyList(List<T> original) {
        List<T> newList = new ArrayList<>();
        original.forEach(t -> newList.add(t.copy()));
        return newList;
    }
}
