public class Main {

        /* args[0] - source path
           args[1] - target path with backslash mark at the end
           args[2] - file's extension, for example: .bmp
           args[3] - minimum file size in bytes */

    public static void main(String[] args) {

        final String source = args[0];
        final String target = args[1];
        final long minSizeInBytes = Long.parseLong(args[2]);
        final String extension = args[3];

        MoveFilesCommand move = new MoveFilesCommand(source, target, minSizeInBytes, extension);
        move.execute();

        }

}

