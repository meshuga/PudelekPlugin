package eu.meshuga.pudelek.toolbar;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import eu.meshuga.pudelek.fs.PudelekVirtualFile;
import eu.meshuga.pudelek.model.Article;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PudelekWindow {
    private String idPlaceHolder;
    private JPanel mainPanel;
    private JTree tree;

    private DefaultMutableTreeNode top;
    private Project project;

    public PudelekWindow(Project project) {
        this.project = project;

        top = new DefaultMutableTreeNode("Pudelek library");
        TreeModel treeModel = new DefaultTreeModel(top);
        tree.setModel(treeModel);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                if (me.getClickCount() == 2) {
                    createArticle(me);
                }
            }
        });
    }

    private void createArticle(MouseEvent me) {
        TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
        if (tp != null) {
            Object lastPathComponent = tp.getLastPathComponent();
            if (lastPathComponent != null && lastPathComponent instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) lastPathComponent;
                Object userObject = treeNode.getUserObject();
                if (userObject instanceof Article) {
                    Article article = (Article) userObject;
                    FileEditorManager editorManager = FileEditorManager.getInstance(project);
                    PudelekVirtualFile file = new PudelekVirtualFile(article);
                    editorManager.openFile(file, true, true);
                }
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setData(List<Article> articles) {
        for (Article article : articles) {
            top.add(new DefaultMutableTreeNode(article));
        }
    }
}
