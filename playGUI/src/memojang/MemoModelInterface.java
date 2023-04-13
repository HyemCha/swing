package memojang;

import javax.swing.*;

public interface MemoModelInterface {
    void createNew();

    void open(MemoView view);

    void save(MemoView view);

    void saveAs(MemoView view);

    void quit(MemoView view);

    void find(MemoView view);

    void findAndReplace(MemoView view);

    JFrame description(MemoView view);
}
