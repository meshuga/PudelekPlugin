package eu.meshuga.pudelek.fs;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import eu.meshuga.pudelek.model.Article;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PudelekVirtualFile extends VirtualFile {

    private Article article;

    public PudelekVirtualFile(Article article) {
        this.article = article;
    }

    @NotNull
    @Override
    public String getName() {
        return article.getTitle();
    }

    @NotNull
    @Override
    public VirtualFileSystem getFileSystem() {
        return PudelekFileSystem.getInstance();
    }

    @NotNull
    @Override
    public String getPath() {
        return article.getUrl();
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public VirtualFile getParent() {
        return null;
    }

    @Override
    public VirtualFile[] getChildren() {
        return new VirtualFile[0];
    }

    @NotNull
    @Override
    public OutputStream getOutputStream(Object o, long l, long l1) throws IOException {
        return new ByteArrayOutputStream();
    }

    @NotNull
    @Override
    public byte[] contentsToByteArray() throws IOException {
        return new byte[0];
    }

    @Override
    public long getTimeStamp() {
        return 0;
    }

    @Override
    public long getLength() {
        return 0;
    }

    @Override
    public void refresh(boolean b, boolean b1, Runnable runnable) {

    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    public long getModificationStamp() {
        return 0L;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PudelekVirtualFile that = (PudelekVirtualFile) o;

        if (article != null ? !article.equals(that.article) : that.article != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return article != null ? article.hashCode() : 0;
    }
}
