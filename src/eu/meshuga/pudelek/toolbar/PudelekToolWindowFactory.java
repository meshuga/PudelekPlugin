package eu.meshuga.pudelek.toolbar;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import eu.meshuga.pudelek.model.Article;
import eu.meshuga.pudelek.model.ArticleRepository;

import java.util.List;

public class PudelekToolWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull final Project project, @NotNull ToolWindow toolWindow) {
        final ToolWindowImpl newToolWindowImpl = new ToolWindowImpl(project);
        Content content = ContentFactory.SERVICE.getInstance().createContent(newToolWindowImpl, "", false);

        toolWindow.setAvailable(true, null);
        toolWindow.setToHideOnEmptyContent(true);
        toolWindow.setTitle("Pudelek");

        toolWindow.getContentManager().addContent(content);

        toolWindow.activate(new Runnable() {
            @Override
            public void run() {
                ArticleRepository ArticleRepository = new ArticleRepository();
                List<Article> articles = ArticleRepository.getArticles();
                newToolWindowImpl.setData(articles);
            }
        });
    }
}
