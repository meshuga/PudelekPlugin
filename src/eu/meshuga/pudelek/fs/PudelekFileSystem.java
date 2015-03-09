package eu.meshuga.pudelek.fs;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.vcs.vfs.VcsFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileSystem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class PudelekFileSystem extends VirtualFileSystem implements ApplicationComponent {

    private static final String PROTOCOL = "pudelek";

    public static PudelekFileSystem getInstance() {
        return ApplicationManager.getApplication().getComponent(PudelekFileSystem.class);
    }

    @NotNull
    @Override
    public String getProtocol() {
        return PROTOCOL;
    }

    @Nullable
    @Override
    public VirtualFile findFileByPath(@NotNull String s) {
        return null;
    }

    @Override
    public void refresh(boolean b) {
    }

    @Nullable
    @Override
    public VirtualFile refreshAndFindFileByPath(@NotNull String s) {
        return null;
    }

    @Override
    public void addVirtualFileListener(@NotNull VirtualFileListener virtualFileListener) {

    }

    @Override
    public void removeVirtualFileListener(@NotNull VirtualFileListener virtualFileListener) {

    }

    @Override
    protected void deleteFile(Object o, @NotNull VirtualFile virtualFile) throws IOException {
        throw new RuntimeException(VcsFileSystem.COULD_NOT_IMPLEMENT_MESSAGE);
    }

    @Override
    protected void moveFile(Object o, @NotNull VirtualFile virtualFile, @NotNull VirtualFile virtualFile1) throws IOException {
        throw new RuntimeException(VcsFileSystem.COULD_NOT_IMPLEMENT_MESSAGE);
    }

    @Override
    protected void renameFile(Object o, @NotNull VirtualFile virtualFile, @NotNull String s) throws IOException {
        throw new RuntimeException(VcsFileSystem.COULD_NOT_IMPLEMENT_MESSAGE);
    }

    @NotNull
    @Override
    protected VirtualFile createChildFile(Object o, @NotNull VirtualFile virtualFile, @NotNull String s) throws IOException {
        throw new RuntimeException(VcsFileSystem.COULD_NOT_IMPLEMENT_MESSAGE);
    }

    @NotNull
    @Override
    protected VirtualFile createChildDirectory(Object o, @NotNull VirtualFile virtualFile, @NotNull String s) throws IOException {
        throw new RuntimeException(VcsFileSystem.COULD_NOT_IMPLEMENT_MESSAGE);
    }

    @NotNull
    @Override
    protected VirtualFile copyFile(Object o, @NotNull VirtualFile virtualFile, @NotNull VirtualFile virtualFile1, @NotNull String s) throws IOException {
        throw new RuntimeException(VcsFileSystem.COULD_NOT_IMPLEMENT_MESSAGE);
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Pudelek.PudelekFileSystem";
    }
}
