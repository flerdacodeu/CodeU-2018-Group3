import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class DisjointSet<T> {
    private List<DisjointItem> subsets;

    DisjointSet() {
        this.subsets = new ArrayList<>();
    }

    int size() {
        return subsets.size();
    }

    void makeSet(T content) {
        subsets.add(new DisjointItem(content));
    }

    void union(T a, T b) {
        find(a).ifPresent(parentOfA -> {
            find(b).ifPresent(parentOfB -> {
                if (parentOfA != parentOfB) {
                    parentOfA.addChild(parentOfB);
                    subsets.remove(parentOfB);
                }
            });
        });
    }

    private Optional<DisjointItem> find(T item) {
        for (DisjointItem subset : subsets) {
            if (isInSubSet(subset, item)) {
                return Optional.of(subset);
            }
        }
        return Optional.empty();
    }

    private boolean isInSubSet(DisjointItem subset, T item) {
        if (subset.getContent() == item) {
            return true;
        }
        for (DisjointItem child : subset.getChildren()) {
            if (isInSubSet(child, item)) {
                return true;
            }
        }
        return false;
    }

    private class DisjointItem {
        private T content;
        private List<DisjointItem> children;

        DisjointItem(T content) {
            this.content = content;
            this.children = new ArrayList<>();
        }

        T getContent() {
            return content;
        }

        void addChild(DisjointItem child) {
            children.add(child);
        }

        List<DisjointItem> getChildren() {
            return children;
        }
    }
}
