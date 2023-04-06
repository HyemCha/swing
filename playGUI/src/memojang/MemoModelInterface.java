package memojang;

public interface MemoModelInterface {
    void createNew();

    void open(MemoView view);

    void save(MemoView view);

    void saveAs(MemoView view);

    void quit(MemoView view);
}
