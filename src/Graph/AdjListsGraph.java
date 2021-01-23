package Graph;

import Pieces.Figure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализация графа на основе списков смежности
 */
public class AdjListsGraph implements Digraph {
    private List<List<Integer>> vEdjLists = new ArrayList<>();
    private int vCount = 0;
    private int eCount = 0;

    public AdjListsGraph(){
        for (int i = 0; i<144; i++){
            vCount++;
            vEdjLists.add(i, new LinkedList<>());
            for (int j = 0; j < 8; j++){
                vEdjLists.get(i).add(null);
            }
            graphWeight.add(null);
        }
    }

    public List<Figure> graphWeight = new ArrayList<>();

    private static Iterable<Integer> nullIterable = new Iterable<Integer>() {
        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public Integer next() {
                    return null;
                }
            };
        }
    };

    @Override
    public int vertexCount() {
        return vCount;
    }

    @Override
    public int edgeCount() {
        return eCount;
    }

    @Override
    public void addAdge(int v1, int v2,int nap) {
        int maxV = Math.max(v1, v2);
        // добавляем вершин в список списков связности
        for (; vCount <= maxV; vCount++) {
            vEdjLists.add(null);
            graphWeight.add(null);
        }
        if (!isAdj(v1, v2)) {
            vEdjLists.get(v1).set(nap,v2);
            eCount++;
            // для наследников
        }
    }

    public static <T> List<T> copyIterator(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

    private int countingRemove(List<Integer> list, int v) {
        int count = 0;
        if (list != null) {
            for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
                if (it.next().equals(v)) {
                    it.remove();
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void removeAdge(int v1, int v2) {
        eCount -= countingRemove(vEdjLists.get(v1), v2);
    }

    @Override
    public Iterable<Integer> adjacencies(int v) {
        return vEdjLists.get(v) == null ? nullIterable : vEdjLists.get(v);
    }
}
