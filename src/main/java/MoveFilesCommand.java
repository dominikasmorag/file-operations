import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

class MoveFilesCommand implements Command {
    private final String source;
    private final String target;
    private final long minSizeInBytes;
    private final String extension;

    public MoveFilesCommand(String source, String target, long minSizeInBytes, String extension) {
        this.source = source;
        this.target = target;
        this.minSizeInBytes = minSizeInBytes;
        this.extension = extension;
    }

    public void execute() {
        try {
            moveFiles();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void moveFiles() throws IOException {
        try {
            Path sourcePath = FileSystems.getDefault().getPath(source);
            File[] list = sourcePath.toFile().listFiles();
            assert list!=null;
            for (File file : list) {
                Path targetPath = FileSystems.getDefault().getPath(target + file.getName());
                Path tmp = FileSystems.getDefault().getPath(file.toString());
                if (file.length() > minSizeInBytes && file.getName().endsWith(extension)) {
                    try {
                        Files.move(tmp, targetPath, StandardCopyOption.ATOMIC_MOVE);
                    } catch (AccessDeniedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
