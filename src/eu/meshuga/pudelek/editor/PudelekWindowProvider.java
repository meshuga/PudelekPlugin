package eu.meshuga.pudelek.editor;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.net.HttpConfigurable;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import eu.meshuga.pudelek.fs.PudelekVirtualFile;

import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;

public class PudelekWindowProvider implements FileEditorProvider, ApplicationComponent {

    private static final Logger LOG = Logger.getInstance(PudelekWindowProvider.class);
    private HttpConfigurable httpConfigurable;

    @Override
    public void initComponent() {
        httpConfigurable = ApplicationManager.getApplication().getComponent(HttpConfigurable.class);
    }

    @Override
    public void disposeComponent() {

    }

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return virtualFile instanceof PudelekVirtualFile;
    }

    @NotNull
    @Override
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        PudelekVirtualFile pudelekVirtualFile = (PudelekVirtualFile) virtualFile;
        PudelekFileEditor pudelekFileEditor = null;
        try {
            HttpURLConnection httpURLConnection = httpConfigurable.openHttpConnection(pudelekVirtualFile.getPath());
            Reader reader = new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8");
            pudelekFileEditor = new PudelekFileEditor(reader);
        } catch (IOException e) {
            LOG.info(e);
        } catch (BadLocationException e) {
            LOG.info(e);
        }
        return pudelekFileEditor;
    }

    @Override
    public void disposeEditor(@NotNull FileEditor fileEditor) {

    }

    @NotNull
    @Override
    public FileEditorState readState(@NotNull Element element, @NotNull Project project, @NotNull VirtualFile virtualFile) {
        return new PudelekEditorState();
    }

    @Override
    public void writeState(@NotNull FileEditorState fileEditorState, @NotNull Project project, @NotNull Element element) {

    }

    @NotNull
    @Override
    public String getEditorTypeId() {
        return "pudeleks";
    }

    @NotNull
    @Override
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.PLACE_BEFORE_DEFAULT_EDITOR;
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Pudelek.PudelekWindowProvider";
    }
}
