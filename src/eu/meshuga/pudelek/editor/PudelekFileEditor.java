package eu.meshuga.pudelek.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.fileEditor.FileEditorStateLevel;
import com.intellij.openapi.util.UserDataHolderBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.StringReader;

public class PudelekFileEditor extends UserDataHolderBase implements FileEditor {
    private JPanel mainPanel;
    private JEditorPane webPane;
    private PudelekEditorState state = new PudelekEditorState();

    public PudelekFileEditor(String articleHtml) throws IOException, BadLocationException {
        HTMLEditorKit htmlKit = new HTMLEditorKit();
        webPane.setEditorKit(htmlKit);

        HTMLDocument htmlDocument = (HTMLDocument) webPane.getDocument();
        htmlDocument.putProperty("IgnoreCharsetDirective", true);
        webPane.setSize(800, Integer.MAX_VALUE);
        htmlKit.read(new StringReader(articleHtml), htmlDocument, 0);
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return mainPanel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return mainPanel;
    }

    @NotNull
    @Override
    public String getName() {
        return "Data";
    }

    @NotNull
    @Override
    public FileEditorState getState(@NotNull FileEditorStateLevel fileEditorStateLevel) {
        return state;
    }

    @Override
    public void setState(@NotNull FileEditorState fileEditorState) {
        if (fileEditorState instanceof PudelekEditorState) {
            state = (PudelekEditorState) fileEditorState;
        }
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void selectNotify() {

    }

    @Override
    public void deselectNotify() {

    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener propertyChangeListener) {

    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener propertyChangeListener) {

    }

    @Nullable
    @Override
    public BackgroundEditorHighlighter getBackgroundHighlighter() {
        return null;
    }

    @Nullable
    @Override
    public FileEditorLocation getCurrentLocation() {
        return null;
    }

    @Nullable
    @Override
    public StructureViewBuilder getStructureViewBuilder() {
        return null;
    }

    @Override
    public void dispose() {

    }
}
