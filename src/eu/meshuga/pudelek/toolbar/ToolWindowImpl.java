package eu.meshuga.pudelek.toolbar;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import eu.meshuga.pudelek.model.Article;

import java.awt.*;
import java.util.List;

public class ToolWindowImpl extends SimpleToolWindowPanel {

    public final PudelekWindow form;

    public ToolWindowImpl(Project project) {
        super(true);
        form = new PudelekWindow(project);

        add(form.getMainPanel(), BorderLayout.CENTER);
    }

    public void setData(List<Article> articles) {
        form.setData(articles);
    }
}
