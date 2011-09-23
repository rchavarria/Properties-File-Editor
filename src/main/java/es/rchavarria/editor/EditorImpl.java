package es.rchavarria.editor;

import java.io.IOException;
import java.util.List;

import es.rchavarria.editor.session.EditorSession;
import es.rchavarria.editor.util.FileLinesReader;
import es.rchavarria.editor.util.FileLinesWriter;

class EditorImpl implements Editor {

    private EditorSession session;

    public EditorImpl(final EditorSession session) {
        this.session = session;
    }

    @Override
    public void update(final String property, final String newValue) throws EditorException {
        try {
            FileLinesReader reader = new FileLinesReader(session.getInputStream());
            List<String> lines = reader.readLines();

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                int idx = line.indexOf("=");
                if (idx < 0) {
                    continue;
                }

                String readProperty = line.substring(0, idx);
                if (readProperty.trim().equals(property)) {
                    line = readProperty + "=" + newValue;
                    lines.set(i, line);
                    break;
                }
            }

            FileLinesWriter writer = new FileLinesWriter(session.getOutputStream());
            writer.writeLines(lines);

        } catch (IOException e) {
            String formatString = "Can't update property %1$s with new value %2$s";
            String msg = String.format(formatString, property, newValue);
            throw new EditorException(msg, e);
        }
    }
}
