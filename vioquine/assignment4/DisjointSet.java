import java.util.*;

class DisjointSet<T> {
    private HashMap<T, DisjointItem> subsets;

    DisjointSet() {
        this.subsets = new HashMap<>();
    }

    void makeSet(T content) {
        subsets.put(content, new DisjointItem());
    }

    void union(T a, T b) {
        find(a).ifPresent(parentOfA -> {
            find(b).ifPresent(parentOfB -> {
                if (parentOfA != parentOfB) {
                    parentOfA.addChild(parentOfB);
                    updateParent(parentOfB, parentOfA);
                }
            });
        });
    }

    private void updateParent(DisjointItem item, DisjointItem newParent) {
        item.setParent(newParent);
        for (DisjointItem child : item.getChildren()) {
            updateParent(child, newParent);
        }
    }

    private Optional<DisjointItem> find(T item) {
        if (subsets.containsKey(item)) {
            return Optional.of(subsets.get(item).getParent());
        }
        return Optional.empty();
    }

    int getSize() {
        Set<DisjointItem> parents = new HashSet<>();
        for (T key : subsets.keySet()) {
            find(key).ifPresent(parents::add);
        }
        return parents.size();
    }

    private class DisjointItem {
        private List<DisjointItem> children;
        private DisjointItem parent;

        DisjointItem() {
            this.children = new ArrayList<>();
            this.parent = this;
        }

        void addChild(DisjointItem child) {
            children.add(child);
        }

        List<DisjointItem> getChildren() {
            return children;
        }

        DisjointItem getParent() {
            return parent;
        }

        void setParent(DisjointItem parent) {
            this.parent = parent;
        }
    }
}
